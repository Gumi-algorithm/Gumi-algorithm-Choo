import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_11562_백양로브레이크 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n,m;
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		int arr[][]=new int[n][n];
		
		//길 큰값으로 초기화
		int maxval=251*251;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i==j)
					arr[i][j]=0;
				else
					arr[i][j]=maxval;
			}
		}
		
		for(int i=0;i<m;i++) {
			str=br.readLine().split(" ");
			int a=Integer.parseInt(str[0])-1;
			int b=Integer.parseInt(str[1])-1;
			int c=Integer.parseInt(str[2]);
			//일방통행에서 길 안나있는곳 가중치 1
			arr[a][b]=0;
			arr[b][a]=1;
			if(c==1) {//양방향통행은 가중치 0
				arr[a][b]=0;
				arr[b][a]=0;
			}
		}
		
		//기존 길들을 일방통행에 없는길은 가중치 1, 양방향통행은 가중치 0으로 잡고
		//플루이드 워샬을 통해 연결가능한 모든길을 연결한뒤
		//a->b가 2의 가중치를 가지고 이동할수 있을경우 b->a가 0이라면 2개의 길을 바꾸면 이동 가능하다
		
		
		//플루이드워샬
		for(int i=0;i<n;i++) {//경유지
			for(int j=0;j<n;j++) {//출발지
				for(int k=0;k<n;k++) {//도착지
					arr[j][k]=Math.min(arr[j][k],arr[j][i]+arr[i][k]);
				}
			}
		}
		
		int k=Integer.parseInt(br.readLine());
		for(int i=0;i<k;i++) {
			str=br.readLine().split(" ");
			int a=Integer.parseInt(str[0])-1;
			int b=Integer.parseInt(str[1])-1;

			pw.println(arr[a][b]);
		}
		
		pw.flush();
		br.close();
		pw.close();
	}
}