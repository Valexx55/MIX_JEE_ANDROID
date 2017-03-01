<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!--<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />-->
  <title>MIRATE_EL_OJO</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="Estilos.css" media="screen" />
    <link rel="icon" href="../img/logoico.ico">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  function sendRespuesta (resp)
  {
	  open("TestServlet?resp="+resp, "_self");
	  
  }
  
  </script>
  
    
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
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
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: skyblue;
      padding: 25px;
    }
  </style>
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="logo"> <img src="img\logo mirate.png"  width="165" height="55"> </a>
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

  
<div class="container-fluid bg-3 text-center">    

<footer class="container-fluid text-center">
  <h2>TEST</h2>
</footer>
  <br>  <br>
    <br>
    <br>
    <br>
    <br>
    <br>
  <div class="row">
   <form class="form-horizontal">
 
  <div class="form-group has-success has-feedback">
      
    <label class="col-sm-2 control-label" for="inputSuccess">
   </label>
          
    <div class="col-sm-8">
      <input type="text" class="form-control" id="inputSuccess" dir="ltr"  value="${pregunta}">
     <!-- <span class="glyphicon glyphicon-ok form-control-feedback"></span>-->
    </div>
  </div>

</form>
    </div>
  </div>
    
  

 
<div class="container" >
  <Center>
       
      <button type="button" class="btn btn-primary" onclick="sendRespuesta('NO');">NO</button>
      <button type="button" class="btn btn-default" onclick="sendRespuesta('SI');">SI </button>
  
  
      </Center>
</div>

<!--<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>-->

</body>
</html>