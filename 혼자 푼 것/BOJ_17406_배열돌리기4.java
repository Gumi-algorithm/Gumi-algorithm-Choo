import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_17406_배열돌리기4 {
	int [][] arr;
	int [][] arr2;
	int []directionX= {-1,0,1,0};
	int []directionY= {0,1,0,-1};
	int []isSelect;
	int []selected;
	int inp2[][];
	
	public void turnArr(int r, int c, int s) {
		int startCnt=3;
		for(int i=1;i<=s;i++) {
			int turnCnt=startCnt*startCnt-(startCnt-2)*(startCnt-2);
			int x=r+i,y=c-i;//시계방향으로 도는 arr의 인덱스
			int moveX=directionX[0],moveY=directionY[0];//움직이는 방향조절
			int cnt=0;//방향 바꾸기위해 카운드
			int directionIdx=1;//방향바꾸는걸 배열로 관리하기위해 Idx설정
			int nextVal=arr2[x][y];//다음칸에 넣기위해 현재값 저장
			for(int j=0;j<turnCnt;j++) {
				int now=nextVal;
				if(cnt++==i*2) {
					cnt=1;
					moveX=directionX[directionIdx];
					moveY=directionY[directionIdx];
					directionIdx=(directionIdx+1)%4;
				}
				x=x+moveX;
				y=y+moveY;
				nextVal=arr2[x][y];
				arr2[x][y]=now;
				
			}
			startCnt+=2;
		}
	}
	
	public int bruteForce(int n1,int n2) {
		int ret=Integer.MAX_VALUE;
		if(n1>n2) {
			//초기화
			for(int i=0;i<arr.length;i++) {
				for(int j=0;j<arr[i].length;j++) {
					arr2[i][j]=arr[i][j];
				}
			}
			
			for(int i=0;i<=n2;i++) {
				int num=selected[i];
				turnArr(inp2[num][0],inp2[num][1],inp2[num][2]);	
			}
			
			//행의 최솟값 구함
			for(int i=0;i<arr2.length;i++) {
				int sum=0;
				for(int j=0;j<arr2[i].length;j++) {
					sum+=arr2[i][j];
				}
				ret= ret>sum?sum:ret;
			}
			return ret;
		}
		
		
		for(int i=0;i<=n2;i++) {
			if(isSelect[i]==1)
				continue;
			isSelect[i]=1;
			selected[n1]=i;
			int tmp=bruteForce(n1+1, n2);
			ret=ret>tmp?tmp:ret;
			isSelect[i]=0;
			
		}
		
		return ret;
	}

	public BOJ_17406_배열돌리기4() throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

		String[] nmk=br.readLine().split(" ");
		int n=Integer.parseInt(nmk[0]);
		int m=Integer.parseInt(nmk[1]);
		int k=Integer.parseInt(nmk[2]);
		arr=new int[n][m];
		arr2=new int[n][m];
		
		//input
		for(int i=0;i<n;i++) {
			String[] strtmp=br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(strtmp[j]);
			}
		}
		inp2=new int[k][3];
		int r,c,s;
		String[] tmp;
		for(int i=0;i<k;i++) {
			tmp=br.readLine().split(" ");
			inp2[i][0]=Integer.parseInt(tmp[0])-1;
			inp2[i][1]=Integer.parseInt(tmp[1])-1;
			inp2[i][2]=Integer.parseInt(tmp[2]);
		}
		
		isSelect=new int[k];
		selected=new int[k];

		int ret=bruteForce(0,k-1);
		
		System.out.println(ret);

	}

	public static void main(String[] args) throws IOException{
		new BOJ_17406_배열돌리기4();
	}
}
/*
5 5 5
3 3 3 3 3 
2 3 3 3 3
2 3 3 3 3
2 3 2 3 3
2 3 3 3 3
3 3 2
3 3 2
3 3 2
2 4 1
3 3 2

10
*/