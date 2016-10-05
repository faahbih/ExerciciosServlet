1. Escreva uma servlet que gere o texto “Hello World”



import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class helloworld
 */

@SuppressWarnings("serial")
@WebServlet("/helloworld")
public class helloworld extends HttpServlet {

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Hello World");
	}
}
