<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Izbor rezisera za Jasper</title>
</head>
<body>

	<a href="/imdb">Pocetna strana</a> <br> 

	<form action="/imdb/film/pozoviJasperFpZ" method="post">
		<select name="idReziser">
			<c:forEach items="${reziseri}" var="r">
				<option value="${r.idReziser}">${r.idReziser}${r.ime}
					${r.prezime}</option>
			</c:forEach>
		</select> <input type="submit" value="Izaberi"><br>
	</form>

		<form action="/imdb/film/getFilmoviPoZanrovima" method="post">
		
		</select> <input type="submit" value="Izgenerisis"><br>
	</form>


</body>
</html>