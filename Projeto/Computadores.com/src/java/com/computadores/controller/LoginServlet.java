package com.computadores.controller;

import com.computadores.dal.ClienteDAO;
import com.computadores.error.DatabaseException;
import com.computadores.model.Cliente;
import com.computadores.model.PessoaFisica;
import com.computadores.model.PessoaJuridica;
import com.computadores.model.TipoPessoa;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eduardo
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    ClienteDAO dao = new ClienteDAO();

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
        if (request.getParameter("acao") != null) {
            if (request.getParameter("acao").equals("login")) {
                // login = E-mail do usuário
                String login = request.getParameter("login");
                // senha = senha do usuário. Deverá ser recebido aqui o hash MD5 da senha do usuário
                String senha = request.getParameter("senha");

                try {
                    Cliente cli = dao.retrieveLogin(login, senha);
                    if (cli != null) {
                        HttpSession session = request.getSession();

                        session.setAttribute("codigo", cli.getCodigo());
                        if (cli.getTipo() == TipoPessoa.FISICA) {
                            session.setAttribute("nome", ((PessoaFisica) cli).getNome());
                        } else if (cli.getTipo() == TipoPessoa.JURIDICA) {
                            session.setAttribute("nome", ((PessoaJuridica) cli).getRazaoSocial());
                        }

                    }
                } catch (DatabaseException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("acao").equals("logout")) {
                HttpSession session = request.getSession();
                session.invalidate();
            }
        }

        // *** A resposta será implementada no futuro, quando a tela estiver disponível
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
