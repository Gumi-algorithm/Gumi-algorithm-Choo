import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_2667_단지번호붙이기 {
	static int[][] arr;
	static int[][] direction= {{1,0},{0,1},{-1,0},{0,-1}};
	static int n;
	
	static int dfs(int x,int y) {
		if(arr[x][y]==0)
			return 0;
		arr[x][y]=0;
		int cnt=1;
		for(int i=0;i<4;i++) {
			int nx=x+direction[i][0];
			int ny=y+direction[i][1];
			
			if(nx<0 || nx>=n || ny<0 ||ny>=n)
				continue;
			if(arr[nx][ny]==0)
				continue;
			
			
			cnt+=dfs(nx,ny);
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		ArrayList<Integer> ans=new ArrayList<>();

		
		String str;
		for (int i = 0; i < n; i++) {
			str = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int tmp=dfs(i,j);
				if(tmp!=0) {
					ans.add(tmp);
				}
			}
		}
		Collections.sort(ans);
		sb.append(ans.size()).append("\n");
		for(int i=0;i<ans.size();i++) {
			int now=ans.get(i);
			if(now!=0) {
				sb.append(now).append("\n");
			}
		}
		pw.print(sb);
		pw.flush();
		pw.close();
		br.close();
	}
}
