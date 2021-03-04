import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_1092_배 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int[] arr;//크래인이 옮길수 있는 갯수들 저장하는배열
		
		//인풋
		int n=Integer.parseInt(br.readLine());
		int[] crain=new int[n];
		String[] str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			crain[i]=Integer.parseInt(str[i]);
		}
		int m=Integer.parseInt(br.readLine());
		int[] box=new int[m];
		str=br.readLine().split(" ");
		for(int i=0;i<m;i++) {
			box[i]=Integer.parseInt(str[i]);
		}
		int time=0;
		Arrays.sort(crain);
		arr=new int[n];
		for(int i=0;i<m;i++) {
			int tmp=0;
			int nowBox=box[i];
			//박스들 무게 확인하며 arr배열 채워
			for(int j=0;j<n;j++) {
				int nowCrain=crain[j];
				if(nowBox<=nowCrain) {
					arr[j]++;
					tmp=1;
					break;
				}
			}
			if(tmp==0) {//만약 못옮긴다면
				time=-1;
				break;
			}
		}
		//위에거 거치면 4 1 0 이 arr에 저장됨
		
		if(time!=-1) {
			//무게가 큰 크래인이 무게가 큰 상자부터 옮김
			while(true) {
				int end=0;
				for(int i=n-1;i>=0;i--) {	//크래인			
					for(int j=i;j>=0;j--) {//크래인이 옮길수있는 박스들
						if(arr[j]!=0) {
							arr[j]--;
							end=1;
							break;
						}
					}		
				}
				//옮길수 있는게 하나도 없으면 break
				if(end==0)
					break;
				time++;
			}
		}
		pw.print(time);
		br.close();
		pw.flush();
		pw.close();
	}
}
/*
3
6 8 9
5
2 5 2 4 7
이 입력으로 들어오면
6보다 작은거 4개
8보다 작은거 5개
9보다 작은거 5개
4 5 5 
되잇는걸
6이 4개를 옮긴다면 8은 1개만 옮기면됨
6이 4개 8이 1개를 옮기면 9는 0개 옮기면됨
그래서
4 1 0
이 나옴
여기서 9는 자기인덱스(2)이하인거 옮겨
4 0 0
다음으로 8도 자기인덱스 (1)이하인거 옮겨
3 0 0
다음 6도 자기인덱스(0)이하인거 옮겨
2 0 0
이렇게 반복

시간복잡도(n^2)
10000개의 박스를 옮기는데
1개 박스 옮길때 자기가 옮길 박스 찾는데 필요한 횟수 최대10000번

만약 같은 무게를 옮기는 크래인이 여러개라면?
앞에 애한테 할당시키자
*/

/*
lower_bound를 이용해 찾는것도 방법이지 않을까?
만약 이방법 쓰면 매번 정렬해야됨
정렬 nlogn
lower_bound logn;

총 시간복잡도 nlogn*logn (맞나? 암튼 더 빠를듯)
lower_bound구현 귀찮으니 위방법으로 할거임
*/