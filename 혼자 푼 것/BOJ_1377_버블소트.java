import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_1377_버블소트 {
	static class Pair{
		int x,y;
		
		public Pair(){}
		public Pair(int a,int b){
			x=a;//idx
			y=b;//val
		}
		public int gety() {
			return this.y;
		}
	}
	
	static Comparator<Pair> comp =new Comparator<Pair>() {
		
		@Override
		public int compare(Pair o1, Pair o2) {
			return o1.y-o2.y;
		}
	};
	
	public static void main(String[] args)throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n=Integer.parseInt(br.readLine());
		int num;
		Pair arr[]= new Pair[n];

		
		//입력
		for(int i=0;i<n;i++) {
			Pair tmp;
			tmp=new Pair(i,Integer.parseInt(br.readLine()));
			arr[i]=tmp;
		}
		
		Arrays.sort(arr,comp);
		
		//람다식 표현
//		Arrays.sort(arr, (a,b)->{return a.y-b.y;});
		
		int result=0;
		for(int i=0;i<n;i++) {
			int val=arr[i].x-i;
			result = result<val?val:result;
		}
		
		bw.write(Integer.toString(result+1));
		bw.close();
		br.close();
	}
}
/*
초점을 큰수가 뒤로 움직이는게 아닌
작은수가 앞으로 움직이는거에 집중해
10 1 5 2 3 (3)
1 10 5 2 3
1 5 10 2 3
1 5 2 10 3
1 5 2 3 10

1 2 5 3 10
1 2 3 5 10
------------
10 1 5 2 3
1 2 3 5 10

앞으로 움직인 횟수가 가장 큰 애를 고르면 전체 몇 번 뒤로 옮겻는지 파악됨

테스트 케이스
6 3 2 4 7 5 답:3
1 2 3 4 1 2 3 4 답:4
10 1 2 3 4 10 1 2 3 4 답:
*/