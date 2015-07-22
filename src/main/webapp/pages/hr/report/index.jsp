<%@include file="../../common/jspHeader.jsp"%>
<h2 class="legend">HR Reports</h2> 
<div id="tabs">
    <ul>

        <li class="k-state-active">Employee</li>

    </ul>
    <div>
        <h2 class='legend'>Employee Reports Section</h2>
        <div>
            <div id="reportForm">
                <div>
                    <div class="report_gen_criteria">
                        <label>Report type</label><br> <select id="reportType" class="dropDown">
                            <option>Select</option>
                            <option>Employee List</option>
                            <option>Finance</option> 
                        </select>
                    </div>
                    <div class="report_gen_criteria">
                        <label>Department</label><br> <select id="department" class="dropDown">
                            <option>All</option>
                            <option>Finance</option>
                            <option>Dev't</option>
                            <option>Management</option>
                        </select>
                    </div>

                    <div class="report_gen_criteria">
                        <label>Performance Index</label><br> <select
                            id="performance" class="dropDown">
                            <option>All</option>
                            <option>Excellent</option>
                            <option>Very good</option>
                            <option>Good</option>
                            <option>Satisfactory</option>
                            <option>Failed</option>
                        </select>
                    </div>

                    <div class="report_gen_criteria">
                        <label>Pay Grade</label><br> <select id="payGrade" class="dropDown">
                            <option>All</option>
                            <option>1000</option>
                            <option>750</option>
                            <option>500</option>
                            <option>300</option>
                            <option>200</option>
                        </select>
                    </div>

                    <div class="report_gen_criteria">
                        </br> </br> <a href="#" class="k-button" id="btn-generate" >Generate</a>
                    </div>
                </div>
            </div>
            <div style="clear: both"></div>
        </div>
    </div>
    <!-- end of first tab -->

</div>

<style type="text/css">
    div#reportForm {
        width: 100%;
        float: left;
    }

    div#dashboard {

        width: 200px;
        float: left;
    }
</style>

<script>
    $(document).ready(function() {
        $("#tabs").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
            }
        });
        $(".dropDown").kendoDropDownList();

        $("#btn-generate").click(function() {


            var reportType = $("#reportType").val();
            var department = $("#department").val();
            var performance = $("#performance").val();
            var payGrade = $("#payGrade").val();


            var json_data = JSON.stringify({"reportType": reportType, "department": department,
                "performance": performance, "payGrade": payGrade});

            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath() + "/do/GenerateHRReport"%>",
                data: json_data,
                dataType: "json",
                contentType: "application/json; charset=utf-8"
            });
        });
    });
</script>
