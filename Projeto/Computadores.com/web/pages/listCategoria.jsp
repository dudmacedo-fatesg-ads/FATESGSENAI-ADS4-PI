<%-- 
    Document   : listCategoria
    Created on : 01/12/2017, 16:15:12
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
                    <input type="text" id="txtBuscar" placeholder="Buscar"/>
                    <button id="btnBuscar" alt="buscar">Buscar</button>
                </div><!-- pesquisar -->
                <table border=1>
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nome</th>
                            <th>Categoria Pai</th>
                            <th colspan=2>Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${categorias}" var="categoria">
                            <tr>
                                <td><c:out value="${categoria.codigo}" /></td>
                                <td><c:out value="${categoria.nome}" /></td>
                                <td><c:if test="${categoria.pai != null}"><c:out value="${categoria.pai.nome}" /></c:if></td>
                                <td><a href="#">Editar</a></td>
                                <td><a href="#">Apagar</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>