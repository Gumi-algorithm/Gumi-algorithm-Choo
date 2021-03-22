import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_17132_두더지가정보섬에올라온이유 {

	static int[][] union_find;
	static int[][] arr;
	
	static int find(int num) {
		if(num==union_find[num][0])
			return num;
		
		return union_find[num][0] = find(union_find[num][0]);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		
		union_find=new int[n+1][2];//0:부모, 1:자식+자기자신 갯수 (최상위에서는 자신 유니온 갯수가 됨)
		arr=new int[n-1][3];
		
		//유니온파인드 초기화
		for(int i=0;i<=n;i++) {
			union_find[i][0]=i;
			union_find[i][1]=1;
		}
		
		//n-1개 받는다햇음
		for(int i=0;i<n-1;i++) {
			String[] str=br.readLine().split(" ");
			arr[i][0]=Integer.parseInt(str[0]);
			arr[i][1]=Integer.parseInt(str[1]);
			arr[i][2]=Integer.parseInt(str[2]);	
		}
		
		//간선의 가중치 내림차순으로 정렬
		Arrays.sort(arr,(a,b)->b[2]-a[2]);
		
		long ans=0;//50000개노드 * 50000개 노드 * 가중치200 하면 엄청 큰수임
		for(int i=0;i<n-1;i++) {
			int a=arr[i][0];
			int b=arr[i][1];
			long c=arr[i][2];
			
			//우선 a,b 유니온의 최상위 노드를 찾아
			int pa=find(a);
			int pb=find(b);
			
			//해당 노드 유니온의 노드 갯수를 서로 곱해 몇개의 연결이 생기는지 확인하고 
			//생긴 연결들은 현재 간선의 가중치의 만족도를 가짐으로
			//a유니온의 노드갯수, b유니온의 노드갯수를 곱한뒤 현재 간선의 가중치를 더한다
			ans+= (union_find[pa][1]*union_find[pb][1]*c);
			
			//그리고 pa pb를 합쳐준다(union해준다)
			//합치면서 유니온안의 노드갯수 갱신해준다
			union_find[pb][0]=pa;
			union_find[pa][1]+=union_find[pb][1];			
		}
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}

}
/*
만약 좌표 압축을 사용한다면
1 2 2
2 3 3 
인 트리가 잇으면
2의 최상위는 1 만족도 2
3의 최상위는 1 만족도 2
그래서 2-1 , 3-2-1은 만족도가 2다!! 라고 되는데 
2-3만 보면 만족도가 3임 그래서 좌표압축을 쓰면안될듯

LCA를 쓰면 안될까 라고 생각했는데 공통조상을 찾아도 찾은 경로상의 만족도를 전부 확인해야됨
그래서 LCA불가능

간선을 기준으로 생각해
간선을 가중치 내림차순으로 정렬한뒤
가중치가 큰애끼리 우선 합쳐(union)

합칠때 왼쪽유니온이 2개 오른쪽유니온이 3개 일경우
2*3만큼이 현재 합쳐지는 가중치로 연결됨(내림차순이니까 매번 연결되는 가중치가 최소임)

그래서 (왼쪽 유니온 갯수 * 오른쪽 유니온 갯수 * 현재간선 가중치)를 반복 하면 만족도를 얻을수 있음
*/