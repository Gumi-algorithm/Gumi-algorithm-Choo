import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_11060_점프점프 {

	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		
		int [] arr= new int[n];
		String[] str= br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		
		Queue<int[]> dp=new LinkedList<>();
		//dp 2차원 배열의 큐로써
		//두번째 인자에 해당하는 값만큼 점프에서 도달할수 있는 위치를 저장
		
		//이미 방문한경우는 체크안핻됨 (BFS같은거임 이미 방문한곳을 다시방문한게 시간 더 오래걸린경우임)
		boolean[] isvisited=new boolean[n];
		
		//0인덱스를 0번점프해서 온거임
		dp.offer(new int[] {0,0});
		isvisited[0]=true;
		int isend=0;
		int ans=-1;
		while(!dp.isEmpty()) {
			int nowidx=dp.peek()[0];
			int nowjump=dp.poll()[1];
			int nownum=arr[nowidx];
			
			//점프 가능한경우 다 넣어
			for(int i=0;i<=nownum;i++) {
				
				if(nowidx+i>=(n-1)) {
					if(i>0)
						nowjump++;
					isend=1;
					break;
				}				
				if(isvisited[nowidx+i])
					continue;
				
				isvisited[nowidx+i]=true;
				dp.offer(new int[] {nowidx+i,nowjump+1});
			}
			if(isend==1) {
				ans=nowjump;
				break;
			}
			
			
		}
		
		pw.print(ans);		
		br.close();
		pw.flush();
		pw.close();		
	}

}
