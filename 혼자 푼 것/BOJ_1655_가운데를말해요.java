import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class BOJ_1655_가운데를말해요 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		int[] arr=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(br.readLine());			
		}
		
		PriorityQueue<Integer> minq=new PriorityQueue<>();
		PriorityQueue<Integer> maxq=new PriorityQueue<>((a,b)->b-a);
		
		maxq.offer(arr[0]);
		pw.println(maxq.peek());
		for(int i=1;i<n;i++) {
			int now=arr[i];
			
			//처음 maxq에 넣은값 기준으로 큰값이 들어오면 minq에 넣고
			// 작은값이 들어오면 maxq에 넣어
			if(now>maxq.peek()) {
				minq.offer(now);
			}else
				maxq.offer(now);
			
			//maxq와 minq가 사이즈가 같거나 ,maxq가 사이즈가 1더 커야함
			//그래야 항상 maxq에서 뽑은값이 중간값이됨
			//이런 작업을 통해 한쪽큐에 값이 쏠리는걸 바지해줌
			while(maxq.size()-minq.size()>1) {
				minq.offer(maxq.poll());
			}

			while(maxq.size()-minq.size()<0) {
				maxq.offer(minq.poll());
			}
			pw.println(maxq.peek());
		}

		br.close();
		pw.flush();
		pw.close();
	}
}
