public class Number { // 创建类
	public static void main(String[] args) { // 主方法
		byte mybyte = 124; // 声明byte型变量并赋值
		short myshort = 32564; // 声明short型变量并赋值
		long mylong2 = 999999999; // 声明int型变量并赋值
		long mylong = 999999999; // 声明long型变量并赋值
		long result = mybyte + myshort + mylong2*9999999 + mylong;// 获得各数相加后的结果
		System.out.println("结果为：" + result); // 将以上变量相加的结果输出
	}
}
