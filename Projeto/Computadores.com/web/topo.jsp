<%@page import="com.computadores.model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header>
    <section id="topo">
        <div class="menu">
            <a href="./">
                <img src="media/imagens/logo/logoTransparente.png">
                <h1>Computadores.com</h1>
            </a>
        </div>
        <div class="menuauxiliar">
            <ul>

                <%
                    if (session.getAttribute("usuarioObj") != null) {
                %>
                <li>
                    <a href="#">
                        <i class="fa fa-user"></i>Olá <%= session.getAttribute("usuarioNome")%>
                    </a>
                </li>
                <%
                    if (((Cliente) session.getAttribute("usuarioObj")).isAdministrador()) {
                %>
                <li>
                    <a href="./Administracao">
                        <i class="fa fa-user"></i>Administração
                    </a>
                </li>
                <%
                    }
                } else {
                %>
                <li>
                    <a href="Login.do">
                        <i class="fa fa-user"></i>Olá Visitante
                    </a>
                </li>
                <%
                    }
                %>


                <li>
                    <a href="#">
                        <i class="fa fa-comments-o"></i>Fale Conosco
                    </a>
                </li>

                <li>
                    <a href="#">
                        <i class="fa fa-shopping-cart"></i>Carrinho de Compras
                    </a>
                </li>
                <%
                    if (session.getAttribute("usuarioObj") != null) {
                %>
                <li>
                    <a href="Login.do?acao=logout">
                        <i class="fa fa-user"></i>Sair
                    </a>
                </li>
                <%
                    }
                %>
            </ul> 
        </div><!-- menu -->
    </section><!--topo-->
</header>
<div class="clear"></div>