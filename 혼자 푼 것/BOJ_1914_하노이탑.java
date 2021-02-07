import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BOJ_1914_하노이탑 {
	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Pair() {}
	}
	static class BigInteger{
		ArrayList<Integer> val=new ArrayList<>();//0인덱스가 1의 자리임
		
		public BigInteger(long num) {
			set(num);
		}
		
		public BigInteger() {}

		public void set(long num) {
			while(num!=0) {
				val.add((int)(num%10));
				num=num/10;
			}
		}
		public void add(BigInteger b){
			int sup=0;
			int minsize=val.size()<b.val.size()?val.size():b.val.size();
			int maxsize=val.size()>b.val.size()?val.size():b.val.size();
			for(int i=0;i<minsize;i++) {
				int num=val.get(i)+b.val.get(i)+sup;
				val.set(i, num%10);
				if(num<10)
					sup=0;
				else
					sup=num/10;
			}
			if(val.size()>minsize) {
				for(int i=minsize;i<maxsize;i++) {
					if(sup==0)
						break;
					int num=val.get(i)+sup;
					val.set(i, num%10);
					if(num<10)
						sup=0;
					else
						sup=num/10;					
				}
			}else {
				for(int i=minsize;i<maxsize;i++) {

					int num=b.val.get(i)+sup;
					val.add(num%10);
					if(num<10)
						sup=0;
					else
						sup=num/10;					
				}
			}
			if(sup!=0) {
				val.add(sup);
			}
		}
	}
	static ArrayList<Pair> arr=new ArrayList<>();
	static BigInteger[] mem=new BigInteger[101];


	static int hanoi(int n,int from,int to) {

		if(n==1) {
			arr.add(new Pair(from,to));
			return 1;
		}
		int cnt=1;
		//n-1개를 2로 옮기고
		int subto=3-from-to;
		cnt+=hanoi(n-1,from, subto);
		//맨 밑에 애를 3으로 옮기고
		arr.add(new Pair(from,to));
		//다시 n-개를 2에서 3으로 옮겨
		cnt+=hanoi(n-1,subto, to);

		return cnt;
	}
	static BigInteger hanoi20(int n,int from,int to) {

		if(mem[n]!=null)
			return mem[n];

		if(n==1) {
			return new BigInteger(1);
		}
		BigInteger cnt=new BigInteger(1);
		//n-1개를 2로 옮기고
		int subto=3-from-to;
		cnt.add(hanoi20(n-1,from, subto));
		//맨 밑에 애를 3으로 옮기고
		//arr.add(new Pair(from,to));
		//다시 n-개를 2에서 3으로 옮겨
		cnt.add(hanoi20(n-1,subto, to));
		mem[n]=cnt;
		return cnt;
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		BigInteger tmp=null;
		long moveCnt=0;
		int n=Integer.parseInt(br.readLine());

		if(n>20) {
			tmp=hanoi20(n,0,2);
			int tmpsize=tmp.val.size();
			for(int i=0;i<tmpsize;i++) {
				pw.print(tmp.val.get(tmpsize-i-1));
			}
		}else {
			moveCnt=hanoi(n,0,2);//높이, 현재위치, 목적지
			pw.print(moveCnt+"\n");
			if(n<=20)
				for(Pair p:arr) {
					pw.print((p.x+1)+" "+(p.y+1)+"\n");
				}
		}
		
		pw.flush();
		pw.close();
		br.close();
	}
}
