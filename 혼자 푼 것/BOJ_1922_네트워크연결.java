import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

//프림 n^2
public class BOJ_1922_네트워크연결 {
	
	static ArrayList<int[]>[] arr;
	static int dist[];//각 인덱스까지의 최소비용(이걸 우선순위큐로 바꾸면 nlogn됨)
	static boolean isvisited[];//방문체크
	static int n,m;
	
	static int prim() {
		int ans=0;
		
		//초기화
		for(int i=0;i<=n;i++) {
			dist[i]=Integer.MAX_VALUE;
			isvisited[i]=false;
		}
		
		dist[1]=0;//1번을 시작지점으로 잡음
		for(int i=1;i<=n;i++) {
			int now=-1;
			int nowlength=Integer.MAX_VALUE;
			//현재 연결된 간선중에 가장 가중치가 적은 간선 골라
			for(int j=1;j<=n;j++) {
				if(!isvisited[j]&&nowlength>dist[j]) {
					now=j;
					nowlength=dist[j];
				}
			}
			if(now<0) {
				//선택된 간선이 없으면 탈출
				return ans;
			}
			//간선이 선택되면 해당 간선과 이어지는 정점을 방문처리해
			isvisited[now]=true;
			ans+=nowlength;//선택된 간선의 가중치만큼 리턴값에 누적시켜
			
			//추가된 정점과 연결된 간선정보들을 기존 간선들과 비교하여 dist에 저장해
			for(int j=0;j<arr[now].size();j++) {
				int vertex=arr[now].get(j)[0];
				int weight=arr[now].get(j)[1];
				dist[vertex]=Math.min(dist[vertex],weight);
			}			
		}
		return ans;
	}
	
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		n=Integer.parseInt(br.readLine());
		m=Integer.parseInt(br.readLine());
		
		dist=new int[n+1];
		isvisited=new boolean[n+1];
		arr=new ArrayList[n+1];
		for(int i=0;i<=n;i++) {
			arr[i]=new ArrayList<>();
		}
		String[] str;
		for(int i=0;i<m;i++) {
			str=br.readLine().split(" ");
			int a=Integer.parseInt(str[0]);
			int b=Integer.parseInt(str[1]);
			int c=Integer.parseInt(str[2]);
			arr[a].add(new int[]{b,c});
			arr[b].add(new int[]{a,c});			
		}
		
		int ans=prim();
		pw.print(ans);
		
		br.close();
		pw.flush();
		pw.close();
	}

}
