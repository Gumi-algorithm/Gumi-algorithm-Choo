import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_9742_순열 {
	static char[] arr;
	
	static void swap(int i,int j) {
		char tmp=arr[i];
		arr[i]=arr[j];
		arr[j]=tmp;
	}
	
	static boolean nextPermutation(int n) {		
		//arr[i-1]<arr[i]가 성립하는 마지막 i찾아
		int i=n-1;
		while(i>0&& arr[i-1]>=arr[i])i--;
		if(i==0)
			return false;
		
		//arr[i-1]<arr[j]가 성립하는 마지막 j찾아
		int j=n-1;
		while(arr[i-1]>=arr[j])j--;
		
		//i-1,j 스왑
		swap(i-1,j);
		
		//i부터 오름차순으로 바꿔 (뒤집어)
		int k=n-1;
		while(i<k)
			swap(i++,k--);
		
		return true;
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();

		String str;
		String[] strarr;
		while(true) {
			str=br.readLine();
			if(str==null)
				break;
			strarr=str.split(" ");
			arr=strarr[0].toCharArray();
			int size=arr.length;
			int num=Integer.parseInt(strarr[1]);
			boolean isSuccess=true;
			for(int i=1;i<num;i++) {
				if(!nextPermutation(size)) {
					//없는경우
					isSuccess=false;
					break;
				}
			}
			sb.append(str).append(" = ");
			if(isSuccess) {
				for(int i=0;i<size;i++) {
					sb.append(arr[i]);
				}
				sb.append("\n");
			}else {
				sb.append("No permutation\n");
			}
		
		}
		
		pw.print(sb);
		pw.flush();
		pw.close();
		br.close();
	}
}