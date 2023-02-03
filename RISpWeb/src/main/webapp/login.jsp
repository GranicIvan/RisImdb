<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<html>

<style>
.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 95%;
 
}
.main {
  text-align: center;
  padding: 15px 15px;
  border-radius: 10px;
  background-color: #274c77;
  margin-left: auto;
  margin-right: auto;
  width: 35%;
}
body {
	font-family: helvetica, sans-serif;
	background-color: #e7ecef;
	color:#e7ecef;
}

a {
color:orange;
}
a:hover{
color:#f4a261;
}
</style>
<body>
<div class="main">
<div class="center">
	<c:url var="loginUrl" value="/login" />
	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
		<div>
			<p>Invalid username and password.</p>
		</div>
	</c:if>
	<form action="${loginUrl}" method="post">
		<table>
			<tr>
				<td>E-mail adresa:</td>
				<td><input type="text" name="username"
					placeholder="Enter Username" required></td>
			</tr>
			<tr>
				<td>Sifra:</td>
				<td><input type="password" name="password"
					placeholder="Enter Password" required></td>
			</tr>
			 <tr>
                <td>Zapamti me:</td>
                <td><input type="checkbox" name="remember-me" /></td>
            </tr>
			<tr>
				<td><input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /></td>
				<td><input type="submit" value="Log in"></td>
			</tr>
		</table>
		Nemate nalog? <a href="/imdb/auth/registerUser">Registrujte se</a>
	</form>
	</div>
	</div>
</body>
</html>