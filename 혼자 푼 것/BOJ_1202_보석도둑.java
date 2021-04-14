import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_1202_보석도둑 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n,k;
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);// 보석의 갯수
		k=Integer.parseInt(str[1]);// 가방의 갯수
		
		int jewel[][]=new int[n][2];
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			jewel[i][0]=Integer.parseInt(str[0]);//무게
			jewel[i][1]=Integer.parseInt(str[1]);//가격
		}
		
		int bag[]=new int[k];
		for(int j=0;j<k;j++) {
			bag[j]= Integer.parseInt(br.readLine());//가방의 최대무게
		}
		
		//가방에는 무조건 한개만 담을수 있음
		//그리디
		
		//보석을 무게순으로 오름차순 정렬
		Arrays.sort(jewel,(a,b)->a[0]-b[0]);
		
		//가방의 무게가 오름차순이 되게 정렬
		Arrays.sort(bag);
		
		//가방을 고른뒤 해당 가방이 담을수 있는 무게의 보석들중 가장 가치가 높은 보석을 담아
		
		//가치가 높은걸 우선하는 우선순위 큐에 가방이 담을수 있는 무게의 보석들을 집어넣음
		//다음 가방에서 증가한 무게만큼 보석을 더 넣고 똑같이 진행
		PriorityQueue<Integer> pq =new PriorityQueue<>((a,b)->b-a);
		long ans=0;
		int jewelidx=0;
		for(int i=0;i<k;i++) {
			
			//가방 무게보다 작은 보석들 pq에 넣어
			for(;jewelidx<n && jewel[jewelidx][0]<=bag[i];jewelidx++) {
				pq.offer(jewel[jewelidx][1]);
			}

			if(!pq.isEmpty()) {
				ans+=pq.poll();
			}
			if(jewelidx==n && pq.isEmpty())
				break;
		}
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}