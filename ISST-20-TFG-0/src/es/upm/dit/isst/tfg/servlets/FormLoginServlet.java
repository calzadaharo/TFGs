package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.ProfessorDAOImplementation;
import es.upm.dit.isst.tfg.dao.TFGDAOImplementation;
import es.upm.dit.isst.tfg.model.Professor;
import es.upm.dit.isst.tfg.model.TFG;

@WebServlet("/FormLoginServlet")
public class FormLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ADMIN_EMAIL = "root";
	private final String ADMIN_PASSWORD = "root";	
	
	public FormLoginServlet() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		List<Professor> profesores = ProfessorDAOImplementation.getInstance().readAll();
		List<TFG> tfgs = TFGDAOImplementation.getInstance().readAll();
		TFG tfg = TFGDAOImplementation.getInstance().login(email, password);
		Professor professor = ProfessorDAOImplementation.getInstance().login(email, password);
		if( ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password) ) {
			req.getSession().setAttribute("admin", true);
			req.getSession().setAttribute("profesores", profesores);
			req.getSession().setAttribute("tfgs", tfgs);			     
            getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
		} else if ( null != tfg ) {
			req.getSession().setAttribute("tfg", tfg);
	        getServletContext().getRequestDispatcher("/TFGView.jsp").forward(req,resp);
		} else if ( null != professor ) {
			req.getSession().setAttribute("profesor", ProfessorDAOImplementation.getInstance().read(professor.getEmail()));
	        getServletContext().getRequestDispatcher("/ProfessorView.jsp").forward(req,resp);
		} else	{
	        getServletContext().getRequestDispatcher("/index.html").forward(req,resp);
		}
	}
}
