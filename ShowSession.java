10. Escreva uma servlet que mostre a seguinte informação sobre uma sessão iniciado pelo cliente
  a) Identificador da Sessão
  b) Data/Hora de criação da sessão
  c) Última vez que acessou
  d) Número de acessos.



import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowSession
 */
@WebServlet("/ShowSession")
public class ShowSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Session Tracking Example";
		HttpSession session = request.getSession(true);
		String heading;
		Integer accessCount =
				(Integer)session.getAttribute("accessCount");
		if (accessCount == null) {
			accessCount = new Integer(0);
			heading = "Welcome, Newcomer";
		} else {
			heading = "Welcome Back";
			accessCount = new Integer(accessCount.intValue() + 1);
		}
		session.setAttribute("accessCount", accessCount);
		out.println(ServletUtilities.headWithTitle(title) +
				"<BODY BGCOLOR=\"#FDF5E6\">\n" +
				"<H1 ALIGN=\"CENTER\">" + heading + "</H1>\n" +
				"<H2>Information on Your Session:</H2>\n" +
				"<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
				"<TR BGCOLOR=\"#FFAD00\">\n" +
				" <TH>Info Type<TH>Value\n" +
				"<TR>\n" +
				" <TD>ID\n" +
				" <TD>" + session.getId() + "\n" +
				"<TR>\n" +
				" <TD>Creation Time\n" +
				" <TD>" +
				new Date(session.getCreationTime()) + "\n" +
				"<TR>\n" +
				" <TD>Time of Last Access\n" +
				" <TD>" +
				new Date(session.getLastAccessedTime()) + "\n" +
				"<TR>\n" +
				" <TD>Number of Previous Accesses\n" +
				" <TD>" + accessCount + "\n" +
				"</TABLE>\n" +
				"</BODY></HTML>");
	}
	/** Handle GET and POST requests identically. */
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		doGet(request, response);
	}
}

