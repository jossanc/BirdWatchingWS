/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.UserDB;
import java.util.logging.Logger;
import javaBeans.User;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author jose
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Logger logger = Logger.getLogger(Login.class.getName());
    
    
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        logger.warning("Aqui entra");
        response.setContentType("text/html;charset=UTF-8");
        String encoding = request.getCharacterEncoding();
        if(encoding == null){
            request.setCharacterEncoding("UTF-8");
        }
        
        
        HttpSession sesion = request.getSession();
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        
        if(UserDB.userExists(user)) {
            if(UserDB.checkPassword(user, password)) {

                    User usuario = UserDB.getUser(user);
                    sesion.setAttribute("user", usuario);
                    request.setAttribute("user",user);
                    RequestDispatcher rd = request.getRequestDispatcher("/Index.html");
                    rd.forward(request, response);   
            } else {
                request.setAttribute("passwd-incorrecta", -1);
                RequestDispatcher rd = request.getRequestDispatcher("/Index.html");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("usuario-incorrecto", -1);
            RequestDispatcher rd = request.getRequestDispatcher("/Index.html");
            rd.forward(request, response);
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        logger.info("Aqui llega, entra al GET");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
