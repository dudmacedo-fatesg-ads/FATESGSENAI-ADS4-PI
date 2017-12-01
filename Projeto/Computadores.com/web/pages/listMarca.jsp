<%-- 
    Document   : listMarca
    Created on : 01/12/2017, 17:41:13
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
                            <th colspan=2>Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${marcas}" var="marca">
                            <tr>
                                <td><c:out value="${marca.codigo}" /></td>
                                <td><c:out value="${marca.nome}" /></td>
                                <td><a id="btnAtualizar" href="#">Atualizar</a><br><a id="btnApagar" href="#">Apagar</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>