import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ_2887_행성터널 {
	static class Node implements Comparable<Node>{
		int pos;
		int idx;
		public Node(int pos, int idx) {
			this.pos = pos;
			this.idx = idx;
		}
		public Node() {}
		@Override
		public int compareTo(Node o) {
			return this.pos-o.pos;
		}
		@Override
		public String toString() {
			return "Node [pos=" + pos + ", idx=" + idx + "]";
		}		
		
	}
	
	static class Edge implements Comparable<Edge>{
		int w;
		int a,b;
		public Edge() {}
		public Edge(int w, int a, int b) {
			this.w = w;
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(Edge o) {			
			return this.w-o.w;
		}
		@Override
		public String toString() {
			return "Edge [w=" + w + ", a=" + a + ", b=" + b + "]";
		}	
		
	}
	
	static int[] union_find;
	
	static int find(int n) {
		if(union_find[n]==n)
			return n;
		return union_find[n]=find(union_find[n]);
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		
		Node[] x=new Node[n];
		Node[] y=new Node[n];
		Node[] z=new Node[n];
		
		for(int i=0;i<n;i++) {
			String[] str=br.readLine().split(" ");
			x[i]=new Node(Integer.parseInt(str[0]),i);
			y[i]=new Node(Integer.parseInt(str[1]),i);
			z[i]=new Node(Integer.parseInt(str[2]),i);
		}
		
		Arrays.sort(x);
		Arrays.sort(y);
		Arrays.sort(z);
		
		ArrayList<Edge> edge=new ArrayList<>();
		for(int i=0;i<n-1;i++) {
			edge.add(new Edge(x[i+1].pos-x[i].pos,x[i].idx,x[i+1].idx));
			edge.add(new Edge(y[i+1].pos-y[i].pos,y[i].idx,y[i+1].idx));
			edge.add(new Edge(z[i+1].pos-z[i].pos,z[i].idx,z[i+1].idx));
		}
		Collections.sort(edge);
		
		int ans=0;
		union_find=new int[n];
		for(int i=0;i<n;i++) {
			union_find[i]=i;
		}
		for(int i=0;i<edge.size();i++) {
			int a=edge.get(i).a;
			int b=edge.get(i).b;
			int w=edge.get(i).w;
			
			int tmp1=find(a);
			int tmp2=find(b);
			if(tmp1!=tmp2) {
				union_find[tmp1]=tmp2;
				ans+=w;
			}
		}
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}