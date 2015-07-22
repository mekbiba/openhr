<%@include file="../common/jspHeader.jsp" %>
<div id="loginFormContainer">
    <html:form action="/LoginAction" styleClass="loginForm">
        <h2 class="login-legend">Login
            <span style="float:right;padding-right:40px;">
                <a class="link" href="<%=request.getContextPath() + "/do/ChangeLanguage?method=amharic" %>">
                    <span><img src="<%=request.getContextPath() + "/css/images/et_flag.png"%>"/></span>
                </a>
                <a class="link" href="<%=request.getContextPath() + "/do/ChangeLanguage?method=english" %>">
                    <span><img src="<%=request.getContextPath() + "/css/images/us_flag.png"%>"/></span>
                </a> 
            </span>


        </h2>
        <div>

            <label><bean:message key="userName"/></label><br />
            <html:text property="username" value=""  /><br />

            <label><bean:message key="password"/></label><br />
            <html:password property="password" value="" /><br /><br />

            <a id="forgotPassword" class="k-link">
                <bean:message key="resetPassword"/>?</a><br /><br />

            <div class="roleSelectorWindow" style="display:none;padding:0;">
                <label>Select Role</label>
                <select id="loginAs">
                    <option value="Employee"><bean:message key="employee"/></option>
                    <option value="HR">Human Resource</option>
                    <option value="Accountant">Accountant</option>
                    <option value="Administrator"><bean:message key="pageAdmin"/></option>
                </select>
                <a id="setLoginAs" class="k-button"><bean:message key="ok"/></a>
            </div>
            <html:hidden property="role" styleId="isAdmin" value="false"/> 
            <a id="submitForm" href="#" class="k-button" >
                <bean:message key="login"/>
            </a>
        </div>
    </html:form>
</div>


<script>
    $(document).ready(function() {

        $("#submitForm").bind("click", function(e) {
            $("#submitForm").css("display", "none");
            e.preventDefault();
            $(".roleSelectorWindow").slideToggle("fast");
            $("#loginAs").kendoDropDownList();
        });
        $("#setLoginAs").bind("click", function() {
            //validate roleSelector3		   
            var loginAs = $("#loginAs").val();
            $("#isAdmin").val(loginAs);

            $(".loginForm").submit();
        });
    });

</script>
<style scoped>
    a.link{
        text-decoration: none;
    }
</style>