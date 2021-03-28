import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_12869_뮤탈리스크 {
	static int[][][] mem=new int[61][61][61];
	
	static int dp(int a,int b,int c) {
		if(a<0) a=0;
		if(b<0) b=0;
		if(c<0) c=0;
		if(a==0 && b==0 && c==0)
			return 0;
		if(mem[a][b][c]!=0)
			return mem[a][b][c];
		int ret=2100000000;
		
		ret=Math.min(ret, dp(a-9,b-3,c-1));
		ret=Math.min(ret, dp(a-9,b-1,c-3));
		ret=Math.min(ret, dp(a-3,b-9,c-1));
		ret=Math.min(ret, dp(a-3,b-1,c-9));
		ret=Math.min(ret, dp(a-1,b-9,c-3));
		ret=Math.min(ret, dp(a-1,b-3,c-9));
		
		mem[a][b][c]=ret+1;
		return ret+1;
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		int[] arr={0,0,0};
		String[] str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		
		int ans=dp(arr[0],arr[1],arr[2]);
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}

}
/*
처음엔 가장 높은체력의 scv에게 가장 높은 데미지를 
 다음 체력에겐 다음 데미지
 가장 낮은 체력에겐 가장 낮은 데미지를 주면 된다 생각함
 근데
 12 10 4는
 12에게 9데미지, 10에게 1데미지, 4에게 3데미지를 줘야 2번만에 끝난다
 
 이걸 어떻게 생각하지 고민했는데
 12가 선택할수 있는 경우의 수가 12-9, 12-3, 12-1이다 그리고 나머지도 마찬가지로 3가지
 계속 늘려가면 처음엔 9가지 그다음에 27가지 그다음에 81가지..... 엄청 커진다 (잘못된 방법)
 
 그런데 생각해보면 12를 2번만에 죽이려면 9 3이러게 데미지를 줘야하는데 
 사실 9 3 데미지를 주든 3 9 데미지를 주던 상관없다 그냥 몇번만에 죽는지가 중요
 그래서 생각함
 hp1은 1의 데미지 1번 주면됨
 hp2는 1의 데미지 2번
 hp3는 3의 데미지 1번
 hp4는 1의 데미지 1번, 3의데미지 1번
 hp5는 1의 데미지 2번, 3의데미지 1번
 hp6는 3의 데미지 2번
 결국 0-n 냅색문제랑 같다 
 근데 여기서 조건 1개더 있음 
 한턴에 9 3 1 이렇게 나누어져야함
 9 9 9 불가능
 54 54 54이런게 주어지면 못품(잘못된 방법)
 
 근데 
 12는 9 3 데미지를 주든 3 9 데미지를 주던 상관없다 그냥 몇번만에 죽는지가 중요
 이건 맞는거 같음
 
 도무지 안떠올라서 풀이 찾아봄
 해당 문제는 세마리의 scv가 같이 있는게 요점임
 그래서 위에서 0-n냅색처럼 하나하나가 다른 scv에 영향을 안주는것처럼 풀면 안댐
 3개가 세트임
 그래서
 12 10 4가 주어지면
 mem[12][10][4]일때 횟수를 저장해야됨
 그리고 모든 경우의수를 돌려보면 메모이제이션때매 시간초과 안나 
 */
