<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aktivni glumci</title>
</head>
<body>

	<table border="1">
		<tr>
			<td>Ime</td>
			<td>Prezime</td>
			<td>Nadimak</td>
			<td>Godiste</td>
			<td>Aktivan od</td>
		</tr>
		
		<c:if test="${!empty glumci}">
			<c:forEach items="${glumci}" var="g">
				<tr>
					<td>${g.ime}</td>
					<td>${g.prezime}</td>
					<td>${g.nadimak}</td>
					<td>${g.godiste}</td>
					<td>${g.aktivan_od}</td>					
					
				</tr>
			</c:forEach>
		</c:if>


	</table>


</body>
</html>