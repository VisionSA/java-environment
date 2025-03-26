<%@ include file="/inc/tags.jsp" %>

<h:form id="navigation">
<x:panelNavigation id="nav"
              styleClass="navigation"
              separatorClass="navseparator"
              itemClass="navitem"
              activeItemClass="navitem_active"
              openItemClass="navitem_open">

    <x:commandNavigation id="inicio" value="Inicio" action="inicio" />
    
    <x:commandNavigation id="operadores" value="Operadores">
        <x:commandNavigation id="alta_operadores" value="Alta" action="altaOperadores" />
        <x:commandNavigation id="listar_operadores" value="Listado" action="#{OperadorBean.check}"/>        
    </x:commandNavigation>
    
    <x:commandNavigation id="permisos" value="Permisos">
        <x:commandNavigation id="listar_permisos" value="Listar" action="#{PermisoBean.check}" />
        <x:commandNavigation id="otorgar_permisos" value="Otorgar" action="#{PermisoBean.otorgar}" />
        <x:commandNavigation id="validar_permisos" value="Validar" action="#{PermisoBean.validarPermisos}" />
    </x:commandNavigation>

    <x:commandNavigation id="t_parametros" value="TParametros">
	    <x:commandNavigation id="parametros" value="Parametros">
	        <x:commandNavigation id="alta_parametros" value="Alta" action="altaParametros" />
	  		<x:commandNavigation id="listar_parametros" value="Listar" action="#{ParametroBean.listar}" />       
	    </x:commandNavigation>
	    
	    <x:commandNavigation id="bancos" value="Bancos">
	        <x:commandNavigation id="alta_bancos" value="Alta" action="altaBancos" />
	        <x:commandNavigation id="listar_bancos" value="Listar" action="#{BancoBean.listar}" />        
	    </x:commandNavigation>    	    
    </x:commandNavigation>    
    
    <x:commandNavigation id="proveedores_menu" value="Proveedores">
	    <x:commandNavigation id="proveedores" value="Proveedores">
	        <x:commandNavigation id="alta_proveedores" value="Alta" action="altaProveedores" />
	    </x:commandNavigation>
	    
	    <x:commandNavigation id="comprobante" value="Comprobantes">
	        <x:commandNavigation id="alta_comprobantes" value="Alta" action="altaComprobantes" />
	    </x:commandNavigation>    	    
    </x:commandNavigation>        

    <x:commandNavigation id="salir" value="Salir" action="#{LoginBean.salir}" />    

</x:panelNavigation>
</h:form>
