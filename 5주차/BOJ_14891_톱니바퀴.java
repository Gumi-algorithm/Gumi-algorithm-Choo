import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ_14891_톱니바퀴 {

	static int[][] wheel = new int[4][8];
	static int[] idx = { 0, 0, 0, 0 };// 12시 방향을 가르킴

	static void rightrotate(int nowwheel, int nextwheel, int rotatedirection) {// 톱니바퀴 번호, 회전방향
		if (nowwheel == 3)
			return;
		// 톱니바퀴 톱니 인덱스
		int now = wheel[nowwheel][(idx[nowwheel] + 2) % 8];// 오른쪽 톱니랑 극 비교해야되니까 12시기준 오른쪽 두칸꺼랑 비교해야됨
		int next = wheel[nextwheel][(idx[nextwheel] - 2 + 8) % 8];// 오른쪽 톱니의 왼쪽극이랑 비교해야되니까 12시기준 왼쪽 2칸이랑 비교
		if (now != next) {// 극이 다르면 회전
			rightrotate(nextwheel, nextwheel + 1, rotatedirection * (-1));

			// 자기 자신은 회전 안시키고 next바퀴만 회전시킴
			// 왜냐하면 아직 출발점 기준 왼쪽으로는 안갓으니까 출발 톱니 회전시켜버리면 안됨
			// 매개변수로 넘어온 회전뱡항은 now기준 그러니까 rotatedirection반대방향으로 돌려야함
			if (rotatedirection == 1) {// now가 시계방향이니까 next는 반시계방향으로 돌아야댐
				idx[nextwheel] = (idx[nextwheel] + 1) % 8;
			} else {
				idx[nextwheel] = (idx[nextwheel] - 1 + 8) % 8;
			}
		}
	}

	static void leftrotate(int nowwheel, int nextwheel, int rotatedirection) {// 톱니바퀴 번호, 회전방향
		if (nowwheel == 0)
			return;
		// 톱니바퀴 톱니 인덱스
		int now = wheel[nowwheel][(idx[nowwheel] - 2 + 8) % 8];
		int next = wheel[nextwheel][(idx[nextwheel] + 2) % 8];
		if (now != next) {// 극이 다르면 회전
			leftrotate(nextwheel, nextwheel - 1, rotatedirection * (-1));

			// 자기 자신은 회전 안시키고 next바퀴만 회전시킴
			// 왜냐하면 아직 출발점 기준 왼쪽으로는 안갓으니까 출발 톱니 회전시켜버리면 안됨
			// 매개변수로 넘어온 회전뱡항은 now기준 그러니까 rotatedirection반대방향으로 돌려야함
			if (rotatedirection == 1) {// now가 시계방향이니까 next는 반시계방향으로 돌아야댐
				idx[nextwheel] = (idx[nextwheel] + 1) % 8;
			} else {
				idx[nextwheel] = (idx[nextwheel] - 1 + 8) % 8;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
//		StringBuilder sb = new StringBuilder();

		String str;
		for (int i = 0; i < 4; i++) {
			str = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = (str.charAt(j) - '0');// N극이면 0 S극이면1
			}
		}
		int k = Integer.parseInt(br.readLine());
		String[] strarr;
		for (int i = 0; i < k; i++) {
			strarr = br.readLine().split(" ");
			int a = Integer.parseInt(strarr[0]);// 톱니바퀴 번호
			int b = Integer.parseInt(strarr[1]);// 회전방향(1 시계방향, -1 반시계방향)
			a=a-1;//인덱스 0부터 시작하니까 -1해줌
			// 오른쪽 톱니바퀴 확인
			rightrotate(a, a + 1, b);
			// 왼쪽 톱니바퀴 확인
			leftrotate(a, a - 1, b);
			// 자기 톱니 회전 시켜
			if (b == 1)// 시계방향
				idx[a] = (idx[a] - 1 + 8) % 8;
			else// 반시계방향
				idx[a] = (idx[a] + 1) % 8;
		}
		int ans=0;
		for(int i=0;i<4;i++) {
			if(wheel[i][idx[i]]==1)
				ans=ans|1<<i;
		}

		pw.print(ans);
		pw.flush();
		br.close();
		pw.close();
	}

}
