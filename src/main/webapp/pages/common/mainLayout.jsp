<%@include file="jspHeader.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:getAsString name="title" /></title>
        <link href="<%=request.getContextPath() + "/css/mainLayout.css"%>" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath() + "/css/global.css"%>" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath() + "/css/home.css"%>" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath() + "/css/kendo.common.min.css"%>" rel="stylesheet" type="text/css">
        <link href="<%=request.getContextPath() + "/css/kendo.default.min.css"%>" rel="stylesheet" type="text/css">

        <link href="<%=request.getContextPath() + "/css/jquery.Jcrop.min.css"%>" rel="stylesheet" type="text/css">  




        <script type="text/javascript" src="<%=request.getContextPath() + "/javascript/jquery.min.js"%>"></script>
        <script type="text/javascript" src="<%=request.getContextPath() + "/javascript/kendo.web.min.js"%>"></script>
        <script type="text/javascript" src="<%=request.getContextPath() + "/javascript/kendo.chart.min.js"%>"></script>
        <script type="text/javascript" src="<%=request.getContextPath() + "/javascript/jquery.maskedinput-1.2.2.js"%>"></script>
        <script type="text/javascript" src="<%=request.getContextPath() + "/javascript/ajaxfileupload.js"%>"></script>
        <script type="text/javascript" src="<%=request.getContextPath() + "/javascript/jquery.color.js"%>"></script>
        <script type="text/javascript" src="<%=request.getContextPath() + "/javascript/jquery.Jcrop.min.js"%>"></script>
        <script type="text/javascript" src="<%=request.getContextPath() + "/javascript/jquery.jqprint-0.3.js"%>"></script>
        <script type="text/javascript" src="<%=request.getContextPath() + "/javascript/jquery.dateformat.js"%>"></script>


    </head>
    <body>
        <div id="wrapper">
            <%if (request.getSession().getAttribute("loggedUser") != null) {
                     if(!request.getSession().getAttribute("loggedUser").toString().equalsIgnoreCase("")){%>
            <div id="header">
                <a href="#" class="logo"></a>
                <div id="user-box">
                    <p><a class="k-link" href="<%out.print(request.getContextPath() + "/do/LogoutAction");%>">[ Log out ]</a></p>                            
                    <%
                         out.println("<p style='font-size:11px;'>Welcome <b>" + request.getSession().getAttribute("loggedUser") + "!</b></p>");
                    %>
                    <!-- <span style="float:right;padding-right:40px;">
                    <a class="link" href="<%=request.getContextPath() + "/do/ChangeLanguage?method=amharic" %>">
                            <span><img src="<%=request.getContextPath() + "/css/images/et_flag.png"%>"</span>
                    </a>
                            <a class="link" href="<%=request.getContextPath() + "/do/ChangeLanguage?method=english" %>">
                                    <span><img src="<%=request.getContextPath() + "/css/images/us_flag.png"%>"</span>
                            </a> 
            </span>-->
                </div>               

                <div id="navigation">
                    <tiles:insert name="navigation"/>
                </div>
            </div>
            <%}else{
                        response.sendRedirect(request.getContextPath() + "/do/LogoutAction");       
                   }
            }%>            
            <div id="main-content">                
                <div id="column-two">
                    <tiles:insert attribute="body"/>
                </div>
                <div style="clear:both;"></div>
            </div>

            <%if (request.getSession().getAttribute("loggedUser") != null) {
                     if(!request.getSession().getAttribute("loggedUser").toString().equalsIgnoreCase("")){%>
            <div id="footer"><p>&copy;2012 OpenHR open source group</p></div>
            <%}
           } %>
        </div>
        <script>
            $(document).ready(function(e) {


                $(".menu").kendoMenu();



                //checks if the user is not on the login page
                var onLoginPage = false;

                var currentPath = "<%=request.getContextPath()%>";

                //alert(currentPath);

                if (!onLoginPage) {
                    //checkSessionTimeout();
                }


                $("#calendar").kendoCalendar({
                    value: new Date(),
                    min: new Date(1950, 0, 1),
                    max: new Date(2049, 11, 31),
                    change: function(e) {
                        alert($("#calendar").data("kendoCalendar").value());
                    }
                });
            });



            function checkSessionTimeout() {
                var sessionExpired = "<%=request.getSession() != null ? "false" : "true"%>";
                if (sessionExpired) {
                    window.location = "<%=request.getContextPath()+"/do/LogoutAction"%>";
                }
                setTimeout('checkSessionTimeout', 5000);
            }
        </script>
    </body>



</html>
