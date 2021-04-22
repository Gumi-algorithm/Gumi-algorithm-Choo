import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BOJ_17825_주사위윷놀이 {
	
	static class Node{
		int next;//빨간화살표
		int next2=0;//파란화살표
		int val;//현재칸 점수(시작,도착은 0)
		int ishorse=0;
		
		public Node() {}
		public Node(int next, int val) {
			super();
			this.next = next;
			this.val = val;
		}
		public void setNext2(int next2) {
			this.next2 = next2;
		}
		public void setNext(int next) {
			this.next = next;
		}
		@Override
		public String toString() {
			return "Node [next=" + next + ", next2=" + next2 + ", val=" + val + ", ishorse=" + ishorse + "]";
		}
		
	}
	
	static Node map[]=new Node[33];
	static ArrayList<Integer> horse=new ArrayList<>();//말 위치정보
	static int[] arr;
	
	//윷놀이판 세팅
	static void setmap() {	
		for(int i=0;i<=20;i++) {
			map[i]=new Node(i+1,i*2);//다음노드, 현재칸 점수
		}
		map[21]=new Node(22, 13);//다음노드, 현재칸 점수
		map[22]=new Node(23, 16);
		map[23]=new Node(24, 19);
		//맨 가운데 값25인애
		map[24]=new Node(30,25);//다음노드, 현재칸 점수
		//가운데 25 밑에부분
		map[25]=new Node(26,22);//다음노드, 현재칸 점수
		map[26]=new Node(24,24);
		//가운데 25 오른쪽부분
		map[27]=new Node(28,28);//다음노드, 현재칸 점수
		map[28]=new Node(29,27);
		map[29]=new Node(24,26);
		//가운데 25윗부분
		map[30]=new Node(31,30);//다음노드, 현재칸 점수
		map[31]=new Node(20,35);
		//파랑 화살표 세팅
		map[5].setNext2(21);
		map[10].setNext2(25);
		map[15].setNext2(27);
		//20번째꺼는 도착지(32)를 가르켜
		map[20].setNext(32);
		//도착지는 자기자신을 가리킴
		map[32]=new Node(32,0);
	}

	static int move(int now,int dicenum) {
		if(map[now].next2!=0)//파란화살표 있으면 그쪽으로 이동
			now=map[now].next2;
		else
			now=map[now].next;
		for(int i=1;i<dicenum;i++) {
			now=map[now].next;
		}		
		return now;
	}
	
	static int start(int idx,int hcnt) {
		//idx:현재 주사위, hcnt:map위의 말 갯수, pre: 이전 맵 도착위치
		
		if(idx==10)//주사위 다굴리면 탈출
			return 0;
		
		int dicenum=arr[idx];
		int tmp=0;
		//움직알 말을 선택
		//출발지에 있는말을 선택함
		if(hcnt<4) {
			int des=move(0,dicenum);
			//말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다.(단, 이동을 마치는 칸이 도착 칸이면 고를 수 있다.) 
			if(map[des].ishorse==0) {//도착칸에 말이 없을때만 움직임
				//첫 시작에선 도착지에 바로 도착하는경우 없음
				horse.set(hcnt,des);
				map[des].ishorse=1;//맵에 현재위치에 말이 있다고 표시
				tmp=start(idx+1,hcnt+1)+map[des].val;
				
				//정보 원상복귀
				horse.set(hcnt,0);
				map[des].ishorse=0;
			}
		}
		//맵위의 말을 선택함
		for(int i=0;i<hcnt;i++) {
			int now=horse.get(i);
			if(now==32) {//도착한 말은 넘겨
				continue;
			}
			int des=move(now,dicenum);
			//말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다.(단, 이동을 마치는 칸이 도착 칸이면 고를 수 있다.) 
			if(map[des].ishorse==1 && des!=32)
				continue;
			horse.set(i, des);//말위치정보 갱신
			
			//맵에 현재위치에 말이 있다고 표시
			map[now].ishorse=0;
			map[des].ishorse=1;
			int tmp2=start(idx+1,hcnt)+map[des].val;
			tmp=Math.max(tmp, tmp2);
			
			//정보 원상복귀
			map[now].ishorse=1;
			map[des].ishorse=0;
			horse.set(i, now);
		}
		
		return tmp;
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		arr=new int [10];
		String[] str=br.readLine().split(" ");
		for(int i=0;i<10;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		//4^10 = 1,048,576
		
		//윷놀이판 세팅
		setmap();
		
		//말 변수 크기세팅
		horse.add(0);
		horse.add(0);
		horse.add(0);
		horse.add(0);
		
		
		//출발
		int ans=start(0,0);
		
		pw.print(ans);
		pw.flush();
		br.close();
		pw.close();
	}
}
