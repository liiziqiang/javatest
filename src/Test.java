
public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		* Title: Java Bean 工具
		* @version 1.0
		* 
		*/
		import java.util.*;
		import java.util.regex.Pattern;
		public class StrTools {
		/**
		* 分割字符串
		*
		* @param str String 原始字符串
		* @param splitsign String 分隔符
		* @return String[] 分割后的字符串数组
		*/
		@SuppressWarnings("unchecked")
		public static String[] split(String str, String splitsign) {
		int index;
		if (str == null || splitsign == null)
		return null;
		ArrayList al = new ArrayList();
		while ((index = str.indexOf(splitsign)) != -1) {
		al.add(str.substring(0, index));
		str = str.substring(index + splitsign.length());
		}
		al.add(str);
		return (String[]) al.toArray(new String[0]);
}

		/**
		* 将指定byte数组以16进制的形式打印到控制台
		*
		* @param hint
		* String
		* @param b
		* byte[]
		* @return void
		*/
		public static void printHexString(String hint, byte[] b)
		{
		System.out.print(hint);
		for (int i = 0; i < b.length; i++)
		{
		String hex = Integer.toHexString(b[i] & 0xFF);
		if (hex.length() == 1)
		{
		hex = '0' + hex;
		}
		System.out.print(hex.toUpperCase() + " ");
		}
		System.out.println("");
}

/**
* 判断任意一个整数是否素数
*
* @param n
* @return boolean
*/
public static boolean isPrimes(int n)
{
for (int i = 2; i <= Math.sqrt(n); i++)
{
if (n % i == 0)
{
return false;
}
}
return true;
}

		/**
		* 获得任意一个整数的阶乘，递归
		*
		* @param n
		* @return n!
		*/
		public static int factorial(int n)
		{
		if (n == 1)
		{
		return 1;
		}
		return n * factorial(n - 1);
		}

		
		/**
* 将某个日期以固定格式转化成字符串
*
* @param date
* @return String
*/
public static String dateToStr(java.util.Date date)
{
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String str = sdf.format(date);
return str;
}


		/**
		* 替换字符串
		*
		* @param from String 原始字符串
		* @param to String 目标字符串
		* @param source String 母字符串
		* @return String 替换后的字符串
		*/
		public static String replace(String from, String to, String source) {
		if (source == null || from == null || to == null)
		return null;
		StringBuffer bf = new StringBuffer("");
		int index = -1;
		while ((index = source.indexOf(from)) != -1) {
		bf.append(source.substring(0, index) + to);
		source = source.substring(index + from.length());
		index = source.indexOf(from);
		}
		bf.append(source);
		return bf.toString();
		}
		/**
		* 替换字符串，能能够在HTML页面上直接显示(替换双引号和小于号)
		*
		* @param str String 原始字符串
		* @return String 替换后的字符串
		*/
		public static String htmlencode(String str) {
		if (str == null) {
		return null;
		}
		return replace("\"", """, replace("<", "<", str));
		}
		/**
		* 替换字符串，将被编码的转换成原始码（替换成双引号和小于号）
		*
		* @param str String
		* @return String
		*/
		public static String htmldecode(String str) {
		if (str == null) {
		return null;
		}
		return replace(""", "\"", replace("<", "<", str));
		}
		private static final String _BR = "<br/>";
		/**
		* 在页面上直接显示文本内容，替换小于号，空格，回车，TAB
		*
		* @param str String 原始字符串
		* @return String 替换后的字符串
		*/
		public static String htmlshow(String str) {
		if (str == null) {
		return null;
		}
		str = replace("<", "<", str);
		str = replace(" ", " ", str);
		str = replace("\r\n", _BR, str);
		str = replace("\n", _BR, str);
		str = replace("\t", "    ", str);
		return str;
		}
		/**
		* 返回指定字节长度的字符串
		*
		* @param str String 字符串
		* @param length int 指定长度
		* @return String 返回的字符串
		*/
		public static String toLength(String str, int length) {
		if (str == null) {
		return null;
		}
		if (length <= 0) {
		return "";
		}
		try {
		if (str.getBytes("GBK").length <= length) {
		return str;
		}
		} catch (Exception ex) {
		}
		StringBuffer buff = new StringBuffer();
		int index = 0;
		char c;
		length -= 3;
		while (length > 0) {
		c = str.charAt(index);
		if (c < 128) {
		length--;
		} else {
		length--;
		length--;
		}
		buff.append(c);
		index++;
		}
		buff.append("...");
		return buff.toString();
		}
		/**
		* 判断是否为整数
		*
		* @param str 传入的字符串
		* @return 是整数返回true,否则返回false
		*/
		public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
		}
		/**
		* 判断是否为浮点数，包括double和float
		*
		* @param str 传入的字符串
		* @return 是浮点数返回true,否则返回false
		*/
		public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
		}
		/**
		* 判断输入的字符串是否符合Email样式.
		*
		* @param str 传入的字符串
		* @return 是Email样式返回true,否则返回false
		*/
		public stat
		ic boolean isEmail(String str) {
		Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(str).matches();
		}
		/**
		* 判断输入的字符串是否为纯汉字
		*
		* @param str 传入的字符窜
		* @return 如果是纯汉字返回true,否则返回false
		*/
		public static boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
		}
		/**
		* 是否为空白,包括null和""
		*
		* @param str
		* @return
		*/
		public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
		}
		/**
		* 判断是否为质数
		*
		* @param x
		* @return
		*/
		public static boolean isPrime(int x) {
		if (x <= 7) {
		if (x == 2 || x == 3 || x == 5 || x == 7)
		return true;
		}
		int c = 7;
		if (x % 2 == 0)
		return false;
		if (x % 3 == 0)
		return false;
		if (x % 5 == 0)
		return false;
		int end = (int) Math.sqrt(x);
		while (c <= end) {
		if (x % c == 0) {
		return false;
		}
		c += 4;
		if (x % c == 0) {
		return false;
		}
		c += 2;
		if (x % c == 0) {
		return false;
		}
		c += 4;
		if (x % c == 0) {
		return false;
		}
		c += 2;
		if (x % c == 0) {
		return false;
		}
		c += 4;
		if (x % c == 0) {
		return false;
		}
		c += 6;
		if (x % c == 0) {
		return false;
		}
		c += 2;
		if (x % c == 0) {
		return false;
		}
		c += 6;
		}
		return true;
		}
		public static void main(String[] args) {
		String[] numbers = { "12345", "-12345", "123.45", "-123.45", ".12345", "-.12345", "a12345", "12345a", "123.a45" };
		for (String str : numbers) {
		System.out.println(str + "=" + isInteger(str) + " " + isDouble(str));
		}
		String[] emails = { "1@2.com", "1.2@3.com", "1@3.4.5.com" };
		for (String str : emails) {
		System.out.println(str + "=" + isEmail(str));
		}
		String[] chineses = { "中国", "1中国", "中国1", "1中国2", "中1国" };
		for (String str : chineses) {
		System.out.println(str + "=" + isChinese(str));
		}
		}
		}
		/* * Db.java
		Created on 2007年8月20日, 上午 8:37
		*/
		import java.io.*;
		import java.sql.*;
		import java.util.Properties;
		public class Db {
		private String driver;
		private String url;
		private String user;
		private String password;
		private Connection conn;
		private Statement stm;
		private ResultSet rs;
		public Db(){
		this("DBConf.properties");
		}
		public Db(String conf) {
		loadProperties(conf);
		setConn();
		}
		public Connection getConn(){
		return this.conn;
		}
		//handle the properties file to get the informations for connection
		private void loadProperties(String conf){
		Properties props = new Properties();
		try {
		props.load(new FileInputStream(conf));
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		this.driver = props.getProperty("driver");
		this.url = props.getProperty("url");
		this.user = props.getProperty("user");
		this.password = props.getProperty("password");
		}
		//implement the Connection
		private void setConn(){
		try {
		Class.forName(driver);
		this.conn = DriverManager.getConnection(url,user,password);
		} catch(ClassNotFoundException classnotfoundexception) {
		classnotfoundexception.printStackTrace();
		System.err.println("db: " + classnotfoundexception.getMessage());
		} catch(SQLException sqlexception) {
		System.err.println("db.getconn(): " + sqlexception.getMessage());
		}
		}
		public void doInsert(String sql) {
		try {
		Statement statement = conn.createStatement();
		int i = stm.executeUpdate(sql);
		} catch(SQLException sqlexception) {
		System.err.println("db.executeInset:" + sqlexception.getMessage());
		}
		}
		public void doDelete(String sql) {
		try {
		stm = conn.createStatement();
		int i = stm.executeUpdate(sql);
		} catch(SQLException sqlexception) {
		System.err.println("db.executeDelete:" + sqlexception.getMessage());
		}
		}
		public void doUpdate(String sql) {
		try {
		stm = conn.createStatement();
		int i = stm.executeUpdate(sql);
		} catch(SQLException sqlexception) {
		System.err.println("db.executeUpdate:" + sqlexception.getMessage());
		}
		}
		public ResultSet doSelect(String sql) {
		try {
		stm = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
		rs = stm.executeQuery(sql);
		} catch(SQLException sqlexception) {
		System.err.println("db.executeQuery: " + sqlexception.getMessage());
		}
		return rs;
		}
		public static void main(String[] args){
		try{
		Db db = new Db();
		Connection conn = db.getConn();
		if(conn != null && !conn.isClosed()) {
		System.out.println("連結成功");
		ResultSet rs = db.doSelect("select * from content");
		while(rs.next()){
		System.out.println(rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3));
		}
		rs.close();
		conn.close();
		}
		}catch(SQLException e) {
		e.printStackTrace();
		}
		}
		}
		DBConf.properties:
		driver=oracle.jdbc.driver.OracleDriver
		url=jdbc:oracle:thin:@tdt151:1521:train
		user=XX
		password=XX
		/**
		* 人民币转成大写
		*
		* @param value
		* @return String
		*/
		public static String hangeToBig(double value)
		{
		char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
		char[] vunit = { '万', '亿' }; // 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
		long midVal = (long) (value * 100); // 转化成整形
		String valStr = String.valueOf(midVal); // 转化成字符串
		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
		String rail = valStr.substring(valStr.length() - 2); // 取小数部分
		String prefix = ""; // 整数部分转化的结果
		String suffix = ""; // 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00"))
		{ // 如果小数部分为0
		suffix = "整";
		}
		else
		{
		suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for (int i = 0; i < chDig.length; i++)
		{ // 循环处理每个数字
		int idx = (chDig.length - i - 1) % 4; // 取段内位置
		int vidx = (chDig.length - i - 1) / 4; // 取段位置
		if (chDig[i] == '0')
		{ // 如果当前字符是0
		zeroSerNum++; // 连续0次数递增
		if (zero == '0')
		{ // 标志
		zero = digit[0];
		}
		else if (idx == 0 && vidx > 0 && zeroSerNum < 4)
		{
		prefix += vunit[vidx - 1];
		zero = '0';
		}
		continue;
		}
		zeroSerNum = 0; // 连续0次数清零
		if (zero != '0')
		{ // 如果标志不为0,则加上,例如万,亿什么的
		prefix += zero;
		zero = '0';
		}
		prefix += digit[chDig[i] - '0']; // 转化该数字表示
		if (idx > 0)
		prefix += hunit[idx - 1];
		if (idx == 0 && vidx > 0)
		{
		prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
		}
		}
		if (prefix.length() > 0)
		prefix += '圆'; // 如果整数部分存在,则有圆的字样
		return prefix + suffix; // 返回正确表示
		}
		/**
		* 全角字符转半角字符
		*
		* @param QJStr
		* @return String
		*/
		public static final String QJToBJChange(String QJStr)
		{
		char[] chr = QJStr.toCharArray();
		String str = "";
		for (int i = 0; i < chr.length; i++)
		{
		chr[i] = (char) ((int) chr[i] - 65248);
		str += chr[i];
		}
		return str;
		}
		/**
		* 去掉字符串中重复的子字符串
		*
		* @param str
		* @return String
		*/
		private static String removeSameString(String str)
		{
		Set<String> mLinkedSet = new LinkedHashSet<String>();
		String[] strArray = str.split(" ");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strArray.length; i++)
		{
		if (!mLinkedSet.contains(strArray[i]))
		{
		mLinkedSet.add(strArray[i]);
		sb.append(strArray[i] + " ");
		}
		}
		System.out.println(mLinkedSet);
		return sb.toString().substring(0, sb.toString().length() - 1);
		}
		/**
		* 设置JSpinner的编辑属性
		* @param spinner 目标JSpinner
		* @param isAllowInvalid 是否允许输入非法值
		* @param isEditable 是否允许编辑
		*/
		public static void setAllowsInvalid(JSpinner spinner, boolean isAllowInvalid, boolean isEditable)
		{
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner, "#");
		spinner.setEditor(editor);
		JFormattedTextField tf = ((JSpinner.NumberEditor)spinner.getEditor()).getTextField();
		tf.setEditable(isEditable);
		DefaultFormatterFactory factory = (DefaultFormatterFactory)tf.getFormatterFactory();
		NumberFormatter formatter = (NumberFormatter)factory.getDefaultFormatter();
		formatter.setAllowsInvalid(isAllowInvalid);
		}
		/**
		* 根据指定方法的参数去构造一个新的对象的拷贝并将他返回
		* @param obj 原始对象
		* @return 新对象
		* @throws NoSuchMethodException
		* @throws InvocationTargetException
		* @throws IllegalAccessException
		* @throws InstantiationException
		* @throws SecurityException
		* @throws IllegalArgumentException
		*/
		@SuppressWarnings("unchecked")
		public static Object copy(Object obj) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException
		{
		//获得对象的类型
		Class classType = obj.getClass();
		//通过默认构造方法去创建一个新的对象，getConstructor的视其参数决定调用哪个构造方法
		Object objectCopy = classType.getConstructor(new Class[]{}).newInstance(new Object[]{});
		//获得对象的所有属性
		Field[] fields = classType.getDeclaredFields();
		for(int i = 0; i < fields.length; i++)
		{
		/
		/获取数组中对应的属性
		Field field = fields[i];
		String fieldName = field.getName();
		String stringLetter = fieldName.substring(0, 1).toUpperCase();
		//获得相应属性的getXXX和setXXX方法名称
		String getName = "get" + stringLetter + fieldName.substring(1);
		String setName = "set" + stringLetter + fieldName.substring(1);
		//获取相应的方法
		Method getMethod = classType.getMethod(getName, new Class[]{});
		Method setMethod = classType.getMethod(setName, new Class[]{field.getType()});
		//调用源对象的getXXX（）方法
		Object value = getMethod.invoke(obj, new Object[]{});
		//调用拷贝对象的setXXX（）方法
		setMethod.invoke(objectCopy, new Object[]{value});
		}
		return objectCopy;
		}
		//过滤特殊字符
		public static String encoding(String src){
		if (src==null)
		return "";
		StringBuilder result=new StringBuilder();
		if (src!=null){
		src=src.trim();
		for (int pos=0;pos<src.length();pos++){
		switch(src.charAt(pos)){
		case '\"':result.append(""");break;
		case '<':result.append("<");break;
		case '>':result.append(">");break;
		case '\'':result.append("'");break;
		case '&':result.append("&");break;
		case '%':result.append("&pc;");break;
		case '_':result.append("&ul;");break;
		case '#':result.append("&shap;");break;
		case '?':result.append("&ques;");break;
		default:result.append(src.charAt(pos));break;
		}
		}
		}
		return result.toString();
		}
		//反过滤特殊字符
		public static String decoding(String src){
		if (src==null)
		return "";
		String result=src;
		result=result.replace(""", "\"").replace("'", "\'");
		result=result.replace("<", "<").replace(">", ">");
		result=result.replace("&", "&");
		result=result.replace("&pc;", "%").replace("&ul", "_");
		result=result.replace("&shap;", "#").replace("&ques", "?");
		return result;
		}
		//利用反射调用一个继承层次上的函数族，比如安装程序，有安装数据库的，安装文件系统的等，命名均已“install”开始，你就可以将参数part设为“install”，src是其实类实例，root是终止父类
		public static <T> void invokeMethods(String part,T src,Class root) throws ExceptionManager{
		if (root!=null){
		if (!root.isInstance(src))return;
		root=(Class)root.getGenericSuperclass();
		}
		HashMap<String,Method> invokees=new HashMap<Str
		ing,Method>();
		Class target=src.getClass();
		do{
		Method [] methods=target.getDeclaredMethods();
		for (Method method:methods){
		String mn=method.getName();
		Boolean isPass=mn.startsWith(part);
		if (isPass){
		Integer nopt=method.getParameterTypes().length;
		Boolean isStatic=Modifier.isStatic(method.getModifiers());
		if ((nopt==0)&&(!isStatic)){
		if (!invokees.containsKey(mn))
		invokees.put(mn, method);
		}
		}
		}
		target=(Class)target.getGenericSuperclass();
		}while(target!=root);
		Iterator<String> methods=invokees.keySet().iterator();
		while (methods.hasNext()){
		Method invokee=invokees.get(methods.next());
		Boolean access=invokee.isAccessible();
		invokee.setAccessible(true);
		try {
		invokee.invoke(src);
		} catch (InvocationTargetException e) {
		throw ExceptionManager.wrap(e.getTargetException());
		}catch (Exception e){}
		invokee.setAccessible(access);
		}
		}
		MySQL：
		String Driver="com.mysql.jdbc.Driver"; //驱动程序
		String URL="jdbc:mysql://localhost:3306/db_name"; //连接的URL,db_name为数据库名
		String Username="username"; //用户名
		String Password="password"; //密码
		Class.forName(Driver).new Instance();
		Connection con=DriverManager.getConnection(URL,Username,Password);
		Microsoft SQL Server 2.0驱动(3个jar的那个):
		String Driver="com.microsoft.jdbc.sqlserver.SQLServerDriver"; //连接SQL数据库的方法
		String URL="jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=db_name"; //db_name为数据库名
		String Username="username"; //用户名
		String Password="password"; //密码
		Class.forName(Driver).new Instance(); //加载数据可驱动
		Connection con=DriverManager.getConnection(URL,UserName,Password); //
		Microsoft SQL Server 3.0驱动(1个jar的那个): // 老紫竹完善
		String Driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"; //连接SQL数据库的方法
		String URL="jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=db_name"; //db_name为数据库名
		String Username="username"; //用户名
		String Password="password"; //密码
		Class.forName(Driver).new Instance(); //加载数据可驱动
		Connection con=DriverManager.getConnection(URL,UserName,Password); //
		Sysbase:
		String Driver="com.sybase.jdbc.SybDriver"; //驱动程序
		String URL="jdbc:Sysbase://localhost:5007/db_name"; //db_name为数据可名
		String Username="username"; //用户名
		String Password="password"; //密码
		Class.forName(Driver).newInstance();
		Connection con=DriverManager.getConnection(URL,Username,Password);
		Oracle(用thin模式):
		String Driver="oracle.jdbc.driver.OracleDriver"; //连接数据库的方法
		String URL="jdbc:oracle:thin:@loaclhost:1521:orcl"; //orcl为数据库的SID
		String Username="username"; //用户名
		String Password="password"; //密码
		Class.forName(Driver).newInstance(); //加载数据库驱动
		Connection con=DriverManager.getConnection(URL,Username,Password);
		PostgreSQL:
		String Driver="org.postgresql.Driver"; //连接数据库的方法
		String URL="jdbc:postgresql://localhost/db_name"; //db_name为数据可名
		String Username="username"; //用户名
		String Password="password"; //密码
		Class.forName(Driver).newInstance();
		Connection con=DriverManager.getConnection(URL,Username,Password);
		DB2：
		String Driver="com.ibm.db2.jdbc.app.DB2.Driver"; //连接具有DB2客户端的Provider实例
		//String Driver="com.ibm.db2.jdbc.net.DB2.Driver"; //连接不具有DB2客户端的Provider实例
		String URL="jdbc:db2://localhost:5000/db_name"; //db_name为数据可名
		String Username="username"; //用户名
		String Password="password"; //密码
		Class.forName(Driver).newInstance();
		Connection con=DriverManager.getConnection(URL,Username,Password);
		Informix:
		String Driver="com.informix.jdbc.IfxDriver";
		String URL="jdbc:Informix-sqli://localhost:1533/db_name:INFORMIXSER=myserver"; //db_name为数据可名
		String Username="username"; //用户名
		String Password="password"; //密码
		Class.forName(Driver).newInstance();
		Connection con=DriverManager.getConnection(URL,Username,Password);
		JDBC-ODBC:
		String Driver="sun.jdbc.odbc.JdbcOdbcDriver";
		String URL="jdbc:odbc:dbsource"; //dbsource为数据源名
		String Username="username"; //用户名
		String Password="password"; //密码
		Class.forName(Driver).newInstance();
		Connection con=DriverManager.getConnection(URL,Username,Password);
		/**
		* 把TXT转换为XML
		*/
		import java.io.BufferedReader;
		import java.io.BufferedWriter;
		import java.io.FileReader;
		import java.io.FileWriter;
		import java.util.StringTokenizer;
		public class TxtToXml {
		private String strTxtFileName;
		private String strXmlFileName;
		public TxtToXml() {
		strTxtFileName = new String();
		strXmlFileName = new String();
		}
		public void createXml(String strTxt, String strXml) {
		strTxtFileName = strTxt;
		strXmlFileName = strXml;
		String strTmp;
		try {
		BufferedReader inTxt = new BufferedReader(new FileReader(
		strTxtFileName));
		BufferedWriter outXml = new BufferedWriter(new FileWriter(
		strXmlFileName));
		outXml.write("<?xml version= \"1.0\" encoding=\"gb2312\"?>");
		outXml.newLine();
		outXml.write("<people>");
		while ((strTmp = inTxt.readLine()) != null) {
		String
		Tokenizer strToken = new StringTokenizer(strTmp, "，");
		String arrTmp[];
		arrTmp = new String[3];
		for (int i = 0; i < 3; i++)
		arrTmp[i] = new String("");
		int index = 0;
		outXml.newLine();
		outXml.write(" <students>");
		while (strToken.hasMoreElements()) {
		strTmp = (String) strToken.nextElement();
		strTmp = strTmp.trim();
		arrTmp[index++] = strTmp;
		}
		outXml.newLine();
		outXml.write(" <name>" + arrTmp[0] + "</name>");
		outXml.newLine();
		outXml.write(" <sex>" + arrTmp[1] + "</sex>");
		outXml.newLine();
		outXml.write(" <age>" + arrTmp[2] + "</age>");
		outXml.newLine();
		outXml.write(" </students>");
		}
		outXml.newLine();
		outXml.write("</people>");
		outXml.flush();
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		public static void main(String[] args) {
		String txtName = "testtxt.txt";
		String xmlName = "testxml.xml";
		TxtToXml thisClass = new TxtToXml();
		thisClass.createXml(txtName, xmlName);
		}
		}
		/**
		* 写入日志
		* filePath 日志文件的路径
		* code 要写入日志文件的内容
		*/
		public static boolean print(String filePath,String code) {
		try {
		File tofile=new File(filePath);
		FileWriter fw=new FileWriter(tofile,true);
		BufferedWriter bw=new BufferedWriter(fw);
		PrintWriter pw=new PrintWriter(bw);
		System.out.println(getDate()+":"+code);
		pw.println(getDate()+":"+code);
		pw.close();
		bw.close();
		fw.close();
		return true;
		} catch (IOException e) {
		return false;
		}
		}
		/**
		* 判断是不是合法手机
		* handset 手机号码
		*/
		public static boolean isHandset(String handset) {
		try {
		if(!handset.substring(0,1).equals("1")) {
		return false;
		}
		if (handset==null || handset.length()!=11) {
		return false;
		}
		String check = "^[0123456789]+$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(handset);
		boolean isMatched = matcher.matches();
		if(isMatched) {
		return true;
		} else {
		return false;
		}
		} catch (RuntimeException e) {
		return false;
		}
		}
		}
		字符串匹配的算法.
		public String getMaxMatch(String a,String b) {
		StringBuffer tmp = new StringBuffer();
		String maxString = "";
		int max = 0;
		int len = 0;
		char[] aArray = a.toCharArray();
		char[] bArray = b.toCharArray();
		int posA = 0;
		int posB = 0;
		while(posA<aArray.length-max) {
		posB = 0;
		while(posB<(bArray.length-max)) {
		if(aArray[posA]==bArray[posB]) {
		len = 1;
		tmp = new StringBuffer();
		tmp.append(aArray[posA]);
		while((posA+len<a
		Array.length)&&(posB+len<bArray.length)&&(aArray[posA+len]==bArray[posB+len])) {
		tmp.append(aArray[posA+len]);
		len++;
		}
		if(len>max) {
		max = len;
		maxString = tmp.toString();
		}
		}
		posB++;
		}
		posA++;
		}
		return maxString;
		}
		import java.text.DecimalFormat;
		import java.util.Arrays;
		/**
		* 时间计算工具类
		*/
		public class Time {
		/**
		* 时间字段常量，表示“秒”
		*/
		public final static int SECOND = 0;
		/**
		* 时间字段常量，表示“分”
		*/
		public final static int MINUTE = 1;
		/**
		* 时间字段常量，表示“时”
		*/
		public final static int HOUR = 2;
		/**
		* 时间字段常量，表示“天”
		*/
		public final static int DAY = 3;
		/**
		* 各常量允许的最大值
		*/
		private final int[] maxFields = { 59, 59, 23, Integer.MAX_VALUE - 1 };
		/**
		* 各常量允许的最小值
		*/
		private final int[] minFields = { 0, 0, 0, Integer.MIN_VALUE };
		/**
		* 默认的字符串格式时间分隔符
		*/
		private String timeSeparator = ":";
		/**
		* 时间数据容器
		*/
		private int[] fields = new int[4];
		/**
		* 无参构造，将各字段置为 0
		*/
		public Time() {
		this(0, 0, 0, 0);
		}
		/**
		* 使用时、分构造一个时间
		* @param hour 小时
		* @param minute 分钟
		*/
		public Time(int hour, int minute) {
		this(0, hour, minute, 0);
		}
		/**
		* 使用时、分、秒构造一个时间
		* @param hour 小时
		* @param minute 分钟
		* @param second 秒
		*/
		public Time(int hour, int minute, int second) {
		this(0, hour, minute, second);
		}
		/**
		* 使用一个字符串构造时间<br/>
		* Time time = new Time("14:22:23");
		* @param time 字符串格式的时间，默认采用“:”作为分隔符
		*/
		public Time(String time) {
		this(time, null);
		}
		/**
		* 使用天、时、分、秒构造时间，进行全字符的构造
		* @param day 天
		* @param hour 时
		* @param minute 分
		* @param second 秒
		*/
		public Time(int day, int hour, int minute, int second) {
		set(DAY, day);
		set(HOUR, hour);
		set(MINUTE, minute);
		set(SECOND, second);
		}
		/**
		* 使用一个字符串构造时间，指定分隔符<br/>
		* Time time = new Time("14-22-23",
		"-");
		* @param time 字符串格式的时间
		*/
		public Time(String time, String timeSeparator) {
		if(timeSeparator != null) {
		setTimeSeparator(timeSeparator);
		}
		String pattern = patternQuote(this.timeSeparator);
		String matcher = new StringBuffer()
		.append("\\d+?").append(pattern)
		.append("\\d+?").append(pattern)
		.append("\\d+?")
		.toString();
		if(!time.matches(matcher)) {
		throw new IllegalArgumentException(time + ", time format error, HH"
		+ this.timeSeparator + "mm" + this.timeSeparator + "ss");
		}
		String[] times = time.split(pattern);
		set(DAY, 0);
		set(HOUR, Integer.parseInt(times[0]));
		set(MINUTE, Integer.parseInt(times[1]));
		set(SECOND, Integer.parseInt(times[2]));
		}
		/**
		* 设置时间字段的值
		* @param field 时间字段常量
		* @param value 时间字段的值
		*/
		public void set(int field, int value) {
		if(value < minFields[field]) {
		throw new IllegalArgumentException(value +
		", time value must be positive.");
		}
		fields[field] = value % (maxFields[field] + 1);
		// 进行进位计算
		int carry = value / (maxFields[field] + 1);
		if(carry > 0) {
		int upFieldValue = get(field + 1);
		set(field + 1, upFieldValue + carry);
		}
		}
		/**
		* 获得时间字段的值
		* @param field 时间字段常量
		* @return 该时间字段的值
		*/
		public int get(int field) {
		if(field < 0 || field > fields.length - 1) {
		throw new IllegalArgumentException(field + ", field value is error.");
		}
		return fields[field];
		}
		/**
		* 将时间进行“加”运算，即加上一个时间
		* @param time 需要加的时间
		* @return 运算后的时间
		*/
		public Time addTime(Time time) {
		Time result = new Time();
		int up = 0; // 进位标志
		for (int i = 0; i < fields.length; i++) {
		int sum = fields[i] + time.fields[i] + up;
		up = sum / (maxFields[i] + 1);
		result.fields[i] = sum % (maxFields[i] + 1);
		}
		return result;
		}
		/**
		* 将时间进行“减”运算，即减去一个时间
		* @param time 需要减的时间
		* @return 运算后的时间
		*/
		public Time subtractTime(Time time) {
		Time result = new Time();
		int down = 0; // 退位标志
		for (int i = 0, k = fields.length - 1; i < k; i++) {
		int difference = fields[i] + down;
		if (difference >= time.fields[i]) {
		difference -= time.fields[i];
		down = 0;
		} else {
		difference += maxFields[i] + 1 - time.fields[i];
		down = -1;
		}
		result.fields[i] = difference;
		}
		result.fields[DAY] = fields[DAY] - time.fields[DAY] + down;
		return result;
		}
		/**
		* 获得时间字段的分隔符
		* @return
		*/
		public String getTimeSeparator() {
		return timeSeparator;
		}
		/**
		* 设置时间字段的分隔符（用于字符串格式的时间）
		* @param timeSeparator 分隔符字符串
		*/
		public void setTimeSeparator(String timeSeparator) {
		this.timeSeparator = timeSeparator;
		}
		/**
		* 正则表达式引用处理方法，源自 JDK @link java.util.regex.Pattern#quote(String)
		*/
		private String patternQuote(String s) {
		int slashEIndex = s.indexOf("\\E");
		if (slashEIndex == -1)
		return "\\Q" + s + "\\E";
		StringBuilder sb = new StringBuilder(s.length() * 2);
		sb.append("\\Q");
		slashEIndex = 0;
		int current = 0;
		while ((slashEIndex = s.indexOf("\\E", current)) != -1) {
		sb.append(s.substring(current, slashEIndex));
		current = slashEIndex + 2;
		sb.append("\\E\\\\E\\Q");
		}
		sb.append(s.substring(current, s.length()));
		sb.append("\\E");
		return sb.toString();
		}
		public String toString() {
		DecimalFormat df = new DecimalFormat("00");
		return new StringBuffer().append(fields[DAY]).append(", ")
		.append(df.format(fields[HOUR])).append(timeSeparator)
		.append(df.format(fields[MINUTE])).append(timeSeparator)
		.append(df.format(fields[SECOND]))
		.toString();
		}
		public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + Arrays.hashCode(fields);
		return result;
		}
		public boolean equals(Object obj) {
		if (this == obj)
		return true;
		if (obj == null)
		return false;
		if (getClass() != obj.getClass())
		return false;
		final Time other = (Time) obj;
		if (!Arrays.equals(fields, other.fields)) {
		return false;
		}
		return true;
		}
		}
		ISBN（国际标准书号）的校验
		public class Test {
		public static void main(String[] args) {
		System.out.println("9787302155638 " + ISBN.checkISBN("9787302155638"));
		System.out.println("7564105607 " + ISBN.checkISBN("7564105607"));
		System.out.println("730213880X " + ISBN.checkISBN("730213880X"));
		System.out.println("7302138800 " + ISBN.checkISBN("7302138800"));
		System.out.println("9790000000000 " + ISBN.checkISBN("9790000000000"
		));
		try {
		System.out.println(ISBN.checkISBN("9770000000000"));
		}catch(Exception e) {
		System.out.println("9770000000000 " + e.getMessage());
		}
		try {
		System.out.println(ISBN.checkISBN("123456545"));
		}catch(Exception e) {
		System.out.println("123456545 " + e.getMessage());
		}
		}
		}
		public final class ISBN {
		/**
		* 根据输入的ISBN号，检验ISBN的有效性。依据 GB/T 5795-2006 和 ISO 2108:2005 ISBN
		* 10位标准和13位标准实现（13位标准自2007年1月1日开始实行，在此之前采用10位标准）。
		*
		* @param String isbn：需要进行校验的ISBN字符串
		* @return true：所输入的ISBN校验正确；<br/> false：所输入的ISBN校验错误
		*/
		public static boolean checkISBN(String isbn) {
		int count = 0;
		int checkBitInt = 0;
		// 将ISBN数据全取大写字母
		//isbn = isbn.toUpperCase();
		char[] cs = isbn.toCharArray();
		switch (isbn.length()) {
		case 10:
		// ****************************************************************
		// 当ISBN为10位时，进行的校验，用于2007年1月1日前的出版物
		// 数据格式：从左至右前9位为ISBN数据，第10位为校验位
		// 校验方法：
		// (1) 从左至右将前9位数据从10开始至2进行编号，作为位权
		// (2) 将9位数据与各位位权进行加权，并求其9位和（称为加权和，记作M）
		// (3) 第10位校验位计算方法，校验位为C：
		// M + C ≡ 0 (mod 11)
		// C为10时，记作“X”
		// ****************************************************************
		// 取出前9位数字进行加权和计算
		for (int i = 0; i < 9; i++) {
		// 若前9位数据中有非数字字符，则抛出异常
		if (cs[i] < '0' || cs[i] > '9') {
		throw new ISBNFormatException("ISBN " + isbn +
		" 第 " + (i + 1) + " 位中出现非法字符 " + cs[i]);
		}
		int c = cs[i] - '0';
		// 求加权和
		count += c * (10 - i);
		}
		// 取出校验位数据0～9和X符合校验字符要求
		if (cs[9] >= '0' && cs[9] <= '9') {
		checkBitInt = cs[9] - '0';
		} else if (cs[9] == 'X' || cs[9] == 'x') {
		// 校验位中的“X”表示数据“10”
		checkBitInt = 10;
		} else {
		// 非0～9或X时抛出异常
		throw new ISBNFormatException("ISBN " + isbn +
		" 第 10 位中出现非法字符 " + cs[9]);
		}
		// 进行校验
		if ((count + checkBitInt) % 11 == 0) {
		return true; // 校验成功
		} else {
		return false; // 校验失败
		}
		case 13:
		// ****************************************************************
		// 当ISBN为13位时，进行的校验，用于2007年1月1日后的出版物
		// 数据格式：从左至右前12位为ISBN数据，第13位为校验位
		// 校验方法：
		// (1) 从左至右将前12位数的取其奇位数和和偶位数和
		// (2) 将偶位数和乘3，并其与奇位数和的和，得加权和
		// (3) 第13位校验位计算方法，校验位为C：
		// M + C ≡ 0 (mod 10)
		// ****************************************************************
		// ISBN为13位数据时，前3位目前只能是“978”（已实行）或“979”（暂未实行）
		if (!isbn.startsWith("978") && !isbn.startsWith("979")) {
		throw new ISBNFormatException("ISBN-13 格式不符合标准");
		}
		// 取出前12位数字进行加权和计算
		int countEven = 0;
		int countOdd = 0;
		for (int i = 0; i < 12; i++) {
		int c = cs[i] - '0';
		// 若前12位数据中有非数字字符，则抛出异常
		if (c < 0 || c > 9) {
		throw new ISBNFormatException("ISBN " + isbn +
		" 第 " + (i + 1) + " 位中出现非法字符 " + cs[i]);
		}
		// 分别计算奇位数和偶位数的和
		if ((i & 0x1) == 0) {
		countOdd += c;
		} else {
		countEven += c;
		}
		}
		// 求加权和
		count = countOdd + (countEven * 3);
		// 取出校验位数据
		if (cs[12] < '0' || cs[12] > '9') {
		// 校验位为非0~9字符时，抛出异常
		throw new ISBNFormatException("ISBN " + isbn
		+ " 第 13 位中出现非法字符 " + cs[12]);
		}
		checkBitInt = cs[12] - '0';
		// 进行校验
		if ((count + checkBitInt) % 10 == 0) {
		return true; // 校验成功
		} else {
		return false; // 校验失败
		}
		default:
		// ISBN为非10位或13位时抛出异常
		throw new ISBNFormatException("ISBN 格式不符合标准");
		}
		}
		}
		import java.util.ArrayList;
		import java.util.Iterator;
		import java.util.LinkedHashMap;
		import java.util.List;
		import java.util.Map;
		/**
		* JSON utility class
		*
		* @since 2008-04-21
		*/
		public class Json {
		// test
		public static void main(String[] args) {
		Json json1 = new Json();
		json1.add("totalCount", 2);
		json1.add("isTest", true);
		Json json_a = new Json();
		json_a.add("menuid", 1);
		json_a.add("menuname", "testmenu");
		json1.add("topics", json_a);
		Json json_b = new Json();
		json_b.add("menuid", 2);
		json_b.add("menuname", "testmenu");
		json1.add("topics", json_b);
		System.out.println(json1.toString());
		}
		private Map map = new LinkedHashMap();
		/**
		* 添加一个 JSON 属性，值为一个字符串，重复添加时产生数组<p/>
		*
		* add("name", "value");<br/>
		* 添加一个字符串，产生的 JSON 如：{"name":"value"}<p/>
		*
		* add("name", "value1");<br/>
		* add("name", "value2");<br/>
		* 添加两个同属性的字符串，产生的 JSON 如：{"name":["value1", "value2"]}<p/>
		*
		* @param key JSON 属性名
		* @param str 字符串格式的属性值
		*/
		public void add(String key, String value) {
		addElement(key, value);
		}
		public void add(String key, int num) {
		addElement(key, new Integer(num));
		}
		public void add(String key, boolean b) {
		addElement(key, new Boolean(b));
		}
		/**
		* 添加一个 JSON 属性，值为一个 JSON，重复添加时产生 JSON 数组<p/>
		*
		* Json json1 = new Json();<br/>
		* json1.add("name1", "value1");<br/>
		* json1.add("name2", "value2");<br/>
		* Json json = new Json();<br/>
		* json.add("message", json1);<br/>
		* 添加一个 JSON，产生的 JSON 如：{"message":{"name1":"value1", "name2":"value2"}}<p/>
		*
		* Json json1 = new Json();<br/>
		* json1.add("name1", "value1");<br/>
		* json1.add("name2", "value2");<br/>
		* Json json2 = new Json();<br/>
		* json2.add("name1", "value3");<br/>
		* json2.add("name2", "value4");<br/>
		* Json json = new Json();<br/>
		* json.add("message", json1);<br/>
		* json.add("message", json2);<br/>
		* 添加两个同属性的 JSON，产生的 JSON 如：{"message":[{"name1":"value1", "name2":"value2"}, {"name1":"value3", "name2":"value4"}]}<p/>
		*
		* @param key JSON 属性名
		* @param json JSON 格式的属性值
		*/
		public void add(String key, Json json) {
		addElement(key, json);
		}
		public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		int k = 0;
		for (Iterator i = map.keySet().iterator(); i.hasNext();) {
		String key = (String)(i.next());
		Object obj = map.get(key);
		if (k > 0) {
		sb.append(",");
		}
		appendKey(sb, key);
		if (obj instanceof String) {
		appendString(sb, (String)obj);
		} else if (obj instanceof List) {
		appendList(sb, (List)obj);
		} else if (obj instanceof Json) {
		appendJson(sb, (Json)obj);
		} else {
		appendOther(sb, obj);
		}
		k++;
		}
		sb.append("}");
		return sb.toString();
		}
		private void addElement(String key, Object obj) {
		if (!map.containsKey(key)) {
		if(obj instanceof Json) {
		List list = new ArrayList();
		list.add(obj);
		map.put(key, list);
		} else {
		map.put(key, obj);
		}
		return;
		}
		Object o = map.remove(key);
		if (o instanceof List) {
		((List)o).add(obj);
		map.put(key, o);
		return;
		}
		// o is a String
		List list = new ArrayList();
		list.add(o);
		list.add(obj);
		map.put(key, list);
		}
		/**
		* Append JSON property name
		*
		* @param sb
		* @param key
		*/
		private void appendKey(StringBuilder sb, String key) {
		sb.append("\"").append(key).append("\":");
		}
		/**
		* Append JSON property value that is a String
		*
		* @param sb
		* @param str
		*/
		private void appendString(StringBuilder sb, String str) {
		sb.append("\"").append(str).append("\"");
		}
		/**
		* Append JSON property value that is a Integer
		*
		* @param sb
		* @param num
		*/
		private void appendOther(StringBuilder sb, Object obj) {
		sb.append(obj);
		}
		/**
		* Append JSON property value that is a List
		*
		* @param sb
		* @param list
		*/
		private void appendList(StringBuilder sb, List list) {
		sb.append("[");
		for (int j = 0, m = list.size(); j < m; j++) {
		if (j > 0) {
		sb.append(",");
		}
		Object obj = list.get(j);
		if (obj instanceof String) {
		appendString(sb, (String)obj);
		} else if (obj instanceof Json) {
		appendJson(sb, (Json)obj);
		} else {
		appendOther(sb, obj);
		}
		}
		sb.append("]");
		}
		/**
		* Append JSON property value that is a JSON
		*
		* @param sb
		* @param json
		*/
		private void appendJson(StringBuilder sb, Json json) {
		sb.append(json.toString());
		}
		}
		/**
		* 从指定的字符串中提取Email
		* content 指定的字符串
		*/
		public static String parse(String content) {
		String email = null;
		if (content==null || content.length()<1) {
		return email;
		}
		//找出含有@
		int beginPos;
		int i;
		String token = "@";
		String preHalf="";
		String sufHalf = "";
		beginPos = content.indexOf(token);
		if (beginPos>-1)
		{
		//前项扫描
		String s = null;
		i= beginPos;
		while(i>0) {
		s = content.substring(i-1,i);
		if (isLetter(s))
		preHalf = s+preHalf;
		else
		break;
		i--;
		}
		//后项扫描
		i= beginPos+1;
		while( i<content.length()) {
		s = content.substring(i,i+1);
		if (isLetter(s))
		sufHalf = sufHalf +s;
		else
		break;
		i++;
		}
		//判断合法性
		email = preHalf + "@" + sufHalf;
		if (isEmail(email)) {
		return email;
		}
		}
		return null;
		}
		/**
		* 判断是不是合法Email
		* email Email地址
		*/
		public static boolean isEmail(String email) {
		try {
		if (email==null || email.length()<1 || email.length()>256) {
		return false;
		}
		String check = "^([0-9a-zA-Z]+[_.0-9a-zA-Z-]+)@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2,3})$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		boolean isMatched = matcher.matches();
		if(isMatched) {
		return true;
		} else {
		return false;
		}
		} catch (RuntimeException e) {
		return false;
		}
		}
		/**
		* 判断是不是合法字符
		* c 要判断的字符
		*/
		public static boolean isLetter(String c) {
		boolean result = false;
		if (c==null || c.length()<0) {
		return false;
		}
		//a-z
		if (c.compareToIgnoreCase("a")>=0 && c.compareToIgnoreCase("z")<=0) {
		return true;
		}
		//0-9
		if (c.compareToIgnoreCase("0")>=0 && c.compareToIgnoreCase("9")<=0) {
		return true;
		}
		//. - _
		if (c.equals(".") || c.equals("-") || c.equals("_") ) {
		return true;
		}
		return result;
		}
		/**
		* 列出某文件夹及其子文件夹下面的文件，并可根据扩展名过滤
		*
		* @param path
		*/
		public static void list(File path)
		{
		if (!path.exists())
		{
		System.out.println("文件名称不存在!");
		}
		else
		{
		if (path.isFile())
		{
		if (path.getName().toLowerCase().endsWith(".pdf")
		|| path.getName().toLowerCase().endsWith(".doc")
		|| path.getName().toLowerCase().endsWith(".html")
		|| path.getName().toLowerCase().endsWith(".htm"))
		{
		System.out.println(path);
		System.out.println(path.getName());
		}
		}
		else
		{
		File[] files = path.listFiles();
		for (int i = 0; i < files.length; i++)
		{
		list(files[i]);
		}
		}
		}
		}
		/**
		* 拷贝一个目录或者文件到指定路径下
		*
		* @param source
		* @param target
		*/
		public static void copy(File source, File target)
		{
		File tarpath = new File(target, source.getName());
		if (source.isDirectory())
		{
		tarpath.mkdir();
		File[] dir = source.listFiles();
		for (int i = 0; i < dir.length; i++)
		{
		copy(dir[i], tarpath);
		}
		}
		else
		{
		try
		{
		InputStream is = new FileInputStream(source);
		OutputStream os = new FileOutputStream(tarpath);
		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = is.read(buf)) != -1)
		{
		os.write(buf, 0, len);
		}
		is.close();
		os.close();
		}
		catch (FileNotFoundException e)
		{
		e.printStackTrace();
		}
		catch (IOException e)
		{
		e.printStackTrace();
		}
		}
		}
		Java日期格式化及其使用例子
		1 SimpleDateFormat担当重任,怎样格式化都行
		import java.util.Date;
		import java.text.SimpleDateFormat;
		public class Demo
		{
		public static void main(String[] args)
		{
		Date now=new Date();
		SimpleDateFormat f=newSimpleDateFormat("今天是"+"yyyy年MM月dd日 E kk点mm分");
		System.out.println(f.format(now));
		f=new SimpleDateFormat("a hh点mm分ss秒");
		System.out.println(f.format(now));
		}
		}
		2 从字符串到日期类型的转换：
		import java.util.Date;
		import java.text.SimpleDateFormat;
		import java.util.GregorianCalendar;
		import java.text.*;
		publicclass Demo
		{
		public static void main(String[] args)
		{
		String strDate="2005年04月22日";
		//注意：SimpleDateFormat构造函数的样式与strDate的样式必须相符
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
		//必须捕获异常
		try
		{
		Date date=simpleDateFormat.parse(strDate);
		System.out.println(date);
		}
		catch(ParseException px)
		{
		px.printStackTrace();
		}
		}
		}
		3 将毫秒数换转成日期类型
		import java.util.Date;
		import java.text.SimpleDateFormat;
		import java.util.GregorianCalendar;
		import java.text.*;
		public class Demo
		{
		public static void main(String[] args)
		{
		long now=System.currentTimeMillis();
		System.out.println("毫秒数："+now);
		Date dNow=new Date(now);
		System.out.println("日期类型："+dNow);
		}
		}
		这3例源自http://blog.csdn.net/zhoujian2003/archive/2005/04/22/358363.aspx
		4 获取系统时期和时间，转换成SQL格式后更新到数据库
		(http://blog.csdn.net/netrope/archive/2005/11/19/532729.aspx)
		java.util.Date d=new java.util.Date(); //获取当前系统的时间
		//格式化日期
		new java.text.SimpleDateFormat s= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = s.format(d); //转为字符串
		使用RS更新数据库，仍然要用rs.updateString，而不是rs.up
		dateDade。
		rs.updateString("regtime",dateStr); //regtime字段为datetime类型的
		下面两例源自 http://blog.csdn.net/kingter520/archive/2004/10/27/155435.aspx
		5 按本地时区输出当前日期
		Date myDate = new Date();
		System.out.println(myDate.toLocaleString());
		输出结果为：
		2003-5-30
		6 如何格式化小数
		DecimalFormat df = new DecimalFormat(",###.00");
		double aNumber = 33665448856.6568975;
		String result = df.format(aNumber);
		Sytem. out.println(result);
		输出结果为：
		33,665,448,856.66
		其他：获取毫秒时间 System.currentTimeMillis();
		7 在数据库里的日期只以年-月-日的方式输出
		(http://blog.csdn.net/zzsxvzzsxv/archive/2007/08/27/1761004.aspx)
		定义日期格式：SimpleDateFormat sdf = new SimpleDateFormat(yy-MM-dd);
		sql语句为：String sqlStr = "select bookDate from roomBook where bookDate between '2007-4-10' and '2007-4-25'";
		输出：
		System.out.println(df.format(rs.getDate("bookDate")));
		Java中的鼠标和键盘事件
		1、使用MouseListener借口处理鼠标事件
		鼠标事件有5种：按下鼠标键，释放鼠标键，点击鼠标键，鼠标进入和鼠标退出
		鼠标事件类型是MouseEvent，主要方法有：
		getX(),getY() 获取鼠标位置
		getModifiers() 获取鼠标左键或者右键
		getClickCount() 获取鼠标被点击的次数
		getSource() 获取鼠标发生的事件源
		事件源获得监视器的方法是addMouseListener()，移去监视器的方法是removeMouseListener()
		处理事件源发生的时间的事件的接口是MouseListener 接口中有如下的方法
		mousePressed(MouseEvent) 负责处理鼠标按下事件
		mouseReleased(MouseEvent) 负责处理鼠标释放事件
		mouseEntered(MouseEvent) 负责处理鼠标进入容器事件
		mouseExited(MouseEvent) 负责处理鼠标离开事件
		mouseClicked(MouseEvent) 负责处理点击事件
		2、使用MouseMotionListener接口处理鼠标事件
		事件源发生的鼠标事件有2种：拖动鼠标和鼠标移动
		鼠标事件的类型是MouseEvent
		事件源获得监视器的方法是addMouseMotionListener()
		处理事件源发生的事件的接口是MouseMotionListener 接口中有如下的方法
		mouseDragged() 负责处理鼠标拖动事件
		mouseMoved() 负责处理鼠标移动事件
		3、控制鼠标的指针形状
		setCursor(Cursor.getPreddfinedCursor(Cursor.鼠标形状定义)) 鼠标形状定义见（书 P 210）
		4、键盘事件
		键盘事件源使用addKeyListener 方法获得监视器
		键盘事件的接口是KeyListener 接口中有3个方法
		public void keyPressed(KeyEvent e) 按下键盘按键
		public void keyReleased(KeyEvent e) 释放键盘按键
		public class IsChineseOrEnglish {
		// GENERAL_PUNCTUATION 判断中文的“号
		// CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
		// HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
		public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
		|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
		|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
		|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
		|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
		|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS){
		return true;
		}
		return false;
		}
		public static void isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
		char c = ch[i];
		if(isChinese(c)==true){
		System.out.println(isChinese(c));
		return;
		}else{
		System.out.println(isChinese(c));
		return ;
		}
		}
		}
		public static void main(String[] args){
		isChinese("zhongguo");
		isChinese("中国");
		}
		}
		MD5和一个可逆加密算法相接合的加密和解密程序
		比较简单。
		[code={0}]
		import java.security.MessageDigest;
		/**
		*先通过MD5加密之后，再来一次可逆的加密。
		*顺序可以调整，可以选择先用可逆加密，然后再用MD5加密
		*/
		public class MD5andKL{
		//MD5加码。32位
		public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
		md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
		System.out.println(e.toString());
		e.printStackTrace();
		return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++)
		byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
		int val = ((int) md5Bytes[i]) & 0xff;
		if (val < 16)
		hexValue.append("0");
		hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
		}
		//可逆的加密算法
		public static String KL(String inStr){
		//String s = new String(inStr);
		char[] a = inStr.toCharArray();
		for (int i = 0;i <a.length;i++) {
		a[i] = (char)(a[i]^'t');
		}
		String s=new String(a);
		return s;
		}
		//加密后解密
		public static String JM(String inStr){
		char[] a=inStr.toCharArray();
		for (int i = 0;i <a.length;i++) {
		a[i]= (char)(a[i]^'t');
		}
		String k=new String(a);
		return k;
		}
		//测试主函数
		public static void main (String args[]){
		String s = new String("admin");
		System.out.println("原始："+s);
		System.out.println("MD5后："+MD5(s));
		System.out.println("MD5后再加密："+KL(MD5(s)));
		System.out.println("解密为MD5后的："+JM(KL(MD5(s))));
		}
		}
		[/code]
		/**
		* 取得服务器当前的各种具体时间
		* 回车：日期时间
		*/
		import java.util.*;
		public class GetNowDate{
		Calendar calendar = null;
		public G
		etNowDate(){
		calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		}
		public int getYear(){
		return calendar.get(Calendar.YEAR);
		}
		public int getMonth(){
		return 1 + calendar.get(Calendar.MONTH);
		}
		public int getDay(){
		return calendar.get(Calendar.DAY_OF_MONTH);
		}
		public int getHour(){
		return calendar.get(Calendar.HOUR_OF_DAY);
		}
		public int getMinute(){
		return calendar.get(Calendar.MINUTE);
		}
		public int getSecond(){
		return calendar.get(Calendar.SECOND);
		}
		public String getDate(){
		return getMonth()+"/"+getDay()+"/"+getYear();
		}
		public String getTime(){
		return getHour()+":"+getMinute()+":"+getSecond();
		}
		public String getDate2(){
		String yyyy="0000", mm="00", dd="00";
		yyyy = yyyy + getYear();
		mm = mm + getMonth();
		dd = dd + getDay();
		yyyy = yyyy.substring(yyyy.length()-4);
		mm = mm.substring(mm.length()-2);
		dd = dd.substring(dd.length()-2);
		return yyyy + "/" + mm + "/" + dd;
		}
		public String getTime2(){
		String hh="00", mm="00", ss="00";
		hh = hh + getHour();
		mm = mm + getMinute();
		ss = ss + getSecond();
		hh = hh.substring(hh.length()-2, hh.length());
		mm = mm.substring(mm.length()-2, mm.length());
		ss = ss.substring(ss.length()-2, ss.length());
		return hh + ":" + mm + ":" + ss;
		}
		}
		/**
		* 用半角的特殊符号代替全角的特殊符号
		* 防止特殊字符在传输参数时出现错误
		*
		*/
		public class ReplaceStrE{
		public static String rightToError(String ss){
		String strs;
		String strs1;
		String strs2;
		String strs3;
		String strs4;
		try{
		strs = ss.replace('＃','#');
		}
		catch(Exception ex){
		return ss;
		}
		try{
		strs1 = strs.replace('＂','"');
		}
		catch(Exception ex){
		return strs;
		}
		try{
		strs2 = strs1.replace(' ','&');
		}
		catch(Exception ex){
		return strs1;
		}
		try{
		strs3 = strs2.replace('＋','+');
		}
		catch(Exception ex){
		return strs2;
		}
		try{
		strs4 = strs3.replace('＇','\'');
		}
		catch(Exception ex){
		return ss;
		}
		return strs4;
		}
		}
		/**
		* Big5字与Unicode的互换
		* 转换后的正常字型
		*/
		import java.io.*;
		public class MyUtil{
		public static String big5ToUnicode(String s){
		try{
		return new String(s.getBytes("ISO8859_1"), "Big5");
		}
		catch (UnsupportedEncodingException uee){
		return s;
		}
		}
		public static String UnicodeTobig5(String s){
		try{
		return new String(s.getBytes("Big5"), "ISO8859_1");
		}
		catch (UnsupportedEncodingException uee){
		return s;
		}
		}
		public static String toHexString(String s){
		String str="";
		for (int i=0; i<s.length(); i++){
		int ch=(int)s.charAt(i);
		String s4="0000"+Integer.toHexString(ch);
		str=str+s4.substring(s4.length()-4)+" ";
		}
		return str;
		}
		}
		import java.io.*;
		import java.util.ArrayList;
		import java.util.List;
		public class FileCopy {
		private String message = "";
		public String getMessage() {
		return message;
		}
		public void setMessage(String message) {
		this.message = message;
		}
		/**
		* 将源文件拷贝到目标文件
		*
		* @param src
		* 写源文件地址，需文件名
		* @param des
		* 写目标文件地址，无需文件名
		*/
		public boolean copyFile(String src, String des) {
		File srcFile = new File(src);
		File desDir = new File(des);
		File desFile = new File(des + "/" + srcFile.getName());
		// 判断源文件是否存在
		if (!srcFile.exists()) {
		this.setMessage("源文件不存在！");
		return false;
		} else if (!srcFile.isFile()) {
		this.setMessage("源文件格式错！");
		return false;
		}
		// 判断源文件是否存在
		if (!desDir.exists()) {
		this.setMessage("目标目录不存在！");
		return false;
		} else if (!desDir.isDirectory()) {
		this.setMessage("不是有效的目录！");
		return false;
		}
		BufferedReader reader = null;
		BufferedWriter writer = null;
		String str;
		try {
		reader = new BufferedReader(new FileReader(srcFile));
		writer = new BufferedWriter(new FileWriter(desFile));
		// 判断目标文件是否存在及其格式，不存在就创建，格式不对先删除，存在就替代
		if (!desFile.exists() || !desFile.isFile()) {
		if (desFile.exists()) {
		desFile.delete();
		}
		desFile.createNewFile();
		}
		// 从源文件读取数据，并写入目标文件
		str = reader.readLine();
		while (str != null) {
		writer.write(str);
		writer.newLine();
		str = reader.readLine();
		}
		} catch (IOException e) {
		this.setMessage(e.getMessage());
		return false;
		} finally {
		if (reader != null) {
		try {
		reader.close();
		} catch (IOException e) {
		this.setMessage(e.getMessage());
		}
		}
		if (writer != null) {
		try {
		writer.close();
		} catch (IOException e) {
		this.setMessage(e.getMessage());
		}
		}
		}
		return true;
		}
		private List fileList = new ArrayList();
		/**
		* 列出所有文件
		* @param srcFile
		*/
		private void file(File srcFile) {
		if (srcFile.isDirectory()) {
		String[] files = srcFile.list();
		for (int i = 0; i < files.length; i++) {
		File f = new File(srcFile + "/" + files[i]);
		// 如果是文件加入列表，否则递归列出
		if (f.isFile()) {
		fileList.add(f);
		} else
		file(f);
		}
		}else this.setMessage(srcFile.getAbsolutePath()+"不是目录");
		}
		/**
		* 建立目录
		* @param des
		* @throws IOException
		*/private void mkdir(File des) {
		if (!des.exists() || !des.isDirectory()) {
		mkdir(des.getParentFile());
		if (des.exists()) {
		des.delete();
		}
		des.mkdir();
		}
		}
		/**
		* 复制目录 将源目录下所有文件拷贝到目标目录下
		* @param src 源目录
		* @param des 目标目录
		*/
		public boolean copyDir(String src, String des) {
		File srcFile = new File(src);
		if (!srcFile.exists()) {
		this.setMessage("源目录不存在！");
		return false;
		} else if (!srcFile.isDirectory()) {
		this.setMessage(src+"不是有效的目录！");
		return false;
		}
		file(srcFile);
		for (int i = 0; i < fileList.size(); i++) {
		String srcName = ((File) fileList.get(i)).getPath();
		String desName = srcName.substring(src.length(), srcName.length());
		desName = des + desName;
		File dir=new File(desName).getParentFile();
		mkdir(dir);
		if(!copyFile(srcName, dir.getPath())){
		return false;
		}
		}
		return true;
		}
		public static void main(String[] args) {
		FileCopy t = new FileCopy();
		System.out.println(t.copyFile("D:/aaa.txt","E:"));
		String src="D:/asdf";
		String des="E:/adf";
		System.out.println(t.copyDir(src, des));
		System.out.println(t.getMessage());
		}
		}
		/**
		* Description: 获取GMT8时间
		* @return 将当前时间转换为GMT8时区后的Date
		*/
		public static Date getGMT8Time(){
		Date gmt8 = null;
		try {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"),Locale.CHINESE);
		Calendar day = Calendar.getInstance();
		day.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		day.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		day.set(Calendar.DATE, cal.get(Calendar.DATE));
		day.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
		day.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
		day.set(Calendar.SECOND, cal.get(Calendar.SECOND));
		gmt8 = day.getTime();
		} catch (Exception e) {
		System.out.println("获取GMT8时间 getGMT8Time() error !");
		e.printStackTrace();
		gmt8 = null;
		}
		return gmt8;
		}
		import java.io.File;
		import java.io.FileInputStream;
		import java.io.FileNotFoundException;
		import java.io.IOException;
		import java.util.Properties;
		/**
		* ReadProperties.java
		* Description: 读取操作属性配置文件
		* @author li.b
		* @version 2.0
		* Jun 26, 2008
		*/
		public class ReadProperties {
		/**
		* Description: 获取属性配置文件
		* @param path 资源文件路径
		* @return Properties Object
		* @throws FileNotFoundException
		* @throws IOException
		*/
		public static Properties getProperties(String path) throws FileNotFoundException, IOException{
		Properties props = null;
		File file = new File(path);
		if(file.exists() && file.isFile()){
		props = new Properties();
		props.load(new FileInputStream(file));
		}else{
		System.out.println(file.toString() + "不存在！");
		}
		return props;
		}
		/**
		* Description: 从属性文件获取值
		* @param props Properties Object
		* @param key
		* @return 通过key匹配到的value
		*/
		public static String getValue(Properties props,String key,String encod){
		String result = "";
		String en = "";
		String localEN = System.getProperty("file.encoding");
		if(encod !=null && !encod.equals("") ){
		en = encod;
		}else{
		en = localEN;
		}
		try {
		key = new String(key.getBytes(en),"ISO-8859-1");
		result = props.getProperty(key);
		if(!result.equals("")){
		result = new String(result.getBytes("ISO-8859-1"),en);
		}
		} catch (Exception e) {
		}finally{
		if(result == null)result = "";
		return result;
		}
		}
		public static String getValue(Properties props,String key){
		return getValue(props, key, "");
		}
		}
		import java.lang.reflect.Array;
		import java.util.Date;
		public class TestCast {
		/**
		* @param args
		*/
		//public static void main(String[] args) {
		/** *//**
		*
		* 一般情况下数组和数组是不能直接进行转换的,例如:
		* Object[] t1={"1","2"};
		* String[] t2=(String[])t1;//这里会出现转换错误
		*
		* 下面提供了一种方式进行转换
		*/
		//1.0测试一般基础类
		/* Object[] t1={"1","2","3","4","5"};
		String[] m1=(String[])TestCast.cast(t1,String.class);
		for(int i=0;i<m1.length;i++)
		System.out.println(m1[i]);
		//2.0测试复杂对象
		Object[] t2={new Date(1000),new Date(2000)};
		Date[] m2=(Date[])TestCast.cast(t2,Date.class);
		for(int i=0;i<m2.length;i++)
		System.out.println(m2[i].toString());*/
		// }
		/** *//**
		* 将数组array转换成clss代表的类型后返回
		* @param array 需要转换的数组
		* @param clss 要转换成的类型
		*
		@return 转换后的数组
		*/
		public static Object cast(Object array,Class clss){
		if(null==clss)
		throw new IllegalArgumentException("argument clss cannot be null");
		if(null==array)
		throw new IllegalArgumentException("argument array cannot be null");
		if(false==array.getClass().isArray())
		throw new IllegalArgumentException("argument array must be array");
		Object[] src=(Object[])array;
		Object[] dest=(Object[])Array.newInstance(clss, src.length);
		System.arraycopy(src, 0, dest, 0, src.length);
		return dest;
		}
		}
		处理特殊符号的工具类，这个类是从sturts源码里挖出来的
		/**
		* DealingCharacter.java
		* Description:
		* @author li.b
		* @version 2.0
		* Jun 27, 2008
		*/
		public class DealingCharacter {
		/**
		* Description: 转译特殊符号标签
		* @param value 需要处理的字符串
		* @return
		*/
		public static String filter(String value)
		{
		if(value == null || value.length() == 0)
		return value;
		StringBuffer result = null;
		String filtered = null;
		for(int i = 0; i < value.length(); i++)
		{
		filtered = null;
		switch(value.charAt(i))
		{
		case 60: // '<'
		filtered = "<";
		break;
		case 62: // '>'
		filtered = ">";
		break;
		case 38: // '&'
		filtered = "&";
		break;
		case 34: // '"'
		filtered = """;
		break;
		case 39: // '\''
		filtered = "'";
		break;
		}
		if(result == null)
		{
		if(filtered != null)
		{
		result = new StringBuffer(value.length() + 50);
		if(i > 0)
		result.append(value.substring(0, i));
		result.append(filtered);
		}
		} else
		if(filtered == null)
		result.append(value.charAt(i));
		else
		result.append(filtered);
		}
		return result != null ? result.toString() : value;
		}
		public static void main(String[] args) {
		System.out.println(DealingCharacter.filter("<HTML>sdfasfas</HTML>"));
		}
		}
		小标签
		import java.io.IOException;
		import java.util.List;
		import javax.servlet.jsp.JspException;
		import javax.servlet.jsp.tagext.TagSupport;
		import com.formcontent.show.ShowFormTypeOperateDb;
		import c
		om.forum.hibernatePrj.Space;
		public class OutPrintForumType extends TagSupport{
		public int doStartTag() throws JspException
		{
		String printStr="";
		ShowFormTypeOperateDb showtype=new ShowFormTypeOperateDb();
		List list=showtype.getForumType();
		if(list!=null&&list.size()>0)
		{
		for(int i=0;i <list.size();i++)
		{
		Space space=(Space)list.get(i);
		if(space!=null)
		{
		printStr+=" <tr> <td>"+" <div align='left' class='TypeCss'>"+
		space.getSpaceName()+" "+space.getSpaceDescription()+" <br/>目前登陆总人数:"+i+" 人访问数:"+i+"人 </div> </td> </tr>"
		+" <tr> <td> </td> </tr>";
		}
		}
		}
		try {
		pageContext.getOut().write(printStr);
		} catch (IOException e) {
		e.printStackTrace();
		}
		return super.doStartTag();
		}
		}
		正则表达式用于字符串处理、表单验证等场合，实用高效。现将一些常用的表达式收集于此，以备不时之需。
		匹配中文字符的正则表达式： [\u4e00-\u9fa5]
		评注：匹配中文还真是个头疼的事，有了这个表达式就好办了
		匹配双字节字符(包括汉字在内)：[^\x00-\xff]
		评注：可以用来计算字符串的长度（一个双字节字符长度计2，ASCII字符计1）
		匹配空白行的正则表达式：\n\s*\r
		评注：可以用来删除空白行
		匹配HTML标记的正则表达式： <(\S*?)[^>]*>.*? </\1> | <.*? />
		评注：网上流传的版本太糟糕，上面这个也仅仅能匹配部分，对于复杂的嵌套标记依旧无能为力
		匹配首尾空白字符的正则表达式：^\s* |\s*$
		评注：可以用来删除行首行尾的空白字符(包括空格、制表符、换页符等等)，非常有用的表达式
		匹配Email地址的正则表达式：\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*
		评注：表单验证时很实用
		匹配网址URL的正则表达式：[a-zA-z]+://[^\s]*
		评注：网上流传的版本功能很有限，上面这个基本可以满足需求
		匹配帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)：^[a-zA-Z][a-zA-Z0-9_]{4,15}$
		评注：表单验证时很实用
		匹配国内电话号码：\d{3}-\d{8} |\d{4}-\d{7}
		评注：匹配形式如 0511-4405222 或 021-87888822
		匹配腾讯QQ号：[1-9][0-9]{4,}
		评注：腾讯QQ号从10000开始
		匹配中国邮政编码：[1-9]\d{5}(?!\d)
		评注：中国邮政编码为6位数字
		匹配身份证：\d{15} |\d{18}
		评注：中国的身份证为15位或18位
		匹配ip地址：\d+\.\d+\.\d+\.\d+
		评注：提取ip地址时有用
		匹配特定数字：
		^[1-9]\d*$　 　 //匹配正整数
		^-[1-9]\d*$ 　 //匹配负整数
		^-?[1-9]\d*$　　 //匹配整数
		^[1-9]\d* |0$　 //匹配非负整数（正整数 + 0）
		^-[1-9]\d* |0$　　 //匹配非正整数（负整数 + 0）
		^[1-9]\d*\.\d* |0\.\d*[1-9]\d*$　　 //匹配正浮点数
		^-([1-9]\d*\.\d* |0\.\d*[1-9]\d*)$　 //匹配负
		浮点数
		^-?([1-9]\d*\.\d* |0\.\d*[1-9]\d* |0?\.0+ |0)$　 //匹配浮点数
		^[1-9]\d*\.\d* |0\.\d*[1-9]\d* |0?\.0+ |0$　　 //匹配非负浮点数（正浮点数 + 0）
		^(-([1-9]\d*\.\d* |0\.\d*[1-9]\d*)) |0?\.0+ |0$　　//匹配非正浮点数（负浮点数 + 0）
		评注：处理大量数据时有用，具体应用时注意修正
		匹配特定字符串：
		^[A-Za-z]+$　　//匹配由26个英文字母组成的字符串
		^[A-Z]+$　　//匹配由26个英文字母的大写组成的字符串
		^[a-z]+$　　//匹配由26个英文字母的小写组成的字符串
		^[A-Za-z0-9]+$　　//匹配由数字和26个英文字母组成的字符串
		^\w+$　　//匹配由数字、26个英文字母或者下划线组成的字符串
		/**
		* 将数组转成字符串 在调试或记录日志时用到
		*
		* @param array
		* @return
		*/
		public static String byte2string(byte[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("Length " + array.length + " Content ");
		for (int i = 0; i < leng; i++) {
		sb = sb.append(String.format("%02X", array[i])).append(":");
		}
		int ind = sb.lastIndexOf(":");
		sb.delete(ind, ind + 1);
		return sb.toString();
		}
		import java.util.Arrays;
		import java.util.Random;
		/**
		* <code>RandomUtil</code> - Random Tool Class.
		* @author SageZk
		* @version 1.0
		*/
		public class RandomUtil {
		private RandomUtil() {}
		private static Random rnd = null;
		/**
		* 初始化随机数发生器。
		*/
		private static void initRnd() {
		if (rnd == null) rnd = new Random();
		}
		/**
		* 计算并返回无重复值的以 <code>min</code> 为下限 <code>max</code> 为上限的随机整数数组。
		* @param min 随机整数下限（包含）
		* @param max 随机整数上限（包含）
		* @param len 结果数组长度
		* @return 结果数组
		*/
		public static int[] getLotteryArray(int min, int max, int len) {
		//参数校验及性能优化
		if (len < 0) return null; //长度小于 0 的数组不存在
		if (len == 0) return new int[0]; //返回长度为 0 的数组
		if (min > max) { //校正参数 min max
		int t = min;
		min = max;
		max = t;
		}
		final int LEN = max - min + 1; //种子个数
		if (len > LEN) return null; //如果出现 35 选 36 的情况就返回 null
		//计算无重复值随机数组
		initRnd(); //初始化随机数发生器
		int[] seed = new int[LEN]; //种子数组
		for (int i = 0, n = min; i < LEN;) seed[i++] = n++; //初始化种子数组
		for (int i = 0, j = 0, t = 0; i < len; ++i) {
		j = rnd.nextInt(LEN - i) + i;
		t = seed[i];
		seed[i] = seed[j];
		seed[j] = t;
		}
		return Arrays.copyOf(seed,
		len); //注意：copyOf 需要 JRE1.6
		}
		//Unit Testing
		public static void main(String[] args) {
		final int N = 10000; //测试次数
		for (int i = 0; i < N; ++i) {
		int[] la = RandomUtil.getLotteryArray(1, 35, 7);
		if (la == null) continue;
		for (int v : la) System.out.printf("%0$02d ", v);
		System.out.println();
		}
		}
		}
		/*
		操作属性文件，可以为我们的程序带来更方便的移植性，下面是一个示例，可以读、写、更改属性
		读采用了两种方式，一种是采用Properties类，另外一种是采用资源绑定类ResourceBundle类，
		下面是源程序，里面有详细的注释：
		*/
		import java.io.FileInputStream;
		import java.io.FileOutputStream;
		import java.io.InputStream;
		import java.util.Properties;
		import java.util.ResourceBundle;
		/**
		*对属性文件（xx.properties）的操作
		*注：属性文件一定要放在当前工程的根目录下，也就是放在与src目录在同一个目录下（我的JDevelop
		*是这样的）
		*/
		public class OperatePropertiesFile {
		public OperatePropertiesFile() {
		}
		/**
		*采用Properties类取得属性文件对应值
		*@parampropertiesFileNameproperties文件名，如a.properties
		*@parampropertyName属性名
		*@return根据属性名得到的属性值，如没有返回""
		*/
		public static String getValueByPropertyName(String propertiesFileName,String propertyName) {
		String s="";
		Properties p=new Properties();//加载属性文件读取类
		FileInputStream in;
		try {
		//propertiesFileName如test.properties
		in = new FileInputStream(propertiesFileName);//以流的形式读入属性文件
		p.load(in);//属性文件将该流加入的可被读取的属性中
		in.close();//读完了关闭
		s=p.getProperty(propertyName);//取得对应的属性值
		} catch (Exception e) {
		e.printStackTrace();
		}
		return s;
		}
		/**
		*采用ResourceBundel类取得属性文件对应值，这个只能够读取，不可以更改及写新的属性
		*@parampropertiesFileNameWithoutPostfixproperties文件名，不带后缀
		*@parampropertyName属性名
		*@return根据属性名得到的属性值，如没有返回""
		*/
		public static String getValueByPropertyName_(String propertiesFileNameWithoutPostfix,String propertyName) {
		String s="";
		//如属性文件是test.properties，那此时propertiesFileNameWithoutPostfix的值就是test
		ResourceBundle bundel = ResourceBundle.getBundle(propertiesFileNameWithoutPostfix);
		s=bundel.getString(propertyName);
		return s;
		}
		/**
		*更改属性文件的值，如果对应的属性不存在，则自动增加该属性
		*@parampropertiesFileNameproperties文件名，如a.properties
		*@parampropertyName属性名
		*@parampropertyValue将属性名更改成该属性值
		*@return是否操作成功
		*/
		public static boolean changeValueByPropertyName(String propertiesFileName,String propertyName,String propertyValue) {
		boolean writeOK=true;
		Properties p=new Properties();
		InputStream in;
		try {
		in = new FileInputStream(propertiesFileName);
		p.load(in);//
		in.close();
		p.setProperty(propertyName,propertyValue);//设置属性值，如不属性不存在新建
		//p.setProperty("testProperty","testPropertyValue");
		FileOutputStream out=new FileOutputStream(propertiesFileName);//输出流
		p.store(out,"");//设置属性头，如不想设置，请把后面一个用""替换掉
		out.flush();//清空缓存，写入磁盘
		out.close();//关闭输出流
		} catch (Exception e) {
		e.printStackTrace();
		}
		return writeOK;
		}
		}
		/**
		* 如果是null，则返回空字符串，如果是非空值则返回该字符串头尾不为空白字符的字符串
		*
		* @param str
		*/
		public static String toNoNullTrimedString(String str) {
		if (str == null) {
		return "";
		}
		return new String(str.trim());
		}
		/**
		* 如果是null，则返回空字符串，如果是非空值则返回该对象所toString后的字符串
		*
		* @param obj
		*/
		public static String toNoNullString(Object obj) {
		if (obj == null)
		return "";
		return obj.toString();
		}
		/**
		* 本方法把一个Throwable的StackTrace作为一个字符串输出，以利于对StackTrace的操作。<br />
		* 通常用于把抛出的Exception转化成字符串进行后续操作。
		*/
		public static String exceptionToStackTrace(Throwable throwable) {
		StringBuffer retu = new StringBuffer();
		StackTraceElement[] traces = throwable.getStackTrace();
		for (StackTraceElement ste : traces) {
		retu.append("\n").append(ste.toString());
		}
		return retu.substring(1);
		}
		package com.sipgl.eam.utils;
		import java.text.SimpleDateFormat;
		import java.util.ArrayList;
		import java.util.Calendar;
		import java.util.Date;
		import java.util.GregorianCalendar;
		import java.util.LinkedHashMap;
		/**
		* 日期公用处理类
		*
		* @author SongJun
		* @version 1.3
		*/
		public class DateUtil {
		/**
		* 解析一个日期之间的所有月份
		*
		* @param beginDateStr
		* @param endDateStr
		* @return
		*/
		public static ArrayList getMonthList(String beginDateStr, String endDateStr) {
		// 指定要解析的时间格式
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		// 返回的月份列表
		String sRet
		= "";
		// 定义一些变量
		Date beginDate = null;
		Date endDate = null;
		GregorianCalendar beginGC = null;
		GregorianCalendar endGC = null;
		ArrayList list = new ArrayList();
		try {
		// 将字符串parse成日期
		beginDate = f.parse(beginDateStr);
		endDate = f.parse(endDateStr);
		// 设置日历
		beginGC = new GregorianCalendar();
		beginGC.setTime(beginDate);
		endGC = new GregorianCalendar();
		endGC.setTime(endDate);
		// 直到两个时间相同
		while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {
		sRet = beginGC.get(Calendar.YEAR) + "-"
		+ (beginGC.get(Calendar.MONTH) + 1);
		list.add(sRet);
		// 以月为单位，增加时间
		beginGC.add(Calendar.MONTH, 1);
		}
		return list;
		} catch (Exception e) {
		e.printStackTrace();
		return null;
		}
		}
		/**
		* 解析一个日期段之间的所有日期
		*
		* @param beginDateStr
		* 开始日期
		* @param endDateStr
		* 结束日期
		* @return
		*/
		public static ArrayList getDayList(String beginDateStr, String endDateStr) {
		// 指定要解析的时间格式
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		// 定义一些变量
		Date beginDate = null;
		Date endDate = null;
		Calendar beginGC = null;
		Calendar endGC = null;
		ArrayList list = new ArrayList();
		try {
		// 将字符串parse成日期
		beginDate = f.parse(beginDateStr);
		endDate = f.parse(endDateStr);
		// 设置日历
		beginGC = Calendar.getInstance();
		beginGC.setTime(beginDate);
		endGC = Calendar.getInstance();
		endGC.setTime(endDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 直到两个时间相同
		while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {
		list.add(sdf.format(beginGC.getTime()));
		// 以日为单位，增加时间
		beginGC.add(Calendar.DAY_OF_MONTH, 1);
		}
		return list;
		} catch (Exception e) {
		e.printStackTrace();
		return null;
		}
		}
		public static ArrayList getYearList() {
		ArrayList list = new ArrayList();
		Calendar c = null;
		c = Calendar.getInstance();
		c.setTime(new Date());
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		int startYear = currYear - 5;
		int endYear = currYear + 10;
		for (int i = startYear; i < endYear; i++) {
		list.add(new Integer(
		i));
		}
		return list;
		}
		public static int getCurrYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
		}
		/**
		* 得到某一年周的总数
		*
		* @param year
		* @return
		*/
		public static LinkedHashMap getWeekList(int year) {
		LinkedHashMap map = new LinkedHashMap();
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		int count = getWeekOfYear(c.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dayOfWeekStart = "";
		String dayOfWeekEnd = "";
		for (int i = 1; i <= count; i++) {
		dayOfWeekStart = sdf.format(getFirstDayOfWeek(year, i));
		dayOfWeekEnd = sdf.format(getLastDayOfWeek(year, i));
		map.put(new Integer(i), "第"+i+"周(从"+dayOfWeekStart + "至" + dayOfWeekEnd+")");
		}
		return map;
		}
		/**
		* 得到一年的总周数
		* @param year
		* @return
		*/
		public static int getWeekCountInYear(int year){
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		int count = getWeekOfYear(c.getTime());
		return count;
		}
		/**
		* 取得当前日期是多少周
		*
		* @param date
		* @return
		*/
		public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
		}
		/**
		* 得到某年某周的第一天
		*
		* @param year
		* @param week
		* @return
		*/
		public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);
		return getFirstDayOfWeek(cal.getTime());
		}
		/**
		* 得到某年某周的最后一天
		*
		* @param year
		* @param week
		* @return
		*/
		public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);
		return getLastDayOfWeek(cal.getTime());
		}
		/**
		* 得到某年某月的第一天
		* @param year
		* @param month
		* @return
		*/
		public static Date getFirestDayOfMonth(int year,int month){
		month = month-1;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR
		, year);
		c.set(Calendar.MONTH, month);
		int day = c.getActualMinimum(c.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, day);
		return c.getTime();
		}
		/**
		* 提到某年某月的最后一天
		* @param year
		* @param month
		* @return
		*/
		public static Date getLastDayOfMonth(int year,int month){
		month = month-1;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		int day = c.getActualMaximum(c.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, day);
		return c.getTime();
		}
		/**
		* 取得当前日期所在周的第一天
		*
		* @param date
		* @return
		*/
		public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
		}
		/**
		* 取得当前日期所在周的最后一天
		*
		* @param date
		* @return
		*/
		public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
		}
		}
		/**
		* 为RootPaneContainer组件添加键盘事件
		* @param rpc RootPaneContainer组件
		* @param action 需要执行的动作
		* @param keyName 键的名称
		* @param keyCode 键的数字代码
		* @param modifiers 任意修饰符的按位或组合
		*/
		public static void registerKeyEvent(RootPaneContainer rpc, Action action, String keyName, int keyCode, int modifiers)
		{
		JRootPane rp = rpc.getRootPane();
		InputMap inputMap = rp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(keyCode, modifiers), keyName);
		rp.getActionMap().put(keyName, action);
		}
		// 判断一个文件是否为二进制文件
		public static boolean isBinary(File file) {
		boolean isBinary = false;
		try {
		FileInputStream fin = new FileInputStream(file);
		long len = file.length();
		for (int j = 0; j < (int) len; j++) {
		int t = fin.read();
		if (t < 32 && t != 9 && t != 10 && t != 13) {
		isBinary = true;
		break;
		}
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
		return isBinary;
		} 
		
		
		
	}
}
