<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text"/>
<xsl:template match="ticket">
<ticket>
<![CDATA[
%centrar%TARJETA FIEL - VISION S.A.
%centrar%CUIT 30-70704996-7 - I.B. 000/070962/8
]]>
Fecha:<xsl:value-of select="header/fecha"/><xsl:text> </xsl:text><xsl:value-of select="header/hora"/> Trans.: <xsl:value-of select="header/transaccion"/>
Caja: <xsl:value-of select="header/caja"/> Operador: <xsl:value-of select="header/operador"/> <xsl:text></xsl:text><xsl:text> </xsl:text><xsl:value-of select="translate(header/nombre , $vLowercaseChars_CONST , $vUppercaseChars_CONST)"/>
Abogado: <xsl:value-of select="abogado"/>
<xsl:text></xsl:text>
----------------------------------------
Cuenta      Nombre              Pagado
----------------------------------------
<xsl:for-each select="clientes/cliente">
<xsl:sort select="id-cliente" data-type="number" order="ascending"/>
<xsl:call-template name="padRightSide">
	<xsl:with-param name="stringToPad"  select="id-cliente"/>
	<xsl:with-param name="totalLength" select="6"/>
</xsl:call-template>

<xsl:call-template name="padRightSide">
	<xsl:with-param name="stringToPad"  select="nombre-apellido"/>
	<xsl:with-param name="totalLength" select="23"/>
</xsl:call-template><xsl:text> </xsl:text>

<xsl:call-template name="padLeftSide">    
	<xsl:with-param name="stringToPad"  select="format-number(@total, '###.##0,00', 'pesoFormat')"/>
	<xsl:with-param name="totalLength" select="10"/>
</xsl:call-template>
<xsl:text>
</xsl:text>
</xsl:for-each>
%centrar%**************************************
%centrar%T O T A L     G E N E R A L
%centrar%**************************************
<xsl:choose>
	<xsl:when test="number(clientes/@tEfectivo) != 0">
Efectivo:                 $<xsl:call-template name="padLeftSide">    
						<xsl:with-param name="stringToPad"  select="format-number(clientes/@tEfectivo, '###.##0,00', 'pesoFormat')"/>
						<xsl:with-param name="totalLength" select="10"/>
					 </xsl:call-template>  
</xsl:when>
</xsl:choose>
<xsl:choose>
	<xsl:when test="number(clientes/@tTickets) != 0">
Tickets:                  $<xsl:call-template name="padLeftSide">    
						<xsl:with-param name="stringToPad"  select="format-number(clientes/@tTickets, '###.##0,00', 'pesoFormat')"/>
						<xsl:with-param name="totalLength" select="10"/>
					 </xsl:call-template>  
	</xsl:when>
</xsl:choose>
<xsl:choose>
	<xsl:when test="number(clientes/@tCheques) != 0">
Cheques:                  $<xsl:call-template name="padLeftSide">    
							<xsl:with-param name="stringToPad"  select="format-number(clientes/@tCheques, '###.##0,00', 'pesoFormat')"/>
							<xsl:with-param name="totalLength" select="10"/>
						</xsl:call-template> 
	</xsl:when>
</xsl:choose>
<xsl:choose>
	<xsl:when test="number(clientes/@tDepositos) != 0">
Dep. Bancarios:           $<xsl:call-template name="padLeftSide">    
							<xsl:with-param name="stringToPad"  select="format-number(clientes/@tDepositos, '###.##0,00', 'pesoFormat')"/>
							<xsl:with-param name="totalLength" select="10"/>
						 </xsl:call-template>     
	</xsl:when>
</xsl:choose>
Total:                    $<xsl:call-template name="padLeftSide">    
							<xsl:with-param name="stringToPad"  select="format-number(clientes/@total, '###.##0,00', 'pesoFormat')"/>
							<xsl:with-param name="totalLength" select="10"/>
						 </xsl:call-template> 
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