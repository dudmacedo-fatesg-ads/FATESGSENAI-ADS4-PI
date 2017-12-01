<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<section id="cadastroCliente">
    <div class="conteudo">
        <div class="linha">
            <div class="formularioCadastro">
                <form method="POST" id="formEditCliente" action="Register.do">
                    <input type="hidden" name="acao" value="registrarcliente"/>
                    <fieldset>
                        <h1>Dados Pessoais</h1>
                        <ul class="listaPincipal">
                            <li>
                                <ul class="dadosPessoais">
                                    <h2><span>Seus Dados</span></h2>
                                    <li>
                                        <label>Tipo de Pessoa</label>
                                        <select name="clienteTipo" id="clienteTipo">
                                            <option value="F" selected>Pessoa Física</option>
                                            <option value="J">Pessoa Jurídica</option>
                                        </select>

                                        <div id="clientePFCampos">
                                            <label>Nome Completo (*)</label>
                                            <input name="clienteNome" id="clienteNome" type="text" placeholder="Insira seu nome completo" />

                                            <label>CPF (*)</label>
                                            <input name="clienteCPF" id="clienteCPF" type="text" placeholder="xxx.xxx.xxx-xx"/>

                                            <label>RG (*)</label>
                                            <input name="clienteRG" id="clienteRG" type="number" placeholder="ex: 12.34.567"/>

                                            <label>Data de Nascimento (*)</label>
                                            <input name="clienteNascimento" id="clienteNascimento" type="date"/>
                                        </div>

                                        <div id="clientePJCampos">
                                            <label>Razão Social (*)</label>
                                            <input name="clienteRazao" id="clienteRazao" type="text" placeholder="Insira a Razão Social" />

                                            <label>CNPJ (*)</label>
                                            <input name="clienteCNPJ" id="clienteCNPJ" type="text" placeholder="xx.xxx.xxx/xxxx-xx"/>

                                            <label>Inscrição Estadual</label>
                                            <input name="clienteIE" id="clienteIE" type="text" placeholder="ex: 12.34.567"/>

                                            <label>Estado Emissor da Inscrição Estadual</label><br>
                                            <select name="clienteEstadoIE" id="clienteEstadoIE">
                                                <c:forEach items="${estadosie}" var="estado">
                                                    <option value="<c:out value="${estado.codigo}"/>" <c:if test="${pre_endereco.cidade.estado.codigo == estado.codigo}">selected</c:if> ><c:out value="${estado.sigla}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </li>
                                </ul>
                            </li>


                            <li>
                                <ul class="dadosEndereco">
                                    <h2><span>Endereço</span></h2>
                                    <li>
                                        <label>CEP (*)</label>
                                        <input name="clienteCEP" id="clienteCEP" type="text" value="<c:out value="${pre_endereco.cepformatado}"/>" placeholder="xxxxx-xxx"/>

                                        <label>Logradouro (*)</label>
                                        <input name="clienteLogradouro" id="clienteLogradouro" type="text" value="<c:out value="${pre_endereco.logradouro}"/>" placeholder="Rua, Avenida, Alameda..."/>

                                        <label>Complemento</label>
                                        <input name="clienteComplemento" id="clienteComplemento" type="text" value="<c:out value="${pre_endereco.complemento}"/>" placeholder="Número, Quadra, Lote, Edificio, Apto..."/>

                                        <label>Bairro (*)</label>
                                        <input name="clienteBairro" id="clienteBairro" type="text" value="<c:out value="${pre_endereco.bairro}"/>" placeholder="ex: Setor Bueno"/>

                                        <label>Estado (*)</label>
                                        <select name="clienteEstado" id="clienteEstado">
                                            <c:forEach items="${estadoscliente}" var="estado">
                                                <option value="<c:out value="${estado.codigo}"/>" <c:if test="${pre_endereco.cidade.estado.codigo == estado.codigo}">selected</c:if> ><c:out value="${estado.sigla}"/></option>
                                            </c:forEach>
                                        </select>

                                        <label>Cidade (*)</label>
                                        <select name="clienteCidade" id="clienteCidade">
                                            <c:forEach items="${cidades}" var="cidade">
                                                <option value="<c:out value="${cidade.codigo}"/>" <c:if test="${pre_endereco.cidade.codigo == cidade.codigo}">selected</c:if> ><c:out value="${cidade.nome}"/></option>
                                            </c:forEach>
                                        </select>
                                    </li>
                                </ul> 
                            </li>

                            <li>
                                <ul class="dadosContato">
                                    <h2><span>Seus Contatos</span></h2>
                                    <li>
                                        <label>Telefone Residencial</label>
                                        <input name="clienteTelResidencial" id="clienteTelResidencial" type="text" placeholder="(xx) xxxx-xxxx"/>

                                        <label>Telefone Comercial</label>
                                        <input name="clienteTelComercial" id="clienteTelComercial" type="text" placeholder="(xx) xxxx-xxxx"/>

                                        <label>Telefone Celular</label>
                                        <input name="clienteTelCelular" id="clienteTelCelular" type="text" placeholder="(xx) xxxxx-xxxx"/>
                                    </li>
                                </ul> 
                            </li>

                            <li>
                                <ul class="dadosAcesso">
                                    <h2><span>Dados de Acesso</span></h2>
                                    <li>
                                        <label>E-mail (*)</label>
                                        <input name="clienteEmail" id="clienteEmail" type="email" maxlength="80" value="<c:out value="${clienteEmail}" />" placeholder="fulano@dominio.com"/>

                                        <label>Senha (*)</label>
                                        <input name="clienteSenha" id="clienteSenha" type="password" placeholder="Insira sua senha"/>

                                        <label>Confirmar Senha (*)</label>
                                        <input name="clienteConfirmaSenha" id="clienteConfirmaSenha" type="password" placeholder="Confirme a senha"/>

                                        <input type="hidden" name="clienteHashSenha" id="clienteHashSenha" value="" />
                                    </li>
                                </ul>
                            </li>        
                        </ul>
                    </fieldset>

                    <button id="btnEnviar" type="submit" class="enviar">Enviar</button>
                    <button id="btnCancelar" class="cancelar">Cancelar</button>
                </form>


            </div><!-- formularioCadastro -->
        </div><!-- linha -->
    </div><!-- conteudo -->
</section>
<script type="text/javascript" src="media/js/editCliente.js"></script>