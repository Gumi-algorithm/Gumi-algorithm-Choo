import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_20154_이구역의승자는누구야 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int[] alpabet= {3,2,1,2,3,3,3,3,1,1,3,1,3,3,1,2,2,2,1,2,1,1,2,2,2,1};
		
		char[] str=br.readLine().toCharArray();
		int[] arr=new int[str.length];
		
		for(int i=0;i<str.length;i++) {
			arr[i]=alpabet[str[i]-65];
		}
		
		for(int i=1;i<str.length;i*=2) {//증가폭			
			for(int j=0;j<str.length;j+=i*2) {//i*2씩 증가하며 arr[j], arr[j+i]를 더함
				if(j+i<str.length)
					arr[j]= (arr[j]+arr[j+i])%10;	
			}
		}
		
		if(arr[0]%2==0) {
			pw.print("You're the winner?");
		}else
			pw.print("I'm a winner!");
		
		
		br.close();
		pw.flush();
		pw.close();
	}
}