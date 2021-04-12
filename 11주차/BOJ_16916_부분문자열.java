import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_16916_부분문자열 {
	static String str,p;
	static int[] fail;
	static void failFunc(String str) {
		//위 아래 문자열을 놓고 아랫부분이 움직인다 생각해
		//i=0;//윗부분 
		int j=0;//아랫부분
		char[] p= str.toCharArray();
		for(int i=1;i<str.length();i++) {
			
			//한글짜라도 같은부분이 앞에서 있었는데 달라졋으면
			//아랫부분은 다시 한칸씩 땡기면서 같은부분 있는지 비교해
			// ABCABCABCABB
			//    ABCABCABCABB
			while(j>0&&p[i]!=p[j])
				j=fail[j-1];
			
			//같은부분 있으면 fail배열 증가시키고 다음 확인
			if(p[i]==p[j])
				fail[i]=++j;
			
			//그냥 다르면 아무것도 안하고 넘어가			
		}		
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		str=br.readLine();
		p=br.readLine();
		
		fail=new int[p.length()];
		
		//실패함수생성
		failFunc(p);
		
		//문자열 비교 시작
		//위 아래 문자열을 놓고 아랫부분이 움직인다 생각해
		//i=0;//윗부분 
		int j=0;//아랫부분
		char[] strc= str.toCharArray();
		char[] pc=p.toCharArray();
		int ans=0;
		for(int i=0;i<str.length();i++) {
			
			//한글짜라도 같은부분이 앞에서 있었는데 달라졋으면
			//아랫부분은 다시 한칸씩 땡기면서 같은부분 있는지 비교해
			while(j>0&&strc[i]!=pc[j])
				j=fail[j-1];
			
			//같은부분 있으면 fail배열 증가시키고 다음 확인
			if(strc[i]==pc[j]) {
				j++;
				if(j==p.length()) {
					ans=1;
					break;
				}
			}
			//그냥 다르면 아무것도 안하고 넘어가			
		}		
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}