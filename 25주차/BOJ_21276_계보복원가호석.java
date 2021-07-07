import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BOJ_21276_계보복원가호석 {

	public static void main(String[] args)throws IOException	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		
		String[] people=br.readLine().split(" ");
		Map<String, Integer> name=new HashMap<String, Integer>();
		
		//이름을 사전순 정렬
		Arrays.sort(people);
		
		for(int i=0;i<n;i++) {
			name.put(people[i], i);
		}
		
		
		//해당 사람이 조상을 가지고있는지 체크
		boolean[] hasParent=new boolean[n];		
		//자신이 가지고 있는 자손들
		ArrayList<Integer>[] children=new ArrayList[n];
		//자신이 가지고 있는 자식들
		ArrayList<Integer>[] answer=new ArrayList[n];
		int[] parentCnt=new int[n];
		
		for(int i=0;i<n;i++) {
			children[i]=new ArrayList<>();
			answer[i]=new ArrayList<>();
		}
		
		
		int k=Integer.parseInt(br.readLine());
		
		for(int i=0;i<k;i++) {
			String[] str=br.readLine().split(" ");
			int child=name.get(str[0]);
			int parent=name.get(str[1]);
			
			//모든 조상을 완벽하게 기억하고 있다.
			//그렇기때문에 주어지는 것만 연결하면됨(사이의 관계를 고려안해도됨)
			//다른 i번째에서 연결 해줄거임
			hasParent[child]=true;
			children[parent].add(child);
			
			//자신에게 들어오는 에지의 갯수(조상의 갯수)를 저장해둠
			parentCnt[child]++;
		}
		
		//위상정렬 비슷하게 돌릴거임
		//자신에게 들어오는 에지가 더이상 없을경우(더이상 조상이 없을경우) q에 집어넣음
		Queue<Integer> q=new LinkedList<Integer>();
		
		//에지를 없애는게 아닌 들어오는 에지의 갯수를 기억해놓고 자신의 에지갯수와 비교하게함
		int[] nowEdgeCnt=new int[n];
	
		//루트들을 큐에 집어넣음, 나중에 출력을위헤 root배열에 저장
		ArrayList<Integer> root=new ArrayList<>();
		for(int i=0;i<n;i++) {
			if(!hasParent[i]) {
				root.add(i);
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int cur=q.poll();
			
			for(int i=0;i<children[cur].size();i++) {
				int now=children[cur].get(i);//cur의 자손들
				nowEdgeCnt[now]++;
				
				//cur을 마지막으로 자신을 가르키는 모든 조상들이 사라지면 마지막인 cur이 자신의 부모임
				if(nowEdgeCnt[now]==parentCnt[now]) {
					answer[cur].add(now);
					q.add(now);
				}
			}			
		}
		
		StringBuilder sb=new StringBuilder();
		
		sb.append(root.size()).append("\n");
		for(int i : root) {
			sb.append(people[i]).append(" ");
		}
		sb.append("\n");
		for(int i=0;i<n;i++) {
			sb.append(people[i]).append(" ").append(answer[i].size()).append(" ");
			//자식도 사전순으로 정렬
			Collections.sort(answer[i]);
			for(int j=0;j<answer[i].size();j++) {
				sb.append(people[answer[i].get(j)]).append(" ");
			}
			sb.append("\n");
		}
		
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}