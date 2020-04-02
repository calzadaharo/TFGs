<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vista del profesor</title>
<link rel="stylesheet" type="text/css" href="main.css" />
</head>
<body>
<h2>Datos del profesor</h2>
<p><b>Nombre: </b>${profesor.name}</p>
<p><b>Email: </b>${profesor.email}</p><h2>TFGs</h2>
<table border="1">
<c:forEach items="${profesor.advisedTFG}" var="tfgi">
<tr>
<td>${tfgi.nombre}</td>
<td>${tfgi.email}</td>
<td>${tfgi.title}</td>
<td>${tfgi.status}</td>
<c:if test="${tfgi.status == 1}">
	<td><form action="Form2ProfesorServlet">
                <input type="hidden" name="tfgemail" value="${tfgi.email}" />
                <button type="submit">Aceptar tutela</button>
                </form></td>
</c:if>
<c:if test="${tfgi.status > 3}">
        <td>
        <b>Memoria: </b>
        <form action="ServeFileServlet" method="get">
             <input type="hidden" name="tfgemail" value="${tfgi.email}" />
        <button type="submit">Descargar</button>
        </form>
        </td>
</c:if>


</tr>
</c:forEach>
</table>




</body>
</html>