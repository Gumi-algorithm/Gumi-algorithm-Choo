import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

/*
4개의 블록이 모이면 터짐

12*6=72
72/4=18
=>최대 18번의 연쇄 발생가능

그럼 최대 12*6인 배열을 18번 탐색하면 나옴
12*6*18=1296
 => 시뮬레이션 가능
 */

//BOJ_16234_인구이동이랑 비슷한것같음
public class BOJ_11559_PuyoPuyo {
	static char[][] arr = new char[12][6];// 게임의 맵을 나타내고있으며 빈공간은 '.', 뿌요가 잇는곳은 RGBPY로 색을 나타냄
	static int[][] isvisited = new int[12][6];// BFS에서 방문한 곳인지 확인하는 변수

	// 모든 좌표는 12*6=72개이니까 72개의 배열에 x,y좌표 두개를 담을수 잇게 73*2배열 선언(72보다 1큰건 그냥 넣음 내맘임)
	static int[][] puyopuyo = new int[77][2];// 4개이상인 뿌요들을 나중에 터트릴려고 모아놓는곳
	static int puyoidx = 0;

	static void init() {
		for (int i = 0; i < 12; i++)
			for (int j = 0; j < 6; j++)
				isvisited[i][j] = 0;
	}

	static int BFS(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		char color = arr[x][y];
		if (color == '.')// 뿌요가 없으면 리턴
			return 0;
		int cnt = 0;// 같은색 뿌요 갯수 세는 변수
		// 시작점 x,y를 큐에 넣고 BFS돌려서 같은색을 카운트해
		q.offer(new int[] { x, y });

		isvisited[x][y]=1;//첫 시작점 큐에 넣엇으니 방문햇다고 바꿔

		while (!q.isEmpty()) {
			int nowx = q.peek()[0];// 큐에 들어잇던 뿌요의 좌표를 받아옴
			int nowy = q.poll()[1];
			isvisited[nowx][nowy] = 1;
			
			//우선 4개 이상이 될 경우를 위해 일단 넣어 (4개이상 안되면 puyoidx갱신안되서 무시될거임)
			puyopuyo[puyoidx+cnt][0] = nowx;
			puyopuyo[puyoidx+cnt][1] = nowy;
			
			cnt++;// 뿌요갯수+1
			
			// 범위를 벗어나지않는지 이미 방문햇던 곳은아닌지 확인을 한뒤
			// 뿌요의 아래가 같은색이면 큐에집어넣어
			// 인덱스 범위체크, 이미 방문한 곳인지 체크, 같은 색상인지 체크
			if (nowx + 1 < 12 && isvisited[nowx + 1][nowy] != 1 && arr[nowx + 1][nowy] == color) {
				q.offer(new int[] { nowx + 1, nowy });
				isvisited[nowx+1][nowy]=1;// 방문한곳을 다시 방문안하게 isvisited변수 1로바꿔
			}
			// 뿌요의 위가 같은색이면 큐에 집어넣어
			if (nowx - 1 >= 0 && isvisited[nowx - 1][nowy] != 1 && arr[nowx - 1][nowy] == color) {
				q.offer(new int[] { nowx - 1, nowy });
				isvisited[nowx-1][nowy]=1;// 방문한곳을 다시 방문안하게 isvisited변수 1로바꿔
			}
			// 뿌요의 왼쪽이 같은색이면 큐에 집어넣어
			if (nowy - 1 >= 0 &&isvisited[nowx][nowy - 1] != 1 &&  arr[nowx][nowy - 1] == color) {
				q.offer(new int[] { nowx, nowy - 1 });
				isvisited[nowx][nowy-1]=1;// 방문한곳을 다시 방문안하게 isvisited변수 1로바꿔
			}
			// 뿌요의 오른쪽이 같은색이면 큐에 집어넣어
			if (nowy + 1 < 6 &&isvisited[nowx][nowy + 1] != 1 &&  arr[nowx][nowy + 1] == color) {
				q.offer(new int[] { nowx, nowy + 1 });
				isvisited[nowx][nowy+1]=1;// 방문한곳을 다시 방문안하게 isvisited변수 1로바꿔
			}
		}

		// BFS를 돌면서 같은 색상의 뿌요들 갯수 리턴
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String str;// 입력을 받기위해 만든 변수

		// 입력(12*6의 칸에 .또는 RGBPY가 주어짐)
		for (int i = 0; i < 12; i++) {
			str = br.readLine();
			for (int j = 0; j < 6; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		int ret=0;//연쇄 몇번 일어낫는지 카운트 하는 변수(정답)
		while (true) {// 터지는 블록이 없을때까지 반복
			puyoidx = 0;
			
			int isEnd = 1;// 터진 블록이 잇는지 없는지 변수에 저장
			
			// isvisited를 전부 0으로 초기화 시켜줘
			init();// puyopuyo배열은 puyoidx범위 안에서만 접근할거라서 따로 초기화 안하고 위에 덮어씌우면됨

			// 우선 맵 전체를 돌면서 한칸씩 BFS를 돌려 상하좌우 연결된 같은색 뿌요를 찾아
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					int num;
					num = BFS(i, j);//같은색이 몇개였는지 num에 저장
					// 만약 4개이상의 뿌요가 모여잇는거면 puyopuyo인덱스를 증가시켜줘
					// 4개 미만일때는 puyoidx를 업데이트 안해주어 BFS에서 집어넣엇던 좌표들이 무시되게함
					if (num >= 4) {
						puyoidx += num;
						isEnd = 0;// 4개이상인 경우가 잇엇으니까 한번더 while문 한번더 돌려바야됨 또 연쇄 잇을지도 모름
					}
				}
			}

			// 터진블록이 하나도 없다면 탈출
			if (isEnd == 1)
				break;
			
			ret++;//탈출을 안했으면 터진거니까 +1해줘
			
			// 찾은 뿌요들 터뜨려 .으로 바꿈
			for (int i = 0; i < puyoidx; i++) {
				int nowx = puyopuyo[i][0];
				int nowy = puyopuyo[i][1];
				arr[nowx][nowy] = '.';
			}

			// 뿌요들이 떨어지게 만들어
			for (int i = 0; i < 6; i++) {// 제일 왼쪽 칸부터 한줄씩 확인하며 떨어뜨려
				int ground = -1;

				for (int j = 11; j >= 0; j--) {// 땅바닥부터 위로 올라가며 땅 위치 저장해둿다가 뿌요 발견하면 땅 위치로 이동시키고 땅위치+1 해줘
					// 우선 땅 위치 or 바닥에 붙어잇는 뿌요들위치를 찾아 (떨어질곳 찾으란 말임)
					if (ground == -1) {
						if(arr[j][i] == '.')
							ground = j;
					} else {// 떨어질곳 찾고나면 arr[i][j]가 .이 아닌애를 찾으면 ground로 이동시키고 ground-1해줘
						if (arr[j][i] != '.') {
							arr[ground][i] = arr[j][i];
							arr[j][i] = '.';
							ground -= 1;
						}
					}
				}
			}

		}
		pw.print(ret);
		pw.flush();
		pw.close();
		br.close();
	}

}
/*
RRRRRR
RRRRRR
RRRRRR
RRRRRR
RRRRRR
RRBBRR
RRRRRR
RRRRRR
RRRRRR
RRBBRR
RRRRRR
RRRRRR
답:2
//위 테케 틀린이유: isvisited를 큐에서 꺼낼때 1로 바꾸게 짯엇음
 				그래서 큐에 넣을때 1로 바꾸게 고침
 				
GRRRRR
YRRRRR
GRRRYY
YRRRRR
GRRYRR
YRBBRR
GRRRRR
YRRRRR
GRRRRR
YRBBRR
GRRRRR
RRRYGG
답:3
//위 테케 틀린이유: BFS에서 nowx-1>=0체크할때 =빼먹음 
*/
