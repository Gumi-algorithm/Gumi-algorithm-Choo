import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_13908_비밀번호 {
	static int n,m;
	static int[] arr;
	static int[] isvisited=new int[10];
	
	static int dfs(int idx,int num) {
		if(idx==n) {
			for(int i=0;i<m;i++) {
				if(isvisited[arr[i]]==0)
					return 0;
			}
			
			return 1;
		}
		
		int ret=0;
		
		for(int i=0;i<10;i++) {
			isvisited[i]++;
			ret+=dfs(idx+1,num*10+i);
			isvisited[i]--;
		}
		return ret;
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);

		
		String[] str=br.readLine().split(" ");
		
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		arr=new int[m];
		str=br.readLine().split(" ");
		for(int i=0;i<m;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		
		int ans=dfs(0,0);
		
		pw.print(ans);		
		br.close();
		pw.flush();
		pw.close();	
	}

}
