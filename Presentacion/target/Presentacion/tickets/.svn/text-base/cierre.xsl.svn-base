<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text"/>
<xsl:template match="ticket">
<ticket>
%centrar%CIERRE (<xsl:value-of select="cierre/@tipo"/>) - Resumido

CAJA: <xsl:value-of select="header/caja"/>
CAJERO: <xsl:value-of select="header/operador"/>
APERTURA: <xsl:value-of select="header/fechaApertura"/><xsl:text>  </xsl:text><xsl:value-of select="header/horaApertura"/>
LISTADO:  <xsl:value-of select="header/fecha"/><xsl:text>  </xsl:text><xsl:value-of select="header/hora"/>
<xsl:for-each select="cierre/cuenta">

---------------------------------------
CUENTA: <xsl:value-of select="substring(@nombre,1,25)"/>

DESCRIPCION                     IMPORTE
<xsl:for-each select="medios/medio">
<xsl:sort select="descripcion" data-type="text" order="ascending"/>
<xsl:call-template name="padRightSide">
	<xsl:with-param name="stringToPad"  select="descripcion"/>
	<xsl:with-param name="totalLength" select="25"/>
</xsl:call-template>
<xsl:call-template name="padLeftSide">
<xsl:with-param name="stringToPad" select="format-number(importe, '###.##0,00', 'pesoFormat')"/>
<xsl:with-param name="totalLength" select="14"/>
</xsl:call-template>
<xsl:text>
</xsl:text>
</xsl:for-each>
<xsl:text>
</xsl:text>
Saldo Final:               <xsl:call-template name="padLeftSide">
				   <xsl:with-param name="stringToPad"  select="format-number(@saldoFinal, '###.##0,00', 'pesoFormat')"/> 
				   <xsl:with-param name="totalLength" select="12"/>
			      </xsl:call-template>	
<xsl:text>
</xsl:text>
<xsl:if test="arqueos/arqueo!=''">
ARQUEO:
DESCRIPCION                    CANTIDAD
<xsl:for-each select="arqueos/arqueo">
<xsl:call-template name="padRightSide">
	<xsl:with-param name="stringToPad"  select="substring(descripcion,6,20)"/>
	<xsl:with-param name="totalLength" select="20"/>
</xsl:call-template>
<xsl:call-template name="padLeftSide">
<xsl:with-param name="stringToPad" select="cantidad"/>
<xsl:with-param name="totalLength" select="16"/>
</xsl:call-template>
<xsl:text>
</xsl:text>
</xsl:for-each>
TOTAL ARQUEO:              <xsl:call-template name="padLeftSide">
				   	<xsl:with-param name="stringToPad"  select="format-number(arqueos/@arqueoTotal, '###.##0,00', 'pesoFormat')"/> 
				   	<xsl:with-param name="totalLength" select="12"/>
				   </xsl:call-template>
<xsl:text>
</xsl:text>
<xsl:value-of select="arqueos/@faltante-sobrante"/>
<xsl:if test="@ajuste = 'true'">
DESCRIPCION                     IMPORTE
<xsl:call-template name="padRightSide">
	<xsl:with-param name="stringToPad"  select="ajuste/medio/descripcion"/>
	<xsl:with-param name="totalLength" select="24"/>
</xsl:call-template>
<xsl:call-template name="padLeftSide">
<xsl:with-param name="stringToPad" select="signo"/>
<xsl:with-param name="totalLength" select="1"/>
</xsl:call-template>
<xsl:call-template name="padLeftSide">
<xsl:with-param name="stringToPad" select="ajuste/medio/importe"/>
<xsl:with-param name="totalLength" select="14"/>
</xsl:call-template>
</xsl:if>
</xsl:if>
</xsl:for-each>
</ticket>
</xsl:template>

<xsl:template name="padLeftSide">
    <xsl:param name="stringToPad"/>
    <xsl:param name="totalLength"/>
    <xsl:param name="padChar" select="' '"/>
    <xsl:param name="padBuffer" select="concat($padChar,$padChar,$padChar,$padChar,$padChar,$padChar,$padChar,$padChar,$padChar,$padChar)"/>
    <xsl:variable name="vNewString" select="concat($padBuffer, $stringToPad)"/>

    <xsl:choose>
        <xsl:when test="not(string-length($vNewString) >= $totalLength)">
            <xsl:call-template name="padLeftSide">
                <xsl:with-param name="stringToPad" select="$vNewString"/>
                <xsl:with-param name="totalLength" select="$totalLength"/>
                <xsl:with-param name="padChar" select="$padChar"/>
                <xsl:with-param name="padBuffer" select="$padBuffer"/>
            </xsl:call-template>
        </xsl:when>
        <xsl:otherwise>

            <xsl:value-of select="substring($vNewString,string-length($vNewString) - $totalLength + 1)"/>
        </xsl:otherwise>
    </xsl:choose>
 </xsl:template>

 <!-- template to pad the right side of strings -->
 <xsl:template name="padRightSide">
    <xsl:param name="totalLength"/>
    <xsl:param name="padChar" select="' '"/>
    <xsl:param name="stringToPad"/>
    <xsl:param name="padBuffer" select="concat($padChar,$padChar,$padChar,$padChar,$padChar,$padChar,$padChar,$padChar,$padChar,$padChar)"/>
    <xsl:variable name="vNewString" select="concat($stringToPad, $padBuffer)"/>
    <xsl:choose>
        <xsl:when test="not(string-length($vNewString) >= $totalLength)">
            <xsl:call-template name="padRightSide">
                <xsl:with-param name="stringToPad" select="$vNewString"/>
                <xsl:with-param name="totalLength" select="$totalLength"/>
                <xsl:with-param name="padChar" select="$padChar"/>
                <xsl:with-param name="padBuffer" select="$padBuffer"/>
            </xsl:call-template>
        </xsl:when>
        <xsl:otherwise>
            <xsl:value-of select="substring($vNewString,1,$totalLength)"/>
        </xsl:otherwise>
    </xsl:choose>


</xsl:template>
<xsl:decimal-format name="pesoFormat" decimal-separator="," grouping-separator="."/>

</xsl:stylesheet>