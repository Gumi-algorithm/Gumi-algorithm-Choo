import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_17135_캐슬디펜스_nextPermutation {
	static int[] arrnp;
	static int n, m, d;
	static int[][] arr;
	static int[][] arr2;

	static void swap(int i, int j) {
		int tmp = arrnp[i];
		arrnp[i] = arrnp[j];
		arrnp[j] = tmp;
	}

	static boolean nextPermutation(int n) {
		// arr은 오름차순으로 정렬되어 들어옴
		// arr[i-1]<arr[i]이 성립하는 마지막 i를 찾아
		int i = n - 1;
		while (i > 0 && arrnp[i - 1] >= arrnp[i])
			i--;
		if (i == 0)
			return false;
		// arr[i-1]<arr[j]이 성립한느 마지막 j를 찾아
		int j = n - 1;
		while (arrnp[i - 1] >= arrnp[j])
			j--;
		// 스왑
		swap(i - 1, j);
		// i포함 뒷부분을 오름차순으로 정렬(뒤집어주면 오름차순 정렬됨)
		int k = n - 1;
		while (i < k)
			swap(i++, k--);
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);// 행
		m = Integer.parseInt(str[1]);// 열
		d = Integer.parseInt(str[2]);// 사격거리
		arr = new int[n][m];
		arr2 = new int[n][m];

		// 입력
		for (int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
				arr2[i][j] = arr[i][j];
			}
		}

		// 넥스트 퍼뮤테이션으로 조합을 구하기 위해 m개의 배열중 맨뒤 3개를 1을 집어넣음
		arrnp = new int[m];
		for (int i = 0; i < 3; i++) {
			arrnp[m - i - 1] = 1;
		}
		// 궁수 사격거리 -1 하고 한칸 앞에 있다고 생각해(시작을 arr의 제일 밑에 행에서 시작해)
		// 그리고 라운드가 진행될수록 적들이 내려오는게 아닌 궁수가 한칸씩 올라간다(i--) 생각해
		d--;
		int ans = 0;
		do {
			// 각 조합의 경우의 수마다 arr을 변경해 주기때문에 새로운 경우에는 arr을 초기화 해줘야함
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					arr[i][j] = arr2[i][j];

			// 궁수가 선택 됬으면 이제 BFS로 궁수의 사정거리 안의 적을 죽여
			// i--해가며 한칸씩 위로 올라감(적이 내려오는게 아니고 궁수가 올라가게 햇음)
			int deadenemy = 0;
			for (int i = n - 1; i >= 0; i--) {// 가장 밑에서 시작
				// 동일한 적을 조준할수도 있기때문에 앞에서 처리한 궁수가 먼저 죽여버렷으면 해당조건이 성립안됨
				// 그래서 배열에 넣어둿다가 한번에 죽여야됨
				ArrayList<int[]> dead = new ArrayList<>();// 죽은 적 좌표

				for (int j = 0; j < m; j++) {// 각 열에 궁수가 서있는곳 찾아서 사정거리내 적 죽여
					if (arrnp[j] == 0)// 넥스트 퍼뮤테이션에서 선택된 애인지 확인
						continue;
					if (arr[i][j] == 1) {// bfs돌기전에 바로앞에 있는지 확인한번함(사실 이거 없어도되는데 그냥넣음)
						dead.add(new int[] { i, j });
						continue;
					}
					// BFS
					Queue<int[]> q = new LinkedList<>();
					int[][] isvisited = new int[n][m];
					int x = i;
					int y = j;
					int nowlength = 0;
					q.offer(new int[] { x, y, nowlength });// x,y좌표, 그리고 사정거리
					isvisited[x][y] = 1;
					while (!q.isEmpty()) {
						x = q.peek()[0];
						y = q.peek()[1];
						nowlength = q.poll()[2];
						if (nowlength > d)// 현재 사정거리가 주어진 사정거리 d보다 크면 탈출
							break;

						if (arr[x][y] == 1) {// 적을 만나면 해당적을 죽일거니까 dead에 집어넣고 탈출
							dead.add(new int[] { x, y });
							break;
						}
						// 왼쪽 적 먼저 죽인됫으니까 왼쪽 위쪽 오른쪽 순으로 탐색해야함
						// 좌표범위 넘어서는지 확인, 이미 방문했는지 확인
						if (y - 1 >= 0 && isvisited[x][y - 1] != 1) {// 왼쪽
							q.offer(new int[] { x, y - 1, nowlength + 1 });
							isvisited[x][y - 1] = 1;
						}
						if (x - 1 >= 0 && isvisited[x - 1][y] != 1) {// 위쪽
							q.offer(new int[] { x - 1, y, nowlength + 1 });
							isvisited[x - 1][y] = 1;
						}
						if (y + 1 < m && isvisited[x][y + 1] != 1) {// 오른쪽
							q.offer(new int[] { x, y + 1, nowlength + 1 });
							isvisited[x][y + 1] = 1;
						}
					}
					// BFS끝
				}
				// 이제 모아둔 적을 죽여야함
				int deadsize = dead.size();
				for (int j = 0; j < deadsize; j++) {
					int x = dead.get(j)[0];// 모아두었던 좌표를 꺼내 0으로 바꿈
					int y = dead.get(j)[1];
					if (arr[x][y] == 1)// 여러 궁수가 같은 적을 노릴수도있으니 한번만 카운트하게 조건을 걸어줘
						deadenemy++;
					arr[x][y] = 0;
				}
			}
			ans = ans < deadenemy ? deadenemy : ans;// 해당 조합의 경우에서의 죽인 적의 수를 ans에 최소값을 저장
		} while (nextPermutation(m));// 다음 조합의 경우 생성

		pw.print(ans);
		pw.flush();
		br.close();
		pw.close();
	}

}
/*
 * 조건
 * 궁수 3명
 * 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 
 * 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다. 
 * 같은 적이 여러 궁수에게 공격당할 수 있다. 
 * 공격받은 적은 게임에서 제외된다.
 * 
 * 시간복잡도 5C3*행(n)*3(궁수3명)*거리(BFS탐색깊이)D
 * 시뮬레이션 가능 
 */