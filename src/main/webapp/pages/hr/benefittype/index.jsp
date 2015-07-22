<%@include file="../../common/jspHeader.jsp" %>
<h2 class="legend">Benefit Type Definition</h2> 
<div id="grid">
</div>
<script>
    $(document).ready(function(e) {

        var BenefitTypeModel = kendo.data.Model.define({
            id: "id",
            fields: {
                name: {type: "string", validation: {required: true}},
                cap: {type: "number", validation: {required: true}}
            }
        });


        $("#grid").kendoGrid({
            dataSource: {
                transport: {
                    read: {
                        url: "<%=request.getContextPath() + "/do/ReadBenefitTypeAction"%>",
                        dataType: 'json',
                        cache: false
                    },
                    create: {
                        url: "<%=request.getContextPath() + "/do/BenefitTypeAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, update: {
                        url: "<%=request.getContextPath() + "/do/UpdateBenefitTypeAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, destroy: {
                        url: "<%=request.getContextPath() + "/do/DeleteBenefitTypeAction"%>",
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
                    model: BenefitTypeModel
                },
                batch: true,
                pageSize: 10
            },
            columns: [
                {field: "name", title: "Name", width: "150px"},
                {field: "cap", title: "Cap", width: "100px"},
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
