<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text"/>
<xsl:template match="ticket">
<ticket>
%centrar%TARJETA FIEL - VISION S.A.
%centrar%CUIT 30-70704996-7 - I.B. 000/070962/8

%centrar%*** RETIRO DE VALORES ***

Fecha:<xsl:text> </xsl:text><xsl:value-of select="header/fecha"/><xsl:text>  </xsl:text><xsl:value-of select="header/hora"/>
Caja:<xsl:text> </xsl:text><xsl:value-of select="header/caja"/><xsl:text>  </xsl:text>Operador:<xsl:text> </xsl:text><xsl:value-of select="header/operador"/>
<xsl:for-each select="retiros/retiro">
<xsl:text>
</xsl:text>

<xsl:if test="@cheque = 'true'">
RETIRO DE CHEQUES:
NUMERO          TIPO           IMPORTE
<xsl:for-each select="cheque">
<xsl:sort select="numero" data-type="number" order="ascending"/>
<xsl:call-template name="padRightSide">
	<xsl:with-param name="stringToPad"  select="numero"/>
	<xsl:with-param name="totalLength" select="14"/>
</xsl:call-template>
<xsl:call-template name="padLeftSide">
<xsl:with-param name="stringToPad" select="tipo"/>
<xsl:with-param name="totalLength" select="5"/>
</xsl:call-template>
<xsl:call-template name="padLeftSide">
<xsl:with-param name="stringToPad" select="importe"/>
<xsl:with-param name="totalLength" select="19"/>
</xsl:call-template>
<xsl:text>
</xsl:text>	
</xsl:for-each>
</xsl:if>
<xsl:if test="@cheque = 'false'">
RETIRO DE:
DESCRIPCION                    IMPORTE      
<xsl:call-template name="padRightSide">
	<xsl:with-param name="stringToPad"  select="medio"/>
	<xsl:with-param name="totalLength" select="25"/>
</xsl:call-template>
<xsl:call-template name="padLeftSide">    
	<xsl:with-param name="stringToPad"  select="importe"/> 
	<xsl:with-param name="totalLength" select="13"/>
</xsl:call-template>
<xsl:text>
</xsl:text>	
</xsl:if>
</xsl:for-each>
</ticket>
</xsl:template>

<!-- template to pad the left side of strings (and right justificy) -->
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