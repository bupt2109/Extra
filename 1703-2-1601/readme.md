描述：金融题4，用数据库代替.dat作为数据读取源。
要求：最好能复用以前的代码，JDBC
技术：SQL
运行说明：
1.PortfolioSQL.java中
String url = "jdbc:mysql://localhost:3306/portfolios";  //替换成你需要的 JDBC database
String username = "root";                               //替换成你需要的 JDBC user name
String password = "123456";                             //替换成你需要的 JDBC password
2.将portfolioDB.sql运行生成数据库的各个表：
portfolioDB.sql目录下shirt+右键“在此处打开命令窗口”，输入
mysql -u用户名 -p密码 -D数据库名<portfolioDB.sql
3.将mysql-connector-java-5.1.39-bin.jar添加进项目lib,运行