import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_10972_다음순열 {

	static void swap(int[] arr,int i, int j) {
		int tmp=arr[i];
		arr[i]=arr[j];
		arr[j]=tmp;
	}
	
	static boolean next_premutation(int n,int[] arr) {
		//1. n[i-1]<n[i]이 성립하는 가장 마지막 i를 찾아라
		int i=n-1;
		while(i>0 && arr[i-1]>=arr[i])i--;
		if(i==0)
			return false;
		
		//2. n[i-1]<n[j]이 성립하는 가장 마지막 j를 찾아라
		int j=n-1;
		while(arr[i-1]>=arr[j])j--;
		
		//3. 스왑
		swap(arr,i-1,j);
		
		//i이후 오름차순으로 만들어(뒤집으면됨)
		int k=n-1;
		while(i<k)
			swap(arr,i++,k--);
		return true;
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		
		int n=Integer.parseInt(br.readLine());
		int[] arr=new int[n];
		String[] str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		if(next_premutation(n,arr)) {
			for(int i=0;i<n;i++)
				sb.append(arr[i]+" ");
		}else
			sb.append(-1);	
		
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}