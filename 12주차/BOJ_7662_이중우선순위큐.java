import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BOJ_7662_이중우선순위큐 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int t=Integer.parseInt(br.readLine());
		
		while(t-->0) {
			
			int n=Integer.parseInt(br.readLine());
			
			PriorityQueue<Long> maxq=new PriorityQueue<>((a,b)->b>a?1:-1);
			PriorityQueue<Long> minq=new PriorityQueue<>();
			Map<Long, Integer> map=new HashMap<Long, Integer>();//각 숫자가 큐에 몇개나 들어있는지 정보를 가짐(isvisited같은역활)
			
			for(int i=0;i<n;i++) {
				String []str=br.readLine().split(" ");
				long num=Long.parseLong(str[1]);
				if(str[0].equals("I")) {//삽입
					maxq.offer(num);
					minq.offer(num);
					if(map.containsKey(num))//동일한 정수가 삽입될수 있음
						map.put(num,map.get(num)+1);
					else
						map.put(num, 1);
				}else if(str[0].equals("D")) {//삭제
					if(num==1) {//최댓값 삭제					
						while(!maxq.isEmpty() && map.get(maxq.peek())<=0)//쓰래기값들 정리
							maxq.poll();
						
						//최댓값 삭제하고 map에 갯수 정보 업데이트
						if(!maxq.isEmpty()) {
							long tmp=maxq.poll();
							map.put(tmp, map.get(tmp)-1);
						}
						
							
					}else {//최소값 삭제
						while(!minq.isEmpty() && map.get(minq.peek())<=0)//쓰래기값들 정리
							minq.poll();
						
						//최소값 삭제하고 map에 갯수 정보 업데이트
						if(!minq.isEmpty()) {
							long tmp=minq.poll();
							map.put(tmp, map.get(tmp)-1);
						}
					}
				}
			}
			//정답 출력하기전에 쓰래기값 정리
			while(!maxq.isEmpty() && map.get(maxq.peek())<=0)//쓰래기값들 정리
				maxq.poll();
			while(!minq.isEmpty() && map.get(minq.peek())<=0)//쓰래기값들 정리
				minq.poll();
			if(minq.isEmpty() || maxq.isEmpty())
				pw.println("EMPTY");
			else
				pw.println(maxq.poll()+" "+minq.poll());
		}
		
		br.close();
		pw.flush();
		pw.close();
	}
}
/*
Input
1
5
I 2
I 3
D -1
I 1
D 1
Output
2 1
Answer
1 1

1
4
I 3
I 3
I 3
D 1
답 
3 3
*/