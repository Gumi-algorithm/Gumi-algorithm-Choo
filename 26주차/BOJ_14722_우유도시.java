import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_14722_우유도시 {

	static int n;
	static int[][] arr;
	static int[][][] mem;
	
	static int dfs(int x,int y,int milk) {//x,y 좌표 , milk 현재 자기가 먹어야 할 우유
		if(x>=n || y>=n)
			return 0;
		if(mem[x][y][milk]!=0)
			return mem[x][y][milk];
		
		int nm=milk;
		int ret=0;
		if(arr[x][y]==milk) {//현재 자기가 먹어야할 우유랑 같을경우 카운트 증가시키고 다음우유로 바꿔
			nm= (nm+1)%3;
			ret++;
		}
		
		int tmp1=dfs(x+1,y,nm);
		
		int tmp2= dfs(x,y+1,nm);
		
		ret+=Math.max(tmp1, tmp2);
		mem[x][y][milk]=ret;
		
		return ret;
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		n=Integer.parseInt(br.readLine());
		
		arr=new int[n][n];
		mem=new int[n][n][3];
		
		//초기화
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				mem[i][j][0]=-1;
//				mem[i][j][1]=-1;	
//			}
//		}
		
		for(int i=0;i<n;i++) {
			String[] str=br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}
		}
		
		int ans=dfs(0,0,0);
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}
/*
3
0 1 2
0 1 2
0 1 2
답 3

처음엔 그냥 mem[x][y]만 체크햇더니 위의 테스트케이스에서 틀린답 나옴

2번째에는 mem[x][y][dir] 3번째인덱스를 오른쪽 아래쪽 방향으로 둿더니 시간초과뜸

그래서 우유 종류에 따라 결국 같은 결과니까 mem[x][y][milk]로 했음
*/