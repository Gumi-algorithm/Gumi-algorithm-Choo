package IM대비문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_17413_단어뒤집기2 {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		
		String str=br.readLine();
		int size=str.length();
		int start=0;
		int end=0;
		boolean tag=false;
		for(int i=0;i<size;i++) {
			char now=str.charAt(i);
			
			if(now=='<') {
				for(int j=1;j<=i-start;j++) {
					sb.append(str.charAt(i-j));
				}
				start=i+1;				
				tag=true;
			}
			if(tag) {
				sb.append(now);
			}
			if(now=='>') {
				start=i+1;
				tag=false;
				continue;
			}
			if(tag)
				continue;
			
			if(now==' ') {
				for(int j=1;j<=i-start;j++) {
					sb.append(str.charAt(i-j));
				}
				sb.append(' ');
				start=i+1;
			}
		}
		if(start<size) {
			for(int i=size-1;i>=start;i--) {
				sb.append(str.charAt(i));
			}
		}
		
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}