import java.sql.*;

public class Conn { // 创建类Conn
	Connection con; // 声明Connection对象
	
	public Connection getConnection() {// 建立返回值为Connection的方法
		try {// 加载数据库驱动类
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			System.out.println("数据库驱动加载成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {// 通过访问数据库的URL获取数据库连接对象
			con = DriverManager.getConnection("jdbc:jtds:sqlserver"
					+ "://localhost:1433/db_jdbc", "sa", "");
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con; // 按方法要求返回一个Connection对象
	}
	
	public static void main(String[] args) { // 主方法
		Conn c = new Conn(); // 创建本类对象
		c.getConnection(); // 调用连接数据库方法
	}
}
