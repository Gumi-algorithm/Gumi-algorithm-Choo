import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_14500_테트로미노 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n,m;
		String[] str=br.readLine().split(" ");
		
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		int[][] arr=new int[n][m];
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}
		}
		
		int [][][] tetro= {
				{{0,0},{0,1},{0,2},{0,3}},//1자모양 가로
				{{0,0},{1,0},{2,0},{3,0}},//1자모양 세로
				{{0,0},{0,1},{1,0},{1,1}},//네모				
				{{0,0},{1,0},{2,0},{2,1}},//ㄴ모양
				{{0,0},{1,0},{2,0},{2,-1}},
				{{0,0},{0,1},{0,2},{1,2}},
				{{0,0},{0,1},{1,1},{2,1}},
				{{0,0},{1,0},{1,1},{1,2}},
				{{0,0},{0,1},{1,0},{2,0}},
				{{0,0},{0,1},{0,2},{1,0}},
				{{0,0},{0,1},{0,2},{-1,2}},//ㄴ모양끝
				{{0,0},{1,0},{1,1},{2,1}},//번개모양 시작
				{{0,0},{0,1},{-1,1},{-1,2}},
				{{0,0},{1,0},{1,-1},{2,-1}},
				{{0,0},{0,1},{1,1},{1,2}},//번개모양 끝
				{{0,0},{0,1},{0,2},{1,1}},//ㅜ모양 시작
				{{0,0},{0,1},{0,2},{-1,1}},
				{{0,0},{1,0},{2,0},{1,1}},
				{{0,0},{-1,1},{0,1},{1,1}}//ㅜ모양 끝	
		};
		
		//모양 확인용(크기 6이상 입력해줘야됨)
//		for(int i=0;i<19;i++) {			
//			for(int j=0;j<n;j++)
//				Arrays.fill(arr[j],0);
//			System.out.println(i);
//			for(int j=0;j<4;j++) {
//				int x=2+tetro[i][j][0];
//				int y=2+tetro[i][j][1];
//				arr[x][y]=1;					
//			}
//			for(int j=0;j<n;j++) {
//				for(int k=0;k<m;k++)
//					System.out.print(arr[j][k]+" ");
//				System.out.println();
//			}
//		}
		
		int ans=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				for(int k=0;k<19;k++) {
					int tmp=0;
					for(int l=0;l<4;l++) {
						int x=i+tetro[k][l][0];
						int y=j+tetro[k][l][1];
						//인덱스 범위체크
						if(x>=n || x<0 || y>=m || y<0) {
							tmp=0;
							break;
						}
						tmp+=arr[x][y];						
					}
					ans=Math.max(ans,tmp);
				}								
			}
		}
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}
//종이 크기 500*500
//테트로미노 모양 종류 (회전, 반전 다 합쳐서) 19
//테트로미노 모양 종류별로 체크해야할 배열 갯수 4개
//500*500*19*4 = 19,000,000<1억
