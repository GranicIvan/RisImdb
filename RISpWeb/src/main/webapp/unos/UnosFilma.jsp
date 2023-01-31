<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unos Filma IG</title>
</head>
<body>

	<form action="/imdb/film/saveFilm" method="post">
		<table>

			<tr>
				<td>Ime:</td>
				<td><input type="text" name="ime"></td>
			</tr>
			<tr>
				<td>Datum izlaska:</td>
				<td><input type="date" name="release_date"></td>
			</tr>
			<tr>
				<td>Trajanje u minutima:</td>
				<td><input type="text" name="trajanje"></td>
			</tr>
			<tr>
				<td>Zanr:</td>
				<td>
					<select name="idZanr">
						<c:forEach items="${zanrovi}" var="z">
							<option value="${z.idZanr}">${z.ime}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>Reziser:</td>
				<td>
					<select name="idReziser">
						<c:forEach items="${reziseri}" var="r">
							<option value="${r.idReziser}">${r.ime} ${r.prezime}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
					
				
			<tr>
				<td><input type="submit" value="Sacuvaj"></td>
			</tr>
		</table>
	</form>


</body>
</html>