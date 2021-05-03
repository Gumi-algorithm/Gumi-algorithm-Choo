import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2877_4와7 {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int k=Integer.parseInt(br.readLine());
		
		int n=0;//몇번째인가 나타내는 변수
		int idx=2;//숫자 갯수
		
		//몇개의 숫자로 이루어졋는지 확인
		for(idx=0;;idx++) {
			n+=(1<<idx);
			if(n>k) {
				n-=(1<<idx);
				break;
			}
		}
		
		int dif=k-n;
		int[] ans=new int[idx];
		
		//이제 각 자리수를 구하는거임
		for(int i=0;i<idx;i++) {
			int num= dif & 1;
			if(num==0)
				ans[i]=4;
			else
				ans[i]=7;
			
			//비트 한칸 오른쪽으로 땡김 (즉, 제일 오른쪽 비트 날림)
			dif=dif>>1;			
		}
		
		StringBuilder sb=new StringBuilder();
		//정답 출력(답을 인덱스 0에 1의 자리를 넣었으니까 반대로 출력)
		for(int i=0;i<idx;i++) {
			sb.append(ans[idx-i-1]);
		}
		pw.print(sb);
		pw.flush();
		pw.close();
		br.close();
	}
}