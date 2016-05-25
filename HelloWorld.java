2. Escreva uma servlet que gere HTML


import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@SuppressWarnings("serial")
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String docType =
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
						"Transitional//EN\">\n";
		out.println(docType +
				"<HTML>\n" +
				"<HEAD><TITLE>Hello</TITLE></HEAD>\n" +
				"<BODY BGCOLOR=\"#FDF5E6\">\n" +
				"<H1>Hello</H1>\n" +
				"</BODY></HTML>");
	}
}
