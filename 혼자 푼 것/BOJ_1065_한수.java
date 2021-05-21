
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1065_한수 {

	public static int isHansu(int num) {
		String str[]=Integer.toString(num).split("");

		int val=-1;
		int dif=-1;
		int tmp;
		if(num<=99)
			return 1;
		
		for (int i=0;i<str.length;i++) {
			if(i==1) {
				dif= val-Integer.parseInt(str[i]);
//				dif= dif<0?dif*(-1):dif;
				
				val=Integer.parseInt(str[i]);
				continue;
			}
			if(i==0) {
				val=Integer.parseInt(str[i]);
				continue;
			}
			
			tmp = val-Integer.parseInt(str[i]);
//			tmp= tmp<0? tmp*-1:tmp;
			
			if(tmp!=dif)
				return 0;
		}
		return 1;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int num= Integer.parseInt(br.readLine());
		int cnt=0;
		for(int i=1;i<=num;i++) {
			if(i<=99) {
				cnt++;
				continue;
			}
			cnt+=isHansu(i);
		}
		bw.write(Integer.toString(cnt));
		br.close();
		bw.close();
	}
}
