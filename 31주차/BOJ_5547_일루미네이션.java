import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_5547_일루미네이션 {
	static int w,h;
	static int[][] arr;//input 받아옴
	static int[][] isvisited;// 방문여부체크
	static int[][] odd = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{0,-1}};
	static int[][] even = {{1,0},{0,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
	
	private static int dfs(int x,int y) {
		int ret=0;
		int[][] direction;
		//y가 짝수일때
		if(y%2==0) {
			direction=even;
		}else {//y가 홀수일때
			direction=odd;
		}		
		for(int i=0;i<6;i++) {
			int nx=x+direction[i][1];
			int ny=y+direction[i][0];
			
			if(nx<0 || nx>=w+2 || ny<0 || ny>=h+2)
				continue;
			if(isvisited[ny][nx]==1)
				continue;
			//건물이면 외벽길이 +1
			if(arr[ny][nx]==1) {
				ret++;
				continue;
			}			
			isvisited[ny][nx]=1;
			ret+=dfs(nx,ny);
		}
		return ret;
	}
	
	//백준 2636 치즈 처럼 풀었음
	// 배열을 상하좌우 한칸씩 크게 잡은뒤 건물이 없는 곳에서 DFS를 돌려서 외각에서 건물가 닿아있는곳만 길이 카운트함
	// 문제의 시작좌표는 1,1이었으나 상하좌우에  한칸씩 크게잡았기때문에 시작을 0,0부터 함
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
	
		String[] str=br.readLine().split(" ");
		w=Integer.parseInt(str[0]);
		h=Integer.parseInt(str[1]);
		
		int ans=0;

		arr=new int[h+2][w+2];
		isvisited=new int[h+2][w+2];
				
		for(int i=1;i<=h;i++) {
			str=br.readLine().split(" ");
			for(int j=1;j<=w;j++) {
				arr[i][j]=Integer.parseInt(str[j-1]);				
			}
		}

		//건물이 아닌곳에서 DFS를 돌려 외벽길이 카운트
		ans=dfs(0,0);
		
		pw.print(ans);
		br.close();
		pw.close();
	}
}
