<%@include file="../common/jspHeader.jsp"%>
<ul class="menu">

    <li>
        <a href="<%=request.getContextPath() + "/do/MNotices" %>">
            Notices</a>
    </li>

    <li>
        <a href="<%=request.getContextPath() + "/do/MLeave" %>">
            Your Leaves</a>
    </li>

    <li>
        Your Benefits
        <ul>
            <li>
                <a href="<%=request.getContextPath() + "/do/MOvertime" %>">
                    Overtime
                </a>
            </li>
            <li>
                <a href="<%=request.getContextPath() + "/do/MLoan" %>">
                    Loan
                </a>
            </li>
            <li>
                <a href="<%=request.getContextPath() + "/do/MHealth" %>">
                    Health Insurance
                </a>
            </li>
        </ul>
    </li>

    <li>
        <a href="#" id="accountSettings">Account Settings</a>
    </li>

</ul>




<!-- Account Setting Starts here -->
<div class="k-content" id="accountSettingsWnd" style="display:none">
    <div>
        <div >
            <div id="form">
                <div>
                    <input type="button" id="makeEditable" value="Edit"/>
                    <input type="button" value="Cancel"/>


                    <div>
                        <div class="form-label">
                            <label>Username</label>
                        </div>
                        <div class="form-field">
                            <input class="k-input k-textbox" type="text" id="newUsername"
                                   disabled="disabled"
                                   value="<%=request.getSession().getAttribute("loggedUser")
					.toString()%>" />
                        </div>
                        <div style="clear: both"></div>
                    </div>

                    <div>
                        <div class="form-label">
                            <label>Old Password</label>
                        </div>
                        <div class="form-field">
                            <input class="k-input k-textbox" type="password" id="oldPassword"
                                   disabled="disabled" value="" />
                        </div>
                        <div style="clear: both"></div>
                    </div>

                    <div>
                        <div class="form-label">
                            <label>New Password</label>
                        </div>
                        <div class="form-field">
                            <input class="k-input k-textbox" type="password" id="newPassword"
                                   disabled="disabled" value="" />
                        </div>
                        <div style="clear: both"></div>
                    </div>

                    <div>
                        <div class="form-label">
                            <label>Confirm Password</label>
                        </div>
                        <div class="form-field">
                            <input class="k-input k-textbox" type="password"
                                   id="confirmPassword" disabled="disabled" value="" />
                        </div>
                        <div style="clear: both"></div>
                    </div>

                    <input type="hidden" id="formIsActive" value="false"/>
                </div>

            </div>
        </div>

    </div>
</div>
<!-- end of Account setting -->
<script>
    $("#accountSettings").click(function(e) {
        $("#accountSettingsWnd").css("display", "block");
        var accountSettingWnd = $("#accountSettingsWnd").kendoWindow({
            modal: true,
            title: "Account Settings"
        }).data("kendoWindow");
        accountSettingWnd.center();
        accountSettingWnd.open();
    });


    $("#makeEditable").click(function(e) {
        var formIsActive = $("#formIsActive").val();

        if (formIsActive == 'false') {
            $("#newUsername").removeAttr('disabled');
            $("#oldPassword").removeAttr('disabled');
            $("#newPassword").removeAttr('disabled');
            $("#confirmPassword").removeAttr('disabled');
            $("#saveChanges").removeAttr('disabled');
            $("#formIsActive").val('true');
            $("#makeEditable").val('Done');

        } else {
            //call form data validator

            //validation succeeded

            //collect form data and prepare for sever call in json format
            var oldUserName = '<%=request.getSession().getAttribute("loggedUser").toString()%>';
            var newUserName = $("#newUsername").val();
            var newPassword = $("#newPassword").val();


            var userUpdateDataJSON = JSON.stringify([{"oldUserName": oldUserName,
                    "newUserName": newUserName, "newPassword": newPassword}]);



            $.ajax({
                url: "<%=request.getContextPath() + "/do/UpdateUserAction"%>",
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                type: 'POST',
                data: userUpdateDataJSON,
                success: function() {
                    alert('Account Settings are updated successfully');
                }
            });

            $("#newUsername").attr('disabled', 'disabled');
            $("#oldPassword").attr('disabled', 'disabled');
            $("#newPassword").attr('disabled', 'disabled');
            $("#confirmPassword").attr('disabled', 'disabled');
            $("#saveChanges").attr('disabled', 'disabled');
            $("#formIsActive").val('false');
            $("#makeEditable").val('Edit');
        }
    });
</script>