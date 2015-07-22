<%@include file="../../common/jspHeader.jsp"%>
<h2 class="legend">Manage Employee Deductions</h2> 
<div id="grid">

</div>

<script>
    $(document).ready(function() {
        var DeductionTypeModel = kendo.data.Model.define({
            id: "id",
            fields: {
                name: {type: "string", validation: {required: true}},
                description: {type: "string", validation: {required: true}}
            }
        });


        $("#grid").kendoGrid({
            dataSource: {
                transport: {
                    read: {
                        url: "<%=request.getContextPath() + "/do/ReadDeductionTypeAction"%>",
                        dataType: 'json',
                        cache: false
                    },
                    create: {
                        url: "<%=request.getContextPath() + "/do/DeductionTypeAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, update: {
                        url: "<%=request.getContextPath() + "/do/UpdateDeductionTypeAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, destroy: {
                        url: "<%=request.getContextPath() + "/do/DeleteDeductionTypeAction"%>",
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
                    model: DeductionTypeModel
                },
                batch: true,
                pageSize: 10
            },
            columns: [
                {field: "name", title: "Name", width: "150px"},
                {field: "description", title: "Description", width: "100px"},
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

    });

</script>