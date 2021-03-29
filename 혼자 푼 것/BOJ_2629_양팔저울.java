import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_2629_양팔저울 {

	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		
//입력 시작-------------------------------------------------	
		//추의 개수(30이하)
		int n=Integer.parseInt(br.readLine());
		int arr1[]=new int[n];//추를 저장하는 배열
		
		//추를 입력받음(가벼운것부터 차례대로 주어짐)
		String[] str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr1[i]=Integer.parseInt(str[i]);
		}
		
		//구슬의 개수(7이하)
		int m=Integer.parseInt(br.readLine());
		int arr2[]=new int[m];
		
		//구슬을 입력받음
		str=br.readLine().split(" ");
		for(int i=0; i<m;i++) {
			arr2[i]=Integer.parseInt(str[i]);
		}
//입력끝-------------------------------------------------
		
//아이디어	시작------------------------------------------------------
//		구슬의 무게가 주어지면 그 구슬의 무게를 주어진 추로 만들수 있는가 하는 문제
//		그런데 구슬의 무게에 추의 무게를 추로 만들수 있는것도 가능함

//		구슬은 한쪽 저울에 무조건 고정
//		그리고 추를 왼쪽에 올릴지 오른쪽에 올릴지 올리지 않을지 3가지 경우가 존재
//		추는 최대 30개가 주어짐 모든 경우의 수는 3^30 (너무큼, 최적화 필요)

//		해당 문제는 가능한가, 불가능한가를 따지는거지  왼쪽에 몇그램 오른쪽에 몇그램인지는 안중요
//		mem[40001]짜리 배열을 만들어서 해당 인덱스 차이를 만들수 있는가 체크하면됨
//		mem[10]은 왼쪽저울 오른쪽 저울 차이를 10으로 만들수 있는가를 표시하는 거임

//		1 4의 무게를 가진 추가 주어진 경우
//		양 저울에 아무것도 얹이지 않으면 그 차이는 0 그러니 mem[0]=true;
//		무게 1짜리 추를 한개 얹이면 그 차이는 1 mem[1]=true;
//		무게 4짜리 추가 주어진 경우 mem[0+4]=true, mem[1+4]=true, mem[4-1]=true
//		true가 된 무게들을 arraylist에 집어넣어두고 추가 추가될때마가 arraylist와 비교하여 가능한 무게를 추가시킴
//		시간복잡도 O(n^2)  n최대 30
//아이디어 끝--------------------------------------------------------
		
//Solution 시작---------------------------------------------------
		boolean mem[]=new boolean[40001];//해당 인덱스 무게차를 만들수 있는가 체크하는 배열
		ArrayList<Integer> passiable=new ArrayList<>();//만들수있는 무게차들을 저장하는 배열
		//mem배열 초기화
		Arrays.fill(mem, false);
		
		//저울에 아무것도 올리지 않은경우 무게차는 0이다
		mem[0]=true;
		passiable.add(0);
		
		//첫번째 추부터 추가해가며 가능한 무게를 늘려가
		for(int i=0;i<n;i++) {
			int now=arr1[i];//현재 추의 무게
			int size=passiable.size();//현재 생성 가능한 무게들을 저장
			for(int j=0;j<size;j++) {
				int pas=passiable.get(j);//현재 생성 가능한 무게
				
				//기존의 생성 가능하던 무게차에 현재 추가된 추의 무게와 비교하여 추가가능한 무게차를 만든다
				
				//ex) 양 저울에 10 17이 있어 현재 무게차가 7일경우 
				//현재추가 1일때 이걸 무거운쪽에 올려놓아 무게차를 8으로 만드는상황
				if(pas+now<=40000 && !mem[pas+now]) {//mem배열에 추가해야되기때문에 이미 존재하는지 확인하는 작업 필요
					//배열을 40001사이즈로 만들었기때문에 범위체크
					passiable.add(pas+now);
					mem[pas+now]=true;
				}
				//ex) 양 저울에 10 17이 있어 현재 무게차가 7일경우 
				//현재추가 1일때 이걸 가벼운쪽에 올려놓아 무게차를 6으로 만드는상황
				if(pas-now>0&& pas-now<=40000 && !mem[pas-now]) {
					//위에서 무게차가 0인경우는 이미 추가했으니 >=말고 >로 함
					passiable.add(pas-now);
					mem[pas-now]=true;
				}
				
				//ex) 양 저울에 16 17이 있어 현재 무게차가 1일경우 
				//현재추가 5일때 이걸 가벼운쪽에 올려놓아 무게차를 4로 만드는상황
				if(now-pas>0 && now-pas<=40000 && !mem[now-pas]) {
					passiable.add(now-pas);
					mem[now-pas]=true;
				}				
			}			
		}
		//만들수 있는 무게차 파악 끝
		
//Solution끝---------------------------------------------------		
		
//답 출력 시작 ----------------------------------------------------------
		//그럼 이제 만들어진 무게차를 바탕으로 구슬의 무게를 확인해 수평이 되는지 판단
		for(int i:arr2) {
			if(mem[i])//i무게를 만들수 있으면 true니까 Y출력
				sb.append("Y ");
			else//i무게를 만들수없으면 false니까 N출력
				sb.append("N ");
		}
//답 출력 끝 --------------------------------------------------------
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}
/*
추는 500g 30개 최대
500 * 30= 15000

2
1 4
2
3 2

4
2 3 3 3
3
1 4 10

*/
