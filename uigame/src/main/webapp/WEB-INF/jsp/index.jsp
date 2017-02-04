<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!-- Css File -->
<c:url value="/resources/css/bootstrap/bootstrap.min.css" var="css_bootstrap_url"/>
<c:url value="/resources/css/app/index.css" var="css_app_index_url"/>
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
       <div class="container">
            <div class="omb_login">
                <h3 class="omb_authTitle">Login or <a href="#">Sign up</a></h3>
                <div class="row omb_row-sm-offset-3 omb_socialButtons">
                    <div class="col-xs-4 col-sm-2">
                        <a href="${facebook_url }" class="btn btn-lg btn-block omb_btn-facebook">
                            <i class="fa fa-facebook visible-xs"></i>
                            <span class="hidden-xs">Facebook</span>
                        </a>
                    </div>
                    <div class="col-xs-4 col-sm-2">
                        <a href="#" class="btn btn-lg btn-block omb_btn-twitter">
                            <i class="fa fa-twitter visible-xs"></i>
                            <span class="hidden-xs">Twitter</span>
                        </a>
                    </div>  
                    <div class="col-xs-4 col-sm-2">
                        <a href="#" class="btn btn-lg btn-block omb_btn-google">
                            <i class="fa fa-google-plus visible-xs"></i>
                            <span class="hidden-xs">Google+</span>
                        </a>
                    </div>  
                </div>

                <div class="row omb_row-sm-offset-3 omb_loginOr">
                    <div class="col-xs-12 col-sm-6">
                        <hr class="omb_hrOr">
                        <span class="omb_spanOr">or</span>
                    </div>
                </div>

                <div class="row omb_row-sm-offset-3">
                    <div class="col-xs-12 col-sm-6">    
                        <form class="omb_loginForm" action="" autocomplete="off" method="POST">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="text" class="form-control" name="username" placeholder="email address">
                            </div>
                            <span class="help-block"></span>
                                                
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input  type="password" class="form-control" name="password" placeholder="Password">
                            </div>
                            <span class="help-block">Password error</span>

                            <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
                        </form>
                    </div>
                </div>
                <div class="row omb_row-sm-offset-3">
                    <div class="col-xs-12 col-sm-3">
                        <label class="checkbox">
                            <input type="checkbox" value="remember-me">Remember Me
                        </label>
                    </div>
                    <div class="col-xs-12 col-sm-3">
                        <p class="omb_forgotPwd">
                            <a href="#">Forgot password?</a>
                        </p>
                    </div>
                </div>          
            </div>
        </div>
    </body>
</html>