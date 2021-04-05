import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_2141_우체국 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		int [][] arr=new int[n][2];
		long sum=0;
		for(int i=0;i<n;i++) {
			String[] str=br.readLine().split(" ");
			int a=Integer.parseInt(str[0]);
			int b=Integer.parseInt(str[1]);
			arr[i][0]=a;
			arr[i][1]=b;
			sum+=b;
		}
		
		Arrays.sort(arr,(a,b)->(a[0]-b[0]));
		
		//여러가지가 가능할 경우 더 작은위치를 출력해야함으로 왼쪽부터 시작
		long left=0;
		long right=sum;
		long min=sum;
		int ans=0;
		for(int i=0;i<n;i++) {
			right-=arr[i][1];
			long dif=Math.abs(left-right);			
			if(dif<min) {
				ans=arr[i][0];
				min=dif;
			}
			left+=arr[i][1];
		}
		pw.print(ans);
		
		
		br.close();
		pw.flush();
		pw.close();
	}
}
/*
무조건 사람이 많은 마을에 놓자
-> 100 100 100 1 101 1

직접 거리를 구할려면 
a마을 사람 * b마을사람 * 거리
가 되어야함 그런데 마을사람 최대 10억이니까 불가능

답보고품 ㅠ
우체국이 위치하는곳 기준으로 오른쪽 마을 사람 합, 왼쪽 마을사람 합의 차가 최소가 되면됨
*/