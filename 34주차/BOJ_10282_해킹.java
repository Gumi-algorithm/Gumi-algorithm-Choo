import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_10282_해킹 {
	
	static class Node{
		int n, time;

		public Node(int n, int time) {
			super();
			this.n = n;
			this.time = time;
		}				
	}
	
	static List<Node>[] arr;
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);

		int t=Integer.parseInt(br.readLine());
		
		while(t-->0) {
			int n,d,c;
			String[] str=br.readLine().split(" ");
			n=Integer.parseInt(str[0]);
			d=Integer.parseInt(str[1]);
			c=Integer.parseInt(str[2]);
			
			arr=new ArrayList[n+1];
			arr[0]=new ArrayList<>();
			for(int i=0;i<d;i++) {
				str=br.readLine().split(" ");
				int a=Integer.parseInt(str[0]);
				int b=Integer.parseInt(str[1]);
				int s=Integer.parseInt(str[2]);
				if(arr[b]==null)
					arr[b]=new ArrayList<>();
				arr[b].add(new Node(a, s));
			}
			
			Queue<Integer> q=new LinkedList<>();
			q.add(c);
			q.add(0);
			int[] isvisited= new int[n+1];
			Arrays.fill(isvisited, 210000000);
			isvisited[c]=0;
			while(!q.isEmpty()) {
				int now=q.poll();
				int time=q.poll();
				
				if(arr[now]==null)
					continue;
				for(Node node : arr[now]) {
					if(node.n==0)
						continue;
					if(time+ node.time < isvisited[node.n]) {
						isvisited[node.n]=time+node.time;
						q.add(node.n);
						q.add(time+ node.time);
					}
				}
			}
			int anscnt=0;
			int anstime=0;
			for(int tmp:isvisited) {
				if(tmp!=210000000) {
					anscnt++;
					anstime = Math.max(anstime, tmp);
				}
			}
			pw.println(anscnt+" "+anstime);
		}
		
		br.close();
		pw.close();
	}
}