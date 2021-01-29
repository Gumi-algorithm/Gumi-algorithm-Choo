import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//8^8
public class BOJ_17281_야구 {
	class Pair{
		int idx;//idx번 선수(선수번호)
		int order;//order번 타차(치는순서)
	}
	ArrayList<int[]> arr=new ArrayList<>();//경기별 선수 점수
	int[] isSelected = new int[10];
	int[] batter=new int[9];//선수의 순서 기록
	int n;
	int nowBatter;//현재몇번타자인지
	
	public int inning(int num,Pair[] player) {//num이닝의 점수 반환
		int score=0;
		int out=0;
		int ru[]=new int[3];
//		int nowPlayer=player[nowBatter].idx;
		int[] nowGame=arr.get(num);//현재 이닝의 선수들 점수
		int nowHit;
		
		
		while(true) {
			nowHit=nowGame[player[nowBatter++].idx];
			
			nowBatter=nowBatter%9;
			
			if(nowHit==0)
				out++;
			else if(nowHit==1) {
				score+=ru[2];
				ru[2]=ru[1];
				ru[1]=ru[0];
				ru[0]=1;
			}else if(nowHit==2) {
				score+=ru[2];
				score+=ru[1];
				ru[2]=ru[0];
				ru[1]=1;
				ru[0]=0;				
			}else if(nowHit==3) {
				score+=ru[2];
				score+=ru[1];
				score+=ru[0];
				ru[2]=1;
				ru[1]=0;
				ru[0]=0;
			}else if(nowHit==4) {
				score+=ru[2];
				score+=ru[1];
				score+=ru[0];
				score+=1;
				ru[2]=0;
				ru[1]=0;
				ru[0]=0;
			}
			if(out>2)
				break;
		}
		

		return score;
	}
	
	public int baseball(int idx) {
		int score=0;
		if(idx+1>9) {
			Pair[] player=new Pair[9];
			for(int i=0;i<9;i++) {
				Pair tmp=new Pair();
				tmp.idx=i;
				tmp.order=batter[i];
				player[i]=tmp;
			}
			//타자치는순서대로 정렬
			Arrays.sort(player,new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					return o1.order>o2.order?1:-1;
				}
			});
			//시작타자
			nowBatter=0;
			int nowScore=0;
			//n이닝만큼 반복
			for(int i=0;i<n;i++) {
				nowScore+=inning(i,player);	
			}
			score=score<nowScore?nowScore:score;
			return score;
		}
		
		
		for(int i=1;i<=9;i++) {
			if(i==4 || isSelected[i]==1)
				continue;
		
			batter[idx]=i;
			isSelected[i]=1;
			
			int nowScore=baseball(idx+1);
			isSelected[i]=0;
			
			score=score<nowScore?nowScore:score;
		}
		
		return score;
	}
	
	public BOJ_17281_야구() throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw =new PrintWriter(System.out);
		
		n= Integer.parseInt(br.readLine());
	
		
		//input
		for(int i=0;i<n;i++) {
			arr.add(new int[9]);
			String strtmp[]=br.readLine().split(" ");
			for(int j=0;j<9;j++) {
				arr.get(i)[j]=Integer.parseInt(strtmp[j]);
			}
		}
		
		//브루트포스
		//1번타자는 4번타자 고정
		batter[0]=4;
		int result=baseball(1);
		
		pw.println(result);
		br.close();
		pw.close();		
	}
	public static void main(String[] args)throws IOException {
		new BOJ_17281_야구();
	}
}
