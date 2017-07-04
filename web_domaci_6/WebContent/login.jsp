<%@page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Primer sa formom</title>
</head>
<body>
<h3>Login</h3>
<% if (!user.isLoggedIn()) { %>
  <form action="http://localhost:8080/web_z7/LoginServlet" method="get">
  <table cellspacing=0 cellpadding=3 border=0>
    <tr>
      <td align=right>Korisnicko ime:</td>
      <td><input type="text" name="username"></td>
    </tr>
    <tr>
      <td align=right>Lozinka:</td>
      <td><input type="password" name="password"></td>
    </tr>
    <tr>
      <td align=right>&nbsp;</td>
      <td><input type="submit" value=" Pošalji "></td>
    </tr>
  </table>
  </form>
<% } else { %>
  <p>
  Već ste se prijavili! <br>
  [<a href="http://localhost:8080/web_z7/LoginServlet?logoff=true">Odjavi se</a>]
  </p>
<% } %>


<h3>Register</h3>

</body>
</html>
