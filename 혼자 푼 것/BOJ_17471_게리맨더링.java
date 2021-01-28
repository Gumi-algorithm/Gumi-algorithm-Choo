import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BOJ_17471_게리맨더링 {

	static class Tree{
		int val;
		ArrayList<Integer> edge=new ArrayList<>();
	}

	static int citiNum;
	static ArrayList<Tree> arr=new ArrayList<>();
	static int[] isSelect;
	static int[] isvisited;
	static int allCitizen;

	static public int dfs(int n) {
		int num=1;
		if(isvisited[n]==1)
			return 0;

		isvisited[n]=1;
		ArrayList<Integer> edge=arr.get(n).edge;

		for(int i:edge) {
			num+=dfs(i);
		}		

		return num;
	}

	static public int geri(int idx) {
		//idx선거구를 고른경우 안고른경우 다해보자

		int ret=1000000;

		if(idx==citiNum+1) {
			//다 골랏으면 고른 선거구, 안고른 선거구가 각각 연결되는지 확인해
			int start=0;
			int a=0;//고른곳 갯수
			int b=0;//안고른곳 갯수

			//고른곳 부터 확인
			for(int i=1;i<=citiNum;i++) {
				if(isSelect[i]==1) {
					isvisited[i]=0;
					start=i;
					a++;
				}
				else {
					isvisited[i]=1;
					b++;
				}
			}
			//한쪽으로 다쏠렷으면 리턴
			if(a==0 || b==0)
				return ret;
			
			if(dfs(start)==a) {//연결된 선거구 갯수와 고른곳이 같은가
				//고른곳이 전부 연결됫으면 안고른곳도 확인해
				//안고른곳 확인
				for(int i=1;i<=citiNum;i++) {
					if(isSelect[i]==1) {
						isvisited[i]=1;
					}
					else {
						isvisited[i]=0;
						start=i;
					}
				}
				if(dfs(start)==b) {
					//둘다 연결 됬으면 시민수 차이 구해
					int citiA=0;//고른곳
					int citiB=0;//안고른곳
					for(int i=1;i<=citiNum;i++) {
						if(isSelect[i]==1) {
							citiA+=arr.get(i).val;
						}else
							citiB+=arr.get(i).val;
					}
					int dif=citiA-citiB;
					dif=dif<0?dif*-1:dif;
					ret=ret>dif?dif:ret;
					
				}				
			}
			return ret;
		}
		
		//idx를 고른경우
		isSelect[idx]=1;
		ret=geri(idx+1);

		isSelect[idx]=0;
		int tmp=geri(idx+1);

		ret=ret>tmp?tmp:ret;

		return ret;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		citiNum=Integer.parseInt(br.readLine());
		isSelect=new int[citiNum+1];
		isvisited=new int[citiNum+1];

		//인덱스0 안쓸거임 도시인덱스 1부터임
		arr.add(null);

		//시민정보 삽입
		String[] str = br.readLine().split(" ");
		for(int i=1;i<=citiNum;i++) {
			Tree tmp=new Tree();
			tmp.val=Integer.parseInt(str[i-1]);
			arr.add(tmp);
			allCitizen+=tmp.val;
		}

		//에지정보 삽입
		for(int i=1;i<=citiNum;i++) {
			String[] strtmp = br.readLine().split(" ");
			int m=Integer.parseInt(strtmp[0]);
			for(int j=0;j<m;j++) {
				arr.get(i).edge.add(Integer.parseInt(strtmp[j+1]));
			}
		}

		//모든경우 다 해보자
		int ret=geri(1);
		if(ret==1000000)
			ret=-1;
		
		bw.write(Integer.toString(ret));
		
		br.close();
		bw.close();
	}
}
