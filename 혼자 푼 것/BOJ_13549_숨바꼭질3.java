import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_13549_숨바꼭질3{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n, k;//수빈이위치, 동생위치
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		k = Integer.parseInt(str[1]);
		
		//수빈이가 갈수 있는 경우는 +1, -1, *2 3가지 존재
		//가장 빠른시간을 찾아야하기때문에 BFS가 좋음
		boolean[] isvisited=new boolean[200001];//100000에서 *2할거 생각해서 크게잡음
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
			
			//*2 순간이동이 가중치가 0이기때문에 가중치를 1가지는 +1, -1보다 앞에 위치해야함 (반례 1 2 답 0)
			if(now*2<200000 && !isvisited[now*2]) {//*2한경우(이동시간 0이니까 nowtime +1안함)
				//now==k일때 break를 걸기때문에 짧은 시간이 먼저 큐에 들어가야함
				//반복문으로 2인경우 다 방문하게 해주자
				for(int i=now*2;i<200000;i=i*2) {
					if(isvisited[i])
						break;
					isvisited[i]=true;
					q.offer(new int[] {i,nowtime});
				}
			}			
			if(now-1>=0 && !isvisited[now-1]) {//-1한경우
				isvisited[now-1]=true;
				q.offer(new int[] {now-1,nowtime+1});
			}
			if(now+1<100000 && !isvisited[now+1]) {//+1한경우
				isvisited[now+1]=true;
				q.offer(new int[] {now+1,nowtime+1});
			}

		}
		pw.print(time);
				

		br.close();
		pw.flush();
		pw.close();
	}
}