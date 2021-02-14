import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

//유클리드 호제법(최대공약수)
//참고: https://velog.io/@yerin4847/W1-%EC%9C%A0%ED%81%B4%EB%A6%AC%EB%93%9C-%ED%98%B8%EC%A0%9C%EB%B2%95
//최소공배수*최대공약수=a*b
public class BOJ_2609_최대공약수와최소공배수 {

	//유클리드 호제범
	static int euclid(int a,int b) {
		int num=a%b;//모듈러연산을 계속해서 0이나올때 작은수가 최대공약수
		if(num==0)
			return b;
		else
			return euclid(b,num);
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		String[] str=br.readLine().split(" ");
		int a,b;
		
		a=Integer.parseInt(str[0]);
		b=Integer.parseInt(str[1]);
		
		if(a<b) {//a를 큰값으로 바꿈
			int tmp=a;
			a=b;
			b=tmp;
		}
		int gcd=euclid((int)a,(int)b);//최대공약수
		long lcm=(long)(a)/gcd*b;//최소공배수(범위 넘어갈까바 곱,나누기 순서바꿈)
		pw.print(gcd+"\n"+lcm);
		
		br.close();
		pw.flush();
		pw.close();
	}
}