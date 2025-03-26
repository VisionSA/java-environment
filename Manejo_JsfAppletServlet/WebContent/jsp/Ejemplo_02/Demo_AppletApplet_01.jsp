
 <HTML>
 
  <HEAD></HEAD>

   <BODY bgcolor="BLACK">

	 <br/>
	 <br/>
     
     <center> <strong> <h2> <font color="red" /> Demo #2: Conexion 'JSP-Applet-Applet' </font> </h2> </strong> </center>
     
	 <br/>
	 <br/>

     <center> 
		 <APPLET CODE="AppletApplet_01.class" codebase="../../applet" HEIGHT="40" WIDTH="400" >
		
			<PARAM name="urlApplet2do"  value="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%= request.getContextPath() %>/jsp/Ejemplo_02/Demo_AppletApplet_02.jsf" />  
			<PARAM name="mensajeApplet" value="Hola JavaMan ...!!!" />
		 </APPLET>
     </center>
     
	 <br/>     
	 <br/>
	 <br/>
	 <br/>
	 
	 <center> <h3> <strong> <a href="../Menu.jsf" style="color:red; text-decoration:none" > Menu Principal </a> </strong> </h3> </center>  
	
	 <br/>
	 
  </BODY>
  
 </HTML>
 