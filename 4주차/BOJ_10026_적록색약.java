import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_10026_적록색약 {
	static char[][] arr;
	static boolean[][] isvisited1;//적록색약 아닌사람 구역체크
	static boolean[][] isvisited2;//적록색약인 사람 구역체크
	
	static int n;

	//적록색약 아닌사람 구역 탐색
	static boolean dfs1(int x, int y, char pre) {//좌표 x,y / 이전색상 pre
		//인덱스 범위체크
		if (x < 0 || x >= n || y < 0 || y >= n)
			return false;
		//방문햇던곳이면 리턴
		if (isvisited1[x][y])
			return false;
		char nowColor = arr[x][y];
		//색상 다르면 리턴
		if(pre!=nowColor)
			return false;
		isvisited1[x][y] = true;

		//사방탐색
		dfs1(x + 1, y, nowColor);
		dfs1(x - 1, y, nowColor);
		dfs1(x, y + 1, nowColor);
		dfs1(x, y - 1, nowColor);
		
		//자기자신 한칸이라도 색상 존재하면 구역 인정되니까 true리턴
		return true;
	}
	//적록색약인사람 구역탐색
	static boolean dfs2(int x, int y, char pre) {//좌표 x,y / 이전색상 pre
		//인덱스 범위체크
		if (x < 0 || x >= n || y < 0 || y >= n)
			return false;
		//방문햇던곳이면 리턴
		if (isvisited2[x][y])
			return false;
		
		char nowColor = arr[x][y];
		if(pre!=nowColor) {
			//(빨강,초록 || 초록,빨강)이 아닐때만 리턴
			if(!((pre=='R'&&nowColor=='G') || (pre=='G'&&nowColor=='R')))
				return false;
		}
		isvisited2[x][y] = true;

		//사방탐색
		dfs2(x + 1, y, nowColor);
		dfs2(x - 1, y, nowColor);
		dfs2(x, y + 1, nowColor);
		dfs2(x, y - 1, nowColor);
		
		//자기자신 한칸이라도 색상 존재하면 구역 인정되니까 true리턴
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		String string;

		// 인풋
		arr = new char[n][n];
		isvisited1 = new boolean[n][n];
		isvisited2 = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		int ans1=0;//적록색약 아닌 사람 카운트하는 변수(정답)
		int ans2=0;//적록색약인 사람카운트하는 변수(정답)
		
		// 주어진 배열 전체를 BFS돌아
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(dfs1(i, j,arr[i][j])) {//첫시작은 돌아야되니까 자기색깔 넣어
					ans1++;
				}
				if(dfs2(i, j,arr[i][j])) {//첫시작은 돌아야되니까 자기색깔 넣어
					ans2++;
				}
			}
		}
		sb.append(ans1).append(" ").append(ans2);
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}