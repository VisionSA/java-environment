package org.apache.jsp.workflow.escritorios;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class escritorio_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(6);
    _jspx_dependants.add("/inc/tags.jsp");
    _jspx_dependants.add("/inc/head.inc");
    _jspx_dependants.add("/inc/scroll.inc");
    _jspx_dependants.add("/inc/page_footer.jsp");
    _jspx_dependants.add("/WEB-INF/jstl-list.tld");
    _jspx_dependants.add("/WEB-INF/secure.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ff_005fview;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005fscript_0026_005flanguage;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005fform_0026_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005frendered;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ff_005fsubview_0026_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005finputHidden_0026_005fvalue_005fimmediate_005fid_005fforceId_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ff_005fverbatim;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fpanelLayout_0026_005fstyleClass_005fnavigationClass_005flayout_005fid_005fheaderClass_005ffooterClass_005fbodyClass;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns_005falign;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fcolumns_005falign;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fstyleClass_005fid_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fpanelTabbedPane_0026_005fserverSideTabSwitch_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns_005fcolumnClasses_005falign;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005finputCalendar_0026_005fweekRowClass_005fvalue_005frendered_005fmonthYearRowClass_005fcurrentDayCellClass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005fpanelGroup;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fs_005flayoutingTitlePane_0026_005flabelNodeClass_005flabel_005fid_005fcontainerNodeClass;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fwidth_005fvar_005fvalue_005fstyle_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005fcolumn;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fpanelTabbedPane_0026_005fserverSideTabSwitch_005fselectedIndex_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fcolumn;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005frendered_005fonclick_005fborder_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid_005fdisabled;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005ff_005fview = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005fscript_0026_005flanguage = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005fform_0026_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005frendered = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ff_005fsubview_0026_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005finputHidden_0026_005fvalue_005fimmediate_005fid_005fforceId_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ff_005fverbatim = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fpanelLayout_0026_005fstyleClass_005fnavigationClass_005flayout_005fid_005fheaderClass_005ffooterClass_005fbodyClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns_005falign = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fcolumns_005falign = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fstyleClass_005fid_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fpanelTabbedPane_0026_005fserverSideTabSwitch_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns_005fcolumnClasses_005falign = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005finputCalendar_0026_005fweekRowClass_005fvalue_005frendered_005fmonthYearRowClass_005fcurrentDayCellClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005fpanelGroup = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fs_005flayoutingTitlePane_0026_005flabelNodeClass_005flabel_005fid_005fcontainerNodeClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fwidth_005fvar_005fvalue_005fstyle_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005fcolumn = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fpanelTabbedPane_0026_005fserverSideTabSwitch_005fselectedIndex_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fcolumn = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005frendered_005fonclick_005fborder_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid_005fdisabled = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005ff_005fview.release();
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fs_005fscript_0026_005flanguage.release();
    _005fjspx_005ftagPool_005fh_005fform_0026_005fid.release();
    _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005frendered.release();
    _005fjspx_005ftagPool_005ff_005fsubview_0026_005fid.release();
    _005fjspx_005ftagPool_005fx_005finputHidden_0026_005fvalue_005fimmediate_005fid_005fforceId_005fnobody.release();
    _005fjspx_005ftagPool_005ff_005fverbatim.release();
    _005fjspx_005ftagPool_005fx_005fpanelLayout_0026_005fstyleClass_005fnavigationClass_005flayout_005fid_005fheaderClass_005ffooterClass_005fbodyClass.release();
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.release();
    _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005fid.release();
    _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns_005falign.release();
    _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fcolumns_005falign.release();
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fstyleClass_005fid_005faction.release();
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.release();
    _005fjspx_005ftagPool_005fx_005fpanelTabbedPane_0026_005fserverSideTabSwitch_005fid.release();
    _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid.release();
    _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns_005fcolumnClasses_005falign.release();
    _005fjspx_005ftagPool_005fx_005finputCalendar_0026_005fweekRowClass_005fvalue_005frendered_005fmonthYearRowClass_005fcurrentDayCellClass_005fnobody.release();
    _005fjspx_005ftagPool_005fh_005fpanelGroup.release();
    _005fjspx_005ftagPool_005fs_005flayoutingTitlePane_0026_005flabelNodeClass_005flabel_005fid_005fcontainerNodeClass.release();
    _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fwidth_005fvar_005fvalue_005fstyle_005fid.release();
    _005fjspx_005ftagPool_005fh_005fcolumn.release();
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyle_005fnobody.release();
    _005fjspx_005ftagPool_005fx_005fpanelTabbedPane_0026_005fserverSideTabSwitch_005fselectedIndex_005fid.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns.release();
    _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle.release();
    _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses.release();
    _005fjspx_005ftagPool_005fx_005fcolumn.release();
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.release();
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.release();
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.release();
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.release();
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.release();
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.release();
    _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle_005fid.release();
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005frendered_005fonclick_005fborder_005fnobody.release();
    _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid_005fdisabled.release();
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
          out.write("   \t<title>");
          if (_jspx_meth_h_005foutputText_005f0(_jspx_th_f_005fview_005f0, _jspx_page_context))
            return;
          out.write("</title>\r\n");
          out.write("    ");
          if (_jspx_meth_s_005fscript_005f0(_jspx_th_f_005fview_005f0, _jspx_page_context))
            return;
          out.write("\r\n");
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
          out.write("<body onbeforeunload=\"ShowWait('escritorioForm');\" onload=\"if('");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${EscritorioBean.popup.mostrar}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("'=='true') {viewDialog.show();} else {window.scrollTo(0,");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ScrollBean.hiddenScrollY}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write(");}\">\r\n");
          out.write("<center>\r\n");
          if (_jspx_meth_h_005fform_005f0(_jspx_th_f_005fview_005f0, _jspx_page_context))
            return;
          out.write("    \r\n");
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
    // /workflow/escritorios/escritorio.jsp(7,11) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f0.setValue("#{EscritorioBean.tituloLargo}");
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
    // /workflow/escritorios/escritorio.jsp(8,4) name = language type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005fscript_005f0.setLanguage("javascript");
    int _jspx_eval_s_005fscript_005f0 = _jspx_th_s_005fscript_005f0.doStartTag();
    if (_jspx_eval_s_005fscript_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("    \tfunction recargar() {\r\n");
      out.write("    \t\tdocument.getElementById('escritorioForm').submit();\r\n");
      out.write("    \t};\r\n");
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

  private boolean _jspx_meth_h_005fform_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005fview_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  h:form
    org.apache.myfaces.taglib.html.HtmlFormTag _jspx_th_h_005fform_005f0 = (org.apache.myfaces.taglib.html.HtmlFormTag) _005fjspx_005ftagPool_005fh_005fform_0026_005fid.get(org.apache.myfaces.taglib.html.HtmlFormTag.class);
    _jspx_th_h_005fform_005f0.setPageContext(_jspx_page_context);
    _jspx_th_h_005fform_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005fview_005f0);
    // /workflow/escritorios/escritorio.jsp(29,0) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fform_005f0.setId("escritorioForm");
    int _jspx_eval_h_005fform_005f0 = _jspx_th_h_005fform_005f0.doStartTag();
    if (_jspx_eval_h_005fform_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t");
      out.write(" \r\n");
      out.write("\t");
      if (_jspx_meth_h_005fpanelGroup_005f0(_jspx_th_h_005fform_005f0, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("    ");
      if (_jspx_meth_x_005fpanelLayout_005f0(_jspx_th_h_005fform_005f0, _jspx_page_context))
        return true;
      out.write('\r');
      out.write('\n');
    }
    if (_jspx_th_h_005fform_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005fform_0026_005fid.reuse(_jspx_th_h_005fform_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005fform_0026_005fid.reuse(_jspx_th_h_005fform_005f0);
    return false;
  }

  private boolean _jspx_meth_h_005fpanelGroup_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:panelGroup
    org.apache.myfaces.taglib.html.HtmlPanelGroupTag _jspx_th_h_005fpanelGroup_005f0 = (org.apache.myfaces.taglib.html.HtmlPanelGroupTag) _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005frendered.get(org.apache.myfaces.taglib.html.HtmlPanelGroupTag.class);
    _jspx_th_h_005fpanelGroup_005f0.setPageContext(_jspx_page_context);
    _jspx_th_h_005fpanelGroup_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fform_005f0);
    // /workflow/escritorios/escritorio.jsp(32,1) name = rendered type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGroup_005f0.setRendered("#{!EscritorioBean.popup.mostrar}");
    int _jspx_eval_h_005fpanelGroup_005f0 = _jspx_th_h_005fpanelGroup_005f0.doStartTag();
    if (_jspx_eval_h_005fpanelGroup_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_005fpanelGroup_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_005fpanelGroup_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_005fpanelGroup_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t");
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
        if (_jspx_meth_f_005fsubview_005f0(_jspx_th_h_005fpanelGroup_005f0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        out.write("   \t      \r\n");
        out.write("\t");
        int evalDoAfterBody = _jspx_th_h_005fpanelGroup_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_005fpanelGroup_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_h_005fpanelGroup_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005frendered.reuse(_jspx_th_h_005fpanelGroup_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005frendered.reuse(_jspx_th_h_005fpanelGroup_005f0);
    return false;
  }

  private boolean _jspx_meth_f_005fsubview_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:subview
    org.apache.myfaces.taglib.core.SubviewTag _jspx_th_f_005fsubview_005f0 = (org.apache.myfaces.taglib.core.SubviewTag) _005fjspx_005ftagPool_005ff_005fsubview_0026_005fid.get(org.apache.myfaces.taglib.core.SubviewTag.class);
    _jspx_th_f_005fsubview_005f0.setPageContext(_jspx_page_context);
    _jspx_th_f_005fsubview_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f0);
    // /inc/scroll.inc(3,0) name = id type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fsubview_005f0.setId("scrollSubView");
    int _jspx_eval_f_005fsubview_005f0 = _jspx_th_f_005fsubview_005f0.doStartTag();
    if (_jspx_eval_f_005fsubview_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write('\r');
      out.write('\n');
      out.write('	');
      if (_jspx_meth_x_005finputHidden_005f0(_jspx_th_f_005fsubview_005f0, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t");
      if (_jspx_meth_f_005fverbatim_005f0(_jspx_th_f_005fsubview_005f0, _jspx_page_context))
        return true;
      out.write('\r');
      out.write('\n');
    }
    if (_jspx_th_f_005fsubview_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fsubview_0026_005fid.reuse(_jspx_th_f_005fsubview_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fsubview_0026_005fid.reuse(_jspx_th_f_005fsubview_005f0);
    return false;
  }

  private boolean _jspx_meth_x_005finputHidden_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005fsubview_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:inputHidden
    org.apache.myfaces.taglib.html.ext.HtmlInputHiddenTag _jspx_th_x_005finputHidden_005f0 = (org.apache.myfaces.taglib.html.ext.HtmlInputHiddenTag) _005fjspx_005ftagPool_005fx_005finputHidden_0026_005fvalue_005fimmediate_005fid_005fforceId_005fnobody.get(org.apache.myfaces.taglib.html.ext.HtmlInputHiddenTag.class);
    _jspx_th_x_005finputHidden_005f0.setPageContext(_jspx_page_context);
    _jspx_th_x_005finputHidden_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005fsubview_005f0);
    // /inc/scroll.inc(4,1) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005finputHidden_005f0.setId("hiddenScrollY");
    // /inc/scroll.inc(4,1) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005finputHidden_005f0.setValue("#{ScrollBean.hiddenScrollY}");
    // /inc/scroll.inc(4,1) name = forceId type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005finputHidden_005f0.setForceId("true");
    // /inc/scroll.inc(4,1) name = immediate type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005finputHidden_005f0.setImmediate("true");
    int _jspx_eval_x_005finputHidden_005f0 = _jspx_th_x_005finputHidden_005f0.doStartTag();
    if (_jspx_th_x_005finputHidden_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005finputHidden_0026_005fvalue_005fimmediate_005fid_005fforceId_005fnobody.reuse(_jspx_th_x_005finputHidden_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005finputHidden_0026_005fvalue_005fimmediate_005fid_005fforceId_005fnobody.reuse(_jspx_th_x_005finputHidden_005f0);
    return false;
  }

  private boolean _jspx_meth_f_005fverbatim_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005fsubview_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:verbatim
    org.apache.myfaces.taglib.core.VerbatimTag _jspx_th_f_005fverbatim_005f0 = (org.apache.myfaces.taglib.core.VerbatimTag) _005fjspx_005ftagPool_005ff_005fverbatim.get(org.apache.myfaces.taglib.core.VerbatimTag.class);
    _jspx_th_f_005fverbatim_005f0.setPageContext(_jspx_page_context);
    _jspx_th_f_005fverbatim_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005fsubview_005f0);
    int _jspx_eval_f_005fverbatim_005f0 = _jspx_th_f_005fverbatim_005f0.doStartTag();
    if (_jspx_eval_f_005fverbatim_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_f_005fverbatim_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_f_005fverbatim_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_f_005fverbatim_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t<script language=\"javascript\" type=\"text/javascript\">\r\n");
        out.write("\t\t\twindow.onscroll = MB_SaveScroll;\r\n");
        out.write("\t\t\tfunction MB_SaveScroll() {\r\n");
        out.write("\t\t\t\tdocument.getElementById(\"hiddenScrollY\").value = document.body.scrollTop;\r\n");
        out.write("\t\t\t}\t\t\t\t\t\t\r\n");
        out.write("\t\t</script>\r\n");
        out.write("\t");
        int evalDoAfterBody = _jspx_th_f_005fverbatim_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_f_005fverbatim_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_f_005fverbatim_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f0);
    return false;
  }

  private boolean _jspx_meth_x_005fpanelLayout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  x:panelLayout
    org.apache.myfaces.custom.layout.HtmlPanelLayoutTag _jspx_th_x_005fpanelLayout_005f0 = (org.apache.myfaces.custom.layout.HtmlPanelLayoutTag) _005fjspx_005ftagPool_005fx_005fpanelLayout_0026_005fstyleClass_005fnavigationClass_005flayout_005fid_005fheaderClass_005ffooterClass_005fbodyClass.get(org.apache.myfaces.custom.layout.HtmlPanelLayoutTag.class);
    _jspx_th_x_005fpanelLayout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_x_005fpanelLayout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fform_005f0);
    // /workflow/escritorios/escritorio.jsp(36,4) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelLayout_005f0.setId("page");
    // /workflow/escritorios/escritorio.jsp(36,4) name = layout type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelLayout_005f0.setLayout("#{globalOptions.pageLayout}");
    // /workflow/escritorios/escritorio.jsp(36,4) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelLayout_005f0.setStyleClass("pageLayout");
    // /workflow/escritorios/escritorio.jsp(36,4) name = headerClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelLayout_005f0.setHeaderClass("pageHeader");
    // /workflow/escritorios/escritorio.jsp(36,4) name = navigationClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelLayout_005f0.setNavigationClass("pageNavigation");
    // /workflow/escritorios/escritorio.jsp(36,4) name = bodyClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelLayout_005f0.setBodyClass("pageBody");
    // /workflow/escritorios/escritorio.jsp(36,4) name = footerClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelLayout_005f0.setFooterClass("pageFooter");
    int _jspx_eval_x_005fpanelLayout_005f0 = _jspx_th_x_005fpanelLayout_005f0.doStartTag();
    if (_jspx_eval_x_005fpanelLayout_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fpanelLayout_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fpanelLayout_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fpanelLayout_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\r\n");
        out.write("        ");
        if (_jspx_meth_f_005ffacet_005f0(_jspx_th_x_005fpanelLayout_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\r\n");
        out.write("        ");
        if (_jspx_meth_f_005ffacet_005f1(_jspx_th_x_005fpanelLayout_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\r\n");
        out.write("        ");
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
        if (_jspx_meth_f_005ffacet_005f27(_jspx_th_x_005fpanelLayout_005f0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        out.write("\r\n");
        out.write("\r\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_x_005fpanelLayout_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fpanelLayout_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fpanelLayout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fpanelLayout_0026_005fstyleClass_005fnavigationClass_005flayout_005fid_005fheaderClass_005ffooterClass_005fbodyClass.reuse(_jspx_th_x_005fpanelLayout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fpanelLayout_0026_005fstyleClass_005fnavigationClass_005flayout_005fid_005fheaderClass_005ffooterClass_005fbodyClass.reuse(_jspx_th_x_005fpanelLayout_005f0);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fpanelLayout_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f0 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f0.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fpanelLayout_005f0);
    // /workflow/escritorios/escritorio.jsp(43,8) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f0.setName("header");
    int _jspx_eval_f_005ffacet_005f0 = _jspx_th_f_005ffacet_005f0.doStartTag();
    if (_jspx_eval_f_005ffacet_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("            ");
        if (_jspx_meth_f_005fsubview_005f1(_jspx_th_f_005ffacet_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f0);
    return false;
  }

  private boolean _jspx_meth_f_005fsubview_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  f:subview
    org.apache.myfaces.taglib.core.SubviewTag _jspx_th_f_005fsubview_005f1 = (org.apache.myfaces.taglib.core.SubviewTag) _005fjspx_005ftagPool_005ff_005fsubview_0026_005fid.get(org.apache.myfaces.taglib.core.SubviewTag.class);
    _jspx_th_f_005fsubview_005f1.setPageContext(_jspx_page_context);
    _jspx_th_f_005fsubview_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f0);
    // /workflow/escritorios/escritorio.jsp(44,12) name = id type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fsubview_005f1.setId("header");
    int _jspx_eval_f_005fsubview_005f1 = _jspx_th_f_005fsubview_005f1.doStartTag();
    if (_jspx_eval_f_005fsubview_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/inc/page_header.jsp", out, false);
      out.write("\r\n");
      out.write("   \t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/inc/navigation_test.jsp", out, false);
      out.write("                \r\n");
      out.write("            ");
    }
    if (_jspx_th_f_005fsubview_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fsubview_0026_005fid.reuse(_jspx_th_f_005fsubview_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fsubview_0026_005fid.reuse(_jspx_th_f_005fsubview_005f1);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fpanelLayout_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f1 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f1.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fpanelLayout_005f0);
    // /workflow/escritorios/escritorio.jsp(50,8) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f1.setName("body");
    int _jspx_eval_f_005ffacet_005f1 = _jspx_th_f_005ffacet_005f1.doStartTag();
    if (_jspx_eval_f_005ffacet_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("            ");
        if (_jspx_meth_h_005fpanelGroup_005f1(_jspx_th_f_005ffacet_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f1);
    return false;
  }

  private boolean _jspx_meth_h_005fpanelGroup_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  h:panelGroup
    org.apache.myfaces.taglib.html.HtmlPanelGroupTag _jspx_th_h_005fpanelGroup_005f1 = (org.apache.myfaces.taglib.html.HtmlPanelGroupTag) _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005fid.get(org.apache.myfaces.taglib.html.HtmlPanelGroupTag.class);
    _jspx_th_h_005fpanelGroup_005f1.setPageContext(_jspx_page_context);
    _jspx_th_h_005fpanelGroup_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f1);
    // /workflow/escritorios/escritorio.jsp(51,12) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGroup_005f1.setId("body");
    int _jspx_eval_h_005fpanelGroup_005f1 = _jspx_th_h_005fpanelGroup_005f1.doStartTag();
    if (_jspx_eval_h_005fpanelGroup_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_005fpanelGroup_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_005fpanelGroup_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_005fpanelGroup_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("            \t");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/inc/title_header.jsp" + (("/inc/title_header.jsp").indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("tituloLargo", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${EscritorioBean.tituloLargo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("tituloCorto", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${EscritorioBean.tituloCorto}", java.lang.String.class, (PageContext)_jspx_page_context, null, false), request.getCharacterEncoding()), out, false);
        out.write("\r\n");
        out.write("            \t\r\n");
        out.write("            \t");
        if (_jspx_meth_h_005fpanelGrid_005f0(_jspx_th_h_005fpanelGroup_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("           ");
        int evalDoAfterBody = _jspx_th_h_005fpanelGroup_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_005fpanelGroup_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_h_005fpanelGroup_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005fid.reuse(_jspx_th_h_005fpanelGroup_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005fid.reuse(_jspx_th_h_005fpanelGroup_005f1);
    return false;
  }

  private boolean _jspx_meth_h_005fpanelGrid_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  h:panelGrid
    org.apache.myfaces.taglib.html.HtmlPanelGridTag _jspx_th_h_005fpanelGrid_005f0 = (org.apache.myfaces.taglib.html.HtmlPanelGridTag) _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns_005falign.get(org.apache.myfaces.taglib.html.HtmlPanelGridTag.class);
    _jspx_th_h_005fpanelGrid_005f0.setPageContext(_jspx_page_context);
    _jspx_th_h_005fpanelGrid_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f1);
    // /workflow/escritorios/escritorio.jsp(57,13) name = columns type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGrid_005f0.setColumns("1");
    // /workflow/escritorios/escritorio.jsp(57,13) name = align type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGrid_005f0.setAlign("center");
    // /workflow/escritorios/escritorio.jsp(57,13) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGrid_005f0.setId("PanelPricipalEscritorio");
    int _jspx_eval_h_005fpanelGrid_005f0 = _jspx_th_h_005fpanelGrid_005f0.doStartTag();
    if (_jspx_eval_h_005fpanelGrid_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_005fpanelGrid_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_005fpanelGrid_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_005fpanelGrid_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        out.write(" \t\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_h_005fpanelGroup_005f2(_jspx_th_h_005fpanelGrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\r\n");
        out.write("\t\t");
        if (_jspx_meth_h_005fpanelGrid_005f1(_jspx_th_h_005fpanelGrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t");
        if (_jspx_meth_x_005fpanelTabbedPane_005f0(_jspx_th_h_005fpanelGrid_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_h_005fpanelGrid_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_005fpanelGrid_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_h_005fpanelGrid_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns_005falign.reuse(_jspx_th_h_005fpanelGrid_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns_005falign.reuse(_jspx_th_h_005fpanelGrid_005f0);
    return false;
  }

  private boolean _jspx_meth_h_005fpanelGroup_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGrid_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    HttpServletRequest request = (HttpServletRequest)_jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse)_jspx_page_context.getResponse();
    //  h:panelGroup
    org.apache.myfaces.taglib.html.HtmlPanelGroupTag _jspx_th_h_005fpanelGroup_005f2 = (org.apache.myfaces.taglib.html.HtmlPanelGroupTag) _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005fid.get(org.apache.myfaces.taglib.html.HtmlPanelGroupTag.class);
    _jspx_th_h_005fpanelGroup_005f2.setPageContext(_jspx_page_context);
    _jspx_th_h_005fpanelGroup_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGrid_005f0);
    // /workflow/escritorios/escritorio.jsp(59,4) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGroup_005f2.setId("errores");
    int _jspx_eval_h_005fpanelGroup_005f2 = _jspx_th_h_005fpanelGroup_005f2.doStartTag();
    if (_jspx_eval_h_005fpanelGroup_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_005fpanelGroup_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_005fpanelGroup_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_005fpanelGroup_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/inc/error.jsp", out, false);
        out.write("\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_h_005fpanelGroup_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_005fpanelGroup_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_h_005fpanelGroup_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005fid.reuse(_jspx_th_h_005fpanelGroup_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005fpanelGroup_0026_005fid.reuse(_jspx_th_h_005fpanelGroup_005f2);
    return false;
  }

  private boolean _jspx_meth_h_005fpanelGrid_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGrid_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:panelGrid
    org.apache.myfaces.taglib.html.HtmlPanelGridTag _jspx_th_h_005fpanelGrid_005f1 = (org.apache.myfaces.taglib.html.HtmlPanelGridTag) _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fcolumns_005falign.get(org.apache.myfaces.taglib.html.HtmlPanelGridTag.class);
    _jspx_th_h_005fpanelGrid_005f1.setPageContext(_jspx_page_context);
    _jspx_th_h_005fpanelGrid_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGrid_005f0);
    // /workflow/escritorios/escritorio.jsp(63,2) name = columns type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGrid_005f1.setColumns("1");
    // /workflow/escritorios/escritorio.jsp(63,2) name = align type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGrid_005f1.setAlign("right");
    int _jspx_eval_h_005fpanelGrid_005f1 = _jspx_th_h_005fpanelGrid_005f1.doStartTag();
    if (_jspx_eval_h_005fpanelGrid_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_005fpanelGrid_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_005fpanelGrid_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_005fpanelGrid_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t");
        if (_jspx_meth_x_005fcommandLink_005f0(_jspx_th_h_005fpanelGrid_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t");
        int evalDoAfterBody = _jspx_th_h_005fpanelGrid_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_005fpanelGrid_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_h_005fpanelGrid_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fcolumns_005falign.reuse(_jspx_th_h_005fpanelGrid_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fcolumns_005falign.reuse(_jspx_th_h_005fpanelGrid_005f1);
    return false;
  }

  private boolean _jspx_meth_x_005fcommandLink_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGrid_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:commandLink
    org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag _jspx_th_x_005fcommandLink_005f0 = (org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag) _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fstyleClass_005fid_005faction.get(org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag.class);
    _jspx_th_x_005fcommandLink_005f0.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcommandLink_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGrid_005f1);
    // /workflow/escritorios/escritorio.jsp(64,3) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f0.setId("backLink");
    // /workflow/escritorios/escritorio.jsp(64,3) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f0.setAction("#{EscritorioBean.volver}");
    // /workflow/escritorios/escritorio.jsp(64,3) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f0.setStyleClass("align:right");
    int _jspx_eval_x_005fcommandLink_005f0 = _jspx_th_x_005fcommandLink_005f0.doStartTag();
    if (_jspx_eval_x_005fcommandLink_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_x_005fgraphicImage_005f0(_jspx_th_x_005fcommandLink_005f0, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t");
    }
    if (_jspx_th_x_005fcommandLink_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fstyleClass_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fstyleClass_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f0);
    return false;
  }

  private boolean _jspx_meth_x_005fgraphicImage_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:graphicImage
    org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag _jspx_th_x_005fgraphicImage_005f0 = (org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag) _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.get(org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag.class);
    _jspx_th_x_005fgraphicImage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_x_005fgraphicImage_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f0);
    // /workflow/escritorios/escritorio.jsp(65,4) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f0.setValue("/img/icon/reload_alt32.png");
    // /workflow/escritorios/escritorio.jsp(65,4) name = title type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f0.setTitle("Refrescar escritorio");
    // /workflow/escritorios/escritorio.jsp(65,4) name = border type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f0.setBorder("0");
    int _jspx_eval_x_005fgraphicImage_005f0 = _jspx_th_x_005fgraphicImage_005f0.doStartTag();
    if (_jspx_th_x_005fgraphicImage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f0);
    return false;
  }

  private boolean _jspx_meth_x_005fpanelTabbedPane_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGrid_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:panelTabbedPane
    org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPaneTag _jspx_th_x_005fpanelTabbedPane_005f0 = (org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPaneTag) _005fjspx_005ftagPool_005fx_005fpanelTabbedPane_0026_005fserverSideTabSwitch_005fid.get(org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPaneTag.class);
    _jspx_th_x_005fpanelTabbedPane_005f0.setPageContext(_jspx_page_context);
    _jspx_th_x_005fpanelTabbedPane_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGrid_005f0);
    // /workflow/escritorios/escritorio.jsp(68,2) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTabbedPane_005f0.setId("tabbedPrincipal");
    // /workflow/escritorios/escritorio.jsp(68,2) name = serverSideTabSwitch type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTabbedPane_005f0.setServerSideTabSwitch("false");
    int _jspx_eval_x_005fpanelTabbedPane_005f0 = _jspx_th_x_005fpanelTabbedPane_005f0.doStartTag();
    if (_jspx_eval_x_005fpanelTabbedPane_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fpanelTabbedPane_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fpanelTabbedPane_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fpanelTabbedPane_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t        ");
        if (_jspx_meth_x_005fpanelTab_005f0(_jspx_th_x_005fpanelTabbedPane_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\n");
        out.write("\t\t\t");
        if (_jspx_meth_x_005fpanelTab_005f4(_jspx_th_x_005fpanelTabbedPane_005f0, _jspx_page_context))
          return true;
        out.write('\n');
        out.write('	');
        out.write('	');
        int evalDoAfterBody = _jspx_th_x_005fpanelTabbedPane_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fpanelTabbedPane_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fpanelTabbedPane_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fpanelTabbedPane_0026_005fserverSideTabSwitch_005fid.reuse(_jspx_th_x_005fpanelTabbedPane_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fpanelTabbedPane_0026_005fserverSideTabSwitch_005fid.reuse(_jspx_th_x_005fpanelTabbedPane_005f0);
    return false;
  }

  private boolean _jspx_meth_x_005fpanelTab_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fpanelTabbedPane_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:panelTab
    org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag _jspx_th_x_005fpanelTab_005f0 = (org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag) _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid.get(org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag.class);
    _jspx_th_x_005fpanelTab_005f0.setPageContext(_jspx_page_context);
    _jspx_th_x_005fpanelTab_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fpanelTabbedPane_005f0);
    // /workflow/escritorios/escritorio.jsp(69,9) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTab_005f0.setId("tab1");
    // /workflow/escritorios/escritorio.jsp(69,9) name = label type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTab_005f0.setLabel("Escritorio");
    int _jspx_eval_x_005fpanelTab_005f0 = _jspx_th_x_005fpanelTab_005f0.doStartTag();
    if (_jspx_eval_x_005fpanelTab_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fpanelTab_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fpanelTab_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fpanelTab_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("             \t\t");
        if (_jspx_meth_h_005fpanelGrid_005f2(_jspx_th_x_005fpanelTab_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_x_005fpanelTabbedPane_005f1(_jspx_th_x_005fpanelTab_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fpanelTab_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fpanelTab_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fpanelTab_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid.reuse(_jspx_th_x_005fpanelTab_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid.reuse(_jspx_th_x_005fpanelTab_005f0);
    return false;
  }

  private boolean _jspx_meth_h_005fpanelGrid_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fpanelTab_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:panelGrid
    org.apache.myfaces.taglib.html.HtmlPanelGridTag _jspx_th_h_005fpanelGrid_005f2 = (org.apache.myfaces.taglib.html.HtmlPanelGridTag) _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns_005fcolumnClasses_005falign.get(org.apache.myfaces.taglib.html.HtmlPanelGridTag.class);
    _jspx_th_h_005fpanelGrid_005f2.setPageContext(_jspx_page_context);
    _jspx_th_h_005fpanelGrid_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fpanelTab_005f0);
    // /workflow/escritorios/escritorio.jsp(70,15) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGrid_005f2.setId("panelPrincipalUno");
    // /workflow/escritorios/escritorio.jsp(70,15) name = columns type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGrid_005f2.setColumns("2");
    // /workflow/escritorios/escritorio.jsp(70,15) name = align type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGrid_005f2.setAlign("top");
    // /workflow/escritorios/escritorio.jsp(70,15) name = columnClasses type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGrid_005f2.setColumnClasses("vertical-align:top");
    int _jspx_eval_h_005fpanelGrid_005f2 = _jspx_th_h_005fpanelGrid_005f2.doStartTag();
    if (_jspx_eval_h_005fpanelGrid_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_005fpanelGrid_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_005fpanelGrid_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_005fpanelGrid_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_x_005finputCalendar_005f0(_jspx_th_h_005fpanelGrid_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_h_005fpanelGroup_005f3(_jspx_th_h_005fpanelGrid_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_h_005fpanelGrid_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_005fpanelGrid_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_h_005fpanelGrid_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns_005fcolumnClasses_005falign.reuse(_jspx_th_h_005fpanelGrid_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns_005fcolumnClasses_005falign.reuse(_jspx_th_h_005fpanelGrid_005f2);
    return false;
  }

  private boolean _jspx_meth_x_005finputCalendar_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGrid_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:inputCalendar
    org.apache.myfaces.custom.calendar.HtmlInputCalendarTag _jspx_th_x_005finputCalendar_005f0 = (org.apache.myfaces.custom.calendar.HtmlInputCalendarTag) _005fjspx_005ftagPool_005fx_005finputCalendar_0026_005fweekRowClass_005fvalue_005frendered_005fmonthYearRowClass_005fcurrentDayCellClass_005fnobody.get(org.apache.myfaces.custom.calendar.HtmlInputCalendarTag.class);
    _jspx_th_x_005finputCalendar_005f0.setPageContext(_jspx_page_context);
    _jspx_th_x_005finputCalendar_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGrid_005f2);
    // /workflow/escritorios/escritorio.jsp(71,6) name = monthYearRowClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005finputCalendar_005f0.setMonthYearRowClass("yearMonthHeader");
    // /workflow/escritorios/escritorio.jsp(71,6) name = weekRowClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005finputCalendar_005f0.setWeekRowClass("weekHeader");
    // /workflow/escritorios/escritorio.jsp(71,6) name = rendered type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005finputCalendar_005f0.setRendered("false");
    // /workflow/escritorios/escritorio.jsp(71,6) name = currentDayCellClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005finputCalendar_005f0.setCurrentDayCellClass("currentDayCell");
    // /workflow/escritorios/escritorio.jsp(71,6) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005finputCalendar_005f0.setValue("#{EscritorioBean.fechaSelec}");
    int _jspx_eval_x_005finputCalendar_005f0 = _jspx_th_x_005finputCalendar_005f0.doStartTag();
    if (_jspx_th_x_005finputCalendar_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005finputCalendar_0026_005fweekRowClass_005fvalue_005frendered_005fmonthYearRowClass_005fcurrentDayCellClass_005fnobody.reuse(_jspx_th_x_005finputCalendar_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005finputCalendar_0026_005fweekRowClass_005fvalue_005frendered_005fmonthYearRowClass_005fcurrentDayCellClass_005fnobody.reuse(_jspx_th_x_005finputCalendar_005f0);
    return false;
  }

  private boolean _jspx_meth_h_005fpanelGroup_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGrid_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:panelGroup
    org.apache.myfaces.taglib.html.HtmlPanelGroupTag _jspx_th_h_005fpanelGroup_005f3 = (org.apache.myfaces.taglib.html.HtmlPanelGroupTag) _005fjspx_005ftagPool_005fh_005fpanelGroup.get(org.apache.myfaces.taglib.html.HtmlPanelGroupTag.class);
    _jspx_th_h_005fpanelGroup_005f3.setPageContext(_jspx_page_context);
    _jspx_th_h_005fpanelGroup_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGrid_005f2);
    int _jspx_eval_h_005fpanelGroup_005f3 = _jspx_th_h_005fpanelGroup_005f3.doStartTag();
    if (_jspx_eval_h_005fpanelGroup_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_005fpanelGroup_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_005fpanelGroup_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_005fpanelGroup_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_f_005fverbatim_005f1(_jspx_th_h_005fpanelGroup_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_s_005flayoutingTitlePane_005f0(_jspx_th_h_005fpanelGroup_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_h_005fpanelGroup_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_005fpanelGroup_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_h_005fpanelGroup_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005fpanelGroup.reuse(_jspx_th_h_005fpanelGroup_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005fpanelGroup.reuse(_jspx_th_h_005fpanelGroup_005f3);
    return false;
  }

  private boolean _jspx_meth_f_005fverbatim_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:verbatim
    org.apache.myfaces.taglib.core.VerbatimTag _jspx_th_f_005fverbatim_005f1 = (org.apache.myfaces.taglib.core.VerbatimTag) _005fjspx_005ftagPool_005ff_005fverbatim.get(org.apache.myfaces.taglib.core.VerbatimTag.class);
    _jspx_th_f_005fverbatim_005f1.setPageContext(_jspx_page_context);
    _jspx_th_f_005fverbatim_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f3);
    int _jspx_eval_f_005fverbatim_005f1 = _jspx_th_f_005fverbatim_005f1.doStartTag();
    if (_jspx_eval_f_005fverbatim_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_f_005fverbatim_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_f_005fverbatim_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_f_005fverbatim_005f1.doInitBody();
      }
      do {
        out.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        int evalDoAfterBody = _jspx_th_f_005fverbatim_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_f_005fverbatim_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_f_005fverbatim_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005flayoutingTitlePane_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:layoutingTitlePane
    org.apache.myfaces.custom.dojolayouts.TitlePaneTag _jspx_th_s_005flayoutingTitlePane_005f0 = (org.apache.myfaces.custom.dojolayouts.TitlePaneTag) _005fjspx_005ftagPool_005fs_005flayoutingTitlePane_0026_005flabelNodeClass_005flabel_005fid_005fcontainerNodeClass.get(org.apache.myfaces.custom.dojolayouts.TitlePaneTag.class);
    _jspx_th_s_005flayoutingTitlePane_005f0.setPageContext(_jspx_page_context);
    _jspx_th_s_005flayoutingTitlePane_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f3);
    // /workflow/escritorios/escritorio.jsp(75,6) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flayoutingTitlePane_005f0.setId("listadoAvisos");
    // /workflow/escritorios/escritorio.jsp(75,6) name = label type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flayoutingTitlePane_005f0.setLabel(" Avisos");
    // /workflow/escritorios/escritorio.jsp(75,6) name = containerNodeClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flayoutingTitlePane_005f0.setContainerNodeClass("contentTitlePane");
    // /workflow/escritorios/escritorio.jsp(75,6) name = labelNodeClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flayoutingTitlePane_005f0.setLabelNodeClass("labelTitlePane; aling='top'");
    int _jspx_eval_s_005flayoutingTitlePane_005f0 = _jspx_th_s_005flayoutingTitlePane_005f0.doStartTag();
    if (_jspx_eval_s_005flayoutingTitlePane_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fdataTable_005f0(_jspx_th_s_005flayoutingTitlePane_005f0, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t");
    }
    if (_jspx_th_s_005flayoutingTitlePane_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005flayoutingTitlePane_0026_005flabelNodeClass_005flabel_005fid_005fcontainerNodeClass.reuse(_jspx_th_s_005flayoutingTitlePane_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005flayoutingTitlePane_0026_005flabelNodeClass_005flabel_005fid_005fcontainerNodeClass.reuse(_jspx_th_s_005flayoutingTitlePane_005f0);
    return false;
  }

  private boolean _jspx_meth_x_005fdataTable_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005flayoutingTitlePane_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:dataTable
    org.apache.myfaces.taglib.html.ext.HtmlDataTableTag _jspx_th_x_005fdataTable_005f0 = (org.apache.myfaces.taglib.html.ext.HtmlDataTableTag) _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fwidth_005fvar_005fvalue_005fstyle_005fid.get(org.apache.myfaces.taglib.html.ext.HtmlDataTableTag.class);
    _jspx_th_x_005fdataTable_005f0.setPageContext(_jspx_page_context);
    _jspx_th_x_005fdataTable_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005flayoutingTitlePane_005f0);
    // /workflow/escritorios/escritorio.jsp(77,7) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f0.setId("avisosList");
    // /workflow/escritorios/escritorio.jsp(77,7) name = var type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f0.setVar("aviso");
    // /workflow/escritorios/escritorio.jsp(77,7) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f0.setValue("");
    // /workflow/escritorios/escritorio.jsp(77,7) name = width type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f0.setWidth("400");
    // /workflow/escritorios/escritorio.jsp(77,7) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f0.setStyle(" width : 663px;");
    int _jspx_eval_x_005fdataTable_005f0 = _jspx_th_x_005fdataTable_005f0.doStartTag();
    if (_jspx_eval_x_005fdataTable_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fdataTable_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fdataTable_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fdataTable_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_h_005fcolumn_005f0(_jspx_th_x_005fdataTable_005f0, _jspx_page_context))
          return true;
        out.write("\t\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fdataTable_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fdataTable_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fdataTable_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fwidth_005fvar_005fvalue_005fstyle_005fid.reuse(_jspx_th_x_005fdataTable_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fwidth_005fvar_005fvalue_005fstyle_005fid.reuse(_jspx_th_x_005fdataTable_005f0);
    return false;
  }

  private boolean _jspx_meth_h_005fcolumn_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:column
    org.apache.myfaces.taglib.html.HtmlColumnTag _jspx_th_h_005fcolumn_005f0 = (org.apache.myfaces.taglib.html.HtmlColumnTag) _005fjspx_005ftagPool_005fh_005fcolumn.get(org.apache.myfaces.taglib.html.HtmlColumnTag.class);
    _jspx_th_h_005fcolumn_005f0.setPageContext(_jspx_page_context);
    _jspx_th_h_005fcolumn_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f0);
    int _jspx_eval_h_005fcolumn_005f0 = _jspx_th_h_005fcolumn_005f0.doStartTag();
    if (_jspx_eval_h_005fcolumn_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_005fcolumn_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_005fcolumn_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_005fcolumn_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_h_005foutputText_005f1(_jspx_th_h_005fcolumn_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t    \t\t");
        int evalDoAfterBody = _jspx_th_h_005fcolumn_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_005fcolumn_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_h_005fcolumn_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005fcolumn.reuse(_jspx_th_h_005fcolumn_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005fcolumn.reuse(_jspx_th_h_005fcolumn_005f0);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fcolumn_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f1 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f1.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fcolumn_005f0);
    // /workflow/escritorios/escritorio.jsp(82,10) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f1.setValue("#{aviso}");
    // /workflow/escritorios/escritorio.jsp(82,10) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f1.setStyle("font-size: 12px;color: blue");
    int _jspx_eval_h_005foutputText_005f1 = _jspx_th_h_005foutputText_005f1.doStartTag();
    if (_jspx_th_h_005foutputText_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f1);
    return false;
  }

  private boolean _jspx_meth_x_005fpanelTabbedPane_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fpanelTab_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:panelTabbedPane
    org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPaneTag _jspx_th_x_005fpanelTabbedPane_005f1 = (org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPaneTag) _005fjspx_005ftagPool_005fx_005fpanelTabbedPane_0026_005fserverSideTabSwitch_005fselectedIndex_005fid.get(org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPaneTag.class);
    _jspx_th_x_005fpanelTabbedPane_005f1.setPageContext(_jspx_page_context);
    _jspx_th_x_005fpanelTabbedPane_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fpanelTab_005f0);
    // /workflow/escritorios/escritorio.jsp(90,4) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTabbedPane_005f1.setId("tabbedTablas");
    // /workflow/escritorios/escritorio.jsp(90,4) name = serverSideTabSwitch type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTabbedPane_005f1.setServerSideTabSwitch("false");
    // /workflow/escritorios/escritorio.jsp(90,4) name = selectedIndex type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTabbedPane_005f1.setSelectedIndex("#{EscritorioBean.idTab}");
    int _jspx_eval_x_005fpanelTabbedPane_005f1 = _jspx_th_x_005fpanelTabbedPane_005f1.doStartTag();
    if (_jspx_eval_x_005fpanelTabbedPane_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fpanelTabbedPane_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fpanelTabbedPane_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fpanelTabbedPane_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        if (_jspx_meth_c_005fif_005f0(_jspx_th_x_005fpanelTabbedPane_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t");
        if (_jspx_meth_c_005fif_005f1(_jspx_th_x_005fpanelTabbedPane_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        if (_jspx_meth_c_005fif_005f2(_jspx_th_x_005fpanelTabbedPane_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fpanelTabbedPane_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fpanelTabbedPane_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fpanelTabbedPane_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fpanelTabbedPane_0026_005fserverSideTabSwitch_005fselectedIndex_005fid.reuse(_jspx_th_x_005fpanelTabbedPane_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fpanelTabbedPane_0026_005fserverSideTabSwitch_005fselectedIndex_005fid.reuse(_jspx_th_x_005fpanelTabbedPane_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fpanelTabbedPane_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fpanelTabbedPane_005f1);
    // /workflow/escritorios/escritorio.jsp(91,5) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty EscritorioBean.tablaTareasPendientes}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_x_005fpanelTab_005f1(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_x_005fpanelTab_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:panelTab
    org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag _jspx_th_x_005fpanelTab_005f1 = (org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag) _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid.get(org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag.class);
    _jspx_th_x_005fpanelTab_005f1.setPageContext(_jspx_page_context);
    _jspx_th_x_005fpanelTab_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /workflow/escritorios/escritorio.jsp(92,6) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTab_005f1.setId("tabPendientes");
    // /workflow/escritorios/escritorio.jsp(92,6) name = label type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTab_005f1.setLabel("Tareas Pendientes");
    int _jspx_eval_x_005fpanelTab_005f1 = _jspx_th_x_005fpanelTab_005f1.doStartTag();
    if (_jspx_eval_x_005fpanelTab_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fpanelTab_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fpanelTab_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fpanelTab_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_s_005flayoutingTitlePane_005f1(_jspx_th_x_005fpanelTab_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fpanelTab_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fpanelTab_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fpanelTab_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid.reuse(_jspx_th_x_005fpanelTab_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid.reuse(_jspx_th_x_005fpanelTab_005f1);
    return false;
  }

  private boolean _jspx_meth_s_005flayoutingTitlePane_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fpanelTab_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:layoutingTitlePane
    org.apache.myfaces.custom.dojolayouts.TitlePaneTag _jspx_th_s_005flayoutingTitlePane_005f1 = (org.apache.myfaces.custom.dojolayouts.TitlePaneTag) _005fjspx_005ftagPool_005fs_005flayoutingTitlePane_0026_005flabelNodeClass_005flabel_005fid_005fcontainerNodeClass.get(org.apache.myfaces.custom.dojolayouts.TitlePaneTag.class);
    _jspx_th_s_005flayoutingTitlePane_005f1.setPageContext(_jspx_page_context);
    _jspx_th_s_005flayoutingTitlePane_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fpanelTab_005f1);
    // /workflow/escritorios/escritorio.jsp(93,7) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flayoutingTitlePane_005f1.setId("listadoTareasPendientes");
    // /workflow/escritorios/escritorio.jsp(93,7) name = label type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flayoutingTitlePane_005f1.setLabel("Listado de tareas pendientes");
    // /workflow/escritorios/escritorio.jsp(93,7) name = containerNodeClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flayoutingTitlePane_005f1.setContainerNodeClass("contentTitlePaneAuto");
    // /workflow/escritorios/escritorio.jsp(93,7) name = labelNodeClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_s_005flayoutingTitlePane_005f1.setLabelNodeClass("labelTitlePaneAuto");
    int _jspx_eval_s_005flayoutingTitlePane_005f1 = _jspx_th_s_005flayoutingTitlePane_005f1.doStartTag();
    if (_jspx_eval_s_005flayoutingTitlePane_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_h_005fpanelGrid_005f3(_jspx_th_s_005flayoutingTitlePane_005f1, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_s_005flayoutingTitlePane_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fs_005flayoutingTitlePane_0026_005flabelNodeClass_005flabel_005fid_005fcontainerNodeClass.reuse(_jspx_th_s_005flayoutingTitlePane_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fs_005flayoutingTitlePane_0026_005flabelNodeClass_005flabel_005fid_005fcontainerNodeClass.reuse(_jspx_th_s_005flayoutingTitlePane_005f1);
    return false;
  }

  private boolean _jspx_meth_h_005fpanelGrid_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_005flayoutingTitlePane_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:panelGrid
    org.apache.myfaces.taglib.html.HtmlPanelGridTag _jspx_th_h_005fpanelGrid_005f3 = (org.apache.myfaces.taglib.html.HtmlPanelGridTag) _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns.get(org.apache.myfaces.taglib.html.HtmlPanelGridTag.class);
    _jspx_th_h_005fpanelGrid_005f3.setPageContext(_jspx_page_context);
    _jspx_th_h_005fpanelGrid_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_005flayoutingTitlePane_005f1);
    // /workflow/escritorios/escritorio.jsp(95,9) name = columns type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGrid_005f3.setColumns("1");
    // /workflow/escritorios/escritorio.jsp(95,9) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGrid_005f3.setId("panelTareasPendientes");
    int _jspx_eval_h_005fpanelGrid_005f3 = _jspx_th_h_005fpanelGrid_005f3.doStartTag();
    if (_jspx_eval_h_005fpanelGrid_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_005fpanelGrid_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_005fpanelGrid_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_005fpanelGrid_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fdiv_005f0(_jspx_th_h_005fpanelGrid_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_h_005fpanelGrid_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_005fpanelGrid_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_h_005fpanelGrid_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns.reuse(_jspx_th_h_005fpanelGrid_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns.reuse(_jspx_th_h_005fpanelGrid_005f3);
    return false;
  }

  private boolean _jspx_meth_x_005fdiv_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGrid_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:div
    org.apache.myfaces.custom.div.DivTag _jspx_th_x_005fdiv_005f0 = (org.apache.myfaces.custom.div.DivTag) _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle.get(org.apache.myfaces.custom.div.DivTag.class);
    _jspx_th_x_005fdiv_005f0.setPageContext(_jspx_page_context);
    _jspx_th_x_005fdiv_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGrid_005f3);
    // /workflow/escritorios/escritorio.jsp(96,9) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdiv_005f0.setStyle("OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 880px; HEIGHT: 370px; border: 1px; margin-left: auto; margin-right: auto;");
    int _jspx_eval_x_005fdiv_005f0 = _jspx_th_x_005fdiv_005f0.doStartTag();
    if (_jspx_eval_x_005fdiv_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fdataTable_005f1(_jspx_th_x_005fdiv_005f0, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fdiv_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle.reuse(_jspx_th_x_005fdiv_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle.reuse(_jspx_th_x_005fdiv_005f0);
    return false;
  }

  private boolean _jspx_meth_x_005fdataTable_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdiv_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:dataTable
    org.apache.myfaces.taglib.html.ext.HtmlDataTableTag _jspx_th_x_005fdataTable_005f1 = (org.apache.myfaces.taglib.html.ext.HtmlDataTableTag) _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses.get(org.apache.myfaces.taglib.html.ext.HtmlDataTableTag.class);
    _jspx_th_x_005fdataTable_005f1.setPageContext(_jspx_page_context);
    _jspx_th_x_005fdataTable_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdiv_005f0);
    // /workflow/escritorios/escritorio.jsp(97,9) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f1.setValue("#{EscritorioBean.tablaTareasPendientes}");
    // /workflow/escritorios/escritorio.jsp(97,9) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f1.setId("tablaTareasPendientes");
    // /workflow/escritorios/escritorio.jsp(97,9) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f1.setStyleClass("standardTable");
    // /workflow/escritorios/escritorio.jsp(97,9) name = headerClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f1.setHeaderClass("standardTable_Header");
    // /workflow/escritorios/escritorio.jsp(97,9) name = footerClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f1.setFooterClass("standardTable_Header");
    // /workflow/escritorios/escritorio.jsp(97,9) name = rowClasses type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f1.setRowClasses("standardTable_Row1,standardTable_Row2");
    // /workflow/escritorios/escritorio.jsp(97,9) name = sortable type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f1.setSortable("true");
    // /workflow/escritorios/escritorio.jsp(97,9) name = columnClasses type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f1.setColumnClasses("standardTable_Column,standardTable_ColumnCentered,standardTable_Column");
    // /workflow/escritorios/escritorio.jsp(97,9) name = var type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f1.setVar("tablaTPendiente");
    // /workflow/escritorios/escritorio.jsp(97,9) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f1.setStyle(" width : 855px;");
    int _jspx_eval_x_005fdataTable_005f1 = _jspx_th_x_005fdataTable_005f1.doStartTag();
    if (_jspx_eval_x_005fdataTable_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fdataTable_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fdataTable_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fdataTable_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t                        ");
        if (_jspx_meth_x_005fcolumn_005f0(_jspx_th_x_005fdataTable_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t                        ");
        if (_jspx_meth_x_005fcolumn_005f1(_jspx_th_x_005fdataTable_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t                        ");
        if (_jspx_meth_x_005fcolumn_005f2(_jspx_th_x_005fdataTable_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                     ");
        if (_jspx_meth_x_005fcolumn_005f3(_jspx_th_x_005fdataTable_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t            \t\t            ");
        if (_jspx_meth_x_005fcolumn_005f4(_jspx_th_x_005fdataTable_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t            \t\t    ");
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcolumn_005f5(_jspx_th_x_005fdataTable_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fdataTable_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fdataTable_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fdataTable_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses.reuse(_jspx_th_x_005fdataTable_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses.reuse(_jspx_th_x_005fdataTable_005f1);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f0 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f0.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f1);
    int _jspx_eval_x_005fcolumn_005f0 = _jspx_th_x_005fcolumn_005f0.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f2(_jspx_th_x_005fcolumn_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f3(_jspx_th_x_005fcolumn_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f0);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f2 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f2.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f0);
    // /workflow/escritorios/escritorio.jsp(106,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f2.setName("header");
    int _jspx_eval_f_005ffacet_005f2 = _jspx_th_f_005ffacet_005f2.doStartTag();
    if (_jspx_eval_f_005ffacet_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f2(_jspx_th_f_005ffacet_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f2);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f2 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f2.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f2);
    // /workflow/escritorios/escritorio.jsp(107,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f2.setValue("Tarea");
    // /workflow/escritorios/escritorio.jsp(107,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f2.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(107,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f2.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f2 = _jspx_th_h_005foutputText_005f2.doStartTag();
    if (_jspx_th_h_005foutputText_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f2);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f3 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f3.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f0);
    // /workflow/escritorios/escritorio.jsp(109,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f3.setValue("#{tablaTPendiente.tarea.titulo}");
    // /workflow/escritorios/escritorio.jsp(109,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f3.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f3 = _jspx_th_h_005foutputText_005f3.doStartTag();
    if (_jspx_th_h_005foutputText_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f3);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f1 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f1.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f1);
    int _jspx_eval_x_005fcolumn_005f1 = _jspx_th_x_005fcolumn_005f1.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f3(_jspx_th_x_005fcolumn_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f5(_jspx_th_x_005fcolumn_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f1);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f3 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f3.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f1);
    // /workflow/escritorios/escritorio.jsp(112,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f3.setName("header");
    int _jspx_eval_f_005ffacet_005f3 = _jspx_th_f_005ffacet_005f3.doStartTag();
    if (_jspx_eval_f_005ffacet_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f4(_jspx_th_f_005ffacet_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f3);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f4 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f4.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f3);
    // /workflow/escritorios/escritorio.jsp(113,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f4.setValue("Proceso");
    // /workflow/escritorios/escritorio.jsp(113,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f4.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(113,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f4.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f4 = _jspx_th_h_005foutputText_005f4.doStartTag();
    if (_jspx_th_h_005foutputText_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f4);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f5 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f5.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f1);
    // /workflow/escritorios/escritorio.jsp(115,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f5.setValue("#{tablaTPendiente.detalleTramite.tramite.proceso.titulo}");
    // /workflow/escritorios/escritorio.jsp(115,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f5.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f5 = _jspx_th_h_005foutputText_005f5.doStartTag();
    if (_jspx_th_h_005foutputText_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f5);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f2 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f2.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f1);
    int _jspx_eval_x_005fcolumn_005f2 = _jspx_th_x_005fcolumn_005f2.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f4(_jspx_th_x_005fcolumn_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f7(_jspx_th_x_005fcolumn_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f2);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f4 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f4.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f2);
    // /workflow/escritorios/escritorio.jsp(118,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f4.setName("header");
    int _jspx_eval_f_005ffacet_005f4 = _jspx_th_f_005ffacet_005f4.doStartTag();
    if (_jspx_eval_f_005ffacet_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f6(_jspx_th_f_005ffacet_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f4);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f6 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f6.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f4);
    // /workflow/escritorios/escritorio.jsp(119,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f6.setValue("Tramite");
    // /workflow/escritorios/escritorio.jsp(119,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f6.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(119,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f6.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f6 = _jspx_th_h_005foutputText_005f6.doStartTag();
    if (_jspx_th_h_005foutputText_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f6);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f7 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f7.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f2);
    // /workflow/escritorios/escritorio.jsp(121,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f7.setValue("#{tablaTPendiente.nombreTramite}");
    // /workflow/escritorios/escritorio.jsp(121,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f7.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f7 = _jspx_th_h_005foutputText_005f7.doStartTag();
    if (_jspx_th_h_005foutputText_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f7);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f3 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f3.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f1);
    int _jspx_eval_x_005fcolumn_005f3 = _jspx_th_x_005fcolumn_005f3.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f5(_jspx_th_x_005fcolumn_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f9(_jspx_th_x_005fcolumn_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f3);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f5 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f5.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f3);
    // /workflow/escritorios/escritorio.jsp(124,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f5.setName("header");
    int _jspx_eval_f_005ffacet_005f5 = _jspx_th_f_005ffacet_005f5.doStartTag();
    if (_jspx_eval_f_005ffacet_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f8(_jspx_th_f_005ffacet_005f5, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f5);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f8 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f8.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f5);
    // /workflow/escritorios/escritorio.jsp(125,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f8.setValue("Operador");
    // /workflow/escritorios/escritorio.jsp(125,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f8.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(125,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f8.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f8 = _jspx_th_h_005foutputText_005f8.doStartTag();
    if (_jspx_th_h_005foutputText_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f8);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f9 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f9.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f3);
    // /workflow/escritorios/escritorio.jsp(127,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f9.setValue("#{tablaTPendiente.operadorInciaTramite}");
    // /workflow/escritorios/escritorio.jsp(127,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f9.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f9 = _jspx_th_h_005foutputText_005f9.doStartTag();
    if (_jspx_th_h_005foutputText_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f9);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f4 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f4.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f1);
    int _jspx_eval_x_005fcolumn_005f4 = _jspx_th_x_005fcolumn_005f4.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        if (_jspx_meth_f_005ffacet_005f6(_jspx_th_x_005fcolumn_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t                \t\t                ");
        if (_jspx_meth_h_005foutputText_005f11(_jspx_th_x_005fcolumn_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f4);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f6 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f6.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f4);
    // /workflow/escritorios/escritorio.jsp(130,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f6.setName("header");
    int _jspx_eval_f_005ffacet_005f6 = _jspx_th_f_005ffacet_005f6.doStartTag();
    if (_jspx_eval_f_005ffacet_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f10(_jspx_th_f_005ffacet_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f6);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f10 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f10.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f6);
    // /workflow/escritorios/escritorio.jsp(131,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f10.setValue("Fecha Creación");
    // /workflow/escritorios/escritorio.jsp(131,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f10.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(131,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f10.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f10 = _jspx_th_h_005foutputText_005f10.doStartTag();
    if (_jspx_th_h_005foutputText_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f10);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f11 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f11.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f4);
    // /workflow/escritorios/escritorio.jsp(133,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f11.setValue("#{tablaTPendiente.detalleTramite.fechaInicio}");
    // /workflow/escritorios/escritorio.jsp(133,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f11.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f11 = _jspx_th_h_005foutputText_005f11.doStartTag();
    if (_jspx_th_h_005foutputText_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f11);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f5 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f5.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f1);
    int _jspx_eval_x_005fcolumn_005f5 = _jspx_th_x_005fcolumn_005f5.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f5.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcommandLink_005f1(_jspx_th_x_005fcolumn_005f5, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f5);
    return false;
  }

  private boolean _jspx_meth_x_005fcommandLink_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:commandLink
    org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag _jspx_th_x_005fcommandLink_005f1 = (org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag) _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.get(org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag.class);
    _jspx_th_x_005fcommandLink_005f1.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcommandLink_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f5);
    // /workflow/escritorios/escritorio.jsp(142,11) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f1.setId("tomarTareaLink");
    // /workflow/escritorios/escritorio.jsp(142,11) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f1.setAction("#{EscritorioBean.tomarTarea}");
    int _jspx_eval_x_005fcommandLink_005f1 = _jspx_th_x_005fcommandLink_005f1.doStartTag();
    if (_jspx_eval_x_005fcommandLink_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_f_005fparam_005f0(_jspx_th_x_005fcommandLink_005f1, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fgraphicImage_005f1(_jspx_th_x_005fcommandLink_005f1, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fcommandLink_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f1);
    return false;
  }

  private boolean _jspx_meth_f_005fparam_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:param
    org.apache.myfaces.taglib.core.ParamTag _jspx_th_f_005fparam_005f0 = (org.apache.myfaces.taglib.core.ParamTag) _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.get(org.apache.myfaces.taglib.core.ParamTag.class);
    _jspx_th_f_005fparam_005f0.setPageContext(_jspx_page_context);
    _jspx_th_f_005fparam_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f1);
    // /workflow/escritorios/escritorio.jsp(143,12) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f0.setId("idDetalle");
    // /workflow/escritorios/escritorio.jsp(143,12) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f0.setName("idDetalle");
    // /workflow/escritorios/escritorio.jsp(143,12) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f0.setValue("#{tablaTPendiente.detalleTramite.idDetalleTramite}");
    int _jspx_eval_f_005fparam_005f0 = _jspx_th_f_005fparam_005f0.doStartTag();
    if (_jspx_th_f_005fparam_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f0);
    return false;
  }

  private boolean _jspx_meth_x_005fgraphicImage_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:graphicImage
    org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag _jspx_th_x_005fgraphicImage_005f1 = (org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag) _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.get(org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag.class);
    _jspx_th_x_005fgraphicImage_005f1.setPageContext(_jspx_page_context);
    _jspx_th_x_005fgraphicImage_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f1);
    // /workflow/escritorios/escritorio.jsp(144,12) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f1.setValue("/img/icon/tomar.gif");
    // /workflow/escritorios/escritorio.jsp(144,12) name = title type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f1.setTitle("Iniciar la tarea.");
    // /workflow/escritorios/escritorio.jsp(144,12) name = border type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f1.setBorder("0");
    int _jspx_eval_x_005fgraphicImage_005f1 = _jspx_th_x_005fgraphicImage_005f1.doStartTag();
    if (_jspx_th_x_005fgraphicImage_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fpanelTabbedPane_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fpanelTabbedPane_005f1);
    // /workflow/escritorios/escritorio.jsp(154,5) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty EscritorioBean.tablaTareasTomadas}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_x_005fpanelTab_005f2(_jspx_th_c_005fif_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_x_005fpanelTab_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:panelTab
    org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag _jspx_th_x_005fpanelTab_005f2 = (org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag) _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid.get(org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag.class);
    _jspx_th_x_005fpanelTab_005f2.setPageContext(_jspx_page_context);
    _jspx_th_x_005fpanelTab_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /workflow/escritorios/escritorio.jsp(155,6) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTab_005f2.setId("tabTomadas");
    // /workflow/escritorios/escritorio.jsp(155,6) name = label type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTab_005f2.setLabel("Tareas Tomadas");
    int _jspx_eval_x_005fpanelTab_005f2 = _jspx_th_x_005fpanelTab_005f2.doStartTag();
    if (_jspx_eval_x_005fpanelTab_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fpanelTab_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fpanelTab_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fpanelTab_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fdiv_005f1(_jspx_th_x_005fpanelTab_005f2, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fpanelTab_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fpanelTab_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fpanelTab_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid.reuse(_jspx_th_x_005fpanelTab_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid.reuse(_jspx_th_x_005fpanelTab_005f2);
    return false;
  }

  private boolean _jspx_meth_x_005fdiv_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fpanelTab_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:div
    org.apache.myfaces.custom.div.DivTag _jspx_th_x_005fdiv_005f1 = (org.apache.myfaces.custom.div.DivTag) _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle.get(org.apache.myfaces.custom.div.DivTag.class);
    _jspx_th_x_005fdiv_005f1.setPageContext(_jspx_page_context);
    _jspx_th_x_005fdiv_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fpanelTab_005f2);
    // /workflow/escritorios/escritorio.jsp(156,7) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdiv_005f1.setStyle("OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 880px; HEIGHT: 370px; border: 1px; margin-left: auto; margin-right: auto;");
    int _jspx_eval_x_005fdiv_005f1 = _jspx_th_x_005fdiv_005f1.doStartTag();
    if (_jspx_eval_x_005fdiv_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fdataTable_005f2(_jspx_th_x_005fdiv_005f1, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fdiv_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle.reuse(_jspx_th_x_005fdiv_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle.reuse(_jspx_th_x_005fdiv_005f1);
    return false;
  }

  private boolean _jspx_meth_x_005fdataTable_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdiv_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:dataTable
    org.apache.myfaces.taglib.html.ext.HtmlDataTableTag _jspx_th_x_005fdataTable_005f2 = (org.apache.myfaces.taglib.html.ext.HtmlDataTableTag) _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses.get(org.apache.myfaces.taglib.html.ext.HtmlDataTableTag.class);
    _jspx_th_x_005fdataTable_005f2.setPageContext(_jspx_page_context);
    _jspx_th_x_005fdataTable_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdiv_005f1);
    // /workflow/escritorios/escritorio.jsp(157,9) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f2.setValue("#{EscritorioBean.tablaTareasTomadas}");
    // /workflow/escritorios/escritorio.jsp(157,9) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f2.setId("tablaTareasTomadas");
    // /workflow/escritorios/escritorio.jsp(157,9) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f2.setStyleClass("standardTable");
    // /workflow/escritorios/escritorio.jsp(157,9) name = headerClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f2.setHeaderClass("standardTable_Header");
    // /workflow/escritorios/escritorio.jsp(157,9) name = footerClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f2.setFooterClass("standardTable_Header");
    // /workflow/escritorios/escritorio.jsp(157,9) name = rowClasses type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f2.setRowClasses("standardTable_Row1,standardTable_Row2");
    // /workflow/escritorios/escritorio.jsp(157,9) name = sortable type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f2.setSortable("true");
    // /workflow/escritorios/escritorio.jsp(157,9) name = columnClasses type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f2.setColumnClasses("standardTable_Column,standardTable_ColumnCentered,standardTable_Column");
    // /workflow/escritorios/escritorio.jsp(157,9) name = var type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f2.setVar("tablaTarea");
    // /workflow/escritorios/escritorio.jsp(157,9) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f2.setStyle(" width : 855px;");
    int _jspx_eval_x_005fdataTable_005f2 = _jspx_th_x_005fdataTable_005f2.doStartTag();
    if (_jspx_eval_x_005fdataTable_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fdataTable_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fdataTable_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fdataTable_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t    \r\n");
        out.write("\t\t\t\t                        ");
        if (_jspx_meth_x_005fcolumn_005f6(_jspx_th_x_005fdataTable_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                        \t\r\n");
        out.write("\t\t\t                        \t");
        if (_jspx_meth_x_005fcolumn_005f7(_jspx_th_x_005fdataTable_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                        \t\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcolumn_005f8(_jspx_th_x_005fdataTable_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\r\n");
        out.write("\t\t\t                        \t");
        if (_jspx_meth_x_005fcolumn_005f9(_jspx_th_x_005fdataTable_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                        ");
        out.write("\r\n");
        out.write("\t\t            \t\t            ");
        if (_jspx_meth_x_005fcolumn_005f10(_jspx_th_x_005fdataTable_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t            \t\t            ");
        if (_jspx_meth_x_005fcolumn_005f11(_jspx_th_x_005fdataTable_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcolumn_005f12(_jspx_th_x_005fdataTable_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fdataTable_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fdataTable_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fdataTable_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses.reuse(_jspx_th_x_005fdataTable_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses.reuse(_jspx_th_x_005fdataTable_005f2);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f6 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f6.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f2);
    int _jspx_eval_x_005fcolumn_005f6 = _jspx_th_x_005fcolumn_005f6.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f6.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f7(_jspx_th_x_005fcolumn_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f13(_jspx_th_x_005fcolumn_005f6, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f6);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f7 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f7.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f6);
    // /workflow/escritorios/escritorio.jsp(167,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f7.setName("header");
    int _jspx_eval_f_005ffacet_005f7 = _jspx_th_f_005ffacet_005f7.doStartTag();
    if (_jspx_eval_f_005ffacet_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f12(_jspx_th_f_005ffacet_005f7, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f7);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f12 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f12.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f7);
    // /workflow/escritorios/escritorio.jsp(168,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f12.setValue("Inició");
    // /workflow/escritorios/escritorio.jsp(168,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f12.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(168,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f12.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f12 = _jspx_th_h_005foutputText_005f12.doStartTag();
    if (_jspx_th_h_005foutputText_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f12);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f13 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f13.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f6);
    // /workflow/escritorios/escritorio.jsp(170,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f13.setValue("#{tablaTarea.detalleTramite.fechaInicioReal}");
    // /workflow/escritorios/escritorio.jsp(170,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f13.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f13 = _jspx_th_h_005foutputText_005f13.doStartTag();
    if (_jspx_th_h_005foutputText_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f13);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f7 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f7.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f2);
    int _jspx_eval_x_005fcolumn_005f7 = _jspx_th_x_005fcolumn_005f7.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f7.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f8(_jspx_th_x_005fcolumn_005f7, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f15(_jspx_th_x_005fcolumn_005f7, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f7);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f8 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f8.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f7);
    // /workflow/escritorios/escritorio.jsp(174,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f8.setName("header");
    int _jspx_eval_f_005ffacet_005f8 = _jspx_th_f_005ffacet_005f8.doStartTag();
    if (_jspx_eval_f_005ffacet_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f14(_jspx_th_f_005ffacet_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f8);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f14(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f14 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f14.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f8);
    // /workflow/escritorios/escritorio.jsp(175,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f14.setValue("Tarea");
    // /workflow/escritorios/escritorio.jsp(175,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f14.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(175,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f14.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f14 = _jspx_th_h_005foutputText_005f14.doStartTag();
    if (_jspx_th_h_005foutputText_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f14);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f15(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f15 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f15.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f7);
    // /workflow/escritorios/escritorio.jsp(177,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f15.setValue("#{tablaTarea.tarea.titulo}");
    // /workflow/escritorios/escritorio.jsp(177,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f15.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f15 = _jspx_th_h_005foutputText_005f15.doStartTag();
    if (_jspx_th_h_005foutputText_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f15);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f8 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f8.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f2);
    int _jspx_eval_x_005fcolumn_005f8 = _jspx_th_x_005fcolumn_005f8.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f8.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f9(_jspx_th_x_005fcolumn_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f17(_jspx_th_x_005fcolumn_005f8, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f8);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f9 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f9.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f8);
    // /workflow/escritorios/escritorio.jsp(181,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f9.setName("header");
    int _jspx_eval_f_005ffacet_005f9 = _jspx_th_f_005ffacet_005f9.doStartTag();
    if (_jspx_eval_f_005ffacet_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f16(_jspx_th_f_005ffacet_005f9, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f9);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f16(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f16 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f16.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f9);
    // /workflow/escritorios/escritorio.jsp(182,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f16.setValue("Proceso");
    // /workflow/escritorios/escritorio.jsp(182,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f16.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(182,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f16.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f16 = _jspx_th_h_005foutputText_005f16.doStartTag();
    if (_jspx_th_h_005foutputText_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f16);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f16);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f17(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f17 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f17.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f8);
    // /workflow/escritorios/escritorio.jsp(184,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f17.setValue("#{tablaTarea.detalleTramite.tramite.proceso.titulo}");
    // /workflow/escritorios/escritorio.jsp(184,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f17.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f17 = _jspx_th_h_005foutputText_005f17.doStartTag();
    if (_jspx_th_h_005foutputText_005f17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f17);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f17);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f9 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f9.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f2);
    int _jspx_eval_x_005fcolumn_005f9 = _jspx_th_x_005fcolumn_005f9.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f9.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f9.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f10(_jspx_th_x_005fcolumn_005f9, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f19(_jspx_th_x_005fcolumn_005f9, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f9);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f10 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f10.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f9);
    // /workflow/escritorios/escritorio.jsp(188,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f10.setName("header");
    int _jspx_eval_f_005ffacet_005f10 = _jspx_th_f_005ffacet_005f10.doStartTag();
    if (_jspx_eval_f_005ffacet_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f18(_jspx_th_f_005ffacet_005f10, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f10);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f18(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f18 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f18.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f10);
    // /workflow/escritorios/escritorio.jsp(189,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f18.setValue("Tramite");
    // /workflow/escritorios/escritorio.jsp(189,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f18.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(189,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f18.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f18 = _jspx_th_h_005foutputText_005f18.doStartTag();
    if (_jspx_th_h_005foutputText_005f18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f18);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f18);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f19(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f19 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f19.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f9);
    // /workflow/escritorios/escritorio.jsp(191,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f19.setValue("#{tablaTarea.nombreTramite}");
    // /workflow/escritorios/escritorio.jsp(191,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f19.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f19 = _jspx_th_h_005foutputText_005f19.doStartTag();
    if (_jspx_th_h_005foutputText_005f19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f19);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f19);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f10 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f10.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f2);
    int _jspx_eval_x_005fcolumn_005f10 = _jspx_th_x_005fcolumn_005f10.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f10.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f10.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        if (_jspx_meth_f_005ffacet_005f11(_jspx_th_x_005fcolumn_005f10, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t                \t\t                ");
        if (_jspx_meth_h_005foutputText_005f21(_jspx_th_x_005fcolumn_005f10, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f10);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f11 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f11.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f10);
    // /workflow/escritorios/escritorio.jsp(202,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f11.setName("header");
    int _jspx_eval_f_005ffacet_005f11 = _jspx_th_f_005ffacet_005f11.doStartTag();
    if (_jspx_eval_f_005ffacet_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f20(_jspx_th_f_005ffacet_005f11, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f11.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f11);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f20(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f20 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f20.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f11);
    // /workflow/escritorios/escritorio.jsp(203,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f20.setValue("Estado");
    // /workflow/escritorios/escritorio.jsp(203,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f20.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(203,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f20.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f20 = _jspx_th_h_005foutputText_005f20.doStartTag();
    if (_jspx_th_h_005foutputText_005f20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f20);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f20);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f21(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f21 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f21.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f10);
    // /workflow/escritorios/escritorio.jsp(205,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f21.setValue("#{tablaTarea.detalleTramite.estado.descripcion}");
    // /workflow/escritorios/escritorio.jsp(205,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f21.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f21 = _jspx_th_h_005foutputText_005f21.doStartTag();
    if (_jspx_th_h_005foutputText_005f21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f21);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f21);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f11 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f11.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f2);
    int _jspx_eval_x_005fcolumn_005f11 = _jspx_th_x_005fcolumn_005f11.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f11.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f11.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        if (_jspx_meth_f_005ffacet_005f12(_jspx_th_x_005fcolumn_005f11, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t                \t\t                ");
        if (_jspx_meth_h_005foutputText_005f23(_jspx_th_x_005fcolumn_005f11, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f11.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f11 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f11);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f12 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f12.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f11);
    // /workflow/escritorios/escritorio.jsp(209,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f12.setName("header");
    int _jspx_eval_f_005ffacet_005f12 = _jspx_th_f_005ffacet_005f12.doStartTag();
    if (_jspx_eval_f_005ffacet_005f12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f22(_jspx_th_f_005ffacet_005f12, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f12.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f12);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f22(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f22 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f22.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f12);
    // /workflow/escritorios/escritorio.jsp(210,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f22.setValue("Supervisor");
    // /workflow/escritorios/escritorio.jsp(210,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f22.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(210,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f22.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f22 = _jspx_th_h_005foutputText_005f22.doStartTag();
    if (_jspx_th_h_005foutputText_005f22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f22);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f22);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f23(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f23 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f23.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f11);
    // /workflow/escritorios/escritorio.jsp(212,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f23.setValue("#{tablaTarea.detalleTramite.tramite.operadorSup.apellido}");
    // /workflow/escritorios/escritorio.jsp(212,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f23.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f23 = _jspx_th_h_005foutputText_005f23.doStartTag();
    if (_jspx_th_h_005foutputText_005f23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f23);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f23);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f12 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f12.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f2);
    int _jspx_eval_x_005fcolumn_005f12 = _jspx_th_x_005fcolumn_005f12.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f12 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f12.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f12.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcommandLink_005f2(_jspx_th_x_005fcolumn_005f12, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f12.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f12 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f12);
    return false;
  }

  private boolean _jspx_meth_x_005fcommandLink_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:commandLink
    org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag _jspx_th_x_005fcommandLink_005f2 = (org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag) _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.get(org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag.class);
    _jspx_th_x_005fcommandLink_005f2.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcommandLink_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f12);
    // /workflow/escritorios/escritorio.jsp(216,11) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f2.setId("verTareaLink");
    // /workflow/escritorios/escritorio.jsp(216,11) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f2.setAction("#{EscritorioBean.verTarea}");
    int _jspx_eval_x_005fcommandLink_005f2 = _jspx_th_x_005fcommandLink_005f2.doStartTag();
    if (_jspx_eval_x_005fcommandLink_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_f_005fparam_005f1(_jspx_th_x_005fcommandLink_005f2, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fgraphicImage_005f2(_jspx_th_x_005fcommandLink_005f2, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fcommandLink_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f2);
    return false;
  }

  private boolean _jspx_meth_f_005fparam_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:param
    org.apache.myfaces.taglib.core.ParamTag _jspx_th_f_005fparam_005f1 = (org.apache.myfaces.taglib.core.ParamTag) _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.get(org.apache.myfaces.taglib.core.ParamTag.class);
    _jspx_th_f_005fparam_005f1.setPageContext(_jspx_page_context);
    _jspx_th_f_005fparam_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f2);
    // /workflow/escritorios/escritorio.jsp(217,12) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f1.setId("idDetalle");
    // /workflow/escritorios/escritorio.jsp(217,12) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f1.setName("idDetalle");
    // /workflow/escritorios/escritorio.jsp(217,12) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f1.setValue("#{tablaTarea.detalleTramite.idDetalleTramite}");
    int _jspx_eval_f_005fparam_005f1 = _jspx_th_f_005fparam_005f1.doStartTag();
    if (_jspx_th_f_005fparam_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f1);
    return false;
  }

  private boolean _jspx_meth_x_005fgraphicImage_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:graphicImage
    org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag _jspx_th_x_005fgraphicImage_005f2 = (org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag) _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.get(org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag.class);
    _jspx_th_x_005fgraphicImage_005f2.setPageContext(_jspx_page_context);
    _jspx_th_x_005fgraphicImage_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f2);
    // /workflow/escritorios/escritorio.jsp(218,12) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f2.setValue("/img/icon/editar.gif");
    // /workflow/escritorios/escritorio.jsp(218,12) name = title type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f2.setTitle("Ver Tarea.");
    // /workflow/escritorios/escritorio.jsp(218,12) name = border type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f2.setBorder("0");
    int _jspx_eval_x_005fgraphicImage_005f2 = _jspx_th_x_005fgraphicImage_005f2.doStartTag();
    if (_jspx_th_x_005fgraphicImage_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fpanelTabbedPane_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fpanelTabbedPane_005f1);
    // /workflow/escritorios/escritorio.jsp(246,5) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty EscritorioBean.tablaTramites || EscritorioBean.verLeyendaTramite==true}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t        ");
        if (_jspx_meth_x_005fpanelTab_005f3(_jspx_th_c_005fif_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
    return false;
  }

  private boolean _jspx_meth_x_005fpanelTab_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:panelTab
    org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag _jspx_th_x_005fpanelTab_005f3 = (org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag) _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid.get(org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag.class);
    _jspx_th_x_005fpanelTab_005f3.setPageContext(_jspx_page_context);
    _jspx_th_x_005fpanelTab_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f2);
    // /workflow/escritorios/escritorio.jsp(247,11) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTab_005f3.setId("tabSuper");
    // /workflow/escritorios/escritorio.jsp(247,11) name = label type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTab_005f3.setLabel("Tramites Supervisados");
    int _jspx_eval_x_005fpanelTab_005f3 = _jspx_th_x_005fpanelTab_005f3.doStartTag();
    if (_jspx_eval_x_005fpanelTab_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fpanelTab_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fpanelTab_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fpanelTab_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        if (_jspx_meth_h_005fpanelGrid_005f4(_jspx_th_x_005fpanelTab_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fpanelTab_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fpanelTab_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fpanelTab_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid.reuse(_jspx_th_x_005fpanelTab_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid.reuse(_jspx_th_x_005fpanelTab_005f3);
    return false;
  }

  private boolean _jspx_meth_h_005fpanelGrid_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fpanelTab_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:panelGrid
    org.apache.myfaces.taglib.html.HtmlPanelGridTag _jspx_th_h_005fpanelGrid_005f4 = (org.apache.myfaces.taglib.html.HtmlPanelGridTag) _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns.get(org.apache.myfaces.taglib.html.HtmlPanelGridTag.class);
    _jspx_th_h_005fpanelGrid_005f4.setPageContext(_jspx_page_context);
    _jspx_th_h_005fpanelGrid_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fpanelTab_005f3);
    // /workflow/escritorios/escritorio.jsp(248,6) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGrid_005f4.setId("panelTramites");
    // /workflow/escritorios/escritorio.jsp(248,6) name = columns type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005fpanelGrid_005f4.setColumns("1");
    int _jspx_eval_h_005fpanelGrid_005f4 = _jspx_th_h_005fpanelGrid_005f4.doStartTag();
    if (_jspx_eval_h_005fpanelGrid_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_005fpanelGrid_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_005fpanelGrid_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_005fpanelGrid_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_h_005fpanelGroup_005f4(_jspx_th_h_005fpanelGrid_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fif_005f3(_jspx_th_h_005fpanelGrid_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fif_005f4(_jspx_th_h_005fpanelGrid_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_c_005fif_005f5(_jspx_th_h_005fpanelGrid_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_h_005fpanelGrid_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_005fpanelGrid_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_h_005fpanelGrid_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns.reuse(_jspx_th_h_005fpanelGrid_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005fpanelGrid_0026_005fid_005fcolumns.reuse(_jspx_th_h_005fpanelGrid_005f4);
    return false;
  }

  private boolean _jspx_meth_h_005fpanelGroup_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGrid_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:panelGroup
    org.apache.myfaces.taglib.html.HtmlPanelGroupTag _jspx_th_h_005fpanelGroup_005f4 = (org.apache.myfaces.taglib.html.HtmlPanelGroupTag) _005fjspx_005ftagPool_005fh_005fpanelGroup.get(org.apache.myfaces.taglib.html.HtmlPanelGroupTag.class);
    _jspx_th_h_005fpanelGroup_005f4.setPageContext(_jspx_page_context);
    _jspx_th_h_005fpanelGroup_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGrid_005f4);
    int _jspx_eval_h_005fpanelGroup_005f4 = _jspx_th_h_005fpanelGroup_005f4.doStartTag();
    if (_jspx_eval_h_005fpanelGroup_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_005fpanelGroup_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_005fpanelGroup_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_005fpanelGroup_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcommandLink_005f3(_jspx_th_h_005fpanelGroup_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_f_005fverbatim_005f2(_jspx_th_h_005fpanelGroup_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcommandLink_005f4(_jspx_th_h_005fpanelGroup_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_f_005fverbatim_005f3(_jspx_th_h_005fpanelGroup_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcommandLink_005f5(_jspx_th_h_005fpanelGroup_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_f_005fverbatim_005f4(_jspx_th_h_005fpanelGroup_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcommandLink_005f6(_jspx_th_h_005fpanelGroup_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_f_005fverbatim_005f5(_jspx_th_h_005fpanelGroup_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcommandLink_005f7(_jspx_th_h_005fpanelGroup_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_f_005fverbatim_005f6(_jspx_th_h_005fpanelGroup_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcommandLink_005f8(_jspx_th_h_005fpanelGroup_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_h_005fpanelGroup_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_005fpanelGroup_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_h_005fpanelGroup_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005fpanelGroup.reuse(_jspx_th_h_005fpanelGroup_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005fpanelGroup.reuse(_jspx_th_h_005fpanelGroup_005f4);
    return false;
  }

  private boolean _jspx_meth_x_005fcommandLink_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:commandLink
    org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag _jspx_th_x_005fcommandLink_005f3 = (org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag) _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.get(org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag.class);
    _jspx_th_x_005fcommandLink_005f3.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcommandLink_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f4);
    // /workflow/escritorios/escritorio.jsp(250,8) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f3.setId("verNuevosLink");
    // /workflow/escritorios/escritorio.jsp(250,8) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f3.setAction("#{EscritorioBean.filtrarTramites}");
    // /workflow/escritorios/escritorio.jsp(250,8) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f3.setValue("Nuevos");
    int _jspx_eval_x_005fcommandLink_005f3 = _jspx_th_x_005fcommandLink_005f3.doStartTag();
    if (_jspx_eval_x_005fcommandLink_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_f_005fparam_005f2(_jspx_th_x_005fcommandLink_005f3, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fgraphicImage_005f3(_jspx_th_x_005fcommandLink_005f3, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fcommandLink_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f3);
    return false;
  }

  private boolean _jspx_meth_f_005fparam_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:param
    org.apache.myfaces.taglib.core.ParamTag _jspx_th_f_005fparam_005f2 = (org.apache.myfaces.taglib.core.ParamTag) _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.get(org.apache.myfaces.taglib.core.ParamTag.class);
    _jspx_th_f_005fparam_005f2.setPageContext(_jspx_page_context);
    _jspx_th_f_005fparam_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f3);
    // /workflow/escritorios/escritorio.jsp(251,9) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f2.setId("idEstadoN");
    // /workflow/escritorios/escritorio.jsp(251,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f2.setName("idEstado");
    // /workflow/escritorios/escritorio.jsp(251,9) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f2.setValue("1");
    int _jspx_eval_f_005fparam_005f2 = _jspx_th_f_005fparam_005f2.doStartTag();
    if (_jspx_th_f_005fparam_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f2);
    return false;
  }

  private boolean _jspx_meth_x_005fgraphicImage_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:graphicImage
    org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag _jspx_th_x_005fgraphicImage_005f3 = (org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag) _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.get(org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag.class);
    _jspx_th_x_005fgraphicImage_005f3.setPageContext(_jspx_page_context);
    _jspx_th_x_005fgraphicImage_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f3);
    // /workflow/escritorios/escritorio.jsp(252,9) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f3.setValue("/img/icon/status_new.gif");
    // /workflow/escritorios/escritorio.jsp(252,9) name = title type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f3.setTitle("Ver Nuevos.");
    // /workflow/escritorios/escritorio.jsp(252,9) name = border type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f3.setBorder("0");
    int _jspx_eval_x_005fgraphicImage_005f3 = _jspx_th_x_005fgraphicImage_005f3.doStartTag();
    if (_jspx_th_x_005fgraphicImage_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f3);
    return false;
  }

  private boolean _jspx_meth_f_005fverbatim_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:verbatim
    org.apache.myfaces.taglib.core.VerbatimTag _jspx_th_f_005fverbatim_005f2 = (org.apache.myfaces.taglib.core.VerbatimTag) _005fjspx_005ftagPool_005ff_005fverbatim.get(org.apache.myfaces.taglib.core.VerbatimTag.class);
    _jspx_th_f_005fverbatim_005f2.setPageContext(_jspx_page_context);
    _jspx_th_f_005fverbatim_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f4);
    int _jspx_eval_f_005fverbatim_005f2 = _jspx_th_f_005fverbatim_005f2.doStartTag();
    if (_jspx_eval_f_005fverbatim_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_f_005fverbatim_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_f_005fverbatim_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_f_005fverbatim_005f2.doInitBody();
      }
      do {
        out.write("&nbsp;&nbsp;&nbsp;");
        int evalDoAfterBody = _jspx_th_f_005fverbatim_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_f_005fverbatim_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_f_005fverbatim_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f2);
    return false;
  }

  private boolean _jspx_meth_x_005fcommandLink_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:commandLink
    org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag _jspx_th_x_005fcommandLink_005f4 = (org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag) _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.get(org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag.class);
    _jspx_th_x_005fcommandLink_005f4.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcommandLink_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f4);
    // /workflow/escritorios/escritorio.jsp(255,8) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f4.setId("verIniciadosLink");
    // /workflow/escritorios/escritorio.jsp(255,8) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f4.setAction("#{EscritorioBean.filtrarTramites}");
    // /workflow/escritorios/escritorio.jsp(255,8) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f4.setValue("Iniciados");
    int _jspx_eval_x_005fcommandLink_005f4 = _jspx_th_x_005fcommandLink_005f4.doStartTag();
    if (_jspx_eval_x_005fcommandLink_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_f_005fparam_005f3(_jspx_th_x_005fcommandLink_005f4, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fgraphicImage_005f4(_jspx_th_x_005fcommandLink_005f4, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fcommandLink_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f4);
    return false;
  }

  private boolean _jspx_meth_f_005fparam_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:param
    org.apache.myfaces.taglib.core.ParamTag _jspx_th_f_005fparam_005f3 = (org.apache.myfaces.taglib.core.ParamTag) _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.get(org.apache.myfaces.taglib.core.ParamTag.class);
    _jspx_th_f_005fparam_005f3.setPageContext(_jspx_page_context);
    _jspx_th_f_005fparam_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f4);
    // /workflow/escritorios/escritorio.jsp(256,9) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f3.setId("idEstadoI");
    // /workflow/escritorios/escritorio.jsp(256,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f3.setName("idEstado");
    // /workflow/escritorios/escritorio.jsp(256,9) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f3.setValue("2");
    int _jspx_eval_f_005fparam_005f3 = _jspx_th_f_005fparam_005f3.doStartTag();
    if (_jspx_th_f_005fparam_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f3);
    return false;
  }

  private boolean _jspx_meth_x_005fgraphicImage_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:graphicImage
    org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag _jspx_th_x_005fgraphicImage_005f4 = (org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag) _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.get(org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag.class);
    _jspx_th_x_005fgraphicImage_005f4.setPageContext(_jspx_page_context);
    _jspx_th_x_005fgraphicImage_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f4);
    // /workflow/escritorios/escritorio.jsp(257,9) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f4.setValue("/img/icon/status_new.gif");
    // /workflow/escritorios/escritorio.jsp(257,9) name = title type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f4.setTitle("Ver Iniciados.");
    // /workflow/escritorios/escritorio.jsp(257,9) name = border type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f4.setBorder("0");
    int _jspx_eval_x_005fgraphicImage_005f4 = _jspx_th_x_005fgraphicImage_005f4.doStartTag();
    if (_jspx_th_x_005fgraphicImage_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f4);
    return false;
  }

  private boolean _jspx_meth_f_005fverbatim_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:verbatim
    org.apache.myfaces.taglib.core.VerbatimTag _jspx_th_f_005fverbatim_005f3 = (org.apache.myfaces.taglib.core.VerbatimTag) _005fjspx_005ftagPool_005ff_005fverbatim.get(org.apache.myfaces.taglib.core.VerbatimTag.class);
    _jspx_th_f_005fverbatim_005f3.setPageContext(_jspx_page_context);
    _jspx_th_f_005fverbatim_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f4);
    int _jspx_eval_f_005fverbatim_005f3 = _jspx_th_f_005fverbatim_005f3.doStartTag();
    if (_jspx_eval_f_005fverbatim_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_f_005fverbatim_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_f_005fverbatim_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_f_005fverbatim_005f3.doInitBody();
      }
      do {
        out.write("&nbsp;&nbsp;&nbsp;");
        int evalDoAfterBody = _jspx_th_f_005fverbatim_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_f_005fverbatim_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_f_005fverbatim_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f3);
    return false;
  }

  private boolean _jspx_meth_x_005fcommandLink_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:commandLink
    org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag _jspx_th_x_005fcommandLink_005f5 = (org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag) _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.get(org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag.class);
    _jspx_th_x_005fcommandLink_005f5.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcommandLink_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f4);
    // /workflow/escritorios/escritorio.jsp(260,8) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f5.setId("verEnProcesoLink");
    // /workflow/escritorios/escritorio.jsp(260,8) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f5.setAction("#{EscritorioBean.filtrarTramites}");
    // /workflow/escritorios/escritorio.jsp(260,8) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f5.setValue("En Proceso");
    int _jspx_eval_x_005fcommandLink_005f5 = _jspx_th_x_005fcommandLink_005f5.doStartTag();
    if (_jspx_eval_x_005fcommandLink_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_f_005fparam_005f4(_jspx_th_x_005fcommandLink_005f5, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fgraphicImage_005f5(_jspx_th_x_005fcommandLink_005f5, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fcommandLink_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f5);
    return false;
  }

  private boolean _jspx_meth_f_005fparam_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:param
    org.apache.myfaces.taglib.core.ParamTag _jspx_th_f_005fparam_005f4 = (org.apache.myfaces.taglib.core.ParamTag) _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.get(org.apache.myfaces.taglib.core.ParamTag.class);
    _jspx_th_f_005fparam_005f4.setPageContext(_jspx_page_context);
    _jspx_th_f_005fparam_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f5);
    // /workflow/escritorios/escritorio.jsp(261,9) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f4.setId("idEstadoP");
    // /workflow/escritorios/escritorio.jsp(261,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f4.setName("idEstado");
    // /workflow/escritorios/escritorio.jsp(261,9) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f4.setValue("3");
    int _jspx_eval_f_005fparam_005f4 = _jspx_th_f_005fparam_005f4.doStartTag();
    if (_jspx_th_f_005fparam_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f4);
    return false;
  }

  private boolean _jspx_meth_x_005fgraphicImage_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:graphicImage
    org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag _jspx_th_x_005fgraphicImage_005f5 = (org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag) _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.get(org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag.class);
    _jspx_th_x_005fgraphicImage_005f5.setPageContext(_jspx_page_context);
    _jspx_th_x_005fgraphicImage_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f5);
    // /workflow/escritorios/escritorio.jsp(262,9) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f5.setValue("/img/icon/status_in_progress.gif");
    // /workflow/escritorios/escritorio.jsp(262,9) name = title type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f5.setTitle("Ver En Proceso.");
    // /workflow/escritorios/escritorio.jsp(262,9) name = border type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f5.setBorder("0");
    int _jspx_eval_x_005fgraphicImage_005f5 = _jspx_th_x_005fgraphicImage_005f5.doStartTag();
    if (_jspx_th_x_005fgraphicImage_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f5);
    return false;
  }

  private boolean _jspx_meth_f_005fverbatim_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:verbatim
    org.apache.myfaces.taglib.core.VerbatimTag _jspx_th_f_005fverbatim_005f4 = (org.apache.myfaces.taglib.core.VerbatimTag) _005fjspx_005ftagPool_005ff_005fverbatim.get(org.apache.myfaces.taglib.core.VerbatimTag.class);
    _jspx_th_f_005fverbatim_005f4.setPageContext(_jspx_page_context);
    _jspx_th_f_005fverbatim_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f4);
    int _jspx_eval_f_005fverbatim_005f4 = _jspx_th_f_005fverbatim_005f4.doStartTag();
    if (_jspx_eval_f_005fverbatim_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_f_005fverbatim_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_f_005fverbatim_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_f_005fverbatim_005f4.doInitBody();
      }
      do {
        out.write("&nbsp;&nbsp;&nbsp;");
        int evalDoAfterBody = _jspx_th_f_005fverbatim_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_f_005fverbatim_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_f_005fverbatim_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f4);
    return false;
  }

  private boolean _jspx_meth_x_005fcommandLink_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:commandLink
    org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag _jspx_th_x_005fcommandLink_005f6 = (org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag) _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.get(org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag.class);
    _jspx_th_x_005fcommandLink_005f6.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcommandLink_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f4);
    // /workflow/escritorios/escritorio.jsp(265,8) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f6.setId("verCanceladosLink");
    // /workflow/escritorios/escritorio.jsp(265,8) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f6.setAction("#{EscritorioBean.filtrarTramites}");
    // /workflow/escritorios/escritorio.jsp(265,8) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f6.setValue("Cancelados");
    int _jspx_eval_x_005fcommandLink_005f6 = _jspx_th_x_005fcommandLink_005f6.doStartTag();
    if (_jspx_eval_x_005fcommandLink_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_f_005fparam_005f5(_jspx_th_x_005fcommandLink_005f6, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fgraphicImage_005f6(_jspx_th_x_005fcommandLink_005f6, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fcommandLink_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f6);
    return false;
  }

  private boolean _jspx_meth_f_005fparam_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:param
    org.apache.myfaces.taglib.core.ParamTag _jspx_th_f_005fparam_005f5 = (org.apache.myfaces.taglib.core.ParamTag) _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.get(org.apache.myfaces.taglib.core.ParamTag.class);
    _jspx_th_f_005fparam_005f5.setPageContext(_jspx_page_context);
    _jspx_th_f_005fparam_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f6);
    // /workflow/escritorios/escritorio.jsp(266,9) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f5.setId("idEstadoC");
    // /workflow/escritorios/escritorio.jsp(266,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f5.setName("idEstado");
    // /workflow/escritorios/escritorio.jsp(266,9) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f5.setValue("4");
    int _jspx_eval_f_005fparam_005f5 = _jspx_th_f_005fparam_005f5.doStartTag();
    if (_jspx_th_f_005fparam_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f5);
    return false;
  }

  private boolean _jspx_meth_x_005fgraphicImage_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:graphicImage
    org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag _jspx_th_x_005fgraphicImage_005f6 = (org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag) _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.get(org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag.class);
    _jspx_th_x_005fgraphicImage_005f6.setPageContext(_jspx_page_context);
    _jspx_th_x_005fgraphicImage_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f6);
    // /workflow/escritorios/escritorio.jsp(267,9) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f6.setValue("/img/icon/status_stopped.gif");
    // /workflow/escritorios/escritorio.jsp(267,9) name = title type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f6.setTitle("Ver Cancelados.");
    // /workflow/escritorios/escritorio.jsp(267,9) name = border type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f6.setBorder("0");
    int _jspx_eval_x_005fgraphicImage_005f6 = _jspx_th_x_005fgraphicImage_005f6.doStartTag();
    if (_jspx_th_x_005fgraphicImage_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f6);
    return false;
  }

  private boolean _jspx_meth_f_005fverbatim_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:verbatim
    org.apache.myfaces.taglib.core.VerbatimTag _jspx_th_f_005fverbatim_005f5 = (org.apache.myfaces.taglib.core.VerbatimTag) _005fjspx_005ftagPool_005ff_005fverbatim.get(org.apache.myfaces.taglib.core.VerbatimTag.class);
    _jspx_th_f_005fverbatim_005f5.setPageContext(_jspx_page_context);
    _jspx_th_f_005fverbatim_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f4);
    int _jspx_eval_f_005fverbatim_005f5 = _jspx_th_f_005fverbatim_005f5.doStartTag();
    if (_jspx_eval_f_005fverbatim_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_f_005fverbatim_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_f_005fverbatim_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_f_005fverbatim_005f5.doInitBody();
      }
      do {
        out.write("&nbsp;&nbsp;&nbsp;");
        int evalDoAfterBody = _jspx_th_f_005fverbatim_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_f_005fverbatim_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_f_005fverbatim_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f5);
    return false;
  }

  private boolean _jspx_meth_x_005fcommandLink_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:commandLink
    org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag _jspx_th_x_005fcommandLink_005f7 = (org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag) _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.get(org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag.class);
    _jspx_th_x_005fcommandLink_005f7.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcommandLink_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f4);
    // /workflow/escritorios/escritorio.jsp(270,8) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f7.setId("verFinalizadosLink");
    // /workflow/escritorios/escritorio.jsp(270,8) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f7.setAction("#{EscritorioBean.filtrarTramites}");
    // /workflow/escritorios/escritorio.jsp(270,8) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f7.setValue("Finalizados");
    int _jspx_eval_x_005fcommandLink_005f7 = _jspx_th_x_005fcommandLink_005f7.doStartTag();
    if (_jspx_eval_x_005fcommandLink_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_f_005fparam_005f6(_jspx_th_x_005fcommandLink_005f7, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fgraphicImage_005f7(_jspx_th_x_005fcommandLink_005f7, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fcommandLink_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f7);
    return false;
  }

  private boolean _jspx_meth_f_005fparam_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:param
    org.apache.myfaces.taglib.core.ParamTag _jspx_th_f_005fparam_005f6 = (org.apache.myfaces.taglib.core.ParamTag) _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.get(org.apache.myfaces.taglib.core.ParamTag.class);
    _jspx_th_f_005fparam_005f6.setPageContext(_jspx_page_context);
    _jspx_th_f_005fparam_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f7);
    // /workflow/escritorios/escritorio.jsp(271,9) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f6.setId("idEstadoF");
    // /workflow/escritorios/escritorio.jsp(271,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f6.setName("idEstado");
    // /workflow/escritorios/escritorio.jsp(271,9) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f6.setValue("6");
    int _jspx_eval_f_005fparam_005f6 = _jspx_th_f_005fparam_005f6.doStartTag();
    if (_jspx_th_f_005fparam_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f6);
    return false;
  }

  private boolean _jspx_meth_x_005fgraphicImage_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f7, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:graphicImage
    org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag _jspx_th_x_005fgraphicImage_005f7 = (org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag) _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.get(org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag.class);
    _jspx_th_x_005fgraphicImage_005f7.setPageContext(_jspx_page_context);
    _jspx_th_x_005fgraphicImage_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f7);
    // /workflow/escritorios/escritorio.jsp(272,9) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f7.setValue("/img/icon/status_finished.gif");
    // /workflow/escritorios/escritorio.jsp(272,9) name = title type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f7.setTitle("Ver Finalizados.");
    // /workflow/escritorios/escritorio.jsp(272,9) name = border type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f7.setBorder("0");
    int _jspx_eval_x_005fgraphicImage_005f7 = _jspx_th_x_005fgraphicImage_005f7.doStartTag();
    if (_jspx_th_x_005fgraphicImage_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f7);
    return false;
  }

  private boolean _jspx_meth_f_005fverbatim_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:verbatim
    org.apache.myfaces.taglib.core.VerbatimTag _jspx_th_f_005fverbatim_005f6 = (org.apache.myfaces.taglib.core.VerbatimTag) _005fjspx_005ftagPool_005ff_005fverbatim.get(org.apache.myfaces.taglib.core.VerbatimTag.class);
    _jspx_th_f_005fverbatim_005f6.setPageContext(_jspx_page_context);
    _jspx_th_f_005fverbatim_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f4);
    int _jspx_eval_f_005fverbatim_005f6 = _jspx_th_f_005fverbatim_005f6.doStartTag();
    if (_jspx_eval_f_005fverbatim_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_f_005fverbatim_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_f_005fverbatim_005f6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_f_005fverbatim_005f6.doInitBody();
      }
      do {
        out.write("&nbsp;&nbsp;&nbsp;");
        int evalDoAfterBody = _jspx_th_f_005fverbatim_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_f_005fverbatim_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_f_005fverbatim_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f6);
    return false;
  }

  private boolean _jspx_meth_x_005fcommandLink_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:commandLink
    org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag _jspx_th_x_005fcommandLink_005f8 = (org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag) _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.get(org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag.class);
    _jspx_th_x_005fcommandLink_005f8.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcommandLink_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f4);
    // /workflow/escritorios/escritorio.jsp(275,8) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f8.setId("verTodosLink");
    // /workflow/escritorios/escritorio.jsp(275,8) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f8.setAction("#{EscritorioBean.filtrarTramites}");
    // /workflow/escritorios/escritorio.jsp(275,8) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f8.setValue("Todos");
    int _jspx_eval_x_005fcommandLink_005f8 = _jspx_th_x_005fcommandLink_005f8.doStartTag();
    if (_jspx_eval_x_005fcommandLink_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_f_005fparam_005f7(_jspx_th_x_005fcommandLink_005f8, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fcommandLink_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fvalue_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f8);
    return false;
  }

  private boolean _jspx_meth_f_005fparam_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f8, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:param
    org.apache.myfaces.taglib.core.ParamTag _jspx_th_f_005fparam_005f7 = (org.apache.myfaces.taglib.core.ParamTag) _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.get(org.apache.myfaces.taglib.core.ParamTag.class);
    _jspx_th_f_005fparam_005f7.setPageContext(_jspx_page_context);
    _jspx_th_f_005fparam_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f8);
    // /workflow/escritorios/escritorio.jsp(276,9) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f7.setId("idEstadoT");
    // /workflow/escritorios/escritorio.jsp(276,9) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f7.setName("idEstado");
    // /workflow/escritorios/escritorio.jsp(276,9) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f7.setValue("0");
    int _jspx_eval_f_005fparam_005f7 = _jspx_th_f_005fparam_005f7.doStartTag();
    if (_jspx_th_f_005fparam_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f7);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGrid_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGrid_005f4);
    // /workflow/escritorios/escritorio.jsp(279,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${EscritorioBean.verLeyendaTramite!=false}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
    if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_h_005foutputText_005f24(_jspx_th_c_005fif_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f24(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f24 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f24.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f3);
    // /workflow/escritorios/escritorio.jsp(280,8) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f24.setId("listVacia");
    // /workflow/escritorios/escritorio.jsp(280,8) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f24.setValue("Lista vacia");
    // /workflow/escritorios/escritorio.jsp(280,8) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f24.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f24 = _jspx_th_h_005foutputText_005f24.doStartTag();
    if (_jspx_th_h_005foutputText_005f24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f24);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f24);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGrid_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f4 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGrid_005f4);
    // /workflow/escritorios/escritorio.jsp(282,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${EscritorioBean.verLeyendaTramite!=true}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f4 = _jspx_th_c_005fif_005f4.doStartTag();
    if (_jspx_eval_c_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fdiv_005f2(_jspx_th_c_005fif_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
    return false;
  }

  private boolean _jspx_meth_x_005fdiv_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:div
    org.apache.myfaces.custom.div.DivTag _jspx_th_x_005fdiv_005f2 = (org.apache.myfaces.custom.div.DivTag) _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle_005fid.get(org.apache.myfaces.custom.div.DivTag.class);
    _jspx_th_x_005fdiv_005f2.setPageContext(_jspx_page_context);
    _jspx_th_x_005fdiv_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f4);
    // /workflow/escritorios/escritorio.jsp(283,7) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdiv_005f2.setId("divTramite");
    // /workflow/escritorios/escritorio.jsp(283,7) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdiv_005f2.setStyle("OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 875px; HEIGHT: 370px; border: 1px; margin-left: auto; margin-right: auto;");
    int _jspx_eval_x_005fdiv_005f2 = _jspx_th_x_005fdiv_005f2.doStartTag();
    if (_jspx_eval_x_005fdiv_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fdataTable_005f3(_jspx_th_x_005fdiv_005f2, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fdiv_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle_005fid.reuse(_jspx_th_x_005fdiv_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle_005fid.reuse(_jspx_th_x_005fdiv_005f2);
    return false;
  }

  private boolean _jspx_meth_x_005fdataTable_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdiv_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:dataTable
    org.apache.myfaces.taglib.html.ext.HtmlDataTableTag _jspx_th_x_005fdataTable_005f3 = (org.apache.myfaces.taglib.html.ext.HtmlDataTableTag) _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses.get(org.apache.myfaces.taglib.html.ext.HtmlDataTableTag.class);
    _jspx_th_x_005fdataTable_005f3.setPageContext(_jspx_page_context);
    _jspx_th_x_005fdataTable_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdiv_005f2);
    // /workflow/escritorios/escritorio.jsp(284,9) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f3.setValue("#{EscritorioBean.tablaTramites}");
    // /workflow/escritorios/escritorio.jsp(284,9) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f3.setId("tablaTramites");
    // /workflow/escritorios/escritorio.jsp(284,9) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f3.setStyleClass("standardTable");
    // /workflow/escritorios/escritorio.jsp(284,9) name = headerClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f3.setHeaderClass("standardTable_Header");
    // /workflow/escritorios/escritorio.jsp(284,9) name = footerClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f3.setFooterClass("standardTable_Header");
    // /workflow/escritorios/escritorio.jsp(284,9) name = rowClasses type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f3.setRowClasses("standardTable_Row1,standardTable_Row2");
    // /workflow/escritorios/escritorio.jsp(284,9) name = sortable type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f3.setSortable("true");
    // /workflow/escritorios/escritorio.jsp(284,9) name = columnClasses type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f3.setColumnClasses("standardTable_Column,standardTable_ColumnCentered,standardTable_Column");
    // /workflow/escritorios/escritorio.jsp(284,9) name = var type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f3.setVar("tablaTramite");
    // /workflow/escritorios/escritorio.jsp(284,9) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f3.setStyle(" width : 855px;");
    int _jspx_eval_x_005fdataTable_005f3 = _jspx_th_x_005fdataTable_005f3.doStartTag();
    if (_jspx_eval_x_005fdataTable_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fdataTable_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fdataTable_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fdataTable_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t    \r\n");
        out.write("\t\t\t\t                        ");
        if (_jspx_meth_x_005fcolumn_005f13(_jspx_th_x_005fdataTable_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t                        \r\n");
        out.write("\t\t\t\t                        ");
        if (_jspx_meth_x_005fcolumn_005f14(_jspx_th_x_005fdataTable_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                        \r\n");
        out.write("\t\t\t\t                        ");
        if (_jspx_meth_x_005fcolumn_005f15(_jspx_th_x_005fdataTable_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t                        ");
        out.write("\r\n");
        out.write("\t\t            \t\t            ");
        if (_jspx_meth_x_005fcolumn_005f16(_jspx_th_x_005fdataTable_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t            \t\t            ");
        if (_jspx_meth_x_005fcolumn_005f17(_jspx_th_x_005fdataTable_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t            \t\t            ");
        if (_jspx_meth_x_005fcolumn_005f18(_jspx_th_x_005fdataTable_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t            \t\t            ");
        if (_jspx_meth_x_005fcolumn_005f19(_jspx_th_x_005fdataTable_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t            \t\t            ");
        if (_jspx_meth_x_005fcolumn_005f20(_jspx_th_x_005fdataTable_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcolumn_005f21(_jspx_th_x_005fdataTable_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fdataTable_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fdataTable_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fdataTable_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses.reuse(_jspx_th_x_005fdataTable_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses.reuse(_jspx_th_x_005fdataTable_005f3);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f13 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f13.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f3);
    int _jspx_eval_x_005fcolumn_005f13 = _jspx_th_x_005fcolumn_005f13.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f13 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f13.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f13.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f13(_jspx_th_x_005fcolumn_005f13, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f26(_jspx_th_x_005fcolumn_005f13, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f13.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f13 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f13);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f13, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f13 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f13.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f13);
    // /workflow/escritorios/escritorio.jsp(294,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f13.setName("header");
    int _jspx_eval_f_005ffacet_005f13 = _jspx_th_f_005ffacet_005f13.doStartTag();
    if (_jspx_eval_f_005ffacet_005f13 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f25(_jspx_th_f_005ffacet_005f13, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f13.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f13);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f25(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f13, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f25 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f25.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f13);
    // /workflow/escritorios/escritorio.jsp(295,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f25.setValue("Nro.");
    // /workflow/escritorios/escritorio.jsp(295,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f25.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(295,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f25.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f25 = _jspx_th_h_005foutputText_005f25.doStartTag();
    if (_jspx_th_h_005foutputText_005f25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f25);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f25);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f26(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f13, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f26 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f26.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f13);
    // /workflow/escritorios/escritorio.jsp(297,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f26.setValue("#{tablaTramite.tramite.idTramite}");
    // /workflow/escritorios/escritorio.jsp(297,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f26.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f26 = _jspx_th_h_005foutputText_005f26.doStartTag();
    if (_jspx_th_h_005foutputText_005f26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f26);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f26);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f14(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f14 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f14.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f3);
    int _jspx_eval_x_005fcolumn_005f14 = _jspx_th_x_005fcolumn_005f14.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f14 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f14.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f14.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f14(_jspx_th_x_005fcolumn_005f14, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f28(_jspx_th_x_005fcolumn_005f14, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f14.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f14 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f14);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f14(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f14 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f14.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f14);
    // /workflow/escritorios/escritorio.jsp(301,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f14.setName("header");
    int _jspx_eval_f_005ffacet_005f14 = _jspx_th_f_005ffacet_005f14.doStartTag();
    if (_jspx_eval_f_005ffacet_005f14 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f27(_jspx_th_f_005ffacet_005f14, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f14.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f14);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f27(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f27 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f27.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f14);
    // /workflow/escritorios/escritorio.jsp(302,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f27.setValue("Proceso");
    // /workflow/escritorios/escritorio.jsp(302,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f27.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(302,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f27.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f27 = _jspx_th_h_005foutputText_005f27.doStartTag();
    if (_jspx_th_h_005foutputText_005f27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f27);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f27);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f28(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f14, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f28 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f28.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f14);
    // /workflow/escritorios/escritorio.jsp(304,35) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f28.setId("outputProceso");
    // /workflow/escritorios/escritorio.jsp(304,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f28.setValue("#{tablaTramite.tramite.proceso.titulo}");
    // /workflow/escritorios/escritorio.jsp(304,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f28.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f28 = _jspx_th_h_005foutputText_005f28.doStartTag();
    if (_jspx_th_h_005foutputText_005f28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f28);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f28);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f15(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f15 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f15.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f3);
    int _jspx_eval_x_005fcolumn_005f15 = _jspx_th_x_005fcolumn_005f15.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f15 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f15.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f15.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f15(_jspx_th_x_005fcolumn_005f15, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f30(_jspx_th_x_005fcolumn_005f15, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f15.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f15 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f15);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f15(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f15, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f15 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f15.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f15);
    // /workflow/escritorios/escritorio.jsp(308,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f15.setName("header");
    int _jspx_eval_f_005ffacet_005f15 = _jspx_th_f_005ffacet_005f15.doStartTag();
    if (_jspx_eval_f_005ffacet_005f15 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f29(_jspx_th_f_005ffacet_005f15, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f15.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f15);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f29(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f15, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f29 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f29.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f15);
    // /workflow/escritorios/escritorio.jsp(309,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f29.setValue("Tramite");
    // /workflow/escritorios/escritorio.jsp(309,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f29.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(309,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f29.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f29 = _jspx_th_h_005foutputText_005f29.doStartTag();
    if (_jspx_th_h_005foutputText_005f29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f29);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f29);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f30(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f15, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f30 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f30.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f15);
    // /workflow/escritorios/escritorio.jsp(311,35) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f30.setId("outputTramite");
    // /workflow/escritorios/escritorio.jsp(311,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f30.setValue("#{tablaTramite.nombreTramite}");
    // /workflow/escritorios/escritorio.jsp(311,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f30.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f30 = _jspx_th_h_005foutputText_005f30.doStartTag();
    if (_jspx_th_h_005foutputText_005f30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f30);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f30);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f16(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f16 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f16.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f3);
    int _jspx_eval_x_005fcolumn_005f16 = _jspx_th_x_005fcolumn_005f16.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f16 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f16.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f16.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        if (_jspx_meth_f_005ffacet_005f16(_jspx_th_x_005fcolumn_005f16, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t                \t\t                ");
        if (_jspx_meth_h_005foutputText_005f32(_jspx_th_x_005fcolumn_005f16, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f16.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f16 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f16);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f16);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f16(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f16 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f16.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f16);
    // /workflow/escritorios/escritorio.jsp(322,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f16.setName("header");
    int _jspx_eval_f_005ffacet_005f16 = _jspx_th_f_005ffacet_005f16.doStartTag();
    if (_jspx_eval_f_005ffacet_005f16 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f31(_jspx_th_f_005ffacet_005f16, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f16.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f16);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f16);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f31(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f31 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f31.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f16);
    // /workflow/escritorios/escritorio.jsp(323,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f31.setValue("Estado");
    // /workflow/escritorios/escritorio.jsp(323,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f31.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(323,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f31.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f31 = _jspx_th_h_005foutputText_005f31.doStartTag();
    if (_jspx_th_h_005foutputText_005f31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f31);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f31);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f32(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f16, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f32 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f32.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f16);
    // /workflow/escritorios/escritorio.jsp(325,35) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f32.setId("outputEstado");
    // /workflow/escritorios/escritorio.jsp(325,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f32.setValue("#{tablaTramite.tramite.estado.descripcion}");
    // /workflow/escritorios/escritorio.jsp(325,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f32.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f32 = _jspx_th_h_005foutputText_005f32.doStartTag();
    if (_jspx_th_h_005foutputText_005f32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f32);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f32);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f17(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f17 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f17.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f3);
    int _jspx_eval_x_005fcolumn_005f17 = _jspx_th_x_005fcolumn_005f17.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f17 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f17.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f17.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        if (_jspx_meth_f_005ffacet_005f17(_jspx_th_x_005fcolumn_005f17, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t                \t\t                ");
        if (_jspx_meth_h_005foutputText_005f34(_jspx_th_x_005fcolumn_005f17, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f17.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f17 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f17);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f17);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f17(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f17 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f17.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f17);
    // /workflow/escritorios/escritorio.jsp(329,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f17.setName("header");
    int _jspx_eval_f_005ffacet_005f17 = _jspx_th_f_005ffacet_005f17.doStartTag();
    if (_jspx_eval_f_005ffacet_005f17 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f33(_jspx_th_f_005ffacet_005f17, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f17.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f17);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f17);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f33(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f33 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f33.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f17);
    // /workflow/escritorios/escritorio.jsp(330,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f33.setValue("Inicio");
    // /workflow/escritorios/escritorio.jsp(330,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f33.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(330,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f33.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f33 = _jspx_th_h_005foutputText_005f33.doStartTag();
    if (_jspx_th_h_005foutputText_005f33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f33);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f33);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f34(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f17, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f34 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f34.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f17);
    // /workflow/escritorios/escritorio.jsp(332,35) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f34.setId("outputIni");
    // /workflow/escritorios/escritorio.jsp(332,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f34.setValue("#{tablaTramite.tramite.fechaInicio}");
    // /workflow/escritorios/escritorio.jsp(332,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f34.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f34 = _jspx_th_h_005foutputText_005f34.doStartTag();
    if (_jspx_th_h_005foutputText_005f34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f34);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f34);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f18(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f18 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f18.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f3);
    int _jspx_eval_x_005fcolumn_005f18 = _jspx_th_x_005fcolumn_005f18.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f18 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f18 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f18.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f18.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        if (_jspx_meth_f_005ffacet_005f18(_jspx_th_x_005fcolumn_005f18, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t                \t\t                ");
        if (_jspx_meth_h_005foutputText_005f36(_jspx_th_x_005fcolumn_005f18, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f18.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f18 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f18);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f18);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f18(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f18, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f18 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f18.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f18);
    // /workflow/escritorios/escritorio.jsp(336,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f18.setName("header");
    int _jspx_eval_f_005ffacet_005f18 = _jspx_th_f_005ffacet_005f18.doStartTag();
    if (_jspx_eval_f_005ffacet_005f18 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f35(_jspx_th_f_005ffacet_005f18, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f18.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f18);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f18);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f35(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f18, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f35 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f35.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f18);
    // /workflow/escritorios/escritorio.jsp(337,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f35.setValue("Inicio Real");
    // /workflow/escritorios/escritorio.jsp(337,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f35.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(337,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f35.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f35 = _jspx_th_h_005foutputText_005f35.doStartTag();
    if (_jspx_th_h_005foutputText_005f35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f35);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f35);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f36(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f18, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f36 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f36.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f18);
    // /workflow/escritorios/escritorio.jsp(339,35) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f36.setId("outputIniReal");
    // /workflow/escritorios/escritorio.jsp(339,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f36.setValue("#{tablaTramite.tramite.fechaInicioReal}");
    // /workflow/escritorios/escritorio.jsp(339,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f36.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f36 = _jspx_th_h_005foutputText_005f36.doStartTag();
    if (_jspx_th_h_005foutputText_005f36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f36);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f36);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f19(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f19 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f19.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f3);
    int _jspx_eval_x_005fcolumn_005f19 = _jspx_th_x_005fcolumn_005f19.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f19 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f19 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f19.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f19.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        if (_jspx_meth_f_005ffacet_005f19(_jspx_th_x_005fcolumn_005f19, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t                \t\t                ");
        if (_jspx_meth_h_005foutputText_005f38(_jspx_th_x_005fcolumn_005f19, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f19.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f19 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f19);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f19);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f19(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f19, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f19 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f19.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f19);
    // /workflow/escritorios/escritorio.jsp(343,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f19.setName("header");
    int _jspx_eval_f_005ffacet_005f19 = _jspx_th_f_005ffacet_005f19.doStartTag();
    if (_jspx_eval_f_005ffacet_005f19 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f37(_jspx_th_f_005ffacet_005f19, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f19.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f19);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f19);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f37(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f19, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f37 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f37.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f19);
    // /workflow/escritorios/escritorio.jsp(344,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f37.setValue("Fin");
    // /workflow/escritorios/escritorio.jsp(344,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f37.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(344,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f37.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f37 = _jspx_th_h_005foutputText_005f37.doStartTag();
    if (_jspx_th_h_005foutputText_005f37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f37);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f37);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f38(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f19, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f38 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f38.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f19);
    // /workflow/escritorios/escritorio.jsp(346,35) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f38.setId("outputFin");
    // /workflow/escritorios/escritorio.jsp(346,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f38.setValue("#{tablaTramite.tramite.fechaFin}");
    // /workflow/escritorios/escritorio.jsp(346,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f38.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f38 = _jspx_th_h_005foutputText_005f38.doStartTag();
    if (_jspx_th_h_005foutputText_005f38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f38);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f38);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f20(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f20 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f20.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f3);
    int _jspx_eval_x_005fcolumn_005f20 = _jspx_th_x_005fcolumn_005f20.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f20 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f20 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f20.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f20.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        if (_jspx_meth_f_005ffacet_005f20(_jspx_th_x_005fcolumn_005f20, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t                \t\t                ");
        if (_jspx_meth_h_005foutputText_005f40(_jspx_th_x_005fcolumn_005f20, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f20.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f20 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f20);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f20);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f20(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f20, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f20 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f20.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f20);
    // /workflow/escritorios/escritorio.jsp(350,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f20.setName("header");
    int _jspx_eval_f_005ffacet_005f20 = _jspx_th_f_005ffacet_005f20.doStartTag();
    if (_jspx_eval_f_005ffacet_005f20 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f39(_jspx_th_f_005ffacet_005f20, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f20.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f20);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f20);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f39(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f20, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f39 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f39.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f39.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f20);
    // /workflow/escritorios/escritorio.jsp(351,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f39.setValue("Fin Real");
    // /workflow/escritorios/escritorio.jsp(351,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f39.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(351,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f39.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f39 = _jspx_th_h_005foutputText_005f39.doStartTag();
    if (_jspx_th_h_005foutputText_005f39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f39);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f39);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f40(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f20, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f40 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f40.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f40.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f20);
    // /workflow/escritorios/escritorio.jsp(353,35) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f40.setId("outputFinReal");
    // /workflow/escritorios/escritorio.jsp(353,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f40.setValue("#{tablaTramite.tramite.fechaFinReal}");
    // /workflow/escritorios/escritorio.jsp(353,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f40.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f40 = _jspx_th_h_005foutputText_005f40.doStartTag();
    if (_jspx_th_h_005foutputText_005f40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f40);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fid_005fnobody.reuse(_jspx_th_h_005foutputText_005f40);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f21(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f21 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f21.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f3);
    int _jspx_eval_x_005fcolumn_005f21 = _jspx_th_x_005fcolumn_005f21.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f21 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f21 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f21.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f21.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcommandLink_005f9(_jspx_th_x_005fcolumn_005f21, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcommandLink_005f10(_jspx_th_x_005fcolumn_005f21, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcommandLink_005f11(_jspx_th_x_005fcolumn_005f21, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f21.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f21 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f21);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f21);
    return false;
  }

  private boolean _jspx_meth_x_005fcommandLink_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f21, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:commandLink
    org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag _jspx_th_x_005fcommandLink_005f9 = (org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag) _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.get(org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag.class);
    _jspx_th_x_005fcommandLink_005f9.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcommandLink_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f21);
    // /workflow/escritorios/escritorio.jsp(357,11) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f9.setId("verTareasLink");
    // /workflow/escritorios/escritorio.jsp(357,11) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f9.setAction("#{EscritorioBean.mostrarTareas}");
    int _jspx_eval_x_005fcommandLink_005f9 = _jspx_th_x_005fcommandLink_005f9.doStartTag();
    if (_jspx_eval_x_005fcommandLink_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_f_005fparam_005f8(_jspx_th_x_005fcommandLink_005f9, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fgraphicImage_005f8(_jspx_th_x_005fcommandLink_005f9, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fcommandLink_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f9);
    return false;
  }

  private boolean _jspx_meth_f_005fparam_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:param
    org.apache.myfaces.taglib.core.ParamTag _jspx_th_f_005fparam_005f8 = (org.apache.myfaces.taglib.core.ParamTag) _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.get(org.apache.myfaces.taglib.core.ParamTag.class);
    _jspx_th_f_005fparam_005f8.setPageContext(_jspx_page_context);
    _jspx_th_f_005fparam_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f9);
    // /workflow/escritorios/escritorio.jsp(358,12) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f8.setId("idTramiteV");
    // /workflow/escritorios/escritorio.jsp(358,12) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f8.setName("idTramite");
    // /workflow/escritorios/escritorio.jsp(358,12) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f8.setValue("#{tablaTramite.tramite.idTramite}");
    int _jspx_eval_f_005fparam_005f8 = _jspx_th_f_005fparam_005f8.doStartTag();
    if (_jspx_th_f_005fparam_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f8);
    return false;
  }

  private boolean _jspx_meth_x_005fgraphicImage_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:graphicImage
    org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag _jspx_th_x_005fgraphicImage_005f8 = (org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag) _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.get(org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag.class);
    _jspx_th_x_005fgraphicImage_005f8.setPageContext(_jspx_page_context);
    _jspx_th_x_005fgraphicImage_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f9);
    // /workflow/escritorios/escritorio.jsp(359,12) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f8.setValue("/img/icon/OrderView.gif");
    // /workflow/escritorios/escritorio.jsp(359,12) name = title type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f8.setTitle("Mostrar tareas.");
    // /workflow/escritorios/escritorio.jsp(359,12) name = border type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f8.setBorder("0");
    int _jspx_eval_x_005fgraphicImage_005f8 = _jspx_th_x_005fgraphicImage_005f8.doStartTag();
    if (_jspx_th_x_005fgraphicImage_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f8);
    return false;
  }

  private boolean _jspx_meth_x_005fcommandLink_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f21, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:commandLink
    org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag _jspx_th_x_005fcommandLink_005f10 = (org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag) _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.get(org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag.class);
    _jspx_th_x_005fcommandLink_005f10.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcommandLink_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f21);
    // /workflow/escritorios/escritorio.jsp(362,11) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f10.setAction("#{EscritorioBean.mostrarTramite}");
    // /workflow/escritorios/escritorio.jsp(362,11) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f10.setId("verTramiteLink");
    int _jspx_eval_x_005fcommandLink_005f10 = _jspx_th_x_005fcommandLink_005f10.doStartTag();
    if (_jspx_eval_x_005fcommandLink_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_f_005fparam_005f9(_jspx_th_x_005fcommandLink_005f10, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fgraphicImage_005f9(_jspx_th_x_005fcommandLink_005f10, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fcommandLink_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f10);
    return false;
  }

  private boolean _jspx_meth_f_005fparam_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:param
    org.apache.myfaces.taglib.core.ParamTag _jspx_th_f_005fparam_005f9 = (org.apache.myfaces.taglib.core.ParamTag) _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.get(org.apache.myfaces.taglib.core.ParamTag.class);
    _jspx_th_f_005fparam_005f9.setPageContext(_jspx_page_context);
    _jspx_th_f_005fparam_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f10);
    // /workflow/escritorios/escritorio.jsp(363,12) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f9.setId("idTramiteM");
    // /workflow/escritorios/escritorio.jsp(363,12) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f9.setName("idTramite");
    // /workflow/escritorios/escritorio.jsp(363,12) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f9.setValue("#{tablaTramite.tramite.idTramite}");
    int _jspx_eval_f_005fparam_005f9 = _jspx_th_f_005fparam_005f9.doStartTag();
    if (_jspx_th_f_005fparam_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f9);
    return false;
  }

  private boolean _jspx_meth_x_005fgraphicImage_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:graphicImage
    org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag _jspx_th_x_005fgraphicImage_005f9 = (org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag) _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.get(org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag.class);
    _jspx_th_x_005fgraphicImage_005f9.setPageContext(_jspx_page_context);
    _jspx_th_x_005fgraphicImage_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f10);
    // /workflow/escritorios/escritorio.jsp(364,12) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f9.setValue("/img/icon/editar.gif");
    // /workflow/escritorios/escritorio.jsp(364,12) name = title type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f9.setTitle("Historico del tramite.");
    // /workflow/escritorios/escritorio.jsp(364,12) name = border type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f9.setBorder("0");
    int _jspx_eval_x_005fgraphicImage_005f9 = _jspx_th_x_005fgraphicImage_005f9.doStartTag();
    if (_jspx_th_x_005fgraphicImage_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f9);
    return false;
  }

  private boolean _jspx_meth_x_005fcommandLink_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f21, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:commandLink
    org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag _jspx_th_x_005fcommandLink_005f11 = (org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag) _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.get(org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag.class);
    _jspx_th_x_005fcommandLink_005f11.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcommandLink_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f21);
    // /workflow/escritorios/escritorio.jsp(367,11) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f11.setAction("#{EscritorioBean.cancelarTramite}");
    // /workflow/escritorios/escritorio.jsp(367,11) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f11.setId("cancelarTramiteLink");
    int _jspx_eval_x_005fcommandLink_005f11 = _jspx_th_x_005fcommandLink_005f11.doStartTag();
    if (_jspx_eval_x_005fcommandLink_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_f_005fparam_005f10(_jspx_th_x_005fcommandLink_005f11, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fgraphicImage_005f10(_jspx_th_x_005fcommandLink_005f11, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fcommandLink_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f11);
    return false;
  }

  private boolean _jspx_meth_f_005fparam_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:param
    org.apache.myfaces.taglib.core.ParamTag _jspx_th_f_005fparam_005f10 = (org.apache.myfaces.taglib.core.ParamTag) _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.get(org.apache.myfaces.taglib.core.ParamTag.class);
    _jspx_th_f_005fparam_005f10.setPageContext(_jspx_page_context);
    _jspx_th_f_005fparam_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f11);
    // /workflow/escritorios/escritorio.jsp(368,12) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f10.setId("idTramiteC");
    // /workflow/escritorios/escritorio.jsp(368,12) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f10.setName("idTramite");
    // /workflow/escritorios/escritorio.jsp(368,12) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f10.setValue("#{tablaTramite.tramite.idTramite}");
    int _jspx_eval_f_005fparam_005f10 = _jspx_th_f_005fparam_005f10.doStartTag();
    if (_jspx_th_f_005fparam_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f10);
    return false;
  }

  private boolean _jspx_meth_x_005fgraphicImage_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f11, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:graphicImage
    org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag _jspx_th_x_005fgraphicImage_005f10 = (org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag) _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005frendered_005fonclick_005fborder_005fnobody.get(org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag.class);
    _jspx_th_x_005fgraphicImage_005f10.setPageContext(_jspx_page_context);
    _jspx_th_x_005fgraphicImage_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f11);
    // /workflow/escritorios/escritorio.jsp(369,12) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f10.setValue("/img/borrar.gif");
    // /workflow/escritorios/escritorio.jsp(369,12) name = title type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f10.setTitle("Cancelar el tramite.");
    // /workflow/escritorios/escritorio.jsp(369,12) name = border type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f10.setBorder("0");
    // /workflow/escritorios/escritorio.jsp(369,12) name = rendered type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f10.setRendered("#{tablaTramite.verCancelarTramite}");
    // /workflow/escritorios/escritorio.jsp(369,12) name = onclick type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f10.setOnclick("return confirm('¿Confirma la cancelacion del tramite?');");
    int _jspx_eval_x_005fgraphicImage_005f10 = _jspx_th_x_005fgraphicImage_005f10.doStartTag();
    if (_jspx_th_x_005fgraphicImage_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005frendered_005fonclick_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005frendered_005fonclick_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f10);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGrid_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f5 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGrid_005f4);
    // /workflow/escritorios/escritorio.jsp(383,7) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f5.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty EscritorioBean.tablaDetalleTareas}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f5 = _jspx_th_c_005fif_005f5.doStartTag();
    if (_jspx_eval_c_005fif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_h_005fpanelGroup_005f5(_jspx_th_c_005fif_005f5, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fdiv_005f3(_jspx_th_c_005fif_005f5, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f5);
    return false;
  }

  private boolean _jspx_meth_h_005fpanelGroup_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:panelGroup
    org.apache.myfaces.taglib.html.HtmlPanelGroupTag _jspx_th_h_005fpanelGroup_005f5 = (org.apache.myfaces.taglib.html.HtmlPanelGroupTag) _005fjspx_005ftagPool_005fh_005fpanelGroup.get(org.apache.myfaces.taglib.html.HtmlPanelGroupTag.class);
    _jspx_th_h_005fpanelGroup_005f5.setPageContext(_jspx_page_context);
    _jspx_th_h_005fpanelGroup_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f5);
    int _jspx_eval_h_005fpanelGroup_005f5 = _jspx_th_h_005fpanelGroup_005f5.doStartTag();
    if (_jspx_eval_h_005fpanelGroup_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_h_005fpanelGroup_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_h_005fpanelGroup_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_h_005fpanelGroup_005f5.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_h_005foutputText_005f41(_jspx_th_h_005fpanelGroup_005f5, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_h_005foutputText_005f42(_jspx_th_h_005fpanelGroup_005f5, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_h_005fpanelGroup_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_h_005fpanelGroup_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_h_005fpanelGroup_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005fpanelGroup.reuse(_jspx_th_h_005fpanelGroup_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005fpanelGroup.reuse(_jspx_th_h_005fpanelGroup_005f5);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f41(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f41 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f41.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f41.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f5);
    // /workflow/escritorios/escritorio.jsp(385,9) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f41.setValue("Tramite: ");
    // /workflow/escritorios/escritorio.jsp(385,9) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f41.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f41 = _jspx_th_h_005foutputText_005f41.doStartTag();
    if (_jspx_th_h_005foutputText_005f41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f41);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f41);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f42(javax.servlet.jsp.tagext.JspTag _jspx_th_h_005fpanelGroup_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f42 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f42.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f42.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_h_005fpanelGroup_005f5);
    // /workflow/escritorios/escritorio.jsp(386,9) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f42.setValue("#{EscritorioBean.tituloTramite}");
    // /workflow/escritorios/escritorio.jsp(386,9) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f42.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(386,9) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f42.setStyle("FONT-WEIGHT: bold; COLOR: blue;");
    int _jspx_eval_h_005foutputText_005f42 = _jspx_th_h_005foutputText_005f42.doStartTag();
    if (_jspx_th_h_005foutputText_005f42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f42);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f42);
    return false;
  }

  private boolean _jspx_meth_x_005fdiv_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:div
    org.apache.myfaces.custom.div.DivTag _jspx_th_x_005fdiv_005f3 = (org.apache.myfaces.custom.div.DivTag) _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle_005fid.get(org.apache.myfaces.custom.div.DivTag.class);
    _jspx_th_x_005fdiv_005f3.setPageContext(_jspx_page_context);
    _jspx_th_x_005fdiv_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f5);
    // /workflow/escritorios/escritorio.jsp(388,8) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdiv_005f3.setId("divDetTarea");
    // /workflow/escritorios/escritorio.jsp(388,8) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdiv_005f3.setStyle("OVERFLOW-Y: auto; OVERFLOW-X: auto; WIDTH: 875px; HEIGHT: 250px; border: 1px; margin-left: auto; margin-right: auto;");
    int _jspx_eval_x_005fdiv_005f3 = _jspx_th_x_005fdiv_005f3.doStartTag();
    if (_jspx_eval_x_005fdiv_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fdataTable_005f4(_jspx_th_x_005fdiv_005f3, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fdiv_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle_005fid.reuse(_jspx_th_x_005fdiv_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fdiv_0026_005fstyle_005fid.reuse(_jspx_th_x_005fdiv_005f3);
    return false;
  }

  private boolean _jspx_meth_x_005fdataTable_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdiv_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:dataTable
    org.apache.myfaces.taglib.html.ext.HtmlDataTableTag _jspx_th_x_005fdataTable_005f4 = (org.apache.myfaces.taglib.html.ext.HtmlDataTableTag) _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses.get(org.apache.myfaces.taglib.html.ext.HtmlDataTableTag.class);
    _jspx_th_x_005fdataTable_005f4.setPageContext(_jspx_page_context);
    _jspx_th_x_005fdataTable_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdiv_005f3);
    // /workflow/escritorios/escritorio.jsp(389,9) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f4.setValue("#{EscritorioBean.tablaDetalleTareas}");
    // /workflow/escritorios/escritorio.jsp(389,9) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f4.setId("tablaDetalleTareas");
    // /workflow/escritorios/escritorio.jsp(389,9) name = headerClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f4.setHeaderClass("standardTable_Header");
    // /workflow/escritorios/escritorio.jsp(389,9) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f4.setStyleClass("standardTable");
    // /workflow/escritorios/escritorio.jsp(389,9) name = footerClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f4.setFooterClass("standardTable_Header");
    // /workflow/escritorios/escritorio.jsp(389,9) name = rowClasses type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f4.setRowClasses("standardTable_Row1,standardTable_Row2");
    // /workflow/escritorios/escritorio.jsp(389,9) name = sortable type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f4.setSortable("true");
    // /workflow/escritorios/escritorio.jsp(389,9) name = columnClasses type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f4.setColumnClasses("standardTable_Column,standardTable_ColumnCentered,standardTable_Column");
    // /workflow/escritorios/escritorio.jsp(389,9) name = var type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f4.setVar("tablaTarea");
    // /workflow/escritorios/escritorio.jsp(389,9) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fdataTable_005f4.setStyle(" width : 855px;");
    int _jspx_eval_x_005fdataTable_005f4 = _jspx_th_x_005fdataTable_005f4.doStartTag();
    if (_jspx_eval_x_005fdataTable_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fdataTable_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fdataTable_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fdataTable_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t    \r\n");
        out.write("\t\t\t\t                        ");
        if (_jspx_meth_x_005fcolumn_005f22(_jspx_th_x_005fdataTable_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                        \t\r\n");
        out.write("\t\t\t                        \t");
        if (_jspx_meth_x_005fcolumn_005f23(_jspx_th_x_005fdataTable_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                    ");
        out.write("    \t\r\n");
        out.write("\t\t\t                        \t");
        if (_jspx_meth_x_005fcolumn_005f24(_jspx_th_x_005fdataTable_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                        \t\r\n");
        out.write("\t\t\t\t                        ");
        if (_jspx_meth_x_005fcolumn_005f25(_jspx_th_x_005fdataTable_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                        \t\t\r\n");
        out.write("\t\t            \t\t            ");
        if (_jspx_meth_x_005fcolumn_005f26(_jspx_th_x_005fdataTable_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t            \t\t            ");
        if (_jspx_meth_x_005fcolumn_005f27(_jspx_th_x_005fdataTable_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcolumn_005f28(_jspx_th_x_005fdataTable_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fdataTable_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fdataTable_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fdataTable_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses.reuse(_jspx_th_x_005fdataTable_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fdataTable_0026_005fvar_005fvalue_005fstyleClass_005fstyle_005fsortable_005frowClasses_005fid_005fheaderClass_005ffooterClass_005fcolumnClasses.reuse(_jspx_th_x_005fdataTable_005f4);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f22(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f22 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f22.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f4);
    int _jspx_eval_x_005fcolumn_005f22 = _jspx_th_x_005fcolumn_005f22.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f22 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f22 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f22.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f22.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f21(_jspx_th_x_005fcolumn_005f22, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f44(_jspx_th_x_005fcolumn_005f22, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f22.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f22 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f22);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f22);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f21(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f22, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f21 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f21.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f22);
    // /workflow/escritorios/escritorio.jsp(398,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f21.setName("header");
    int _jspx_eval_f_005ffacet_005f21 = _jspx_th_f_005ffacet_005f21.doStartTag();
    if (_jspx_eval_f_005ffacet_005f21 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f43(_jspx_th_f_005ffacet_005f21, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f21.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f21);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f21);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f43(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f21, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f43 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f43.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f43.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f21);
    // /workflow/escritorios/escritorio.jsp(399,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f43.setValue("Inició");
    // /workflow/escritorios/escritorio.jsp(399,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f43.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(399,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f43.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f43 = _jspx_th_h_005foutputText_005f43.doStartTag();
    if (_jspx_th_h_005foutputText_005f43.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f43);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f43);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f44(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f22, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f44 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f44.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f44.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f22);
    // /workflow/escritorios/escritorio.jsp(401,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f44.setValue("#{tablaTarea.detalleTramite.fechaInicioReal}");
    // /workflow/escritorios/escritorio.jsp(401,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f44.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f44 = _jspx_th_h_005foutputText_005f44.doStartTag();
    if (_jspx_th_h_005foutputText_005f44.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f44);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f44);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f23(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f23 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f23.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f4);
    int _jspx_eval_x_005fcolumn_005f23 = _jspx_th_x_005fcolumn_005f23.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f23 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f23 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f23.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f23.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f22(_jspx_th_x_005fcolumn_005f23, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f46(_jspx_th_x_005fcolumn_005f23, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f23.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f23 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f23);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f23);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f22(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f23, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f22 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f22.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f23);
    // /workflow/escritorios/escritorio.jsp(405,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f22.setName("header");
    int _jspx_eval_f_005ffacet_005f22 = _jspx_th_f_005ffacet_005f22.doStartTag();
    if (_jspx_eval_f_005ffacet_005f22 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f45(_jspx_th_f_005ffacet_005f22, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f22.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f22);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f22);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f45(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f22, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f45 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f45.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f45.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f22);
    // /workflow/escritorios/escritorio.jsp(406,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f45.setValue("Tarea");
    // /workflow/escritorios/escritorio.jsp(406,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f45.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(406,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f45.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f45 = _jspx_th_h_005foutputText_005f45.doStartTag();
    if (_jspx_th_h_005foutputText_005f45.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f45);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f45);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f46(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f23, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f46 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f46.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f46.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f23);
    // /workflow/escritorios/escritorio.jsp(408,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f46.setValue("#{tablaTarea.tarea.titulo}");
    // /workflow/escritorios/escritorio.jsp(408,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f46.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f46 = _jspx_th_h_005foutputText_005f46.doStartTag();
    if (_jspx_th_h_005foutputText_005f46.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f46);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f46);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f24(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f24 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f24.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f4);
    int _jspx_eval_x_005fcolumn_005f24 = _jspx_th_x_005fcolumn_005f24.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f24 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f24 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f24.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f24.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f23(_jspx_th_x_005fcolumn_005f24, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f48(_jspx_th_x_005fcolumn_005f24, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f24.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f24 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f24);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f24);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f23(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f24, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f23 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f23.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f24);
    // /workflow/escritorios/escritorio.jsp(419,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f23.setName("header");
    int _jspx_eval_f_005ffacet_005f23 = _jspx_th_f_005ffacet_005f23.doStartTag();
    if (_jspx_eval_f_005ffacet_005f23 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f47(_jspx_th_f_005ffacet_005f23, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f23.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f23);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f23);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f47(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f23, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f47 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f47.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f47.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f23);
    // /workflow/escritorios/escritorio.jsp(420,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f47.setValue("Duración");
    // /workflow/escritorios/escritorio.jsp(420,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f47.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(420,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f47.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f47 = _jspx_th_h_005foutputText_005f47.doStartTag();
    if (_jspx_th_h_005foutputText_005f47.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f47);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f47);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f48(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f24, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f48 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f48.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f48.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f24);
    // /workflow/escritorios/escritorio.jsp(422,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f48.setValue("#{tablaTarea.tarea.duracion}");
    // /workflow/escritorios/escritorio.jsp(422,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f48.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f48 = _jspx_th_h_005foutputText_005f48.doStartTag();
    if (_jspx_th_h_005foutputText_005f48.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f48);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f48);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f25(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f25 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f25.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f4);
    int _jspx_eval_x_005fcolumn_005f25 = _jspx_th_x_005fcolumn_005f25.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f25 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f25 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f25.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f25.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        if (_jspx_meth_f_005ffacet_005f24(_jspx_th_x_005fcolumn_005f25, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f50(_jspx_th_x_005fcolumn_005f25, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f25.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f25 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f25);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f25);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f24(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f25, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f24 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f24.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f25);
    // /workflow/escritorios/escritorio.jsp(426,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f24.setName("header");
    int _jspx_eval_f_005ffacet_005f24 = _jspx_th_f_005ffacet_005f24.doStartTag();
    if (_jspx_eval_f_005ffacet_005f24 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t            \t\t                    ");
        if (_jspx_meth_h_005foutputText_005f49(_jspx_th_f_005ffacet_005f24, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f24.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f24);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f24);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f49(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f24, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f49 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f49.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f49.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f24);
    // /workflow/escritorios/escritorio.jsp(427,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f49.setValue("Progreso");
    // /workflow/escritorios/escritorio.jsp(427,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f49.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(427,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f49.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f49 = _jspx_th_h_005foutputText_005f49.doStartTag();
    if (_jspx_th_h_005foutputText_005f49.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f49);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f49);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f50(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f25, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f50 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f50.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f50.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f25);
    // /workflow/escritorios/escritorio.jsp(429,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f50.setValue("#{tablaTarea.detalleTramite.progreso}");
    // /workflow/escritorios/escritorio.jsp(429,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f50.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f50 = _jspx_th_h_005foutputText_005f50.doStartTag();
    if (_jspx_th_h_005foutputText_005f50.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f50);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f50);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f26(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f26 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f26.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f4);
    int _jspx_eval_x_005fcolumn_005f26 = _jspx_th_x_005fcolumn_005f26.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f26 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f26 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f26.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f26.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        if (_jspx_meth_f_005ffacet_005f25(_jspx_th_x_005fcolumn_005f26, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t                \t\t                ");
        if (_jspx_meth_h_005foutputText_005f52(_jspx_th_x_005fcolumn_005f26, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f26.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f26 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f26);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f26);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f25(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f26, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f25 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f25.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f26);
    // /workflow/escritorios/escritorio.jsp(433,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f25.setName("header");
    int _jspx_eval_f_005ffacet_005f25 = _jspx_th_f_005ffacet_005f25.doStartTag();
    if (_jspx_eval_f_005ffacet_005f25 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f51(_jspx_th_f_005ffacet_005f25, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f25.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f25);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f25);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f51(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f25, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f51 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f51.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f51.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f25);
    // /workflow/escritorios/escritorio.jsp(434,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f51.setValue("Estado");
    // /workflow/escritorios/escritorio.jsp(434,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f51.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(434,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f51.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f51 = _jspx_th_h_005foutputText_005f51.doStartTag();
    if (_jspx_th_h_005foutputText_005f51.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f51);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f51);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f52(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f26, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f52 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f52.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f52.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f26);
    // /workflow/escritorios/escritorio.jsp(436,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f52.setValue("#{tablaTarea.detalleTramite.estado.descripcion}");
    // /workflow/escritorios/escritorio.jsp(436,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f52.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f52 = _jspx_th_h_005foutputText_005f52.doStartTag();
    if (_jspx_th_h_005foutputText_005f52.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f52);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f52);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f27(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f27 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f27.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f4);
    int _jspx_eval_x_005fcolumn_005f27 = _jspx_th_x_005fcolumn_005f27.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f27 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f27 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f27.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f27.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t                    \t\t        ");
        if (_jspx_meth_f_005ffacet_005f26(_jspx_th_x_005fcolumn_005f27, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t                \t\t                ");
        if (_jspx_meth_h_005foutputText_005f54(_jspx_th_x_005fcolumn_005f27, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t                    \t\t    ");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f27.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f27 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f27);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f27);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f26(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f27, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f26 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f26.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f27);
    // /workflow/escritorios/escritorio.jsp(440,32) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f26.setName("header");
    int _jspx_eval_f_005ffacet_005f26 = _jspx_th_f_005ffacet_005f26.doStartTag();
    if (_jspx_eval_f_005ffacet_005f26 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t                                ");
        if (_jspx_meth_h_005foutputText_005f53(_jspx_th_f_005ffacet_005f26, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t    \t\t                        ");
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f26.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f26);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f26);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f53(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f26, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f53 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f53.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f53.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f26);
    // /workflow/escritorios/escritorio.jsp(441,36) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f53.setValue("Operador Responsable");
    // /workflow/escritorios/escritorio.jsp(441,36) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f53.setStyleClass("texto");
    // /workflow/escritorios/escritorio.jsp(441,36) name = style type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f53.setStyle("font: bold;color: white;");
    int _jspx_eval_h_005foutputText_005f53 = _jspx_th_h_005foutputText_005f53.doStartTag();
    if (_jspx_th_h_005foutputText_005f53.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f53);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fstyle_005fnobody.reuse(_jspx_th_h_005foutputText_005f53);
    return false;
  }

  private boolean _jspx_meth_h_005foutputText_005f54(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f27, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  h:outputText
    org.apache.myfaces.taglib.html.HtmlOutputTextTag _jspx_th_h_005foutputText_005f54 = (org.apache.myfaces.taglib.html.HtmlOutputTextTag) _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.get(org.apache.myfaces.taglib.html.HtmlOutputTextTag.class);
    _jspx_th_h_005foutputText_005f54.setPageContext(_jspx_page_context);
    _jspx_th_h_005foutputText_005f54.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f27);
    // /workflow/escritorios/escritorio.jsp(443,35) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f54.setValue("#{tablaTarea.detalleTramite.operadorResponsable.label}");
    // /workflow/escritorios/escritorio.jsp(443,35) name = styleClass type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_h_005foutputText_005f54.setStyleClass("texto");
    int _jspx_eval_h_005foutputText_005f54 = _jspx_th_h_005foutputText_005f54.doStartTag();
    if (_jspx_th_h_005foutputText_005f54.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f54);
      return true;
    }
    _005fjspx_005ftagPool_005fh_005foutputText_0026_005fvalue_005fstyleClass_005fnobody.reuse(_jspx_th_h_005foutputText_005f54);
    return false;
  }

  private boolean _jspx_meth_x_005fcolumn_005f28(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fdataTable_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:column
    org.apache.myfaces.custom.column.HtmlColumnTag _jspx_th_x_005fcolumn_005f28 = (org.apache.myfaces.custom.column.HtmlColumnTag) _005fjspx_005ftagPool_005fx_005fcolumn.get(org.apache.myfaces.custom.column.HtmlColumnTag.class);
    _jspx_th_x_005fcolumn_005f28.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcolumn_005f28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fdataTable_005f4);
    int _jspx_eval_x_005fcolumn_005f28 = _jspx_th_x_005fcolumn_005f28.doStartTag();
    if (_jspx_eval_x_005fcolumn_005f28 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fcolumn_005f28 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fcolumn_005f28.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fcolumn_005f28.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t");
        if (_jspx_meth_x_005fcommandLink_005f12(_jspx_th_x_005fcolumn_005f28, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_x_005fcolumn_005f28.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fcolumn_005f28 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fcolumn_005f28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f28);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcolumn.reuse(_jspx_th_x_005fcolumn_005f28);
    return false;
  }

  private boolean _jspx_meth_x_005fcommandLink_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcolumn_005f28, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:commandLink
    org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag _jspx_th_x_005fcommandLink_005f12 = (org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag) _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.get(org.apache.myfaces.taglib.html.ext.HtmlCommandLinkTag.class);
    _jspx_th_x_005fcommandLink_005f12.setPageContext(_jspx_page_context);
    _jspx_th_x_005fcommandLink_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcolumn_005f28);
    // /workflow/escritorios/escritorio.jsp(447,11) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f12.setId("verTareaLink");
    // /workflow/escritorios/escritorio.jsp(447,11) name = action type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fcommandLink_005f12.setAction("#{EscritorioBean.verTareaSuper}");
    int _jspx_eval_x_005fcommandLink_005f12 = _jspx_th_x_005fcommandLink_005f12.doStartTag();
    if (_jspx_eval_x_005fcommandLink_005f12 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_f_005fparam_005f11(_jspx_th_x_005fcommandLink_005f12, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_x_005fgraphicImage_005f11(_jspx_th_x_005fcommandLink_005f12, _jspx_page_context))
        return true;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_x_005fcommandLink_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fcommandLink_0026_005fid_005faction.reuse(_jspx_th_x_005fcommandLink_005f12);
    return false;
  }

  private boolean _jspx_meth_f_005fparam_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:param
    org.apache.myfaces.taglib.core.ParamTag _jspx_th_f_005fparam_005f11 = (org.apache.myfaces.taglib.core.ParamTag) _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.get(org.apache.myfaces.taglib.core.ParamTag.class);
    _jspx_th_f_005fparam_005f11.setPageContext(_jspx_page_context);
    _jspx_th_f_005fparam_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f12);
    // /workflow/escritorios/escritorio.jsp(448,12) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f11.setId("idDetalle");
    // /workflow/escritorios/escritorio.jsp(448,12) name = name type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f11.setName("idDetalle");
    // /workflow/escritorios/escritorio.jsp(448,12) name = value type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005fparam_005f11.setValue("#{tablaTarea.detalleTramite.idDetalleTramite}");
    int _jspx_eval_f_005fparam_005f11 = _jspx_th_f_005fparam_005f11.doStartTag();
    if (_jspx_th_f_005fparam_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fparam_0026_005fvalue_005fname_005fid_005fnobody.reuse(_jspx_th_f_005fparam_005f11);
    return false;
  }

  private boolean _jspx_meth_x_005fgraphicImage_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fcommandLink_005f12, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:graphicImage
    org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag _jspx_th_x_005fgraphicImage_005f11 = (org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag) _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.get(org.apache.myfaces.taglib.html.ext.HtmlGraphicImageTag.class);
    _jspx_th_x_005fgraphicImage_005f11.setPageContext(_jspx_page_context);
    _jspx_th_x_005fgraphicImage_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fcommandLink_005f12);
    // /workflow/escritorios/escritorio.jsp(449,12) name = value type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f11.setValue("/img/icon/editar.gif");
    // /workflow/escritorios/escritorio.jsp(449,12) name = title type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f11.setTitle("Ver Tarea.");
    // /workflow/escritorios/escritorio.jsp(449,12) name = border type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fgraphicImage_005f11.setBorder("0");
    int _jspx_eval_x_005fgraphicImage_005f11 = _jspx_th_x_005fgraphicImage_005f11.doStartTag();
    if (_jspx_th_x_005fgraphicImage_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fgraphicImage_0026_005fvalue_005ftitle_005fborder_005fnobody.reuse(_jspx_th_x_005fgraphicImage_005f11);
    return false;
  }

  private boolean _jspx_meth_x_005fpanelTab_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fpanelTabbedPane_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  x:panelTab
    org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag _jspx_th_x_005fpanelTab_005f4 = (org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag) _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid_005fdisabled.get(org.apache.myfaces.custom.tabbedpane.HtmlPanelTabTag.class);
    _jspx_th_x_005fpanelTab_005f4.setPageContext(_jspx_page_context);
    _jspx_th_x_005fpanelTab_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fpanelTabbedPane_005f0);
    // /workflow/escritorios/escritorio.jsp(462,3) name = id type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTab_005f4.setId("tab2");
    // /workflow/escritorios/escritorio.jsp(462,3) name = label type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTab_005f4.setLabel("Tarea");
    // /workflow/escritorios/escritorio.jsp(462,3) name = disabled type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_x_005fpanelTab_005f4.setDisabled("true");
    int _jspx_eval_x_005fpanelTab_005f4 = _jspx_th_x_005fpanelTab_005f4.doStartTag();
    if (_jspx_eval_x_005fpanelTab_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_x_005fpanelTab_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_x_005fpanelTab_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_x_005fpanelTab_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\r\n");
        out.write("\t\t    ");
        int evalDoAfterBody = _jspx_th_x_005fpanelTab_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_x_005fpanelTab_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_x_005fpanelTab_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid_005fdisabled.reuse(_jspx_th_x_005fpanelTab_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fx_005fpanelTab_0026_005flabel_005fid_005fdisabled.reuse(_jspx_th_x_005fpanelTab_005f4);
    return false;
  }

  private boolean _jspx_meth_f_005ffacet_005f27(javax.servlet.jsp.tagext.JspTag _jspx_th_x_005fpanelLayout_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:facet
    javax.faces.webapp.FacetTag _jspx_th_f_005ffacet_005f27 = (javax.faces.webapp.FacetTag) _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.get(javax.faces.webapp.FacetTag.class);
    _jspx_th_f_005ffacet_005f27.setPageContext(_jspx_page_context);
    _jspx_th_f_005ffacet_005f27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_x_005fpanelLayout_005f0);
    // /inc/page_footer.jsp(3,0) name = name type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_f_005ffacet_005f27.setName("footer");
    int _jspx_eval_f_005ffacet_005f27 = _jspx_th_f_005ffacet_005f27.doStartTag();
    if (_jspx_eval_f_005ffacet_005f27 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_f_005fverbatim_005f7(_jspx_th_f_005ffacet_005f27, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_f_005ffacet_005f27.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_f_005ffacet_005f27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f27);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005ffacet_0026_005fname.reuse(_jspx_th_f_005ffacet_005f27);
    return false;
  }

  private boolean _jspx_meth_f_005fverbatim_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_f_005ffacet_005f27, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  f:verbatim
    org.apache.myfaces.taglib.core.VerbatimTag _jspx_th_f_005fverbatim_005f7 = (org.apache.myfaces.taglib.core.VerbatimTag) _005fjspx_005ftagPool_005ff_005fverbatim.get(org.apache.myfaces.taglib.core.VerbatimTag.class);
    _jspx_th_f_005fverbatim_005f7.setPageContext(_jspx_page_context);
    _jspx_th_f_005fverbatim_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_f_005ffacet_005f27);
    int _jspx_eval_f_005fverbatim_005f7 = _jspx_th_f_005fverbatim_005f7.doStartTag();
    if (_jspx_eval_f_005fverbatim_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_f_005fverbatim_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_f_005fverbatim_005f7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_f_005fverbatim_005f7.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t<p style=\"copy\" align=\"center\">\r\n");
        out.write("\t\t\t<label style=\"color:#FFFFFF;font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 10px;\">Tarjeta Fiel 2007 - Todos los Derechos reservados</label>\r\n");
        out.write("\t\t</p>\r\n");
        out.write("\t");
        int evalDoAfterBody = _jspx_th_f_005fverbatim_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_f_005fverbatim_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_f_005fverbatim_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005ff_005fverbatim.reuse(_jspx_th_f_005fverbatim_005f7);
    return false;
  }
}
