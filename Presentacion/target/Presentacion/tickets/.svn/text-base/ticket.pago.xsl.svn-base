<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text"/>
<xsl:template match="ticket">
<ticket>

<![CDATA[
%centrar%COMPROBANTE DE PAGO

%centrar%TARJETA FIEL - VISION S.A.
%centrar%CUIT 30-70704996-7 - I.B. 000/070962/8
]]>
Fecha:<xsl:value-of select="header/fecha"/> <xsl:text> </xsl:text> <xsl:value-of select="header/hora"/> Trans.: <xsl:value-of select="header/transaccion"/>
Caja: <xsl:value-of select="header/caja"/> Operador: <xsl:value-of select="header/operador"/> <xsl:text> </xsl:text><xsl:value-of select="translate(header/nombre , $vLowercaseChars_CONST , $vUppercaseChars_CONST)"/>
Cliente:<xsl:value-of select="header/cuenta"/><xsl:text> </xsl:text> <xsl:value-of select="substring(translate(header/cliente , $vLowercaseChars_CONST , $vUppercaseChars_CONST),1,24)"/>
<xsl:text> 
</xsl:text>
Medio Pago     Importe   Bco. N. Cheque
<xsl:for-each select="transacciones/transaccion">
<xsl:call-template name="padRightSide">
	<xsl:with-param name="stringToPad"  select="medio-pago"/>
	<xsl:with-param name="totalLength" select="12"/>
</xsl:call-template>
<xsl:call-template name="padLeftSide">    
	<xsl:with-param name="stringToPad"  select="importe"/> 
	<xsl:with-param name="totalLength" select="10"/>
</xsl:call-template>
<xsl:text>    </xsl:text>
<xsl:call-template name="padLeftSide">
<xsl:with-param name="stringToPad" select="cheque-nro"/>
<xsl:with-param name="totalLength" select="13"/>
</xsl:call-template>
<xsl:text>
</xsl:text>
</xsl:for-each>
Total: 	    <xsl:call-template name="padLeftSide">
			<xsl:with-param name="stringToPad"  select="transacciones/@total"/> 
			<xsl:with-param name="totalLength" select="10"/>
		    </xsl:call-template>	

Nro. Resumen      Fec. Vto      Importe
<xsl:for-each select="resumenes/resumen">

<xsl:call-template name="padRightSide">
	<xsl:with-param name="stringToPad"  select="nro-resumen"/>
	<xsl:with-param name="totalLength" select="14"/>
</xsl:call-template>

<xsl:call-template name="padLeftSide">
<xsl:with-param name="stringToPad" select="fec-vto"/>
<xsl:with-param name="totalLength" select="13"/>
</xsl:call-template>

<xsl:call-template name="padLeftSide">
<xsl:with-param name="stringToPad" select="format-number(importe, '##.##0,00', 'pesoFormat')"/>
<xsl:with-param name="totalLength" select="12"/>
</xsl:call-template>

<xsl:text>
</xsl:text>
</xsl:for-each>
<xsl:text>
</xsl:text>
<xsl:value-of select="substring (header/cliente,1,1)"/>
<xsl:value-of select="substring (header/fecha,1,2)"/>
<xsl:value-of select="substring (transacciones/@total,2,1)"/>
<xsl:value-of select="substring (header/cliente,3,1)"/>
<xsl:value-of select="substring (header/hora,4,2)"/>
<xsl:value-of select="substring (header/fecha,4,2)"/>
<xsl:value-of select="substring (header/hora,1,2)"/>
<xsl:value-of select="substring (header/transaccion,6,1)"/>
<xsl:value-of select="substring (header/cliente,2,1)"/>
<xsl:value-of select="substring (header/cuenta,4,2)"/>
<xsl:text>
</xsl:text>
%centrar%Comprobante de caja valido 
%centrar%sin sellos.
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
<xsl:variable name="vLowercaseChars_CONST" select="'abcdefghijklmnopqrstuvwxyz'"/>
<xsl:variable name="vUppercaseChars_CONST" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>
</xsl:stylesheet>