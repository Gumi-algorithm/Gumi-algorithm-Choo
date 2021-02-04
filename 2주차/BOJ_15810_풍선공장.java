import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_15810_풍선공장 {
	static int[] arr;
	static int n,m;
	
	//minute동안 만들수있는 풍선 갯수 리턴
	static long balloon(long minute) {
		long sum=0;
		for(int i=0;i<arr.length;i++) {
			sum+=minute/arr[i];
		}
		return sum;
	}
	
	static long binarySearch(long a, long b) {
		if(a>b)
			return -1;
		
		long min=(a+b)/2;
		long ret=-1;
		long tmp=0;
		long bal=balloon(min);
		
		if(bal==m) {
			return min;
		}else if(bal>m) {
			tmp = binarySearch(a, min-1);
		}else if (bal<m){
			tmp = binarySearch(min+1, b);
		}
		if(tmp==-1 && bal>m)
			return min;
		return tmp;
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);

		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		arr=new int[n];
		str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		Arrays.sort(arr);
		int min=arr[0];
		int max=arr[arr.length-1];
		
		//m개 만드는 시간 찾아
		long minute=binarySearch(0, (long)max*m);
		
		while(true) {
			if(balloon(minute-1)!=m)//m개 만드는 시간중최소시간 찾아
				break;
			minute--;			
		}
		pw.print(Long.toString(minute));
		
		br.close();
		pw.close();
	}
}
/*
5 5
1 1 1 1 5

답:2초
*/