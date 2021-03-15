import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_17144_미세먼지안녕 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		int r,c,t;
		String[] str=br.readLine().split(" ");
		r=Integer.parseInt(str[0]);
		c=Integer.parseInt(str[1]);
		t=Integer.parseInt(str[2]);
		
		int[][] arr= new int[r][c];//방
		int[][] spread=new int[r][c];//확산되는 미세먼지들 저장 (확산 끝나면 방에 더해)
		
		int topaircleaner=-1;
		int botaircleaner=-1;
		
		for(int i=0;i<r;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<c;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}
			//공기청정기 위치 저장
			if(arr[i][0]==-1) {
				if(topaircleaner ==-1)
					topaircleaner=i;
				else
					botaircleaner=i;
			}
		}
		
		int tidx=0;
		int[][] direction= {{1,0},{-1,0},{0,1},{0,-1}};
		while(tidx++<t) {
			
			
			//1. 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든칸에서 동시네 일어난다
			for(int i=0;i<r;i++) {
				for(int j=0;j<c;j++) {
					int now=arr[i][j];
					//먼지가 있을때만 확산이 일어난다
					if(now!=-1 && now!=0) {						
						for(int k=0;k<4;k++) {
							int nx=i+direction[k][0];
							int ny=j+direction[k][1];
							
							//인덱스 범위체크
							if(nx<0 || nx>=r || ny<0 || ny>=c)
								continue;
							//공기청정기론 못퍼짐
							if(arr[nx][ny]==-1)
								continue;
							spread[nx][ny]+=arr[i][j]/5;
							now-=arr[i][j]/5;
						}
						arr[i][j]=now;
					}	
				}
			}
			
			//확산된 미세먼지를 방에 적용
			for(int i=0;i<r;i++) {
				for(int j=0;j<c;j++) {
					arr[i][j]+=spread[i][j];
					spread[i][j]=0;
				}
			}
			
			//2. 공기청정기가 작동한다
			//위쪽 공기청정기 작동;
			int[][] movetop= {{0,1},{-1,0},{0,-1},{1,0}};
			int movetopidx=0;
			int pre=-1;
			int x=topaircleaner;
			int y=1;
			while(true) {
				if(arr[x][y]==-1)
					break;
				if(pre==-1) {
					pre=arr[x][y];
					arr[x][y]=0;
				}else {
					int tmp=arr[x][y];
					arr[x][y]=pre;
					pre=tmp;
				}
				
				
				int nx=x+movetop[movetopidx][0];
				int ny=y+movetop[movetopidx][1];				
				if(nx<0 || nx>=r || ny<0 || ny>=c) {
					movetopidx++;
					nx=x+movetop[movetopidx][0];
					ny=y+movetop[movetopidx][1];
				}
				x=nx;
				y=ny;
			}
			
			//아래쪽 공기청정기 작동;
			int[][] movebot= {{0,1},{1,0},{0,-1},{-1,0}};
			int movebotidx=0;
			pre=-1;
			x=botaircleaner;
			y=1;
			while(true) {
				if(arr[x][y]==-1)
					break;
				if(pre==-1) {
					pre=arr[x][y];
					arr[x][y]=0;
				}else {
					int tmp=arr[x][y];
					arr[x][y]=pre;
					pre=tmp;
				}
				
				
				int nx=x+movebot[movebotidx][0];
				int ny=y+movebot[movebotidx][1];				
				if(nx<0 || nx>=r || ny<0 || ny>=c) {
					movebotidx++;
					nx=x+movebot[movebotidx][0];
					ny=y+movebot[movebotidx][1];
				}
				x=nx;
				y=ny;
			}
		}
		int ans=0;
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(arr[i][j]!=-1)
					ans+=arr[i][j];
			}
		}
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();		
	}
}