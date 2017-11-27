<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header>
    <section id="topo">
        <div class="menu">
            
            <img src="media/imagens/logo/logoTransparente.png">
            <h1>Computadors.com</h1>
            <ul>
                <li>
                    <a href="#">
                        <i class="fa fa-user"></i>Olá Visitante
                    </a>
                </li>
                
                <li>
                    <a href="#">
                        <i class="fa fa-comments-o"></i>Fale Conosco
                    </a>
                </li>
                
                <li>
                    <a href="#">
                        <i class="fa fa-shopping-cart"></i>Carrinho de Compras
                    </a>
                </li>
            </ul> 
        </div><!-- menu -->
        
        
        <div class="categorias">
            <form method="POST" action='' name="frmAddUser">
                <select name="categorias">
                    <option value="0">Categorias</option>
                    <option value="1">Computadores</option>
                    <option value="2">Notebooks</option>
                    <option value="3">Parceiros</option>
                    <option value="4">Peças</option>
                </select>               
                <input input type="text" name="psquisar" value="" placeholder="pesquise aqui"/>
            </form>
        </div>
        
    </section><!--topo-->
</header>
<div class="clear"></div>