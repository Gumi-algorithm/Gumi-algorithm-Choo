import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2644_촌수계산 {
	
	static int start,end;

	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		String[] str=br.readLine().split(" ");
	
		int a=Integer.parseInt(str[0]);
		int b=Integer.parseInt(str[1]);
		
		start=Math.min(a, b);
		end=Math.max(a, b);
		
		
		int m=Integer.parseInt(br.readLine());
	
		ArrayList<Integer> edge[]=new ArrayList[n+1];
		
		for(int i=0;i<=n;i++) {
			edge[i]=new ArrayList<>();
		}
		
		for(int i=0;i<m;i++) {
			str=br.readLine().split(" ");
			a=Integer.parseInt(str[0]);
			b=Integer.parseInt(str[1]);
			edge[a].add(b);
			edge[b].add(a);
		}
		int ans=-1;
		
		Queue<int[]> q=new LinkedList<>();
		q.offer(new int[] {start,0});
		int isvisited[] =new int[n+1];
		Arrays.fill(isvisited,0);
		while(!q.isEmpty()) {
			int now=q.peek()[0];
			int cnt=q.poll()[1];
			
			if(now==end) {
				ans=cnt;
				break;
			}
			int size=edge[now].size();
			for(int i=0;i<size;i++) {
				int tmp=edge[now].get(i);
				if(isvisited[tmp]==1)
					continue;
				isvisited[tmp]=1;
				q.offer(new int[] {tmp,cnt+1});
			}			
		}
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}

}
