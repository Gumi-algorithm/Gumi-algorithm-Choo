import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BOJ_6416_트리인가 {
	
	private static class Node{
		int a,b;		
	}
	

	//3번 조건 확인하기 위함
	static int[] unionfind;	
	
	static int find(int num) {
		if(unionfind[num]==num)
			return num;
		return find(unionfind[num]);
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
//1.들어오는 간선이 하나도 없는 단 하나의 노드가 존재한다. 이를 루트(root) 노드라고 부른다.
//2.루트 노드를 제외한 모든 노드는 반드시 단 하나의 들어오는 간선이 존재한다.
//3.루트에서 다른 노드로 가는 경로는 반드시 가능하며, 유일하다. 이는 루트를 제외한 모든 노드에 성립해야 한다.
		
		List<Node> input=new ArrayList<>();
		
		boolean isend=false;
		boolean answer=true;
		int tidx=1;
		int max=0;
		while(true) {
			boolean iszero=false;
			
			String[] str=br.readLine().split("  ");
			for(String s : str) {
				if("".equals(s))
					break;
				String[] stmp=s.split(" ");
				Node tmp=new Node();
				
				tmp.a=Integer.parseInt(stmp[0]);
				tmp.b=Integer.parseInt(stmp[1]);
				if(tmp.a==0) {
					iszero=true;
					break;
				}
				if(tmp.a==-1) {
					isend=true;
					break;						
				}
				max=Math.max(max, tmp.a);
				max=Math.max(max, tmp.b);
				
				input.add(tmp);
			}
			if(isend)
				break;
		
			if(iszero) {
				
				int[] arr=new int[max+1];
				
				
				//2.루트 노드를 제외한 모든 노드는 반드시 단 하나의 들어오는 간선이 존재한다.
				for(Node tmp: input) {
					arr[tmp.b]++;
					if(arr[tmp.b]>1) {
						answer=false;
						break;
					}
				}
				
				//1.들어오는 간선이 하나도 없는 단 하나의 노드가 존재한다. 이를 루트(root) 노드라고 부른다.
				//3.루트에서 다른 노드로 가는 경로는 반드시 가능하며, 유일하다. 이는 루트를 제외한 모든 노드에 성립해야 한다.
				//유니온파인드
				unionfind=new int[max+1];
				
				//자기자신 가르킴
				for(Node tmp: input) {
					unionfind[tmp.a]=tmp.a;
					unionfind[tmp.b]=tmp.b;	
				}
				
				//유니온
				for(Node tmp: input) {	
					int pa=find(tmp.a);
					int pb=find(tmp.b);
					if(pa == pb) {
						//사이클이 생기는 경우
						answer=false;
						break;
					}
					unionfind[pb]=pa;
				}
				
				//한번 더 사이클 돌려서 모두가 루트를 가르키게 함
				for(Node tmp: input) {
					unionfind[tmp.a]=find(tmp.a);
					unionfind[tmp.b]=find(tmp.b);			
				}
				
				int root=-1;
				for(Node tmp:input) {
					if(root==-1)
						root=unionfind[tmp.a];
					if(unionfind[tmp.a]!=root || unionfind[tmp.b]!=root) {
						answer=false;
						break;
					}
				}
				
				if(answer==false) {
					pw.println("Case "+tidx+" is not a tree.");
					tidx++;
				}else {
					pw.println("Case "+tidx+" is a tree.");
					tidx++;
				}
				
				max=0;
				input.clear();
				answer=true;
			}	
		}		
		br.close();
		pw.close();
	}
}