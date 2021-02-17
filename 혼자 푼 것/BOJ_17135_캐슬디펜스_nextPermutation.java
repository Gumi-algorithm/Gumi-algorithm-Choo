import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_17135_캐슬디펜스_nextPermutation {
	static int[] arrnp;
	static int n,m,d;
	static int[][] arr;
	static int[][] arr2;
	
	
	
	static void swap(int i, int j) {
		int tmp = arrnp[i];
		arrnp[i] = arrnp[j];
		arrnp[j] = tmp;
	}

	static boolean nextPermutation(int n) {
		int i = n - 1;
		while (i > 0 && arrnp[i - 1] >= arrnp[i])
			i--;
		if (i == 0)
			return false;

		int j = n - 1;
		while (arrnp[i - 1] >= arrnp[j])
			j--;

		swap(i - 1, j);

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
				arr2[i][j]=arr[i][j];
			}
		}
		
		
		arrnp = new int[m];
		for (int i = 0; i < 3; i++) {
			arrnp[m - i - 1] = 1;
		}
		// 궁수 사격거리 -1 하고 한칸 앞에 있다고 생각해(시작을 arr의 제일 밑에 행에서 시작해)
		// 그리고 라운드가 진행될수록 적들이 내려오는게 아닌 궁수가 한칸씩 올라간다(i--) 생각해
		d--;
		int ans=0;
		do {
			for(int i=0;i<n;i++) 
				for(int j=0;j<m;j++)
					arr[i][j]=arr2[i][j];
			
			//궁수가 선택 됬으면 이제 BFS로 궁수의 사정거리 안의 적을 죽여
			//i--해가며 한칸씩 위로 올라감(적이 내려오는게 아니고 궁수가 올라가게 햇음)
			int deadenemy=0;
			for(int i=n-1;i>=0;i--) {//가장 밑에서 시작 
				//동일한 적을 조준할수도 있기때문에 앞에서 처리한 궁수가 먼저 죽여버렷으면 해당조건이 성립안됨
				//그래서 배열에 넣어둿다가 한번에 죽여야됨
				ArrayList<int[]> dead=new ArrayList<>();//죽은 적 좌표
				
				for(int j=0;j<m;j++) {//각 열에 궁수가 서있는곳 찾아서 사정거리내 적 죽여
					if(arrnp[j]==0)//넥스트 퍼뮤테이션에서 선택된 애인지 확인
						continue;
					if(arr[i][j]==1) {//bfs돌기전에 바로앞에 있는지 확인한번함(사실 이거 없어도되는데 그냥넣음)
						dead.add(new int[] {i,j});
						continue;
					}
					//BFS
					Queue<int[]> q=new LinkedList<>();
					int[][] isvisited=new int[n][m];
					int x=i;
					int y=j;
					int nowlength=0;
					q.offer(new int[] {x,y,nowlength});
					isvisited[x][y]=1;
					while(!q.isEmpty()) {
						x=q.peek()[0];
						y=q.peek()[1];
						nowlength=q.poll()[2];
						if(nowlength>d)
							break;
						
						if(arr[x][y]==1) {
							dead.add(new int[] {x,y});
							break;
						}
						//왼쪽 적 먼저 죽인됫으니까 왼쪽 위쪽 오른쪽 순으로 탐색해야함
						if(y-1>=0&&isvisited[x][y-1]!=1) {//왼쪽
							q.offer(new int[] {x,y-1,nowlength+1});
							isvisited[x][y-1]=1;
						}
						if(x-1>=0&&isvisited[x-1][y]!=1) {//위쪽
							q.offer(new int[] {x-1,y,nowlength+1});
							isvisited[x-1][y]=1;
						}
						if(y+1<m&&isvisited[x][y+1]!=1) {//오른쪽
							q.offer(new int[] {x,y+1,nowlength+1});
							isvisited[x][y+1]=1;
						}
					}
					//BFS끝					
				}
				//이제 모아둔 적을 죽여야함
				int deadsize=dead.size();
				for(int j=0;j<deadsize;j++) {
					int x=dead.get(j)[0];
					int y=dead.get(j)[1];
					if(arr[x][y]==1)
						deadenemy++;
					arr[x][y]=0;
				}
			}
			ans=ans<deadenemy?deadenemy:ans;
		} while (nextPermutation(m));

		pw.print(ans);
		pw.flush();
		br.close();
		pw.close();
	}

}
/*
 * 궁수 3명
 * 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 
 * 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다. 
 * 같은 적이 여러 궁수에게 공격당할 수 있다. 
 * 공격받은 적은 게임에서 제외된다.
 * 
 * 시간복잡도 5C3*행(n)*3(궁수3명)*거리(BFS탐색깊이)D
 * 
 */