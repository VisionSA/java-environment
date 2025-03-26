package org.apache.jsp.inc;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class navigation_005ftest_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(3);
    _jspx_dependants.add("/inc/tags.jsp");
    _jspx_dependants.add("/WEB-INF/jstl-list.tld");
    _jspx_dependants.add("/WEB-INF/secure.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fjscookMenu_0026_005ftheme_005flayout_005fimmediate_005fimageLocation;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fnavigationMenuItems_0026_005fvalue_005fid_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fx_005fjscookMenu_0026_005ftheme_005flayout_005fimmediate_005fimageLocation = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fnavigationMenuItems_0026_005fvalue_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fx_005fjscookMenu_0026_005ftheme_005flayout_005fimmediate_005fimageLocation.release();
    _005fjspx_005ftagPool_005fx_005fnavigationMenuItems_0026_005fvalue_005fid_005fnobody.release();
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
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_x_005fjscookMenu_005f0(_jspx_page_context))
        return;
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

  private boolean _jspx_meth_x_005fjscookMenu_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:jscookMenu
    org.apache.myfaces.custom.navmenu.jscookmenu.HtmlJSCookMenuTag _jspx_th_x_005fjscookMenu_005f0 = (org.apache.myfaces.custom.navmenu.jscookmenu.HtmlJSCookMenuTag) _005fjspx_005ftagPool_005fx_005fjscookMenu_0026_005ftheme_005flayout_005fimmediate_005fimageLocation.get(org.apache.myfaces.custom.navmenu.jscookmenu.HtmlJSCookMenuTag.class);
    _jspx_th_x_005fjscookMenu_005f0.setPageContext(_jspx_page_context);
    _jspx_th_x_005fjscookMenu_005f0.setParent(null);
    // /inc/navigation_test.jsp(3,0) name = layout type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fjscookMenu_005f0.setLayout("hbr");
    // /inc/navigation_test.jsp(3,0) name = theme type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fjscookMenu_005f0.setTheme("ThemeOffice2003");
    // /inc/navigation_test.jsp(3,0) name = imageLocation type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fjscookMenu_005f0.setImageLocation("/css/ThemeOffice2003");
    // /inc/navigation_test.jsp(3,0) name = immediate type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fjscookMenu_005f0.setImmediate("true");
    int _jspx_eval_x_005fjscookMenu_005f0 = _jspx_th_x_005fjscookMenu_005f0.doStartTag();
    if (_jspx_eval_x_005fjscookMenu_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write('\r');
      out.write('\n');
      out.write('	');
      if (_jspx_meth_x_005fnavigationMenuItems_005f0(_jspx_th_x_005fjscookMenu_005f0, _jspx_page_context))
        return true;
      out.write('\r');
      out.write('\n');
    }
    if (_jspx_th_x_005fjscookMenu_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fjscookMenu_0026_005ftheme_005flayout_005fimmediate_005fimageLocation.reuse(_jspx_th_x_005fjscookMenu_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fjscookMenu_0026_005ftheme_005flayout_005fimmediate_005fimageLocation.reuse(_jspx_th_x_005fjscookMenu_005f0);
    return false;
  }

  private boolean _jspx_meth_x_005fnavigationMenuItems_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fjscookMenu_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:navigationMenuItems
    org.apache.myfaces.custom.navmenu.HtmlNavigationMenuItemsTag _jspx_th_x_005fnavigationMenuItems_005f0 = (org.apache.myfaces.custom.navmenu.HtmlNavigationMenuItemsTag) _005fjspx_005ftagPool_005fx_005fnavigationMenuItems_0026_005fvalue_005fid_005fnobody.get(org.apache.myfaces.custom.navmenu.HtmlNavigationMenuItemsTag.class);
    _jspx_th_x_005fnavigationMenuItems_005f0.setPageContext(_jspx_page_context);
    _jspx_th_x_005fnavigationMenuItems_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fjscookMenu_005f0);
    // /inc/navigation_test.jsp(4,1) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fnavigationMenuItems_005f0.setId("list_menu");
    // /inc/navigation_test.jsp(4,1) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fnavigationMenuItems_005f0.setValue("#{MenuBean.menuSegunRol}");
    int _jspx_eval_x_005fnavigationMenuItems_005f0 = _jspx_th_x_005fnavigationMenuItems_005f0.doStartTag();
    if (_jspx_th_x_005fnavigationMenuItems_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fnavigationMenuItems_0026_005fvalue_005fid_005fnobody.reuse(_jspx_th_x_005fnavigationMenuItems_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fnavigationMenuItems_0026_005fvalue_005fid_005fnobody.reuse(_jspx_th_x_005fnavigationMenuItems_005f0);
    return false;
  }
}
