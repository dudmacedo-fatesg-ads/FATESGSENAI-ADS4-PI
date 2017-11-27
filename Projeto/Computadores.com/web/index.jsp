<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="media/css/estilos.css" rel="stylesheet" />
        <link type="text/css" href="media/css/slick.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/media/js/jquery.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/media/js/slick.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/media/js/index.js"></script>
        <title>Home</title>
    </head>
    <body>
        <jsp:include page="topo.jsp" />
        <div class="banner">
                    <ul class="lista_banner">
                        <li class="lista_item">
                            <img src="media/imagens/um.png">
                        </li>
                        <li class="lista_item">
                            <img src="media/imagens/dois.png">
                        </li>
                        <li class="lista_item">
                            <img src="media/imagens/image.jpg">
                        </li>
                    </ul><!-- lista banner -->
                </div><!-- banner -->
        
        <h1></h1>
        
        <jsp:include page="rodape.jsp" />
    </body>
</html>
