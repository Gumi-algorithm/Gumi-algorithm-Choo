
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1013_Contact {
	static String str;

	static boolean verify(int idx) {
		if(idx==str.length())
			return true;
		try {
			boolean ret;
			if (str.charAt(idx++) == '1') {
				// 100+1+
				if(str.charAt(idx++)=='0'&&str.charAt(idx++)=='0') {//00
					while(str.charAt(idx)=='0') idx++;//+
					if(str.charAt(idx++)=='1') {//10011001이런경우 대비
						if(idx==str.length())
							return true;
						while(str.charAt(idx)=='1') {
							idx++;
							if(idx==str.length())
								return true;
							if(str.charAt(idx)=='0') {
								if(verify(idx-1))
									return true;
							}
							
						}					
					}
					ret=verify(idx);
				}else {
					return false;
				}
			} else {
				// 01
				if(str.charAt(idx++)=='1') {
					ret=verify(idx);
				}else {
					return false;
				}
			}
			return ret;
		} catch (StringIndexOutOfBoundsException e) {
			return false;
		}
		
	}

	public static void main(String[] args) throws IOException {
		// (100+1+|01)+
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int t;

		t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			str = br.readLine();
			if(verify(0)) {
				bw.write("YES\n");
			}else {
				bw.write("NO\n");
			}
//			bw.flush();
		}
		br.close();
		bw.close();
	}

}
/*
 * 1을 만나면 0 0 을 확인하고 0을 전부 없앤뒤 1을 확인한뒤 1을 전부 없앰 그리고 재귀
 * 0을 만나면 1을 확인하고 재귀
 * 
 */

//4
//1001
//01
//10001
//10000111111001010110001111111
