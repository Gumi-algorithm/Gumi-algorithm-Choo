import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BOJ_4949_균형잡힌세상 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

		char stk[]=new char[110];
		int stkidx=0;
		String str;
		int result;
		while(true) {
			str=br.readLine();
			if(str.equals("."))
				break;
			result=1;
			stkidx=0;
			for(int i=0;i<str.length();i++) {
				char now=str.charAt(i);

				if(now=='('||now=='[') {
					stk[stkidx++]=now;
				}else if(now==')') {
					if(stkidx-1>=0&&stk[stkidx-1]=='(') 
						stk[--stkidx]=0;
					else 
						result=0;
				}else if(now==']') {
					if(stkidx-1>=0&&stk[stkidx-1]=='[')
						stk[--stkidx]=0;
					else
						result=0;						
				}
				if(result==0)
					break;
			}
			
			if(result==1 && stkidx==0)
				bw.write("yes\n");
			else
				bw.write("no\n");

		}
		br.close();
		bw.close();
	
	}
}
