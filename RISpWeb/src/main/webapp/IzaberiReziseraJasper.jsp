<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Izbor rezisera za Jasper</title>
</head>
<body>

		<form action="/imdb/film/pozoviJasperFpZ" method="post">
		<select name="idReziser">
			<c:forEach items="${reziseri}" var="r">
				<option value="${r.idReziser}">${r.idReziser} ${r.ime}
					${r.prezime}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Izaberi"><br>
	</form>
	
	

</body>
</html>