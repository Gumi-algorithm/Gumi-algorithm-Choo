import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2531_회전초밥 {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		String[] str=br.readLine().split(" ");
		int n,d,k,c;
		n=Integer.parseInt(str[0]);//접시의 수
		d=Integer.parseInt(str[1]);//초밥의 가짓수
		k=Integer.parseInt(str[2]);//연속해서 먹는 접시의 수
		c=Integer.parseInt(str[3]);//쿠폰번호
				
		int []arr= new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		int []isselected=new int[d+1];
		
		int s=0;
		int e=k-1;
		int cat=0;//종류
		int max=0;
		//처음 범위 0~k-1 에서 몇종류를 가지는지 체크
		for(int i=0;i<k;i++) {
			if(isselected[arr[i]]==0) {
				cat++;
			}
			isselected[arr[i]]++;			
		}
		max=cat;
		if(isselected[c]==0) {
			max=cat+1;
		}
		
		for(int i=0;i<n;i++) {
			isselected[arr[s]]--;
			if(isselected[arr[s]]==0)//마지막 한개 남았던 거라면 종류 감소
				cat--;
			
			//오른쪽으로 한칸 이동
			s = (s+1)%n;
			e = (e+1)%n;
			
			if(isselected[arr[e]]==0)//중복선택아니면 종류 증가
				cat++;
			isselected[arr[e]]++;
			
			if(isselected[c]==0)//쿠폰초밥을 선택안했으면 종류 +1 해서 max비교해야함
				max=Math.max(max, cat+1);
			else
				max=Math.max(max, cat);			
		}
		
		pw.print(max);
		br.close();
		pw.flush();
		pw.close();
	}
}