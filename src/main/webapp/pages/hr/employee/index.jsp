<%@include file="../../common/jspHeader.jsp" %>
<h2 class="legend">Employee Form</h2> 
<div id="grid">
</div>
<div id="empForm">
</div>
<script>
    var createNewEmpForm;
    var dropDownURL = "<%=request.getContextPath()%>" + "/do/ReadPositionAction";
    var empDataSource;
    var empWindow;
    var empFormPath = "<%=request.getContextPath()%>" + "/pages/hr/employee/employeeForm.jsp";

    status = [{"id": "ACTIVE", "value": "ACTIVE"}, {"id": "IN ACTIVE", "value": "IN ACTIVE"}];
    sex = [{"id": "Male", "value": "Male"}, {"id": "Female", "value": "Female"}];

    $(document).ready(function(e) {

        var employeeModel = kendo.data.Model.define({
            id: "id",
            fields: {
                employeeId: {
                    type: "string",
                    validation: {
                        required: true
                    }
                },
                firstname: {
                    type: "string",
                    validation: {
                        required: true
                    }
                },
                middlename: {
                    type: "string",
                    validation: {
                        required: true
                    }
                },
                lastname: {
                    type: "string",
                    validation: {
                        required: true
                    }
                },
                sex: {
                    type: "String",
                    validation: {
                        required: true
                    },
                    defaultValue: "Male"
                },
                birthdate: {
                    type: "date",
                    validation: {
                        required: true
                    }
                },
                hiredate: {
                    type: "date",
                    validation: {
                        required: true
                    }
                },
                positionId: {
                    defaultValue: {
                        id: 0,
                        name: "",
                        salary: 0,
                        raisePerYear: 0
                    }
                },
                photo: {
                    type: "string",
                    validation: {
                        required: true
                    }
                },
                status: {
                    type: "string",
                    validation: {
                        required: true
                    },
                    defaultValue: "ACTIVE"
                }
            }
        });


        empDataSource = new kendo.data.DataSource({
            transport: {
                read: {
                    url: "<%=request.getContextPath() + "/do/ReadEmployeeAction"%>",
                    dataType: "json",
                    cache: false
                },
                destroy: {
                    url: "<%=request.getContextPath() + "/do/DeleteEmployeeAction"%>",
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    type: 'POST',
                    complete: function(jqXHR, textStatus) {
                        alert(textStatus);
                    }
                },
                parameterMap: function(options, operation) {
                    if (operation !== "read" && options.models) {
                        return JSON.stringify(options.models);
                    }
                }
            },
            schema: {
                model: employeeModel
            },
            pageSize: 15,
            autoSync: false,
            batch: true
        });


        var grid = $("#grid").kendoGrid({
            dataSource: empDataSource,
            columns: [
                /*{ field : "photo", title : "Photo" ,template : "<img width=80 height=100 src='#='/OpenHR'+photo#'/>", width : 95,filterable :false},*/
                {field: "employeeId", title: "Id", width: 120},
                {field: "firstname", title: "First Name", width: 100, filterable: false},
                {field: "middlename", title: "Middle Name", width: 100, filterable: false},
                {field: "lastname", title: "Last Name", width: 100, filterable: false},
                {field: "sex", title: "Sex", editor: sexDropDownEditor, width: 80},
                {field: "positionId", title: "Title", filterable: false, editor: positionDropDownEditor, groupable: false, template: '#=positionId ? positionId.name: ""#', width: 100},
                {field: "birthdate", title: "Birth date", template: "#= kendo.toString(new Date(birthdate), 'MMM, dd yyyy') #", width: 100, filterable: false},
                {field: "hiredate", title: "Hired date", template: "#= kendo.toString(new Date(hiredate) , 'MMM, dd yyyy') #", width: 100},
                {field: "status", title: "Status", editor: statusDropDownEditor, width: 100},
                {command: [{"name": "edit", className: "editEmp"}, "destroy"], /* width : 100,*/ filterable: false}

            ],
            dataBound: function() {
                $.each(empDataSource.data(), function() {
                    this.birthdate = new Date(this.birthdate);
                    this.hiredate = new Date(this.hiredate);
                });
            },
            toolbar: [{"name": "create", className: "newEmp", text: "Add New Employee"}],
            sortable: true,
            scrollable: true,
            height: 465,
            filterable: true,
            groupable: true,
            resizeable: true,
            reorderable: true,
            selectable: "row",
            editable: "popup",
            pageable: true
        }).data("kendoGrid");




        $("#grid").delegate(".newEmp", "click", function(e) {
            e.preventDefault();
            createNewEmpForm();
            empWindow.open();
            empWindow.center();
        });


        createNewEmpForm = function() {
            if (empWindow)
                empWindow.content("");
            empWindow = $("#empForm").kendoWindow({
                title: "EmployeeForm",
                content: empFormPath,
                modal: true,
                resizable: false,
                width: 650
            }).data("kendoWindow");

            empWindow.open();
            empWindow.center();

        };

        $("#grid").delegate(".editEmp", "click", function(e) {
            e.preventDefault();
            var dataItem = grid.dataItem($(this).closest("tr"));
            var empTemplate = kendo.template($("#employeeTemplate").html());


            var wnd = $("#empForm").kendoWindow({
                title: "Employee Details",
                modal: true,
                resizable: false,
                width: 700
            }).data("kendoWindow");


            wnd.content(empTemplate(dataItem));
            $("#cancelEmp").click(function() {
                wnd.content('');
                wnd.close();
            });

            var selectedSex = dataItem.sex;
            var selectedStatus = dataItem.status;
            var selectedPos = JSON.stringify(dataItem.positionId.id);
            $("#positions").kendoDropDownList({
                editor: positionDropDownEditor,
                dataTextField: "name",
                dataValueField: "id",
                dataSource: {
                    type: "json",
                    transport: {
                        read: "<%=request.getContextPath() + "/do/ReadPositionAction"%>"
                    }
                }
            });

            if (!$("#sex").data("kendoDropDownList"))
                $("#sex").kendoDropDownList({
                    editor: sexDropDownEditor,
                    dataTextField: "value",
                    dataValueField: "id",
                    dataSource: {data: sex}
                });

            //if(!$("#status1").data("kendoDropDownList"))
            $("#status1").kendoDropDownList(/*{
             editor: statusDropDownEditor,
             dataTextField: "value",
             dataValueField: "id",
             dataSource :{ data : status}
             }*/);

            $("#sex").data("kendoDropDownList").value(selectedSex);

            $("#status1").data("kendoDropDownList").value(selectedStatus);

            $("#positions").data("kendoDropDownList").value(selectedPos);
            $("#birthdate").kendoDatePicker();
            $("#hiredate").kendoDatePicker();


            wnd.open();
            wnd.center();





            $("#saveEmp").bind("click", function() {
                //crop variables
                var x, y, width, height;
                x = $('#x').val();
                y = $('#y').val();
                width = $('#w').val();
                height = $('#h').val();

                var croppingData = JSON.stringify({
                    "x": x,
                    "y": y,
                    "width": width,
                    "height": height,
                    "employeeId": $("#employeeId").val()
                });




                var id, employeeId, firstname, middlename, lastname, sex, birthdate, hiredate, positionId, photo;








                id = $("#id").val();
                employeeId = $("#employeeId").val();
                firstname = $("#firstname").val();
                middlename = $("#middlename").val();
                lastname = $("#lastname").val();
                sex = $("#sex").val();

                positionId = $("#positions").val();
                status = $("#status1").val();


                var bdate = new Date($("#birthdate").val());
                var hdate = new Date($("#hiredate").val());
                birthdate = bdate.getTime();
                hiredate = hdate.getTime();

                var yrDifference = parseInt(hdate.getFullYear()) - parseInt(bdate.getFullYear());
                //alert(yrDifference);
                if (yrDifference <= 0) {
                    alert('Invalid date entry, please enter valid dates!');
                    return;
                } else if (yrDifference < 14) {
                    alert('The system cannot save profile for person under the age of 14 \n' +
                            '\n Contact Administrator for support');
                    return;
                }



                if ($("#profilePicUploader").val()) {
                    photo = "/data/photo/" + $("#profilePicUploader").val();
                    $.ajax({
                        url: "<%=request.getContextPath() + "/do/PhotoCropAction"%>",
                        type: 'POST',
                        dataType: 'json',
                        contentType: 'application/json; charset=utf-8',
                        data: croppingData,
                        success: function() {
                            //alert("cropping dimension successfully uploaded");
                        },
                        failure: function(e) {
                            alert(e.responseText);
                        }
                    });


                    $.ajaxFileUpload({
                        url: "<%=request.getContextPath() + "/do/EmployeePhotoAction"%>",
                        secureuri: false,
                        fileElementId: 'profilePicUploader',
                        dataType: 'json',
                        success: function(data, status)
                        {
                            if (typeof (data.error) != 'undefined')
                            {
                                if (data.error != '')
                                {
                                    alert(data.error);
                                } else
                                {
                                    alert(data.msg);
                                }
                            }
                        },
                        error: function(data, status, e)
                        {
                            alert(e);
                        }
                    });
                } else {
                    photo = $("#photo").val();
                }

                var updateData = JSON.stringify([{
                        "id": id,
                        "employeeId": employeeId,
                        "firstname": firstname,
                        "middlename": middlename,
                        "lastname": lastname,
                        "sex": sex,
                        "birthdate": birthdate,
                        "hiredate": hiredate,
                        "positionId": positionId,
                        "photo": photo,
                        "status": status
                    }]);

                $.ajax({
                    type: "POST",
                    url: '<%=request.getContextPath()+ "/do/UpdateEmployeeAction"%>',
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: updateData,
                    success: function() {
                        wnd.close();
                        alert("Employee Record updated successfully!");
                        location.reload();
                    }
                });
            });
        });


    });



    function sexDropDownEditor(container, options) {
        $('<input data-text-field="value" data-value-field="id" data-bind="value:' + options.field + '"/>').appendTo(container).kendoDropDownList({
            autoBind: false,
            dataSource: {
                data: sex
            },
            dataTextField: "id",
            dataValueField: "value"
        });
    }


    function statusDropDownEditor(container, options) {
        $('<input data-text-field="value" data-value-field="id" data-bind="value:' + options.field + '"/>').appendTo(container).kendoDropDownList({
            autoBind: false,
            dataSource: {
                data: status
            },
            dataTextField: "id",
            dataValueField: "value"
        });
    }


    function positionDropDownEditor(container, options) {
        $('<input data-text-field="name" data-value-field="id" data-bind="value:' + options.field + '"/>').appendTo(container).kendoDropDownList({
            autoBind: false,
            dataSource: {
                type: "json",
                transport: {
                    read: "<%=request.getContextPath() + "/do/ReadPositionAction"%>"
                }
            }
        });
    }

</script>  

<script type="text/x-kendo-template" id="employeeTemplate">
    <div id="employeeForm">
    <div class="clear"></div>
    <div id="left-col">
    <div>
    <div class="label">EMP-ID</div>
    <div class="field">
    <input type="hidden" id="id" value="#=id#"/>
    <input type="hidden" id="photo" value="#=photo#"/>
    <input type="text" disabled=disabled class="k-input k-textbox" id="employeeId"  value="#=employeeId#" />
    </div>
    <div class="clear"></div>
    </div>


    <div>
    <div class="label">First name</div>
    <div class="field">
    <input type="text" class="k-input k-textbox" id="firstname"  value="#=firstname#" />
    </div>
    <div class="clear"></div>
    </div>



    <div>
    <div class="label">Middle name</div>
    <div class="field">
    <input type="text" class="k-input k-textbox"  id="middlename" value="#=middlename#" />
    </div>
    <div class="clear"></div>
    </div>



    <div>
    <div class="label">Last name</div>
    <div class="field">
    <input type="text" class="k-input k-textbox" id="lastname"  value="#=lastname#" />
    </div>
    <div class="clear"></div>
    </div>



    <div>
    <div class="label">Sex</div>
    <div class="field">
    <input class="sex_input" id="sex" value="#=sex#"/>
    </div>
    <div class="clear"></div>
    </div>



    <div>
    <div class="label">Birth date</div>
    <div class="field">
    <input type="text" data-type="date" data-role="datepicker" class="k-input k-textbox" 
    id="birthdate" value="#= kendo.toString(new Date(birthdate), 'MMM, dd yyyy')#" />
    </div>
    <div class="clear"></div>
    </div>



    <div>
    <div class="label">Hire date</div>
    <div class="field">
    <input type="text" data-type="date" data-role="datepicker" class="k-input k-textbox" 
    id="hiredate" value="#= kendo.toString(new Date(hiredate), 'MMM, dd yyyy')#" />
    </div>
    <div class="clear"></div>
    </div>



    <div>
    <div class="label">Position/ Title</div>
    <div class="field">
    <input id="positions" value="#=positionId ? positionId.name : ''#"/>
    </div>
    <div class="clear"></div>
    </div>


    <div>
    <div class="label">Status</div>
    <div class="field">
    <select id="status1">
    <option value="ACTIVE">ACTIVE</option>
    <option value="IN ACTIVE">IN ACTIVE</option>
    </select> 	
    </div>
    <div class="clear"></div>
    </div>

    <div>
    <div class="field">
    <a class="k-button k-icontext" id="saveEmp"><span class="k-icon k-update"></span>Update</a> <a
    class="k-button k-icontext" id="cancelEmp"><span class="k-icon k-cancel"></span>Cancel</a>
    </div>
    <div class="clear"></div>
    </div>
    </div>


    <div id="right-col">
    <div style="height:250px;width:200px;overflow:hidden">
    <img id="preview" class="k-image j-cropview"
    height=250 width=200 src="#='/OpenHR'+photo#"/>
    <div class="clear"></div>
    </div>

    <div>
    <div class="field">
    <html:form action="/EmployeePhotoAction" styleClass="fileForm" method="post" enctype="multipart/form-data">
        <html:file property="photoFile" styleId="profilePicUploader"
                   onchange="readURL(this)" /> </html:form>
    </div>
    <div class="clear"></div>
    </div>

    </div>
    <div class="clear"></div>

    </div>
    <div></div>
    <div id="imageCropper" style="display:none">	
    <a id="cropImage" class="k-button">Done</a>
    <input type=hidden id="x"/>
    <input type=hidden id="y"/>
    <input type=hidden id="w"/>
    <input type=hidden id="h"/>
    <img  id="target" src="" style="display:none"/>	
    </div>

</script>

<script>
    var cropperWindow;
    var jcrop_api, boundx, boundy;

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function(e) {

                $("#preview").attr('src', e.target.result);
                $("#target").attr('src', e.target.result);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
</script> 