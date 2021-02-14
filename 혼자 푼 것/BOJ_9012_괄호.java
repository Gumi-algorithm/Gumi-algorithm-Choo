import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_9012_괄호 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		String str;
		int stkidx=0;
		int ans=1;
		for(int i=0;i<n;i++) {
			str=br.readLine();
			stkidx=0;
			ans=1;
			for(int j=0;j<str.length();j++) {
				char now=str.charAt(j);
				if(now=='(') {
					stkidx++;
				}
				else if(now==')') {
					stkidx--;
					if(stkidx<0) {	
						ans=0;
						break;
					}
				}
			}
			//다 빠져나왔을때 스택이 비어있어야함
			if(stkidx!=0)
				ans=0;
			
			if(ans==1)
				pw.print("YES\n");
			else 
				pw.print("NO\n");
		}		
		br.close();
		pw.flush();
		pw.close();
	}
}