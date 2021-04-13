import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2564_경비원 {
	//좌표평면에 뒀을때 좌표 차이가 최소 거리임
	//단, 서로 마주보고 있지 않은 경우에만 해당거리가 최소거리임
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n;// 가로길이(열)
		int m;// 세로길이(행)
		String []str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		int s=Integer.parseInt(br.readLine());
		int shop[][]=new int[s][2];
		for(int i=0;i<s;i++) {
			str=br.readLine().split(" ");
			shop[i][0]=Integer.parseInt(str[0]);
			shop[i][1]=Integer.parseInt(str[1]);			
		}
		
		//동근이의 위치
		int dir,x,y;
		str=br.readLine().split(" ");
		x=Integer.parseInt(str[0]);
		y=Integer.parseInt(str[1]);
		
		dir=x;
		//좌표평면에서의 좌표처럼 만들어(0아니고 1부터 시작하는거임)
		if(dir==1)//북
			x=0;
		else if(dir==2)//남
			x=m;
		else if(dir==3) {//서
			x=y;
			y=0;
		}else if(dir==4) {//동
			x=y;
			y=n;
		}
		
		int ans=0;
		for(int i=0;i<s;i++) {
			int sdir=shop[i][0];
			int sx=shop[i][0];
			int sy=shop[i][1];
			//좌표평면에서의 좌표처럼 만들어(0아니고 1부터 시작하는거임)
			if(sdir==1)//북
				sx=0;
			else if(sdir==2)//남
				sx=m;
			else if(sdir==3) {//서
				sx=sy;
				sy=0;
			}else if(sdir==4) {//동
				sx=sy;
				sy=n;
			}
			
			//서로 마주보고 있는경우
			if(dir+sdir==3) {// 북, 남 으로 마주볼때
				int tmp=sy+y;
				tmp=Math.min(tmp, (n-sy)+(n-y));
				ans+=(m+tmp);
			}
			else if(dir+sdir==7) {// 동, 서 로 마주볼때
				int tmp=sx+x;
				tmp=Math.min(tmp, (m-sx)+(m-x));
				ans+=(n+tmp);
			}else {
				ans+=(Math.abs(sx-x)+Math.abs(sy-y));
			}			
		}		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}