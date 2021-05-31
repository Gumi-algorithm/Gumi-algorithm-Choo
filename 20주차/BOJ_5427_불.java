import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_5427_불 {

	static class Node {
		int x, y, time;

		public Node() {
		}

		public Node(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	static int[][] arr = new int[1000][1000];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			int w, h;

			String[] str = br.readLine().split(" ");
			w = Integer.parseInt(str[0]);
			h = Integer.parseInt(str[1]);

			Queue<Node> fire = new LinkedList<>();
			Queue<Node> humen = new LinkedList<>();
			int[][] hisvisited = new int[h][w];//사람 방문체크
			
			for (int i = 0; i < h; i++) {
				str = br.readLine().split("");
				for (int j = 0; j < w; j++) {
					if (str[j].equals("."))
						arr[i][j] = 0;
					else if (str[j].equals("#")) {
						arr[i][j] = 1;
					} else if (str[j].equals("*")) {
						fire.add(new Node(i, j, 0));
						arr[i][j] = 2;
					} else if (str[j].equals("@")) {
						humen.add(new Node(i, j, 0));
						arr[i][j] = 0;
						hisvisited[i][j]=1;
					}
				}
			}

		
			int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
			int ans=-1;
			while (!humen.isEmpty()) {
				Node cur = humen.poll();
				// 사람이 먼저 움직이고 불이 움직임
				
				while(!fire.isEmpty()) {
					Node curfire = fire.peek();
					
					// 사람이 다움직였으면 불이 움직여
					if(cur.time<=curfire.time)
						break;
					fire.poll();
					
					for(int i=0;i<4;i++) {
						int nx=curfire.x+dir[i][0];
						int ny=curfire.y+dir[i][1];
						
						if(nx<0 || nx>=h || ny<0 || ny>=w)
							continue;
						
						//벽엔 불이 붙을수 없음
						if(arr[nx][ny]!=0)
							continue;
						
						arr[nx][ny]=2;
						fire.add(new Node(nx,ny,curfire.time+1));
					}
				}
				
				// 불이 덮혔으면 안댐
				if(arr[cur.x][cur.y]==2)
					continue;
				
				for(int i=0;i<4;i++) {
					int nx=cur.x+dir[i][0];
					int ny=cur.y+dir[i][1];
					
					//탈출
					if(nx<0 || nx>=h || ny<0 || ny>=w) {
						ans=cur.time+1;
						break;
					}
					
					//벽, 불은 지날수 없음
					if(arr[nx][ny]!=0)
						continue;
					
					if(hisvisited[nx][ny]==1)
						continue;
					
					hisvisited[nx][ny]=1;
					humen.add(new Node(nx,ny,cur.time+1));
					
				}
				
				if(ans!=-1)
					break;

			}
			if(ans ==-1)
				pw.println("IMPOSSIBLE");
			else
				pw.println(ans);
		}
		br.close();
		pw.flush();
		pw.close();
	}
}