<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sortirani Filmovi</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>Ime</td>
			<td>Datum izlaska</td>
			<td>Trajanje</td>
			<td>Zanr</td>
			<td>Reziser</td>
		</tr>

		<c:if test="${!empty filmovi}">
			<c:forEach items="${filmovi}" var="f">
				<tr>
					<td>${f.ime}</td>
					<td>${f.release_date}</td>
					<td>${f.trajanje}</td>
					<td>${f.glavniZanr.ime}</td>
					<td>${f.reziser.ime}$ {f.reziser.prezime}</td>
					

				</tr>
			</c:forEach>
		</c:if>


	</table>


</body>
</html>