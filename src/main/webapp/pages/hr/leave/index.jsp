<%@include file="../../common/jspHeader.jsp" %>
<h2 class="legend">Employee Leave Assignment</h2> 
<div id="grid">
</div>
<script>

    $(document).ready(function(e) {

        var leaveModel = kendo.data.Model.define({
            id: "id",
            fields: {
                leaveType: {
                    defaultValue: {
                        id: 0,
                        name: "",
                        dayCap: 0
                    }
                },
                unuseddays: {
                    type: "number",
                    validation: {
                        required: true
                    }
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
                }, year: {
                    validation: {
                        required: true
                    },
                    defaultValue: new Date().getFullYear()
                },
            }
        });


        $("#grid").kendoGrid({
            dataSource: {
                transport: {
                    read: {
                        url: "<%=request.getContextPath() + "/do/ReadLeaveAction"%>",
                        dataType: 'json',
                        cache: false
                    },
                    create: {
                        url: "<%=request.getContextPath() + "/do/LeaveAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, update: {
                        url: "<%=request.getContextPath() + "/do/UpdateLeaveAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, destroy: {
                        url: "<%=request.getContextPath() + "/do/DeleteLeaveAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    },
                    parameterMap: function(options, operation) {
                        if (operation !== "read" && options.models) {
                            return JSON.stringify(options.models);
                        }
                    }
                },
                schema: {
                    model: leaveModel
                },
                batch: true,
                pageSize: 10
            },
            columns: [
                {field: "employeeId", title: "Employee", editor: employeeDropDownEditor, template: '#=employeeId ? employeeId.employeeId: ""#', width: 120},
                {field: "leaveType", title: "Leave Type", editor: leaveTypeDropDownEditor, template: '#=leaveType ? leaveType.name: ""#', width: 120},
                {field: "unuseddays", title: "Balance", width: 120},
                {field: "year", title: "Year", template: '#=kendo.toString(year)#', width: 70},
                {
                    command: ["edit", "destroy"], title: "&nbsp;", width: "210px", filterable: false
                }
            ],
            toolbar: ["create"],
            editable: "popup",
            sortable: true,
            scrollable: true,
            filterable: true,
            selectable: "row",
            pageable: true

        });



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
