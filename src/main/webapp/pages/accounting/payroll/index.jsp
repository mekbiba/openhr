<%@include file="../../common/jspHeader.jsp"%>
<h2 class="legend">Payroll</h2> 
<div class="payroll_form">
    <div style="float: right; padding-top: 20px; padding-right: 20px;">

        <div>
            <a href="<%=request.getContextPath() + "/do/GeneratePayroll"%>">
                <input type="button" value="Run Payroll"/>
            </a>
            <a id="launchSettingsWindow">
                <input type="button" value="Settings"/></a>
        </div>
    </div>
    <div id="container">

        <div style="width: 800px;">
            <fieldset>
                <legend>Search Employee</legend>
                <div style="float:left">
                    <label>First Name</label>
                    <input type="text" style="width:350px" id="searchByName"/><br />
                    <div style="display:none" id="employeeHeader">
                        <input type="button" id="clearSearch" value="Clear Search"/>
                        <table cellspacing=15 style="border:1px solid #999">
                            <tr>
                                <td>Photo</td>								
                                <td>Id #</td>
                                <td>Full Name</td>
                            </tr>
                            <tr>
                                <td><img id="photo" style="border: 2px solid #000" width=100 height=75 src=""/></td>
                                <td><label id="empId" style="font-size:16px;">No employee selected</label></td>
                                <td><label id="fullName" style="font-size:16px;">No employee selected</label></td>								
                            </tr>
                        </table>					
                    </div>

                </div>
                <div style="float:right; padding-right:100px">
                    <input type="button" id="view_pay_summary" value="View Summary"
                           style="display:none"/>
                </div>
                <div style="clear:both"></div>
            </fieldset>
        </div>

        <div id="calculations" style="display:none">

            <div class="k-content">

                <fieldset>
                    <legend>General Summary</legend>

                    <table id="employeeSummaryGrid">
                        <thead>
                            <tr>
                                <th>Employee ID</th>
                                <th>Gross Salary</th>
                                <th>Worked for</th>

                                <th>Monthly Salary Before TAX</th>
                                <th>Overtime</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr>
                                <td><input disabled="disabled" type="text" value="" id="employeeId"/></td>

                                <td><input disabled="disabled" style="width: 60px"
                                           type="text" id="grossSalary"/>ETB</td>

                                <td><input disabled="disabled" style="width: 60px"
                                           type="text" value="20" />days<br> <a href="#">View
                                        Time sheet</a></td>

                                <td><input disabled="disabled" type="text"
                                           style="width: 60px" value="4500" />ETB</td>

                                <td><input disabled="disabled" style="width: 60px"
                                           type="text" value="528.45" />ETB<br> <a href="#"
                                           id="showOvertimeSheet">View Overtime sheet</a></td>

                            </tr>
                        </tbody>
                    </table>
                </fieldset>
            </div>

            <div>
                <div style="float: left; width: 440px"  id="allowances">
                    <fieldset>
                        <legend>Allowances</legend>
                        <p class="information_msg"
                           style="font-size: 14px; padding: 5px; background-color: #006699; color: #fff; border: 1px solid yellow">
                            un check on <i>Allowance</i> to exculde from payroll
                        </p>
                        <div style="padding: 10px;">
                            <div style="float: left; width: 200px">
                                <input type="checkbox" checked="checked" value="0" />Transport
                                Allowance<br> <input type="text" value="1000"
                                                     disabled="disabled" />
                            </div>

                            <div style="margin-left: 200px">
                                <input type="checkbox" checked="checked" value="0" />House
                                Allowance<br> <input type="text" value="2000"
                                                     disabled="disabled" />
                            </div>
                            <p style="clear: both"></p>
                        </div>

                        <div style="padding: 10px;">
                            <div style="float: left; width: 200px">
                                <input type="checkbox" checked="checked" value="0" />Bonus<br>
                                <input type="text" value="550" disabled="disabled" />
                            </div>

                            <div style="margin-left: 200px"></div>
                            <p style="clear: both"></p>
                        </div>

                        <div style="padding: 10px;">
                            <div style="float: left; width: 200px">
                                <h2 style="font-size: 20px">Sub total</h2>
                                <input type="text" style="width: 80px; font-size: 18px"
                                       value="3550" disabled="disabled" />ETB
                            </div>

                            <div style="margin-left: 200px">


                                <input type="button" value="Edit" id="editAllowances"/>

                            </div>
                            <p style="clear: both"></p>
                        </div>						
                    </fieldset>
                </div>



                <div style="margin-left: 440px" id="deductions">
                    <fieldset>
                        <legend>Deductions</legend>
                        <p class="information_msg"
                           style="font-size: 14px; padding: 5px; background-color: #006699; color: #fff; border: 1px solid yellow;">
                            un check on <i>Deduction</i> to exculde from payroll
                        </p>
                        <div style="padding: 10px;">
                            <div style="float: left; width: 200px">
                                <input type="checkbox" checked="checked" value="0" />Pension(6%)<br>
                                <input type="text" value="300" disabled="disabled" />
                            </div>

                            <div style="margin-left: 200px">
                                <input type="checkbox" checked="checked" value="0" />Income
                                TAX(15%)<br> <input type="text" value="950"
                                                    disabled="disabled" />
                            </div>
                            <p style="clear: both"></p>
                        </div>

                        <div style="padding: 10px;">
                            <div style="float: left; width: 200px">
                                <input type="checkbox" checked="checked" value="0" />Staff
                                Loan(10%)<br> <input type="text" value="550"
                                                     disabled="disabled" />
                            </div>

                            <div style="margin-left: 200px"></div>
                            <p style="clear: both"></p>
                        </div>

                        <div style="padding: 10px;">
                            <div style="float: left; width: 200px">
                                <h2 style="font-size: 20px">Sub total</h2>
                                <input type="text" style="width: 80px; font-size: 18px"
                                       value="1800" disabled="disabled" />ETB
                            </div>

                            <div style="margin-left: 200px">


                                <input type="button" value="Edit" id="editDeductions"/>

                            </div>
                            <p style="clear: both"></p>
                        </div> 
                    </fieldset>
                </div>
                <p style="clear: both"></p>
            </div>


            <div style="float: left; width: 440px">
                <fieldset>
                    <legend>Net payable</legend>
                    <div style="padding: 10px;">
                        <div style="float: left; width: 200px">

                            <input type="text" style="width: 80px; font-size: 18px"
                                   value="6250" disabled="disabled" /><label>ETB</label>
                        </div>

                        <p style="clear: both"></p>
                    </div>

                </fieldset>
            </div>
        </div>
    </div>
</div>


<div id="overtimeSheet" style="display: none">

    <table id="overtimeGrid">
        <thead>
            <tr>
                <th>Day</th>
                <th>Hours</th>
                <th>Rate based on</th>
                <th>Amount</th>
            </tr>

        </thead>

        <tbody>
            <tr>
                <td><a href="#" id="otDate">July 25, 2012</a></td>

                <td>3.25</td>
                <td>
                    <div>
                        <input type="radio" value="workday" name="otRate"
                               checked="checked" /><label >Work day(1.5%)</label>
                    </div>
                    <div>
                        <input type="radio" value="Weekend" name="otRate" /><label>Weekend(2%)</label>
                    </div>
                    <div>
                        <input type="radio" value="Holiday" name="otRate" /><label>Holiday(2.5%)</label>
                    </div>
                </td>
                <td>128.45</td>
            </tr>
            <tr>
                <td><a href="#" class="otDate">March 12, 2013</a></td>
                <td>3.25</td>
                <td>
                    <div>
                        <input type="radio" value="workday" name="otRate1" /><label>Work
                            day(1.5%)</label>
                    </div>
                    <div>
                        <input type="radio" value="Weekend" name="otRate1" /><label>Weekend(2%)</label>
                    </div>
                    <div>
                        <input type="radio" value="Holiday" name="otRate1"
                               checked="checked" /><label>Holiday(2.5%)</label>
                    </div>
                </td>
                <td>450</td>


            </tr>
            <tr>
                <td></td>
                <td></td>
                <td><label>Total</label></td>
                <td><input type="text" disabled="disabled"
                           style="width: 60px; font-size: 18px" value="528.45" /><label>ETB</label></td>
            </tr>
        </tbody>
    </table>

</div>


<div id="payrollSettingsWindow" style="display: none">
    <div id="payrollSettingTabs">
        <ul>
            <li class="k-state-active">Overtime Setting</li>
            <li>Staff Loan Setting</li>

            <li>Currency Setting</li>
        </ul>

        <div>
            <fieldset>
                <legend>Overtime pay rate Definition</legend>
                <label>Choose day group</label><br> 
                <select >
                    <option>Work day</option>
                    <option>Weekend</option>
                    <option>Holiday</option>
                </select> 
                <label>% of Gross salary</label> 
                <input type="text" value="1.5%"/><br><br> 
                <input type="button" value="Save" />
            </fieldset>
        </div>


        <div>
            <fieldset>
                <legend>Staff loan eligibility Definition</legend>
                <label>Experience in years</label> <br>
                <select>
                    <option>1/2</option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>over 3 years</option>
                </select><br> 
                <label>Can ask for</label> <br>
                <select >
                    <option>50</option>
                    <option>100</option>
                    <option>200</option>
                    <option>300</option>
                    <option>400</option>
                </select> <label>% of salary</label><br> <br>
                <input type="button" value="Save" />
            </fieldset>
        </div>




        <div>
            <fieldset>
                <legend>Currency Definition</legend>
                <label>Choose currency</label><br>
                <select >
                    <option>ETB</option>
                    <option>USD</option>
                    <option>CAD</option>
                    <option>EURO</option>
                    <option>POUND</option>
                </select><br> <br>
                <input type="button" value="Save" />
            </fieldset>
        </div>


    </div>

</div>



<div id="pay_summary_cont" style="width:650px;display:none">
    <div style="clear:both">
        <input type="button" value="Print" id="printPaySlip" />
    </div>
    <div>
        <h3 style="float:right">Name, Anthony Williams</h3>	
        <hr style="clear:both"">
    </div>
    <div>
        <h3 style="float:left">Basic</h3>
        <hr style="clear:both"/>
    </div>
    <div>
        <div style="float:left"><p style="width:400px;font-size:13px;text-align:right">Salary</p></div>
        <div style="float:right"><strong style="color:#0f0;font-style:underline;font-size:16px;">2569.56</strong></div>
        <div style="clear:both"></div>
    </div>




    <div>
        <h3 style="float:left">Allowances</h3>
        <hr style="clear:both"/>
    </div>			
    <div>
        <div style="float:left"><p style="width:400px;font-size:13px;text-align:right">House</p></div>
        <div style="float:right"><strong style="color:#0f0;font-style:underline;font-size:16px;">251.26</strong></div>
        <div style="clear:both"></div>
    </div>

    <div>
        <div style="float:left"><p style="width:400px;font-size:13px;text-align:right">Fuel</p></div>
        <div style="float:right"><strong style="color:#0f0;font-style:underline;font-size:16px;">452</strong></div>
        <div style="clear:both"></div>
    </div>

    <div>
        <div style="float:left"><p style="width:400px;font-size:13px;text-align:right">Representation</p></div>
        <div style="float:right"><strong style="color:#0f0;font-style:underline;font-size:16px;">236.9</strong></div>
        <div style="clear:both"></div>
    </div>
    <hr>






    <div>
        <h3 style="float:left">Deductions</h3>
        <hr style="clear:both"/>
    </div>			
    <div>
        <div style="float:left"><p style="width:400px;font-size:13px;text-align:right">Income Tax(35%)</p></div>
        <div style="float:right"><strong style="color:#0f0;font-style:underline;font-size:16px;">652.26</strong></div>
        <div style="clear:both"></div>
    </div>

    <div>
        <div style="float:left"><p style="width:400px;font-size:13px;text-align:right">Pension(6%)</p></div>
        <div style="float:right"><strong style="color:#0f0;font-style:underline;font-size:16px;">142.45</strong></div>
        <div style="clear:both"></div>
    </div>
    <div>
        <div style="float:left"><p style="width:400px;font-size:13px;text-align:right">Staff Loan</p></div>
        <div style="float:right"><strong style="color:#0f0;font-style:underline;font-size:16px;">520.25</strong></div>
        <div style="clear:both"></div>
    </div>
    <hr>







    <div>
        <div style="float:left">
            <h3>Total</h3>
        </div>
        <div style="float:right">
            <strong style="color:#0f0;font-style:underline;font-size:18px;">2397.56</strong>			
        </div>
        <div style="clear:both"></div>
    </div>
    <hr>

    <div>
        <div style="float:left">
            <h3>Net Pay</h3>
        </div>
        <div style="float:right">
            <strong style="color:#0f0;font-style:underline;font-size:18px;">1500.56</strong>
            <hr>
            <hr>
        </div>
        <div style="clear:both"></div>
    </div>


</div> 


<style scoped>
    h3{
        font-size : 18px;
    }
</style>


<script>
    var no_Of_steps = 3, first_step = 1;
    $(document).ready(function() {

        $("#employeeSummaryGrid").kendoGrid();
        $("#calculations_payroll_menu").kendoMenu();

        $("#showOvertimeSheet").click(function() {
            $("#overtimeSheet").css("display", "block");
            $("#overtimeGrid").kendoGrid();
            var wnd = $("#overtimeSheet").kendoWindow({
                modal: true,
                resizable: false,
                width: 800,
                height: 250,
                title: "Overtime work hours"
            }).data("kendoWindow");
            wnd.center();
            wnd.open();
        });



        $("#otDate").live("click", function() {
            $(this).kendoDatePicker({
                value: new Date($(this).text()),
                enable: false
            }).data("kendoDatePicker").open();
        });


        $("#editAndSave").click(function() {
            if ($(this).val("Edit")) {
                $(this).val("Save");
                $("#currentlyAssigned").removeAttr("disabled");
                $("#currentlyAssigned").css("border", "1px solid #333021");
            }
        });

        $("#launchSettingsWindow").bind("click", function() {
            $("#payrollSettingsWindow").css("display", "block");
            $("#payrollSettingTabs").kendoTabStrip({
                animation: {
                    open: {
                        effects: "fadeIn"
                    }
                }
            });
            var wnd = $("#payrollSettingsWindow").kendoWindow({
                modal: true,
                resizable: false,
                width: 700,
                title: "Payroll Settings"
            }).data("kendoWindow");
            wnd.center();
            wnd.open();
        });

        $("#clearSearch").click(function() {
            $("#searchByName").text("Start typing");
            $("#employeeHeader").css("display", "none");
            $("#calculations").css("display", "none");
            $("#view_pay_summary").css("display", "none");
        });

        $("#searchByName").kendoAutoComplete({
            dataSource: new kendo.data.DataSource({
                transport: {
                    read: "<%=request.getContextPath() + "/do/ReadEmployeeAction"%>"
                }
            }), select: function(e) {
                ////////////////////////////////////////////////////
                var dataItem = this.dataItem(e.item.index());
                ////////////////////////////////////////////////////
                $("#employeeHeader").css("display", "block");
                $("#calculations").css("display", "block");
                $("#view_pay_summary").css("display", "block");
                ////////////////////////////////////////////////////
                $("#empId").text(dataItem.employeeId);
                $("#fullName").text(dataItem.firstname + ' ' +
                        dataItem.middlename + ' ' +
                        dataItem.lastname);
                $("#photo").attr("src", "/OpenHR" + dataItem.photo);
                //output selected dataItem
                //alert(kendo.stringify(dataItem));
                $("#employeeId").val(dataItem.employeeId);
                $("#grossSalary").val(dataItem.positionId.salary);

            },
            dataTextField: "firstname",
            placeholder: 'Start typing',
            template: kendo.template($("#searchByNameAutoComplete").html())
        });







        $("#printPaySlip").click(function() {

            $(".information_msg").css("display", "none");
            $("input[type=button]").css("display", "none");
            $("a").css("display", "none");
            $("#pay_summary_cont").jqprint();
            setTimeout(function() {
                $(".information_msg").css("display", "block");
                $("input[type=button]").css("display", "block");
                $("a").css("display", "block");
            }, 5000);
        });

        $("#view_pay_summary").click(function() {
            $("#pay_summary_cont").css("display", "block");
            $("#pay_summary_cont").kendoWindow({
                modal: true,
                title: "Pay Summary"
            });
            $("#pay_summary_cont").data("kendoWindow").center();
            $("#pay_summary_cont").data("kendoWindow").open();
        });


        $("#editAllowances").click(function() {
            var allowanceEditor = $("#allowances").clone();
            $(allowanceEditor).kendoWindow({
                modal: true,
                resizable: false,
                title: "Allowances editor window"
            });

            $("div.k-widget input[type='text']").removeAttr("disabled");



            $("div.k-widget input#editAllowances").val("Save");
            $("div.k-widget input#editAllowances").click(function() {
                if (confirm('Are you sure you want to save changes \nYes if you want to proceed' +
                        '\nNo if you want to cancel')) {
                    alert('Your changes are saved successfully!');
                    //iterate the new value set from the modal window
                    alert(JSON.stringify(allowanceEditor));
                    $(allowanceEditor).data("kendoWindow").close();
                } else {
                    $(allowanceEditor).data("kendoWindow").close();
                }
            });
            $(allowanceEditor).data("kendoWindow").center();
            $(allowanceEditor).data("kendoWindow").open();

        });




    });
</script>


<script id="searchByNameAutoComplete" type="text/x-kendo-tmpl"> 	
    <table>
    <tr>
    <td><img width=40 height=40 class='image_preview'  src="#='/OpenHR'+photo#"/></td>
    <td><p style="font-family:'Century Gothic'">#=firstname+' '+middlename+' '+lastname#</p></td>    
    </tr>
    </table>	 
</script>

<style>

    img.image_preview{
        border : 2px solid #fff;
    }
    #prevousStep,#nextStep {
        padding: 4px;
    }

    #prevousStep {
        float: left;
    }

    #nextStep {
        float: right;
    }
</style>
