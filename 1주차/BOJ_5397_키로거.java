import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_5397_키로거 {

	static class Node{
		Node left,right;
		char val;
		boolean isStart;
		
		public Node() {
			left=null;
			right=null;
			val=' ';
			isStart=false;
		}
		public Node(char val) {
			left=null;
			right=null;
			this.val=val;
			isStart=false;
		}
	}
	
	static class NodeHeader{
		Node Start;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int t=Integer.parseInt(br.readLine());
		
		
		for(int i=0;i<t;i++) {
			//시작
			String str=br.readLine();
			Node nd=new Node();
			NodeHeader nh=new NodeHeader();
			nh.Start=nd;
			nd.isStart=true;
			for(int j=0;j<str.length();j++) {
				char now=str.charAt(j);
				
					if(now=='<') {
						if(nd.isStart)//시작점이면 왼쪽으로 이동못함
							continue;
						nd=nd.left;
					}else if(now=='>') {
						if(nd.right!=null)//오른쪽에 아무것도 없는게 아니면 이동해
							nd=nd.right;
					}else if(now=='-') {//삭제 
						Node pre=nd;//삭제될 노드 저장
						if(nd.isStart) {//시작점은 못지움
							continue;
						}
						nd=nd.left;//노드가 삭제될꺼니까 왼쪽으로 이동
						nd.right=pre.right;	
						if(pre.right!=null)
							pre.right.left=nd;
					}else {//비밀번호 들어온경우
						//중간에 끼워넣어
						Node next=new Node(now);
						next.left=nd;
						
						if(nd.right!=null) {
							nd.right.left=next;
						}
						next.right=nd.right;
						nd.right=next;
						
						//오른쪽에 추가됫으니 커서도 옮겨야됨
						nd=nd.right;
						
					}
			}
			
			//nd를 시작점까지 땡겨
//			while(!nd.isStart) {
//				nd=nd.left;
//			}
			nd=nh.Start;
			
			//출력
			nd=nd.right;//시작점부터니까 오른쪽으로 한번 땡기고 시작해야대
			while(nd!=null) {
				bw.write(nd.val);
				nd=nd.right;
			}
			bw.write("\n");			
		}
		bw.close();
		br.close();
		
	}

}
/*
1
hel<----
이런경우 제일 왼쪽이 존재해야됨

이거 고쳐도 50퍼에서 멈춘다
->큰값을 넣었을때 문제?

잘못된 테케
1
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-
내코드 답:aaaaaaaaaaaaaaaaaaaaaaaaa
원래 정답: 공백

1
aaaaa<<<<<>->->->->-
내코드 답:aa
원래 정답: 공백

1
12345<<<<<>->->->->-
내코드 답:24
원래 정답: 공백
*/
