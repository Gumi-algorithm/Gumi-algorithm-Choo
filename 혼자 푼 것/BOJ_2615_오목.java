import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2615_오목 {
	//아래부터 반시계방향
	//0,0에서 시작할거라서 아래 오른 아래오른대각 위오른대각쪽만 가면됨 나머지는 반대방향에 같은돌 잇는지 카운트용도
	static int[][] direction= {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
	static int [][] arr;
	static int n=19;
	
	//한방향으로만 탐색(두방향 할랫는데 최악에선 결국 다돌아야되서 연산시간 늘어남)
	static int dfs(int x,int y,int didx, int cnt,int stone) {//x,y좌표, direction인덱스, 갯수, 1흰돌 2검은돌
		
		
		int nx=x+direction[didx][0];
		int ny=y+direction[didx][1];
		if(nx>=n || nx<0 || ny>=n || ny<0)
			return cnt;
		//돌 색상 다르면 리턴
		if(arr[nx][ny]!= stone)
			return cnt;
		
		//오목갯수 5개 넘어가면 리턴
		if(cnt+1>5)
			return -10;
		
		//한뱡향이라서 방문체크 필요없음
		int ret=dfs(nx,ny,didx,cnt+1,stone);	
		
		return ret;
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		
		arr=new int[19][19];
		
		String[] str; 
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(str[j]);				
			}
		}
		int ans=0;
		int ansx=0,ansy=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				for(int k=0;k<4;k++) {
					if(arr[i][j]!=0 ) {
						//반대방향(왼쪽)에 같은돌 있으면 넘어감(어차피 탐색 햇던 거라서 넘어가도됨)
						int nx=i+direction[(k+4)%8][0];
						int ny=j+direction[(k+4)%8][1];
								
						if(nx<n && nx>=0 && ny<n && ny>=0 && arr[nx][ny]==arr[i][j])
							continue;
						if(dfs(i,j,k,1,arr[i][j])==5) {
							ans=arr[i][j];
							ansx=i;
							ansy=j;
							break;
						}
					}

				}
				if(ans!=0)
					break;
			}
			if(ans!=0)
				break;
		}
		if(ans!=0)
			sb.append(ans).append("\n").append(ansx+1).append(" ").append(ansy+1);
		else
			sb.append(0);
		pw.print(sb);
		pw.flush();
		br.close();
		pw.close();	
	}
}