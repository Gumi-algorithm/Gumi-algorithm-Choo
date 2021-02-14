import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_10814_나이순정렬 {
	static class Pair{
		int age;
		String name;	
		public Pair(int age,String name) {
			this.age=age;
			this.name=name;
		}
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		Pair[] arr;
		int n=Integer.parseInt(br.readLine());
		arr=new Pair[n];
		String[] str;
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			arr[i]=new Pair(Integer.parseInt(str[0]),str[1]);
		}
		Arrays.sort(arr,(a,b)->a.age-b.age);
		for(int i=0;i<n;i++) {
			pw.print(arr[i].age+" "+arr[i].name+"\n");
		}
		
		pw.flush();
		pw.close();
		br.close();
	}

}
