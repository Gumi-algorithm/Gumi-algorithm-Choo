import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_9421_소수상근수 {

	static boolean[] prime=new boolean[1000010];
	static int[] sg=new int[1000010];//상근수
	
	static int issgnum(int num) {
		int ret=0;
		
		if(sg[num]==2) {
			sg[num]=-1;
			return -1;
		}
		if(sg[num]!=0)
			return sg[num];
		
		int cpnum=num;
		while(true) {
			int tmp=cpnum%10;
			ret+= tmp*tmp;
			cpnum=cpnum/10;
			if(cpnum==0)
				break;					
		}
		
		if(ret==1) {
			sg[num]=1;//상근수가 맞다
		}
		else {
			sg[num]=2;//한번 거쳤던 애
			sg[num]=issgnum(ret);
		}
		
		return sg[num];
	}
	
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringBuilder sb=new StringBuilder();
		
		int n=Integer.parseInt(br.readLine());
		
		
		for(int i=2;i*i<1000010;i++) {
			if(prime[i]==true)
				continue;
			for(int j=i*i;j<1000010;j=j+i) {
				prime[j]=true;//소수기 아닌건 true
			}
		}
		
		for(int i=2;i<=n;i++) {
			if(prime[i])//소수 아니면 넘어가
				continue;
			int ret=issgnum(i);
			if(ret==1)
				sb.append(i).append("\n");
		}
		
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}