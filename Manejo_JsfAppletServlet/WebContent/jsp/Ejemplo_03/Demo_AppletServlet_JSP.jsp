
 <HTML>

  <HEAD>
    <TITLE> ...::: JSP Applet Servlet Communication :::... </TITLE>
  </HEAD>

  <% 
     /* REQUEST OBJECTs: (http://www.exforsys.com/tutorials/jsp/jsp-request-object.html)
        ---------------
		getCookies() 
		getHeader(String name) 
		getHeaderNames() 
		getAttribute(String name) 
		getAttributeNames() 
		getMethod() 
		getParameter(String name) 
		getParameterNames() 
		getParameterValues(String name) 
		getQueryString() 
		getRequestURI() 
		getServletPath()      
      */

     System.out.println( " " );      
     System.out.println( "REQUEST OBJECTs: " );    
     System.out.println( "---------------- " );
     System.out.println( "HeaderNames: "  + request.getHeaderNames() );
     System.out.println( "Method: "       + request.getMethod() );
     System.out.println( "RequestURI: "   + request.getRequestURI() ); 
     System.out.println( "ServletPath: "  + request.getServletPath() );
     System.out.println( "ContextPath: "  + request.getContextPath() );   
     System.out.println( "ContentType: "  + request.getContentType() ); 
     System.out.println( "QueryString: "  + request.getQueryString() ); 
     System.out.println( "PathInfo: "     + request.getPathInfo() );
     System.out.println( "Protocol: "     + request.getProtocol() ); 
     System.out.println( "ServerName: "   + request.getServerName() );     
     System.out.println( "ServerPort: "   + request.getServerPort() );
     System.out.println( " " );  
  %>

  <BODY bgcolor="black" >

	<FORM id="frmAppletServlet" action="" method="post" > 
	 
	  <BR>
	   
	   <CENTER>
	      <H2> <FONT COLOR="red" > Demo #3A: Conexion 'JSP-Applet-Servlet' </FONT> </H2>
	   </CENTER>
	     
	  <BR>
	  <BR>

	  <CENTER>
		  <APPLET code="RichiApplet.class" codebase="../../applet" width="500" height="200" alt="JAVA-APPLET" title="JAVA-APPLET" >  
		  		  
		  	 <PARAM name="URL_NombreAppletServlet" value="http://<%=request.getServerName()%>:<%=request.getServerPort()%>/<%=request.getContextPath()%>/JavaManServlet_02?nombre='ricardo'" />  
			 <PARAM name="URL_ServletContext"      value="<%= getServletContext().getContextPath() %>" />
			 <PARAM name="URL_ContextPath"         value="<%= request.getContextPath() %>" />
		  		  
		  </APPLET>  
      </CENTER>
      	  
	  <BR>
	  <BR>
	  <BR>
	  <BR>
	  <BR> 
	  
	  <center> <h3> <strong> <a href="../Menu.jsf" style="color:red; text-decoration:none" > Menu Principal </a> </strong> </h3> </center>  
	
	  <br/>
	   
	 </FORM>
 
   </BODY>

 </HTML>
