import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_10974_모든순열 {
	static int[] arr;
	
	static void swap(int i,int j) {
		int tmp=arr[i];
		arr[i]=arr[j];
		arr[j]=tmp;
	}
	
	static boolean next_permutation(int n) {
		
		//n[i-1]<n[i]가 성립하는 마지막 i를 찾아라(뒤에서부터 꼭대기를 찾아라)
		int i=n-1;
		while(i>0 && arr[i-1]>=arr[i])i--;
		
		//꼭대기가 젤 첫번째면 안됨 더 이상 순열생성못함 그니까 탈출
		if(i==0)
			return false;
		
		//n[i-1]<n[j]가 성립하는 마지막 j를 찾아라
		int j=n-1;
		while(true) {
			if(arr[i-1]<arr[j])
				break;
			j--;
		}
		//스왑
		swap(i-1,j);
		
		//i부터 마지막까지 뒤집어서 오름차순이 되게 만들어
		int k=n-1;
		while(i<k)
			swap(i++,k--);
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		arr=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=i+1;
		}
		
		do {
			for(int i=0;i<n;i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
		}while(next_permutation(n));
		
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}

}
