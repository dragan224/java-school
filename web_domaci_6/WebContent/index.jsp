<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="beans.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Pocetna strana</title>
	</head>

	<body>
		<%
			String path = application.getRealPath("");
			System.out.print(path);
			Products products = new Products(path);
			application.setAttribute("products", products);
		%>
		Raspolozivi proizvodi:
		<table border="1">
			<tr bgcolor="lightgrey">
				<th>Naziv</th>
				<th>Cena</th>
				<th>&nbsp;</th>
			</tr>
			<%
				for (Product p : products.values()) {
			%>
			<form method="GET" action="Pregled.jsp">
				<tr>
					<td><%=p.getName()%></td>
					<td><%=p.getPrice()%></td>
					<td>
						<input type="text" size="3" name="itemCount">
						<input type="submit" value="Dodaj">
					</td>
				</tr>
			</form>
			<%
				}
			%>
		</table>
		<a href="Pregled.jsp">Pregled sadrzaja korpe</a>
		<br>
		<a href="pretraga.jsp">Pretraga proizvoda</a>
		<br>
		<a href="login.jsp">Login/Register</a>
	</body>
</html>