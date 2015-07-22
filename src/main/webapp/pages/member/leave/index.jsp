<div id="leaveTabs">
    <ul>
        <li class="k-state-active">Make Request</li>		
    </ul>



    <div>

        <div class="k-content">

            <div class="legend">

                <div style="float:right">
                    <input type="submit" value="New" id="newLeave"/>
                    <input type="submit" value="Delete"/>
                    <input type="submit" value="Edit"/>
                </div>

                <div style="float:left">
                    <p>Your Leave History</p>
                </div>
                <div style="clear:both"></div>
            </div>
            <div id="pendingGrid">
            </div>
        </div>
    </div>	


    <!-- Leave Application Form -->
    <div id="leaveApplication" style="display:none">

        <table>
            <tr>
                <td><label for="fromDate">From Date</label></td>
                <td><input type="text" id="newLeaveDate" value=""/></td>
            </tr>
            <tr>
                <td><label for="newReturnDate">To Date</label></td>
                <td><input type="text" id="newReturnDate" value=""/></td>
            </tr>

            <tr>
                <td><label for="newLeaveType">Leave Type</label></td>
                <td><select name="newleaveType" id="newLeaveType">
                        <option value="Annual">Annual</option>
                        <option value="Sick">Sick</option>
                        <option value="Other">Other</option>
                    </select></td>
            </tr>

            <tr>
                <td><label for="newReason">Reason</label></td>
                <td><textarea cols="35" rows="5" id="newReason"></textarea></td>
            </tr>

            <tr>
                <td colspan=2>
                    <input type="button" id="applyForLeave" value="Save"/>
                </td>
            </tr>

        </table>

        <!-- start of leave summary DIV -->
        <input type="button" id="calcWDays" value="View Working Days"/>
        <div>
            <p id="workingDaysHeading"></p>
            <input type="button" value="print" disabled=disabled id="print-leave-summary"/>
            <div style="width : 550px; border: 1px solid #ccc;">
                <table id="leave-summary" cellpadding=0 cellspacing=0 border=1 width=100%>	
                    <caption>Working days calendar</caption>
                    <tr>
                        <th>Day</th>
                        <th colspan=3>Leave Options</th>			
                    </tr>
                    <tbody id="available-work-days">
                    </tbody>
                </table>
                <br />
            </div>
        </div>	
        <!-- end of leave summary DIV -->


    </div>
    <!-- End of Leave Application Form -->
</div>

<script type="text/javascript">
    var leaveDateStart, leaveDateEnd, noOfDays, employeeId, leaveTypeId;
    $(document).ready(function() {

        $("#applyForLeave").click(function(e) {
            var leaveDate, returnDate, leaveType, reason;

            leaveDate = $("#newLeaveDate").val();
            returnDate = $("#newReturnDate").val();
            noOfDays = $("#noOfDays").val();
            leaveType = $("#newLeaveType").val();
            reason = $("#newReason").val();

            var jsonString = JSON.stringify([{"leaveDate": leaveDate,
                    "returnDate": returnDate, "leaveType": leaveType,
                    "noOfDays": noOfDays, "reason": reason}]);

            $.ajax({
                url: "<%=request.getContextPath() + "/do/LeaveApplicationAction"%>",
                type: "POST",
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: jsonString,
                success: function() {
                    //code after request succeeds
                },
                failure: function() {
                    //code after request fails
                }
            });


        });


        $("#newLeave").click(function(e) {

            $("#newLeaveDate").kendoDatePicker();
            $("#newReturnDate").kendoDatePicker({
                change: function(e) {

                    for (var i = 1; i <= 5; i++) {
                        var row = "<td>22-01-2145(Mon)</td><td>" +
                                "<input type='radio; name='leaveOption' value='FullDay'/>Full" +
                                "<input type='radio' name='leaveOption' value='HalfDay_AM'/>Half Day(AM)" +
                                "<input type='radio' name='leaveOption' value='HalfDay_PM'/>Half Day(PM)</td></tr>";

                        $("table#leaveDays tbody").append(row)
                    }

                }
            });
            $("#newLeaveType").kendoDropDownList();

            $("#leaveApplication").css("display", "block");

            $("#leaveApplication").kendoWindow({
                title: "New Leave Application",
                width: 750,
                modal: true
            }).data("kendoWindow").center().open();

        });





        $("#leaveTabs").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
            }
        });

        employeeId = $("#empId").kendoAutoComplete({
            autoBind: false,
            dataTextField: "employeeId",
            dataSource: {
                type: "json",
                transport: {
                    read: "<%=request.getContextPath() + "/do/ReadEmployeeAction"%>"
                }
            }
        }).data('kendoAutoComplete');


        leaveTypeId = $("#leaveType").kendoDropDownList({
            autoBind: false,
            dataTextField: "name",
            dataValueField: "id",
            dataSource: {
                type: "json",
                transport: {
                    read: "<%=request.getContextPath() + "/do/ReadLeaveTypeAction"%>"
                }
            }
        }).data("kendoDropDownList");

        leaveDateStart = $("#leaveDate").kendoDatePicker({
            min: new Date(),
            value: new Date()
        }).data("kendoDatePicker");

        leaveDateEnd = $("#returnDate").kendoDatePicker({
            min: new Date()
        }).data("kendoDatePicker");

        noOfDays = $("#noOfDays").kendoNumericTextBox({
            min: 1,
            value: 1,
            max: 20,
            step: 1,
            format: "# days",
            change: function(e) {
                var leaveDate = new Date(leaveDateStart.value());
                var returnDate = leaveDate;
                if (leaveDate.getDay() == 0 || leaveDate.getDay() == 6) {
                    alert("please select a working day as leave day");
                    return;
                }
                //returnDate.setDate(leaveDate.getDate() + noOfDays.value());									

                var returnDateBy = returnDate;
                var temp = returnDateBy;
                console.log("My Leave date " + temp);
                for (var dayCount = 1; dayCount <= noOfDays.value(); dayCount++) {
                    temp.setDate(temp.getDate() + 1);
                    returnDateBy = temp;
                    if (returnDateBy.getDay() == 6) {
                        temp.setDate(temp.getDate() + 2);
                    }
                }
                leaveDateEnd.value(kendo.toString(returnDateBy, 'MM/dd/yyyy'));
            }
        }).data('kendoNumericTextBox');

        $("#leaveTabs").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
            }
        });

        $("#saveLeaveReq").bind("click", function(e) {
            var empId = employeeId.value();
            var leaveType = leaveTypeId.value();
            var leaveDate = new Date($("#leaveDate").val()).getTime();
            var noOfDays = $("#noOfDays").val();
            var returnDate = new Date($("#returnDate").val()).getTime();
            var description = $("#description").val();

            var JSONData = JSON.stringify([{
                    "employeeId": empId,
                    "leaveTypeId": leaveType,
                    "leaveDate": leaveDate,
                    "noOfDays": noOfDays,
                    "returnDate": returnDate,
                    "description": description
                }]);

            $.ajax({
                url: "<%=request.getContextPath() +"/do/LeaveApplicationAction"%>",
                type: "POST",
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: JSONData,
                error: function(response) {
                    alert(response.responseText);
                }
            });
        });






        /** methods for working days calculator *****/

        $(function() {
            $("#print-leave-summary").click(function(e) {
                $("#leave-summary").jqprint();
            });

            $(".include_exclude_day").bind("click", function() {
                $(this).remove();
            });


            $("#date1").kendoDatePicker({});
            $("#date2").kendoDatePicker({});
        });





        $("#calcWDays").click(function() {
            daysAvailable = new Array();
            dateOne = new Date($("#newLeaveDate").val());
            dateTwo = new Date($("#newReturnDate").val());
            workingDaysCounter = 0;
            if (dateOne)
                $("#available-work-days").html("");
            var noOfDays = (dateTwo.getTime() - dateOne.getTime()) / (24 * 60 * 60 * 1000);

            //set back the date as in the loop a day will be added to it before the 
            //checking starts
            dateOne.setDate(dateOne.getDate() - 1);
            for (var i = 0; i <= noOfDays; i++)
            {
                tempDate = dateOne;
                tempDate.setDate(tempDate.getDate() + 1);
                if (tempDate.getDay() != 6 && tempDate.getDay() != 0)
                {
                    var input_checkbox = '<tr><td><input type="checkbox" class="include_exclude_day" checked=checked name="leaving-days" value="' + tempDate + '"/>'
                            + $.format.date(tempDate, "MMM dd, yyyy(ddd)") +
                            '</td><td><input type="radio" checked=checked  name="half_day_' + workingDaysCounter + '" value="full" />Full Day</td>' +
                            '<td><input type="radio" name="half_day_' + workingDaysCounter + '" value="am"/>Half Day(AM)</td>' +
                            '<td><input type="radio" name="half_day_' + workingDaysCounter + '" value="pm"/>Half Day(PM)</td></tr>';
                    $("#available-work-days").append(input_checkbox);
                    daysAvailable.push(tempDate);
                    workingDaysCounter++;
                }
                /*else{
                 var input_checkbox = '<tr class="not-working-day"><td><input type="checkbox" disabled=disabled name="leaving-days" value="'
                 + tempDate +'"/>' + $.format.date(tempDate, "MMM dd, yyyy(ddd)") + 
                 '</td><td colspan=3>Week End</td></tr>';
                 $("#available-work-days").append(input_checkbox);
                 }*/
            }

            $("#print-leave-summary").attr("disabled", false);
            $("#workingDaysHeading").html("You have <strong>" + workingDaysCounter + " days</strong> to choose to leave on");
        });

        /**** end of methods for working days calculator*/

    });
</script>
