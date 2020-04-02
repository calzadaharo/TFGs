<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vista del alumno</title>
<link rel="stylesheet" type="text/css" href="main.css" />
</head>
<body>
<h2>Datos del TFG</h2>
<p><b>Nombre: </b>${tfg.nombre}</p>
<p><b>Email: </b>${tfg.email}</p>
<p><b>Título: </b>${tfg.title}</p>
<p><b>Tutor: </b>${tfg.advisor.name}</p>
<p><b>Email del tutor: </b>${tfg.advisor.email}</p>
<p><b>Estado del trabajo: </b>${tfg.status}</p>
<c:if test="${tfg.status == 3}">
<form action="Form4TFGServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="tfgemail" value="${tfg.email}" />
        <input type="file" name="file"/>
        <button type="submit">Subir memoria</button>
</form>
</c:if>
<c:if test="${tfg.status > 3}">
        <p>
        <b>Memoria subida: </b>
        <form action="ServeFileServlet" method="get">
             <input type="hidden" name="tfgemail" value="${tfg.email}" />
        <button type="submit">Descargar</button>
        </form>
        </p>
</c:if>
</body>
</html>