package IM대비문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_10163_색종이 {
	static int[][] arr=new int[111][111];
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		
		int n=Integer.parseInt(br.readLine());
		int [] area=new int[n+1];
		String[] str;
		for(int i=1;i<=n;i++) {
			str=br.readLine().split(" ");
			int x1=Integer.parseInt(str[0]);
			int y1=Integer.parseInt(str[1]);
			int x2=Integer.parseInt(str[2]);
			int y2=Integer.parseInt(str[3]);
			
			for(int j=0;j<x2;j++) {
				for(int k=0;k<y2;k++) {
					arr[x1+j][y1+k]=i;
				}
			}
		}
		for(int i=0;i<110;i++) {
			for(int j=0;j<110;j++) {
				area[arr[i][j]]++;
			}
		}
		for(int i=1;i<=n;i++) {
			sb.append(area[i]).append("\n");
		}		
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}