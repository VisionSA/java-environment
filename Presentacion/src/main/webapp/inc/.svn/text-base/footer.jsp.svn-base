<footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Versión</b> 2.0
    </div>
    <strong>Copyright &copy; 2018 Tarjeta Fiel</a>.</strong> Derechos Reservados.
</footer>


</div>
<!--./wrapper-->



<!-- jQuery 3 -->
<script src="<%=request.getContextPath()%>/assets/bower_components/jquery/dist/jquery.min.js" type="text/javascript"></script>
<!-- Bootstrap 3.3.7 -->
<script src="<%=request.getContextPath()%>/assets/bower_components/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
<!-- jQuery UI 1.11.4 -->
<script src="<%=request.getContextPath()%>/assets/bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>

<!-- Morris.js charts -->
<script src="<%=request.getContextPath()%>/assets/bower_components/raphael/raphael.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/bower_components/morris.js/morris.min.js"></script>
<!-- Sparkline -->
<script src="<%=request.getContextPath()%>/assets/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="<%=request.getContextPath()%>/assets/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="<%=request.getContextPath()%>/assets/bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="<%=request.getContextPath()%>/assets/bower_components/moment/min/moment.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="<%=request.getContextPath()%>/assets/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="<%=request.getContextPath()%>/assets/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="<%=request.getContextPath()%>/assets/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="<%=request.getContextPath()%>/assets/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath()%>/assets/dist/js/adminlte.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="<%=request.getContextPath()%>/assets/dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=request.getContextPath()%>/assets/dist/js/demo.js"></script>


<script type="text/javascript">

function abrirPopUp(link,nombre,acc){
	var accion = 'acciones:' + acc;
	document.getElementById(accion).click();
	var url = 'http://' + window.location.host + '/Presentacion/' + link;
	var ancho = window.screen.width;
	var alto = window.screen.height;
	window.open(url,nombre,'width='+ancho+',height='+alto); 
	    
	return false;
}


function callLink(link){
    document.getElementById(link).click();
 }

function executeJsfAction(target,action){
	var dummyForm = document.forms[target];
    dummyForm.elements['jscook_action'].value = action;
    dummyForm.submit();
}

function generateMenu(item){
    generateMenu(item,"");
}

function generateMenu(item,act){
var result = ``;
if(item.length>5){
  result += `
  <li class="treeview">
      <a href="#">
          <i class=`;
        result += item[0].split('=')[1];
        result += `</i>
          <span> `;
          result += item[1];
          result += `</span>
          <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
          </span>
       <ul class="treeview-menu">`;
        for(var i = 5; i < item.length; i++) {
            var obj = item[i];
            result += generateSubMenu(obj,act); 
            result += `</li>`;
            if(i == item.length-1){
              result += `</ul>`;
            }
        }
}
    
else{
  result += `
  <li class="treeview">
      <a href="#">
          <i class=`;
        result += item[0].split('=')[1];
        result += `</i>
        <span> `;
        result += item[1];
        result += `</span>
      </a>`;
    }
return result;
}

function generateSubMenu(item,act){
  var result = ``;
  if(item.length>5){
    result += `
    <li class="treeview">
      <a href="#">
      <i class=`;
      result += item[0].split('=')[1];
      result += `</i>
      <span> `;
      result += item[1];
      result += `</span>
      <span class="pull-right-container">
      <i class="fa fa-angle-left pull-right"></i>
      </span>
      </a>
      <ul class="treeview-menu">`;
      for(var i = 5; i < item.length; i++) {
          var obj = item[i];
          result += generateSubMenu(obj,act);
          result += `</li>`;
          if(i == item.length-1){
            result += `</ul>`;
          }
      }
  }
  else{
      if(act==item[2]){
          result += `<li id="activeItem" class="active">`
      }
      else{
          result += `<li>`
      }
      result +=  `<a href="#" onclick="return executeJsfAction('`;
      result += item[3];
      result += `','`;
      result += item[2];
      result += `');">
      <i class=`;
      result += item[0].split('=')[1];
      result += `</i><span>`;
      result += item[1];
      result += `</span></a>`;
  }

  return result;
}

</script>

