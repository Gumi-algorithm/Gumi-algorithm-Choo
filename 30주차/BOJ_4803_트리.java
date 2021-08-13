import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_4803_트리 {
	static int[] arr=null;
	
	private static int find(int num) {
		if(num==arr[num])
			return arr[num];
		
		return find(arr[num]);
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		StringBuilder sb=new StringBuilder();
		
		int t=0;
		while(true) {
			t++;
			int n,m;
			String[] str=br.readLine().split(" ");
			
			n=Integer.parseInt(str[0]);
			m=Integer.parseInt(str[1]);
			
			if(n==0 && m==0)
				break;
			
			arr=new int[n+1];
			boolean[] isvisited=new boolean[n+1];
			
			for(int i=0;i<n+1;i++) {
				arr[i]=i;
			}
			
			for(int i=0;i<m;i++) {
				str=br.readLine().split(" ");
				int a=Integer.parseInt(str[0]);
				int b=Integer.parseInt(str[1]);
				
				int fa=find(a);
				int fb=find(b);
				
				//이러면 사이클 생기는거임
				if(fa==fb) {
					isvisited[fa]=true;
				}else {
					//둘중 한개라도 사이클이 이미 있는 애라면 다른애로 연결되면 더이상 트리가 아님
					if(isvisited[fa] || isvisited[fb]) {
						isvisited[fa]=true;
						isvisited[fb]=true;
					}
					
					if(fa<fb)
						arr[fb]=arr[fa];
					else
						arr[fa]=arr[fb];
				}
			}
			
			//모두 최상위 루트 가르키게함
			for(int i=0;i<n+1;i++) {
				arr[i]=find(i);
			}
			
			int ans=0;
			for(int i=1;i<n+1;i++) {
				if(isvisited[arr[i]])
					continue;
				isvisited[arr[i]]=true;
				ans++;
			}
			if(ans==0)
				sb.append("Case ").append(t).append(": No trees.\n");
			else if(ans==1)
				sb.append("Case ").append(t).append(": There is one tree.\n");
			else
				sb.append("Case ").append(t).append(": A forest of ").append(ans).append(" trees.\n");
		}
		pw.print(sb);
		pw.close();
		br.close();
	}

}
