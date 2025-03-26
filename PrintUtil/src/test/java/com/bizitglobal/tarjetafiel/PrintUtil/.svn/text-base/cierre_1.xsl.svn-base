<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text"/>
<xsl:template match="ticket">
<ticket>
%centrar%CIERRE (<xsl:value-of select="cierre/@tipo"/>) - Resumido

CAJA Numero: <xsl:value-of select="header/caja"/>	
CAJERO: <xsl:value-of select="header/operador"/>
Apertura: <xsl:value-of select="header/fechaApertura"/><xsl:text>  </xsl:text><xsl:value-of select="header/horaApertura"/>
Listado:  <xsl:value-of select="header/fecha"/><xsl:text>  </xsl:text><xsl:value-of select="header/hora"/>


<xsl:for-each select="cierre/cuenta">

---------------------------------------
CUENTA: <xsl:value-of select=" @nombre"/>
<xsl:text>
</xsl:text>
<xsl:for-each select="medios/medio">
<xsl:text>
</xsl:text>
<xsl:value-of select="id"/><xsl:text> </xsl:text><xsl:value-of select="descripcion"/><xsl:text> </xsl:text><xsl:value-of select="importe"/><xsl:text> </xsl:text><xsl:value-of select="signo"/>
</xsl:for-each>
<xsl:text>
</xsl:text>
Saldo Final:        <xsl:value-of select="@saldoFinal"/><xsl:text>  DB</xsl:text>
<xsl:text>
</xsl:text>
<xsl:if test="arqueos/arqueo!=''">
ARQUEO<xsl:for-each select="arqueos/arqueo">
<xsl:text>
</xsl:text>
<xsl:value-of select="cantidad"/><xsl:text> </xsl:text>de<xsl:text>  </xsl:text><xsl:value-of select="descripcion"/>
</xsl:for-each>

TOTAL ARQUEO:	<xsl:value-of select="arqueos/@arqueoTotal"/>
<xsl:text>
</xsl:text>

<xsl:value-of select="arqueos/@faltante-sobrante"/>
</xsl:if>
</xsl:for-each>

<xsl:if test="cierre/cheques/@hayCheques = 'true'">
CHEQUES EN CAJA
Número    Tipo    Importe     
<xsl:for-each select="cierre/cheques/cheque">
<xsl:value-of select="numero"/> <xsl:text> </xsl:text><xsl:value-of select="tipo"/> <xsl:text>     </xsl:text><xsl:value-of select="importe"/>
</xsl:for-each>
</xsl:if>
</ticket>
</xsl:template>

</xsl:stylesheet>