import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class BOJ_1316_그룹단어체커 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		int ans=0;
		while(n-->0) {
			boolean jud=true;
			
			String str=br.readLine();
			
			char[] arr=str.toCharArray();
			
			Set<Character> set=new HashSet<>();
			char pre=' ';
			for(char c: arr) {
				if(set.contains(c) && pre!=c) {
					jud=false;
					break;
				}
				pre=c;
				set.add(c);
			}
			if(jud)
				ans++;			
		}
		
		pw.print(ans);
		br.close();
		pw.close();
	}

}
