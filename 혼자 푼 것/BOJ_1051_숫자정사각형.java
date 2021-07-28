import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1051_숫자정사각형 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n,m;
		
		String[] str=br.readLine().split(" ");
		
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		int[][] arr=new int[n][m];
		for(int i=0;i<n;i++) {
			str=br.readLine().split("");
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}
		}
		
		//한변의 길이
		int maxlength=1;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				
				for(int k=j+maxlength;k<m;k++) {
					if(arr[i][j]==arr[i][k]) {//위에 두개 같으면 밑에 같은 거리에 같은값이 있는지 확인
						int val=arr[i][j];
						int dif=k-j;
						if(i+dif>=n)
							continue;
						
						if(arr[i+dif][j]!=val)
							continue;
						if(arr[i+dif][k]!=val)
							continue;
						
						maxlength=Math.max(maxlength, dif+1);
												
					}
				}
				
			}
		}
		
		pw.print(maxlength*maxlength);
		br.close();
		pw.flush();
		pw.close();
	}

}
