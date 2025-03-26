<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text"/>
<xsl:template match="ticket">
<ticket>
<![CDATA[
%centrar%TARJETA FIEL - VISION S.A.
%centrar%CUIT 30-70704996-7 - I.B. 000/070962/8
]]>
%centrar%ORDEN DE PAGO

Fecha Emision:<xsl:value-of select="header/fecha"/> <xsl:text> </xsl:text> <xsl:value-of select="header/hora"/>
Caja: <xsl:value-of select="header/caja"/> Operador: <xsl:value-of select="header/operador"/>
<xsl:if test="pagos/@porCuit = 'true'">
Empresa: <xsl:value-of select="pagos/@cuit"/>
<xsl:value-of select="pagos/@empresa"/>
</xsl:if>
<xsl:if test="pagos/@porCuit = 'false'">
Comercio: <xsl:value-of select="pagos/@codigo"/>
<xsl:value-of select="pagos/@empresa"/>
</xsl:if>

<xsl:for-each select="pagos/pago">

Liquidacion Nro: <xsl:value-of select="@liquidacion"/>
<xsl:text>
</xsl:text>
<xsl:for-each select="medios/medio">
<xsl:value-of select="@nombre"/>
<xsl:if test="@tipo = 'cheque'">
Numero          Fecha         Importe
<xsl:call-template name="padRightSide">
	<xsl:with-param name="stringToPad"  select="@numero"/>
	<xsl:with-param name="totalLength" select="10"/>
</xsl:call-template>
<xsl:call-template name="padLeftSide">
	<xsl:with-param name="stringToPad" select="@fecha"/>
	<xsl:with-param name="totalLength" select="13"/>
</xsl:call-template>
<xsl:call-template name="padLeftSide">
	<xsl:with-param name="stringToPad" select="@importe"/>
	<xsl:with-param name="totalLength" select="16"/>
</xsl:call-template>
</xsl:if>

<xsl:if test="@tipo = 'acreditacion'">
Fecha                    Importe
<xsl:call-template name="padLeftSide">
<xsl:with-param name="stringToPad" select="@fecha"/>
<xsl:with-param name="totalLength" select="13"/>
</xsl:call-template>
<xsl:call-template name="padLeftSide">
<xsl:with-param name="stringToPad" select="@importe"/>
<xsl:with-param name="totalLength" select="22"/>
</xsl:call-template>
</xsl:if>
<xsl:if test="@tipo = 'efectivo'">
<xsl:call-template name="padLeftSide">
<xsl:with-param name="stringToPad" select="@importe"/>
<xsl:with-param name="totalLength" select="12"/>
</xsl:call-template>
</xsl:if>
</xsl:for-each>
</xsl:for-each>

Firma:     _________________________

Aclaracion _________________________

Nro. Doc   _________________________
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