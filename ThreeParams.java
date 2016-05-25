4. Escreva uma servlet que leia três parâmetros introduzidos pelo utilizador de uma página da Web.



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThreeParams
 */
@WebServlet("/ThreeParams")
public class ThreeParams extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Reading Three Request Parameters";
		out.println(ServletUtilities.headWithTitle(title) +
				"<BODY BGCOLOR=\"#FDF5E6\">\n" +
				"<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
				"<UL>\n" +
				" <LI><B>param1</B>: "
				+ request.getParameter("param1") + "\n" +
				" <LI><B>param2</B>: "
				+ request.getParameter("param2") + "\n" +
				" <LI><B>param3</B>: "
				+ request.getParameter("param3") + "\n" +
				"</UL>\n" +
				"</BODY></HTML>");
	}
}

HTML

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Collecting Three Parameters</title>
</head>
<body BGCOLOR="#FDF5E6">
	<H1 ALIGN="CENTER">Collecting Three Parameters</H1>
	<FORM ACTION="/servlet/moreservlets.ThreeParams">
		First Parameter: <INPUT TYPE="TEXT" NAME="param1"><BR>
		Second Parameter: <INPUT TYPE="TEXT" NAME="param2"><BR>
		Third Parameter: <INPUT TYPE="TEXT" NAME="param3"><BR>
		<CENTER>
			<INPUT TYPE="SUBMIT">
		</CENTER>
	</FORM>
</body>
</html>
