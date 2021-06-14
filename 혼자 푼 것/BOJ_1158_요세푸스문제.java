import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class MainQueue {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		Queue<Integer> q=new LinkedList<>();
		String[] str=br.readLine().split(" ");
		int n,k;
		n=Integer.parseInt(str[0]);
		k=Integer.parseInt(str[1]);

		for(int i=0;i<n;i++)
			q.offer((k+i-1)%n+1);
		int cnt=1;
		pw.print("<");
		while(!(n==cnt)) {
			
			pw.print(q.poll()+", ");
			for(int i=1;i<k;i++)
				q.offer(q.poll());

			cnt++;
		}
		pw.print(q.poll()+">");
		
		pw.flush();
		pw.close();
		br.close();	
	}
}
