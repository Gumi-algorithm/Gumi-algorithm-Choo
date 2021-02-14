import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1259_팰린드롬수 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		String str;
		int length;
		
		while(true) {
			str=br.readLine();
			if(str.equals("0"))
				break;
			length=str.length();
			int jud=1;//다른게 존재하는지 판단하는 변수
			for(int i=0;i<length/2;i++) {
				if(str.charAt(i)!=str.charAt(length-i-1)) {
					jud=0;
					break;
				}
			}
			if(jud==0)
				pw.print("no\n");
			else
				pw.print("yes\n");
		}
		pw.flush();
		pw.close();
		br.close();
	}

}
