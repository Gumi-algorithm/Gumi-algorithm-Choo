import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_4779_칸토어집합 {
	static String str;
	static char[] arr;
	
	static void divcon(int start, int end) {
		int mid1=start+(end-start)/3;
		int mid2=start+(end-start)/3*2;
		for(int i=mid1 ; i<mid2;i++) {
			arr[i]=' ';
		}
		//사라지는 선의 갯수가 1이면 중단
		if(mid2 - mid1 <=1)
			return;
		divcon(start,mid1);
		divcon(mid2,end);
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		StringBuilder sb=new StringBuilder();
		while((str=br.readLine())!=null) {
			int num=Integer.parseInt(str);
			
			arr=new char[(int) Math.pow(3, num)];
			Arrays.fill(arr, '-');
			divcon(0,arr.length);
			
			for(char c:arr) {
				sb.append(c);
			}
			sb.append("\n");
		}
		pw.print(sb);
		br.close();
		pw.close();
	}
}
