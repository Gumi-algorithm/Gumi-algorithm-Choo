import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BOJ_1786_찾기 {
	static String t,p;
	static int fail[];
	static ArrayList<Integer> ans=new ArrayList<>();
	
	static void getFailfunc() {
		int size=p.length();
		int j=0;
		for(int i=1;i<size;i++) {
			while(j>0 && p.charAt(i)!= p.charAt(j))
				j=fail[j-1];
			if(p.charAt(i)==p.charAt(j))
				fail[i] = ++j;			
		}
	}
	
	static void kmp() {		
		getFailfunc();//실패함수 생성
		int tsize=t.length();
		int psize=p.length();
		int j=0;
		for(int i=0;i<tsize;i++) {
			while(j>0&&t.charAt(i)!=p.charAt(j))//일치하지 않으면 현재 접미사와 일치하는 접두사로 이동
				j=fail[j-1];
			if(t.charAt(i)==p.charAt(j)) {
				if(j==psize-1) {//패턴의 마지막에 도달 했을때
					ans.add(i-(psize-1)+1);//정답 넣고
					j=fail[j];//패턴은 현재 접미사와 같은 접두사쪽으로 이동
				}else
					j++;
			}
		}		
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		t=br.readLine();
		p=br.readLine();
		
		fail=new int[p.length()];
		
		kmp();
		int num=ans.size();
		pw.print(num+"\n");
		for(int i:ans) {
			pw.print(i+" ");
		}
		
		br.close();
		pw.flush();
		pw.close();
	}

}
