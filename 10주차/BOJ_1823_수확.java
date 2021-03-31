import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

//답은 나오는데 메모이제이션 어떻게 해야할지 모르겟다...
/*
1 3 1 5 2 
이렇게 주어지면
1 2고르거나 2 1 고르거나 같음 이런 중복을 없애줘야대
그래서 인덱스 1~3을 메모이제이션 한다 생각했는데 틀렷대 (인덱스 설정 잘못함 이렇게 하는게 맞음)

그리고 다른 방법에서 [cnt][idx]로 해당 인덱스를 몇번째에 뽑나 라고 생각도 해봤는데 안댐

*/
public class BOJ_1823_수확 {

	static int [][]mem;//[i][j]i~j까지수확할때 최대값
	static int [] arr;
	static int n;
	static int dp(int start, int end , int cnt) {
		
		if(start>end)
			return 0;
		if(mem[start][end]!=0)
			return mem[start][end];
		int a=0,b=0;
		a=dp(start+1,end,cnt+1)+arr[start]*cnt;

		b=dp(start,end-1,cnt+1)+arr[end]*cnt;
		
		mem[start][end]= Math.max(a, b);
		
		return mem[start][end];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		n=Integer.parseInt(br.readLine());
		arr=new int[n];
		mem =new int[n][n];
		
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		int a=dp(0,n-1,1);

		
		int ans=a;
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}