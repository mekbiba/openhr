<%@include file="../../common/jspHeader.jsp" %>
<h2 class="legend">Role Form</h2> 

<script id="resourceAssignemnet" type="text/x-kendo-tmpl"> 	
    <div style="padding:5px;">
    <label>Filter by permission</label>
    <select id="filterRolesBy">
    <option value="">System Administrator</option>
    <option value="">Human Resource</option>
    <option value="">Accounting</option>
    <option value="">Employee</option>
    </select>

    </div>
</script>
<div id="grid">
</div>
<script>
    $(document).ready(function(e) {
        var usersModel = kendo.data.Model.define({
            id: "id",
            fields: {
                name: {
                    type: "string",
                    validation: {
                        required: true
                    }
                }
            }
        });

        var roleDataSource = new kendo.data.DataSource({
            transport: {
                read: {
                    url: "<%=request.getContextPath() + "/do/ReadRoleAction"%>",
                    dataType: "json",
                    cache: false
                },
                create: {
                    url: "<%=request.getContextPath() + "/do/RoleAction"%>",
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    type: 'POST'
                },
                update: {
                    url: "<%=request.getContextPath() + "/do/UpdateRoleAction"%>",
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    type: 'POST'
                },
                destroy: {
                    url: "<%=request.getContextPath() + "/do/DeleteRoleAction"%>",
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
            dataSource: roleDataSource,
            columns: [
                {field: "name", title: "Role name", width: 500},
                {command: ["edit", "destroy"], width: 210,
                    filterable: false}
            ],
            toolbar: ['create', {
                    template: $("#resourceAssignemnet").html()
                }],
            sortable: true,
            scrollable: true,
            filterable: true,
            columnMenu: true,
            pageable: true,
            selectable: "row",
            editable: "popup"
        });
        $("#filterRolesBy").kendoDropDownList({
            dataTextField: "PermissionName",
            dataValueField: "id",
            autoBind: false,
            optionLabel: "All",
            change: function() {
                var value = this.value();
                if (value) {
                    $("#grid").data("kendoGrid").dataSource.filter({
                        field: "id",
                        operator: "eq",
                        value: parseInt(value)
                    });
                } else {
                    $("#grid").data("kendoGrid").dataSource.filter({});
                }
            }
        });
    });


</script>  
<style scoped>
    div#resourceAssignemnet{
        float: right;
    }
</style>

