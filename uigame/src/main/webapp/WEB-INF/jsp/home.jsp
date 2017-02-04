<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!-- Css File -->
<c:url value="/resources/css/bootstrap/bootstrap.min.css" var="css_bootstrap_url"/>
<c:url value="/resources/css/app/home.css" var="css_app_index_url"/>
<!-- Js File -->
<c:url value="/resources/js/bootstrap/bootstrap.min.js" var="js_bootstrap_url"/>
<c:url value="/resources/js/app/index.js" var="js_app_index_url"/>

<!--  -->
<c:url value="/auth/facebook" var="facebook_url" />
<html>
    <head>
        <meta charset="utf-8">        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${css_bootstrap_url}">
        <link rel="stylesheet" href="${css_app_index_url}">
        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="${js_bootstrap_url}"></script>
        <script src="${js_app_index_url}"></script>
    </head>
    <body>
       <div class="header">
            <div id = "header_container">
                <div id="logo">
                    Akoalita
                </div>
                <div id="user">
                    <span id = "name">${username}</span>
                </div>
                <div id = "pop_pup_1">
                        456456132123
                </div>
            </div>
       </div>
       <div class="container">

        </div>
    </body>
</html>