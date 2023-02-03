<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Filmovi u periodu</title>
</head>
<body>


	<a href="/imdb">Pocetna strana</a>
	<br>




	<form action="/imdb/film/filmUPerodu" method="get">
		<table>
			<tr>
				<p>Izaberite iz kod perioda zelite da vidite filmove:</p>
				<br>
			</tr>
			<tr>
				<td>Datum od:</td>
				<td><input name="datumOd" type="date"></td>
			</tr>
			<tr>
				<td>Datum do:</td>
				<td><input name="datumDo" type="date"></td>
			</tr>

			<tr>
				<td><input type="submit" value="Prikaz"></td>
			</tr>
		</table>

	</form>

	<c:if test="${!empty filmovi}">
		Filmovi koji su u izabranom periodu su: <br>

		<table border="1">
			<tr>
				<td>Ime</td>
				<td>Datum izlaska</td>
				<td>Trajanje</td>
				<td>Zanr</td>
				<td>Reziser</td>
			</tr>


			<c:forEach items="${filmovi}" var="f">
				<tr>
					<td>${f.ime}</td>
					<td>${f.release_date}</td>
					<td>${f.trajanje}</td>
					<td>${f.glavniZanr.ime}</td>
					<td>${f.reziser.ime}${f.reziser.prezime}</td>
					<td><a href="/imdb/film/faveFilm?idF=${f.idFilm}">Fave</a></td>

				</tr>
			</c:forEach>
		</table>
	</c:if>








</body>
</html>