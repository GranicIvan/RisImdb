<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Izmena rezisera</title>
</head>
<body>
	<form action="/imdb/reziser/loadReziserData" method="post">
		<select name="idReziser">
			<c:forEach items="${reziseri}" var="r">
				<option value="${r.idReziser}">${r.idReziser} ${r.ime}
					${r.prezime}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Izaberi"><br>
	</form>

	<c:if test="${!empty reziser}">
		<form action="/imdb/reziser/changeReziser" method="post">
			<table>

				<tr>
					<td>Ime:</td>
					<td><input type="text" name="ime" value="${reziser.ime}"></td>
				</tr>
				<tr>
					<td>Prezime:</td>
					<td><input type="text" name="prezime" value="${reziser.prezime}"></td>
				</tr>
				
				<tr>
					<td>Nadimak:</td>
					<td><input type="text" name="nadimak" value="${reziser.nadimak}"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Sacuvaj"></td>
				</tr>
			</table>
		</form>
	</c:if>
	
	<c:if test="${!empty poruka}">
		${poruka}
	</c:if>
	

</body>
</html>