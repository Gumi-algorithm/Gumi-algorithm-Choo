import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BOJ_3190_뱀 {

	static class Node{
		int x, y;


		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int hashCode() {
			int hashcode=1;
			hashcode= 31*hashcode + x;
			hashcode= 31*hashcode + y;			
			return hashcode;
		}
		@Override
		public boolean equals(Object obj) {
			Node n=(Node)obj;
			if(this.x==n.x && this.y==n.y)
				return true;
			else
				return false;
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		
		
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		int k=Integer.parseInt(br.readLine());
		
		int arr[][]=new int[n][n];
		
		
		String[] str;
		for(int i=0;i<k;i++) {
			str=br.readLine().split(" ");
			int a=Integer.parseInt(str[0])-1;
			int b=Integer.parseInt(str[1])-1;	
			arr[a][b]=1;
		}
		int l=Integer.parseInt(br.readLine());
		
		//몇초에 방향 바꾸는지 확인하는 맵
		Map<Integer, Integer> move= new HashMap<Integer, Integer>();
		
		for(int i=0;i<l;i++) {
			str=br.readLine().split(" ");
			int a=Integer.parseInt(str[0]);
			if(str[1].equals("L"))
				move.put(a, 0);//왼쪽
			else
				move.put(a, 1);//오른쪽
		}
		
		int[][] direction= {{-1,0},{0,1},{1,0},{0,-1}};
		
		//시작
		Set<Node> sbody=new HashSet<>();//움직인 자리에 자기 몸이 있는지 확인하기 위한 셋
		Queue<Node> snake=new LinkedList<>();//한칸씩 움직이기 위한 큐		
		int hx=0,hy=0;//뱀은 처음 맨위 맨좌측에 위치(머리위치)
		snake.offer(new Node(hx,hy));
		sbody.add(new Node(hx,hy));
		int len=1;//뱀의 첫 길이는 1;
		int dir=1;//처음엔 오른쪽으로 향함(0:위, 1:오른, 2:아래, 3:왼)
		int time=0;
		while(true) {
			//시간증가
			time++;	
			
			//먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
//			if(time!=0) {
				hx+=direction[dir][0];
				hy+=direction[dir][1];
//			}
			Node node=new Node(hx,hy);
			//이걸 꼬리가 없어지기 전에 해야되는지 없어지고 나서 해야하는지 모르겟다(머리부터 움직였을때 꼬리있으면 죽은거임)
			//벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
			if(sbody.contains(node) || (hx>=n || hx<0 || hy>=n || hy<0 )) {
				break;
			}
			
			snake.offer(node);
			sbody.add(node);
			
			//만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
			if(arr[hx][hy]==1) {
				arr[hx][hy]=0;
			}else {//만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
				sbody.remove(snake.peek());
				snake.poll();
			}
			
			
			//게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다.
			if(move.containsKey(time)) {
				int next=move.get(time);
				if(next==0) {//왼쪽
					dir=(dir-1+4)%4;
				}else//오른쪽
					dir=(dir+1)%4;
			}
			
					
		}
		
		pw.print(time);
		br.close();
		pw.flush();
		pw.close();
	}

}
