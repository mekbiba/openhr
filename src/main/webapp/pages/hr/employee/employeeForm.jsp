<%@page import="com.openhr.employee.EmployeeIdUtility"%>
<%@page import="com.openhr.Config"%>
<%Config.readConfig();%>
<div id="employeeForm">
    <div class="clear"></div>
    <div id="left-col">
        <div>
            <div class="label">EMP-ID</div>
            <div class="field">
                <input type="text" readonly="readonly" class="k-input k-textbox" id="employeeId"  
                       value="<%=Config.employeeIdPattern + "-" + EmployeeIdUtility.nextId()%>" />
            </div>
            <div class="clear"></div>
        </div>


        <div>
            <div class="label">First name</div>
            <div class="field">
                <input type="text" required=required class="k-input k-textbox" id="firstname"  value="" />
            </div>
            <div class="clear"></div>
        </div>



        <div>
            <div class="label">Middle name</div>
            <div class="field">
                <input type="text" required=required class="k-input k-textbox" id="middlename"  value="" />
            </div>
            <div class="clear"></div>
        </div>



        <div>
            <div class="label">Last name</div>
            <div class="field">
                <input type="text" required=required class="k-input k-textbox" id="lastname"  value="" />
            </div>
            <div class="clear"></div>
        </div>



        <div>
            <div class="label">Sex</div>
            <div class="field" >
                <select id="sex" >
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                </select>
            </div>
            <div class="clear"></div>
        </div>



        <div>
            <div class="label">Birth date</div>
            <div class="field">
                <input type="text" class="k-input k-textbox" 
                       id="birthdate" value="" />
            </div>
            <div class="clear"></div>
        </div>



        <div>
            <div class="label">Hire date</div>
            <div class="field">
                <input type="text" class="k-input k-textbox" 
                       id="hiredate" value="" />
            </div>
            <div class="clear"></div>
        </div>



        <div>
            <div class="label">Position/ Title</div>
            <div class="field">
                <input id="positionDropDownList"/>
            </div>
            <div class="clear"></div>
        </div>


        <div>
            <div class="label">Status</div>
            <div class="field">
                <select id="status">
                    <option value="ACTIVE">Active</option>
                    <option value="IN ACTIVE">In Active</option>
                </select>
            </div>
            <div class="clear"></div>
        </div>

        <div>
            <div class="field">
                <a class="k-button k-icontext" id="saveEmp"><span class="k-add k-icon"></span>Save</a> <a
                    class="k-button k-icontext" id="cancelEmp"><span class="k-cancel k-icon"></span>Cancel</a>
            </div>
            <div class="clear"></div>
        </div>
    </div>


    <div id="right-col">
        <div style="width:200px; height:250px; overflow:hidden">
            <img id="preview" style="border:2px solid #999;" class="k-image j-cropview"
                 height=250 width=200 src="http://localhost:8080/OpenHR/data/photo/placeholder-pic.png"/>
            <div class="clear"></div>
        </div>

        <div>
            <div class="field">
                <input type="file" required=required id="profilePicUploader"
                       onchange="readURL(this)" />
            </div>
            <div class="clear"></div>
        </div>





    </div>
    <div class="clear"></div>

</div>
<div></div> 


<script>


    $(document).ready(function() {
        var today = new Date();

        //$("#employeeId").mask("aaaaaa-9999" , {placeholder:"-"});


        $("#hiredate").kendoDatePicker({
            value: today
        });

        $("#birthdate").kendoDatePicker();

        $("#birthdate").data('kendoDatePicker').value(new Date('1960/01/01'));

        $("#cancelEmp").bind("click", function() {
            empWindow.content('');
            empWindow.close();
        });
        $("#saveEmp").bind("click", function() {

            var id = '', employeeId, firstname, middlename, lastname, sex, birthdate, hiredate, positionId, photo;
            //crop variables
            var x, y, width, height;
            //id = $("#id").val(); 
            employeeId = $("#employeeId").val();
            firstname = $("#firstname").val();
            middlename = $("#middlename").val();
            lastname = $("#lastname").val();
            sex = $("#sex").val();




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
                        '\n\n Contact Administrator for support');
                return;
            }

            positionId = $("#positionDropDownList").val();

            status = $("#status").val();

            x = $('#x').val();
            y = $('#y').val();
            width = $('#w').val();
            height = $('#h').val();

            var croppingData = JSON.stringify({
                "x": x,
                "y": y,
                "width": width,
                "height": height,
                "employeeId": employeeId
            });

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


            if ($("#profilePicUploader").val()) {
                photo = $("#profilePicUploader").val();

                $.ajaxFileUpload({
                    url: '<%=request.getContextPath()+ "/do/EmployeePhotoAction"%>',
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
                photo = "/data/photo/placeholder-pic.png";
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
                url: '<%=request.getContextPath()+ "/do/EmployeeAction"%>',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: updateData,
                success: function() {
                    empWindow.close();
                    alert("Employee Record created successfully!");
                    location.reload();
                }

            });
        });






        $("#positionDropDownList").kendoDropDownList({
            dataTextField: "name",
            dataValueField: "id",
            dataSource: {
                type: "json",
                transport: {
                    read: "<%=request.getContextPath()+ "/do/ReadPositionAction"%>"
                }
            }
        });
    });
</script>

<div id="imageCropper" style="display:none">	
    <a id="cropImage" class="k-button">OK</a>
    <input type=hidden id="x"/>
    <input type=hidden id="y"/>
    <input type=hidden id="w"/>
    <input type=hidden id="h"/>
    <img  alt="" id="target" src=""/>	
</div>
<script>
    var cropperWindow;

    var jcrop_api, boundx, boundy;
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function(e) {
                $("#preview").attr('src', e.target.result);
                /*$("#target").attr('src', e.target.result);
                 jcrop_api = $('#target').Jcrop({
                 aspectRatio : 1, 
                 onChange: updatePreview,
                 onSelect: updatePreview		         
                 },function(){
                 // Use the API to get the real image size
                 var bounds = this.getBounds();
                 boundx = bounds[0];
                 boundy = bounds[1];
                 // Store the API in the jcrop_api variable
                 jcrop_api = this;
                 });
                 $("#imageCropper").css("display" , "block");
                 $("#imageCropper").css("width","400px");
                 $("#imageCropper").css("height","400px")
                 cropperWindow = $("#imageCropper").kendoWindow({
                 title : "Employee Photo Editor",
                 width : "auto",
                 modal : true
                 }).data("kendoWindow");
                 
                 cropperWindow.center();
                 cropperWindow.open();
                 $("#cropImage").bind("click", function(){
                 cropperWindow.empty();
                 cropperWindow.close();
                 });
                 function updatePreview(c){ 
                 updateCoords(c);
                 if (parseInt(c.w) > 0)
                 {
                 var rx = 200 / c.w;
                 var ry = 200 / c.h; 
                 $('#preview').css({
                 width: Math.round(rx * boundx) + 'px',
                 height: Math.round(ry * boundy) + 'px',
                 marginLeft: '-' + Math.round(rx * c.x) + 'px',
                 marginTop: '-' + Math.round(ry * c.y) + 'px'
                 });
                 }
                 }
                 */
                /*function updateCoords(c)
                 {
                 $('#x').val(c.x);
                 $('#y').val(c.y);
                 $('#w').val(c.w);
                 $('#h').val(c.h);				
                 };*/
            }

            reader.readAsDataURL(input.files[0]);
        }
    }
</script>