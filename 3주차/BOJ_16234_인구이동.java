import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BOJ_16234_인구이동 {
	static int n,l,r;
	static int [][]arr;
	static int [][]arr2;
	
	static int [][]isvisited;
	static int num,sum,avr;
	static ArrayList<int[]> unite = new ArrayList<>();
	
	static void func(int x,int y,int pre) {
		if(x>=n||x<0||y>=n||y<0)
			return;
		if(isvisited[x][y]!=0)
			return;
		
		int now=arr[x][y];
		int diff=Math.abs(pre-now);
		
		if(diff>=l&&diff<=r) {
			isvisited[x][y]=1;
			num++;
			sum+=now;
			unite.add(new int[]{x,y});//연합하는애들 좌표값 저장
			func(x+1,y,now);
			func(x-1,y,now);
			func(x,y+1,now);
			func(x,y-1,now);
		}
	}
	
	static void init() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				isvisited[i][j]=0;
				arr[i][j]=arr2[i][j];
			}
		}
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);
		
		String[] str= br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		l=Integer.parseInt(str[1]);
		r=Integer.parseInt(str[2]);
		arr=new int[n][n];
		arr2=new int[n][n];
		
		isvisited=new int[n][n];
		int ret=0;
		//인풋
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
				arr2[i][j]=arr[i][j];
			}
		}

		while(true) {
			int tmp=0;//연합이 성공햇는가
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					num=0;
					sum=0;
					func(i,j,arr[i][j]-l);				
					if(num>1) {//자신말고 다른곳으로 방문했으면 인구이동
						tmp=1;
						avr=sum/num;
						for(int k=0;k<num;k++) {//저장된 좌표값들에 전부 인구평균 넣어
							arr2[unite.get(k)[0]][unite.get(k)[1]]=avr;
						}
					}
					unite.clear();
				}
			}
			if(tmp==0)//연합한곳이 하나도 없으면 탈출
				break;
			init();//isvisited초기화하고, arr에 arr2 복사
			ret++;
		}
		pw.print(ret);
		pw.flush();
		pw.close();
		br.close();	
	}
}