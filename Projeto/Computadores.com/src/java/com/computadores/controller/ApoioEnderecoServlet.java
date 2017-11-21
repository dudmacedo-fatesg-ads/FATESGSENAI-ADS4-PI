package com.computadores.controller;

import com.computadores.dal.EnderecoDAO;
import com.computadores.error.DatabaseException;
import com.computadores.model.Endereco;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eduardo
 */
@WebServlet(name = "ApoioEndereco", urlPatterns = {"/ApoioEndereco.get"})
public class ApoioEnderecoServlet extends HttpServlet {

    EnderecoDAO dao = new EnderecoDAO();

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

        String retorno = "";
        
        String cep = request.getParameter("cep");
        if (cep != null) {
            int valor_cep = Integer.parseInt(cep.replace("-", ""));
            Endereco end = null;

            try {
                end = dao.retrieveByCEP(valor_cep);
            } catch (DatabaseException ex) {
                Logger.getLogger(ApoioEnderecoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (end != null) {
                retorno = String.format(
                        "{ \"endereco\" {"
                        + "\"cep\" : \"%s\","
                        + "\"logradouro\" : \"%s\","
                        + "\"cidade\" : %d,"
                        + "\"estado\" : %d"
                        + "} }",
                        String.format("%05d-%03d", end.getCep() / 1000, end.getCep() % 1000),
                        end.getLogradouro(),
                        end.getCidade().getCodigo(),
                        end.getCidade().getEstado().getCodigo()
                );
            } else {
                retorno = "NULL";
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(retorno);
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ApoioEndereco</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ApoioEndereco at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
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
