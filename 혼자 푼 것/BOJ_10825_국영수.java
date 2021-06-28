import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BOJ_10825_국영수 {

	static class student {
		String name;
		int a, b, c;

		public student(String name, int a, int b, int c) {
			super();
			this.name = name;
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n = Integer.parseInt(br.readLine());

		ArrayList<student> std = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			std.add(new student(str[0], Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3])));
		}

		Collections.sort(std, new Comparator<student>() {

			@Override
			public int compare(student o1, student o2) {
				//국어 점수가 같으면
				if (o1.a == o2.a) {
					//국어점수와 영어점수가 같으면
					if (o1.b == o2.b) {
						if (o1.c == o2.c)
							return o1.name.compareTo(o2.name);
						//수학 점수가 감소하는 순서로
						return -(o1.c - o2.c);
					}

					// 국어점수가 같으면 영어점수가 증가하는 순서로
					return o1.b - o2.b;
				}

				// 국어 내림차순
				return -(o1.a - o2.a);

			}
		});

		for(int i=0;i<std.size();i++) {
			pw.println(std.get(i).name);
		}
		
		br.close();
		pw.flush();
		pw.close();
	}
}