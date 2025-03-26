
<html>

<head>
<title>...::: Objectos Request 'JSP' :::...</title>
</head>

<body bgcolor="black">

<br />

<center><strong>
<h2><font color="red" /> Objectos Request 'JSP' </font></h2>
</strong></center>

<FONT color="white"> <br />
<b>Request Method:</b> <%out.print(request.getMethod()); %> <br>
<b>URI: </b><%=request.getRequestURI()%> <br>
<b>Protocol:</b> <%=request.getProtocol()%> <br>
<b>Servlet path:</b> <%=request.getServletPath()%> <br>
<b>Path info:</b>
<%
	out.print(request.getPathInfo());
%> <br>
<b>Query string:</b>
<%
	out.print(request.getQueryString());
%> <br>
<b>Content length:</b><%=request.getContentLength()%> <br>
<b>Content type:</b>
<%
	out.print(request.getContentType());
%> <br>
<b>Server name:</b><%=request.getServerName()%> <br>
<b>Server port:</b><%=request.getServerPort()%> <br>
<b>Remote user: </b><%=request.getRemoteUser()%> <br>
<b>Remote address:</b><%=request.getRemoteAddr()%> <br>
<b>Remote host:</b><%=request.getRemoteHost()%> <br>
<b>Authorization scheme:</b><%=request.getAuthType()%> <br>
<b>Locale:</b><%=request.getLocale()%> <br>
<b>Browser:</b>
<%
	out.print(request.getHeader("User-Agent"));
%> </FONT>

<center>
<h3><strong> <a href="../Menu.jsf"
	style="color: red; text-decoration: none"> Menu Principal </a> </strong></h3>
</center>

<br />

</body>

</html>
