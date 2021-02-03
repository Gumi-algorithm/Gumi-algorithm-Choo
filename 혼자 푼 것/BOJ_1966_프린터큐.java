import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1966_프린터큐 {
	static class Pair{
		int idx;
		int val;
		public Pair() {}
		public Pair(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
		
	}	
	static ArrayList<Integer> arr=new ArrayList<>();
	static Queue<Pair> q=new LinkedList<>();
	
	public static void main(String[] args)throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		int t=Integer.parseInt(br.readLine());
		int n,m;
		String[] strtmp;
		while(t-->0) {
			strtmp=br.readLine().split(" ");
			n=Integer.parseInt(strtmp[0]);
			m=Integer.parseInt(strtmp[1]);
			strtmp=br.readLine().split(" ");
			int tmp;
			arr.clear();
			q.clear();
			for(int i=0;i<n;i++) {
				tmp=Integer.parseInt(strtmp[i]);
				q.add(new Pair(i,tmp));
				arr.add(tmp);
			}
			Collections.sort(arr,(a,b)->b-a);
			int aidx=0;
			for(int i=0;!q.isEmpty();i++) {
				Pair now=q.poll();
				if(arr.get(aidx)==now.val) {
					if(now.idx==m) {
						pw.print(Integer.toString(aidx+1)+"\n");
						break;
					}
					aidx++;
				}else {
					q.add(now);
				}
				
			}
			
		}
		br.close();
		pw.close();		
	}

}
