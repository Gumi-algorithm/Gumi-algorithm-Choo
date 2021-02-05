import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1937_욕심쟁이판다 {

	static int[][] arr;
	static int[][] isvisited;
	static int n;

	static int squar(int x, int y,int pre) {
		if(x>=n || x<0 ||y>=n || y<0)
			return 0;
		int now=arr[x][y];
		if(now<=pre)
			return 0;
		if(isvisited[x][y]!=0)
			return isvisited[x][y];


		int cnt=1;
		int tmp1=0,tmp2=0;
		tmp1=squar(x+1,y,now);
		tmp2=tmp2<tmp1?tmp1:tmp2;

		tmp1=squar(x-1,y,now);
		tmp2=tmp2<tmp1?tmp1:tmp2;

		tmp1=squar(x,y+1,now);
		tmp2=tmp2<tmp1?tmp1:tmp2;

		tmp1=squar(x,y-1,now);
		tmp2=tmp2<tmp1?tmp1:tmp2;

		cnt+=tmp2;

		isvisited[x][y]=cnt;

		return cnt;
	}


	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);



		int k;//판다가 살수있는 일수
		String[] str;


		n=Integer.parseInt(br.readLine());
		k=0;
		arr=new int[n][n];
		isvisited=new int[n][n];

		for(int i=0;i<n;i++) {
			str= br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}				
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				isvisited[i][j]=0;
			}
		}

		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				int tmp=squar(i, j,arr[i][j]-1);
				if(k<tmp) {
					k=tmp;
				}
			}
		}
		pw.print(k);

		br.close();
		pw.close();
	}
}
