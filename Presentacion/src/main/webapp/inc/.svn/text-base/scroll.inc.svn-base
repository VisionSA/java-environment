<%@include file="/inc/tags.jsp" %>

<f:subview id="scrollSubView">
	<x:inputHidden id="hiddenScrollY" value="#{ScrollBean.hiddenScrollY}" forceId="true" immediate="true"/>
	
	<f:verbatim>
		<script language="javascript" type="text/javascript">
			window.onscroll = MB_SaveScroll;
			function MB_SaveScroll() {
				document.getElementById("hiddenScrollY").value = document.body.scrollTop;
			}						
		</script>
	</f:verbatim>
</f:subview>
