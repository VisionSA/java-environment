<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text"/>
<xsl:template match="ticket">
<ticket>
%centrar%CANCELACION DE PAGO
CAJA Numero: <xsl:value-of select="header/caja"/>
CAJERO: <xsl:value-of select="header/operador"/>
FECHA:  <xsl:value-of select="header/fecha"/><xsl:text>  </xsl:text><xsl:value-of select="header/hora"/>



Nro de comprobante: <xsl:value-of select="cancelacionPago/nroComprobante/@valor"/>
IMPORTE   $: <xsl:value-of select="cancelacionPago/importe/@valor"/>

	

</ticket>
</xsl:template>

</xsl:stylesheet>