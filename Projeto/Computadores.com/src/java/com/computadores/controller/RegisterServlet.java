package com.computadores.controller;

import com.computadores.Config;
import com.computadores.dal.CidadeDAO;
import com.computadores.dal.EnderecoDAO;
import com.computadores.error.DatabaseException;
import com.computadores.model.Cidade;
import com.computadores.model.Endereco;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eduardo
 */
@WebServlet(name = "Register.do", urlPatterns = {"/Register.do"})
public class RegisterServlet extends HttpServlet {

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
        if (request.getParameter("acao") != null) {
            if (request.getParameter("acao").equals("novoregistro")) {
                request.setAttribute("clienteEmail", request.getParameter("usuarioEmail"));
                
                // Tenta converter o CEP
                int cep = 0;
                try {
                    cep = Integer.parseInt(request.getParameter("usuarioCEP").replaceAll("-", ""));
                } catch (NumberFormatException ex) {
                    Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    EnderecoDAO end_dao = new EnderecoDAO();
                    Endereco pre_endereco = end_dao.retrieveByCEP(cep);
                    request.setAttribute("pre_endereco", pre_endereco);
                    request.setAttribute("estados", Config.getEstados());
                    
                    CidadeDAO cid_dao = new CidadeDAO();
                    Iterator<Cidade> cidades = cid_dao.listCidades(pre_endereco.getCidade().getEstado());
                    request.setAttribute("cidades", cidades);
                } catch (DatabaseException ex) {
                    Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                RequestDispatcher view = request.getRequestDispatcher("?p=editCliente");
                view.forward(request, response);
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
