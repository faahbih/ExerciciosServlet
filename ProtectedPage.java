7. Escreva uma servlet que só permite o acesso a uma página mediante a introdução de um login/password válido.



import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class ProtectedPage
 */
@WebServlet("/ProtectedPage")
public class ProtectedPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Properties passwords;
	private String passwordFile;
	public void init(ServletConfig config)
			throws ServletException {
		super.init(config);
		try {
			passwordFile = config.getInitParameter("passwordFile");
			passwords = new Properties();
			passwords.load(new FileInputStream(passwordFile));
		} catch(IOException ioe) {}
	}
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String authorization = request.getHeader("Authorization");
		if (authorization == null) {
			askForPassword(response);
		} else {
			String userInfo = authorization.substring(6).trim();
			BASE64Decoder decoder = new BASE64Decoder();
			String nameAndPassword =
					new String(decoder.decodeBuffer(userInfo));
			int index = nameAndPassword.indexOf(":");
			String user = nameAndPassword.substring(0, index);
			String password = nameAndPassword.substring(index+1);
			String realPassword = passwords.getProperty(user);
			if ((realPassword != null)
					&& (realPassword.equals(password))) {
				String title = "Welcome to the Protected Page";
				out.println(ServletUtilities.headWithTitle(title) +
						"<BODY BGCOLOR=\"#FDF5E6\">\n" +
						"<H1 ALIGN=CENTER>" + title + "</H1>\n" +
						"Congratulations. You have accessed a\n" +
						"highly proprietary company document.\n" +
						"Shred or eat all hardcopies before\n" +
						"going to bed tonight.\n" +
						"</BODY></HTML>");
			} else {
				askForPassword(response);
			}
		}
	}
	private void askForPassword(HttpServletResponse response) {
		response.setStatus(response.SC_UNAUTHORIZED); // Ie 401
		response.setHeader("WWW-Authenticate",
				"BASIC realm=\"privileged-few\"");
	}
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		doGet(request, response);
	}
}


