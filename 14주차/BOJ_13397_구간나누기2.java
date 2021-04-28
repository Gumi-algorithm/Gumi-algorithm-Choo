import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_13397_구간나누기2 {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n,m;
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		int[] arr=new int[n];
		str=br.readLine().split(" ");
		int a=0;
		int b=0;
		for(int i=0;i<n;i++) {
			int tmp=Integer.parseInt(str[i]);
			arr[i]=tmp;
			b=Math.max(b, tmp);
			
		}
		
		while(a<b) {
			int mid=(a+b)/2;//구간의 점수의 최댓값의 최솟값
			int min=0;//첫번째 부터 시작 (arr의 인덱스)
			int max=0;//min,max는 현재 구간에서 최소값, 최대값의 인덱스
			int cnt=1;//현재 구간 갯수
			int isSuccess=1;//m이하의 구간으로 mid이 최댓값의 최솟값으로 만족하는경우
			for(int i=1;i<n;i++) {
				int mindif=Math.abs(arr[min]-arr[i]);//현재 구간의 최솟값 - i번째값
				int maxdif=Math.abs(arr[max]-arr[i]);//현재 구간의 최댓값 - i번째값
				
				//값이 차이가 mid를 넘으면 i번째 값은 현재구간이 아닌 다음구간으로 넘어감
				if(mindif>mid || maxdif>mid) {
					cnt++;
					min=i;
					max=i;					
				}else {//갑의 차이가 mid이하이면 i번째 값은 현재구간에 포함시키고 min,max를 갱신함
					if(arr[i]>arr[max]) {
						max=i;
					}
					if(arr[i]<arr[min]) {
						min=i;
					}					
				}
				if(cnt>m) {//m개의 구간으로 mid가 최댓값의 최솟값이 만족못하면 mid 늘려
					a=mid+1;
					isSuccess=0;
					break;
				}				
			}
			if(isSuccess==1) {// mid를 최댓값의 최솟값으로 놓았을때 m개의 구간 이하가 나온다면 mid 줄여
				b=mid;
			}			
		}
		pw.print(a);
		pw.flush();
		pw.close();
		br.close();
	}
}