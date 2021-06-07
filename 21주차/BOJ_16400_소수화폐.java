import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BOJ_16400_소수화폐 {

	static ArrayList<Integer> primenum = new ArrayList<>();
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n = Integer.parseInt(br.readLine());
		
		// 소수를 구함
		arr=new int[n+1];
		arr[0] = -1;
		arr[1] = -1;
		for (int i = 2; i * i < n+1; i++) {
			if (arr[i] != 0)
				continue;
			for (int j = i * i; j < n+1; j += i) {
				arr[j] = -1;
			}
		}

		//소수를 따로 모아
		for (int i = 2; i < n+1; i++) {
			if (arr[i] == 0)
				primenum.add(i);
		}



		// 구한 소수를 이용해 DP 돌림 (2,3 뽑은거랑 3,2뽑은게 같음)
		// 0-n 냅색 문제처럼 풀면됨	
		dp = new int[n+1];
		int ans = 0;
		for (int i = 0; i < primenum.size(); i++) {
			int now = primenum.get(i);
			if(now>n)
				break;
			dp[0] = 1;// 0원으로 딱떨어지면 카운트 되어야 되니까 추가함
			for (int j = 2; j <= n; j++) {
				int tmp = 0;
				if (j - now >= 0)
					tmp = dp[j - now];
				dp[j] = (dp[j]%123456789 + tmp%123456789)%123456789;
			}
		}
		ans=dp[n];
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}