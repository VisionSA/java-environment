<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html>
<head>
	<title><h:outputText value="#{ListaPrecioBean.tituloLargo}"/></title>
	<s:script language="javascript">
		function recargar() {
			document.getElementById('amListaPrecioForm').submit();
		}
		function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;
		    popupWindow=open(pagina,'','resizable=no,autohide=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		    
		};
	</s:script>
	
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('amListaPrecioForm');" onload="if('${ListaPrecioBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}; ocultarCargaNuevaVersion();" >

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    Alta Lista De Precio
    <small>TARJETA FIEL</small>
  </h1>
</section>



<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

     <script languaje="javascript">

               var cuota1 = new Array ();
               var cuota2 = new Array ("0.25","0.50","0.75","0.99","1.24","1.48","1.72","1.97","2.21","2.45","2.68","2.92","3.16","3.39","3.63","3.86","4.09","4.33","4.56","4.79","5.02","5.24","5.47","5.70","5.92","6.14","6.37","6.59","6.81","7.03","7.25","7.47","7.68","7.90","8.12","8.33","8.54","8.76","8.97","9.18","9.39","9.60","9.81","10.02","10.22","10.43","10.63","10.84","11.04","11.24","11.44","11.65","11.85","12.04","12.24","12.44","12.64","12.83","13.03","13.22","13.42","13.61","13.80","13.99","14.18","14.37","14.56","14.75","14.94","15.13","15.31","15.50","15.68","15.87","16.05","16.23","16.41","16.59","16.78","16.96");
               var cuota3 = new Array ("0.33","0.66","0.99","1.32","1.64","1.97","2.29","2.61","2.93","3.24","3.56","3.87","4.18","4.49","4.80","5.11","5.41","5.71","6.01","6.31","6.61","6.91","7.20","7.50","7.79","8.08","8.37","8.66","8.94","9.23","9.51","9.79","10.07","10.35","10.62","10.90","11.17","11.45","11.72","11.99","12.26","12.52","12.79","13.05","13.32","13.58","13.84","14.10","14.35","14.61","14.87","15.12","15.37","15.62","15.87","16.12","16.37","16.62","16.86","17.10","17.35","17.59","17.83","18.07","18.31","18.54","18.78","19.01","19.25","19.48","19.71","19.94","20.17","20.40","20.62","20.85","21.07","21.29","21.52","21.74");
               var cuota4 = new Array ("0.42","0.83","1.24","1.64","2.05","2.45","2.85","3.25","3.64","4.03","4.42","4.81","5.19","5.57","5.95","6.33","6.70","7.07","7.44","7.81","8.17","8.54","8.90","9.25","9.61","9.96","10.31","10.66","11.01","11.35","11.69","12.03","12.37","12.71","13.04","13.37","13.70","14.03","14.36","14.68","15.00","15.32","15.64","15.95","16.27","16.58","16.89","17.20","17.50","17.81","18.11","18.41","18.71","19.01","19.30","19.60","19.89","20.18","20.47","20.75","21.04","21.32","21.60","21.88","22.16","22.44","22.71","22.99","23.26","23.53","23.80","24.07","24.33","24.60","24.86","25.12","25.38","25.64","25.89","26.15");
               var cuota5 = new Array ("0.50","0.99","1.48","1.97","2.45","2.93","3.41","3.88","4.35","4.81","5.27","5.73","6.19","6.64","7.08","7.53","7.97","8.41","8.84","9.27","9.70","10.12","10.55","10.96","11.38","11.79","12.20","12.61","13.01","13.41","13.81","14.20","14.59","14.98","15.37","15.75","16.13","16.51","16.89","17.26","17.63","18.00","18.36","18.72","19.08","19.44","19.79","20.15","20.50","20.84","21.19","21.53","21.87","22.21","22.54","22.88","23.21","23.53","23.86","24.18","24.51","24.83","25.14","25.46","25.77","26.08","26.39","26.70","27.00","27.31","27.61","27.90","28.20","28.50","28.79","29.08","29.37","29.66","29.94","30.22");
               var cuota6 = new Array ("0.58","1.16","1.73","2.29","2.85","3.41","3.96","4.51","5.05","5.58","6.12","6.64","7.17","7.68","8.20","8.71","9.21","9.71","10.21","10.70","11.19","11.67","12.16","12.63","13.10","13.57","14.04","14.50","14.95","15.41","15.85","16.30","16.74","17.18","17.61","18.04","18.47","18.90","19.32","19.73","20.15","20.56","20.96","21.37","21.77","22.17","22.56","22.95","23.34","23.72","24.11","24.49","24.86","25.23","25.60","25.97","26.34","26.70","27.06","27.41","27.77","28.12","28.46","28.81","29.15","29.49","29.83","30.16","30.50","30.82","31.15","31.48","31.80","32.12","32.44","32.75","33.06","33.37","33.68","33.99");
               var cuota7 = new Array ("0.66","1.32","1.97","2.61","3.25","3.88","4.51","5.13","5.74","6.35","6.95","7.54","8.13","8.72","9.29","9.87","10.43","11.00","11.55","12.10","12.65","13.19","13.73","14.26","14.78","15.30","15.82","16.33","16.84","17.34","17.83","18.33","18.81","19.30","19.78","20.25","20.72","21.19","21.65","22.11","22.56","23.01","23.46","23.90","24.33","24.77","25.20","25.62","26.05","26.46","26.88","27.29","27.70","28.10","28.50","28.90","29.29","29.68","30.07","30.45","30.83","31.21","31.58","31.95","32.32","32.68","33.04","33.40","33.76","34.11","34.46","34.80","35.15","35.49","35.82","36.16","36.49","36.82","37.15","37.47");
               var cuota8 = new Array ("0.75","1.48","2.21","2.93","3.65","4.35","5.05","5.74","6.43","7.10","7.77","8.43","9.09","9.73","10.37","11.01","11.63","12.25","12.87","13.47","14.08","14.67","15.26","15.84","16.42","16.99","17.55","18.11","18.66","19.21","19.75","20.29","20.82","21.34","21.86","22.38","22.89","23.39","23.89","24.38","24.87","25.36","25.84","26.31","26.78","27.25","27.71","28.17","28.62","29.07","29.51","29.95","30.38","30.81","31.24","31.66","32.08","32.50","32.91","33.31","33.72","34.12","34.51","34.90","35.29","35.67","36.05","36.43","36.80","37.17","37.54","37.90","38.26","38.62","38.97","39.32","39.67","40.02","40.36","40.69");
               var cuota9 = new Array ("0.83","1.65","2.45","3.25","4.04","4.82","5.59","6.35","7.11","7.85","8.58","9.31","10.03","10.73","11.43","12.13","12.81","13.49","14.16","14.82","15.47","16.12","16.75","17.39","18.01","18.63","19.24","19.84","20.43","21.02","21.61","22.18","22.75","23.32","23.87","24.43","24.97","25.51","26.04","26.57","27.09","27.61","28.12","28.62","29.12","29.62","30.11","30.59","31.07","31.54","32.01","32.47","32.93","33.39","33.84","34.28","34.72","35.15","35.58","36.01","36.43","36.85","37.26","37.67","38.08","38.48","38.87","39.27","39.65","40.04","40.42","40.80","41.17","41.54","41.91","42.27","42.63","42.98","43.33","43.68");
               var cuota10 = new Array ("0.91","1.81","2.70","3.57","4.43","5.29","6.13","6.96","7.78","8.59","9.39","10.17","10.95","11.72","12.48","13.23","13.97","14.70","15.42","16.13","16.83","17.53","18.21","18.89","19.56","20.22","20.87","21.52","22.15","22.78","23.40","24.02","24.62","25.22","25.81","26.40","26.98","27.55","28.11","28.67","29.22","29.76","30.30","30.83","31.36","31.88","32.39","32.90","33.40","33.90","34.39","34.87","35.35","35.82","36.29","36.75","37.21","37.66","38.11","38.55","38.99","39.42","39.85","40.28","40.69","41.11","41.52","41.92","42.32","42.72","43.11","43.50","43.88","44.26","44.64","45.01","45.37","45.74","46.10","46.45");
               var cuota11 = new Array ("0.99","1.97","2.94","3.89","4.82","5.75","6.66","7.56","8.44","9.32","10.18","11.03","11.87","12.69","13.51","14.31","15.10","15.89","16.66","17.42","18.17","18.91","19.64","20.36","21.07","21.77","22.46","23.15","23.82","24.49","25.14","25.79","26.43","27.06","27.69","28.30","28.91","29.51","30.10","30.68","31.26","31.83","32.39","32.95","33.50","34.04","34.57","35.10","35.62","36.14","36.65","37.15","37.64","38.13","38.62","39.10","39.57","40.04","40.50","40.95","41.40","41.85","42.29","42.72","43.15","43.58","44.00","44.41","44.82","45.23","45.63","46.02","46.41","46.80","47.18","47.56","47.93","48.30","48.67","49.03");
               var cuota12 = new Array ("1.07","2.13","3.18","4.20","5.21","6.21","7.19","8.15","9.10","10.04","10.96","11.87","12.77","13.65","14.52","15.37","16.22","17.05","17.87","18.68","19.47","20.26","21.03","21.79","22.54","23.28","24.01","24.73","25.44","26.14","26.83","27.51","28.18","28.84","29.49","30.13","30.77","31.39","32.01","32.62","33.22","33.81","34.39","34.97","35.54","36.10","36.65","37.20","37.74","38.27","38.79","39.31","39.82","40.33","40.83","41.32","41.80","42.28","42.75","43.22","43.68","44.13","44.58","45.03","45.46","45.90","46.32","46.75","47.16","47.57","47.98","48.38","48.78","49.17","49.55","49.94","50.31","50.69","51.05","51.42");
               var cuota13 = new Array ("1.16","2.29","3.41","4.52","5.60","6.66","7.71","8.74","9.76","10.76","11.74","12.70","13.66","14.59","15.51","16.42","17.31","18.19","19.06","19.91","20.75","21.57","22.39","23.19","23.98","24.75","25.52","26.27","27.01","27.74","28.46","29.17","29.87","30.56","31.23","31.90","32.56","33.21","33.85","34.48","35.10","35.71","36.31","36.91","37.49","38.07","38.64","39.20","39.76","40.30","40.84","41.37","41.89","42.41","42.92","43.42","43.91","44.40","44.88","45.36","45.83","46.29","46.75","47.20","47.64","48.08","48.51","48.94","49.36","49.77","50.18","50.59","50.99","51.38","51.77","52.16","52.53","52.91","53.28","53.64");
               var cuota14 = new Array ("1.24","2.46","3.65","4.83","5.98","7.12","8.23","9.33","10.40","11.46","12.50","13.53","14.53","15.52","16.49","17.45","18.39","19.31","20.22","21.12","22.00","22.86","23.71","24.55","25.37","26.18","26.98","27.76","28.54","29.30","30.04","30.78","31.50","32.22","32.92","33.61","34.29","34.96","35.62","36.26","36.90","37.53","38.15","38.76","39.36","39.96","40.54","41.11","41.68","42.24","42.79","43.33","43.86","44.38","44.90","45.41","45.92","46.41","46.90","47.38","47.86","48.32","48.78","49.24","49.69","50.13","50.57","51.00","51.42","51.84","52.25","52.66","53.06","53.45","53.84","54.23","54.61","54.98","55.35","55.72");
               var cuota15 = new Array ("1.32","2.62","3.89","5.14","6.36","7.57","8.75","9.91","11.05","12.16","13.26","14.34","15.40","16.44","17.46","18.46","19.45","20.41","21.36","22.30","23.22","24.12","25.01","25.88","26.73","27.58","28.40","29.22","30.02","30.80","31.58","32.34","33.08","33.82","34.54","35.25","35.95","36.64","37.32","37.98","38.64","39.28","39.91","40.54","41.15","41.76","42.35","42.94","43.51","44.08","44.64","45.19","45.73","46.26","46.79","47.30","47.81","48.31","48.81","49.29","49.77","50.24","50.71","51.17","51.62","52.06","52.50","52.93","53.36","53.77","54.19","54.59","55.00","55.39","55.78","56.17","56.54","56.92","57.29","57.65");
               var cuota16 = new Array ("1.40","2.78","4.13","5.45","6.74","8.01","9.26","10.48","11.68","12.86","14.01","15.14","16.25","17.34","18.41","19.45","20.48","21.49","22.48","23.46","24.41","25.35","26.27","27.17","28.06","28.93","29.79","30.63","31.45","32.26","33.06","33.84","34.61","35.37","36.11","36.84","37.55","38.26","38.95","39.63","40.30","40.96","41.60","42.24","42.87","43.48","44.08","44.68","45.26","45.84","46.40","46.96","47.51","48.05","48.58","49.10","49.61","50.12","50.61","51.10","51.58","52.06","52.52","52.98","53.44","53.88","54.32","54.75","55.18","55.59","56.01","56.41","56.81","57.21","57.60","57.98","58.35","58.73","59.09","59.45");
               var cuota17 = new Array ("1.48","2.94","4.36","5.75","7.12","8.46","9.77","11.05","12.31","13.54","14.75","15.93","17.09","18.23","19.34","20.43","21.50","22.55","23.58","24.59","25.58","26.55","27.50","28.44","29.35","30.25","31.13","32.00","32.85","33.68","34.50","35.30","36.09","36.86","37.62","38.37","39.10","39.82","40.53","41.22","41.90","42.57","43.23","43.87","44.51","45.13","45.74","46.34","46.93","47.52","48.09","48.65","49.20","49.74","50.28","50.80","51.32","51.82","52.32","52.81","53.30","53.77","54.24","54.70","55.15","55.60","56.03","56.46","56.89","57.30","57.72","58.12","58.52","58.91","59.30","59.67","60.05","60.42","60.78","61.14");
               var cuota18 = new Array ("1.57","3.10","4.60","6.06","7.50","8.90","10.27","11.62","12.93","14.22","15.48","16.71","17.92","19.10","20.26","21.39","22.50","23.59","24.66","25.70","26.72","27.73","28.71","29.67","30.61","31.54","32.44","33.33","34.20","35.06","35.90","36.72","37.52","38.31","39.09","39.85","40.59","41.32","42.04","42.75","43.44","44.12","44.78","45.44","46.08","46.71","47.33","47.93","48.53","49.12","49.69","50.26","50.81","51.36","51.89","52.42","52.94","53.45","53.95","54.44","54.92","55.39","55.86","56.32","56.77","57.21","57.65","58.08","58.50","58.91","59.32","59.72","60.12","60.51","60.89","61.27","61.64","62.00","62.36","62.71");
               var cuota19 = new Array ("1.65","3.26","4.83","6.37","7.87","9.34","10.77","12.17","13.55","14.89","16.20","17.48","18.74","19.96","21.16","22.34","23.49","24.61","25.71","26.79","27.84","28.87","29.88","30.87","31.84","32.79","33.72","34.63","35.52","36.39","37.25","38.09","38.91","39.71","40.50","41.27","42.03","42.77","43.50","44.22","44.92","45.60","46.28","46.94","47.58","48.22","48.84","49.45","50.06","50.64","51.22","51.79","52.35","52.89","53.43","53.96","54.48","54.98","55.48","55.97","56.46","56.93","57.39","57.85","58.30","58.74","59.17","59.60","60.02","60.43","60.83","61.23","61.62","62.01","62.39","62.76","63.12","63.48","63.84","64.19");
               var cuota20 = new Array ("1.73","3.42","5.06","6.67","8.24","9.77","11.27","12.73","14.16","15.55","16.91","18.24","19.54","20.81","22.05","23.27","24.45","25.61","26.75","27.85","28.94","30.00","31.03","32.05","33.04","34.01","34.96","35.89","36.80","37.69","38.56","39.41","40.25","41.07","41.87","42.65","43.42","44.17","44.91","45.63","46.34","47.03","47.71","48.37","49.03","49.67","50.29","50.91","51.51","52.10","52.68","53.25","53.81","54.36","54.89","55.42","55.94","56.45","56.94","57.43","57.91","58.38","58.85","59.30","59.75","60.18","60.61","61.04","61.45","61.86","62.26","62.65","63.04","63.42","63.79","64.16","64.52","64.88","65.23","65.57");
               var cuota21 = new Array ("1.81","3.57","5.30","6.97","8.61","10.20","11.76","13.28","14.76","16.21","17.62","18.99","20.34","21.65","22.93","24.18","25.40","26.60","27.76","28.90","30.01","31.10","32.16","33.19","34.21","35.20","36.17","37.12","38.04","38.95","39.83","40.70","41.55","42.38","43.19","43.98","44.76","45.52","46.26","46.99","47.70","48.40","49.09","49.76","50.41","51.05","51.68","52.30","52.91","53.50","54.08","54.65","55.20","55.75","56.29","56.81","57.33","57.83","58.33","58.82","59.29","59.76","60.22","60.67","61.11","61.55","61.97","62.39","62.80","63.21","63.60","63.99","64.37","64.75","65.12","65.48","65.83","66.18","66.53","66.86");
               var cuota22 = new Array ("1.89","3.73","5.53","7.27","8.98","10.63","12.25","13.83","15.36","16.86","18.31","19.74","21.12","22.47","23.79","25.08","26.33","27.56","28.75","29.92","31.06","32.17","33.25","34.31","35.35","36.36","37.34","38.31","39.25","40.17","41.07","41.95","42.80","43.64","44.46","45.27","46.05","46.82","47.57","48.30","49.02","49.72","50.41","51.08","51.74","52.38","53.02","53.63","54.24","54.83","55.41","55.98","56.53","57.08","57.61","58.14","58.65","59.15","59.65","60.13","60.60","61.07","61.52","61.97","62.41","62.84","63.26","63.67","64.08","64.48","64.87","65.25","65.63","66.00","66.36","66.72","67.07","67.41","67.75","68.08");
               var cuota23 = new Array ("1.97","3.89","5.76","7.57","9.34","11.06","12.74","14.37","15.95","17.50","19.00","20.47","21.90","23.29","24.64","25.96","27.25","28.51","29.73","30.92","32.09","33.22","34.33","35.41","36.46","37.49","38.49","39.47","40.42","41.35","42.26","43.15","44.02","44.87","45.70","46.51","47.30","48.07","48.83","49.56","50.29","50.99","51.68","52.35","53.01","53.66","54.29","54.91","55.51","56.10","56.68","57.25","57.80","58.35","58.88","59.40","59.91","60.41","60.90","61.38","61.85","62.31","62.76","63.20","63.63","64.06","64.48","64.88","65.28","65.68","66.06","66.44","66.81","67.18","67.53","67.88","68.23","68.57","68.90","69.22");
               var cuota24 = new Array ("2.05","4.05","5.99","7.87","9.70","11.49","13.22","14.90","16.54","18.13","19.68","21.19","22.66","24.09","25.48","26.83","28.15","29.44","30.69","31.90","33.09","34.25","35.37","36.47","37.54","38.58","39.60","40.59","41.56","42.51","43.43","44.32","45.20","46.06","46.89","47.71","48.50","49.28","50.04","50.78","51.50","52.21","52.90","53.58","54.24","54.88","55.51","56.13","56.73","57.32","57.90","58.46","59.02","59.56","60.08","60.60","61.11","61.60","62.09","62.56","63.03","63.48","63.93","64.37","64.80","65.22","65.63","66.03","66.43","66.81","67.19","67.57","67.93","68.29","68.64","68.98","69.32","69.65","69.98","70.30");
 
      //Permite que se ingrese un número decimal de decimales definidos en un Input Text
				function soloDecimalesPrecisos(InputText, evt, cantDeci) {
					var charCode = (evt.which) ? evt.which : event.keyCode;
					var strValue = '';
					var canti = parseInt(cantDeci);
				 	var selectionStart = getSelectionStart(InputText);
				  	var selectionEnd = getSelectionEnd(InputText);
					strValue = InputText.value.substring(0, selectionStart) + String.fromCharCode(charCode) + InputText.value.substring(selectionEnd);
					if (!isDecimalConNDecimales(strValue, canti) && charCode > 31) {
						return false;
					}		
					return true;
				}
				
				
				// Retorna true si inputVal es decimal.
				function isDecimalConNDecimales(inputVal, numeroDecimales) {
					oneDecimal = false;
					cantidades = 0;
					inputStr = inputVal.toString();
					for (var i = 0; i < inputStr.length; i++) {
						var oneChar = inputStr.charAt(i);
						if (i == 0 && oneChar == "-") {
							continue;
						}
						if (oneDecimal) {
						    cantidades = cantidades + 1;
						    if (cantidades > parseInt(numeroDecimales)) {return false;}
						}
						if (oneChar == "." && !oneDecimal) {
							oneDecimal = true;
							continue;
						}
						if (oneChar < "0" || oneChar > "9") {
							return false;
						}
					}
					return true;
				}	
				
			//	function rellenarTEAS() {
			//		for(i=0; document.getElementById('amListaPrecioForm:tablaDeItems:'+i+':TEAcomercio')!=null; i++) {
			//			var auxCom = document.getElementById('amListaPrecioForm:tablaDeItems:'+i+':tnaComercio');
			//			var auxCli = document.getElementById('amListaPrecioForm:tablaDeItems:'+i+':tnaCliente');
			//			teaComercio(auxCom.value,auxCom);
			//			teaCliente(auxCli.value,auxCli);
			//	        }
			//	return true;
            //    }
				
				// Calcula variables del comercio a partir del tna Comercio
				function teaComercio(num, InputText) {
                    var temS;
                    var cadena = InputText.id;
 		 	    	cadena = cadena.substring(31,cadena.length - 12);
					// calculo Tem
					var tna = parseFloat(num);
					var tem = tna/12;
                    if (tna!=0) {
						temS = tem;
						tem = (Math.round(tem*10000)/10000);
		                var t=tem+"";
	                    tem = parseFloat(t.substring(0,(t.indexOf(".")+4)));
	                    
						
						document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':TEMcomercio').value = tem;
	
						// calculo tea [=var tna = parseFloat(num);
						var tea = Math.pow((1 + temS/100),12)-1;
						tea = (Math.round(tea*1000000)/10000);
	                     //tea = parseFloat(t.substring(0,(t.indexOf(".")+4)));
						document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':TEAcomercio').value = tea;
	
						// calculo factor [=var tna = parseFloat(num);
						var cuota = parseFloat(document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':comCuotas').value);
	                    var cuan = Math.pow((1 + temS/100),-cuota);
						var coeficiente = (1/((1 - cuan)/temS));
						coeficiente = (Math.round(coeficiente*100)/10000);
						document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':coeficiente').value = coeficiente;
						
						var factor = coeficiente*cuota;
						factor = (Math.round(factor*10000)/10000);
		                //factor = parseFloat(t.substring(0,(t.indexOf(".")+4)));
						document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':factor').value = factor;
	
						var direc = (factor - 1)/factor;
						direc = (Math.round(direc*10000)/10000)*100;
		                //factor = parseFloat(t.substring(0,(t.indexOf(".")+4)));
						document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':accesKey').value = direc;
                    } else {
                    	document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':tnaComercio').value = 0;
                    	document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':TEMcomercio').value = 0;
                    	document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':TEAcomercio').value = 0;
                    	document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':coeficiente').value = 1;
                    	document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':factor').value = 1;
                    	document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':accesKey').value = 0;
                    }
					return true;
				}

				// Calcula variables del comercio a partir del tna Comercio
				function teaComercioItemFiel(num, InputText) {
                    var temS;
                    var cadena = InputText.id;
                   

                   // amListaPrecioForm:tablaDeItemsFiel:0:tnaFiel
 		 	    	cadena = cadena.substring(35,cadena.length - 8);
					var tna = parseFloat(num);
					var tem = tna/12;
                    if (tna!=0) {
						temS = tem;
						tem = (Math.round(tem*10000)/10000);
		                var t=tem+"";
	                    tem = parseFloat(t.substring(0,(t.indexOf(".")+4)));
	                    
						
						document.getElementById('amListaPrecioForm:tablaDeItemsFiel:'+ cadena +':temFiel').value = tem;
	
                    } else {
                    	document.getElementById('amListaPrecioForm:tablaDeItemsFiel:'+ cadena +':tnaFiel').value = 0;
                    	document.getElementById('amListaPrecioForm:tablaDeItemsFiel:'+ cadena +':temFiel').value = 0;
                    }
					return true;
				}
				
				
				// Calcula variables del comercio a partir del tna Comercio
				function calculaTem(num, InputText) {
                    var temS;
                    var cadena = InputText.id;
                    //alert(cadena); 
                    //amListaPrecioForm:idTnaFiel
 		 	    	//cadena = cadena.substring(32,cadena.length - 8);
					// calculo Tem
					var tna = parseFloat(num);
					var tem = tna/12;
                    if (tna!=0) {
						temS = tem;
						tem = (Math.round(tem*10000)/10000);
		                var t=tem+"";
	                    tem = parseFloat(t.substring(0,(t.indexOf(".")+4)));
						document.getElementById('amListaPrecioForm:idTemFiel').value = tem;
	
                    } else {
                    	document.getElementById('amListaPrecioForm:idTemFiel').value = 0;
                    	//document.getElementById('amListaPrecioForm:tablaItemFiel:'+ cadena +':temFiel').value = 0;
                    }
					return true;
				}
				

				// Calcula el variables a partir del % directo Comercio
				function teaComercioDesdeDirecto(num, InputText) {
				  	 var cadena = InputText.id;
 		 	    	 cadena = cadena.substring(31,cadena.length - 9);
 				   	 var cuota = parseFloat(document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':comCuotas').value);
					 var directo = parseFloat(num);
					//alert('directo ' +  directo);
					 var tna;
	                  switch (cuota) {
/*@I7798*/	                  case 1:
	                    	 for (j=0; j<cuota1.length;j++) {
	 	 		 	    		if (directo < parseFloat(cuota1[j])) {
	 								tna = 0;
	 								break;
	 							} 
	 						}
	                           break;
                     case 2:
                    	 for (j=0; j<cuota2.length;j++) {
                    		 if (directo <= parseFloat(cuota2[j])) {
  	 		 	    			if (directo < parseFloat(cuota2[j])) {
  								tna = 2*j + 1;
  								break;
  	 		 	    			}	else {
  								tna = 2*j + 2;
  								break;
  	 		 	    		} 							 
  						}
                    	}
                           break;
                     case 3:
                    	 for (j=0; j<cuota3.length;j++) {
                    	//	 alert('parseFloat(cuota3[j]) ' +  parseFloat(cuota3[j]));
 	 		 	    		if (directo <= parseFloat(cuota3[j]) ) {
 	 		 	    			if (directo < parseFloat(cuota3[j])) {
 								tna = 2*j + 1;
 								break;
 	 		 	    			}	else {
 								tna = 2*j + 2;
 								break;
 	 		 	    		} 							 
 						}
                    	 }
                         break;
                     case 4:
                    	 for (j=0; j<cuota4.length;j++) {
                    	 if (directo <= parseFloat(cuota4[j]) ) {
	 		 	    			if (directo <= parseFloat(cuota4[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 5:
                    	 for (j=0; j<cuota5.length;j++) {
                    	 if (directo <= parseFloat(cuota5[j]) ) {
	 		 	    			if (directo < parseFloat(cuota5[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 6:
                    	 for (j=0; j<cuota6.length;j++) {
                    	 if (directo <= parseFloat(cuota6[j]) ) {
	 		 	    			if (directo < parseFloat(cuota6[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 7:
                    	 for (j=0; j<cuota7.length;j++) {
                    	 if (directo <= parseFloat(cuota7[j]) ) {
	 		 	    			if (directo < parseFloat(cuota7[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                    
                     case 8:
                    	 for (j=0; j<cuota8.length;j++) {
                    	 if (directo <= parseFloat(cuota8[j]) ) {
	 		 	    			if (directo < parseFloat(cuota8[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 9:
                    	 for (j=0; j<cuota9.length;j++) {
                    	 if (directo <= parseFloat(cuota9[j]) ) {
	 		 	    			if (directo < parseFloat(cuota9[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 10:
                    	 for (j=0; j<cuota10.length;j++) {
                    	 if (directo <= parseFloat(cuota10[j]) ) {
	 		 	    			if (directo < parseFloat(cuota10[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;


                     case 11:
                    	 for (j=0; j<cuota11.length;j++) {
                    	 if (directo <= parseFloat(cuota11[j]) ) {
	 		 	    			if (directo < parseFloat(cuota11[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 12:
                    	 for (j=0; j<cuota12.length;j++) {
                   		 //alert('parseFloat(cuota12[j]) ' +  parseFloat(cuota12[j]));
                    	 if (directo <= parseFloat(cuota12[j]) ) {
	 		 	    			if (directo < parseFloat(cuota12[j])) {
	 		 	    			//	alert('no iguales ' +  parseFloat(cuota12[j]) + ' ' + directo);
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
	 		 	    			//	alert('iguales ' +  parseFloat(cuota12[j]) + ' ' + directo);
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 13:
                    	 for (j=0; j<cuota13.length;j++) {
                    	 if (directo <= parseFloat(cuota13[j]) ) {
	 		 	    			if (directo < parseFloat(cuota13[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 14:
                    	 for (j=0; j<cuota14.length;j++) {
                    	 if (directo <= parseFloat(cuota14[j]) ) {
	 		 	    			if (directo < parseFloat(cuota14[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 15:
                    	 for (j=0; j<cuota15.length;j++) {
                    	 if (directo <= parseFloat(cuota15[j]) ) {
	 		 	    			if (directo < parseFloat(cuota15[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 16:
                    	 for (j=0; j<cuota16.length;j++) {
                    	 if (directo <= parseFloat(cuota16[j]) ) {
	 		 	    			if (directo < parseFloat(cuota16[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 17:
                    	 for (j=0; j<cuota17.length;j++) {
                    	 if (directo <= parseFloat(cuota17[j]) ) {
	 		 	    			if (directo < parseFloat(cuota17[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 18:
                    	 for (j=0; j<cuota18.length;j++) {
                    	 if (directo <= parseFloat(cuota18[j]) ) {
	 		 	    			if (directo < parseFloat(cuota18[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 19:
                    	 for (j=0; j<cuota19.length;j++) {
                    	 if (directo <= parseFloat(cuota19[j]) ) {
	 		 	    			if (directo < parseFloat(cuota19[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 20:
                    	 for (j=0; j<cuota20.length;j++) {
                    	 if (directo <= parseFloat(cuota20[j]) ) {
	 		 	    			if (directo < parseFloat(cuota20[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 21:
                    	 for (j=0; j<cuota21.length;j++) {
                    	 if (directo <= parseFloat(cuota21[j]) ) {
	 		 	    			if (directo < parseFloat(cuota21[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 22:
                    	 for (j=0; j<cuota22.length;j++) {
                    	 if (directo <= parseFloat(cuota22[j]) ) {
	 		 	    			if (directo < parseFloat(cuota22[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 23:
                    	 for (j=0; j<cuota23.length;j++) {
                    	 if (directo <= parseFloat(cuota23[j]) ) {
	 		 	    			if (directo < parseFloat(cuota23[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                     case 24:
                    	 for (j=0; j<cuota24.length;j++) {
                    	 if (directo <= parseFloat(cuota24[j]) ) {
	 		 	    			if (directo < parseFloat(cuota24[j])) {
								tna = 2*j + 1;
								break;
	 		 	    			}	else {
								tna = 2*j + 2;
								break;
	 		 	    		} 							 
						}
                    	 }
                         break;
                    
	/*@F7798*/                  }
					 
					 
                    var temS;
                    if (cuota != 1) {
                         document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':tnaComercio').value = tna;
						// calculo Veo com recupero la tna de la lista pasada...
						var tem = tna/12;
						temS = tem;
						tem = (Math.round(tem*10000)/10000);
		                var t=tem+"";
	                    tem = parseFloat(t.substring(0,(t.indexOf(".")+4)));
						document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':TEMcomercio').value = tem;
						// calculo tea [=var tna = parseFloat(num);
						var tea = Math.pow((1 + temS/100),12)-1;
						tea = (Math.round(tea*1000000)/10000);
	                     //tea = parseFloat(t.substring(0,(t.indexOf(".")+4)));
						document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':TEAcomercio').value = tea;
						// calculo factor [=var tna = parseFloat(num);
	                    var cuan = Math.pow((1 + temS/100),-cuota);
						var coeficiente = (1/((1 - cuan)/temS));
						coeficiente = (Math.round(coeficiente*100)/10000);
						document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':coeficiente').value = coeficiente;
	
						var factor = coeficiente*cuota;
						factor = (Math.round(factor*10000)/10000);
		                //factor = parseFloat(t.substring(0,(t.indexOf(".")+4)));
						document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':factor').value = factor;
                    } else {
                    	document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':tnaComercio').value = 0;
                    	document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':TEMcomercio').value = 0;
                    	document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':TEAcomercio').value = 0;
                    	document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':coeficiente').value = 1;
                    	document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':factor').value = 1;
                    }
				}


				
				
				// Calcula el TEA Cliente
				function teaCliente(num, InputText) {
					var tna = parseFloat(num);
				    tna = tna/100;
					var tea = Math.pow((1 + tna*30/365),365/30);
					tea = (Math.round(tea*1000000)/1000000 - 1);
					var t=tea+"";
                    tea = parseFloat(t.substring(0,(t.indexOf(".")+7)));
					var cadena = InputText.id;
 		 	    	cadena = cadena.substring(31,cadena.length - 11);
					document.getElementById('amListaPrecioForm:tablaDeItems:'+ cadena +':TEAcliente').value = tea;
					return true;
				}
		
		
		</script> 

<center>
	<secure:check/>

	<h:form  id="amListaPrecioForm"  enctype="multipart/form-data">

	<%-- GRABA EL ESTADO DEL SCROLL(Dibujar solo si el popup no se muestra) --%>
	<h:panelGroup rendered="#{!ListaPrecioBean.popup.mostrar}">
	<%@include file="/inc/scroll.inc" %>
	</h:panelGroup>

		<x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
			styleClass="pageLayout"
			headerClass="pageHeader"
			navigationClass="pageNavigation"
			bodyClass="pageBody"
			footerClass="pageFooter" >



			<f:facet name="body">
				<h:panelGroup id="body">

					<h:panelGrid columns="1" align="center" id="PanelPricipal" width="900">
						<%-- INCLUDE PARA LOS ERRORES --%>
						<h:panelGroup id="errores">
							<jsp:include page="/inc/error.jsp" />
						</h:panelGroup>

						<%-- POPUP PARA LOS MENSAJES DE EXITO Y FRACASO --%>
						<s:modalDialog dialogId="viewDialog" dialogVar="viewDialog" styleClass="viewDialog" dialogTitle="#{ListaPrecioBean.tituloCorto}">
									
							<h:panelGrid columns="2" width="500">
								<x:graphicImage value="/img/#{ListaPrecioBean.popup.nombreIcono}" />
								<h:outputText value="#{ListaPrecioBean.popup.mensaje}" styleClass="texto"/>
							</h:panelGrid>
							
							<h:panelGrid columns="3" width="500">
								<x:commandButton action="#{ListaPrecioBean.irANuevoListaPrecio}" onclick="clickLink('nuevoListaPrecio');dojo.widget.byId('viewDialog').hide();"
									value="Nuevo" styleClass="btn btn-primary btn-flat" title="Agregar nueva."/>
								<x:commandButton action="#{ListaPrecioBean.irAModificarListaPrecio}" onclick="clickLink('modificarListaPrecio');dojo.widget.byId('viewDialog').hide();"
									value="Modificar" styleClass="btn btn-primary btn-flat" title="Seguir modificando."/>
								<x:commandButton action="#{ListaPrecioBean.irAListarListaPrecio}" onclick="clickLink('listarListaPrecio');dojo.widget.byId('viewDialog').hide();"
									value="Listar" styleClass="btn btn-primary btn-flat" title="Ir al listado."/>
							</h:panelGrid>
						
						</s:modalDialog>
						
						<x:commandLink id="nuevoListaPrecio" action="#{ListaPrecioBean.irANuevoListaPrecio}" forceId="true" style="display: block;"/>
						<x:commandLink id="modificarListaPrecio" action="#{ListaPrecioBean.irAModificarListaPrecio}" forceId="true" style="display: block;"/>
						<x:commandLink id="listarListaPrecio" action="#{ListaPrecioBean.irAListarListaPrecio}" forceId="true" style="display: block;"/>

                        <h:panelGrid columns="2" id="decisionFielComercio" align="center" rendered="#{ListaPrecioBean.mostrarOpciones}">
                        	<h:outputText value="Seleccione que tipo de lista de precio desea dar de alta: " styleClass="texto" style="color:green" />
						    <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
							<h:selectOneRadio value="#{ListaPrecioBean.tipoLista}" id="tipoCuent" styleClass="radioB">
								<f:selectItem itemLabel="Lista Precio Comercios" itemValue="N" />
								<f:selectItem itemLabel="Lista Precio Fiel" itemValue="S" />
							</h:selectOneRadio>
                            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                            <h:commandButton action="#{ListaPrecioBean.aceptarTipoLista}" value="Aceptar" id="decidirTipoLista" styleClass="btn btn-primary btn-flat"/>
								      
                        </h:panelGrid>
                        <h:panelGrid columns="11" id="present" align="center" rendered="#{!ListaPrecioBean.mostrarOpciones}">
                        	<h:outputText value="Descripción:" styleClass="texto"/>
                        	<f:verbatim>&nbsp;&nbsp;</f:verbatim>
							<h:inputText id="iddescripcion" value="#{ListaPrecioBean.listaPrecio.descripcion}" disabled="#{!ListaPrecioBean.alta}"
                        	styleClass="bordeceldatext" maxlength="50"	style="width: 250px" onfocus="encender(this);" onblur="apagar(this);"/>
                        	<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						    <h:outputText value="Activo:" styleClass="texto"/>
						    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
						    <h:selectBooleanCheckbox value="#{ListaPrecioBean.listaPrecio.estaActivo}" id="checkActivo" disabled="#{!ListaPrecioBean.alta}"/>
						    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                            <h:outputText value="Es producto Fiel:" styleClass="texto"/>
                            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
						    <h:selectBooleanCheckbox value="#{ListaPrecioBean.perteneceAFiel}" id="checkEsdeFiel" disabled="true"/>
                        </h:panelGrid>

                        <f:verbatim>&nbsp;</f:verbatim>

                        <h:panelGrid columns="5" id="presentSeleccionable" align="center" rendered="#{!ListaPrecioBean.mostrarOpciones}">
                        	<h:outputText value="Seleccione el checkbox si desea que la lista de precios se pueda asignar a futuros comercios" styleClass="texto"/>
                        	<f:verbatim>&nbsp;&nbsp;</f:verbatim>
						    <h:selectBooleanCheckbox value="#{ListaPrecioBean.listaPrecio.disponibleSeleccion}" id="checkEsSeleccionable" />
						    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                            <h:commandButton id="cambioDisponibilidad" value="Cambiar Disponibilidad" action="#{ListaPrecioBean.cambiarDisponibilidad}" styleClass="btn btn-primary btn-flat"/>
                        </h:panelGrid>

                        <h:panelGrid columns="3" id="versionesExistentes" align="center" width="700" rendered="#{!ListaPrecioBean.alta}">
                        	<h:outputText value="Versiones Anteriores:" styleClass="texto"/>
                        	<h:outputText value="Version Actual:" styleClass="texto"/>
                        	<h:outputText value="Versiones Futuras:" styleClass="texto"/>
                        	
                        	<c:choose>
						        <c:when test="${!empty ListaPrecioBean.listaPrecio.versionesAnteriores}">
		                            <h:dataTable id="tblVersionesAnt" value="#{ListaPrecioBean.listaPrecio.versionesAnteriores}" var="verAnt">
		                            	 <h:column>
			                            	<x:commandLink id="verVersionesAnt" action="#{ListaPrecioBean.editarVersionAnterior}" value="Versión: #{verAnt.version} - Fecha Inicio: #{verAnt.diaMesAnioVigencia}" >
												<f:param id="idVerAnterior" name="idVerAnterior" value="#{verAnt.version}"/>
											</x:commandLink>
										</h:column>
		                            </h:dataTable>
                             	</c:when>
						        <c:otherwise>
						        	<h:outputText value="No existen versiones anteriores." styleClass="texto" style="color:green"/>
						        </c:otherwise>
						    </c:choose>
							<c:choose>
						        <c:when test="${null != ListaPrecioBean.listaPrecio.versionVigente}">
						            <x:commandLink id="verNuevosLink" action="#{ListaPrecioBean.editarVersionVigente}" value="Versión: #{ListaPrecioBean.listaPrecio.versionVigente.version} - Fecha Inicio: #{ListaPrecioBean.listaPrecio.versionVigente.diaMesAnioVigencia}" >
										<f:param id="idVerActual" name="idVerActual" value="1"/>
									</x:commandLink>
						        </c:when>
						        <c:otherwise>
						            <h:outputText value="No existe una version actual." styleClass="texto" style="color:green"/>
						        </c:otherwise>
						    </c:choose>
							<c:choose>
						        <c:when test="${!empty ListaPrecioBean.listaPrecio.versionesFuturas}">
		                            <h:dataTable id="tblVersionesFut" value="#{ListaPrecioBean.listaPrecio.versionesFuturas}" var="verFut">
		                                <h:column>
			                            	<x:commandLink id="verVersionesFut" action="#{ListaPrecioBean.editarVersionFutura}" value="Versión: #{verFut.version} - Fecha Inicio: #{verFut.diaMesAnioVigencia}" >
												<f:param id="idVerFutura" name="idVerFutura" value="#{verFut.version}"/>
											</x:commandLink>
										</h:column>
		                            </h:dataTable>
		                        </c:when>
						        <c:otherwise>
						            <h:outputText value="No existen versiones futuras." styleClass="texto" style="color:green"/>
						        </c:otherwise>
						    </c:choose>
                        </h:panelGrid>
                        <h:panelGrid columns="1" align="center" id="fiell" rendered="#{!ListaPrecioBean.mostrarOpciones}">

                        
						<s:fieldset legend="Lista Precio" id="fieldPrincipalUno" style=" width : 700px;" rendered="#{!ListaPrecioBean.perteneceAFiel}">  
						<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1" id="boxLP">  
						      <h:panelGrid id="panDescripcion" columns="9" align="center" style=" width : 700px;">
						            <h:outputText value="Arancel Comercio:" id="outArancelDelComercio" styleClass="texto"/>
									<h:inputText id="idArancel" value="#{ListaPrecioBean.versionEditada.comArancel}" onkeypress="return soloDecimalesPrecisos(this,event,2);"
									styleClass="bordeceldainferior"	style=" width : 250px; height : 22px;" onfocus="encender(this);" onblur="apagar(this);"/>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						            <h:outputText value="Fecha Vigencia:" id="outFechaVigencia" styleClass="texto"/>
									<f:verbatim>
							                <div class="input-group date">
							                    <div class="input-group-addon">
							                        <i class="fa fa-calendar"></i>
							                    </div>
							                    <input type="text" class="form-control pull-right" id="fDesde" placeholder="DD/MM/AAAA">
							                </div>
									</f:verbatim>
								    <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<h:outputText value="Version:" id="outVersion" styleClass="texto"/>
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<h:outputText value="#{ListaPrecioBean.versionEditada.version}" id="outversionEditada" styleClass="textoblue"/>	
						      </h:panelGrid>
						      <h:panelGrid columns="16" id="cicloDias" align="center">
						             <h:outputText value="Ciclo:" styleClass="texto" id="ciclo"/>
						             <f:verbatim>&nbsp;&nbsp;</f:verbatim>
						             <h:inputText id="idComCiclo" value="#{ListaPrecioBean.versionEditada.comCiclo}"  onkeypress="return soloEnteros(this,event);"
									           styleClass="bordeceldainferior"	style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"/>
									 <f:verbatim>&nbsp;&nbsp;</f:verbatim>
						             <h:outputText value="Días:" styleClass="texto" id="dias"/>
						             <f:verbatim>&nbsp;&nbsp;</f:verbatim>
						              <h:inputText id="idComDias" value="#{ListaPrecioBean.versionEditada.comDias}"  onkeypress="return soloEnteros(this,event);"
									 styleClass="bordeceldainferior"	style="width: 100px" onfocus="encender(this);" onblur="apagar(this);"/>
									 <f:verbatim>&nbsp;&nbsp;</f:verbatim>
						             <h:outputText value="Fechas:" styleClass="texto" id="fechas"/>
						             <f:verbatim>&nbsp;&nbsp;</f:verbatim>
						              <h:inputText id="idfechaLiquidacion" value="#{ListaPrecioBean.versionEditada.fechaLiquidacion}"  onkeypress="return soloEnteros(this,event);"
								      styleClass="bordeceldainferior"	style=" width : 100px;" onfocus="encender(this);" onblur="apagar(this);"/>
								      <f:verbatim>&nbsp;&nbsp;</f:verbatim>
								      <h:commandButton action="#{ListaPrecioBean.verCalendario}" value="Ver Calendario de Pagos" id="calenDePagos" styleClass="btn btn-primary btn-flat"/>
								      <f:verbatim>&nbsp;&nbsp;</f:verbatim>
								      <h:outputText value="Difiere Liquidación" id="difLiq" />
						              <h:selectBooleanCheckbox value="#{ListaPrecioBean.versionEditada.difiereLiquidacionBoolean}" id="checkDifiere"/>
						              
						      </h:panelGrid>
						      <h:panelGrid columns="1" id="panelDelCalendario" align="center" rendered="#{ListaPrecioBean.calendarioVisible}">
						      	<c:choose>
						        <c:when test="${!empty ListaPrecioBean.diasDePago}">
		                            <h:dataTable id="tblDelCalendario" value="#{ListaPrecioBean.diasDePago}" var="dias">
		                            	<h:column>
			                            	<h:outputText id="fechasLiquidacion" value="Día Cierre: #{dias.fechaLiquidacion}" />
										</h:column>
										<h:column>
			                            	<h:outputText id="diasFechaPago" value="Día Liquidación: #{dias.fechaPago}" />
										</h:column>
		                            </h:dataTable>
                             	</c:when>
						        <c:otherwise>
						        	<h:outputText id="estadoCalendario" value="#{ListaPrecioBean.estadoCalendario}" styleClass="texto" style="color:green"/>
						        </c:otherwise>
						    </c:choose>
						      <h:commandButton action="#{ListaPrecioBean.ocultarCalendario}" value="Ocultar" id="ocultarCalendario" styleClass="btn btn-primary btn-flat" />
						      
						      </h:panelGrid>

						      	<h:inputText id="FechaVigencia" value="#{ListaPrecioBean.fechaVigencia}" style="display: none;">
							        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
							    </h:inputText>

						</h:panelGrid>
						</s:fieldset>
						
						
						<s:fieldset legend="Lista Precio Fiel" id="fieldPrincipalUnoFiel" style=" width : 700px;" rendered="#{ListaPrecioBean.perteneceAFiel}">
						<h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1" id="boxLPF" > 
						      <h:panelGrid id="panDescripcionFiel" columns="14" align="center" style=" width : 650px;"> 
						            <h:outputText value="TNA:" styleClass="texto" id="pdtna"/>
						            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<h:inputText id="idTnaFiel" value="#{ListaPrecioBean.versionEditada.tnaFiel}" onkeypress="return soloDecimalesPrecisos(this,event,2);"
									styleClass="bordeceldainferior"	style="height : 22px; width : 112px;" onfocus="encender(this);" onblur="apagar(this);" onkeyup="calculaTem(this.value, this)"/>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<h:outputText value="TEM:" styleClass="texto" id="pdtem"/>
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<h:inputText id="idTemFiel" value="#{ListaPrecioBean.versionEditada.temFiel}" onkeypress="return soloDecimalesPrecisos(this,event,2);"
									styleClass="bordeceldainferior"	style="height : 22px; width : 112px;" onfocus="encender(this);" onblur="apagar(this);"/>
									<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						            <h:outputText value="Fecha Vigencia:" styleClass="texto" id="pdvigencia"/>
									<f:verbatim>
							                <div class="input-group date">
							                    <div class="input-group-addon">
							                        <i class="fa fa-calendar"></i>
							                    </div>
							                    <input type="text" class="form-control pull-right" id="fHasta" placeholder="DD/MM/AAAA">
							                </div>
									</f:verbatim>
								    <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
									<h:outputText value="Version: " styleClass="texto" id="pdVersion"/>	
									<f:verbatim>&nbsp;&nbsp;</f:verbatim>
									<h:outputText value="#{ListaPrecioBean.versionEditada.version}" id="pdVerEdi" styleClass="textoblue"/>	
						      </h:panelGrid>

						      	<h:inputText id="fechaVigenciaFiel" value="#{ListaPrecioBean.fechaVigencia}" style="display: none;">
							        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
							    </h:inputText>
						</h:panelGrid>
						</s:fieldset>
							
						</h:panelGrid>

						<f:verbatim>&nbsp;</f:verbatim>

						<h:panelGrid columns="1" id="panelDeItemsGenerales" align="center" rendered="#{!ListaPrecioBean.mostrarOpciones}">
						<h:panelGrid columns="1" id="ocul" align="center" rendered="#{!ListaPrecioBean.perteneceAFiel}">
						<h:panelGrid id="tablaItem" columns="1" align="center" rendered="#{!ListaPrecioBean.calendarioVisible}">
                             <c:if test="${!empty ListaPrecioBean.items}">			
								<h:dataTable value="#{ListaPrecioBean.items}" id="tablaDeItems"
									styleClass="standardTable"
							        headerClass="standardTable_Header"
							        footerClass="standardTable_Header"
							        rowClasses="standardTable_Row1,standardTable_Row2"
							        columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"							
									var="item" style=" width : 700px;">
						            
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="Comercio Cuota" styleClass="texto" />
						                     </f:facet>
						                     <h:inputText id="comCuotas"
						                      value="#{item.listaPrecioItem.comCuotas}" style="width: 40px" styleClass="bordeceldainferior" onkeypress="return soloEnteros(this,event);" onfocus="encender(this);" onblur="apagar(this);" />
						            </h:column>	
	
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="Comercio % TNA" styleClass="texto" />
						                     </f:facet>
						                     <h:inputText id="tnaComercio"
						                     value="#{item.listaPrecioItem.comTNA}" 
						                     style="width: 60px"  styleClass="bordeceldainferior" onkeypress="return soloDecimalesPrecisos(this,event, 2);" onfocus="encender(this);" onblur="apagar(this);" onchange="teaComercio(this.value, this);" />
						            </h:column>

                                    <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="TEM comercio" styleClass="texto" />
						                     </f:facet>
						                     <h:inputText id="TEMcomercio" value="#{item.listaPrecioItem.comTem}" maxlength="8" style="width: 80px"  styleClass="bordeceldainferior"  onkeypress="return false;"/>
						            </h:column>						            

						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="TEA comercio" styleClass="texto" />
						                     </f:facet>
						                     <h:inputText id="TEAcomercio" value="#{item.listaPrecioItem.comTEA}" maxlength="8" style="width: 80px; "  styleClass="bordeceldainferior" onkeypress="return false;"/>
						            </h:column>

									<h:column>
						                     <f:facet name="header">
						                         <h:outputText value="Factor" styleClass="texto" />
						                     </f:facet>
						                     <h:inputText id="factor" value="#{item.listaPrecioItem.comFactor}" maxlength="8" style="width: 80px;"  styleClass="bordeceldainferior"  onkeypress="return false;"/>
						            </h:column>

									<h:column>
						                     <f:facet name="header">
						                         <h:outputText value="Coeficiente" styleClass="texto" />
						                     </f:facet>
						                     <h:inputText id="coeficiente" value="#{item.listaPrecioItem.comCoeficiente}" maxlength="8" style="width: 80px;"  styleClass="bordeceldainferior"  onkeypress="return false;"/>
						            </h:column>
						                        
						            <h:column>
						                  <f:facet name="header">
						                      <h:outputText value="Comercio % directo" styleClass="texto" />
						                  </f:facet>
						                  <h:inputText id="accesKey" 
						                   value="#{item.listaPrecioItem.comPorcentajeDirecto}" style="width: 60px" styleClass="bordeceldainferior" onkeypress="return soloDecimalesPrecisos(this, event, 2);" onfocus="encender(this);" onblur="apagar(this);" onkeyup="teaComercioDesdeDirecto(this.value, this);" />
						            </h:column>
						            
                                    <h:column>
						                 <f:facet name="header">
						                     <h:outputText value="Dias Diferimiento Comercio" styleClass="texto" />
						                 </f:facet>
						                 <h:inputText id="diasDif"
						                  value="#{item.listaPrecioItem.comDiasDiferimiento}" style="width: 40px; align: right;" styleClass="bordeceldainferior" onkeypress="return soloEnteros(this,event);" onfocus="encender(this);" onblur="apagar(this);"/>
						            </h:column>							            

						            <h:column >
						                  <f:facet name="header">
						                      <h:outputText value="Dias Diferimiento Cliente" styleClass="texto" />
						                  </f:facet> 
						                  <h:inputText id="cliDias"
						                  value="#{item.listaPrecioItem.cliDiasDiferimiento}" style="width: 40px" styleClass="bordeceldainferior" onkeypress="return soloEnteros(this,event);" onfocus="encender(this);" onblur="apagar(this);" />
						            </h:column>
						            
						            <h:column>
						                  <f:facet name="header">
						                      <h:outputText value="Cliente % TNA" styleClass="texto" />
						                  </f:facet>
						                  <h:inputText id="tnaCliente" value="#{item.listaPrecioItem.cliTNA}" styleClass="bordeceldainferior" style="width: 60px" onkeypress="return soloDecimalesPrecisos(this, event, 2);" onfocus="encender(this);" onblur="apagar(this);"  onkeyup="teaCliente(this.value, this);"/>
						            </h:column>
						            
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="TEA cliente" styleClass="texto" />
						                     </f:facet>
						                     <h:inputText id="TEAcliente" value="#{item.listaPrecioItem.cliTEA}" onkeydown="if (window.event.keyCode==13) {return false;}" style="width: 80px; align: right;" maxlength="8" styleClass="textoblue" onkeypress="return false;" />
						            </h:column>
						                        
						            <h:column>
										  <x:commandLink action="#{ListaPrecioBean.eliminarItem}" id="eliminarItemLink">
											  <f:param id="idItemElim" name="idItemElim" value="#{item.indice}"/>
										      <x:graphicImage value="/img/cat_act.gif" title="Eliminar el item." border="0"/>
										  </x:commandLink>
									</h:column>
								</h:dataTable>
					        </c:if>

                    		<c:if test="${empty ListaPrecioBean.items}">
								 <h:outputText value="No existen Items de Pago para la Lista de Precios." id="outNoHAYitems" styleClass="texto" style="color:green"/>
							</c:if>	
							<h:panelGrid id="agre" columns="1" align="right" width="700">
								<f:verbatim>&nbsp;</f:verbatim>
			                    <h:commandButton onkeypress="return null;" id="botonCargarItem" value="Agregar Item" action="#{ListaPrecioBean.agregarItem}" styleClass="btn btn-primary btn-flat pull-right" />
			                </h:panelGrid>					
						    
						
						</h:panelGrid>
						</h:panelGrid>
						
						<h:panelGrid id="tablaItemFiel" columns="1" align="center" rendered="#{ListaPrecioBean.perteneceAFiel}">
                             <c:if test="${!empty ListaPrecioBean.itemsFiel}">		
                             	<f:verbatim>&nbsp;</f:verbatim>	
								<h:dataTable value="#{ListaPrecioBean.itemsFiel}" id="tablaDeItemsFiel"
									styleClass="standardTable"
							        headerClass="standardTable_Header"
							        footerClass="standardTable_Header"
							        rowClasses="standardTable_Row1,standardTable_Row2"
							        columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"							
									var="item" style=" width : 300px;">
						            <h:column>
						                 <f:facet name="header">
						                     <h:outputText value="Nro.Cuota" styleClass="texto" />
						                 </f:facet>
						                 <h:inputText id="nrosCuota"
						                  value="#{item.listaPrecioItem.nroCuotaFiel}" style=" width : 50px;" styleClass="bordeceldainferior" onkeypress="return soloEnteros(this,event);" onfocus="encender(this);" onblur="apagar(this);"/>
						            </h:column>		
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="Días Diferimiento" styleClass="texto" />
						                     </f:facet>
						                     <h:inputText id="diasDiferimientoFiel"
						                     value="#{item.listaPrecioItem.diasDiferimientoFiel}" 
						                     style=" width : 76px;"  styleClass="bordeceldainferior" onkeypress="return soloEnteros(this,event);" onfocus="encender(this);" onblur="apagar(this);" />
						            </h:column>
						            
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="TNA" styleClass="texto" />
						                     </f:facet>
						                     <h:inputText id="tnaFiel" value="#{item.listaPrecioItem.tnaXCuotaFiel}" style="width: 80px"  styleClass="bordeceldainferior" onkeypress="return soloDecimalesPrecisos(this, event, 4);"   onkeyup="teaComercioItemFiel(this.value, this);" />
						            </h:column>
						                        
						            <h:column>
						                  <f:facet name="header">
						                      <h:outputText value="TEM" styleClass="texto" />
						                  </f:facet>
						                  <h:inputText id="temFiel" 
						                   value="#{item.listaPrecioItem.temXCuotaFiel}" style="width: 60px" styleClass="bordeceldainferior" onkeypress="return soloDecimalesPrecisos(this, event, 2);" onfocus="encender(this);" onblur="apagar(this);" />
						            </h:column>
						            
						            
						                        
						            <h:column>
										  <x:commandLink action="#{ListaPrecioBean.eliminarItem}" id="eliminarItemFielLink">
											  <f:param id="idItemFielElim" name="idItemFielElim" value="#{item.indice}"/>
										      <x:graphicImage value="/img/cat_act.gif" title="Eliminar el item." border="0"/>
										  </x:commandLink>
									</h:column>
								</h:dataTable>
					        </c:if>
                    		<c:if test="${empty ListaPrecioBean.itemsFiel}">
								 <h:outputText value="No existen Items para la Lista de Precios." styleClass="texto" id="itemsInexistentes" style="color:green"/>
							</c:if>	
							<h:panelGrid id="agreFiel" columns="1" align="right" width="700">
			                    <f:verbatim>&nbsp;</f:verbatim>
			                    <h:commandButton onkeypress="return null;" id="botonCargarItemFiel" value="Agregar Item" action="#{ListaPrecioBean.agregarItem}" styleClass="btn btn-primary btn-flat pull-right" />
			                </h:panelGrid>					
						    
						
						</h:panelGrid>
						
						
						
						<h:panelGrid id="tablaDetallesFiel" columns="1" align="center" rendered="#{ListaPrecioBean.perteneceAFiel}">
                             <c:if test="${!empty ListaPrecioBean.detallesFiel}">
                             	<f:verbatim>&nbsp;</f:verbatim>
								<h:dataTable value="#{ListaPrecioBean.detallesFiel}" id="tablaDeDetallesFiel"
									styleClass="standardTable"
							        headerClass="standardTable_Header"
							        footerClass="standardTable_Header"
							        rowClasses="standardTable_Row1,standardTable_Row2"
							        columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"							
									var="item" style=" width : 300px;">
						            <h:column>
						                 <f:facet name="header">
						                     <h:outputText value="Descripción" styleClass="texto" />
						                 </f:facet>
						                 <h:inputText id="descripcionDetalles"
						                  value="#{item.listaPrecioDetalle.descripcion}" style=" width : 207px;" styleClass="bordeceldatext"  onfocus="encender(this);" onblur="apagar(this);"/>
						            </h:column>		
									<h:column>
						                     <f:facet name="header">
						                         <h:outputText value="Orden" styleClass="texto" />
						                     </f:facet>
						                     <h:inputText id="ordenImputacionDetalles"
						                     value="#{item.listaPrecioDetalle.ordenImputacion}" 
						                     style=" width : 60px;"  styleClass="bordeceldainferior" onkeypress="return soloDecimalesPrecisos(this,event,2);" onfocus="encender(this);" onblur="apagar(this);" />
						            </h:column> 
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="Monto" styleClass="texto" />
						                     </f:facet>
						                     <h:inputText id="montoDetalles"
						                     value="#{item.listaPrecioDetalle.monto}" 
						                     style=" width : 60px;"  styleClass="bordeceldainferior" onkeypress="return soloDecimalesPrecisos(this,event,2);" onfocus="encender(this);" onblur="apagar(this);" />
						            </h:column>
						            
						            <h:column>
						                     <f:facet name="header">
						                         <h:outputText value="Porcentaje" styleClass="texto" />
						                     </f:facet>
						                     <h:inputText id="porcentajeDetalle" value="#{item.listaPrecioDetalle.porcentaje}" style="width: 60px"  styleClass="bordeceldainferior"  onkeypress="return soloDecimalesPrecisos(this, event, 2);"/>
						            </h:column>
						                        
						            <h:column>
						                  <f:facet name="header">
						                      <h:outputText value="% Mínimo" styleClass="texto" />
						                  </f:facet>
						                  <h:inputText id="porcenMInimoDetalle" 
						                   value="#{item.listaPrecioDetalle.porcentajeMinimo}" style="width: 60px" styleClass="bordeceldainferior" onkeypress="return soloDecimalesPrecisos(this, event, 2);" onfocus="encender(this);" onblur="apagar(this);" />
						            </h:column>
						            
						            <h:column>
						                  <f:facet name="header">
						                      <h:outputText value="% Máximo" styleClass="texto" />
						                  </f:facet>
						                  <h:inputText id="porcenMaximoDetalle" 
						                   value="#{item.listaPrecioDetalle.porcentajeMaximo}" style="width: 60px" styleClass="bordeceldainferior" onkeypress="return soloDecimalesPrecisos(this, event, 2);" onfocus="encender(this);" onblur="apagar(this);" />
						            </h:column>
						                        
						            <h:column>
										  <x:commandLink action="#{item.eliminarDetalleFiel}" id="eliminarDetalleFielLink">
											  <f:param id="idDetalleFielElim" name="idDetalleFielElim" value="#{item.indice}"/>
										      <x:graphicImage value="/img/cat_act.gif" title="Eliminar el detalle." border="0"/>
										  </x:commandLink>
									</h:column>
								</h:dataTable>
					        </c:if>
                    		<c:if test="${empty ListaPrecioBean.detallesFiel}">
								 <h:outputText value="No existen Detalles para la Lista de Precios." styleClass="texto" id="detallesInexistentes" style="color:green"/>
							</c:if>	
							<h:panelGrid id="agreDetallesFiel" columns="1" align="right" width="700">
			                    <f:verbatim>&nbsp;</f:verbatim>
			                    <h:commandButton onkeypress="return null;" id="botonCargarDetalleFiel" value="Agregar Item" action="#{ListaPrecioBean.agregarDetalle}" styleClass="btn btn-primary btn-flat pull-right" />
			                </h:panelGrid>					
						    
						
						</h:panelGrid>
						
						<h:panelGrid id="panelDeAltaArchivo" >

							<x:inputFileUpload id="fileUpLoad" storage="file" styleClass="fileUploadInput" maxlength="1000"  
									value="#{ListaPrecioBean.imagen}" onfocus="encender(this);" onblur="apagar(this);" />
							<f:verbatim>&nbsp;</f:verbatim>
							<h:commandButton value="Adjuntar" action="#{ListaPrecioBean.saveFile}" styleClass="btn btn-primary btn-flat" id="btonAdjuntarTDocLink" />

                            <f:verbatim>&nbsp;</f:verbatim>

                            <s:fieldset legend="Archivo Adjunto" style="width:300px;" id="fielDeArchisAdj">
                            <h:panelGrid styleClass="box box-primary" style="background:#fcfcfc" columns="1" id="boxAdj"> 
								<h:panelGrid columns="2" id="archAdj" width="300">
									<h:commandLink value="#{ListaPrecioBean.versionEditada.urlArchivo}" id="urlDeArchiv" action="#{ListaPrecioBean.abrirArchivoAdjunto}">
									</h:commandLink>
												   
									<x:commandLink id="eliminarArchivoAdjuntoLink" action="#{ListaPrecioBean.eliminarArchivoAdjunto}" >
										<x:graphicImage id="grapImag1" value="/img/borrar.gif" title="Eliminar archivo." border="0" />
									</x:commandLink>
								</h:panelGrid>
							</h:panelGrid>
							</s:fieldset>							
							
						</h:panelGrid>
						
						</h:panelGrid>
						
						
					    <f:verbatim>&nbsp;</f:verbatim>
						<f:verbatim><br></f:verbatim>
						
						<f:verbatim><hr align="center" width="700"></f:verbatim>
						<h:panelGrid columns="1" width="727" id="panelBotonera" align="center" rendered="#{!ListaPrecioBean.mostrarOpciones}">
							<h:panelGrid columns="10" rendered="#{!ListaPrecioBean.verAltaVersiones}" id="botoneras">
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
								<x:commandButton id="buttonCopiarVersion" rendered="#{!ListaPrecioBean.alta}" value="Copiar esta lista" action="#{ListaPrecioBean.copiarVersion}" styleClass="btn btn-primary btn-flat" />
								<x:commandButton id="buttonGrabarNuevaVersion" rendered="#{!ListaPrecioBean.alta}" value="Guardar nueva versión" action="#{ListaPrecioBean.aceptarNuevaVersion}" styleClass="btn btn-primary btn-flat" />
								<x:commandButton id="buttonGrabar" value="Guardar" rendered="#{ListaPrecioBean.verBtnGuardar}" action="#{ListaPrecioBean.grabar}" styleClass="btn btn-primary btn-flat"/>
								<x:commandButton id="buttonBorrarVersion" value="Borrar Versión" rendered="#{ListaPrecioBean.verEdicion}" action="#{ListaPrecioBean.borrarVersion}" styleClass="btn btn-primary btn-flat"/>
								<x:commandButton id="buttonCancelar" value="Cancelar" action="#{ListaPrecioBean.cancelar}"  styleClass="btn btn-primary btn-flat"/>
							</h:panelGrid>
						<h:panelGrid columns="4" id="aceptarFecha" rendered="#{ListaPrecioBean.verAltaVersiones}">
							<h:outputText id="leyendaParaFecha" value="Ingrese la fecha en la que entrará en vigencia la versión:" styleClass="texto" style="color:green"/>
							<f:verbatim>
					                <div class="input-group date">
					                    <div class="input-group-addon">
					                        <i class="fa fa-calendar"></i>
					                    </div>
					                    <input type="text" class="form-control pull-right" id="fComienzoNV" placeholder="DD/MM/AAAA">
					                </div>
							</f:verbatim>
						    <x:commandButton id="buttonAceptaNue" value="Aceptar" action="#{ListaPrecioBean.grabarNuevaVersion}" styleClass="btn btn-primary btn-flat"/>
						    <x:commandButton id="buttonCancelaNue" action="#{ListaPrecioBean.cancelarNuevaVersion}" value="Cancelar" styleClass="btn btn-primary btn-flat"/>
						    	<h:inputText id="aPartirDe" value="#{ListaPrecioBean.fechaComienzoNuevaVersion}" style="display: none;">
							        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
							    </h:inputText>
						</h:panelGrid>
		</h:panelGrid>
                      <h:panelGrid  columns="1" width="727" id="panelBotoneraBisVol" align="right">
                      		<x:commandButton id="buttonVolverAlListado" value="Volver al listado" action="#{ListaPrecioBean.volverAlListado}" rendered="#{ListaPrecioBean.verBtnVolver}" styleClass="btn btn-primary btn-flat"/>
                      </h:panelGrid>
                          
					</h:panelGrid>
				</h:panelGroup>
			</f:facet>

		</x:panelLayout>


	</h:form>
		

</center>

<div class="box-header"></div>

</div>
</section> 

</div>
<!-- ./Main content -->

<%@include file="/inc/footer.jsp" %>


<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp2_menu.length-1; i++) {
    var obj = mainMenu__idJsp2_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ListaPrecioBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>


<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
    });

    $("#fHasta").datepicker({
      autoclose: true,
    });

    $("#fComienzoNV").datepicker({
      autoclose: true,
    });


    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        //la hora va en gtm 0. al pasar el server que está en gtm -3, le restará 3 hs.
        document.getElementById("amListaPrecioForm:FechaVigencia").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00"; 
    });

    $("#fHasta").change(function() {
        var fd = $(this).datepicker("getDate");
        //la hora va en gtm 0. al pasar el server que está en gtm -3, le restará 3 hs.
        document.getElementById("amListaPrecioForm:fechaVigenciaFiel").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00"; 
    });

    $("#fComienzoNV").change(function() {
        var fd = $(this).datepicker("getDate");
        //la hora va en gtm 0. al pasar el server que está en gtm -3, le restará 3 hs.
        document.getElementById("amListaPrecioForm:aPartirDe").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00"; 
    });

  });

</script>


</body>
</html>
</f:view>
