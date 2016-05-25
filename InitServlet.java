3. Escreva uma servlet que leia dois parâmetro de inicialização, da pasta de configuração web.xml (Tomcat). 
O primeiro parâmetro deve conter um nome e o segundo um endereço de e-mail.





import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@SuppressWarnings("serial")
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {

	private String firstName, emailAddress;
	
	public void init() {
		ServletConfig config = getServletConfig();
		firstName = config.getInitParameter("firstName");
		emailAddress = config.getInitParameter("emailAddress");
	}
	
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String uri = request.getRequestURI();
		out.println(ServletUtilities.headWithTitle("Init Servlet") +
				"<BODY BGCOLOR=\"#FDF5E6\">\n" +
				"<H2>Init Parameters:</H2>\n" +
				"<UL>\n" +
				"<LI>First name: " + firstName + "\n" +
				"<LI>Email address: " + emailAddress + "\n" +
				"</UL>\n" +
				"</BODY></HTML>");
	}
}


WEB.XML
<web-app>
  <servlet>
    <servlet-name>InitTest</servlet-name>
    <servlet-class>InitServlet</servlet-class>
      <init-param>
        <param-name>firstName</param-name>
        <param-value>Larry</param-value>
      </init-param>
      <init-param>
        <param-name>emailAddress</param-name>
        <param-value>ellison@microsoft.com</param-value>
      </init-param>
  </servlet>
</web-app>
