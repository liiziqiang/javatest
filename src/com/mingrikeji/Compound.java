package com.mingrikeji;
public class Compound {
	public static void main(String args[]) {
		int x = 20;
		{
			int y = 40;
			System.out.println(y);
			int z = 245;
			boolean b;
			{
				b = y > z;
				System.out.println(b); // 复合语句
			}
		}
		String word = "hello java";
		System.out.println(word);
	}
}
