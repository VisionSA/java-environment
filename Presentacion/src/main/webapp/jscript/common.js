//********************
// variables globales
//********************
var is_gecko = /gecko/i.test(navigator.userAgent);
var is_ie    = /MSIE/.test(navigator.userAgent);
var colorLlamativo = '#FFE7C3';
var colorSuave = '#FFFFFF';

// Variables que permiten el cambio de color de las grillas al pasar el mouse sobre los registros.
var colorSeleccionado='';
var colorCambio='#A5CBFF';

// Al seleccionar el registro.
function seleccion(elemento) {
	colorSeleccionado = elemento.style.backgroundColor;
	elemento.style.backgroundColor = colorCambio;
}

// Al de seleccionar el registro.
function deSeleccion(elemento) {
	elemento.style.backgroundColor = colorSeleccionado;	
}

function setSelectionRange(input, start, end) {
	if (is_gecko) {
		input.setSelectionRange(start, end);
	} else {
		// assumed IE
		var range = input.createTextRange();
		range.collapse(true);
		range.moveStart("character", start);
		range.moveEnd("character", end - start);
		range.select();
	}
};

function getSelectionStart(input) {
	if (is_gecko)
		return input.selectionStart;
	var range = document.selection.createRange();
	var isCollapsed = range.compareEndPoints("StartToEnd", range) == 0;
	if (!isCollapsed)
		range.collapse(true);
	var b = range.getBookmark();
	return b.charCodeAt(2) - 2;
};

function getSelectionEnd(input) {
	if (is_gecko)
		return input.selectionEnd;
	var range = document.selection.createRange();
	var isCollapsed = range.compareEndPoints("StartToEnd", range) == 0;
	if (!isCollapsed)
		range.collapse(false);
	var b = range.getBookmark();
	return b.charCodeAt(2) - 2;
};

//funcion para validar número de CUIT
function ValidaCuit(nro){
	nro = nro.replace(/-/g,"");

	var suma;
	var resto;
	var verif;
	var pos = nro.split('');
	
	if (!/^\d{11}$/.test(nro)) {return false;}	
	while (true){
		suma = (pos[0] * 5 + pos[1] * 4 + pos[2] * 3 +
		pos[3] * 2 + pos[4] * 7 + pos[5] * 6 +
		pos[6] * 5 + pos[7] * 4 + pos[8] * 3 + pos[9] * 2);
		resto = suma % 11;
	
		if (resto == 0){verif = 0; break;}
		else if (resto == 1 && (pos[1] == 0 || pos[6] == 7)){pos[1] = 4; continue;}
		else {verif = 11 - resto; break;}
	}
	return pos[10] == verif;
}

// Valida que la fecha sea correcta.
function isDate(dtStr){  
	var dtCh = "/";
	var dAct = new Date();
	var minYear = dAct.getFullYear()-50;
	var maxYear = dAct.getFullYear()+50;

	var pos1 = dtStr.indexOf(dtCh);
	var pos2 = dtStr.indexOf(dtCh, pos1+1);
	
	var strDay  = dtStr.substring(0, pos1);
	var strMonth= dtStr.substring(pos1+1, pos2);
	var strYear = dtStr.substring(pos2+1);
	
	strYr = strYear;
	if (strDay.charAt(0)=="0" && strDay.length > 1){
		strDay = strDay.substring(1);
	}
	if (strMonth.charAt(0)=="0" && strMonth.length > 1){
		strMonth = strMonth.substring(1);
	}
	for (var i = 1; i <= 3; i++){
		if (strYr.charAt(0)=="0" && strYr.length > 1){strYr = strYr.substring(1);}
	}
	var daysInMonth = DaysArray(12);
	month= parseInt(strMonth);
	day  = parseInt(strDay);
	year = parseInt(strYr);

	if (pos1==-1 || pos2==-1){return false;}
	if (strMonth.length < 1 || month < 1 || month > 12){return false;}
	if (strDay.length<1||day<1||day>31||(month==2&&day > daysInFebruary(year))||day > daysInMonth[month]){return false;}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){return false;}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){return false;}

	return true
}

//Permite que se ingrese un número entero en un Input Text
function soloEnteros(InputText, evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	var strValue = '';
		
  	var selectionStart = getSelectionStart(InputText);
  	var selectionEnd = getSelectionEnd(InputText);
  	strValue = InputText.value.substring(0, selectionStart) + String.fromCharCode(charCode) + InputText.value.substring(selectionEnd);
		
	if (!isInteger(strValue) && charCode > 31) {
		return false;
	}
	return true;
}

//Permite que se ingrese un número decimal en un Input Text
function soloDecimales(InputText, evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	var strValue = '';
		
 	var selectionStart = getSelectionStart(InputText);
  	var selectionEnd = getSelectionEnd(InputText);
	strValue = InputText.value.substring(0, selectionStart) + String.fromCharCode(charCode) + InputText.value.substring(selectionEnd);
		
	if (!isDecimal(strValue) && charCode > 31) {
		return false;
	}
				
	return true;
}


// Retorna true si inputVal es decimal.
function isDecimal(inputVal) {
	oneDecimal = false;
	inputStr = inputVal.toString();
	for (var i = 0; i < inputStr.length; i++) {
		var oneChar = inputStr.charAt(i);
		if (i == 0 && oneChar == "-") {
			continue;
		}
		if (oneChar == "." && !oneDecimal) {
			oneDecimal = true;
			continue;
		}
		if (oneChar < "0" || oneChar > "9") {
			return false;
		}
	}
	return true;
}

// Retorna true si inputVal es entero.
function isInteger(inputVal) {
	inputStr = inputVal.toString();
	for (var i = 0; i < inputStr.length; i++) {
		var oneChar = inputStr.charAt(i);
		if (i == 0 && oneChar == "-") {
			continue;
		}
		if (oneChar < "0" || oneChar > "9") {
			return false;
		}
	}
	return true;
}

// Retorna true si inputStr es vacio o nulo.
function isEmpty(inputStr) {
	if (inputStr == "" || inputStr == null) {
		return true
	}
	return false
}

//Función que simula el Tab al presionar Enter.
function enterToTab(objeto, event){
	var keyCode = event.keyCode?event.keyCode:event.which?event.which:event.charCode;
	if (keyCode == 13){
		var i; for (i = 0; i < objeto.form.elements.length; i++){
			if (objeto == objeto.form.elements[i]){break;}
		}
		i = objeto.form.elements[i].tabIndex + 1;
		for( j = 0 ; j < objeto.form.elements.length; j++){
			if( objeto.form.elements[j].tabIndex == i){break;}
		}
		objeto.form.elements[j].focus(); return false;
	} else {return true;}
}

// Abre un popup que contiene la pagina
function popup(pagina,popW,popH) {
	var w = 0, h = 0;
   	w = screen.width;
   	h = screen.height;

	var leftPos = (w-popW)/2, topPos = (h-popH)/2;

    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
    if (popupWindow.opener == null){popupWindow.opener = self;}
}

// Cambia el color de fondo de un objeto.
function encender(elemento) {
    if (!document.layers) {
    	elemento.style.background = colorLlamativo;
    }
    
    return true;
}

// Retorna el color de fondo del objeto.
function apagar(elemento) {
    if (!document.layers) {
	    elemento.style.background = colorSuave;
    }
	    
    return true;
}

// Cambia de visible a no visible una div con nombre:"unaDiv"
function change(unaDiv) {
	var element = document.getElementById(unaDiv);
	element.style.display= (element.style.display=='none')?'block':'none';
}

function resetearText(objActual, idResetear){
	var id = objActual.id;

	index1 = id.lastIndexOf(":");
	var idObj = id.substring(0, index1);  
	var idObjReset = idObj + ":" + idResetear;
	var element = document.getElementById(idObjReset);
	element.value = '';		
}

//Se utiliza para armar el nombre del Asiento
function armarNombreAsiento(nombreCorto , razonSocial, codigo){
//	alert('ENTRO!!!');
	var element = document.getElementById('altaComprobantesForm:ConceptoAsiento');
	
	var obj1 = document.getElementById('altaComprobantesForm:NroCorto');
//	alert(obj1.value);
	
	var obj2 = document.getElementById('altaComprobantesForm:NroLargo');
//	alert(obj2.value);
	
	element.value = codigo + " " + razonSocial + " " + nombreCorto + " " + obj1.value + "-" + obj2.value;
}

function sumar2(idObj1, idObj2, idObjDestino){
	var objeto1 = document.getElementById(idObj1);
	var objeto2 = document.getElementById(idObj2);
	var objetoDestino = document.getElementById(idObjDestino);
	objetoDestino.innerHTML = parseFloat(objeto1.value) + parseFloat(objeto2.value);
}

function agrupar3(idObj1, idObj2, idObj3, idObjDestino){
	alert('Entro');
	var objeto1 = document.getElementById(idObj1);
	var objeto2 = document.getElementById(idObj2);
	var objeto3 = document.getElementById(idObj3);
	var objetoDestino = document.getElementById(idObjDestino);
	objetoDestino.innerHTML = objeto1.value + objeto2.value + objeto3.value;
	alert('Entro' + objetoDestino.value );
}

// Simula un click sobre algun link con id=linkId
function clickLink(linkId) {
  var fireOnThis = document.getElementById(linkId);
  if (document.createEvent) {
    var evObj = document.createEvent('MouseEvents');
    evObj.initEvent( 'click', true, false);
    fireOnThis.dispatchEvent(evObj);
  } else if (document.createEventObject) {
    fireOnThis.fireEvent('onclick');
  }
}

// Cambia el value de un elemento con idElement.
function viewUser(newId,idElement) {
	setValueId(newId,idElement);	
}

// Cambia el value de un elemento con idElement.
function setValueId(newId,idElement) {
	var elemento = document.getElementById(idElement);
	elemento.value = newId;
}

function validarCheckTelefono(elemento) {
	var idCompleto = elemento.id;
	var indiceCorchete = idCompleto.lastIndexOf("[");
	
	var id = idCompleto.substring(0,indiceCorchete);
	var indice = idCompleto.substring((indiceCorchete+1),(indiceCorchete+2));
	
	if(id=="esCelular") {
		var secundario = document.getElementById("esFax["+indice+"]");
		secundario.checked = false;
	} else {
		var secundario = document.getElementById("esCelular["+indice+"]");
		secundario.checked = false;
	}
}

function validarCheckDias(elemento) {
	var id = elemento.id;
	
	if(id=="chkFechaFactura") {
		var secundario = document.getElementById("chkDiaCC");
		secundario.checked = false;
	} else {
		var secundario = document.getElementById("chkFechaFactura");
		secundario.checked = false;	
	}
}

function validarCheckFF(elemento) {
	var id  = elemento.id;
	var ele = elemento;
	
	if(id == "chkFechaF"){
		var secundario = document.getElementById("chkCuentaC");
		if(ele.checked == true ) {
			secundario.checked = false;
		} else {
			secundario.checked = true;	
		}
	}else{
		var secundario = document.getElementById("chkFechaF");
		if(ele.checked == true ) {
			secundario.checked = false;
		} else {
			secundario.checked = true;	
		}
	}
}

function highlightTableRows(tableId) {
    var previousClass = null;
    var table = document.getElementById(tableId);
    var tbody = table.getElementsByTagName("tbody")[0];
    var rows = tbody.getElementsByTagName("tr");
    // add event handlers so rows light up and are clickable
    for (i=0; i < rows.length; i++) {
        rows[i].onmouseover = function() { previousClass=this.className;this.className+=' over' };
        rows[i].onmouseout = function() { this.className=previousClass };
        rows[i].onclick = function() {
            var cell = this.getElementsByTagName("td")[0];
            if (cell.getElementsByTagName("a").length > 0) {
                var link = cell.getElementsByTagName("a")[0];
                if (link.onclick) {
                    call = link.getAttributeValue("onclick");
                    // this will not work for links with onclick handlers that return false
                    eval(call);
                } else {
                  location.href = link.getAttribute("href");
                }
                this.style.cursor="wait";
            }
        }
    }
}

function ShowWait(theform) {
	//document.getElementById("divWait").style.display = '';
	disableForm(document.getElementById(theform));
}

function HideWait() {
	document.getElementById("divWait").style.display = 'none';
}

function disableForm(theform) {
	if (document.all || document.getElementById) {
		for (i = 0; i < theform.length; i++) {
			var tempobj = theform.elements[i];
			tempobj.disabled = true;
		}
		
		return true;
	} else {
		alert("The form has been submitted. But, since you're not using IE 4+ or NS 6, the submit button was not disabled on form submission.");
		return false;
	}
} 

function rellenarNro(quien,que,relleno){
	var cadcero='';
								
	for(i=0;i<(relleno-que.length);i++){
		cadcero+='0';
	}
	quien.value=cadcero+que;
}



function rellenarNroCorto(quien,que){
	var cadcero='';
								
	for(i=0;i<(4-que.length);i++){
		cadcero+='0';
	}
	quien.value=cadcero+que;
}

function rellenarNroLargo(quien,que){
	var cadcero='';
								
	for(i=0;i<(8-que.length);i++){
		cadcero+='0';
	}
	quien.value=cadcero+que;
}

function cambiarCheckTarea(elemento) {
	var id = elemento.id;
	
	if(id=="chkInicio") {
		var secundario = document.getElementById("chkFin");
		secundario.checked = false;
	} else {
		var secundario = document.getElementById("chkInicio");
		secundario.checked = false;	
	}
}

//El siguiente script es para la barra de progreso.

var w3c=(document.getElementById)?true:false;
var ie=(document.all)?true:false;
var N=-1;

function createBar(w,h,bgc,brdW,brdC,blkC,speed,blocks,count,action){
if(ie||w3c){
var t='<div id="_xpbar'+(++N)+'" style="visibility:visible; position:relative; overflow:hidden; width:'+w+'px; height:'+h+'px; background-color:'+bgc+'; border-color:'+brdC+'; border-width:'+brdW+'px; border-style:solid; font-size:1px;">';
t+='<span id="blocks'+N+'" style="left:-'+(h*2+1)+'px; position:absolute; font-size:1px">';
for(i=0;i<blocks;i++){
t+='<span style="background-color:'+blkC+'; left:-'+((h*i)+i)+'px; font-size:1px; position:absolute; width:'+h+'px; height:'+h+'px; '
t+=(ie)?'filter:alpha(opacity='+(100-i*(100/blocks))+')':'-Moz-opacity:'+((100-i*(100/blocks))/100);
t+='"></span>';
}
t+='</span></div>';
document.write(t);
var bA=(ie)?document.all['blocks'+N]:document.getElementById('blocks'+N);
bA.bar=(ie)?document.all['_xpbar'+N]:document.getElementById('_xpbar'+N);
bA.blocks=blocks;
bA.N=N;
bA.w=w;
bA.h=h;
bA.speed=speed;
bA.ctr=0;
bA.count=count;
bA.action=action;
bA.togglePause=togglePause;
bA.showBar=function(){
this.bar.style.visibility="visible";
}
bA.hideBar=function(){
this.bar.style.visibility="hidden";
}
bA.tid=setInterval('startBar('+N+')',speed);
return bA;
}}

function startBar(bn){
var t=(ie)?document.all['blocks'+bn]:document.getElementById('blocks'+bn);
if(parseInt(t.style.left)+t.h+1-(t.blocks*t.h+t.blocks)>t.w){
t.style.left=-(t.h*2+1)+'px';
t.ctr++;
if(t.ctr>=t.count){
eval(t.action);
t.ctr=0;
}}else t.style.left=(parseInt(t.style.left)+t.h+1)+'px';
}

function togglePause(){
if(this.tid==0){
this.tid=setInterval('startBar('+this.N+')',this.speed);
}else{
clearInterval(this.tid);
this.tid=0;
}}

function togglePause(){
if(this.tid==0){
this.tid=setInterval('startBar('+this.N+')',this.speed);
}else{
clearInterval(this.tid);
this.tid=0;
}}


/**
// Modal Dialog Box
// copyright 8th July 2006 by Stephen Chapman
// http://javascript.about.com/
// permission to use this Javascript on your web page is granted
// provided that all of the code in this script (including these
// comments) is used without any alteration
function pageWidth() {return window.innerWidth != null? window.innerWidth: document.documentElement && document.documentElement.clientWidth ? document.documentElement.clientWidth:document.body != null? document.body.clientWidth:null;}
function pageHeight() {return window.innerHeight != null? window.innerHeight: document.documentElement && document.documentElement.clientHeight ? document.documentElement.clientHeight:document.body != null? document.body.clientHeight:null;}
function posLeft() {return typeof window.pageXOffset != 'undefined' ? window.pageXOffset:document.documentElement && document.documentElement.scrollLeft? document.documentElement.scrollLeft:document.body.scrollLeft? document.body.scrollLeft:0;}
function posTop() {return typeof window.pageYOffset != 'undefined' ? window.pageYOffset:document.documentElement && document.documentElement.scrollTop? document.documentElement.scrollTop: document.body.scrollTop?document.body.scrollTop:0;}
function $(x){return document.getElementById(x);}
function scrollFix(){var obol=$('ol');obol.style.top=posTop()+'px';obol.style.left=posLeft()+'px'}
function sizeFix(){var obol=$('ol');obol.style.height=pageHeight()+'px';obol.style.width=pageWidth()+'px';}
function kp(e){ky=e?e.which:event.keyCode;if(ky==88||ky==120)hm();return false}
function inf(h){
tag=document.getElementsByTagName('select');
for(i=tag.length-1;i>=0;i--)
	tag[i].style.visibility=h;
tag=document.getElementsByTagName('iframe');
for(i=tag.length-1;i>=0;i--)
	tag[i].style.visibility=h;
tag=document.getElementsByTagName('object');
for(i=tag.length-1;i>=0;i--)
	tag[i].style.visibility=h;}

function sm(obl, wd, ht){
var h='hidden';
var b='block';
var p='px';
var obol=$('ol'); 
var obbxd = $('mbd');
obbxd.innerHTML = $(obl).innerHTML;
obol.style.height=pageHeight()+p;
obol.style.width=pageWidth()+p;
obol.style.top=posTop()+p;
obol.style.left=posLeft()+p;
obol.style.display=b;
var tp=posTop()+((pageHeight()-ht)/2)-12;
var lt=posLeft()+((pageWidth()-wd)/2)-12;
var obbx=$('mbox');
obbx.style.top=(tp<0?0:tp)+p;
obbx.style.left=(lt<0?0:lt)+p;
obbx.style.width=wd+p;
obbx.style.height=ht+p;
inf(h);
obbx.style.display=b;
return false;}


function hm(){
var v='visible';
var n='none';
$('ol').style.display=n;
$('mbox').style.display=n;
inf(v);
document.onkeypress=''}
function initmb(){
var ab='absolute';
var n='none';
var obody=document.getElementsByTagName('body')[0];
var frag=document.createDocumentFragment();
var obol=document.createElement('div');
obol.setAttribute('id','ol');
obol.style.display=n;
obol.style.position=ab;
obol.style.top=0;
obol.style.left=0;
obol.style.zIndex=998;
obol.style.width='100%';
frag.appendChild(obol);
var obbx=document.createElement('div');
obbx.setAttribute('id','mbox');
obbx.style.display=n;
obbx.style.position=ab;
obbx.style.zIndex=999;
var obl=document.createElement('span');
obbx.appendChild(obl);
var obbxd=document.createElement('div');
obbxd.setAttribute('id','mbd');
obl.appendChild(obbxd);
frag.insertBefore(obbx,obol.nextSibling);
obody.insertBefore(frag,obody.firstChild);
window.onscroll = scrollFix; 
window.onresize = sizeFix;}

window.onload = initmb;
   */               