import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_20119_클레어와물약 {

	static class Node{
		ArrayList<Integer> linked=new ArrayList<>();//현재 Node를 재료로하는 물약들
		ArrayList<ArrayList<Integer>> recipes=new ArrayList<>();
		ArrayList<Integer> needcnt=new ArrayList<>();// 제조하려면 몇개의 물약이 필요한지
		ArrayList<Integer> nowcnt=new ArrayList<>();//현재 몇개의 물약이 있는지
	}

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n,m;
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		ArrayList<Node> graph=new ArrayList<>();
		
		for(int i=0;i<n+1;i++)
			graph.add(new Node());
		
		for(int i=0;i<m;i++) {
			str=br.readLine().split(" ");
			int k=Integer.parseInt(str[0]);
			int r=Integer.parseInt(str[k+1]);
			ArrayList<Integer> recipe= new ArrayList<>();
			
			for(int j=0;j<k;j++) {
				int need=Integer.parseInt(str[j+1]);
				recipe.add(need);
				graph.get(need).linked.add(r);
			}
			graph.get(r).recipes.add(recipe);
			graph.get(r).needcnt.add(k);
			graph.get(r).nowcnt.add(0);
		}
		
		int ans=0;
		boolean isvisited[] =new boolean[n+1];//방문여부(만들수 있는 여부)
		Queue<Integer> nowHaved=new LinkedList<>();
		int l=Integer.parseInt(br.readLine());
		str=br.readLine().split(" ");
		for(int i=0;i<l;i++) {
			int num=Integer.parseInt(str[i]);
			nowHaved.add(num);
			isvisited[num]=true;
			ans++;
		}
		
		
		while(!nowHaved.isEmpty()) {
			int cur=nowHaved.poll();
			
			Node now=graph.get(cur);
			
			for(int i=0;i<now.linked.size();i++) {
				int next=now.linked.get(i);
				if(isvisited[next])
					continue;
				
				//해당 레시피를 쓰는 물약 찾아가서 레시피확인함
				for(int j=0;j<graph.get(next).recipes.size();j++) {//여러 레시피중
					for(int k=0;k<graph.get(next).recipes.get(j).size();k++) {//한개의 레시피를 골라서 cur물약을 쓰는지 확인
						if(cur==graph.get(next).recipes.get(j).get(k)) {
							int tmp=graph.get(next).nowcnt.get(j);
							graph.get(next).nowcnt.set(j,tmp+1);
							
							//재료가 충족될 경우
							if(tmp+1==graph.get(next).needcnt.get(j)) {
								isvisited[next]=true;
								ans++;
								nowHaved.add(next);
								break;
							}
						}
					}
					if(isvisited[next])
						break;
				}
				
				
			}			
		}
		
		StringBuilder sb=new StringBuilder();
		sb.append(ans).append("\n");
		for(int i=0;i<n+1;i++) {
			if(isvisited[i])
				sb.append(i).append(" ");
		}
		
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}

//한 물약을 만드는 레시피가 2개 이상일 수 있습니다.