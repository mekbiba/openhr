<%@include file="../../common/jspHeader.jsp" %>
<h2 class="legend">Position Form</h2>

<div id="grid">
</div>
<script>
    var isCreating = false;
    $(document).ready(function(e) {

        var positionModel = kendo.data.Model.define({
            id: "id",
            fields: {
                name: {type: "string", validation: {required: true}},
                salary: {type: "number", validation: {required: true, min: 500}},
                raisePerYear: {type: "number", validation: {required: true, min: 100}}
            }
        });


        $("#grid").kendoGrid({
            dataSource: {
                transport: {
                    read: {
                        url: "<%=request.getContextPath() + "/do/ReadPositionAction"%>",
                        dataType: 'json',
                        cache: false
                    },
                    create: {
                        url: "<%=request.getContextPath() + "/do/PositionAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, update: {
                        url: "<%=request.getContextPath() + "/do/UpdatePositionAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, destroy: {
                        url: "<%=request.getContextPath() + "/do/DeletePositionAction"%>",
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
                    model: positionModel
                },
                failure: function(reponse) {
                    alert(response.responseText());
                },
                batch: true,
                pageSize: 10
            },
            columns: [
                {field: "name", title: "Name", width: "150px"},
                {field: "salary", title: "Salary", format: "{0:c}", width: "100px"},
                {field: "raisePerYear", title: "Annual Raise", format: "{0:c}", width: "100px"},
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
