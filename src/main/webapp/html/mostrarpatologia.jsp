<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>MIRATE_EL_OJO</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="img\logoico.ico">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: skyblue ;
      padding: 25px;
    }
H1{size:4 color:pink}
body{direction:rtl;
     background-image: url(img/fondo.jpg);
     background-color: rgb(255, 255, 255);
     background-attachment: fixed;
     background-repeat: repeat-x;
     background-clip: content-box;
     background-origin: content-box;
     background-size: cover;
     color:black;
    font-family:GESSTwoLight,GESSTwoMediumRegular,Droid Arabic Kufi,Helvetica,sans-serif;
    font-size:16px}
  </style>
</head>
<body  background="/img/fondo.jpg"  width="1500" heigth="1500">
  
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="logo" href="html/home.html"> <img src="img\logo mirate.png"  width="165" height="55"> </a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="#">
            <a href="html/home.html">Inicio</a></li>
        <li><a href="html/patologias.html">Patologias</a></li>
        <li><a href="../InitTest">Test</a></li>
        <li><a href="../html/descripcionProyecto.html">Acerca de</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
     <p>
    <button type="button" class="btn btn-info">
        <a href="html/busqueda.html">  
            <span class="glyphicon glyphicon-search"></span> BÚSQUEDA</a>
    </button>
  </p>
      </ul>
    </div>
  </div>
</nav>
<br><br>


<div class="container">
  <h1 align="left">${patologia.nombre_patologia}</h1>
            
    <center><table style="backgroundcolor="white"; opacity: 0,5; filter:alpha(opacity=50)" class="table table-bordered">
    <tbody>
    <tr>
        <td><img src= ${patologia.ruta_imagen_patologia} class="img-rounded" alt="Cinque Terre" width="404" height="300"></td>
        <td align="left"> <br><b>DESCRIPCIÓN  </b><br><br>${patologia.descripcion_patologia}<br></td>
   
   </tr>
    </tbody>
        <tbody>
      <tr>
     
        <td colspan="2" align="left"><br><b>CAUSA</b> <br><br><div align="left" >${patologia.causa_patologia}<br></div></td>
      </tr>
      <tr>
          <td colspan="2" align="left"><br><b>TRATAMIENTO </b><br><br> <div> <p align="left">${patologia.tratamiento_patologia}<br></p></div></td>
        
       
      </tr>
      <tr>
        <td colspan="2" align="left"><br><b>SINTOMAS</b><br><br><div align="left" align-text="justify">
        <c:forEach items="${patologia.lista_sintomas}" 
        var="sintoma">
        ${sintoma.descripcion}<br>
		</c:forEach></div>
     </td>
     
      </tr>
    </tbody>
  </table></center>
    </CENTER>
    </div>

</body>
</html>