import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_14888_연산자끼워넣기 {

	static int[] op=new int[4];
	static int [] arr;
	static int bigans=Integer.MIN_VALUE;
	static int smallans=Integer.MAX_VALUE;
	static int n;
	
	//완탐 
	static void func(int idx, int num) {
		if(idx==n) {
			bigans=bigans<num?num:bigans;
			smallans=smallans>num?num:smallans;
			return;
		}
		//연산자 고르는거
		for(int i=0;i<4;i++) {
			if(op[i]==0)
				continue;
			int tmp=0;
			
			if(i==0)//덧셈
				tmp=num+arr[idx];
			else if(i==1)//뺄셈
				tmp=num-arr[idx];
			else if(i==2)//곱셈
				tmp=num*arr[idx];
			else if(i==3)//나눗셈
				tmp=num/arr[idx];
			
			op[i]--;
			func(idx+1,tmp);
			op[i]++;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		n= Integer.parseInt(br.readLine());
		
		String[] str=br.readLine().split(" ");
		arr=new int[n];
		
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		str=br.readLine().split(" ");
		
		op[0]=Integer.parseInt(str[0]);
		op[1]=Integer.parseInt(str[1]);
		op[2]=Integer.parseInt(str[2]);
		op[3]=Integer.parseInt(str[3]);
		
		func(1,arr[0]);
		
		pw.print(bigans+"\n");
		pw.print(smallans);		
		pw.flush();
		pw.close();
		br.close();
	}
}

/*
왜 백트래킹인지 모르겟음 그냥 완탐하니까 풀림
n이 최대 11이니까
연산자의 갯수는 처음 숫자는 11개를 고를수잇고 마지막 숫자는 남은 1개를 고름
그래서 연산횟수는 11*10*......*2*1=39,916,800 이 됨
*/
