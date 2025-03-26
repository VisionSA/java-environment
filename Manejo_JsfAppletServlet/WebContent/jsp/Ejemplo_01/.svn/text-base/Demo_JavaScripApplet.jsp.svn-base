
<html>

<head>
   <!-- El JavaScript, una funcion que obtiene el Applet usado su 'id' y llama al mètodo 'conexionJspApplet()' -->
   <script>
      function conexionJspApplet(){
         var applet = document.getElementById('idApplet');    //Objeto del applet embebido en la pagina
         
         if( applet != null ){
             applet.mensajeApplet();
         } 
      }
   </script>
</head>

<body bgcolor="BLACK">
	 <FORM name="formDisplay">
	 
	 <br/>
	 <br/>
     
     <center> <strong> <h2> <font color="red" /> Demo #1: Conexion 'JSP-Applet' </font> </h2> </strong> </center>
     
	 <br/>
	 <br/>
	 
     <center>
 
		<!-- El applet, con un id que permita identificarlo -->
		<applet id="idApplet" code="JavaScriptApplet.class" codebase="../../applet" width="200" height="100"> </applet>  
		
		 <br/>
	
		 <!-- Un boton que al pulsarlo llame al metodo 'conexionJspApplet(') de JavasCript -->
		 <INPUT TYPE="button" NAME="boton" VALUE=" Procesar " onClick="conexionJspApplet()" />
   
     </center>

	 <br/>
	 <br/>
	 <br/>
	 <br/>
	 
	 <center> <h3> <strong> <a href="../Menu.jsf" style="color:red; text-decoration:none" > Menu Principal </a> </strong> </h3> </center>  
	
	 <br/>
	 
	 </FORM>
</body>

</html>

