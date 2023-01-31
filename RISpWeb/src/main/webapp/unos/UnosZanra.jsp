<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unos Zanra</title>
</head>
<body>
	<!-- kontext path /requestMapping kontrolera/RequestMapping metode     -->
	<form action="/imdb/zanr/saveZanr" modelAttribute="zanr" method="post" >
		<table>
			<tr>
				<td>Ime zanra: </td>
				<td><input type="text" name="ime"></td>
			</tr>
			<tr>
				<td> <input type="submit" value="Ubaci Zanr"></td>
			</tr>
		</table>
	
	
	
	</form>

</body>
</html>