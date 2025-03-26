package org.apache.jsp.WEB_002dINF.jsp.layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class nav_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

      out.write('\r');
      out.write('\n');
  String root = request.getContextPath(); 
      out.write("\r\n");
      out.write("<table width=\"145\" height=\"100%\" class=\"nav\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td width=\"142\" valign=\"top\">\r\n");
      out.write("\t\t<a href=\"");
      out.print(root);
      out.write("/schedule/scheduleControl.action\">Schedule Control</a>\r\n");
      out.write("\t\t<td width=\"3\" >&nbsp;</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td width=\"142\" valign=\"top\">\r\n");
      out.write("\t\t<a href=\"");
      out.print(root);
      out.write("/definition/list.action\">Job Definitions</a>\r\n");
      out.write("\t\t<td width=\"3\" >&nbsp;</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td width=\"142\" valign=\"top\" ><a href=\"");
      out.print(root);
      out.write("/jobs/createJob.action\">Create Job</a></td>\r\n");
      out.write("\t\t<td width=\"3\" >&nbsp;</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td width=\"142\" valign=\"top\" ><a href=\"");
      out.print(root);
      out.write("/schedule/listJobs.action\">List Jobs</a></td>\r\n");
      out.write("\t\t<td width=\"3\" >&nbsp;</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td width=\"142\" valign=\"top\" ><a href=\"");
      out.print(root);
      out.write("/schedule/listTriggers.action\">List all Triggers</a></td>\r\n");
      out.write("\t\t<td width=\"3\" >&nbsp;</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td width=\"142\" valign=\"top\" ><a href=\"");
      out.print(root);
      out.write("/quartzLog.action\">Logging</a></td>\r\n");
      out.write("\t\t<td width=\"3\" >&nbsp;</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr height=\"100%\">\r\n");
      out.write("\t\t<td width=\"142\" valign=\"top\" >&nbsp;</td>\r\n");
      out.write("\t\t<td width=\"3\" >&nbsp;</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("</table>");
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
}
