<%-- 
    Document   : administracao
    Created on : 01/12/2017, 10:57:25
    Author     : eduardo
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<section id="principal">
    <div class="conteudo conteudoMain">
        <div class="menuLateral">
            <div class="lista">
                <ul class="menuLista">
                    <li class="N1">
                        > Produtos
                    </li>
                    <li class="N2">
                        <a href="#">Cadastro de Produtos</a>
                    </li>
                    <li class="N2">
                        <a href="./Administracao?funcao=listCategoria">Cadastro de Categoria</a>
                    </li>
                    <li class="N2">
                        <a href="./Administracao?funcao=listMarca">Cadastro de Marca</a>
                    </li>
                    > Clientes
                    </li>
                    <li class="N2">
                        <a href="./Administracao?funcao=listCliente">Cadastro de Cliente</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="paginaFuncao">
            <div class="funcao">
                <c:if test="${funcao.equals('listCategoria')}">
                    <%@include file = "../pages/listCategoria.jsp" %>
                </c:if>
                <c:if test="${funcao.equals('listMarca')}">
                    <%@include file = "../pages/listMarca.jsp" %>
                </c:if>
                <c:if test="${funcao.equals('listCliente')}">
                    <%@include file = "../pages/listClienteAdm.jsp" %>
                </c:if>
            </div>
        </div>
    </div>
</section>