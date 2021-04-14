import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_2473_세용액 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		String[] str=br.readLine().split(" ");
		int[] arr=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		Arrays.sort(arr);//오름차순 정렬
		
		
		//가운데꺼 골라놓고 양옆에서 투포인터로 접근해
		long min=3000000000l;
		int anss=0;
		int ansm=0;
		int anse=0;
		for(int mid=1;mid<n-1;mid++) {
			
			int s=0;
			int e=n-1;
			long sum=(long)arr[s]+(long)arr[mid]+(long)arr[e];
			if(Math.abs(sum)<Math.abs(min)) {
				anss=s;
				ansm=mid;
				anse=e;
				min=sum;
			}
			while(s<mid && mid<e) {
				
				if(sum==0)
					break;
				
				if(sum>0) {
					//0보다 크니까 오른쪽 값을 작은걸로 바꿔야댐
					long tmp= sum-arr[e--]+arr[e];
					
					//만약 같은거 두개 골랏을경우 탈출
					if(e==mid) {
						break;
					}
					sum=tmp;
					//정답 갱신
					if(Math.abs(sum)<Math.abs(min)) {
						anss=s;
						anse=e;
						ansm=mid;
						min=sum;
					}
				}else {
					//0보다 작으니까 왼쪽 값을 큰걸로 바꿔야댐
					long tmp= sum-arr[s++]+arr[s];
					
					//만약 같은거 두개 골랏을경우 탈출
					if(s==mid) {
						break;
					}
					sum=tmp;
					//정답 갱신
					if(Math.abs(sum)<Math.abs(min)) {
						anss=s;
						anse=e;
						ansm=mid;
						min=sum;
					}					
				}
			}
			if(min==0)
				break;			
		}
		
		pw.print(arr[anss]+" "+arr[ansm]+" "+arr[anse]);
		br.close();
		pw.flush();
		pw.close();
	}
}
