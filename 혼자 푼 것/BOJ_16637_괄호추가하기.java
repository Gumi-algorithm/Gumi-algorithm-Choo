import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_16637_괄호추가하기 {
	static String num;
	static int n;
	
	static int calculate(int a,int b,char op) {
		if(op=='+') {
			return a+b;
		}else if(op=='-')
			return a-b;
		else if(op=='*')
			return a*b;
		return 0;
	}

	static int bf(int val,int idx) {
		int result=Integer.MIN_VALUE;
		int tmp;
		char op;
		
		if(idx>=n)
			return val;
		
		//앞에서 얻은 값이랑 다음거랑 연산
		op=num.charAt(idx-1);
		tmp=calculate(val, num.charAt(idx)-'0', op);
		
		tmp=bf(tmp,idx+2);
		result=result<tmp?tmp:result;
		
		if(idx+2<n) {
			//앞에거 냅두고 다음,다다음꺼 먼저 계산
			tmp=calculate(num.charAt(idx)-'0', num.charAt(idx+2)-'0', num.charAt(idx+1));
			tmp=calculate(val,tmp, op);//괄호 중첩안되니 연산한거 다시 더함
			
			tmp=bf(tmp,idx+4);
			result=result<tmp?tmp:result;
		}
		return result;
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);
		int result;
		//input
		n=Integer.parseInt(br.readLine());
		num=br.readLine();

		result=bf(num.charAt(0)-'0',2);
		
		pw.print(Integer.toString(result));
		pw.close();
		br.close();
	}
}