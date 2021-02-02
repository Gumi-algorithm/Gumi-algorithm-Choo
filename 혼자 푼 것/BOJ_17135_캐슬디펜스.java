import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collector;

public class BOJ_17135_캐슬디펜스 {

	static class Pair{
		int first;
		int second;
		int deep;
		public Pair() {}
		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
		public Pair(int first, int second,int deep) {
			this.first = first;
			this.second = second;
			this.deep = deep;
		}
		@Override
		public boolean equals(Object obj) {
			if(((Pair)obj).first==this.first&&((Pair)obj).second==this.second)
				return true;
			else
				return false;
		}
	}

	static int arr[][];
	static int arr2[][];
	static int n,m,d;


	static Pair bfs(int x,int y, int dis) {
		int isvisited[][]=new int[n][m];
		int nowx,nowy;
		ArrayList<Pair> queue=new ArrayList<>();
		int queueIdx=0;
		int deep=0;//길이
		
		queue.add(new Pair(x,y,0));
		
		while(true) {
			if(queue.size()==queueIdx)
				break;
			
			nowx=queue.get(queueIdx).first;
			nowy=queue.get(queueIdx).second;
			deep=queue.get(queueIdx++).deep;
			if(arr2[nowx][nowy]==1)
				return new Pair(nowx,nowy);
			
			if(deep+1<=dis) {
				if(nowy-1 >= 0&&isvisited[nowx][nowy-1]!=1) {//왼쪽
					queue.add(new Pair(nowx,nowy-1,deep+1));
					isvisited[nowx][nowy-1]=1;
				}
				if(nowx-1 >= 0&&isvisited[nowx-1][nowy]!=1) {//위쪽
					queue.add(new Pair(nowx-1,nowy,deep+1));
					isvisited[nowx-1][nowy]=1;
				}
				if(nowy+1<m&&isvisited[nowx][nowy+1]!=1) {//오른쪽
					queue.add(new Pair(nowx,nowy+1,deep+1));
					isvisited[nowx][nowy+1]=1;
				}
			}

		}
		return null;
	}

	//궁수는 성이 아니라 성 바로위에 위치한다 생각하고 거리를 -1 해서 생각하자
	static int deffence(int[] archer,int row) {
		if(row<0)
			return 0;
		int deadEnemycnt=0;
		int distance=d-1;
		ArrayList<Pair> deadEnemy=new ArrayList<>();
		for(int i=0;i<3;i++) {
			//i번째 아처
			if(arr2[row][archer[i]]==1) {
				deadEnemy.add(new Pair(row,archer[i]));
				continue;
			}
			//여기 거리 1넘는경우 적어야댐
			//BFS탐색(근데 아래쪽은 안해도됨)
			Pair de=bfs(row, archer[i], distance);
			if(de!=null)
				deadEnemy.add(de);
		}
		Collections.sort(deadEnemy,new Comparator<Pair>() {
			@Override
			public int compare(Pair a, Pair b) {
				if(a.first==b.first)
					return a.second-b.second;
				else
					return a.first-b.first;
			}
		});

		for(int i=0;i<deadEnemy.size();i++) {
			//같은거 카운트 안함
			if(i+1<deadEnemy.size() && deadEnemy.get(i).equals(deadEnemy.get(i+1))) {
				continue;
			}
			deadEnemycnt++;	
			arr2[deadEnemy.get(i).first][deadEnemy.get(i).second]=0;
		}
		deadEnemycnt+=deffence(archer,row-1);

		return deadEnemycnt;
	}

	//궁수를 선택해 deffence실행
	static int getArcher(int[] archer,int idx) {
		int deadEnemy=0;
		if(idx>2) {
			for(int i=0;i<arr.length;i++) {
				for(int j=0;j<arr[i].length;j++) {
					arr2[i][j]=arr[i][j];
				}
			}
			deadEnemy=deffence(archer, n-1);
			return deadEnemy;
		}

		for(int i=0;i<m;i++) {
			int jud=0;
			for(int j=0;j<idx;j++) 
				if(archer[j]==i)
					jud=1;
			if(jud==1)
				continue;
			archer[idx]=i;
			int tmp=getArcher(archer, idx+1);
			archer[idx]=-1;
			deadEnemy=deadEnemy<tmp?tmp:deadEnemy;
		}

		return deadEnemy;
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);



		String[] strtmp=br.readLine().split(" ");
		n=Integer.parseInt(strtmp[0]);
		m=Integer.parseInt(strtmp[1]);
		d=Integer.parseInt(strtmp[2]);

		arr=new int[n][m];
		arr2=new int[n][m];


		for(int i=0;i<n;i++) {
			strtmp=br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(strtmp[j]);
			}
		}

		//궁수위치 모두 테스트
		int result=getArcher(new int[3], 0);

		pw.print(Integer.toString(result));

		br.close();
		pw.close();
	}

}
/*

궁수위치 완탐: mC3
행마다 연산 수행: n
궁수 하나당 적 닿는 범위 계산 범위(d) 10일경우: 100칸 
궁수 인원: 3명

455 * 15 * 100 * 3 = 2,047,500
 */