import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_14719_빗물 {

	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int h,w;
		
		String[] str=br.readLine().split(" ");
		
		h=Integer.parseInt(str[0]);
		w=Integer.parseInt(str[1]);
		
		int[] arr=new int[w];
		str=br.readLine().split(" ");
		for(int i=0;i<w;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		
		int now=arr[0];//지금까지 지나온 블록중 가장 높은 블록
		int sum=0;//빗물의 합
		int cnt=0;//now가 선정되고 이때까지 지나온 블록 갯수
		for(int i=1;i<w;i++) {
			if(now<=arr[i]) {//i번째 블록이 now보다 크면 now갱신
				now = arr[i];
				cnt=0;
			}else {//i번째 블록이 now보다 작으면 그 차이만큼 빗물이 쌓임
				sum += now-arr[i];
				cnt++;
			}
		}
		
		//만약 마지막 블록이 now보다 작은경우 빗물은 마지막 블록만큼 쌓이게되니까
		//cnt만큼 되돌아가면서  빗물을 수정해
		int last=arr[w-1];//되돌아 오면서 확인할 now
		if(now>last) {
			//일단 맨 마지막꺼 뺌
			sum-= (now-last);
			for(int i=2;i<=cnt;i++) {
				int idx=w-i;
				sum -= (now-arr[idx]);//일단 잘못 더해진애들 빼
				if(last<arr[idx]) {//더 큰칸 나오면 last높이 갱신
					last=arr[idx];
				}else {
					sum+=last-arr[idx];
				}
			}			
		}
		
		pw.print(sum);
		pw.flush();
		pw.close();
		br.close();
	}
}
/*
4 4
4 3 2 1 

답:0
오답:-3
*/