import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_8980_택배 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String[] str=br.readLine().split(" ");
		int n=Integer.parseInt(str[0]);
		int c=Integer.parseInt(str[1]);
		int m=Integer.parseInt(br.readLine());
		int[][] arr=new int[m][3];

		for(int i=0;i<m;i++) {
			str=br.readLine().split(" ");
			arr[i][0]=Integer.parseInt(str[0]);//출발
			arr[i][1]=Integer.parseInt(str[1]);//도착
			arr[i][2]=Integer.parseInt(str[2]);//갯수
			

		}
		//도착이 빠른순으로 정렬 같으면 출발 빠른순
		Arrays.sort(arr,(a,b)->(a[1]==b[1]?a[0]-b[0]:a[1]-b[1]));
		
		int[] truck=new int[n+1];//각 마을별 담긴 박스갯수
		int answer=0;
		for(int i=0;i<m;i++) {
			int s=arr[i][0];
			int d=arr[i][1];
			int cnt=arr[i][2];
			
			//싣을수 잇는지 확인
			for(int j=s;j<d;j++) {
				if(c-truck[j]<cnt) {//용량 부족하면
					cnt=c-truck[j];//일부만 넣어
				}
				if(cnt==0)
					break;
			}
			//싣어		
			if(cnt!=0) {
				for(int j=s;j<d;j++)
					truck[j]+=cnt;
				answer+=cnt;
			}			
		}
		pw.print(answer);
		pw.flush();
		br.close();
		pw.close();
	}
}