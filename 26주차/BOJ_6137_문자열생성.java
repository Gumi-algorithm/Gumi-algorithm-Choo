import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_6137_문자열생성 {

	static char[] arr;
	static StringBuilder sb=new StringBuilder();
	
	static int sameState(int s,int e) {
		
		if(s>=e)
			return 0;
		
		//달라지는 경우
		if(arr[s]!=arr[e]) {
			if(arr[s]>arr[e])
				return 0;//오른쪽을 골라야함
			else
				return 1;//왼쪽을 골라야함
		}
		
		//arr[s]와 arr[e]가 같은걸 가르키는경우 (1개만 남은경우)
		if(s==e) {
			return 0;
		}
		
		//BBBBBBABBBBB, BBBBBBCBBBBB이런경우

		return sameState(s+1, e-1);

	
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		
		arr=new char[n];
		int s=0,e=n-1;//arr의 시작점, 끝점
		
		for(int i=0;i<n;i++) {
			arr[i]=br.readLine().charAt(0);
		}
		
		
		
		for(int i=0;i<n;i++) {
			//80개 적고 개행
			if(i%80==0 && i!=0)
				sb.append("\n");
			if(arr[s]>arr[e]) {
				sb.append(arr[e--]);
			}
			else if(arr[s]<arr[e]) {
				sb.append(arr[s++]);
			}else {
				
				int num=sameState(s,e);

				if(num%2==0) {
					sb.append(arr[e--]);
				}else
					sb.append(arr[s++]);
			}
		}
		
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}
/*
이런 경우엔 오른쪽 B만을 고른뒤 A를 선택해야함
BBBBBBABBBBB

이런경우엔 B를 모두 다쓰고 C를 골라야댐
BBBBBBCBBBBB

이런경우 오른쪽 부터 해야함
DCBBABCD
-> DCBABBCD

이런경우 오른쪽 부터 가야함
DCBECBCD
-> DCBCDCBE

ABCDAEDCBA
-> AABBCCDAE


*/