<%@include file="/inc/tags.jsp" %>

				<c:if test="${ImputacionBean.validado==true}">
					<s:layoutingTitlePane id="datos"
								label="Datos del Proveedor"
								containerNodeClass="contentTitlePane"
								labelNodeClass="labelTitlePane">
					<h:panelGrid id="imputacionesPanelUno" columns="3" align="center">
						<h:panelGrid columns="3" align="center">
			                <h:outputText id="outCodigo" value="Código: " styleClass="texto"/>
			                <h:outputText id="outCodigoValor" value="#{ImputacionBean.idProveedor}" styleClass="texto"/>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                <h:outputText id="outRazonSocial" value="Razon Social: " styleClass="texto"/>
			                <h:outputText id="outRazonSocialValor" value="#{ImputacionBean.razonSocial}" styleClass="texto"/>							
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>			                
						</h:panelGrid>					
						
						<f:verbatim>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;							
						</f:verbatim>
												
						<h:panelGrid columns="3" align="center">
			                <h:outputText id="outCuitDos" value="Cuit: " styleClass="texto"/>
							<h:panelGroup id="panelCuitDos">
								<h:outputText id="outCuit" value="#{ImputacionBean.cuit}" styleClass="texto"/>
							</h:panelGroup>
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
			                <h:outputText id="outDomicilioLegal" value="Domicilio Legal: " styleClass="texto"/>
			                <h:outputText id="outDomicilioLegalValor" value="#{ImputacionBean.domicilioLegal}" styleClass="texto"/>												
							<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						</h:panelGrid>
					</h:panelGrid>	
					</s:layoutingTitlePane>
				
        		<x:div style="OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 910; HEIGHT: 350px; border: 1px; margin-left: auto; margin-right: auto;">
					<h:panelGrid id="imputacionesPanelDos" columns="3" align="center">  
					<s:fieldset legend="Cuotas de Comprobantes" style="height: 325px;">
						<c:if test="${empty ImputacionBean.cuotasComprobates}">
							<h:outputText value="No existen comprobantes." styleClass="texto"/>
						</c:if>
					
						<c:if test="${!empty ImputacionBean.cuotasComprobates}">
		        			<f:verbatim>
					        	<display:table id="comprobantes" 
					        				   name="sessionScope.ImputacionBean.cuotasComprobates" 
					        				   defaultsort="1" pagesize="10" export="false"
					        				   class="tableA" requestURIcontext="true"
					        				   requestURI="${ImputacionBean.rutaRedireccion}"
					        				   style="width: 420px;">
					        		<display:column property="tipo" title="Tipo y Número" sortable="true" class="tdA"/>
					        		<display:column property="fechaVencimiento" title="Vto" sortable="true" class="tdA"/>
					        		<display:column property="cuota.importe" title="Monto" sortable="true" class="tdA"/>
					        		<display:column property="imputado" title="Imputado" sortable="true" class="tdA"/>
					        		<display:column property="resto" title="Saldo" sortable="true" class="tdA"/>
									<display:column style="width:8%" media="html">
										<input type="checkbox" id="checkComprobantes[${comprobantes.cuota.idCuotaComprobante}]" name="checkComprobantes[${comprobantes.cuota.idCuotaComprobante}]"/>
										<input type="hidden" id="checkComprobantes" name="checkComprobantes" value="${comprobantes.cuota.idCuotaComprobante}"/>
									</display:column>
		
								    <display:setProperty name="sort.amount" value="list" />
								    <display:setProperty name="paging.banner.group_size" value="6" />
								    <display:setProperty name="paging.banner.placement" value="bottom" />
								    <display:setProperty name="paging.banner.item_name" value="Comprobante" />
								    <display:setProperty name="paging.banner.items_name" value="" />
								    <display:setProperty name="paging.banner.some_items_found">
							        <div class="pagebanner" align="center" style="width: 305px;">{0} {1} encontrados, mostrando desde el {2} hasta el {3}</div>
								    </display:setProperty>
								    <display:setProperty name="paging.banner.no_items_found">
										<div class="pagebanner">No se encontraron {0}.</div>
								    </display:setProperty>						    
								    <display:setProperty name="paging.banner.one_item_found">
										<div class="pagebanner">Un {0} encontrado.</div>
								    </display:setProperty>						    						    
								    <display:setProperty name="paging.banner.all_items_found">
										<div class="pagebanner">{0} {1} encontrados, mostrando todos.</div>
								    </display:setProperty>						    								    
									    						    
						       		<display:setProperty name="paging.banner.full">
						       			<div class="pagelinks" align="center" style="width: 305px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
						       		</display:setProperty>
						       		<display:setProperty name="paging.banner.first">
						       			<div class="pagelinks" align="center" style="width: 305px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
						       		</display:setProperty>
						       		<display:setProperty name="paging.banner.last">
						       			<div class="pagelinks" align="center" style="width: 305px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
						       		</display:setProperty>
					        	</display:table>
			        		</f:verbatim>
						</c:if>
					</s:fieldset>
						
					<f:verbatim>&nbsp;</f:verbatim>
					
					<s:fieldset legend="Ordenes de pago" style="height: 325px;">
						<c:if test="${empty ImputacionBean.cuotasOrdenes}">
							<h:outputText value="No existen ordenes de pago." styleClass="texto"/>
						</c:if>						
						
						<c:if test="${!empty ImputacionBean.cuotasOrdenes}">						
						
		        			<f:verbatim>
					        	<display:table id="ordenes" 
					        				   name="sessionScope.ImputacionBean.cuotasOrdenes" 
					        				   defaultsort="1" pagesize="10" export="false"
					        				   class="tableA" requestURIcontext="true"
					        				   requestURI="${ImputacionBean.rutaRedireccion}"
					        				   style="width: 430px;">
					        		<display:column property="tipo" title="Tipo y Número" sortable="true" class="tdA"/>
					        		<display:column property="fechaEmision" title="Emitida" sortable="true" class="tdA"/>
					        		<display:column property="imputado" title="Imputado" sortable="true" class="tdA"/>
					        		<display:column property="cuenta" title="A cuenta" sortable="true" class="tdA"/>
									<display:column style="width:8%" media="html">
										<input type="checkbox" id="checkOrdenes[${ordenes.cuota.idCuotaComprobante}]" name="checkOrdenes[${ordenes.cuota.idCuotaComprobante}]"/>
										<input type="hidden" id="checkOrdenes" name="checkOrdenes" value="${ordenes.cuota.idCuotaComprobante}"/>
									</display:column>
		
								    <display:setProperty name="sort.amount" value="list" />
								    <display:setProperty name="paging.banner.group_size" value="6" />
								    <display:setProperty name="paging.banner.placement" value="bottom" />
								    <display:setProperty name="paging.banner.item_name" value="Orden" />
								    <display:setProperty name="paging.banner.items_name" value="" />
								    <display:setProperty name="paging.banner.some_items_found">
							        <div class="pagebanner" align="center" style="width: 305px;">{0} {1} encontrados, mostrando desde el {2} hasta el {3}</div>
								    </display:setProperty>
								    <display:setProperty name="paging.banner.no_items_found">
										<div class="pagebanner">No se encontraron {0}.</div>
								    </display:setProperty>						    
								    <display:setProperty name="paging.banner.one_item_found">
										<div class="pagebanner">Una {0} encontrada.</div>
								    </display:setProperty>						    						    
								    <display:setProperty name="paging.banner.all_items_found">
										<div class="pagebanner">{0} {1} encontrados, mostrando todos.</div>
								    </display:setProperty>						    								    
									    						    
						       		<display:setProperty name="paging.banner.full">
						       			<div class="pagelinks" align="center" style="width: 305px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
						       		</display:setProperty>
						       		<display:setProperty name="paging.banner.first">
						       			<div class="pagelinks" align="center" style="width: 305px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
						       		</display:setProperty>
						       		<display:setProperty name="paging.banner.last">
						       			<div class="pagelinks" align="center" style="width: 305px;"><a href={1}><img src="<%=request.getContextPath()%>/img/icon/skipb_16.gif"></a><a href={2}><img src="<%=request.getContextPath()%>/img/icon/rewnd_16.gif"></a>{0}<a href={3}><img src="<%=request.getContextPath()%>/img/icon/fastf_16.gif"></a><a href={4}><img src="<%=request.getContextPath()%>/img/icon/skipf_16.gif"></a></div>
						       		</display:setProperty>
					        	</display:table>
			        		</f:verbatim>						
						
						</c:if>
					</s:fieldset>
					</h:panelGrid>
        		</x:div>
				</c:if>

				<c:if test="${ImputacionBean.validado==true}">
				
					<c:if test="${empty ImputacionBean.cuotasComprobates || empty ImputacionBean.cuotasOrdenes}">
						<h:panelGrid columns="2" align="center">
							<h:outputText value="Imposible imputar." styleClass="texto"/>
						</h:panelGrid>
					</c:if>
	
					<c:if test="${!empty ImputacionBean.cuotasComprobates && !empty ImputacionBean.cuotasOrdenes}">
					<h:panelGrid columns="2" align="center">
						<h:commandButton action="#{ImputacionBean.generar}" value="Cargar el monto a imputaciones seleccionadas" styleClass="botones"/>
					</h:panelGrid>

					<c:if test="${!empty ImputacionBean.imputaciones}">
						<h:panelGrid columns="1" align="center">
						<s:fieldset legend="Tabla de imputaciones">
						<x:dataTable id="tablaImputaciones" 
					                 styleClass="standardTable"
					                 headerClass="dataTable_Header"
					                 footerClass="standardTable_Header"
					                 rowClasses="standardTable_Row1,standardTable_Row2"
					                 columnClasses="dataTable_columns,standardTable_ColumnCentered,standardTable_Column"						
					                 var="imp" renderedIfEmpty="false"
					                 value="#{ImputacionBean.imputaciones}" style=" width : 680px;">
						 	<h:column>
			                	<f:facet name="header">
			                    	<h:outputText value="Comprobante" styleClass="texto" style="color: white;"/>
								</f:facet>
								<h:outputText value="#{imp.comprobanteText}" styleClass="texto" />
				            </h:column>		                   	
				                   	
			               	<h:column>
			                	<f:facet name="header">
			                    	<h:outputText value="Orden" styleClass="texto" style="color: white;"/>
			                    </f:facet>
								<h:outputText value="#{imp.ordenText}" styleClass="texto" />
				    		</h:column>
				                   	
						    <h:column>
			                	<f:facet name="header">
			                    	<h:outputText value="Imputa Por" styleClass="texto" style="color: white;"/>
			                  	</f:facet>
								<x:inputText id="monto"	value="#{imp.monto}" style="width: 60px;" 
										 styleClass="bordeceldainferior" onfocus="encender(this);" 
										 onblur="apagar(this);" onkeypress="return soloDecimales(this,event);"/>
				        	</h:column>
				        	
						    <h:column>
								<x:commandLink action="#{ImputacionBean.eliminarImputacion}" id="eliminarImpLink">
									<f:param id="idComprobante" name="idComprobante" value="#{imp.cuotasImpComprobante.idImputable}"/>
									<f:param id="idOrden" name="idOrden" value="#{imp.cuotasImpOrden.idImputable}"/>
									<x:graphicImage value="/img/cat_act.gif" title="Eliminar imputación." border="0"/>
								</x:commandLink>				        	
							</h:column>
						</x:dataTable>
						</s:fieldset>
						</h:panelGrid>
					</c:if>
					</c:if>
				</c:if>
