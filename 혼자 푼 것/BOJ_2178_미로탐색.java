import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2178_미로탐색 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n,m;
		String[] strarr=br.readLine().split(" ");
		
		n=Integer.parseInt(strarr[0]);
		m=Integer.parseInt(strarr[1]);
		int[][] arr=new int[n][m];
		
		
		for(int i=0;i<n;i++) {
			String str=br.readLine();
			for(int j=0;j<m;j++) {
				arr[i][j]=str.charAt(j)-'0';
			}
		}
		
		Queue<int[]> q=new LinkedList<>();
		q.offer(new int [] {0,0,1});
		
		int[][] direction= {{1,0},{-1,0},{0,-1},{0,1}};
		int ans=0;
		while(!q.isEmpty()) {
			int x=q.peek()[0];
			int y=q.peek()[1];
			int length=q.poll()[2];
			
			if(x==n-1 && y==m-1) {
				ans=length;
				break;
			}
			
			for(int i=0;i<4;i++) {
				int nx=x+direction[i][0];
				int ny=y+direction[i][1];
				
				if(nx>=n || nx<0 || ny>=m || ny<0)
					continue;
				
				if(arr[nx][ny]==0)
					continue;
				
				arr[nx][ny]=0;
				q.offer(new int[]{nx,ny,length+1});				
			}
		}
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}

}
