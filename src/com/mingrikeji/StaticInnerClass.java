package com.lzw;

public class StaticInnerClass {
	int x=100;
	static class Inner{
		void doitInner(){
			//System.out.println("外部类"+x);
		}
		public static void main(String args[]){
			System.out.println("a");
		}
	}
}
