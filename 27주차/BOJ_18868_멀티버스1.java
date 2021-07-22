import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_18868_멀티버스1 {
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);

		int m, n;
		
		String[] str=br.readLine().split(" ");
		
		m=Integer.parseInt(str[0]);
		n=Integer.parseInt(str[1]);
		

		int[][] arr=new int[m][n];
	
		
		for(int i=0;i<m;i++) {
			str=br.readLine().split(" ");
	
			for(int j=0;j<n;j++) {
				int num=Integer.parseInt(str[j]);
				arr[i][j]=num;
			}
		}

		int ans=0;
		for(int i=0;i<m;i++) {
			for(int j=i+1;j<m;j++) {
				int isSame=1;
				for(int k=0;k<n;k++) {
					for(int l=k+1;l<n;l++) {
						if(arr[i][k]<arr[i][l] && arr[j][k]<arr[j][l])
							continue;
						if(arr[i][k]==arr[i][l] && arr[j][k]==arr[j][l])
							continue;
						if(arr[i][k]>arr[i][l] && arr[j][k]>arr[j][l])
							continue;
						
						isSame=0;
						break;						
					}
					if(isSame==0)
						break;
				}
				if(isSame==1)
					ans++;
			}
		}
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}