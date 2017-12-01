package com.computadores.controller;

import com.computadores.Config;
import com.computadores.dal.CidadeDAO;
import com.computadores.dal.ClienteDAO;
import com.computadores.dal.EnderecoDAO;
import com.computadores.error.DatabaseException;
import com.computadores.model.Cidade;
import com.computadores.model.Cliente;
import com.computadores.model.Endereco;
import com.computadores.model.Estado;
import com.computadores.model.PessoaFisica;
import com.computadores.model.PessoaJuridica;
import com.computadores.model.TipoPessoa;
import com.computadores.util.Hash;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        request.setCharacterEncoding("UTF-8");
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
                    if (pre_endereco != null) {
                        request.setAttribute("pre_endereco", pre_endereco);
                        CidadeDAO cid_dao = new CidadeDAO();
                        Iterator<Cidade> cidades = cid_dao.listCidades(pre_endereco.getCidade().getEstado());
                        request.setAttribute("cidades", cidades);
                    }
                    request.setAttribute("estadoscliente", Config.getEstados());
                    request.setAttribute("estadosie", Config.getEstados());
                    
                } catch (DatabaseException ex) {
                    Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                RequestDispatcher view = request.getRequestDispatcher("?p=editCliente");
                view.forward(request, response);
            } //
            //
            else if (request.getParameter("acao").equals("registrarcliente")) {
                Cliente novo_cliente = null;
                // Seta campos específicos
                switch (TipoPessoa.getById(request.getParameter("clienteTipo"))) {
                    case FISICA:
                        PessoaFisica pf = new PessoaFisica();
                        pf.setCpf(Long.parseLong(request.getParameter("clienteCPF").replaceAll("\\.", "").replaceAll("-", "")));
                        pf.setRg(Integer.parseInt(request.getParameter("clienteRG")));
                        pf.setNome(request.getParameter("clienteNome"));
                        try {
                            pf.setDtNasc(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("clienteNascimento")));
                        } catch (ParseException ex) {
                            // Se o usuário fizer direito não vai acontecer este erro...
                            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        novo_cliente = pf;
                        break;
                    case JURIDICA:
                        PessoaJuridica pj = new PessoaJuridica();
                        pj.setCnpj(Long.parseLong(request.getParameter("clienteCNPJ").replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "")));
                        pj.setRazaoSocial(request.getParameter("clienteRazao"));
                        if (request.getParameter("clienteIE").length() > 0) {
                            pj.setInscricaoestadual(request.getParameter("clienteIE"));
                            pj.setEstadoemissor(new Estado(Integer.parseInt(request.getParameter("clienteEstadoIE"))));
                        }
                        
                        novo_cliente = pj;
                        break;
                    default:
                        response.sendRedirect("./");
                        break;
                }

                // Seta demais campos
                if (novo_cliente != null) {
                    // Contatos
                    if (request.getParameter("clienteTelResidencial").length() > 0) {
                        novo_cliente.setTelresidencial(request.getParameter("clienteTelResidencial"));
                    }
                    if (request.getParameter("clienteTelComercial").length() > 0) {
                        novo_cliente.setTelcomercial(request.getParameter("clienteTelComercial"));
                    }
                    if (request.getParameter("clienteTelCelular").length() > 0) {
                        novo_cliente.setTelcelular(request.getParameter("clienteTelCelular"));
                    }

                    // Credenciais
                    novo_cliente.setEmail(request.getParameter("clienteEmail"));
                    novo_cliente.setSenha(Hash.getHash(request.getParameter("clienteHashSenha"), Hash.BCRYPT));

                    // Endereço Principal
                    Endereco endereco = new Endereco();
                    endereco.setCep(Integer.parseInt(request.getParameter("clienteCEP").replace("-", "")));
                    endereco.setLogradouro(request.getParameter("clienteLogradouro"));
                    if (request.getParameter("clienteComplemento").length() > 0) {
                        endereco.setComplemento(request.getParameter("clienteComplemento"));
                    }
                    endereco.setBairro(request.getParameter("clienteBairro"));
                    endereco.setCidade(new Cidade(Integer.parseInt(request.getParameter("clienteCidade"))));
                    endereco.setPadrao(true);
                    novo_cliente.setEnderecos(new ArrayList<>());
                    novo_cliente.getEnderecos().add(endereco);

                    // Aqui o Cliente não será administrador
                    novo_cliente.setAdministrador(false);

                    // Gravar cliente no banco
                    ClienteDAO cli_dao = new ClienteDAO();
                    
                    try {
                        cli_dao.create(novo_cliente);

                        // Se cadastrou o cliente...
                        if (novo_cliente.getCodigo() != null) {
                            // Faz login e mostra o home
                            response.sendRedirect(
                                    String.format(
                                            "./Login.do?acao=login&usuarioEmail=%s&usuarioSenha=%s",
                                            request.getParameter("clienteEmail"),
                                            request.getParameter("clienteHashSenha")));
                        }
                    } catch (DatabaseException ex) {
                        response.sendRedirect("./");
                        Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
//                response.sendRedirect("./");
            }
        } //
        //
        else if (request.getParameter("verifEmail") != null) {
            try {
                
                ClienteDAO cli_dao = new ClienteDAO();
                boolean existe = (cli_dao.verificaEmail(request.getParameter("verifEmail")));
                
                try (PrintWriter out = response.getWriter()) {
                    out.print(existe);
                    
                }
            } catch (DatabaseException ex) {
                Logger.getLogger(RegisterServlet.class
                        .getName()).log(Level.SEVERE, null, ex);
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
