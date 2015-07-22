<%@include file="../../common/jspHeader.jsp" %>
<h2 class="legend">Leave Type Definition</h2> 
<div id="grid">
</div>
<script>
    $(document).ready(function(e) {

        var leaveTypeModel = kendo.data.Model.define({
            id: "id",
            fields: {
                name: {type: "string", validation: {required: true}},
                dayCap: {type: "number", validation: {required: true}}
            }
        });


        $("#grid").kendoGrid({
            dataSource: {
                transport: {
                    read: {
                        url: "<%=request.getContextPath() + "/do/ReadLeaveTypeAction"%>",
                        dataType: 'json',
                        cache: false
                    },
                    create: {
                        url: "<%=request.getContextPath() + "/do/LeaveTypeAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, update: {
                        url: "<%=request.getContextPath() + "/do/UpdateLeaveTypeAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, destroy: {
                        url: "<%=request.getContextPath() + "/do/DeleteLeaveTypeAction"%>",
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
                    model: leaveTypeModel
                },
                batch: true,
                pageSize: 10
            },
            columns: [
                {field: "name", title: "Name", width: "150px"},
                {field: "dayCap", title: "Day cap", width: "100px"},
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
