<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unos Glumca IG</title>
</head>
<body>

	<form action="/imdb/glumac/saveGlumac" method="post">
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
				<td>Godiste:</td>
				<td><input type="text" name="godiste"></td>
			</tr>
			<tr>
				<td>Aktivan od:</td>
				<td><input type="date" name="aktivan_od"></td>
			</tr>
			<tr>
				<td>Aktivan do:</td>
				<td><input type="date" name="aktivan_do"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Sacuvaj"></td>
			</tr>
		</table>	
	</form>



</body>
</html>