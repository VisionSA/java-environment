package org.apache.jsp.tarjetafiel.transacciones.reportes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class generarListadoVerificadores_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(4);
    _jspx_dependants.add("/inc/tags.jsp");
    _jspx_dependants.add("/inc/head.inc");
    _jspx_dependants.add("/WEB-INF/jstl-list.tld");
    _jspx_dependants.add("/WEB-INF/secure.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ff_005fview;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fscript_0026_005flanguage;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fsecure_005fcheck_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005ff_005fview = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fscript_0026_005flanguage = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fsecure_005fcheck_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005ff_005fview.release();
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fscript_0026_005flanguage.release();
    _005fjspx_005ftagPool_005fsecure_005fcheck_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write(" \r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');
      java.util.Date ahora = null;
      synchronized (request) {
        ahora = (java.util.Date) _jspx_page_context.getAttribute("ahora", PageContext.REQUEST_SCOPE);
        if (ahora == null){
          ahora = new java.util.Date();
          _jspx_page_context.setAttribute("ahora", ahora, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("\r\n");
      //  f:view
      org.apache.myfaces.taglib.core.ViewTag _jspx_th_f_005fview_005f0 = (org.apache.myfaces.taglib.core.ViewTag) _005fjspx_005ftagPool_005ff_005fview.get(org.apache.myfaces.taglib.core.ViewTag.class);
      _jspx_th_f_005fview_005f0.setPageContext(_jspx_page_context);
      _jspx_th_f_005fview_005f0.setParent(null);
      int _jspx_eval_f_005fview_005f0 = _jspx_th_f_005fview_005f0.doStartTag();
      if (_jspx_eval_f_005fview_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_f_005fview_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_f_005fview_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_f_005fview_005f0.doInitBody();
        }
        do {
          out.write("\r\n");
          out.write("<html>\r\n");
          out.write("<head>\r\n");
          out.write("    <title>");
          if (_jspx_meth_h_005foutputText_005f0(_jspx_th_f_005fview_005f0, _jspx_page_context))
            return;
          out.write("</title>\r\n");
          out.write("    <script src=\"http://java.com/js/deployJava.js\"></script>\r\n");
          out.write("    ");
          if (_jspx_meth_s_005fscript_005f0(_jspx_th_f_005fview_005f0, _jspx_page_context))
            return;
          out.write("    \r\n");
          out.write("</head>\r\n");
          out.write("\r\n");
          out.write("<head>\r\n");
          out.write("  <title>Tarjeta Fiel </title>\r\n");
          out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"");
          out.print(request.getContextPath());
          out.write("/css/screen.css\" />\r\n");
          out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"");
          out.print(request.getContextPath());
          out.write("/css/basic.css\" />\r\n");
          out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"");
          out.print(request.getContextPath());
          out.write("/css/popup.css\" />\r\n");
          out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"");
          out.print(request.getContextPath());
          out.write("/css/ThemeOffice2003/theme.css\" />\r\n");
          out.write("  <script language=\"javascript\" type=\"text/javascript\" src=\"");
          out.print(request.getContextPath());
          out.write("/jscript/common.js\"></script>\r\n");
          out.write("  <script language=\"javascript\" type=\"text/javascript\" src=\"");
          out.print(request.getContextPath());
          out.write("/jscript/ThemeOffice2003/theme.js\"></script>\r\n");
          out.write("\r\n");
          out.write("</head>\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("<body onbeforeunload=\"ShowWait('generalForm');\" onload=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${GenerarArchivosAfipBean.popupReport}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\">\r\n");
          out.write("<center>\r\n");
          out.write("\r\n");
          if (_jspx_meth_secure_005fcheck_005f0(_jspx_th_f_005fview_005f0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("\r\n");
          out.write("<applet code=\"org.fiel.scanning.class\"\r\n");
          out.write("\theight=\"100%\" width=\"100%\" codebase=\"/WEB-INF/lib/scanning.jar\">\r\n");
          out.write("\t<param name=\"cache_option\" value=\"plugin\"/>\r\n");
          out.write("<!-- \t<param name=\"cache_archive\" value=\"scanning.jar,ij.jar,plugin.jar,twain.jar,itextpdf-5.5.4.jar\" /> -->\r\n");
          out.write("\t<param name=\"cache_version\" value=\"1.0.0.0\"/>\r\n");
          out.write("\t</applet>\r\n");
          out.write("\t\r\n");
          out.write("\t<script>\r\n");
          out.write("    var attributes = {codebase:'http://my.url/my/path/to/codebase',\r\n");
          out.write("                      code:'my.main.Applet.class',\r\n");
          out.write("                      archive: 'scanning.jar\"',\r\n");
          out.write("                      width: '800', \r\n");
          out.write("                      height: '600'};\r\n");
          out.write("    var parameters = {java_arguments: '-Xmx256m'}; // customize per your needs\r\n");
          out.write("    var version = '1.6' ; // JDK version\r\n");
          out.write("    deployJava.runApplet(attributes, parameters, version);\r\n");
          out.write("</script>  \r\n");
          out.write("\r\n");
          out.write('\r');
          out.write('\n');
          out.write("\r\n");
          out.write("<!--             styleClass=\"pageLayout\" -->\r\n");
          out.write("<!--             headerClass=\"pageHeader\" -->\r\n");
          out.write("<!--             navigationClass=\"pageNavigation\" -->\r\n");
          out.write("<!--             bodyClass=\"pageBody\" -->\r\n");
          out.write("<!--             footerClass=\"pageFooter\" > -->\r\n");
          out.write("\r\n");
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write("\r\n");
          out.write("\r\n");
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write("\r\n");
          out.write("            \t\r\n");
          out.write("\r\n");
          out.write("\t\t\tINCLUDE PARA LOS ERRORES \t\r\n");
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write("\r\n");
          out.write("\t\t\t\t\r\n");
          out.write("\t\r\n");
          out.write("\t\t\t\t\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("<!-- \t\t\t\t\t\t\t\t      containerNodeClass=\"contentTitlePane\" labelNodeClass=\"labelTitlePane\" > -->\r\n");
          out.write("\t\t\t\t\t\t\t\t      \r\n");
          out.write("\t\t\t\t\t\t\t\t      \r\n");
          out.write("\t\t\t\t\t\t\t\t      \r\n");
          out.write("\t\t\t\t\t\r\n");
          out.write("\t\t\t\t\t\r\n");
          out.write("\r\n");
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write("\r\n");
          out.write("<!-- \t\t\t\t                styleClass=\"bordeceldainferior\" style=\"width: 90px\"  -->\r\n");
          out.write("<!-- \t\t        \t\t        popupDateFormat=\"dd/MM/yyyy\" helpText=\"DD/MM/YYYY\" forceId=\"true\"/> -->\r\n");
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write("\r\n");
          out.write("<!-- \t\t\t\t                styleClass=\"bordeceldainferior\" style=\"width: 90px\"  -->\r\n");
          out.write("<!-- \t\t        \t\t        popupDateFormat=\"dd/MM/yyyy\" helpText=\"DD/MM/YYYY\" forceId=\"true\"/> -->\r\n");
          out.write('\r');
          out.write('\n');
          out.write("\r\n");
          out.write("\r\n");
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write("\r\n");
          out.write("\t\t\t\t          \t\r\n");
          out.write("\t\t\t\t\t\r\n");
          out.write("\t\t\t\t\t\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t \r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t \r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t \r\n");
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write("\r\n");
          out.write("\t\t\t\t\t\r\n");
          out.write("\r\n");
          out.write("\t\t\t        \r\n");
          out.write('\r');
          out.write('\n');
          out.write('\r');
          out.write('\n');
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write('\r');
          out.write('\n');
          out.write("\r\n");
          out.write("</center>    \r\n");
          out.write("</body>\r\n");
          out.write("</html>\r\n");
          int evalDoAfterBody = _jspx_th_f_005fview_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_f_005fview_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_f_005fview_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005ff_005fview.reuse(_jspx_th_f_005fview_005f0);
        return;
      }
      _005fjspx_005ftagPool_005ff_005fview.reuse(_jspx_th_f_005fview_005f0);
      out.write('\r');
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_h_005foutputText_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005fview_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f0 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f0.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005fview_005f0);
    // /tarjetafiel/transacciones/reportes/generarListadoVerificadores.jsp(7,11) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f0.setValue("#{GenerarArchivosAfipBean.tituloLargo}");
    int _jspx_eval_h_005foutputText_005f0 = _jspx_th_h_005foutputText_005f0.doStartTag();
    if (_jspx_th_h_005foutputText_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fnobody.reuse(_jspx_th_h_005foutputText_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fnobody.reuse(_jspx_th_h_005foutputText_005f0);
    return false;
  }

  private boolean _jspx_meth_s_005fscript_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005fview_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:script
    org.apache.myfaces.custom.script.ScriptTag _jspx_th_s_005fscript_005f0 = (org.apache.myfaces.custom.script.ScriptTag) _005fjspx_005ftagPool_005fs_005fscript_0026_005flanguage.get(org.apache.myfaces.custom.script.ScriptTag.class);
    _jspx_th_s_005fscript_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005fscript_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005fview_005f0);
    // /tarjetafiel/transacciones/reportes/generarListadoVerificadores.jsp(9,4) name = language type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fscript_005f0.setLanguage("javascript");
    int _jspx_eval_s_005fscript_005f0 = _jspx_th_s_005fscript_005f0.doStartTag();
    if (_jspx_eval_s_005fscript_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("    \tfunction recargar() {\r\n");
      out.write("    \t\tdocument.getElementById('generalForm').submit();\r\n");
      out.write("    \t}\r\n");
      out.write("    \tfunction popup(pagina,popW,popH) {\r\n");
      out.write("\t\t\tvar w = 0, h = 0;\r\n");
      out.write("\t\t   \tw = screen.width;\r\n");
      out.write("\t\t   \th = screen.height;\r\n");
      out.write("\r\n");
      out.write("\t\t\tvar leftPos = (w-popW)/2, topPos = (h-popH)/2;\r\n");
      out.write("\r\n");
      out.write("\t\t    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);\r\n");
      out.write("\t\t    if (popupWindow.opener == null){popupWindow.opener = self;}\r\n");
      out.write("\t\t};\r\n");
      out.write("    ");
    }
    if (_jspx_th_s_005fscript_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005fscript_0026_005flanguage.reuse(_jspx_th_s_005fscript_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005fscript_0026_005flanguage.reuse(_jspx_th_s_005fscript_005f0);
    return false;
  }

  private boolean _jspx_meth_secure_005fcheck_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005fview_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  secure:check
    com.bizitglobal.webapp.jstl.SecureTag _jspx_th_secure_005fcheck_005f0 = (com.bizitglobal.webapp.jstl.SecureTag) _005fjspx_005ftagPool_005fsecure_005fcheck_005fnobody.get(com.bizitglobal.webapp.jstl.SecureTag.class);
    _jspx_th_secure_005fcheck_005f0.setPageContext(_jspx_page_context);
    _jspx_th_secure_005fcheck_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005fview_005f0);
    int _jspx_eval_secure_005fcheck_005f0 = _jspx_th_secure_005fcheck_005f0.doStartTag();
    if (_jspx_th_secure_005fcheck_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fsecure_005fcheck_005fnobody.reuse(_jspx_th_secure_005fcheck_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fsecure_005fcheck_005fnobody.reuse(_jspx_th_secure_005fcheck_005f0);
    return false;
  }
}
