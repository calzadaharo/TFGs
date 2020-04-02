package es.upm.dit.isst.tfg.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.upm.dit.isst.tfg.dao.TFGDAOImplementation;
import es.upm.dit.isst.tfg.model.TFG;

/**
 * Servlet implementation class Form4TFGServlet
 */
@WebServlet("/Form4TFGServlet")
@MultipartConfig
public class Form4TFGServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
           throws ServletException, IOException {
                TFG tfg = (TFG) req.getSession().getAttribute("tfg");        
                Part filePart = req.getPart("file");
                InputStream fileContent = filePart.getInputStream();
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                for (int length = 0; (length = fileContent.read(buffer)) > 0;)
                       output.write(buffer, 0, length);
                tfg.setDocument(output.toByteArray());
                tfg.setStatus(4);
                TFGDAOImplementation.getInstance().update(tfg);                
                req.getSession().setAttribute("tfg", tfg);
                getServletContext().getRequestDispatcher("/TFGView.jsp").forward(req,resp);

        }

}