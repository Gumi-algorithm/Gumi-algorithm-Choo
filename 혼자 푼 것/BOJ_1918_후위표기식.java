import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1918_후위표기식 {


	public static void main(String[] args)throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		String str=br.readLine();
		char[] stk=new char[110];
		char[] op=new char[110];
		int idx=0;
		int opidx=0;
		
		for(int i=0;i<str.length();i++) {
			char now=str.charAt(i);
			if(now>='A' && now<='Z') {//숫자 들어왔을때
				stk[idx++]=now;
			}else {//식 들어왓을때
				if(opidx==0) {
					op[opidx++]=now;
					continue;
				}
				//우선순위 *,/>+,-
				if(now=='+'||now=='-') {
					//우선순위 높은애들(*,/)이나 같은애들 잇으면 빼내고 집어넣어
					while(opidx-1>=0 && (op[opidx-1]=='*'||op[opidx-1]=='/'||op[opidx-1]=='+'||op[opidx-1]=='-')) {
						stk[idx++]=op[--opidx];
					}
					op[opidx++]=now;
				}else if(now=='*'||now=='/') {
					//우선순위 같은애들(*,/)있으면 빼고 넣고 아니면 그냥넣어
					while(opidx-1>=0 && (op[opidx-1]=='*'||op[opidx-1]=='/')) {
						stk[idx++]=op[--opidx];
					}
					op[opidx++]=now;
				}else if(now=='('){
					//그냥삽입
					op[opidx++]=now;
				}else if(now==')') {
					//(만날때까지 다 빼내
					while(opidx-1>=0 && op[opidx-1] !='(')
						stk[idx++]=op[--opidx];
					opidx--;//그리고 (도 빼버려
				}		
			}
		}
		while(opidx>0)
			stk[idx++]=op[--opidx];
		for(int i=0;i<idx;i++) {
			pw.print(stk[i]);
		}
		pw.flush();
		br.close();
		pw.close();
	}
}