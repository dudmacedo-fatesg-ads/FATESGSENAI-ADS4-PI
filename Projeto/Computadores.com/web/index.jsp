<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html5>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="media/css/estilos.css" rel="stylesheet" />
        <link type="text/css" href="media/css/slick.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
        <script type="text/javascript" src="media/js/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="media/js/jquery.maskedinput.min.js"></script>
        <script type="text/javascript" src="media/js/slick.js"></script>
        <script type="text/javascript" src="media/js/index.js"></script>
        <script type="text/javascript" src="media/js/md5.js"></script>
        <title>Computadores.com - A sua loja on-line de informática</title>
    </head>
    <body>
        <jsp:include page="topo.jsp" />

        <% if (request.getParameter("p") == null || request.getParameter("p").equalsIgnoreCase("home")) { %>
        <%@include file = "pages/home.jsp" %>
        <%} else if (request.getParameter("p").equalsIgnoreCase("login")) { %>
        <%@include file = "pages/formularioAcesso.jsp" %>
        <%} else if (request.getParameter("p").equalsIgnoreCase("editCliente")) { %>
        <%@include file = "pages/editCliente.jsp" %>
        <%} else if (request.getParameter("p").equalsIgnoreCase("administracao")) { %>
        <%@include file = "pages/administracao.jsp" %>
        <%}%>

        <h1></h1>

        <jsp:include page="rodape.jsp" />
    </body>
</html>
