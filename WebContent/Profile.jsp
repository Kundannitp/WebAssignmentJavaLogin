<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
//String id = request.getParameter("userid");
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "webassignment";
String userid = "root";
String password = "12345";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html>
<html>
<head>
	<style>
		table{
			border:solid 2px;
		}
	</style>
</head>
<body>

<h1>Your Profile</h1>
<table border="1">
<tr>
<td>Name</td>
<td>Roll no.</td>
<td>Email</td>
<td>Phone</td>
<td>UserId</td>
</tr>
<%
try{
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.createStatement();
String id1=(String)session.getAttribute("Username");
String sql ="SELECT * FROM users WHERE uid=\""+id1+"\"";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr>
<td><%=resultSet.getString("name") %></td>
<td><%=resultSet.getString("roll") %></td>
<td><%=resultSet.getString("email") %></td>
<td><%=resultSet.getString("phone") %></td>
<td><%=resultSet.getString("uid") %></td>
</tr>
<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
<button onClick="printdoc()" style="margin-top: 20px; margin-bottom: 10px; width: 135px; font-size: 25px; color: white; border-radius: 10px; background-color: #4d80e4; border: solid 1px;">
        Print</button>
<script>
function printdoc(){
	window.print();
}
</script>
</body>
</html>