������������4�������ݿ����.dat��Ϊ���ݶ�ȡԴ��
Ҫ������ܸ�����ǰ�Ĵ��룬JDBC
������SQL
����˵����
1.PortfolioSQL.java��
String url = "jdbc:mysql://localhost:3306/portfolios";  //�滻������Ҫ�� JDBC database
String username = "root";                               //�滻������Ҫ�� JDBC user name
String password = "123456";                             //�滻������Ҫ�� JDBC password
2.��portfolioDB.sql�����������ݿ�ĸ�����
portfolioDB.sqlĿ¼��shirt+�Ҽ����ڴ˴�������ڡ�������
mysql -u�û��� -p���� -D���ݿ���<portfolioDB.sql
3.��mysql-connector-java-5.1.39-bin.jar��ӽ���Ŀlib,����