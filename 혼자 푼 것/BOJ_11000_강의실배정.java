import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_11000_강의실배정 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][2];
		int roomidx=0;
		String[] str;
		for (int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			arr[i][0] = Integer.parseInt(str[0]);
			arr[i][1] = Integer.parseInt(str[1]);
		}
		//시작 시간 기준으로 정렬
		Arrays.sort(arr,(a,b)->(a[0]-b[0]));
		PriorityQueue<Integer> room=new PriorityQueue<>();
		//큐에 끝나는 시간을 집어넣은뒤 다음 강의를 넣을때 충돌이 없으면 제거해 
		room.offer(arr[0][1]);
		for(int i=1;i<n;i++) {
			int now=room.peek();
			
			if(now<=arr[i][0]) {
				room.poll();
			}
			room.offer(arr[i][1]);
		}
		roomidx=room.size();		
		pw.print(roomidx);
		br.close();
		pw.flush();
		pw.close();
	}
}
/*
4
1 2
1 4
2 6
4 5
답:2

6
1 3
2 5
7 8
4 12
9 10
7 11
답: 3
*/