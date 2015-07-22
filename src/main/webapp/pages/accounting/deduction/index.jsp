<%@include file="../../common/jspHeader.jsp"%>
<h2 class="legend">Deduction Type Definition</h2> 
<div id="deductions_wrapper">

    <label>Employee</label><br />

    <input id="employeeId" type="text" value=""/><br />

    <input type="button" value="Add Deduction" id="showDeductionWindow"/>
</div>


<div id="deductionEditor" style="display : none">
    <label>Deduction Type</label><br />
    <input type="text" id="deductionType"/><br />
    <label>Amount</label><br />
    <input type="text" id="amount"/><br />
    <input type="button" value="Add Deduction" id="addDeduction"/><br />
    <br />
    <input type="button" value="Add Deductions" id="addDeductionAll"/>
    <input type="button" value="Cancel" id="cancelDeductions"/>
</div>

<script>
    $("#employeeId").kendoDropDownList({
        dataTextField: "employeeId",
        dataValueField: "id",
        dataSource: {
            type: "json",
            transport: {
                read: "<%=request.getContextPath()+ "/do/ReadEmployeeAction"%>"
            }
        }
    });



    $("#deductionType").kendoDropDownList({
        dataTextField: "name",
        dataValueField: "id",
        dataSource: {
            type: "json",
            transport: {
                read: "<%=request.getContextPath()+ "/do/ReadDeductionTypeAction"%>"
            }
        }
    });


    $("#showDeductionWindow").click(function() {
        $("#deductionEditor").css("display", "block");
        $("#deductionEditor").kendoWindow({
            title: "Employee Deduction Editor",
            modal: true,
            width: 400
        }).data("kendoWindow").center().open();
    });
</script>