<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>MIRATE_EL_OJO</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="../img/logoico.ico">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
 
body{direction:rtl;
     background-image: url(../img/fondo.jpg);
     background-color: rgb(255, 255, 255);
     background-attachment: fixed;
     background-repeat: repeat-x;
     background-clip: content-box;
     background-origin: content-box;
     background-size: cover;
     color:#5E6066;
    font-family:GESSTwoLight,GESSTwoMediumRegular,Droid Arabic Kufi,Helvetica,sans-serif;
    font-size:16px}
  </style>
</head>
<body  background="../img/fondo.jpg"  width="1500" heigth="1500">
  
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="logo"> <img src="..\img\logo mirate.png"  width="165" height="55"> </a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="#">
            <a href="home.html">Inicio</a></li>
        <li><a href="patologias.html">Patologias</a></li>
        <li><a href="test.html">Test</a></li>
        
      </ul>
      <ul class="nav navbar-nav navbar-right">
     <p>
    <button type="button" class="btn btn-info">
        <a href="busqueda.html">  
            <span class="glyphicon glyphicon-search"></span> BÚSQUEDA</a>
    </button>
  </p>
      </ul>
    </div>
  </div>
</nav>
<br><br>


<div class="container">
  <CENTER ><h2>ESTRABISMO</h2>
            
    <center><table class="table table-bordered">
    <tbody>
    <tr>
        <td><img src="../img/estrabismo-test.jpg"  class="img-rounded" alt="Cinque Terre" width="404" height="336">
        DESCRIPCIÓN <br>${patologia.descripcion_patologia}</td>
   </tr>
    </tbody>
        <tbody>
      <tr>
     
        <td><center>CAUSA <br>${patologia.causa_patologia}</center></td>
      </tr>
      <tr>
          <td><center>TRATAMIENTO <br>${patologia.tratamiento_patologia}</center></td>
      </tr>
        <tr>
        <td><center>SINTOMAS</center>
        <c:forEach items="${patologia.lista_sintomas}" 
        var="sintoma">
        ${sintoma.descripcion}<br>
		</c:forEach>
     </td>
        </table>
      </center>
    </CENTER>
    </div>
    </body>
</html>