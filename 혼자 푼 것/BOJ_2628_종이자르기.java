import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_2628_종이자르기 {

	static class Node{
		int type;
		int num;
		public Node(int type, int num) {
			super();
			this.type = type;
			this.num = num;
		}
		
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);

		int h,w;
		
		String[] str=br.readLine().split(" ");
		
		w=Integer.parseInt(str[0]);
		h=Integer.parseInt(str[1]);
		
		int n=Integer.parseInt(br.readLine());
		
		List<Node> arr=new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			int type=Integer.parseInt(str[0]);
			int num=Integer.parseInt(str[1]);
			
			arr.add(new Node(type, num));
		}
		
		int wmax=0;
		int hmax=0;
		
		Collections.sort(arr, (a,b)-> {
			return a.type==b.type?a.num-b.num:a.type-b.type;
		});
		int pretype=0;
		int prenum=0;
		for(Node node: arr) {
			if(pretype!=node.type) {
				hmax=Math.max(hmax, h-prenum);
				pretype=1;
				prenum=0;
			}
			if(node.type==0) {//wide
				int val=node.num-prenum;
				hmax=Math.max(hmax, val);
				prenum=node.num;
			}else {//height
				int val=node.num-prenum;
				wmax=Math.max(wmax, val);
				prenum=node.num;
			}
		}
		wmax=Math.max(wmax, w-prenum);
		pw.print(wmax*hmax);
		br.close();
		pw.close();
	}

}
