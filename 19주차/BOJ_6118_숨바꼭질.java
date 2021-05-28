import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_6118_숨바꼭질 {
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		int n,m;
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		ArrayList<Integer> edge[]=new ArrayList[n+1];
		
		for(int i=0;i<=n;i++) {
			edge[i]=new ArrayList<>();
		}
		
		for(int i=0;i<m;i++) {
			str=br.readLine().split(" ");
			int tmp1=Integer.parseInt(str[0]);
			int tmp2=Integer.parseInt(str[1]);
			
			edge[tmp1].add(tmp2);
			edge[tmp2].add(tmp1);					
		}
		
		
		int[] isvisited=new int[n+1];
		isvisited[1]=1;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		q.add(1);//거리 (0을 방문 안한걸로 쓸거라서 시작지점 거리를 1로둠)
		int maxlen=0;
		while(!q.isEmpty()) {
			int cur=q.poll();
			int len=q.poll();
			
			maxlen=Math.max(maxlen, len);
			
			for(int i=0;i<edge[cur].size();i++) {
				int now=edge[cur].get(i);
				
				if(isvisited[now]!=0) {
					continue;
				}
				q.add(now);
				q.add(len+1);
				
				isvisited[now]=len+1;
			}			
		}
		ArrayList<Integer> ans=new ArrayList<>();
		for(int i=1;i<=n;i++) {
			if(maxlen==isvisited[i]) {
				ans.add(i);
			}
		}
		Collections.sort(ans);
		
		pw.print(ans.get(0)+" "+ (maxlen-1) +" " + ans.size());
		
		br.close();
		pw.flush();
		pw.close();
	}
}
