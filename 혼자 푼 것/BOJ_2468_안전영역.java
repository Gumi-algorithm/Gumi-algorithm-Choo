
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2468_안전영역 {
	static int n;
	static int[][] arr=new int[101][101];
	static int[][] isvisited=new int[101][101];//1이면 방문안한거 0이면 방문한거
	
	static void dfs(int i, int j,int height) {
		if(arr[i][j]<=height) {
			isvisited[i][j]=0;
			return;
		}
		if(isvisited[i][j]==0)//방문했으면 탈출
			return;
		
		isvisited[i][j]=0;
		
		if(i+1<n)			
			dfs(i+1,j,height);
		
		if(j+1<n)
			dfs(i,j+1,height);
		
		if(i-1>=0)
			dfs(i-1,j,height);
		
		if(j-1>=0)
			dfs(i,j-1,height);
		
		return;
	}
	
	//초기화 함수 isvisited의 알맞은 영역을 1로 초기화
	static void init() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				isvisited[i][j]=1;
			}	
		}	
	}
	
	static int verify(int height) {
		int count=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(arr[i][j]<=height) {
					isvisited[i][j]=0;
					continue;
				}
				if(isvisited[i][j]==1) {//방문안햇으면 들어가바
					dfs(i,j,height);
					count++;
				}
			}
		}
		return count;
	}
	
	public static void main (String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n=Integer.parseInt(br.readLine());
		int maxCount=0;
		int maxHeight=0;
		int tmp;
		int maxInput=0;
		
		//input
		for(int i=0;i<n;i++) {
			String arrtmp[]=br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(arrtmp[j]);
				maxInput=maxInput<arr[i][j]?arr[i][j]:maxInput;
			}
		}
		
		for(int i=0;i<=maxInput;i++) {
			//i=물높이  i=2일땐 2인 섬까지 잠긴거임
			
			//초기화
			init();
			
			//물높이 i일때 확인
			tmp=verify(i);
			if(tmp>maxCount) {
				maxCount=tmp;
				maxHeight=i;
			}
		}
		bw.write(Integer.toString(maxCount));
//		bw.write(Integer.toString(maxHeight));
		br.close();
		bw.close();
	}
}
