import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_2583_영역구하기 {
	
	static int [][]arr;
	static int [][]isvisited;
	static int m,n,squarNum;
	
	static int dfs(int x, int y) {
		int cnt=1;
		
		if(isvisited[x][y]==1 || arr[x][y]!=0)
			return 0;
		
		isvisited[x][y]=1;
		
		if(x+1<n)
			cnt+=dfs(x+1,y);
		if(x-1>=0)
			cnt+=dfs(x-1,y);
		if(y+1<m)
			cnt+=dfs(x,y+1);
		if(y-1>=0)
			cnt+=dfs(x,y-1);
		
		return cnt;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str=br.readLine().split(" ");
		m=Integer.parseInt(str[0]);
		n=Integer.parseInt(str[1]);
		squarNum=Integer.parseInt(str[2]);
		
		arr=new int[n][m];
		isvisited=new int[n][m];
		
		int x1,y1;
		int x2,y2;
		for(int i=0;i<squarNum;i++) {
			str=br.readLine().split(" ");
			x1=Integer.parseInt(str[0]);
			y1=Integer.parseInt(str[1]);
			x2=Integer.parseInt(str[2]);
			y2=Integer.parseInt(str[3]);
			
			for(int j=x1;j<x2;j++) {
				for(int k=y1;k<y2;k++) {
					arr[j][k]+=1;
				}
			}		
		}
		ArrayList<Integer> result=new ArrayList<Integer>();
		//탐색
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				int tmp=dfs(i,j);
				if(tmp!=0)
					result.add(tmp);
			}
		}
		
		Collections.sort(result);
		bw.write(Integer.toString(result.size())+"\n");
		for(int i=0;i<result.size();i++) {
			bw.write(Integer.toString(result.get(i))+" ");
		}
		
		bw.close();
		br.close();		
	}
}
