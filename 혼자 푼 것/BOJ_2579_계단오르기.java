import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2579_계단오르기 {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		
		int[] arr=new int[n];
		int[][] mem=new int[n][3];//각 계단 밟은 횟수잇때의 최댓값
		
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		mem[0][1]=arr[0];
		for(int i=0;i<n;i++) {	
			if(i-2>=0) {
				int tmp=Math.max(mem[i-2][1],mem[i-2][2]);
				mem[i][1]=arr[i] + tmp;
			}else
				mem[i][1]=arr[i];
			if(i-1>=0 && mem[i-1][1]!=0)
				mem[i][2]= arr[i]+ mem[i-1][1];
		}
		int ans=Math.max(mem[n-1][1],mem[n-1][2]);

		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}