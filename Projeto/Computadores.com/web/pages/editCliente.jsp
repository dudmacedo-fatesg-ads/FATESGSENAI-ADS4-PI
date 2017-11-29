<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<section id="cadastroCliente">
    <div class="conteudo">
        <div class="linha">
            <div class="formularioCadastro">
                <form method="POST" action='' name="frmAddUser">
                    <fieldset>
                        <label>Cadastrar Cliente</label>
                        <select>
                            <option value="1">Pessoa Física</option>
                            <option value="2">Pessoa Jurídica</option>
                        </select>

                        <!-- PESSOA FÍSICA -->
                        <label>Nome Completo</label>
                        <input name="clienteNome" type="text" placeholder="Insira seu nome completo" />

                        <label>CPF</label>
                        <input name="clienteFisicoCPF" type="text" placeholder="ex: 123.456.789-00"/>

                        <label>RG</label>
                        <input name="clienteFisicoRG" type="text" placeholder="ex: 12.34.567"/>

                        <label>Data de Nascimento</label>
                        <input name="clienteFisicoNascimento" type="date"/>

                        <label>Telefone Residencial</label>
                        <input name="clienteTelResidencial" id="clienteTelResidencial" type="text" placeholder="(xx) xxxx-xxxx"/>

                        <label>Telefone Comercial</label>
                        <input name="clienteTelComercial" id="clienteTelComercial" type="text" placeholder="(xx) xxxx-xxxx"/>

                        <label>Telefone Celular</label>
                        <input name="clienteTelCelular" id="clienteTelCelular" type="text" placeholder="(xx) xxxxx-xxxx"/>

                        <label>E-mail</label>
                        <input name="clienteEmail" type="text" maxlength="80" value="<c:out value="${clienteEmail}" />" placeholder="fulano@dominio.com"/>

                        <label>Senha</label>
                        <input name="clienteSenha" type="password" placeholder="Insira sua senha"/>

                        <label>Confirmar Senha</label>
                        <input name="clienteConfirmaSenha" type="password" placeholder="Confirme a senha"/>

                        <label>CEP</label>
                        <input name="clienteCEP" type="text" value="<c:out value="${pre_endereco.cepformatado}"/>" placeholder="12.345-678"/>

                        <label>Logradouro</label>
                        <input name="clienteLogradour" type="text" value="<c:out value="${pre_endereco.logradouro}"/>" placeholder="12.345-678"/>

                        <label>Complemento</label>
                        <input name="clienteComplemento" type="text" value="<c:out value="${pre_endereco.complemento}"/>" placeholder="ex: edificil, apartamento, torre."/>

                        <label>Bairro</label>
                        <input name="clienteBairro" type="text" value="<c:out value="${pre_endereco.bairro}"/>" placeholder="ex: Setor Bueno"/>

                        <label>Estado</label>
                        <select name="clienteEstado" id="clienteEstado">
                            <c:forEach items="${estados}" var="estado">
                                <option value="<c:out value="${estado.codigo}"/>" <c:if test="${pre_endereco.cidade.estado.codigo == estado.codigo}">selected</c:if> ><c:out value="${estado.sigla}"/></option>
                            </c:forEach>
                        </select>

                        <label>Cidade</label>
                        <select name="clienteCidade" id="clienteCidade">
                            <c:forEach items="${cidades}" var="cidade">
                                <option value="<c:out value="${cidade.codigo}"/>" <c:if test="${pre_endereco.cidade.codigo == cidade.codigo}">selected</c:if> ><c:out value="${cidade.nome}"/></option>
                            </c:forEach>
                        </select>
                    </fieldset>                            
                </form>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="media/js/editCliente.js"></script>