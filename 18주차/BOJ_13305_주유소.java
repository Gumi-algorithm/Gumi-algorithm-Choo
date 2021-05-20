import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_13305_주유소 {

	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		
		long[] load=new long[n-1];
		String[] str=br.readLine().split(" ");
		for(int i=0;i<n-1;i++) {
			load[i]=Integer.parseInt(str[i]);
		}
		
		long[] city=new long[n];
		str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			city[i]=Integer.parseInt(str[i]);
		}
		long ans=0;
		for(int i=0;i<n-1;i++) {
			long nowcost=city[i];
			ans+=load[i]*nowcost;
			for(int j=i+1;j<n-1;j++) {
				//현재도시보다 다음도시가 더 쌀경우
				if(nowcost>city[j]) {
					break;
				}else {//현재도시가 다음도시보다 비쌀경우
					ans+=load[j]*nowcost;//현재도시에서 기름넣음
					i=j;
				}
			}
		}
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();	
	}
}