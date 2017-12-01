<%-- 
    Document   : listClienteAdm
    Created on : 01/12/2017, 16:47:13
    Author     : eduardo
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<section id="list">
    <div class="conteudo">
        <div class="linha">
            <div class="listagem">
                <div class="pesquisar">
                    <label>Pesquisar</label>
                    <input type="text" id="txtBuscar" placeholder="buscar usuario"/>
                    <button id="btnBuscar" alt="buscar">Buscar</button>
                </div><!-- pesquisar -->
                <table border=1>
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Tipo</th>
                            <th>CPF/CNPJ</th>
                            <th>Nome/Razão</th>
                            <th>E-mail</th>
                            <th colspan=2>Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${clientes}" var="cliente">
                            <tr>
                                <td><c:out value="${cliente.codigo}" /></td>
                                <td><c:out value="${cliente.tipo}" /></td>
                                <c:if test="${cliente.tipo.id.equals('F')}">
                                    <td><c:out value="${cliente.cpf_formatado}" /></td>
                                    <td><c:out value="${cliente.nome}" /></td>
                                </c:if>
                                <c:if test="${cliente.tipo.id.equals('J')}">
                                    <td><c:out value="${cliente.cnpj_formatado}" /></td>
                                    <td><c:out value="${cliente.razaoSocial}" /></td>
                                </c:if>
                                <td><c:out value="${cliente.email}" /></td>
                                <td><a id="btnAtualizar" href="#">Atualizar</a><br><a id="btnApagar" href="#">Apagar</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>