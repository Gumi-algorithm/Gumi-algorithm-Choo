import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_15684_사다리조작 {

	static int[][] map;
	static int n,m,h;
	static int ans=4;
	
	static boolean simulation() {
		//세로줄
		int now=0;
	
		for(int i=0;i<n;i++) {
			now=i;//i번 세로줄에서 출발
			for(int j=0;j<h;j++) {
				now+=map[j][now];
			}
			//h개의 가로줄을 지나온뒤 출발 세로줄과 같은지 비교
			if(now!=i)
				return false;
		}
		
		//모두 세로선과 가로선이 같은경우
		return true;
		
	}
	
	static void dfs(int x,int y,int cnt) {
		
		if(cnt>=ans)
			return;
	
		if(simulation()) {
			ans=Math.min(ans, cnt);
			return;
		}
		//j가 한번만 y로 초기화 하기위해 만듬
		int tmp2=1;
		//j=y라고 해버리면 y가 2일경우 j반복문이 끝난뒤 다시 시작할때 0이아닌 2부터 시작함
		for(int i=x;i<h;i++) {
			for(int j=0;j<n-1;j++) {
				if(tmp2==1) {
					j=y;
					tmp2=0;
				}
				//이미 선이 있으면 넘어가
				if(map[i][j]!=0 || map[i][j+1]!=0)
					continue;
				int nx= (j+1)/(n-1)==1?i+1:i;
				int ny=(j+1)%(n-1);			
							
				map[i][j]=1;
				map[i][j+1]=-1;
				dfs(nx,ny,cnt+1);//고르는 경우
				
				//원상복귀
				map[i][j]=0;
				map[i][j+1]=0;
			}
		}
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);

		String[] str=br.readLine().split(" ");
		
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		h=Integer.parseInt(str[2]);
		
		//n*h 개의 선들 중에서 최대 3개를 뽑는거임
		//최대 10*30=300
		//1개 고르는경우 300 C 1 = 300
		//2개 고르는경우 300 C 2 = 300*299
		//3개 고르는경우 300 C 3 = 300*299*298
		//=> 300+300*299/2+300*299*298/6
		//가로 선을 고르는 연산횟수 : 4,500,250
		//고른 가로선을 이용해 시뮬레이션 하는연산 : 30
		//두개 곱하면 135,007,500 시간초과
		
		//그런데 선 갯수를 10*30=300 이라고 했는데 선이 가로로 연속해서 있을수 없으니까 
		//최대 10*15=150 개 이지 않을까 생각됨
		//150*149*148/6 + 150*149/2 + 150 = 562,625
		//가로 선을 고르는 연산횟수 : 562,625
		//고른 가로선을 이용해 시뮬레이션 하는연산 : 30
		//=>16,878,750
		
		
		
		map=new int[h][n];
		
		for(int i=0;i<m;i++) {
			str=br.readLine().split(" ");
			int a=Integer.parseInt(str[0])-1;
			int b=Integer.parseInt(str[1])-1;
			
			
			map[a][b]=1;
			if(b+1<n)
				map[a][b+1]=-1;
		}
		
		dfs(0,0,0);
		
		if(ans>3)
			pw.print(-1);
		else
			pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}
/*
DFS 문에서 반복문 i,j를 x,y로 초기화 하는것에 대한 테케
4 3 4 
1 1
2 2
1 3
답 3
*/
