import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_16890_창업 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String g = br.readLine();
		String c = br.readLine();
		
		char[] garr = g.toCharArray();
		char[] carr = c.toCharArray();
		
		Arrays.sort(garr);
		Arrays.sort(carr);
				
		int n= garr.length;
		int gf=0;
		int ge=n/2;
		if(n%2!=1)
			ge--;
		
		int ce=n-1;
		int cf = n/2;
		if(n%2==1)
			cf++;
		
		char[] ans = new char[n];
		int j=0;
		int ansf=0;
		int anse=n-1;
		for(int i=0;i<n;i++) {
			if(j==0) {
				if(garr[gf]< carr[ce])
					ans[ansf++]=garr[gf++];
				else
					ans[anse--]=garr[ge--];					
			}
			else {
				if(carr[ce]> garr[gf])
					ans[ansf++]=carr[ce--];			
				else
					ans[anse--]=carr[cf++];	
			}
			j=1-j;
		}
		
		StringBuilder sb=new StringBuilder();
		for(char tmp:ans)
			sb.append(tmp);
		pw.print(sb);
		pw.flush();
		pw.close();
		br.close();
	}
}