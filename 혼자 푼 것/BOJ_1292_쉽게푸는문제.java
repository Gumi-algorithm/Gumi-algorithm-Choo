import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1292_쉽게푸는문제 {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);

		String[] str=br.readLine().split(" ");
		
		int a=Integer.parseInt(str[0]);
		int b=Integer.parseInt(str[1]);
		
		int n=1;
		int sum=0;
		int i=1;
		while(true) {
			for(int j=0;j<i;j++) {
				if(n>=a && n<=b) {
					sum+=i;
				}
				n++;	
				if(n>b)
					break;
			}
			if(n>b)
				break;
			i++;
		}
				
		pw.print(sum);		
		br.close();
		pw.close();
	}
}