import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_11437_LCA {
	static class Node {
		ArrayList<Integer> vertex = new ArrayList<>();
		int val;
		int depth;
		int sup;//부모;
	}
	static ArrayList<Node> tree = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n = Integer.parseInt(br.readLine());
		String[] str;
		//tree사이즈 초기화
		for(int i=0;i<=n;i++) {
			tree.add(new Node());
		}
		
		// 트리 생성
		for (int i = 0; i < n-1; i++) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			tree.get(a).vertex.add(b);
			tree.get(a).val=a;
			tree.get(b).vertex.add(a);
			tree.get(b).val=b;
			
		}

		// 깊이 지정(루트깊이 1)
		int [] isvisited=new int[n+1];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 1, 1 });
		isvisited[1]=1;
		isvisited[0]=1;		
		while (!q.isEmpty()) {
			int nowval = q.peek()[0];
			int nowdep = q.poll()[1];
			Node now = tree.get(nowval);
			int size = now.vertex.size();
			for (int i = 0; i < size; i++) {
				int nowchild=now.vertex.get(i);
				if(isvisited[nowchild]==1)
					continue;
				isvisited[nowchild]=1;
				tree.get(nowchild).sup=nowval;
				tree.get(nowchild).depth=nowdep+1;
				q.offer(new int[]{nowchild,nowdep+1});
			}
		}
		
		//이제 공통부모 찾아
		int m=Integer.parseInt(br.readLine());
		for(int i=0;i<m;i++) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			//a에 깊이 깊은애(큰 애)가 들어가게
			if(tree.get(a).depth<tree.get(b).depth) {
				int tmp=a;
				a=b;
				b=tmp;
			}
			Node nodea=tree.get(a);
			Node nodeb=tree.get(b);
			
			//a의 깊이를 b의 깊이와 같게 만들어
			while(nodea.depth!=nodeb.depth) {
				nodea=tree.get(tree.get(nodea.val).sup);
			}
			//깊이가 같아졋으면 부모가 같아질때까지 위로 올라가
			if(nodea.val==nodeb.val)//이미 같은 값이면 출력해
				pw.print(nodea.val+"\n");
			else {
				while(nodea.sup!=nodeb.sup) {
					nodea=tree.get(tree.get(nodea.val).sup);
					nodeb=tree.get(tree.get(nodeb.val).sup);
				}
				pw.print(nodea.sup+"\n");
			}
		}
		pw.flush();
		pw.close();
		br.close();
	}
}