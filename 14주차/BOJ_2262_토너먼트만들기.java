import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

//허프만코드와 유사함
public class BOJ_2262_토너먼트만들기 {
	
	static class Node{
		int num;
		int idx;
		public Node(int num, int idx) {
			super();
			this.num = num;
			this.idx = idx;
		}
		
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		int arr[]=new int[n];
		Node sarr[]=new Node[n];//정렬용 배열
		String[] str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
			sarr[i]=new Node(arr[i],i);
		}
		
		//큰거부터 탈락시켜  
		//대신 양옆 비교해서 최소 차이로 탈락시켜
		//어떻게 구현해야할지 모르겟다 으어어어
		
		//어차피 n은 256개라서 n^2이어도 통과함 그냥 노가다 돌려
		Arrays.sort(sarr, (a,b)->b.num-a.num);
		int ans=0;
		for(int i=0;i<n-1;i++) {//1등은 남겨야되니까 n-1까지만 돌려
			int nowidx=sarr[i].idx;//현재 탈락시켜야 되는 수 인덱스
			int nownum=sarr[i].num;
			
			//nowidx기준 살아있는 애들중 왼쪽 숫자 가져와
			int leftidx=nowidx-1;
			int leftnum=0;
			while(leftidx>=0) {
				if(arr[leftidx]!=0) {
					leftnum=arr[leftidx];
					break;	
				}
				leftidx--;
			}
			
			//nowidx기준 살아있는 애들중 오른쪽 숫자 가져와
			int rightidx=nowidx+1;
			int rightnum=0;
			while(rightidx<n) {
				if(arr[rightidx]!=0) {
					rightnum=arr[rightidx];
					break;	
				}
				rightidx++;
			}
			
			//현재숫자와 왼쪽,오른쪽 숫자를 비교해서 등수차이가 최소가 되게 매칭 시켜서 현재숫자 탈락시켜
			if(leftnum==0) {
				ans+=Math.abs(nownum-rightnum);
			}else if(rightnum==0) {
				ans+=Math.abs(nownum-leftnum);
			}else {
				int a=Math.abs(nownum-rightnum);
				int b=Math.abs(nownum-leftnum);
				ans+=Math.min(a, b);
			}		
			arr[nowidx]=0;//탈락한 애는 0으로 만듬
		}
		
		pw.print(ans);
		pw.flush();
		pw.close();
		br.close();
	}
}