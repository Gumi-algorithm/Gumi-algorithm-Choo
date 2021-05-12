import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_5875_오타 {
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);

		String[] str=br.readLine().split("");
		int n=str.length;
		int[] arr=new int[n];
		int[] sum=new int[n];
		int close=0;
		int ans=0;
		
		if(str[0].equals("(")) {
			arr[0]=1;
		}else {
			arr[0]=-1;
			close++;
		}
		sum[0]=arr[0];
			
		for(int i=1;i<n;i++) {
			if(str[i].equals("(")) {
				arr[i]=1;			
			}else {
				arr[i]=-1;
				close++;
			}
			sum[i]=sum[i-1]+arr[i];	
			if(sum[i]<0) {
				ans=close;
				break;
			}
		}
		
		//누적합이 음수가 되면 안됨 -1이 되는 시점에서 오타난거임 
			//-> 틀린거 포함 앞부분 닫힌괄호 갯수
		//끝이 양수로 나면 안됨
		
		//누적합을 끝까지 했을때 마지막이+2 또는 -2일때 한개가 오타난경우임
		//+2일경우 누적합이 0이었던 가장 나중 경우 이후부터 마지막까지 여는괄호 갯수 -1
		//한개 빼는 이유는 0이후 다음 첫 괄호는 무조건 (인데 이걸 )로 바꿔버리면 누적합이 -1이 되서 안됨
		//예외케이스 ((())(
		//(	(	(	)	)	(
		//1	2	3	2	1	2
		//답:1
		//왜인지 모르겠는데 누적합이 0일때를 기준으로잡고 첫괄호 제외니까 -1하는게아니라
		//처음부터 1일때로 해야하나봄
		if(sum[n-1]==2) {
			ans=0;
			for(int i=n-1;i>=0;i--) {
				if(sum[i]==1)
					break;
				if(arr[i]==1)
					ans++;	
			}
		}

		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();		
	}
}

/*
()()()
(	)	(	)	(	(
1	0	1	0	1	2
답:1
(	)	(	)	)	)
1	0	1	0	-1	-2
답:3
(	)	(	(	(	)
1	0	1	2	3	2
답:2
(	)	)	)	(	)
1	0	-1	-2	-1	-2
답:2
(	(	(	)	(	)
1	2	3	2	3	2
답:3
)	)	(	)	(	)
-1	-2	-1	-2	-1	-2
답:1


((()))
(	(	(	)	)	(
1	2	3	2	1	2
답:1
(	(	(	)	(	)
1	2	3	2	3	2
답:3
(	(	(	(	)	)
1	2	3	4	3	2
답:3
(	(	)	)	)	)
1	2	1	0	-1	-2
답:3
(	)	(	)	)	)
1	0	1	0	-1	-2
답:3
)	(	(	)	)	)
-1	0	1	0	-1	-2
답:1

(	)	)	(	)	)
1	0	-1	0	-1	-2
답:2
*/
