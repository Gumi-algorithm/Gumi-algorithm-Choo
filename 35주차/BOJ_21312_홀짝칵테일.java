import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_21312_홀짝칵테일 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int[] arr=new int[3];
		
		String[] str=br.readLine().split(" ");
		
		arr[0]=Integer.parseInt(str[0]);
		arr[1]=Integer.parseInt(str[1]);
		arr[2]=Integer.parseInt(str[2]);
		
		long num=1;
		int tmp=0;
		//모든 홀수의 곱 출력
		for(int a : arr) {
			if(a%2==1) {
				num*=a;
				tmp=1;
			}
		}
		
		//만약 모두 짝수라면 모든 짝수의 곱 출력
		if(tmp==0) {
			for(int a : arr) {
				num*=a;
			}
		}
		
		pw.print(num);
		pw.flush();
		pw.close();
		br.close();
	}
	
}
