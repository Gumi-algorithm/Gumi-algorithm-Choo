

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_16927_배열돌리기2 {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int [][] arr;
		
		int n,m,r;
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		r=Integer.parseInt(str[2]);
		arr=new int[n][m];
		
		//입력
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}
		}
		
		int x,y;
		int min=Math.min(n, m);
		//가장 겉부분 부터 속까지 한칸씩 들어가면서 r번 회전시킴
		for(int i=0;i<min/2;i++) {
			//회전을 하다보면 원래 자리로 돌아오게됨 그래서 mod를 해줌
			int modr=r%((n-2*i)*(m-2*i)-(n-2*i-2)*(m-2*i-2));
			//여기서 r번 회전해
			for(int j=0;j<modr;j++) {
				x=i;
				y=i;
				//회전 횟수
				int rotCnt=(n-2*i)*(m-2*i)-(n-2*i-2)*(m-2*i-2);
				int idx=0;
				int tmp,pre=0;
				int addtype=0;
				//while문은 한칸씩 반시계로 움직이는 애
				while(idx<=rotCnt) {
					//옮겨라
					
					if(idx!=0) {
						tmp=arr[x][y];
						arr[x][y]=pre;
						pre=tmp;
					}else {
						pre=arr[x][y];
					}
					
					//경계 체크
					if(addtype==0 && x+1>=n-i)
						addtype=1;
					else if(addtype==1 && y+1>=m-i)
						addtype=2;
					else if(addtype==2 && x-1<0+i)
						addtype=3;
					else if(addtype==3 && y-1<0+i)
						addtype=0;
					
					//좌표 이동(x,y증감)
					if(addtype==0)
						x++;
					else if(addtype==1)
						y++;
					else if(addtype==2)
						x--;
					else if(addtype==3)
						y--;
					
					idx++;
				}
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				pw.print(arr[i][j]+" ");
			}
			pw.print("\n");
		}
		pw.flush();
		pw.close();
		br.close();
	}
}