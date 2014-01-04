package mogal.development.triptogether;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mogal.development.triptogether.ejbs.users.UsersEjbLocal;
import mogal.development.triptogether.entities.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(urlPatterns = {"/FirstServlet/*"})
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UsersEjbLocal ejb;
	Logger logger;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		logger = LogManager.getLogger();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder = new StringBuilder();
		List<User> users = ejb.getUsers();
		
		builder.append("<!DOCTYPE html>");
		builder.append("<html>");
		builder.append("<head>");
		builder.append("<title>Users</title>");
		builder.append("</head>");
		builder.append("<body>");
		builder.append("starting... </br>");

		for (User user : users) {
			builder.append(user.getFirstName() + " " + user.getLastName());
			builder.append(": ");
			builder.append(user.getBirthDate());
			builder.append(",");
			builder.append("is loged in = " + user.getIsLogedIn().toString());
			builder.append("</br></br>");
		}
		
		builder.append("done!");
		builder.append("</body>");
		builder.append("</html>");
		
		response.getOutputStream().write(builder.toString().getBytes());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
