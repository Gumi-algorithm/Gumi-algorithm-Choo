import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_1755_숫자놀이 {

	//미리 각 숫자를 문자열로 변환시켜놓음
	static String[] number= {"zero","one","two","three","four","five","six","seven","eight","nine"};
	
	static class Node implements Comparable<Node>{
		String str;//일었을때 문자열
		int num;//숫자
		
		//생성자
		public Node(String str, int num) {
			super();
			this.str = str;
			this.num = num;
		}
		
		@Override
		public String toString() {//디버깅을 위한 toString
			return "Node [str=" + str + ", num=" + num + "]";
		}
		
		@Override
		public int compareTo(Node o) {//sort하기위해 comparTo를 이용해 문자열 비교
			return this.str.compareTo(o.str);
		}			
	}
	
	//숫자를 받아와 읽었을때 문자열로 반환해줌
	static String getnumstr(int num) {
		String str="";//빈문자열 초기화
		int stk[]=new int[5];//인풋숫자가 최대 99니까 3이면 되는데 그냥 안전하게 5로 만듬
		int idx=0;
		while(num>0) {//num을 1의자리부터 한개씩 떼서 스택에 담음
			stk[idx++]=num%10;//스택에 1의자리를 담음
			num=num/10;//1의 자리를 없앰
		}
		//스택에 담겻으니 뒤에서부터 반복문 돌림
		for(int i=idx-1;i>=0;i--) {
			str+=(number[stk[i]]+" ");//미리 선언해놓음 number배열을 이용해 숫자를 문자열로 변환
		}		
		return str;//변환 완료된거 리턴
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		
		String[] str=br.readLine().split(" ");
		
		//m이상 n이하의 정수를 읽었을때 사전순으로 출력하라
		//그러니까 m<n임
		int m=Integer.parseInt(str[0]);
		int n=Integer.parseInt(str[1]);
		
		
		//m이상 n이하이기때문에 n,m둘다 포함되어야한다 그래서 큰값-작은값 +1을 해준다
		Node arr[]=new Node[n-m+1];
		
		//m부터 n까지의 숫자들을 배열에 숫자와 읽었을때 문자열로 집어넣음
		for(int i=m;i<=n;i++) {
			//i를 0이아닌 m부터햇기때문에 i-m해줌
			arr[i-m]=new Node(getnumstr(i),i);//특이하게 11인경우 일레븐이 아니라 one one임 그래서 함수로 변환시켜줌
		}
		
		//문자열을 기준으로 정렬해서 문제의 조건에 성립하게 해줌
		Arrays.sort(arr);
		
		int idx=0;//10번마다 개행해주기위해 변수만듬
		for(Node i:arr) {//문자열 기준으로 정렬된 배열의 숫자를 출력
			sb.append(i.num).append(" ");
			idx++;
			if(idx==10) {//10개 출력했으면 개행
				sb.append("\n");
				idx=0;
			}
		}
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}


