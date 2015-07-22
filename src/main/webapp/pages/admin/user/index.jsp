<%@include file="../../common/jspHeader.jsp" %>
<h2 class="legend">User Form</h2> 
<div id="grid">
</div>
<script>
    $(document).ready(function(e) {
        var usersModel = kendo.data.Model.define({
            id: "id",
            fields: {
                roleId: {
                    defaultValue: {
                        id: 0,
                        name: ""
                    }
                }, employeeId: {
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
                username: {
                    type: "string",
                    validation: {
                        required: true
                    }
                },
                password: {
                    type: "string",
                    validation: {
                        required: true
                    },
                    editable: false
                }
            }
        });

        var usrDataSource = new kendo.data.DataSource({
            transport: {
                read: {
                    url: "<%=request.getContextPath() + "/do/ReadUserAction"%>",
                    dataType: "json",
                    cache: false
                },
                create: {
                    url: "<%=request.getContextPath() + "/do/UserAction"%>",
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    type: 'POST'
                },
                update: {
                    url: "<%=request.getContextPath() + "/do/UpdateUserAction"%>",
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    type: 'POST'
                },
                destroy: {
                    url: "<%=request.getContextPath() + "/do/DeleteUserAction"%>",
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
                model: usersModel
            },
            batch: true,
            pageSize: 10
        });


        $("#grid").kendoGrid({
            dataSource: usrDataSource,
            columns: [
                {field: "roleId", title: "Role", editor: roleDropDownEditor, template: '#=roleId ? roleId.name: ""#', width: 120},
                {field: "employeeId", title: "Employee", editor: employeeDropDownEditor, template: '#=employeeId ? employeeId.employeeId: ""#', width: 120},
                {field: "username", title: "Username", width: 120},
                {field: "password", title: "Password",
                    template: "<input type='password' disabled='disabled' value='#= password #'/>",
                    width: 0},
                {command: ["edit", "destroy"], width: "210px", filterable: false}
            ],
            toolbar: ["create"],
            sortable: true,
            scrollable: true,
            filterable: true,
            pageable: true,
            selectable: "row",
            editable: "popup"
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

        function roleDropDownEditor(container, options) {
            $('<input data-text-field="name" data-value-field="id" data-bind="value:' + options.field + '"/>').appendTo(container).kendoDropDownList({
                autoBind: false,
                dataSource: {
                    type: "json",
                    transport: {
                        read: "<%=request.getContextPath() + "/do/ReadRoleAction"%>"
                    }
                }
            });
        }


    });


</script> 



