import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_2239_스도쿠 {
	static int arr[][];
	
	static int[] getPossible(int x,int y) {
		int[] p=new int[10];
		Arrays.fill(p, 1);
		//가로,세로
		for(int i=0;i<9;i++) {
			p[arr[x][i]]=0;
			p[arr[i][y]]=0;			
		}
		
		//3*3칸
		int xa=x/3*3;
		int xb=xa+3;
		int ya=y/3*3;
		int yb=ya+3;
		for(int i=xa;i<xb;i++) {
			for(int j=ya;j<yb;j++) {
				p[arr[i][j]]=0;
			}
		}
		
		return p;
	}
	
	static boolean dfs(int x,int y) {
		int isfirstx=0;
		int isfirsty=0;
		
		for(int i=0;i<9;i++) {
			if(isfirstx==0) {
				i=x;
				isfirstx=1;
			}
			for(int j=0;j<9;j++) {
				if(isfirsty==0) {
					j=y;
					isfirsty=1;
				}
				int now=arr[i][j];
				if(now!=0)
					continue;
				int[] possible=getPossible(i,j);

				for(int k=1;k<=9;k++) {					
					if(possible[k]==1) {
						arr[i][j]=k;
						if(dfs(i,j))
							return true;
					}
				}
				if(!(i==8 && j==8)) {
					arr[i][j]=0;
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		arr=new int[9][9];
		for(int i=0;i<9;i++) {
			String[] str=br.readLine().split("");
			for(int j=0;j<9;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}
		}
		
		dfs(0,0);
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}

}
