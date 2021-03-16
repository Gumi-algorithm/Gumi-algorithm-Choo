import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_3709_레이저빔은어디로 {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		
		int t=Integer.parseInt(br.readLine());
		int tidx=0;
		int n,r;
		String[] str;
		int[][] arr=new int[55][55];//거울이 있는지 없는지 저장
		while(tidx++<t) {
			
			str=br.readLine().split(" ");
			
			n=Integer.parseInt(str[0]);
			r=Integer.parseInt(str[1]);
			
			//arr초기화
			for(int i=0;i<=n;i++) {
				Arrays.fill(arr[i], 0);
			}
	
			//거울입력
			for(int i=0;i<r;i++) {
				str=br.readLine().split(" ");
				arr[Integer.parseInt(str[0])-1][Integer.parseInt(str[1])-1]=1;
			}
			
			//레이저 좌표
			str=br.readLine().split(" ");
			int rx,ry;//레이저 x,y저장
			rx=Integer.parseInt(str[0])-1;
			ry=Integer.parseInt(str[1])-1;
			int dir=0;//1: 위, 2: 오른, 3: 아래, 4: 왼 //비추는 방향
			//방향 찾아
			if(rx==n) {//위
				rx=n-1;
				dir=1;
			}else if(rx==-1) {//아래
				rx=0;
				dir=3;
			}else if(ry==n) {//왼
				ry=n-1;
				dir=4;
			}else if(ry==-1) {//오른
				ry=0;
				dir=2;
			}
			
			//방향 위 오른 아래 왼
			int[][] direction= {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
			//시뮬 돌려

			while(true) {
			
				//보드 밖에 나갓는지 체크
				if(rx>=n || rx<0 || ry>=n || ry<0 )
					break;
		
				//거울 만나면 방향바꿔
				if(arr[rx][ry]==1) {
					//방향바꾸기 전에 방문 표시
					arr[rx][ry]=1;
					dir=(dir)%4+1;
				}
				
				//방향에맞게 이동해
				rx=rx+direction[dir][0];
				ry=ry+direction[dir][1];
					
			}

			sb.append(rx+1).append(" ").append(ry+1).append("\n");

		}
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();	
	}
}
//레이저가 비추는 바로앞칸에 거울이 잇는경우를 체크 못하고있었음
//지금 규칙에서는 무한루프가 생길수 없는데 내소스에서 자꾸 무한루프때매 시간초과뜸
//-> 원인 rx,ry 에 거울잇는지 체크하고 dirction[dir]더해야되는데
//	더하고나서 거울잇는지 체크했음