<%-- 
    Document   : cadastroProduto
    Created on : 27/11/2017, 19:38:30
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="media/css/estilos.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
        <title>Cadastrar Produto</title>
    </head>
    <body>
        <jsp:include page="topo.jsp" />
        <div class="cadastroProduto">
            <form method="POST" action='' name="frmAddUser">
                <fieldset>
                    <label>Nome do Produto</label>
                    <input name="" type="" value="" placeholder="Nome do Produto"/>
                    
                    <label>Categoria</label>
                    <select>
                        <option value="1">Computador</option>
                        <option value="2">Notebook</option>
                        <option value="3">Hardware</option>
                        <option value="4">Software</option>
                        <option value="5">Monitores</option>
                        <option value="6">Outros Produtos</option>
                    </select>
                    
                    <label>Fábricante</label>
                    <select>
                        <option value="1">Dell</option>
                        <option value="2">Apple</option>
                        <option value="3">Samsung</option>
                        <option value="">Globaltech</option>
                        <option value="5">NVIDIA</option>
                        <option value="6">AMD</option>
                    </select>    
                    
                    <label>Preço</label>
                    <input name="" type="" placeholder="valor do produto"/>
                </fieldset>
            </form>
        </div>
        
        
        <jsp:include page="rodape.jsp" />
    </body>
</html>
