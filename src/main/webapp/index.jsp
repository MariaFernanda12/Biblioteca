<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.co.sergio.mundo.vo.*"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
#caja{
background:#f0f0f0;
width: 200px;
border:1px solid white;
margin: 200px auto;
padding:1em;
border-radius:6px; 
}
body{
background:#b6b6b6;
}
h1{
font-family:arial;
color:#006Dff;
}
input[type=text],input[type=password]{
margin:0 0 1em 0;
border: 0px;
padding:1em;
border-radius:3px; 
}
input[type=submit]{
padding: 1em;
background:#006Dff;
border:none;
color:White;
font-family:arial black;
font-size:4xp;



}

</style>
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
   <div align="center" style="margin-top: 50px;">
        <form action="CrunchifyServlet">
           Usuario :  <input type="text" name="usuario" size="20px"> <br>
           Clave :  <input type="password" name="clave" size="20px"> <br><br>
        <input type="submit" value="submit">
        </form>
     </div>
   <%
       if( request.getAttribute("estudiante")!=null){
          List<Estudiante> estudiante  = (List<Estudiante>)request.getAttribute("estudiante ");
           for (Estudiante estudiante: estudiante) {
         %>      
         <h1> <%=estudiante.getNom_estudiante()%> </h1><br/> 
         <%      
          }
       }
      
    
    %>
</body>
</html>
