
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_1446_지름길 {
	
	static private class Node{
		int start;
		int end;
		int length;
		
		public Node(int start, int end, int length) {
			this.start = start;
			this.end = end;
			this.length = length;
		}

		@Override
		public String toString() {
			return "Node [start=" + start + ", end=" + end + ", length=" + length + "]";
		}
		
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String[] str=br.readLine().split(" ");
		int n=Integer.parseInt(str[0]);
		int d=Integer.parseInt(str[1]);
		
		List<Node> arr=new ArrayList<Node>();
		
		int[] road=new int[d+1];
		
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			arr.add(new Node(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2])));
		}
		
		Collections.sort(arr,(a,b)->a.start-b.start);
		int arridx=0;
		
		//초깃값 할당
		for(int i=0;i<=d;i++) {
			road[i]=i;
		}
		
		for(int i=0;i<=d;i++) {
			if(i==0) {
				road[i]=0;
			}else{				
				road[i]=Math.min(road[i], road[i-1]+1);
			}
			while(arridx<arr.size()) {
				int a=arr.get(arridx).start;
				int b=arr.get(arridx).end;
				int c=arr.get(arridx).length;
				if(a==i) {	
					arridx++;
					//역주행은 못함
					if(b>d) {
						continue;			
					}
					road[b]= Math.min(road[b], road[a]+c);	
				}else
					break;
			}
		}
		
		
		pw.print(road[d]);
		pw.flush();
		pw.close();
		br.close();
	}
}