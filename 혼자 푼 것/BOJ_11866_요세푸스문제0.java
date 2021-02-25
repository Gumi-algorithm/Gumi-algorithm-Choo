import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_11866_요세푸스문제0 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		String[] str=br.readLine().split(" ");
		int n=Integer.parseInt(str[0]);
		int k=Integer.parseInt(str[1]);
		Queue<Integer> q=new LinkedList<>();
		
		for(int i=0;i<n;i++) {
			q.offer(i+1);
		}
		sb.append("<");
		int idx=1;
		while(!q.isEmpty()) {
			if(idx%k==0)
				sb.append(q.poll()).append(", ");
			else
				q.offer(q.poll());
			idx++;
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(">");
		pw.print(sb);
		pw.flush();
		br.close();
		pw.close();
	}
}