import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_12018_YonseiTOTO {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n,m;
		String[] str=br.readLine().split(" ");
		
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		int[] minpoint=new int[n];
		for(int i=0;i<n;i++) {
			int p,l;
			str=br.readLine().split(" ");
			p=Integer.parseInt(str[0]);
			l=Integer.parseInt(str[1]);
			
			int[] arr=new int[p];
			str=br.readLine().split(" ");
			for(int j=0;j<p;j++) {
				arr[j]=Integer.parseInt(str[j]);
			}
			Arrays.sort(arr);
			
			int tmp=arr.length-l;
			if(tmp<0) {
				minpoint[i]=1;
			}else
				minpoint[i]=arr[tmp];			
		}
		
		Arrays.sort(minpoint);
		int ans=0;
		for(int i=0;i<n;i++) {
			m-=minpoint[i];
			if(m<0)
				break;
			
			ans++;
		}
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}