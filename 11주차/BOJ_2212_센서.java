import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_2212_센서 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n = Integer.parseInt(br.readLine());
		int k=Integer.parseInt(br.readLine());
		
		//인풋
		int[] arr=new int[n];
		String[] str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		//센서를 오름차순으로 정렬
		Arrays.sort(arr);
		
		//각 센서사이의 값(차이값)들을 저장
		Integer[] dif= new Integer[n-1];
		for(int i=0;i<n-1;i++) {
			dif[i]=arr[i+1]-arr[i];		
		}
		
		//첫 센서부터 마지막센서까지의 거리 저장
		int sum=arr[n-1]-arr[0];
		
		//가장 큰 차이값을 가지는 것부터 영역을 나눌꺼니까 내림차순으로 차이값 정렬
		Arrays.sort(dif,(a,b)->(b-a));
		
		if(k>(n-1))//집중국이 센서보다 많은경우
			sum=0;
		else {
			for(int i=0;i<k-1;i++) {//집중국이 2개이면 1개의 사잇값을 무시할수 있으니까 k-1만큼 반복해
				sum-=dif[i];
			}
		}
		pw.print(sum);		
		br.close();
		pw.flush();
		pw.close();
	}
}