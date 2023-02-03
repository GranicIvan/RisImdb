<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Glumci u filmu</title>
</head>
<body>

	<a href="/imdb">Pocetna strana</a> <br> 

	<form action="/imdb/glumac/glumciUFilmu" method="post">
		<table>

			<tr>
				<td>Film:</td>
				<td>
					<select name="idFilm">
						<c:forEach items="${filmovi}" var="f">
							<option value="${f.idFilm}">${f.ime} (${f.release_date}) - ${f.reziser.ime} ${f.reziser.prezime}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		
					
				
			<tr>
				<td><input type="submit" value="Izaberi film"></td>
			</tr>
		</table>
	</form>


	<c:if test="${!empty glumci}">
		<table border="1">
			<tr>			
				<td>Ime</td>
				<td>Prezime</td>
				<td>Nadimak</td>
				<td>Godiste</td>
				
			</tr>

			<c:forEach items="${glumci}" var="g">
				<tr>
					<td>${g.ime}</td>
					<td>${g.prezime}</td>
					<td>${g.nadimak}</td>
					<td>${g.godiste}</td>
					<br>
				</tr>
			</c:forEach>
		</table>
		
			
	</c:if>



</body>
</html>