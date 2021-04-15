import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_17298_오큰수 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		
		int n=Integer.parseInt(br.readLine());
		
		int arr[]=new int[n];
		String[] str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		
		int[] stk=new int[n];//스택
		int[] ans=new int[n];//정답
		int idx=0;
		for(int i=0;i<n;i++) {
			if(idx==0) {//스택이 비었으며 집어넣음
				stk[idx++]=i;
				ans[i]=-1;//정답 일단 -1로 초기화
				continue;
			}
			
			//스택이 안비어있으면
			//스택에 들어있던 애들이랑 현재 숫자랑 비교해서 NGE성립하는애들은 답 입력하고
			//성립안하는애들 만나면 현재숫자 집어넣어
			while(idx>0 && arr[stk[idx-1]]<arr[i]) {
				idx--;
				ans[stk[idx]]=arr[i];//정답 저장
			}
			stk[idx++]=i;//비교 다 한 뒤 스택에 저장
			ans[i]=-1;//정답 일단 -1로 초기화
		}
		
		for(int i=0;i<n;i++) {
			sb.append(ans[i]).append(" ");
		}
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}