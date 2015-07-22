<%@include file="../../common/jspHeader.jsp"%>


<h2 class="legend">Pay Group Definition</h2>

<div id="grid">
</div>


<script>
    var isCreating = false;
    $(document).ready(function(e) {

        var payGroupModel = kendo.data.Model.define({
            id: "id",
            fields: {
                name: {type: "string", validation: {required: true}},
                payRate: {type: "number", validation: {required: true}}
            }
        });


        $("#grid").kendoGrid({
            dataSource: {
                transport: {
                    read: {
                        url: "<%=request.getContextPath() + "/do/ReadPayGroupAction"%>",
                        dataType: 'json',
                        cache: false
                    },
                    create: {
                        url: "<%=request.getContextPath() + "/do/PayGroupAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, update: {
                        url: "<%=request.getContextPath() + "/do/UpdatePayGroupAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, destroy: {
                        url: "<%=request.getContextPath() + "/do/DeletePayGroupAction"%>",
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
                    model: payGroupModel
                },
                failure: function(reponse) {
                    alert(response.responseText());
                },
                batch: true,
                pageSize: 10
            },
            columns: [
                {field: "name", title: "Name", width: "150px"},
                {field: "payRate", title: "PayRate", format: "{0:c}", width: "100px"},
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
    });
</script>