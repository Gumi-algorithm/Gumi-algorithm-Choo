import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1932_정수삼각형 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		int[][] arr=new int[n][n];
		int[] mem=new int[n];
		String[] str;
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<=i;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}
		}
		
		//가장 위에서부터 아래로 내려가면서 최대값 저장
		//먼저 가장 아랫줄 mem에 저장

		mem[0]=arr[0][0];
	
		
		for(int i=1;i<n;i++) {
			for(int j=i;j>=0;j--) {
				int tmp=0;
	
				//왼쪽 위일때 인덱스 벗어날경우 체크
				if(j-1>=0)
					tmp=mem[j-1];

	
				mem[j]=arr[i][j]+Math.max(tmp, mem[j]);
			}
		}
		int ans=0;
		for(int i=0;i<n;i++) {
			ans=Math.max(ans, mem[i]);
		}
		pw.print(ans);
		pw.flush();
		pw.close();
		br.close();
	}
}