<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>       
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Povezivanje filma i glumca</title>
</head>
<body>
	
	<a href="/imdb">Pocetna strana</a> <br> 
	
	<form action="/imdb/film/poveziFiG" method="post">
		<table>

			<tr>
				<td>Film:</td>
				<td>
					<select name="idFilm">
						<c:forEach items="${filmovi}" var="f">
							<option value="${f.idFilm}">${f.ime} ${f.release_date}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>Glumac:</td>
				<td>
					<select name="idGlumac">
						<c:forEach items="${glumci}" var="g">
							<option value="${g.idGlumac}">${g.ime} ${g.prezime}</option>
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