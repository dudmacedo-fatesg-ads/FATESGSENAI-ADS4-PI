<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="identificar">
    <div class="conteudo">
        <div class="linha">
            <div class="formLogin">
                <form method="POST" action="Login.do" id="formLogin">
                    <input type="hidden" name="acao" value="login"/>
                    <i class="fa fa-user"></i><h1>Login<span> - Já sou cliente</span></h1>
                    <p>Se voce já possuir cadastro, por gentileza, realizar o login em sua conta.</p>
                    <fieldset>
                        <input type="email" name="usuarioEmail" id="loginEmail" maxlength="80" placeholder="Digite Seu E-mail"/> <br />

                        <input type="password" name="usuarioSenha" id="loginSenha" placeholder="Digite Sua Senha"/> <br /> 
                    </fieldset>
                    <button type="submit">LOGIN</button>
                </form>
            </div>

            <div class="formCadastro">
                <form method="POST" action="Register.do">
                    <input type="hidden" name="acao" value="novoregistro"/>
                    <i class="fa fa-user-plus"></i><h1>Criar Conta<span> - Quero ser cliente</span></h1>
                    <p>É a sua primeira compra em nosso site? Por favor, preencha com seu e-mail e CEP.</p>
                    <fieldset>
                        <input type="email" name="usuarioEmail" maxlength="80" placeholder="Digite Seu E-mail"/> <br />

                        <input type="text" name="usuarioCEP" id="usuarioCEP" placeholder="Digite Seu CEP"/> <br /> 
                    </fieldset>
                    <button type="submit">CONTINUAR</button>
                </form>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="media/js/formularioAcesso.js"></script>
<c:if test="${msg != null}">
    <script type="text/javascript">
        alert('<c:out value="${msg}" />');
    </script>
</c:if>
