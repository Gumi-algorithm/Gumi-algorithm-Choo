import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class BOJ_2075_N번째큰수 {

	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		PriorityQueue<Integer> npq=new PriorityQueue<>();//큰수 n개를 저장하는 pq(최소힙)
		String[] str;
		int num;
		int ans=0;
		str=br.readLine().split(" ");
		//처음엔 큐 가득채움
		for(int i=0;i<n;i++) {
			npq.offer(Integer.parseInt(str[i]));
		}
		ans=npq.peek();
		for(int i=1;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				num=Integer.parseInt(str[j]);
				if(ans<num) {//현재 n번째 큰 수보다 큰게 들어오면 갱신해줘
					npq.offer(num);
					npq.poll();//n개만 들어가 있어야되니까 1개 넣고 1개빼는거임
					ans=npq.peek();
				}
			}
		}
		pw.print(ans);
		pw.flush();
		pw.close();
		br.close();
	}
}
