<%@include file="../../common/jspHeader.jsp"%>


<h2 class="legend">Employee Pay group Assignment Form</h2>

<div id="grid"></div>


<script>
    $(document).ready(function(e) {
        var empPayGroupModel = kendo.data.Model.define({
            id: "id",
            fields: {
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
                        photo: ""
                    }
                },
                payGroupId: {
                    defaultValue: {
                        id: 0,
                        name: "",
                        payRate: 0
                    }
                }

            }
        });



        $("#grid").kendoGrid({
            dataSource: {
                read: {
                    url: "<%=request.getContextPath() + "/do/ReadEmpPayGroupAction"%>",
                    dataType: 'json',
                    cache: false
                },
                create: {
                    url: "<%=request.getContextPath() + "/do/EmpPayGroupAction"%>",
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    type: 'POST'
                }, update: {
                    url: "<%=request.getContextPath() + "/do/UpdateEmpPayGroupAction"%>",
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    type: 'POST'
                }, destroy: {
                    url: "<%=request.getContextPath() + "/do/DeleteEmpPayGroupAction"%>",
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    type: 'POST'
                },
                parameterMap: function(options, operation) {
                    if (operation !== "read" && options.models) {
                        return JSON.stringify(options.models);
                    }
                },
                schema: {
                    model: empPayGroupModel
                },
                batch: true,
                pageSize: 10
            },
            columns: [
                {field: "employeeId", title: "Employee", editor: employeeDrpDwnEdtr, template: '#=employeeId ? employeeId.employeeId: ""#', width: 120},
                {field: "payGroupId", title: "PayRate", editor: payGradeDrpDwnEdtr, template: "#=payGroupId ? payGroupId.name :''#", width: 100},
                {
                    command: ["edit", "destroy"], title: "&nbsp;", width: "210px", filterable: false, sortable: false
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


        //Employee DropDownEditor
        function employeeDrpDwnEdtr(container, options) {
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
        //PayGroup DropDownEditor
        function payGradeDrpDwnEdtr(container, options) {
            $('<input data-text-field="name" data-value-field="id" data-bind="value:' + options.field + '"/>').appendTo(container).kendoDropDownList({
                autoBind: false,
                dataSource: {
                    type: "json",
                    transport: {
                        read: "<%=request.getContextPath() + "/do/ReadPayGroupAction"%>"
                    }
                }
            });
        }
    });
</script>