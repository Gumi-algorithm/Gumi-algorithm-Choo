import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_17406_배열돌리기4_한번더푼것 {
	static int [][] arr;
	static int [][] arr2;
	
	static int [] selectnum;
	static int [] isselect;
	
	static int[][] move;
	
	static int n,m,k;
	
	static void rotate(int r,int c,int s) {
		int x,y;
		//r,c기준 왼쪽 위를 시작점으로 돌릴거임
		for(int j=1;j<=s;j++) {
			x=r-j;
			y=c-j;

			//큰 사각형에서 작은 사각형 뺀거(한바퀴 도는데 필요한 이동횟수)
			int movecnt=(1+2*j)*(1+2*j)-(2*j-1)*(2*j-1);
			int idx=0;
			int tmp,pre=0;
			int type=0;
			while(idx<=movecnt) {
				if(idx==0)
					pre=arr2[x][y];
				else {
					tmp=arr2[x][y];
					arr2[x][y]=pre;
					pre=tmp;
				}
				
				//r,c기준으로 j칸씩 움직임으로 이렇게 처리
				if(type==0 && y+1>c+j)
					type=1;
				else if(type==1 && x+1>r+j)
					type=2;
				else if(type==2 && y-1<c-j)
					type=3;
				else if(type==3 && x-1<r-j)
					type=0;

				if(type==0)
					y++;
				else if(type==1)
					x++;
				else if(type==2)
					y--;
				else if(type==3)
					x--;	
				idx++;
			}
		}
	}
	static void init() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				arr2[i][j]=arr[i][j];
			}
		}		
	}
	
	static int permutation(int idx) {
		int ret=Integer.MAX_VALUE;
		if(idx==k) {
			
			init();//arr2초기화
			for(int i=0;i<k;i++) {//순서대로 회전시켜
				rotate(move[selectnum[i]][0]-1,move[selectnum[i]][1]-1,move[selectnum[i]][2]);
			}
			for(int i=0;i<n;i++) {//회전시킨뒤 각 행씩 더해서 최솟값 찾아
				int sum=0;
				for(int j=0;j<m;j++) {
					sum+=arr2[i][j];
				}
				ret=ret>sum?sum:ret;
			}
			return ret;
		}
		
		for(int i=0;i<k;i++) {//경우의수 구해서 selectnum에 저장
			if(isselect[i]==1)
				continue;
			
			isselect[i]=1;
			selectnum[idx]=i;
			int tmp=permutation(idx+1);
			ret=ret>tmp?tmp:ret;
			isselect[i]=0;
		}
		return ret;
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		k=Integer.parseInt(str[2]);
		arr=new int[n][m];
		arr2=new int[n][m];
		
		selectnum=new int[k];
		isselect=new int[k];
		
		//입력
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}
		}
		move=new int[k][3];
		
		//회전시키는 경우들 저장
		for(int i=0;i<k;i++) {
			str=br.readLine().split(" ");
			move[i][0]=Integer.parseInt(str[0]);
			move[i][1]=Integer.parseInt(str[1]);
			move[i][2]=Integer.parseInt(str[2]);
		}
		
		int ret=permutation(0);
		
		pw.print(ret);
	
		pw.flush();
		pw.close();
		br.close();
	}
}