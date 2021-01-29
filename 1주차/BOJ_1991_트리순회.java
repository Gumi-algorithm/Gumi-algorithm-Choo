import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_1991_트리순회 {
	class Tree{
		int left;
		int right;
		int sup;
		public Tree(int left, int right) {
			this.left = left;
			this.right = right;
		}	
		public Tree(int sup) {
			this.sup=sup;
		}	

		public Tree() {

		}	

	}

	Tree[] tree=new Tree[27];





	public BOJ_1991_트리순회() throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw =new PrintWriter(System.out);

		int n= Integer.parseInt(br.readLine());



		for(int i=0;i<n;i++) {
			String[] strtmp=br.readLine().split(" ");
			int a,b,c;
			a=strtmp[0].charAt(0)-64;
			if(strtmp[1].charAt(0)!='.')
				b=strtmp[1].charAt(0)-64;
			else
				b=0;
			if(strtmp[2].charAt(0)!='.')
				c=strtmp[2].charAt(0)-64;
			else
				c=0;

			//트리생성
			if(tree[a]==null)
				tree[a]=new Tree(b,c);
			else {
				tree[a].left=b;
				tree[a].right=c;
			}

			if(tree[b]==null) {
				tree[b]=new Tree(a);
			}else
				tree[b].sup=a;

			if(tree[c]==null) {
				tree[c]=new Tree(a);
			}else
				tree[c].sup=a;
		}
		tree[0]=null;

		//순회
		//전위순회
		int idx=1;
		int stk[]= new int[50];
		int stkIdx=0;
		while(true) {
			if(idx==1 && stk[stkIdx]==2) {
				stk[stkIdx]=0;
				break;
			}
			if(stk[stkIdx]==0) {
				//출력
				pw.printf("%c",64+idx);
				//왼쪽 자식
				if(tree[idx].left!=0) {
					idx=tree[idx].left;
					stkIdx++;
				}else 
					stk[stkIdx]++;

				continue;				
			}else if(stk[stkIdx]== 1) {
				//오른쪽 자식
				if(tree[idx].right!=0) {
					idx=tree[idx].right;
					stkIdx++;
				}else
					stk[stkIdx]++;
				continue;
			}else if(stk[stkIdx]==2) {
				//탈출
				idx=tree[idx].sup;
				stk[stkIdx--]=0;
				stk[stkIdx]++;
				continue;
			}
		}
		pw.println();

		//중위순회
		while(true) {
			if(idx==1 && stk[stkIdx]==2) {
				stk[stkIdx]=0;
				break;
			}
			if(stk[stkIdx]==0) {
				//왼쪽 자식
				if(tree[idx].left!=0) {
					idx=tree[idx].left;
					stkIdx++;
				}else 
					stk[stkIdx]++;

				continue;				
			}else if(stk[stkIdx]== 1) {
				//출력
				pw.printf("%c",64+idx);
				//오른쪽 자식
				if(tree[idx].right!=0) {
					idx=tree[idx].right;
					stkIdx++;
				}else
					stk[stkIdx]++;
				continue;
			}else if(stk[stkIdx]==2) {
				//탈출
				idx=tree[idx].sup;
				stk[stkIdx--]=0;
				stk[stkIdx]++;
				continue;
			}
		}
		pw.println();

		//후위순회
		while(true) {
			if(idx==1 && stk[stkIdx]==2) {
				//출력
				pw.printf("%c",64+idx);
				stk[stkIdx]=0;
				break;
			}
			if(stk[stkIdx]==0) {
				//왼쪽 자식
				if(tree[idx].left!=0) {
					idx=tree[idx].left;
					stkIdx++;
				}else 
					stk[stkIdx]++;

				continue;				
			}else if(stk[stkIdx]== 1) {
				//오른쪽 자식
				if(tree[idx].right!=0) {
					idx=tree[idx].right;
					stkIdx++;
				}else
					stk[stkIdx]++;
				continue;
			}else if(stk[stkIdx]==2) {
				//출력
				pw.printf("%c",64+idx);
				//탈출
				idx=tree[idx].sup;
				stk[stkIdx--]=0;
				stk[stkIdx]++;
				continue;
			}
		}
		pw.println();

		br.close();
		pw.close();
	}
	public static void main(String[] args) throws IOException{
		new BOJ_1991_트리순회();
	}
}