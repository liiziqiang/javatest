package com.mingrikeji;


public class TheSameName {
	private int x=111;

	
	static class Inner {
		private int x = 9;
		public void doit(int x) {
			x++; // 调用的是形参x
			this.x++; // 调用内部类的变量x
			//TheSameName.this.x++; // 调用外部类的变量x
			System.out.println(x);
			System.out.println(this.x);
		}
	}
	public static void main(String[] args) {
		TheSameName aa = new TheSameName();
		System.out.println(aa.x);
		Inner in = new Inner();
		in.doit(1);

	}
}
