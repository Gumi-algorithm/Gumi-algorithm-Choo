
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_14890_경사로 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();


		int ans=0;

			
		String[] str=br.readLine().split(" ");
		int n,x;
		n=Integer.parseInt(str[0]);
		x=Integer.parseInt(str[1]);
		int [][] arr=new int[n][n];
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}
		}
		
		int nowcnt=0;
		int preval=0;
		int lowcheck=0;//낮아지는 경사를 놓아야할경우 인지 확인하는 변수
		//가로축으로 먼저 체크
		for(int i=0;i<n;i++) {
			nowcnt=0;
			preval=0;
			lowcheck=0;
			for(int j=0;j<n;j++) {
				if(j==0) {
					preval=arr[i][j];
				}
				
				if(arr[i][j]==preval) {
					nowcnt++;
				}else {//다른 높이 나온경우
					if(Math.abs(arr[i][j]-preval)!=1)//높이차가 1보다 크면 탈출
						break;
					
					if(lowcheck==1) {//아직 이전경우의 낮아지는 경사로 건설 못했으면 탈출(실패)
						break;
					}
					
					if(arr[i][j]>preval) {//높아지는 경사인경우
						if(nowcnt<x)//경사로 놓을 공간없으면 탈출
							break;
						//있으면 cnt초기화하고 preval수정
						nowcnt=1;
						preval=arr[i][j];
					}else {//낮아지는 경사인경우
						lowcheck=1;
						nowcnt=1;
						preval=arr[i][j];
					}						
				}
				if(lowcheck==1) {
					if(nowcnt>=x) {
						lowcheck=0;
						nowcnt=0;
					}
				}
				if(j==n-1) {//끝에 도달했을경우
					if(lowcheck==0)//경사로 전부 가능하면 갯수 ++
						ans++;
				}
			}
		}
		
		nowcnt=0;
		preval=0;
		lowcheck=0;//낮아지는 경사를 놓아야할경우 인지 확인하는 변수
		//세로축 체크
		for(int j=0;j<n;j++) {
			nowcnt=0;
			preval=0;
			lowcheck=0;
			for(int i=0;i<n;i++) {
				if(i==0) {
					preval=arr[i][j];
				}
				
				if(arr[i][j]==preval) {
					nowcnt++;
				}else {//다른 높이 나온경우
					if(Math.abs(arr[i][j]-preval)!=1)//높이차가 1보다 크면 탈출
						break;
					
					if(lowcheck==1) {//아직 이전경우의 낮아지는 경사로 건설 못했으면 탈출(실패)
						break;
					}
					
					if(arr[i][j]>preval) {//높아지는 경사인경우
						if(nowcnt<x)//경사로 놓을 공간없으면 탈출
							break;
						//있으면 cnt초기화하고 preval수정
						nowcnt=1;
						preval=arr[i][j];
					}else {//낮아지는 경사인경우
						lowcheck=1;
						nowcnt=1;
						preval=arr[i][j];
					}						
				}
				if(lowcheck==1) {
					if(nowcnt>=x) {
						lowcheck=0;
						nowcnt=0;
					}
				}
				if(i==n-1) {//끝에 도달했을경우
					if(lowcheck==0)//경사로 전부 가능하면 갯수 ++
						ans++;
				}
			}
		}
	
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}

}