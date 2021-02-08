import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_20304_비밀번호제작 {
	static class Pair{
		int x;//값
		int y;//안전도
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Pair() {}
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		int m=Integer.parseInt(br.readLine());
		String[] str=br.readLine().split(" ");
		Queue<Pair> q=new LinkedList<>();
		boolean[] isvisited=new boolean[n+1];
		int ans=0;
		
		for(int i=0;i<m;i++) {
			int now=Integer.parseInt(str[i]);
			q.add(new Pair(now,0));//안전도 0인애들 일단 큐에 저장
		}
		
		//BFS
		while(!q.isEmpty()) {
			int nownum=q.peek().x;
			int nowsec=q.poll().y;
			ans=ans<nowsec?nowsec:ans;
			
			//이제 nownum이랑 비트 한개 다른애들 찾아
			for(int i=1;i<=n;i=i<<1) {
				int diffBit=nownum&i;
				int secnum;//1비트만큼 다른 수 저장할 변수
				//찾은애들은 안전도+1해서 큐에 집어넣어
				if(diffBit<=0) 
					secnum=i+nownum;
				else
					secnum=nownum-i;
				if(secnum>n||isvisited[secnum])
					continue;
				isvisited[secnum]=true;
				q.add(new Pair(secnum,nowsec+1));
				//그래서 n까지 전부 방문하면 끝
			}
		}
		pw.print(ans);
		pw.flush();
		pw.close();
		br.close();
	}
}
/*
우선 해커가 썻던 암호들중 안전도가 0이되는것부터 찾아
그리고 안전도가 1이되는것들을 찾아
그리고 ...           2가 되는것들을찾아
반복

어케 찾는가
BFS로 이미 갓던 숫자들은 못가게 하면
안전도 0인애들이랑 비트가 1다른애 찾으면 안전도 1인게 나옴
여기서 안전도 1인애들이랑 아직 방문하지 않은 애들중 비트가 1다른애 찾으면 안전도 2인게 나옴
반복
*/