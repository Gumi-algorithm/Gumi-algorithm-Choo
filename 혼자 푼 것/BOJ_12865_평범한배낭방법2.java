import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_12865_평범한배낭방법2 {
	
	//mem[i][j]= max(mem[i-1][j], mem[i-1][j-w[i]]+v[i]);//안넣은거, 넣은거 중에 큰 가치
	static int[][] mem=new int[101][100100];
	static int[][] arr;
	static int n,k;
	
	static int knapsack(int idx,int w) {
		if(idx==-1)
			return 0;
		if(w==0)
			return 0;
		int ret=mem[idx][w];
		if(ret!=0)
			return ret;
		
		int tmp;
		tmp=knapsack(idx-1, w);
		ret=ret<tmp?tmp:ret;
		if(w-arr[idx][0]>=0) {
			tmp=knapsack(idx-1, w-arr[idx][0])+arr[idx][1];
			ret=ret<tmp?tmp:ret;
		}
		mem[idx][w]=ret;
		
		return ret;
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		
		String[] str=br.readLine().split(" ");
		
		n=Integer.parseInt(str[0]);
		k=Integer.parseInt(str[1]);
		arr=new int[n+1][2];
		
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			arr[i][0]=Integer.parseInt(str[0]);//무게
			arr[i][1]=Integer.parseInt(str[1]);//가치			
		}
		pw.print(knapsack(n-1,k));
		pw.flush();
		pw.close();
		br.close();
	}

}
//다른방법: https://chanhuiseok.github.io/posts/improve-6/
//위 블로그처럼 반복문써서 할수도 있음