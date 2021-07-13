import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_14595_동방프로젝트 {

	static int[] parent;
	
	
	static int find(int num) {
		if(parent[num]==num)
			return num;
		else return parent[num]=find(parent[num]);
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		int m=Integer.parseInt(br.readLine());
		
		parent=new int[n+1];
		
		//부모를 자기자신으로 설정
		for(int i=0;i<=n;i++)
			parent[i]=i;
			
		
		for(int i=0;i<m;i++) {
			String[] str=br.readLine().split(" ");
			int start=Integer.parseInt(str[0]);
			int end=Integer.parseInt(str[1]);
			
			int nowparent=end;
			int idx=start;
			while(idx<end) {
				//이미 부모가 자기자신이 아니면 벽이 허물어져 있는거니까 부모까지 건너뜀
				if(parent[idx]!=idx)
					idx=parent[idx];
				else {
					parent[idx]=end;
					idx++;
				}
			}
		}
		
		//아직도 자기자신을 부모로 가지면 허물어지지 않은거임
		int ans=0;
		for(int i=1;i<=n;i++) {
			if(parent[i]==i)
				ans++;
		}
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}