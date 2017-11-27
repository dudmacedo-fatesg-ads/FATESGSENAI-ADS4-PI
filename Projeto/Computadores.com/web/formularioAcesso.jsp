<%-- 
    Document   : formularioEmpresa
    Created on : 17/11/2017, 17:54:37
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
        <title>Identificacao</title>
    </head>
    <body>
        <jsp:include page="topo.jsp" />
        <section class="identificar">
            <div class="conteudo">
                <div class="linha">
                    <div class="formLogin">
                        <form method="POST" action='' name="frmAddUser">
                            <i class="fa fa-user"></i><h1>Login<span> Sou cliente</span></h1>
                            <p>Se voce já possuir cadastro, por gentileza, realizar o login em sua conta.</p>
                            <fieldset>
                                <input type="text" name="usuarioEmail" value="" placeholder="Digite Seu E-mail"/> <br />
                               
                                <input type="text" name="usuarioSenha" value="" placeholder="Digite Sua Senha"/> <br /> 
                            </fieldset>
                            <button>LOGIN</button>
                        </form>
                    </div><!-- formulario Login -->
                    
                    <div class="formCadastro">
                        <form method="POST" action='' name="frmAddUser">
                            <i class="fa fa-user-plus"></i><h1>Criar Conta<span> Sou Novo Cliente</span></h1>
                            <p>É a sua primeira compra em nosso site? Por favor, preencha com seu e-mail e CEP.</p>
                            <fieldset>
                                <input type="text" name="usuarioEmail" value="" placeholder="Digite Seu E-mail"/> <br />
                               
                                <input type="text" name="usuarioSenha" value="" placeholder="Digite Seu CEP"/> <br /> 
                            </fieldset>
                            <button>CONTINUAR</button>
                        </form>
                    </div><!-- formulario Login -->
                </div><!-- linha -->
            </div><!-- conteudo -->
        </section>
        <jsp:include page="rodape.jsp" />
    </body>
</html>
