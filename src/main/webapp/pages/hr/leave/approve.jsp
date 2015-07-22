<%@page import="com.openhr.data.Employee"%>
<%@include file="../../common/jspHeader.jsp" %>
<h2 class="legend">Approve Leave form</h2>

<div id="grid">

</div>

<script>
    $(document).ready(function() {

        var leaveApprovalModel = kendo.data.Model.define({
            id: "id",
            fields: {
                leaveDate: {
                    type: "date"
                },
                returnDate: {
                    type: "date"
                },
                noOfDays: {
                    type: "number"
                },
                status: {
                    type: "string"
                },
                employeeId: {
                    defaultValue: {
                        id: 0,
                        employeeId: "",
                        firstname: "",
                        middlename: "",
                        lastname: "",
                        sex: "",
                        hiredate: "",
                        birthdate: "",
                        positionId: {
                            defaultValue: {
                                id: 0,
                                name: "",
                                salary: 0,
                                raisePerYear: 0
                            }
                        },
                        photo: "",
                    }
                },
                leaveTypeId: {
                    defaultValue: {
                        id: 0,
                        name: "",
                        dayCap: 0
                    }
                },
                description: {
                    type: "string"
                }
            }
        });

        $("#grid").kendoGrid({
            dataSource: {
                transport: {
                    read: {
                        url: "<%=request.getContextPath() + "/do/ReadRequestedLeaveAction"%>",
                        dataType: 'json',
                        cache: false
                    },
                    parameterMap: function(options, operation) {
                        if (operation !== "read" && options.models) {
                            $.each(options.models, function() {
                                /*this.leaveDate = new Date(this.leaveDate);
                                 this.returnDate = new Date(this.returnDate);*/
                            });
                            return JSON.stringify(options.models);
                        }
                    }
                },
                schema: {
                    model: leaveApprovalModel
                },
                batch: true,
                pageSize: 10
            },
            columns: [
                {field: "employeeId", title: "Employee", editor: employeeDropDownEditor, template: '#=employeeId ? employeeId.employeeId: ""#', width: 100},
                {title: "Full name", template: '#=employeeId ? employeeId.firstname +" "+employeeId.middlename +" "+employeeId.lastname: ""#', width: 180},
                {field: "leaveTypeId", title: "Leave Type", editor: leaveTypeDropDownEditor, template: '#=leaveTypeId ? leaveTypeId.name: ""#', width: 120},
                {field: "leaveDate", title: "Leave date", template: "#= kendo.toString(new Date(leaveDate), 'MMM, dd yyyy') #", width: 100},
                {field: "returnDate", title: "Return date", template: "#= kendo.toString(new Date(returnDate) , 'MMM, dd yyyy') #", width: 100},
                {field: "noOfDays", title: "No of days", width: 100},
                {field: "description", title: "Description", width: 100},
                {field: "status", title: "Status", template: "#= status == 0 ? 'New' : 'In Process' #", width: 100},
                {
                    command: [{name: "approve", text: "Aprrove", className: "approve"}, {name: "reject", text: "Reject", className: "approve"}], width: 200, filterable: false
                }
            ],
            sortable: true,
            scrollable: true,
            filterable: true,
            selectable: "row",
            /*resizable : true,*/
            pageable: true
        });


        $("#grid").delegate(".approve", "click", function(e) {
            //the code below needs a critical evaluation and is 
            //not to be used in production mode
            e.preventDefault();
            var dataItem = $("#grid").data("kendoGrid").dataItem($(this).closest("tr"));
            var leaveId = dataItem;
            var approverId = '<%=request.getSession().getAttribute("loggedEmployee")%>';

            alert(approverId);

            var approvedByDate = new Date().getTime();
            var approvalData = JSON.stringify([{"approvedbydate": approvedByDate, "approverId": approverId, "requestId": leaveId.id}]);

            if (approverId != '')
                $.ajax({
                    url: "<%=request.getContextPath() + "/do/ApproveLeaveAction"%>",
                    type: 'POST',
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: approvalData,
                    success: function() {
                        alert("succeded");
                    },
                    failure: function() {
                        alert("failed");
                    }
                });
            else
            {
                document.body = "<div style='border:1px solid #f00; height : 60px; width : 70px;font-size:24px;'>Browser Session has expired</div>";
                alert("Your browser's session have expired!\nPlease login again to continue");
                window.location.href = "<%=request.getContextPath()+"/do/LogoutAction"%>";
            }

        });


        /*
         *
         *
         *The following two funciton are editors for dropdownlists in the grid
         */

        function employeeDropDownEditor(container, options) {
            $('<input data-text-field="employeeId" data-value-field="id" data-bind="value:' + options.field + '"/>').appendTo(container).kendoDropDownList({
                autoBind: false,
                dataSource: {
                    type: "json",
                    transport: {
                        read: "<%=request.getContextPath() + "/do/ReadEmployeeAction"%>"
                    }
                }
            });
        }

        function leaveTypeDropDownEditor(container, options) {
            $('<input data-text-field="name" data-value-field="id" data-bind="value:' + options.field + '"/>').appendTo(container).kendoDropDownList({
                autoBind: false,
                dataSource: {
                    type: "json",
                    transport: {
                        read: "<%=request.getContextPath() + "/do/ReadLeaveTypeAction"%>"
                    }
                }
            });
        }
    });
</script>