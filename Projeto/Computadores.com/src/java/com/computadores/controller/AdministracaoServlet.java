package com.computadores.controller;

import com.computadores.dal.CategoriaDAO;
import com.computadores.dal.ClienteDAO;
import com.computadores.dal.MarcaDAO;
import com.computadores.error.DatabaseException;
import com.computadores.model.Categoria;
import com.computadores.model.Cliente;
import com.computadores.model.Marca;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
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
@WebServlet(name = "Administracao", urlPatterns = {"/Administracao"})
public class AdministracaoServlet extends HttpServlet {

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
        String ADM_PAGE = "?p=administracao";

        if (request.getSession().getAttribute("usuarioObj") == null) {
            response.sendRedirect("./");
        } else {
            if (request.getParameter("funcao") != null) {//
                // CRUD Categorias
                if (request.getParameter("funcao").equals("listCategoria")) {
                    request.setAttribute("funcao", "listCategoria");

                    CategoriaDAO cat_dao = new CategoriaDAO();

                    try {
                        Iterator<Categoria> categorias = cat_dao.list().iterator();
                        request.setAttribute("categorias", categorias);
                    } catch (DatabaseException ex) {
                        Logger.getLogger(AdministracaoServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (request.getParameter("funcao").equals("editCategoria")) {

                }//
                // CRUD Marcas
                else if (request.getParameter("funcao").equals("listMarca")) {
                    request.setAttribute("funcao", "listMarca");

                    MarcaDAO mar_dao = new MarcaDAO();

                    try {
                        Iterator<Marca> marcas = mar_dao.list().iterator();
                        request.setAttribute("marcas", marcas);
                    } catch (DatabaseException ex) {
                        Logger.getLogger(AdministracaoServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (request.getParameter("funcao").equals("editMarca")) {

                }//
                // CRUD Clientes
                else if (request.getParameter("funcao").equals("listCliente")) {
                    request.setAttribute("funcao", "listCliente");

                    ClienteDAO cli_dao = new ClienteDAO();

                    try {
                        Iterator<Cliente> clientes = cli_dao.list().iterator();
                        request.setAttribute("clientes", clientes);
                    } catch (DatabaseException ex) {
                        Logger.getLogger(AdministracaoServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } //
                else if (request.getParameter("funcao").equals("editCliente")) {

                } //
            }
            RequestDispatcher view = request.getRequestDispatcher(ADM_PAGE);
            view.forward(request, response);
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
