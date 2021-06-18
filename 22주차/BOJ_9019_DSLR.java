import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_9019_DSLR {

	static class Node {
		int num;
		StringBuilder str=new StringBuilder();

		public Node(int num) {
			super();
			this.num = num;
		}

		public Node(int num, String str, String add) {
			super();
			this.num = num;
			this.str.append(str).append(add);
		}
	}

	static String[] itos(int num) {
		String[] ret = new String[5];
		for (int i = 0; i < 5; i++)
			ret[i] = "0";

		int idx = 4;
		while (num != 0) {
			ret[idx] = Integer.toString(num % 10);
			num = num / 10;
			idx--;
		}
		return ret;
	}

	static int stoi(String[] str) {
		int ret = 0;
		for (int i = 0; i < 5; i++) {
			
			ret *= 10;
			if(!str[i].equals("")) {
				ret += Integer.parseInt(str[i]);
			}
		}
		return ret;
	}

	// n*2
	static int d(int n) {
		return (n * 2) % 10000;
	}

	// n-1
	static int s(int n) {
		return (n + 9999) % 10000;
	}

	static int l(int n) {
		int ret = 0;
		String[] num = itos(n);// 문자열로 변환
		String next =num[1];

		// 시프트(한칸씩 왼쪽으로)
		for (int i = 1; i < 4; i++) {
			num[i] = num[i + 1];
		}
		num[4] = next;

		// 인트형으로 변환
		ret = stoi(num);
		return ret;
	}

	static int r(int n) {
		int ret = 0;
		String[] num = itos(n);// 문자열로 변환
		String next =num[0];
		String tmp;

		
		// 시프트(한칸씩 오른쪽으로)
		for (int i = 0; i < 4; i++) {
			tmp = num[i + 1];
			num[i + 1] = next;
			next = tmp;
		}
		num[1] = next;

		// 인트형으로 변환
		ret = stoi(num);
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			String ans = "";

			int[] isvisited = new int[10001];
			isvisited[a] = 1;
			Queue<Node> q = new LinkedList<Node>();
			q.add(new Node(a));
			while (!q.isEmpty()) {
				Node cur = q.poll();

				int dnum = d(cur.num);
				if (isvisited[dnum] != 1) {
					q.add(new Node(dnum, cur.str.toString(),"D"));
					isvisited[dnum] = 1;
					if (dnum == b) {
						ans = cur.str.append("D").toString();
						break;
					}
				}

				int snum = s(cur.num);
				if (isvisited[snum] != 1) {
					q.add(new Node(snum, cur.str.toString(),"S"));
					isvisited[snum] = 1;
					if (snum == b) {
						ans = cur.str.append("S").toString();
						break;
					}
				}

				int lnum = l(cur.num);
				if (isvisited[lnum] != 1) {
					q.add(new Node(lnum, cur.str.toString(),"L"));
					isvisited[lnum] = 1;
					if (lnum == b) {
						ans = cur.str.append("L").toString();
						break;
					}
				}

				int rnum = r(cur.num);
				if (isvisited[rnum] != 1) {
					q.add(new Node(rnum,  cur.str.toString(),"R"));
					isvisited[rnum] = 1;
					if (rnum == b) {
						ans = cur.str.append("R").toString();
						break;
					}
				}
			}
			pw.println(ans);
		}

		br.close();
		pw.flush();
		pw.close();
	}
}