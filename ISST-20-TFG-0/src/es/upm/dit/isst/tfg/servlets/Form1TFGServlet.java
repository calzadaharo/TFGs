package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.ProfessorDAOImplementation;
import es.upm.dit.isst.tfg.dao.TFGDAOImplementation;
import es.upm.dit.isst.tfg.model.Professor;
import es.upm.dit.isst.tfg.model.TFG;

@WebServlet("/Form1TFGServlet")
public class Form1TFGServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String title = req.getParameter("titulo");
		String advisorEmail = req.getParameter("profesor");
		Professor advisor = ProfessorDAOImplementation.getInstance().read(advisorEmail);
		if( null != advisor ) {
			TFG tfg = new TFG();
			tfg.setEmail(email);
			tfg.setPassword(password);
			tfg.setNombre(name);
			tfg.setTitle(title);
			tfg.setAdvisor(advisor);
			tfg.setStatus(1);
			TFGDAOImplementation.getInstance().create(tfg);
			req.getSession().setAttribute("tfg", tfg);
		}	
		getServletContext().getRequestDispatcher("/index.html").forward(req, resp);
	}

}
