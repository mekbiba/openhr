<%@include file="../common/jspHeader.jsp"%>
<div id="dashboard" class="k-content"> 
    <div class="chart-wrapper" >
        <div id="chart" style="float: left; width:400px"></div>

        <div id="revenueChart" style="float: right; width:500px;padding-right:30px;"></div>
        <div style="clear: both"></div>
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
                                value: 22
                            }, {
                                category: "Management",
                                value: 54
                            }, {
                                category: "Design",
                                value: 49
                            }, {
                                category: "Sales",
                                value: 33
                            }, {
                                category: "Maintenace",
                                value: 27
                            }]
                    }],
                tooltip: {
                    visible: true,
                    format: "{0}%"
                }
            });
        }

        $(document).ready(function() {
            setTimeout(function() {
                // Initialize the chart with a delay to make sure
                // the initial animation is visible
                createChart();

                $("#dashboard").bind("kendo:skinChange", function(e) {
                    createChart();
                });
            }, 400);
        });

        function createRevenueBarChart() {
            $("#revenueChart").kendoChart({
                theme: $(document).data("kendoSkin") || "default",
                title: {
                    text: "Last Five Months Revenue share in percent per Department"
                },
                legend: {
                    position: "bottom"
                },
                seriesDefaults: {
                    type: "column"
                },
                series: [{
                        name: "Development",
                        data: [15.7, 16.7, 20, 23.5, 26.6]
                    }, {
                        name: "Design",
                        data: [20.96, 82.93, 75, 74, 78]
                    }, {
                        name: "Sales",
                        data: [25.5, 39.5, 65.1, 11, 25.89]
                    }, {
                        name: "Maintenance",
                        data: [25.5, 39.5, 65.1, 11, 25.89]
                    }],
                valueAxis: {
                    labels: {
                        format: "{0}%"
                    }
                },
                categoryAxis: {
                    categories: ['March', 'April', 'June', 'July', 'August']
                },
                tooltip: {
                    visible: true,
                    format: "{0}%"
                }
            });
        }

        $(document).ready(function() {
            setTimeout(function() {
                createRevenueBarChart();

                // Initialize the chart with a delay to make sure
                // the initial animation is visible
            }, 400);

            $(document).bind("kendo:skinChange", function(e) {
                createRevenueBarChart();
            });
        });
    </script>
</div>

<div style="font-family:arial;
     font-size:12px;
     text-align:justify;
     width:900px;
     padding:10px; ">
    <h2 style="font-size:16px;">Summary Note:</h2>
    Something about the data visualization is to go here
    Any one who wants to can do what he wants too if it is
    true. Something about the data visualization is to go here
    Any one who wants to can do what he wants too if it is
    true.Something about the data visualization is to go here
    Any one who wants to can do what he wants too if it is
    true.Something about the data visualization is to go here
    Any one who wants to can do what he wants too if it is
    true.
</div>



