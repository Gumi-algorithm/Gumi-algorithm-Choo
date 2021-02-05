import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_11660_구간합구하기5 {
	
	
	public static void main(String[] args)throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);
		
		String[] str;
		int n,m;
		str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		int[][] arr =new int[n][n];
		int[][] arr2 =new int[n][n];
		
		
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
				arr2[i][j]=arr[i][j];
				if(i-1>=0)
					arr2[i][j]+=arr2[i-1][j];
				if(j-1>=0)
					arr2[i][j]+=arr2[i][j-1];
				if(i-1>=0&&j-1>=0)
					arr2[i][j]-=arr2[i-1][j-1];
			}
		}
		int x1,y1;
		int x2,y2;
		int ret;
		for(int i=0;i<m;i++) {
			str=br.readLine().split(" ");
	
			x1=Integer.parseInt(str[0])-1;
			y1=Integer.parseInt(str[1])-1;
			x2=Integer.parseInt(str[2])-1;
			y2=Integer.parseInt(str[3])-1;
			
			ret=arr2[x2][y2];
			if(x1-1>=0)
				ret-=arr2[x1-1][y2];
			if(y1-1>=0)
				ret-=arr2[x2][y1-1];
			if(x1-1>=0 && y1-1>=0)
				ret+=arr2[x1-1][y1-1];
			
			pw.print(ret+"\n");
			
		}
		pw.close();
		br.close();
	}
}
/*
1 	2	 3	 4
2 	3 	4 	5
3 	4 	5 	6
4 	5 	6 	7
=====================
1	3	6	10
3	8	15	24
6	15	27	42
10	24	42	64

arr2(i,j)= arr1(i,j) + arr2(i-1,j) + arr2(i,j-1) - arr2(i-1.j-1)

x1,y1  x2,y2구간함
arr2(x2,y2)-arr2(x2,y1-1)-arr2(x1-1,y2)+arr2(x1-1,y1-1) 
  */
