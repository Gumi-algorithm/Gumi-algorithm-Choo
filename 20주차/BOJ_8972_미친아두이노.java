import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_8972_미친아두이노 {

	static class Node{
		int x,y;
		int t;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public Node(int x, int y,int t) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;			
		}
		
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int r,c;
		
		String[] str=br.readLine().split(" ");
		r=Integer.parseInt(str[0]);
		c=Integer.parseInt(str[1]);
		
		int[][] arr=new int[r][c];
		Queue<Node> mad=new LinkedList<>();
		Node arduino=null;
		
		for(int i=0;i<r;i++) {
			str=br.readLine().split("");
			for(int j=0;j<c;j++) {
				if(str[j].equals(".")) {
					arr[i][j]=0;
				}else if(str[j].equals("I")) {
					arduino=new Node(i,j);
					arr[i][j]=0;
				}else if(str[j].equals("R")) {
					mad.add(new Node(i,j,0));
					arr[i][j]=1;
				}
			}
		}

		//종수의 아두이노가 먼저 이동, 그리고 미친 아두이노 이동
		
		int[][] dir= {{},{1,-1},{1,0},{1,1},{0,-1},{0,0},{0,1},{-1,-1},{-1,0},{-1,1}};
		str=br.readLine().split("");
		int idx=0;
		int dead=0;
		while(idx<str.length) {
			int now=Integer.parseInt(str[idx]);
			
			//종수의 아두이노 먼저 이동
			arduino.x+=dir[now][0];
			arduino.y+=dir[now][1];
			
			//이동한 위치에 미친 아두이도가 있으면 게임끝
			if(arr[arduino.x][arduino.y]!=0) {
				dead=idx+1;//면번째 움직임에 죽는지
				break;
			}
			
			//미친 아두이도 이동
			while(!mad.isEmpty()) {
				Node cur=mad.peek();
				
				//미친 아두이노의 번호가 현재 시간에 맞지않으면 탈출
				if(cur.t>idx)
					break;
				mad.poll();
				
				
				arr[cur.x][cur.y]--;
				
				//아두이노와 미친아두이노 위치차이를 이용해 어디로 이동할지 결정
				int nx=arduino.x-cur.x==0?0:(arduino.x-cur.x>0?1:-1);
				int ny=arduino.y-cur.y==0?0:(arduino.y-cur.y>0?1:-1);
				
				//이동한 위치에 종수의 아두이노 있으면 게임끝
				if(arduino.x==cur.x+nx && arduino.y == cur.y+ny) {
					dead=idx+1;
					break;
				}
				
				mad.add(new Node(cur.x+nx, cur.y+ny, cur.t+1));
				arr[cur.x+nx][cur.y+ny]++;
			}
			
			if(dead!=0){
				break;
			}
			
			//미친 아두이노가 이동후 한곳에 여러개의 아두이도가 있을경우 폭파시켜야함
			Queue<Node> boom=new LinkedList<>();
			
			int size=mad.size();
			for(int i=0;i<size;i++) {
				Node cur=mad.poll();
				//맵상에 한곳에 아두이노가 한대이상 있으면 폭파한거니까 큐에 넣으면 안됨
				if(arr[cur.x][cur.y]==1) {
					mad.add(cur);
				}else {
					boom.add(cur);//폭발하는 애들은 따로 모아둿다가 맵 정보 업데이트해줄때 씀
				}
			}
			
			//폭파한애들 맵에서 지워줌
			while(!boom.isEmpty()) {
				Node cur=boom.poll();
				arr[cur.x][cur.y]=0;
			}			
			idx++;
		}
		
		if(dead!=0) {
			pw.print("kraj "+dead);
		}else {
			//종수의 아두이노 위치 맵에 표시
			arr[arduino.x][arduino.y]=-1;
			
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<r;i++) {
				for(int j=0;j<c;j++) {
					if(arr[i][j]==0)
						sb.append('.');
					else if(arr[i][j]==-1)
						sb.append('I');
					else if(arr[i][j]==1)
						sb.append('R');
					
				}
				sb.append("\n");
			}
			pw.print(sb);
		}
		
		
		br.close();
		pw.flush();
		pw.close();
	}

}
