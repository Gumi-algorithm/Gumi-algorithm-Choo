import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_1931_회의실배정 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		int[][] arr=new int[n][2];
		String[] str;
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			arr[i][0]=Integer.parseInt(str[0]);
			arr[i][1]=Integer.parseInt(str[1]);
		}
		Arrays.sort(arr,(a,b)->{
			if(a[1]==b[1])
				return a[0]-b[0];
			return a[1]-b[1];
			});
		int now=0;
		int cnt=1;
		for(int i=1;i<n;i++) {
			if(arr[i][0]>=arr[now][1]) {
				cnt++;
				now=i;
			}
		}
		pw.print(cnt);
		br.close();
		pw.flush();
		pw.close();
	}
}
/*
5
1 3
4 4
3 4
2 5
4 5
답4
*/