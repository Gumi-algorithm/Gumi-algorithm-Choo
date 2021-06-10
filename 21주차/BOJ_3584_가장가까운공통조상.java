import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

public class BOJ_3584_가장가까운공통조상 {

	static class Node{
		int p=0;//부모		
		ArrayList<Integer> c=new ArrayList<>();//자식
//		int level;//깊이
	}
	static Node[] arr;
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int t=Integer.parseInt(br.readLine());
		
		while(t-->0) {
			
			int n=Integer.parseInt(br.readLine());
			//0번은 안씀
			arr=new Node[n+1];
			
			//트리 생성
			String[] str;
			int a,b;
			for(int i=1;i<n;i++) {
				str=br.readLine().split(" ");
				a=Integer.parseInt(str[0]);
				b=Integer.parseInt(str[1]);
				
				if(arr[a]==null) 
					arr[a]=new Node();
				arr[a].c.add(b);
				
				if(arr[b]==null)
					arr[b]=new Node();
				arr[b].p=a;	
			}
			str=br.readLine().split(" ");
			a=Integer.parseInt(str[0]);
			b=Integer.parseInt(str[1]);
			
			Stack<Integer> pa=new Stack<>();
			Stack<Integer> pb=new Stack<>();
			
			
			//a의 조상들을 모두 qa에 넣어(자신포함)
			while(a!=0) {
				pa.add(a);
				a=arr[a].p;
			}
			
			//b의 조상들을 모두 qb에 넣어(자신포함)
			while(b!=0) {
				pb.add(b);
				b=arr[b].p;
			}
			int ans=0;
			
			//루트 노드부터 쭈루룩 내려오면서 부모가 달라질때까지 계속 확인해
			while(!pa.isEmpty() && !pb.isEmpty()) {
				a=pa.pop();
				b=pb.pop();
				
				if(a!=b)
					break;
				ans=a;//같으면 ans에 집어넣어
			}
			pw.println(ans);
		}
		br.close();
		pw.flush();
		pw.close();
	}
}