<%@include file="/inc/tags.jsp" %>

<f:view>
<html>
<head>
    <title><h:outputText value="Tarjeta Fiel - Revisión de Cupones Rechazados"/></title>
    
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/basic.css" />
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/popup.css" />    
</head>
<%@include file="/inc/head.inc"%>

<body onload="ocultarComponentes(); mostrarComponentes(0);">
<script languaje="javascript">

	     var cantidadCuponesAConciliar = 0; 
	     var cuponConciliado = 1;

	     function checkTypes(elEvento) {
	    	 var evento = window.event || elEvento;
			 if (evento.keyCode == 13) {
			 	return true;
			 }
			 return false;
	     }
	     
         function mostrarComponentes(numeroObjeto) {
            var numeroObjeto = parseInt(numeroObjeto);
         	for (z=1;document.getElementById('revisionCR:_' + numeroObjeto + 'campo'+ z)!=null;z++) { 
         	      document.getElementById('revisionCR:_' + numeroObjeto + 'campo'+ z).style.border = '1px solid #666666';
         	     
         	      document.getElementById('revisionCR:_' + numeroObjeto + 'campo'+ z).style.width = 200;
         	      document.getElementById('revisionCR:_' + numeroObjeto + 'campo'+ z).style.height = 20;
         	      document.getElementById('revisionCR:_' + numeroObjeto + 'campo'+ z).style.background = '#FFFFFF';
	         	  document.getElementById('revisionCR:_' + numeroObjeto + 'campo'+ z).style.display = '';
			}
			var xx = numeroObjeto + 1;
			document.getElementById('revisionCR:contador').value= 'Conciliando el cupon ' + xx + ' de ' + cantidadCuponesAConciliar + '.';
			cuponConciliado = xx;
			resaltarDiferencias(numeroObjeto);
            ubicarFoco("revisionCR:_" + numeroObjeto + "campo0");
           
         	return true;
         }
         
         function ocultarObj(num) {
            var objAOcultar = parseInt(num);
         	for (h=1;document.getElementById('revisionCR:_' + objAOcultar + 'campo'+ h)!=null;h++) { 
         	      document.getElementById('revisionCR:_' + objAOcultar + 'campo'+ h).style.border = 'none';
         	      document.getElementById('revisionCR:_' + objAOcultar + 'campo'+ h).style.width = 0;
         	      document.getElementById('revisionCR:_' + objAOcultar + 'campo'+ h).style.height = 0;
         	      document.getElementById('revisionCR:_' + objAOcultar + 'campo'+ h).style.background = '#dcdcdc';
	              document.getElementById('revisionCR:_' + objAOcultar + 'campo'+ h).style.display = 'none';
			}
         	document.getElementById('revisionCR:liOmitir').style.display='inline';
         	document.getElementById('revisionCR:liOmitirOut').style.display='inline';
         	document.getElementById('revisionCR:liRechazar').style.display='inline';
         	document.getElementById('revisionCR:liRechazarOut').style.display='inline';
         	return true;
         }
         
         function resaltarDiferencias(numeroObjeto) {
         	var numero = parseInt(numeroObjeto);
         	var siguiente;
         	for (z=1;document.getElementById('revisionCR:_' + numero + 'campo'+ z)!=null;z= z + 2) { 
         	      siguiente = z+1;
         	      if (document.getElementById('revisionCR:_' + numero + 'campo'+ z).value==document.getElementById('revisionCR:_' + numero + 'campo'+ siguiente).value) {
         	          document.getElementById('revisionCR:_' + numero + 'campo'+ z).disabled=true;
         	          document.getElementById('revisionCR:_' + numero + 'campo'+ siguiente).disabled=true;
         	          var g = parseInt((z + 1)/2);
         	          document.getElementById('revisionCR:renglon' + g).bgColor = document.getElementById('revisionCR:botonera').bgColor;
         	      } else {
         	          var g = parseInt((z + 1)/2);
         	     	  document.getElementById('revisionCR:renglon' + g).bgColor = "red";
         	      }
			}
			return true;
         }
         
         function ubicarFoco(idObjeto) {
         	var numero = parseInt(idObjeto.substring(12,13));
         	var componente = parseInt(idObjeto.substring(18,idObjeto.length));
         	if (componente%2==0) {
         	   var variable = componente + 1;
               for (i = variable; document.getElementById('revisionCR:_' + numero + 'campo'+ i)!=null; i= i+2) {
                  	if (!document.getElementById('revisionCR:_' + numero + 'campo'+ i).disabled) {
                  	    document.getElementById('revisionCR:_' + numero + 'campo'+ i).select();
                  	    document.getElementById('revisionCR:_' + numero + 'campo'+ i).focus();
                  	    return true;
                  	}
               }
         	} else {
         	   var siguiente = componente + 1;
         	   document.getElementById('revisionCR:_' + numero + 'campo'+ siguiente).focus();
         	   return false;
         	}
         	
         	return false;
         }
         
         function recalcularColor(InputText) {
         	var obj = parseInt(InputText.id.substring(12,13));
		    var comp = parseInt(InputText.id.substring(18,InputText.id.length)); 
		    var otroComp;
		        if (parseInt(comp)%2==0) {
		        	otroComp = parseInt(comp) - 1;
		        	var g = parseInt(parseInt(comp)/2);
		        	if (document.getElementById(InputText.id).value==document.getElementById(InputText.id.substring(0,18) + otroComp).value) {
		        	    document.getElementById('revisionCR:renglon' + g).bgColor = document.getElementById('revisionCR:botonera').bgColor;
		        	} else {
		        		document.getElementById('revisionCR:renglon' + g).bgColor = 'red';
		        	}
		        } else {
		        	otroComp = parseInt(comp) + 1;
		        	var g = parseInt(otroComp/2);
		        	if (document.getElementById(InputText.id).value==document.getElementById(InputText.id.substring(0,18) + otroComp).value) {
		        	    document.getElementById('revisionCR:renglon' + g).bgColor = document.getElementById('revisionCR:botonera').bgColor;
		        	} else {
		        	    document.getElementById('revisionCR:renglon' + g).bgColor = 'red';
		        	}
		        }
		    return true;
         }
         
         function avanzarFoco(InputText, elEvento) {
         	var evento = window.event || elEvento;
		    if (evento.keyCode == 13) {
		        var obj = parseInt(InputText.id.substring(12,13));
		        var comp = parseInt(InputText.id.substring(18,InputText.id.length)); 
		        var otroComp;
		        if (parseInt(comp)%2==0) {
		        	otroComp = parseInt(comp) - 1;
		        	if (document.getElementById(InputText.id).value==document.getElementById(InputText.id.substring(0,18) + otroComp).value) {
		        	    var bool = ubicarFoco(InputText.id);
		        	    if(!bool) {
		        	       if (obj<(cantidadCuponesAConciliar-1)) {
		        	       		ocultarObj(obj);
		        	       		obj++;
		        	       		mostrarComponentes(obj);
		        	       } else {
		        	           document.getElementById('revisionCR:contador').value= 'Presione guardar para continuar';
		   	                for (n=0; n<=obj; n++) {
		        	           		mostrarComponentesAlGuardar(n);
		        	           }
		        	           document.getElementById('revisionCR:botonGuardar').style.display='';
		        	       }
		        	    }
		        	}
		        } else {
		        	otroComp = parseInt(comp) + 1;
		        	document.getElementById(InputText.id.substring(0,18) + otroComp).select();
		        	document.getElementById(InputText.id.substring(0,18) + otroComp).focus();
		        }
		    	return false;
 		 	}
		    return true;
         }
         
         function mostrarComponentesAlGuardar(numeroObjeto) {
            var numeroObjeto = parseInt(numeroObjeto);
         	for (z=1;document.getElementById('revisionCR:_' + numeroObjeto + 'campo'+ z)!=null;z++) { 
         	      document.getElementById('revisionCR:_' + numeroObjeto + 'campo'+ z).disabled=false;
         	      document.getElementById('revisionCR:_' + numeroObjeto + 'campo'+ z).style.width = 0;
         	      document.getElementById('revisionCR:_' + numeroObjeto + 'campo'+ z).style.height = 0;
	         	  document.getElementById('revisionCR:_' + numeroObjeto + 'campo'+ z).style.display = '';
			}
			document.getElementById('revisionCR:liOmitir').style.display='none';
			document.getElementById('revisionCR:liOmitirOut').style.display='none';
			document.getElementById('revisionCR:liRechazar').style.display='none';
			document.getElementById('revisionCR:liRechazarOut').style.display='none';
         	return true;
         }
       
		 function ocultarComponentes() {
         	for (j=0;j<10;j++) {
	         	for (i=1;document.getElementById('revisionCR:_' + j + 'campo'+ i)!=null ;i++) {
	         	   if (i==1 && document.getElementById('revisionCR:_'+ j +'campo'+ i).value!=null && document.getElementById('revisionCR:_'+ j +'campo'+ i).value!="") {
	         	   		cantidadCuponesAConciliar++;
	         	   }
	               document.getElementById('revisionCR:_'+ j +'campo'+ i).style.display='none';    
				}
         	}
         	document.getElementById('revisionCR:botonGuardar').style.display='none';
         	return true;
         }	
         
         function omitirConciliacion(){
        	 
       	      var obj = cuponConciliado-1;
		      var comp = 20;
		      var otroComp;
		      otroComp = parseInt(comp) - 1;	
		    document.getElementById('revisionCR:_'+obj+'omitir').value  = "S";							 
			if (obj<(cantidadCuponesAConciliar-1)) {
		        ocultarObj(obj);
		    	obj++;
		       	mostrarComponentes(obj);
		    } else {
				document.getElementById('revisionCR:contador').value= 'Presione guardar para continuar';
		        for (n=0; n<=obj; n++) {
					mostrarComponentesAlGuardar(n);
		       	}
		        document.getElementById('revisionCR:botonGuardar').style.display='';
		    }	
		 	
        		
         }
         
         function rechazarConciliacion(){
        	 if (confirm('¿Confirma rechazar el cupon definitivamente? ')) {  
       	      var obj = cuponConciliado-1;
		      var comp = 20;
		      var otroComp;
		      otroComp = parseInt(comp) - 1;								 
			if (obj<(cantidadCuponesAConciliar-1)) {
		        ocultarObj(obj);
		    	obj++;
		       	mostrarComponentes(obj);
		    } else {
				document.getElementById('revisionCR:contador').value= 'Presione guardar para continuar';
		        for (n=0; n<=obj; n++) {
					mostrarComponentesAlGuardar(n);
		       	}
		        document.getElementById('revisionCR:botonGuardar').style.display='';
		    }	
		 	document.getElementById('revisionCR:_'+obj+'rechazar').value  = "S";
           }
         }
         
         
         
				
    </script>

<center>
<h:form id="revisionCR"   style="width: 800;">
	
	
	<x:panelTabbedPane bgcolor="#dcdcdc">
	<h:panelGroup id="conciManual">
	
		<h:outputText value="Revisión de Cupones Rechazados" style="FONT-SIZE: large;" styleClass="texto" /> 
	    <%-- INCLUDE PARA LOS ERRORES --%> 	
		<h:panelGroup id="errores">
			<jsp:include page="/inc/error.jsp" />
		</h:panelGroup>     
	
	    
		
		
		<%-- Comienzan los cambios --%>
		
		<h:panelGrid columns="2" id="datos0" width="700" align="center">
		            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
		            <h:panelGrid id="titulos" columns="3" width="500" align="center">
			            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			            <h:outputText value="#{ConciliacionCuponesBean.revisionManual.subTitulo1}" style="FONT-SIZE: large;" styleClass="texto" />
			            <h:outputText value="#{ConciliacionCuponesBean.revisionManual.subTitulo2}" style="FONT-SIZE: large;" styleClass="texto" />
		            </h:panelGrid>
		          <h:outputText value="Código Comercio: " styleClass="textoblue" />
		            <h:panelGrid id="renglon1" columns="2" width="450" cellspacing="0" cellpadding="0" align="center" >
		                 

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.codComercio}" id="_0campo1" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc;  border:none; margin-left: 13px"/>
						 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.transaccion.codComercio}" id="_0campo2"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px "/>

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.codComercio}" id="_1campo1" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  value="#{ConciliacionCuponesBean.revisionManual.lciUno.transaccion.codComercio}" id="_1campo2"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.codComercio}" id="_2campo1" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.transaccion.codComercio}" id="_2campo2"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.codComercio}" id="_3campo1" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
  	                     <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.transaccion.codComercio}" id="_3campo2"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.codComercio}" id="_4campo1" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.transaccion.codComercio}" id="_4campo2"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.codComercio}" id="_5campo1" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.transaccion.codComercio}" id="_5campo2"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.codComercio}" id="_6campo1" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.transaccion.codComercio}" id="_6campo2"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.codComercio}" id="_7campo1" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.transaccion.codComercio}" id="_7campo2"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.codComercio}" id="_8campo1" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.transaccion.codComercio}" id="_8campo2"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.codComercio}" id="_9campo1" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.transaccion.codComercio}" id="_9campo2"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>		               
		            </h:panelGrid>
		            <h:outputText value="Fecha Real: " styleClass="textoblue" />
		            <h:panelGrid id="renglon2" columns="2" width="450" cellpadding="0" cellspacing="0" align="center">
		                 

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.fechaReal}" id="_0campo3" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.transaccion.wraperFechaReal}" id="_0campo4"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.fechaReal}" id="_1campo3" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.transaccion.wraperFechaReal}" id="_1campo4"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>

		                
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.fechaReal}" id="_2campo3" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.transaccion.wraperFechaReal}" id="_2campo4"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>		               
		               
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.fechaReal}" id="_3campo3" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.transaccion.wraperFechaReal}" id="_3campo4"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.fechaReal}" id="_4campo3" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.transaccion.wraperFechaReal}" id="_4campo4"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.fechaReal}" id="_5campo3" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.transaccion.wraperFechaReal}" id="_5campo4"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.fechaReal}" id="_6campo3" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.transaccion.wraperFechaReal}" id="_6campo4"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.fechaReal}" id="_7campo3" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.transaccion.wraperFechaReal}" id="_7campo4"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.fechaReal}" id="_8campo3" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.transaccion.wraperFechaReal}" id="_8campo4"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.fechaReal}" id="_9campo3" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.transaccion.wraperFechaReal}" id="_9campo4"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		            </h:panelGrid>
		            <h:outputText value="Número Tarjeta: " styleClass="textoblue" />
		            <h:panelGrid id="renglon3" columns="2" width="450" cellpadding="0" cellspacing="0" align="center">
		               

		               <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.nroTarjeta}" id="_0campo5" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.transaccion.nroTarjeta}" id="_0campo6"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
    
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.nroTarjeta}" id="_1campo5" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.transaccion.nroTarjeta}" id="_1campo6"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.nroTarjeta}" id="_2campo5" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.transaccion.nroTarjeta}" id="_2campo6"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>  

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.nroTarjeta}" id="_3campo5" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.transaccion.nroTarjeta}" id="_3campo6"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.nroTarjeta}" id="_4campo5" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.transaccion.nroTarjeta}" id="_4campo6"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.nroTarjeta}" id="_5campo5" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.transaccion.nroTarjeta}" id="_5campo6"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.nroTarjeta}" id="_6campo5" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.transaccion.nroTarjeta}" id="_6campo6"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.nroTarjeta}" id="_7campo5" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.transaccion.nroTarjeta}" id="_7campo6"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.nroTarjeta}" id="_8campo5" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
						 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.transaccion.nroTarjeta}" id="_8campo6"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.nroTarjeta}" id="_9campo5" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		          		 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.transaccion.nroTarjeta}" id="_9campo6"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		            </h:panelGrid> 
		            <h:outputText value="Cantidad de Cuotas: " styleClass="textoblue" />
		            <h:panelGrid id="renglon4" columns="2" width="450" cellpadding="0" cellspacing="0" align="center">

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.cantCuotas}" id="_0campo7" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.transaccion.cantCuotas}" id="_0campo8"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.cantCuotas}" id="_1campo7" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.transaccion.cantCuotas}" id="_1campo8"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.cantCuotas}" id="_2campo7" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.transaccion.cantCuotas}" id="_2campo8"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.cantCuotas}" id="_3campo7" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.transaccion.cantCuotas}" id="_3campo8"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/> 
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.cantCuotas}" id="_4campo7" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                  <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.transaccion.cantCuotas}" id="_4campo8"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.cantCuotas}" id="_5campo7" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.transaccion.cantCuotas}" id="_5campo8"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.cantCuotas}" id="_6campo7" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.transaccion.cantCuotas}" id="_6campo8"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>		                 
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.cantCuotas}" id="_7campo7" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.transaccion.cantCuotas}" id="_7campo8"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.cantCuotas}" id="_8campo7" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.transaccion.cantCuotas}" id="_8campo8"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.cantCuotas}" id="_9campo7" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		              	 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.transaccion.cantCuotas}" id="_9campo8"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>	   
		                 
		            </h:panelGrid>
		            <h:outputText value="Importe: " styleClass="textoblue" />
		            <h:panelGrid id="renglon5" columns="2" width="450" cellpadding="0" cellspacing="0" align="center">

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.importe}" id="_0campo9" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.transaccion.importe}" id="_0campo10"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.importe}" id="_1campo9" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.transaccion.importe}" id="_1campo10"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.importe}" id="_2campo9" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.transaccion.importe}" id="_2campo10"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.importe}" id="_3campo9" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.transaccion.importe}" id="_3campo10"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.importe}" id="_4campo9" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.transaccion.importe}" id="_4campo10"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>		                 
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.importe}" id="_5campo9" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.transaccion.importe}" id="_5campo10"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.importe}" id="_6campo9" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.transaccion.importe}" id="_6campo10"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.importe}" id="_7campo9" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.transaccion.importe}" id="_7campo10"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.importe}" id="_8campo9" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.transaccion.importe}" id="_8campo10"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.importe}" id="_9campo9" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
			             <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.transaccion.importe}" id="_9campo10"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>		                 

		            </h:panelGrid>
		            <h:outputText value="Código Autorizacion:: " styleClass="textoblue" />
		            <h:panelGrid id="renglon6" columns="2" width="450" cellpadding="0" cellspacing="0" align="center">

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.codigoAutorizacion}" id="_0campo11" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
						 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.transaccion.codigoAutorizacion}" id="_0campo12"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
	                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.codigoAutorizacion}" id="_1campo11" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.transaccion.codigoAutorizacion}" id="_1campo12"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.codigoAutorizacion}" id="_2campo11" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.transaccion.codigoAutorizacion}" id="_2campo12"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.codigoAutorizacion}" id="_3campo11" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.transaccion.codigoAutorizacion}" id="_3campo12"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.codigoAutorizacion}" id="_4campo11" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.transaccion.codigoAutorizacion}" id="_4campo12"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>			                 
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.codigoAutorizacion}" id="_5campo11" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.transaccion.codigoAutorizacion}" id="_5campo12"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.codigoAutorizacion}" id="_6campo11" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.transaccion.codigoAutorizacion}" id="_6campo12"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.codigoAutorizacion}" id="_7campo11" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
			             <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.transaccion.codigoAutorizacion}" id="_7campo12"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.codigoAutorizacion}" id="_8campo11" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
			             <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.transaccion.codigoAutorizacion}" id="_8campo12"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>	                 
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.codigoAutorizacion}" id="_9campo11" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.transaccion.codigoAutorizacion}" id="_9campo12"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		            </h:panelGrid>
		            
		          <c:if	test="${ConciliacionCuponesBean.revisionManual.origenCargaAutom}">
		            <h:outputText value="Nro de Lote: " styleClass="textoblue" />
		            <h:panelGrid id="renglon7" columns="2" width="450" cellpadding="0" cellspacing="0" align="center">

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.nroLote}" id="_0campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
                         <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.transaccion.nroLote}" id="_0campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.nroLote}" id="_1campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.transaccion.nroLote}" id="_1campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.nroLote}" id="_2campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.transaccion.nroLote}" id="_2campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.nroLote}" id="_3campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
			             <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.transaccion.nroLote}" id="_3campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.nroLote}" id="_4campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
			             <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.transaccion.nroLote}" id="_4campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>		                 
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.nroLote}" id="_5campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
                         <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.transaccion.nroLote}" id="_5campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.nroLote}" id="_6campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.transaccion.nroLote}" id="_6campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		               
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.nroLote}" id="_7campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.transaccion.nroLote}" id="_7campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.nroLote}" id="_8campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.transaccion.nroLote}" id="_8campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>		               
		               
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.nroLote}" id="_9campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.transaccion.nroLote}" id="_9campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		            </h:panelGrid>
		         </c:if> 
		         
		          <c:if	test="${!ConciliacionCuponesBean.revisionManual.origenCargaAutom}">
		            <h:outputText value="Nro cupon: " styleClass="textoblue" />
		            <h:panelGrid id="renglon7" columns="2" width="450" cellpadding="0" cellspacing="0" align="center">

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.nroCupon}" id="_0campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
                         <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.transaccion.nroCupon}" id="_0campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.nroCupon}" id="_1campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.transaccion.nroCupon}" id="_1campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.nroCupon}" id="_2campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.transaccion.nroCupon}" id="_2campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.nroCupon}" id="_3campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
			             <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.transaccion.nroCupon}" id="_3campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.nroCupon}" id="_4campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
			             <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.transaccion.nroCupon}" id="_4campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>		                 
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.nroCupon}" id="_5campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
                         <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.transaccion.nroCupon}" id="_5campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.nroCupon}" id="_6campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.transaccion.nroCupon}" id="_6campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		               
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.nroCupon}" id="_7campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.transaccion.nroCupon}" id="_7campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.nroCupon}" id="_8campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.transaccion.nroCupon}" id="_8campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>		               
		               
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.nroCupon}" id="_9campo13" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.transaccion.nroCupon}" id="_9campo14"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		            </h:panelGrid>
		         </c:if> 
		         
		           
		            <c:if	test="${ConciliacionCuponesBean.revisionManual.origenCargaAutom}">
		            <h:outputText value="Tipo Registro: " styleClass="textoblue" />
		            <h:panelGrid id="renglon8" columns="2" width="450" cellpadding="0" cellspacing="0" align="center">

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.tipoRegistro}" id="_0campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.transaccion.tipoRegistro}" id="_0campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.tipoRegistro}" id="_1campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.transaccion.tipoRegistro}" id="_1campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.tipoRegistro}" id="_2campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.transaccion.tipoRegistro}" id="_2campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.tipoRegistro}" id="_3campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.transaccion.tipoRegistro}" id="_3campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.tipoRegistro}" id="_4campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.transaccion.codigoAutorizacion}" id="_4campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.tipoRegistro}" id="_5campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.transaccion.codigoAutorizacion}" id="_5campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.tipoRegistro}" id="_6campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.transaccion.tipoRegistro}" id="_6campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.tipoRegistro}" id="_7campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.transaccion.tipoRegistro}" id="_7campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.tipoRegistro}" id="_8campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.transaccion.tipoRegistro}" id="_8campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>	
		                 		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.tipoRegistro}" id="_9campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.transaccion.tipoRegistro}" id="_9campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		            </h:panelGrid>
		            </c:if>
		            
		         <c:if	test="${!ConciliacionCuponesBean.revisionManual.origenCargaAutom}">
		            <h:outputText value="Documento Nro: " styleClass="textoblue" />
		            <h:panelGrid id="renglon8" columns="2" width="450" cellpadding="0" cellspacing="0" align="center">

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.nroDoc}" id="_0campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.transaccion.nroDoc}" id="_0campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.nroDoc}" id="_1campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.transaccion.nroDoc}" id="_1campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.nroDoc}" id="_2campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.transaccion.nroDoc}" id="_2campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.nroDoc}" id="_3campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.transaccion.nroDoc}" id="_3campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.nroDoc}" id="_4campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.transaccion.nroDoc}" id="_4campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.nroDoc}" id="_5campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.transaccion.nroDoc}" id="_5campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.nroDoc}" id="_6campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.transaccion.nroDoc}" id="_6campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.nroDoc}" id="_7campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.transaccion.nroDoc}" id="_7campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.nroDoc}" id="_8campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.transaccion.nroDoc}" id="_8campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>	
		                 		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.nroDoc}" id="_9campo15" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.transaccion.nroDoc}" id="_9campo16"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		            </h:panelGrid>
		          </c:if>
		            
		          <c:if	test="${ConciliacionCuponesBean.revisionManual.origenCargaAutom}">
		            <h:outputText value="Importe sin Descuento: " styleClass="textoblue" />
		            <h:panelGrid id="renglon9" columns="2" width="450" cellpadding="0" cellspacing="0" align="center">

		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.codigoMoneda}" id="_0campo17" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCero.transaccion.codigoMoneda}" id="_0campo18"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.codigoMoneda}" id="_1campo17" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciUno.transaccion.codigoMoneda}" id="_1campo18"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.codigoMoneda}" id="_2campo17" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciDos.transaccion.codigoMoneda}" id="_2campo18"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.codigoMoneda}" id="_3campo17" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
			             <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);"  onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciTres.transaccion.codigoMoneda}" id="_3campo18"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.codigoMoneda}" id="_4campo17" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
			             <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.transaccion.codigoMoneda}" id="_4campo18"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
	                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.codigoMoneda}" id="_5campo17" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciCinco.transaccion.codigoMoneda}" id="_5campo18"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.codigoMoneda}" id="_6campo17" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSeis.transaccion.codigoMoneda}" id="_6campo18"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.codigoMoneda}" id="_7campo17" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciSiete.transaccion.codigoMoneda}" id="_7campo18"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.codigoMoneda}" id="_8campo17" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciOcho.transaccion.codigoMoneda}" id="_8campo18"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>		                 
		                 
		                 <h:inputText onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.codigoMoneda}" id="_9campo17" style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		                 <h:inputText onkeypress="return checkTypes(event);" onkeydown="avanzarFoco(this,event);" onkeyup="recalcularColor(this);" value="#{ConciliacionCuponesBean.revisionManual.lciNueve.transaccion.codigoMoneda}" id="_9campo18"  style="width: 0px; height: 0px; display:hidden; background-color: #dcdcdc; border:none;  margin-left: 13px"/>
		            </h:panelGrid>
		           </c:if>  

		            <h:panelGrid id="renglon11" columns="2" width="0" cellpadding="0" cellspacing="0" align="center">
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciCero.omitido}" id="_0omitir"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciUno.omitido}" id="_1omitir"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciDos.omitido}" id="_2omitir"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciTres.omitido}" id="_3omitir"/>						                 												
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.omitido}" id="_4omitir"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciCinco.omitido}" id="_5omitir"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciSeis.omitido}" id="_6omitir"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciSiete.omitido}" id="_7omitir"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciOcho.omitido}" id="_8omitir"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciNueve.omitido}" id="_9omitir"/>						                 																																										
		            </h:panelGrid> 
		             <h:panelGrid id="renglon12" columns="2" width="0" cellpadding="0" cellspacing="0" align="center">
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciCero.rechazado}" id="_0rechazar"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciUno.rechazado}" id="_1rechazar"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciDos.rechazado}" id="_2rechazar"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciTres.rechazado}" id="_3rechazar"/>						                 												
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciCuatro.rechazado}" id="_4rechazar"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciCinco.rechazado}" id="_5rechazar"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciSeis.rechazado}" id="_6rechazar"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciSiete.rechazado}" id="_7rechazar"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciOcho.rechazado}" id="_8rechazar"/>						                 
						<h:inputHidden value="#{ConciliacionCuponesBean.revisionManual.lciNueve.rechazado}" id="_9rechazar"/>						                 																																										
		            </h:panelGrid> 
		            
		            
		</h:panelGrid> 
		 
		
		
		
		
		<h:panelGrid columns="1" align="center" id="panContador">
			<h:inputText value="Conciliando cupones" disabled="true"  id="contador" style="FONT-SIZE: large; background-color: #dcdcdc; border: none; width : 500px;" styleClass="texto" />
		</h:panelGrid>
		   
			
		<h:panelGrid id="leyenda_ayuda" columns="1" align="center">
		     <h:outputText value="Utilize la tecla enter para avanzar."  style="FONT-SIZE: large; background-color: #dcdcdc; border: none; width : 500px;" styleClass="texto"/>
		</h:panelGrid>
		<h:panelGrid id="botonera" columns="9" width="567">
				<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				
				<x:commandLink value="GUARDAR" title="" actionListener="#{ConciliacionCuponesBean.revisionManual.guardarYCerrarPopup}" id="botonGuardar" > 
				   <x:graphicImage id="im" value="/img/save32.gif"></x:graphicImage>
				</x:commandLink>

				<h:graphicImage id="liOmitir" value="/img/warning.png" onmouseover= "document.body.style.cursor='pointer';" onmouseout= "document.body.style.cursor='default'" onclick="omitirConciliacion()" />
                <h:outputText id="liOmitirOut" value="DECIDIR MAS TARDE"  style="FONT-SIZE: MEDIUM; background-color: #dcdcdc; border: none; width : 500px;" styleClass="texto" onclick="omitirConciliacion()"/>

				<h:graphicImage id="liRechazar"  value="/img/trashcan_full_32x32.png" onmouseover= "document.body.style.cursor='pointer';" onmouseout= "document.body.style.cursor='default'" onclick="rechazarConciliacion()" />		
                <h:outputText id="liRechazarOut" value="RECHAZAR CUPON"  style="FONT-SIZE: MEDIUM; background-color: #dcdcdc; border: none; width : 500px;" styleClass="texto" onclick="rechazarConciliacion()" />

				<x:commandLink value="" title="Cancelar" onclick="window.close();" id="liDos"> 
				    <x:graphicImage id="imDos" value="/img/button_cancel.png"></x:graphicImage>
                    <h:outputText value="CANCELAR"  style="FONT-SIZE: MEDIUM; background-color: #dcdcdc; border: none; width : 500px;" styleClass="texto"/>
				</x:commandLink>

              
			</h:panelGrid>
		
	</h:panelGroup>
	</x:panelTabbedPane>
	
	
</h:form>	
</center>
</body>
</html>
</f:view>