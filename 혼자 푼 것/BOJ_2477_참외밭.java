package IM대비문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2477_참외밭 {
	
	//시계방향이면 true
	static boolean isClock(int pre,int now) {
		if(pre==1 && now==3)
			return true;
		if(pre==2 && now==4)
			return true;
		if(pre==3 && now==2)
			return true;
		if(pre==4 && now==1)
			return true;
		
		return false;
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int k=Integer.parseInt(br.readLine());
		int[] arr=new int[5];
		int s1=0,s2=0;
		int sl1=0,sl2=0;
		
		int now,pre=0;
		int nowlen,prelen=0;
		
		String[] str;
		int start=0;
		int startlen=0;
		for(int i=0;i<6;i++) {
			str=br.readLine().split(" ");
			now=Integer.parseInt(str[0]);
			nowlen=Integer.parseInt(str[1]);
			arr[now]=nowlen;
			
			if(i==0) {
				start=now;
				startlen=nowlen;						
				pre=now;
				prelen=nowlen;
				continue;
			}
			if(isClock(pre, now)) {//만약 시계 방향이면 잘못된거임
				s1=now;
				sl1=nowlen;
				s2=pre;
				sl2=prelen;					
			}
			pre=now;
			prelen=nowlen;
		}
		
		//만약 s1,s2가 초기화 안됫다면(처음값, 끝값이 시계 방향이라면) 따로 처리해줘야됨
		if(s1==0 &&s2==0) {
			s1=start;
			sl1=startlen;
			s2=pre;
			sl2=prelen;
		}
		
		int big1=0,big2=0;
		for(int i=1;i<=4;i++) {
			if(i!=s1 && i!=s2) {
				if(big1==0) {
					big1=i;
				}else
					big2=i;
			}
			
		}
		int area=arr[big1]*arr[big2]-(sl1*sl2);
		pw.print(area*k);
		pw.flush();
		br.close();
		pw.close();
	}
}
/*
7
3 20
1 100
4 50
2 160
3 30
1 60
답:47600
*/