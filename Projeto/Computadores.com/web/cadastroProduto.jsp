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
                    
                    <label>Descrição</label>
                    <textarea rows="180" cols="180"></textarea>
                    
                    <label>Categoria</label>
                    <select>
                        <option value="1">Computador</option>
                        <option value="2">Notebook</option>
                        <option value="3">Hardware</option>
                        <option value="4">Software</option>
                        <option value="5">Monitores</option>
                        <option value="6">Outros Produtos</option>
                    </select>
                    
                    <label>Marca</label>
                    <select>
                        <option value="1">Dell</option>
                        <option value="2">Apple</option>
                        <option value="3">Samsung</option>
                        <option value="">Globaltech</option>
                        <option value="5">NVIDIA</option>
                        <option value="6">AMD</option>
                    </select>    
                    
                    <label>Quantidade</label>
                    <input name="quantidadeproduto" type="" value="" placeholder="informe a quantidade do produto"/>
                    
                    
                    <label>Valor</label>
                    <input name="" type="" placeholder="valor do produto"/>
                    
                    <label>Produto Personalizável?</label>
                    <input type="radio" name="produtopersonalizar" value="true"> Sim<br>
                    <input type="radio" name="produtopersonalizar" value="false"> Não<br>
                    
                    <label>Status do produto</label>
                    <input type="radio" name="produtostatus" value="emestoque"> Em estoque<br>
                    <input type="radio" name="produtostatus" value="semestoque"> Sem estoque<br>
                    <input type="radio" name="produtostatus" value="acabandoestoque"> Acabando estoque<br>
                    
                    <button type="button" onclick="alert('Produto salvo!')">Salvar</button>
                    <button>Cancelar</button>
                    <button type="button" onclick="alert('Campos Limpos!')">Limpar</button>
                </fieldset>
            </form>
        </div>
        
        
        <jsp:include page="rodape.jsp" />
    </body>
</html>
