/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Clases.ConexionBaseDeDatos;
import Clases.Tienda;
import Clases.TiendaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mgeoh
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
Tienda tienda;
    TiendaController registroDatos;
     Tienda[] datosRegistrados;
     StringBuffer objetoRespuesta = new StringBuffer();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter respuesta = response.getWriter()) {            
           
           registroDatos=new TiendaController();
           String control = request.getParameter("control");
           
           if(control.toUpperCase().equals("GUARDAR")){
               tienda=new Tienda(
                Integer.parseInt(request.getParameter("codigo")),
                request.getParameter("marca"),
                request.getParameter("modelo"),
                request.getParameter("pantalla"),
                Integer.parseInt(request.getParameter("año")));                
                registroDatos.guardarDatos2(tienda);//almacenarlo en BD                 
           }else if(control.toUpperCase().equals("ELIMINAR")){
               int codigoEliminar= Integer.parseInt(request.getParameter("codigo_alumno"));
               registroDatos.eliminarDatos(codigoEliminar);
               
               registroDatos.getDato2(objetoRespuesta);
           respuesta.write(objetoRespuesta.toString()); 
               
               
           }
    }
        
    }
}
