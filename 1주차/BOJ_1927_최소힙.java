import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1927_최소힙 {
	static int[] tree=new int[200010];
	static int pqIdx;//다음으로 들어갈곳 가르키는 인덱스
	
	static void swap(int idx1, int idx2) {
		int tmp=tree[idx1];
		tree[idx1]=tree[idx2];
		tree[idx2]=tmp;
	}
	
	//최소힙, 작은애가 젤 위로
	static void push(int num) {
		int idx=pqIdx++;
		tree[idx]=num;
		
		while(idx>1) {
			//작은게 젤 위로가게 교환
			if(tree[idx]<tree[idx/2]) {//자식이 부모보다 작은경우 교환
				swap(idx, idx/2);
				idx=idx/2;
			}else//자식이 부모보다 작지않은경우 힙이 유지되니 탈출
				break;
		}
	}

	static int pop() {
		//비어잇으면 0리턴
		if(pqIdx==1)
			return 0;
		
		int num=tree[1];
		pqIdx--;
		
		int idx=1;
		//마지막애가 젤 위로가게
		tree[1]=tree[pqIdx];
		tree[pqIdx]=0;//그리고 삭제
		
		//삭제후 1개밖에 없으면 조정 필요없음
		if(pqIdx==1)
			return num;
		
		//다시 자리 조정
		while(idx<pqIdx) {
			//자식 둘중 더 작은거 넣어야됨
			//0인곳은 없는노드임
			if(tree[idx*2]!=0 && tree[idx*2+1]!=0 && tree[idx*2]<tree[idx*2+1] && tree[idx]>tree[idx*2]) {
				swap(idx,idx*2);
				idx=idx*2;
			}else if(tree[idx*2]!=0 && tree[idx*2+1]!=0 && tree[idx*2]>=tree[idx*2+1] && tree[idx]>=tree[idx*2+1]) {//같은경우도 생각해야됨
				swap(idx,idx*2+1);
				idx=idx*2+1;
			}else if(tree[idx*2]!=0 && tree[idx]>tree[idx*2]) {//자식 하나만 잇는경우에 걸릴듯
				swap(idx,idx*2);
				idx=idx*2;
			}else if(tree[idx*2+1]!=0 && tree[idx]>tree[idx*2+1]) {
				swap(idx,idx*2+1);
				idx=idx*2+1;
			}else
				break;
		}
		return num;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		int num;
		pqIdx=1;
		for(int i=0;i<n;i++) {
			num=Integer.parseInt(br.readLine());
			if(num==-1)
				System.out.println(tree[pqIdx-1]);
			else if(num==0) {
				pw.print(Integer.toString(pop())+"\n");
			}else
				push(num);
			pw.flush();
		}
		br.close();
		pw.close();
	}
}