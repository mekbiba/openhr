<%@page import="com.openhr.Config"%> 
<%@include file="../../common/jspHeader.jsp"%>
<%
        Config.readConfig();
%>
<div style="border: 1px outset #999; padding: 20px ; margin : 10px auto; width:600px;">
    <h2 style="font-size: 20px;margin-bottom: 15px; background-color: #036;padding: 4px;color: white;">Global System Settings</h2>
    <div style="float: left; padding-top: 50px; padding-right: 30px; width : 80px; height : 100px;">
        <img width=110 height=100 alt="" src="<%=request.getContextPath()
					+ "/css/images/settings-banner.png"%>" />
    </div>

    <div style="float:right; padding-right:180px;">
        <input type="checkbox"
               style="font-size: 13px;" id="makeEditable" value="edit" /><span style="padding: 2px;">Edit</span><br />
        <label class="lbl" class="lbl">Company Name</label><br /> 
        <input class="k-input k-textbox" type="text" id="companyName" disabled="disabled" value="Your Company Name"/><br />


        <label class="lbl">Address 1</label><br /> 
        <input class="k-input k-textbox" type="text" id="address1" disabled="disabled" value="Address"/><br />

        <label class="lbl">Address 2(optional)</label><br /> 
        <input class="k-input k-textbox" type="text" id="address2" disabled="disabled" value="Address"/><br />

        <label class="lbl">Telephone</label><br /> 
        <input class="k-input k-textbox" type="text" id="telephone" disabled="disabled" value="+2510245478"/><br />

        <label class="lbl">P.O.Box</label><br /> 
        <input class="k-input k-textbox" type="text" id="pobox" disabled="disabled" value="4521"/><br />

        <label class="lbl">Employee Id Prefix</label><br /> 
        <input class="k-input k-textbox" type="text" id="idPattern" disabled="disabled" value="<%=Config.employeeIdPattern + "-0000"%>" />
        <br /> <br /> 

        <label class="lbl">Employee Promotion Strategy</label><br /> 

        <input type="text" id="promotionStrategy" disabled="disabled" value="<%=Config.employeePromotionStrategy%>" />
        <br /> <br /> 

        <select id="promotionStrategyDrl" style="display: none;">
            <option value="0">Select</option>
            <option value="Automatic">Automatic</option>
            <option value="User defined">User defined</option>
        </select> 

        <label class="lbl">Payroll Generation Strategy</label><br /> 

        <input type="text" id="payrollStrategy" disabled="disabled" value="<%=Config.payrollStrategy%>" />
        <br /> <br /> 

        <select id="payrollStrategyDrl" style="display: none;">
            <option value="0">Select</option>
            <option value="Automatic">Automatic</option>
            <option value="User defined">User defined</option>
        </select> 
        <a id="saveChanges" class="k-button" href="#">Apply</a>
    </div>
    <div style="clear: both"></div>
</div>

<script>

    $(document).ready(function() {
        $("#promotionStrategy").kendoDropDownList({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: [
                {text: "Automatic", value: "Automatic"},
                {text: "User defined", value: "User defined"}
            ],
            enabled: false
        });

        $("#promotionStrategy").data("kendoDropDownList").value("<%=Config.employeePromotionStrategy%>");
        $("#payrollStrategy").kendoDropDownList({
            dataTextField: "text",
            dataValueField: "value",
            dataSource: [
                {text: "Automatic", value: "Automatic"},
                {text: "User defined", value: "User defined"}
            ],
            enabled: false
        });
        $("#payrollStrategy").data("kendoDropDownList").value("<%=Config.payrollStrategy%>");


        //sends an ajax request to the specified url
        $("#saveChanges").click(function() {
            if ($("#makeEditable").attr('checked')) {
                var newPattern = $("#idPattern").val();
                var newPromotionStrategy = $("#promotionStrategy").val();
                var newPayrollStrategy = $("#payrollStrategy").val();


                newPattern = newPattern.replace('-0000', '');

                var settingData = JSON.stringify({"idPattern": newPattern, "promotionStrategy": newPromotionStrategy, "payrollStrategy": newPayrollStrategy});
                $.ajax({
                    url: "<%=request.getContextPath() + "/do/SettingsAction"%>",
                    type: 'POST',
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: settingData,
                    success: function() {
                        $(
                                "#makeEditable")
                                .removeAttr(
                                        'checked');
                        $("#idPattern")
                                .val(
                                        $(
                                                "#idPattern")
                                        .val()
                                        + "-0000");
                        $("#idPattern")
                                .attr(
                                        'disabled',
                                        'disabled');
                        $(
                                "#promotionStrategy")
                                .data(
                                        "kendoDropDownList")
                                .enable(
                                        false);
                        $(
                                "#payrollStrategy")
                                .data(
                                        "kendoDropDownList")
                                .enable(
                                        false);
                        alert("Settings saved successfully!");
                    },
                    failure: function(
                            e) {
                        alert(e.responseText);
                    }
                });
            } else {
                alert("You have not made any changes to the settings! \n Make sure you have checked on the Edit checkbox before saving");
            }
        });

        //
        $("#makeEditable").bind(
                "click",
                function(e) {
                    if ($(this).attr('checked')) {

                        $("#companyName").removeAttr('disabled');
                        $("#address1").removeAttr('disabled');
                        $("#address2").removeAttr('disabled');
                        $("#telephone").removeAttr('disabled');
                        $("#pobox").removeAttr('disabled');


                        $("#idPattern").val(
                                $("#idPattern").val().replace(
                                '-0000', ''));
                        $("#idPattern").removeAttr('disabled');
                        $("#promotionStrategy").data(
                                "kendoDropDownList").enable(
                                true);
                        $("#payrollStrategy").data(
                                "kendoDropDownList").enable(
                                true);

                    } else {
                        $("#companyName").attr('disabled', 'disabled');
                        $("#address1").attr('disabled', 'disabled');
                        $("#address2").attr('disabled', 'disabled');
                        $("#telephone").attr('disabled', 'disabled');
                        $("#pobox").attr('disabled', 'disabled');

                        $("#idPattern")
                                .val(
                                        $("#idPattern").val()
                                        + "-0000");
                        $("#idPattern").attr('disabled',
                                'disabled');
                        $("#promotionStrategy").data(
                                "kendoDropDownList").enable(
                                false);
                        $("#payrollStrategy").data(
                                "kendoDropDownList").enable(
                                false);
                    }
                });

    });
</script>