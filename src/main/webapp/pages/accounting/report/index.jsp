<%@include file="../../common/jspHeader.jsp"%>
<h2 class="legend">Reports</h2> 
<div id="tabs">
    <ul>

        <li class="k-state-active">Employee</li>
        <li>Financial</li>
        <li>Security</li>
    </ul>
    <div>
        <h2 class='legend'>Employee Reports Section</h2>
        <div>
            <div id="reportForm">
                <div>

                    <div class="report_gen_criteria">
                        <label>Department</label><br> <select class="departmentCombo">
                            <option>All</option>
                            <option>Finance</option>
                            <option>Dev't</option>
                            <option>Management</option>
                        </select>
                    </div>

                    <div class="report_gen_criteria">
                        <label>Performance Index</label><br> <select
                            class="departmentCombo">
                            <option>All</option>
                            <option>Excellent</option>
                            <option>Very good</option>
                            <option>Good</option>
                            <option>Satisfactory</option>
                            <option>Failed</option>
                        </select>
                    </div>

                    <div class="report_gen_criteria">
                        <label>Pay Grade</label><br> <select class="departmentCombo">
                            <option>All</option>
                            <option>Excellent</option>
                            <option>Very good</option>
                            <option>Good</option>
                            <option>Satisfactory</option>
                            <option>Failed</option>
                        </select>
                    </div>

                    <div class="report_gen_criteria">
                        <label>Experience</label><br> <select class="departmentCombo">
                            <option>All</option>
                            <option>Excellent</option>
                            <option>Very good</option>
                            <option>Good</option>
                            <option>Satisfactory</option>
                            <option>Failed</option>
                        </select>
                    </div>
                    <div class="report_gen_criteria">
                        </br> </br> <a href="#" class="k-button" >Generate</a>
                    </div>
                </div>
            </div>

            <div id="dashboard">
                <div class="chart-wrapper">
                    <div id="chart"></div>
                </div>
                <script>
                    function createChart() {
                        $("#chart").kendoChart({
                            theme: $(document).data("kendoSkin") || "default",
                            title: {
                                text: "Employee break up per department"
                            },
                            legend: {
                                position: "bottom",
                                labels: {
                                    template: "#= text # (#= value #%)"
                                }
                            },
                            seriesDefaults: {
                                labels: {
                                    visible: true,
                                    format: "{0}%"
                                }
                            },
                            series: [{
                                    type: "pie",
                                    data: [{
                                            category: "Development",
                                            value: 55
                                        }, {
                                            category: "Administration",
                                            value: 2
                                        }, {
                                            category: "Design",
                                            value: 49
                                        }, {
                                            category: "Sales",
                                            value: 27
                                        }]
                                }],
                            tooltip: {
                                visible: true,
                                format: "{0}%"
                            }
                        });
                    }

                    $(document).ready(
                            function() {
                                setTimeout(function() {
                                    // Initialize the chart with a delay to make sure
                                    // the initial animation is visible
                                    createChart();

                                    $("#dashboard").bind("kendo:skinChange",
                                            function(e) {
                                                createChart();
                                            });
                                }, 400);
                            });
                </script>
            </div>
            <div style="clear: both"></div>
        </div>
    </div>
    <!-- end of first tab -->
    <div>
        <h2 class="legend">Financial Reports Section</h2>	
        <div>

            <div class="report_gen_criteria">
                <label>Department</label><br> <select class="departmentCombo">
                    <option>All</option>
                    <option>Finance</option>
                    <option>Dev't</option>
                    <option>Management</option>
                </select>
            </div>

            <div class="report_gen_criteria">
                <label>Performance Index</label><br> <select
                    class="departmentCombo">
                    <option>All</option>
                    <option>Excellent</option>
                    <option>Very good</option>
                    <option>Good</option>
                    <option>Satisfactory</option>
                    <option>Failed</option>
                </select>
            </div>

            <div class="report_gen_criteria">
                <label>Pay Grade</label><br> <select class="departmentCombo">
                    <option>All</option>
                    <option>Excellent</option>
                    <option>Very good</option>
                    <option>Good</option>
                    <option>Satisfactory</option>
                    <option>Failed</option>
                </select>
            </div>

            <div class="report_gen_criteria">
                <label>Experience</label><br> <select class="departmentCombo">
                    <option>All</option>
                    <option>Excellent</option>
                    <option>Very good</option>
                    <option>Good</option>
                    <option>Satisfactory</option>
                    <option>Failed</option>
                </select>
            </div>
            <div class="report_gen_criteria">
                </br> </br> <a href="#" class="k-button" >Generate</a>
            </div>
            <div style="clear:both"></div>
        </div>	
    </div>
    <!-- end of second tab -->
    <div>
        <h2 class="legend">Security Reports Section</h2>	
        <div>

            <div class="report_gen_criteria">
                <label>Department</label><br> <select class="departmentCombo">
                    <option>All</option>
                    <option>Finance</option>
                    <option>Dev't</option>
                    <option>Management</option>
                </select>
            </div>

            <div class="report_gen_criteria">
                <label>Performance Index</label><br> <select
                    class="departmentCombo">
                    <option>All</option>
                    <option>Excellent</option>
                    <option>Very good</option>
                    <option>Good</option>
                    <option>Satisfactory</option>
                    <option>Failed</option>
                </select>
            </div>

            <div class="report_gen_criteria">
                <label>Pay Grade</label><br> <select class="departmentCombo">
                    <option>All</option>
                    <option>Excellent</option>
                    <option>Very good</option>
                    <option>Good</option>
                    <option>Satisfactory</option>
                    <option>Failed</option>
                </select>
            </div>

            <div class="report_gen_criteria">
                <label>Experience</label><br> <select class="departmentCombo">
                    <option>All</option>
                    <option>Excellent</option>
                    <option>Very good</option>
                    <option>Good</option>
                    <option>Satisfactory</option>
                    <option>Failed</option>
                </select>
            </div>
            <div class="report_gen_criteria">
                </br> </br> <a href="#" class="k-button">Generate</a>
            </div>
            <div style="clear:both"></div>
        </div>
    </div>
    <!-- end of third tab -->
</div>

<style type="text/css">
    div#reportForm {
        width: 400px;
        float: left;
    }

    div#dashboard {
        padding-right: 100px;
        width: 400px;
        float: right;
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
        $(".departmentCombo").kendoDropDownList();
    });
</script>
