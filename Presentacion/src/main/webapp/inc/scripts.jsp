<script type="text/javascript">


//marco item activo en el menú
var x = document.getElementById("activeItem");
while(x.id!="sideMenu"){
  	x = x.parentNode;
  	if(x.nodeName==="LI"){
  		x.classList.add("menu-open");
  		x.classList.add("active");
  	}
}

//Agrego estilo a elementos antiguos
//$(".botones").addClass("btn btn-primary btn-flat");
$(".tdB").addClass("text-right");
$(".report").addClass("table-striped table-bordered");
$(".standardTable").addClass("table-bordered");
$(".dinero").each(function() { $(this).html(parseFloat($(this).text()).toLocaleString("es-AR", { minimumFractionDigits: 2 })); });

//var jq = jQuery.noConflict();

</script>