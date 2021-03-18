import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_1197_최소스패닝트리 {
	static int[] union_find;
	
	static int find(int num) {
		if(num==union_find[num])
			return num;
		return union_find[num]=find(union_find[num]);
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		int v,e;
		String[] str=br.readLine().split(" ");
		v=Integer.parseInt(str[0]);
		e=Integer.parseInt(str[1]);
		ArrayList<int[]> arr=new ArrayList<>();
		
	
		
		for(int i=0;i<e;i++) {
			str=br.readLine().split(" ");
			int a=Integer.parseInt(str[0]);
			int b=Integer.parseInt(str[1]);
			int c=Integer.parseInt(str[2]);
			
			arr.add(new int[] {a,b,c});
			
		}
		
		union_find=new int[v+1];
		
		//초기화
		for(int i=0;i<v;i++) {
			union_find[i]=i;
		}
		
		//크루스칼
		//가중치 오름차순 정렬
		Collections.sort(arr,(a,b)->a[2]-b[2]);
		int totalweight=0;
		int selectnum=0;
		for(int i=0;i<e;i++) {
			int nowa=arr.get(i)[0];
			int nowb=arr.get(i)[1];
			int weight=arr.get(i)[2];
			
			//사이클이 생기는지 확인
			int pa=find(nowa);
			int pb=find(nowb);
			if(pa==pb) {
				continue;
			}else {//사이클 안생기면 그 간선 선택해
				//union
				union_find[pb]=pa;
				
				totalweight+=weight;
				selectnum++;
			}
			
			if(selectnum==(v-1))
				break;
		}
		
		pw.print(totalweight);
		br.close();
		pw.flush();
		pw.close();	
	}
}