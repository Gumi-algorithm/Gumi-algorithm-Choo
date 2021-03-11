import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BOJ_20055_컨베이어벨트위의로봇 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n,k;
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		k=Integer.parseInt(str[1]);
		//컨베이어벨트 회전을 queue에 넣어서 매번 회전시키는게 아닌
		//배열에 넣어두고 올라가는칸, 내려가는칸을 인덱스로 관리함
		int up=0;//올라가는칸
		int down=n-1;//내려가는칸
		
		
		//0 인덱스: 컨베이어벨트의 내구도를 관리
		//1 인덱스: 로봇의 존재유무를 관리(0은 로봇없음, 1은 로봇있음)
		int[][] arr=new int[2*n][2];
		str=br.readLine().split(" ");
		for(int i=0;i<2*n;i++) {
			arr[i][0]=Integer.parseInt(str[i]);
			arr[i][1]=0;
		}
		
		int zerocnt=0;//내구도가 0인벨트 갯수
		int step=0;//몇단계인지 저장하는 변수
		
		//4.내구도 0인게 k개가 되면 멈춤
		while(zerocnt<k) {
			//1. 벨트가 한칸 회전
			up=(up-1+2*n)%(2*n);
			down=(down-1+2*n)%(2*n);
			
			//2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
			//벨트위를 돌면서 로봇들 움직여
			for(int i=0;i<n;i++) {
				//가장 먼저 올라간 로봇 먼저니까 뒤에서부터 함
				int now=(down-i + 2*n)%(2*n);
				int next=(now+1)%(2*n);
				
				//로봇이 내려가는 위치에 있으면 내려간다
				if(now==down) {
					arr[now][1]=0;
					continue;
				}					
				
				//벨트위에 로봇없으면 그냥 지나가
				if(arr[now][1]==0)
					continue;
				
				//이동하려는 칸에 로봇이 있거나 내구도가 0이면 이동못함
				if(arr[next][1]==1 || arr[next][0]<=0)
					continue;
				
				//현재 칸에 로봇이 있으면 한칸 옮겨
				arr[now][1]=0;
				
				//다음칸 내구도 1 깍아
				arr[next][0]--;
				//내구도가 0이라면 zerocnt증가
				if(arr[next][0]==0)
					zerocnt++;
				//만약 다음칸이 내려가는 칸이면 로봇 내려가
				if(next==down)
					continue;
				//다음칸 로봇 옮겨
				arr[next][1]=1;
			}
			
			//3.올라가는 위치에 로봇이 없다면 로봇을 하나 올린다
			//내구도가 0인칸에는 로봇이 올라갈수 없다
			if(arr[up][0]>0 && arr[up][1]==0) {
				arr[up][1]=1;
				arr[up][0]--;
				
				//내구도가 0이라면 zerocnt증가
				if(arr[up][0]==0)
					zerocnt++;
			}
					
			step++;
		}
		pw.print(step);
		br.close();
		pw.flush();
		pw.close();
	}
}