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

import mogal.development.triptogether.ejbs.PersistenceEjbLocal;
import mogal.development.triptogether.entities.Product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(urlPatterns = {"/FirstServlet/*"})
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PersistenceEjbLocal ejb;
	Logger logger;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		logger = LogManager.getLogger();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder = new StringBuilder();
		List<Product> products = ejb.getProducts();
		
		builder.append("<!DOCTYPE html>");
		builder.append("<html>");
		builder.append("<head>");
		builder.append("<title>asdasdasasd</title>");
		builder.append("</head>");
		builder.append("<body>");
		builder.append("starting... </br>");

		for (Product product : products) {
			builder.append(product.getName());
			builder.append("<img src='" + product.getImageUrl() + "' width='50' height='50'>");
			builder.append("</br></br>");
		}
		
		builder.append("done!");
		builder.append("</body>");
		builder.append("</html>");
		logger.debug("debug");
		logger.debug("debug");
		logger.error("error");
		logger.error("error");
		
		response.getOutputStream().write(builder.toString().getBytes());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
