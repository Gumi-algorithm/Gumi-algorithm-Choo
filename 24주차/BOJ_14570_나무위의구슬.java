import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_14570_나무위의구슬 {

	static class Node {
		int left = 0;
		int right = 0;
		int cnt = 0; // 현재 노드의 구슬 갯수
		int lcnt = 0; // 왼쪽 서브트리 구슬갯수 누적합
		int rcnt = 0; // 오른쪽 서브트리 구슬갯수 누적합
	}

	static Node[] tree;

	static int drop(int num, long cnt) {
		
		//자식이 둘다 없는 경우
		if(tree[num].left==0 && tree[num].right==0) {
			return num;
		}
		int ret=0;
		//자식이 둘다 있는경우
		if(tree[num].left!=0 && tree[num].right!=0) {
			if(cnt%2==1) {
				ret=drop(tree[num].left,cnt/2+1);
			}else {
				ret=drop(tree[num].right,cnt/2);
			}
		}else if(tree[num].left!=0) {//왼쪽자식만 있는 경우
			ret=drop(tree[num].left,cnt);
		}else {//오른쪽 자식만 있는 경우
			ret=drop(tree[num].right,cnt);
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n = Integer.parseInt(br.readLine());

		tree = new Node[n + 1];

		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			int l = Integer.parseInt(str[0]);
			int r = Integer.parseInt(str[1]);
			tree[i+1]=new Node();
			if (l != -1)
				tree[i + 1].left = l;
			if (r != -1)
				tree[i + 1].right = r;
		}

		long k = Long.parseLong(br.readLine());
		int ans=0;
	
		// 루트는 항상 1번 노드이다.
		ans=drop(1,k);
	
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}