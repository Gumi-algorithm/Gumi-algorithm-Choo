import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1074_Z {

	static public int z(int n, int r, int c) {
		if (n == 0) {
			return 0;
		}
		int mid=(int)Math.pow(2, n-1);
		
		if(r<mid &&c<mid) {//왼쪽 위
			return z(n-1,r,c);
		}else if(r<mid && c>=mid) {//오른쪽 위
			return z(n-1,r,c-mid)+mid*mid;
		}else if(r>=mid && c<mid) {//왼쪽 아래
			return z(n-1,r-mid,c)+mid*mid*2;
		}else if(r>=mid && c>=mid) {//오른쪽 아래
			return z(n-1,r-mid,c-mid)+mid*mid*3;
		}
		return 0;
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String[] str=br.readLine().split(" ");
		int n=Integer.parseInt(str[0]);
		int r=Integer.parseInt(str[1]);
		int c=Integer.parseInt(str[2]);
		
		pw.print(z(n,r,c));
		pw.flush();
		pw.close();
		br.close();		
	}
}