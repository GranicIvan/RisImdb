<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unos Rezisera IG</title>
</head>
<body>

	<a href="/imdb">Pocetna strana</a> <br> 

	<form action="/imdb/reziser/saveReziser" method="post">
		<table>

			<tr>
				<td>Ime:</td>
				<td><input type="text" name="ime"></td>
			</tr>
			<tr>
				<td>Prezime:</td>
				<td><input type="text" name="prezime"></td>
			</tr>
			<tr>
				<td>Nadimak:</td>
				<td><input type="text" name="nadimak"></td>
			</tr>			
			<tr>
				<td><input type="submit" value="Sacuvaj"></td>
			</tr>
		</table>
	</form>

	<c:if test="${!empty reziser}">
		${poruka} 	Reziser se zove: ${reziser}
	</c:if>

</body>
</html>