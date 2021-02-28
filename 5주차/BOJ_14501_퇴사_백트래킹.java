import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_14501_퇴사_백트래킹 {

	static int[][] arr;
	static int[] day=new int[21];//n최대 15, t최대 5
	static int n;
	public static int powerset(int idx) {
		if(idx==n+1) {
			
			return 0;
		}
		
		
		//선택 안했을때
		int ret=powerset(idx+1);
		int nowt=arr[idx][0];
		int nowp=arr[idx][1];
		if(idx+nowt-1>n)
			return ret;
		//모든 날짜가 가능한지 확인(어차피 날짜 오름차순이라서 전부 검사할필요없음)
//		for(int i=0;i<nowt;i++) {
//			if(day[idx+i]==1)
//				return ret;
//		}
		if(day[idx]==1)
			return ret;
		
		//가능하다면 day 1로 바꿈
		for(int i=0;i<nowt;i++) {
			day[idx+i]=1;
		}
		int tmp=powerset(idx+1)+nowp;
		ret=ret<tmp?tmp:ret;
		//다시 원래대로
		for(int i=0;i<nowt;i++) {
			day[idx+i]=0;
		}
		return ret;
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);
		
		n=Integer.parseInt(br.readLine());
		arr=new int[n+1][2];
		String[] str;
		for(int i=1;i<=n;i++) {
			str=br.readLine().split(" ");
			arr[i][0]=Integer.parseInt(str[0]);
			arr[i][1]=Integer.parseInt(str[1]);			
		}
		//1<= n <= 15 백트래킹?
		int ans=powerset(1);
		
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}