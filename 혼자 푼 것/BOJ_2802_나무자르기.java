
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_2802_나무자르기 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n,m;
		String[] str=br.readLine().split(" ");
		
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		str=br.readLine().split(" ");
		List<Integer> arr=new ArrayList<>();
		for(int i=0;i<n;i++) {
			arr.add(Integer.parseInt(str[i]));
		}
		Collections.sort(arr,(a,b)->b-a);
		
		int ans=0;
		int max=arr.get(0);
		int min=0;
		int mid;
		while(min<max) {
			mid=(max+min)/2;
			long sum=0;
			if(mid<ans)
				break;
			for(Integer tmp : arr) {
				if(tmp-mid<=0)
					break;
				sum+=(tmp-mid);
				if(sum>=m) {
					ans=mid;
					break;
				}
			}
			if(sum < m) {
				max=mid;
			}else {
				min=mid+1;
			}
		}
		
		pw.print(ans);
		pw.close();
		br.close();
	}

}
