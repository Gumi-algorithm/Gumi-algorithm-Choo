import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_13913_숨바꼭질4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n, k;//수빈이위치, 동생위치
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		k = Integer.parseInt(str[1]);
		
		//수빈이가 갈수 있는 경우는 +1, -1, *2 3가지 존재
		//가장 빠른시간을 찾아야하기때문에 BFS가 좋음
		//역추적을위해 int형으로 만듬 (0은 방문안한거 -1은 -1해서 접근, 1은 +1해서 접근, 2는 *2로 접근)
		int[] isvisited=new int[200001];//100000에서 *2할거 생각해서 크게잡음
		Queue<int[]> q=new LinkedList<>();
		q.offer(new int[] {n,0});
		int time=0;//제일처음 시작은 0으로 쳐야되니까 일단 -1로해둠
		while(!q.isEmpty()) {
			int now=q.peek()[0];
			int nowtime=q.poll()[1];
			if(now==k) {
				time=nowtime;
				break;
			}

			//더 짧은시간에 이미 방문햇으면 방문안해도 되니까 isvisited로 체크해줘
			if(now-1>=0 && isvisited[now-1]==0) {//-1한경우
				isvisited[now-1]=-1;
				q.offer(new int[] {now-1,nowtime+1});
			}
			if(now+1<100000 && isvisited[now+1]==0) {//+1한경우
				isvisited[now+1]=1;
				q.offer(new int[] {now+1,nowtime+1});
			}
			if(now*2<200000 && isvisited[now*2]==0) {//*2한경우
				isvisited[now*2]=2;
				q.offer(new int[] {now*2,nowtime+1});
			}
		}
		pw.print(time+"\n");
		int nowval=k;
		int[] arr=new int[time];//방문햇던게 꺼꾸로 들어가잇으니까 여기에 넣엇다가 뒤집어서 출력
		int arridx=0;
		//isvisited에 남은 방문 기록들을 역추적에 arr에 담음
		while(nowval!=n) {
			arr[arridx++]=nowval;
			int trace=isvisited[nowval];
			if(trace==-1)
				nowval+=1;
			else if(trace==1)
				nowval-=1;
			else if(trace==2)
				nowval/=2;
		}
		pw.print(n+" ");//time개 만큼 뒤에서 담았으니까 첫 출발점은 안담겨잇음 그래서 그냥 먼저 출력해줌
		for(int i=time-1;i>=0;i--)
			pw.print( arr[i]+" ");

		br.close();
		pw.flush();
		pw.close();
	}
}