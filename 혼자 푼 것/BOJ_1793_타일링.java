import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class BOJ_1793_타일링 {
	static BigInteger bi[]=new BigInteger[251];
	
	static BigInteger fibo(int num) {
		if(num==0)//0일땐 아무것도안하는것도 1가지로 친대
			return BigInteger.ONE;
		if(num==1)
			return BigInteger.ONE;
		if(bi[num]!=null)
			return bi[num];
		bi[num]= fibo(num-1).add(fibo(num-2).multiply(BigInteger.valueOf(2)));
		return bi[num];
	}	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		
		String str;
//		int i=1;
		while((str=br.readLine())!=null) {
			sb.append(fibo(Integer.parseInt(str))).append("\n");
//			if(i++==6)
//				break;
		}
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}

}
