import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_14502_연구소 {

	static int arrnp[];
	
	static void swap(int i, int j) {
		int tmp = arrnp[i];
		arrnp[i] = arrnp[j];
		arrnp[j] = tmp;
	}
	
	static boolean nextPermutation(int n) {
		// arr은 오름차순으로 정렬되어 들어옴
		// arr[i-1]<arr[i]이 성립하는 마지막 i를 찾아
		int i = n - 1;
		while (i > 0 && arrnp[i - 1] >= arrnp[i])
			i--;
		if (i == 0)
			return false;
		// arr[i-1]<arr[j]이 성립한느 마지막 j를 찾아
		int j = n - 1;
		while (arrnp[i - 1] >= arrnp[j])
			j--;
		// 스왑
		swap(i - 1, j);
		// i포함 뒷부분을 오름차순으로 정렬(뒤집어주면 오름차순 정렬됨)
		int k = n - 1;
		while (i < k)
			swap(i++, k--);
		return true;
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		String[] str=br.readLine().split(" ");
		int n=Integer.parseInt(str[0]);
		int m=Integer.parseInt(str[1]);
		int[][] arr=new int[n][m];
		
		ArrayList<int[]> empty=new ArrayList<>(); 
		ArrayList<int[]> virus=new ArrayList<>();
		Queue<int[]> q =new LinkedList<>();
		int[][] isvisite=new int[n][m];
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
				if(arr[i][j]==2) {
					virus.add(new int[] {i,j});
				}else if(arr[i][j]==0)
					empty.add(new int[] {i,j});
			}
		}
		int emptysize=empty.size();
		//넥스트 퍼뮤테이션으로 모든경우 다돌아
		arrnp=new int[emptysize];
		for(int i=0;i<3;i++)
			arrnp[emptysize-i-1]=1;
		int ans=0;
		do {
			for(int i=0;i<n;i++) {
				Arrays.fill(isvisite[i],0);
			}
			
			//벽 생성
			for(int i=0;i<emptysize;i++) {
				if(arrnp[i]==0)
					continue;				
				int x=empty.get(i)[0];
				int y=empty.get(i)[1];	
				isvisite[x][y]=1;
			}
			for(int i=0;i<virus.size();i++) {
				q.offer(new int[] {virus.get(i)[0],virus.get(i)[1]});
				isvisite[virus.get(i)[0]][virus.get(i)[1]]=1;
			}
			int cnt=0;
			//BFS
			while(!q.isEmpty()) {
				int x=q.peek()[0];
				int y=q.poll()[1];
				
				
				if(x+1<n && isvisite[x+1][y]==0 && arr[x+1][y]!=1) {
					q.offer(new int[] {x+1,y});
					isvisite[x+1][y]=1;
					cnt++;
				}
				if(x-1>=0 && isvisite[x-1][y]==0 && arr[x-1][y]!=1) {
					q.offer(new int[] {x-1,y});
					isvisite[x-1][y]=1;
					cnt++;
				}
				if(y+1<m && isvisite[x][y+1]==0 && arr[x][y+1]!=1) {
					q.offer(new int[] {x,y+1});
					isvisite[x][y+1]=1;
					cnt++;
				}
				if(y-1>=0 && isvisite[x][y-1]==0 && arr[x][y-1]!=1) {
					q.offer(new int[] {x,y-1});
					isvisite[x][y-1]=1;
					cnt++;
				}
			}
			
			int safezone=empty.size()-cnt-3;
			ans=ans<safezone?safezone:ans;
			
			q.clear();
			
		}while(nextPermutation(emptysize));
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}

}
/*
64C3= 41664

완탐
*/