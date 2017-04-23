package com.crunchify.jsp.servlet;
 
import edu.co.sergio.mundo.dao.EstudianteDAO;
import edu.co.sergio.mundo.vo.Estudiante;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.RequestDispatcher;
 

public class HelloCrunchify extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String id = request.getParameter("usuario");
        String nombre = request.getParameter("clave");
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        EstudianteDAO dao = new EstudianteDAO();
        
        Estudiante estudiante = new Estudiante();
        estudiante.setClave(Integer.parseInt(id));
        estudiante.setNom(nombre);
        dao.insert(estudiante);
        
        //Listando la informacion  
        List<Estudiante> v =  dao.findAll();
        request.setAttribute("estudiante", estudiante);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("index.jsp");
        redireccion.forward(request, response);
        
        
        }
}
