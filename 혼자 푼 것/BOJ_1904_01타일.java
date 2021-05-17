import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1904_01타일 {

	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		
		int[] arr=new int[2];
		arr[0]=1;
		arr[1]=1;
		int t=0;
		
		for(int i=1;i<n;i++) {
			arr[t]= (arr[t]+arr[1-t])%15746;
			t=1-t;
		}
		
		pw.print(arr[1-t]);
		br.close();
		pw.flush();
		pw.close();	
	}
}
/*
1	1
2	00	11
3	001	100	111
4	0000	0011	1001	1100	1111
5	00001	00100	10000	00111	10011	11001	11100	11111

n	n-2뒤에 00을 붙이는경우 + n-1뒤에 1을 붙이는 경우
*/