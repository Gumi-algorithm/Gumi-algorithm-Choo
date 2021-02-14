import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class BOJ_10816_숫자카드2 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		
		int n=Integer.parseInt(br.readLine());
		Map<Integer, Integer> map=new HashMap<>();
		String[] str;
		str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			int now=Integer.parseInt(str[i]);
			if(map.get(now)==null)
				map.put(now, 1);
			else {
				map.replace(now, map.get(now)+1);
			}			
		}
		
		int m=Integer.parseInt(br.readLine());
		str=br.readLine().split(" ");
		for(int i=0;i<m;i++) {
			int now=Integer.parseInt(str[i]);
			if(map.get(now)==null)
				sb.append(0).append(" ");
			else
				sb.append(map.get(now)).append(" ");
		}
		pw.print(sb);
		
		pw.flush();
		pw.close();
		br.close();
	}
}