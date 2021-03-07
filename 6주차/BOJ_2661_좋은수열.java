import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2661_좋은수열 {

	//좋은수열인지 확인하는 함수
	static boolean isGood(String str) {
		int size=str.length();
		//substring을통해 str의 뒤에서부터 i개씩 substring을 만들어 비교
		for(int i=1;i<=size/2;i++) {
			String a=str.substring(size-i, size);
			String b=str.substring(size-2*i,size-i);
			if(a.equals(b)) {
				return false;
			}
		}
		return true;
	}
	
	static String getPermutation(int idx,int n,String str) {
		if(idx==n) {
			return str.toString();
		}
		String ret=null;
		for(int i=1;i<=3;i++) {
			
			//좋은수열이 아니면 continue;
			if(!isGood(str+i))
				continue;
			
			ret=getPermutation(idx+1, n, str+i);
			if(ret!=null)
				return ret;
		}
		
		return ret;
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		
		String ans=getPermutation(0,n,"");

		pw.print(ans);
		pw.flush();
		pw.close();
		br.close();
	}
}