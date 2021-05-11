import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_7490_0만들기 {

	static int n;
	static ArrayList<String> ans;
	
	
	static int cal(String[] arr,int idx,int pre,String op) {
		if(idx>=arr.length || arr[idx]==null )
			return pre;
		
		int ret=0;
		if(arr[idx].equals("+")) {
			ret = cal(arr,idx+1,pre,"+");
		}else if(arr[idx].equals("-")) {
			ret = cal(arr,idx+1,pre,"-");
		}else{//숫자가 들어왔을때
			if(op.equals("+")) {
				ret = cal(arr,idx+1,pre+Integer.parseInt(arr[idx]),"");
			}else if(op.equals("-")) {
				ret = cal(arr,idx+1,pre-Integer.parseInt(arr[idx]),"");
			}			
		}
		return ret;
	}
	static String[] merge(char[] arr) {
		String[] str=new String[arr.length-1];
		int sidx=0;
		for(int i=0;i<arr.length-1;i++) {
			if(arr[i]==' ') {
				str[sidx-1]+=Character.toString(arr[++i]);
			}else
				str[sidx++]=Character.toString(arr[i]);
		}
		
		return str;
	}
	static void dfs(int num, char[] stk,int idx) {
		if(num>n) {
			
			String[] str=merge(stk);
			int val=cal(str,2,Integer.parseInt(str[0]), str[1]);
		
			if(val==0) {
				StringBuilder sb=new StringBuilder();
				for(int i=0;i<stk.length-1;i++) {
					sb.append(stk[i]);
				}
				ans.add(sb.toString());
			}
			return;
		}
		
		//+
		stk[idx]='+';
		stk[idx+1]=(char)(num+'0');
		dfs(num+1,stk,idx+2);

		//-
		stk[idx]='-';
		stk[idx+1]=(char)(num+'0');
		dfs(num+1,stk,idx+2);
		
		//' '
		stk[idx]=' ';
		stk[idx+1]=(char)(num+'0');
		dfs(num+1,stk,idx+2);
		
		return;
	}
	
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		int t=Integer.parseInt(br.readLine());
		
		while(t-->0) {
			ans=new ArrayList<>();
			n=Integer.parseInt(br.readLine());
			char[] stk=new char[n*2];
			stk[0]='1';
			//시작은 2부터, 1은 미리 더해져있다고 생각
			dfs(2,stk,1);
			
			Collections.sort(ans);
			
			for(String str:ans) {
				sb.append(str).append("\n");
			}	
			sb.append("\n");
		}
		
		
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}