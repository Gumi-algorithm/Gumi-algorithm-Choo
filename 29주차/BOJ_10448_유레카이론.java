import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_10448_유레카이론 {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw =new PrintWriter(System.out);
		
		int t=Integer.parseInt(br.readLine());
		
		//삼각형
		int[] tri=new int[1100];
		
		int[] ans=new int[3500];
		
		int i=1;
		int j=2;
		while(i<1100) {
			tri[j-1]=i;
			ans[i]=1;
			i+=j;
			j++;
		}
		j--;
		j--;
		
		for(i=1;i<j;i++) {
			for(int k=1;k<j;k++) {
				for(int l=1;l<j;l++) {
					int tmp=tri[i]+tri[k]+tri[l];
					ans[tmp]=1;
				}
			}
		}
		
		
		for(i=0;i<t;i++) {
			int k=Integer.parseInt(br.readLine());
			pw.println(ans[k]);			
		}
		
		
		
		pw.close();
		br.close();
	}

}
