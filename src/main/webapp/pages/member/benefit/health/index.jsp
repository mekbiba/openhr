<div id="benefitTabs">
    <ul>
        <li class="k-state-active">Make Request</li> 
        <li>View Pending</li> 
    </ul>	

    <div>

        <div class="k-content">

            <h2 class="legend">New</h2>			

        </div>
    </div>

    <div>

        <div class="k-content">

            <h2 class="legend">History</h2>

        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        $("#benefitTabs").kendoTabStrip({
            animation: {
                open: {
                    effects: "fadeIn"
                }
            }
        });

        $("#benefitsGrid").kendoGrid({
            selectable: "row",
            sortable: true,
            height: 200
        });



        $("#empIdBenefit").kendoAutoComplete({
            dataSource: new kendo.data.DataSource({
                transport: {
                    read: "<%=request.getContextPath() + "/do/ReadEmployeeAction"%>"
                }
            }),
            change: function(e) {
                $("#calculations").css("display", "block");
            },
            dataTextField: "firstname",
            placeholder: 'Start typing',
            template: kendo.template($("#searchByNameAutoComplete").html())
        });
    });
</script>

<script id="searchByNameAutoComplete" type="text/x-kendo-tmpl">
    <div class="autoCompleteDIV">
    <img width=50 height=50 class='image_preview'  src="#='/OpenHR'+photo#"/> 
    <b>#=employeeId+' - '+firstname+' '+middlename+' '+lastname#</b>
    </div>
</script>
<style>
    div.autoCompleteDIV{
        vertical-align: middle;
        display:block;
    }
</style>