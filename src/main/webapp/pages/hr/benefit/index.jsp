<%@include file="../../common/jspHeader.jsp" %>
<h2 class="legend">Assign Employee Benefit</h2> 
<div id="grid">
</div>
<script>
    var isCreating = false;
    $(document).ready(function(e) {

        var benefitModel = kendo.data.Model.define({
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
                        photo: "",
                    }
                },
                typeId: {
                    defaultValue: {
                        id: 0,
                        name: "",
                        cap: 0
                    }
                },
                amount: {
                    type: "number"/*,
                     validation: { required: true} */
                }
            }
        });


        benefitGrid = $("#grid").kendoGrid({
            dataSource: {
                transport: {
                    read: {
                        url: "<%=request.getContextPath() + "/do/ReadBenefitAction"%>",
                        dataType: 'json',
                        cache: false
                    },
                    create: {
                        url: "<%=request.getContextPath() + "/do/BenefitAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, update: {
                        url: "<%=request.getContextPath() + "/do/UpdateBenefitAction"%>",
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        type: 'POST'
                    }, destroy: {
                        url: "<%=request.getContextPath() + "/do/DeleteBenefitAction"%>",
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
                    model: benefitModel
                },
                batch: true,
                pageSize: 10
            },
            columns: [
                /*{ title : "Photo", template: '<img  width=70 height=60 src="#=employeeId ? "/OpenHR" + employeeId.photo: ""#"/>',
                 width : 60, filterable : false, editable : false, sortable : false },*/
                {filterable: false,
                    field: "employeeId",
                    title: "Employee Id",
                    editor: employeeDropDownEditor,
                    template: '#=employeeId ? employeeId.employeeId: ""#',
                    width: 120},
                {title: "Full name", template: '#=employeeId ? employeeId.firstname +" "+employeeId.middlename +" "+employeeId.lastname: ""#', width: 180},
                {field: "typeId", title: "Benefit Type", editor: benefitTypeDropDownEditor, template: '#=typeId ? typeId.name: ""#', width: 120},
                {field: "amount", title: "Amount", width: 100},
                {
                    command: ["edit", "destroy"], title: "&nbsp;", /*width: 70,*/ filterable: false
                }

            ],
            toolbar: ["create"],
            editable: "popup",
            sortable: true,
            scrollable: true,
            filterable: true,
            selectable: "row",
            pageable: true

        }).data('kendoGrid');




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


        function benefitTypeDropDownEditor(container, options) {
            $('<input data-text-field="name" data-value-field="id" data-bind="value:' + options.field + '"/>').appendTo(container).kendoDropDownList({
                autoBind: false,
                dataSource: {
                    type: "json",
                    transport: {
                        read: "<%=request.getContextPath() + "/do/ReadBenefitTypeAction"%>"
                    }
                }
            }).data('kendoDropDownList');
        }

    });
</script>