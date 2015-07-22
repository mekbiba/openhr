<h2 class="legend">Employee Home</h2> 
<div id="benefitTabs">
    <ul>
        <li class="k-state-active">Make Request</li> 
    </ul>



    <div>

        <div class="k-content">

            <h2 class="legend">View Benefit History</h2>
            <div>
                <input type="submit" value="+New"/>
                <input type="submit" value="-Delete"/>
                <input type="submit" value="_,Edit"/>
            </div>
            <table id="benefitsGrid">

                <thead>
                    <tr>
                        <th data-field="requestDate">Requested On Date</th>
                        <th data-field="requestedLeavingDate">Request Benefit Type</th>
                        <th data-field="requestedReturningDate">Amount</th>
                        <th data-field="status">Status</th>
                        <th data-field="approvedBy">Approved Date</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>11/07/2012</td>
                        <td>Overtime Payment</td>
                        <td>$ 250</td>
                        <td>Pending</td>
                        <td>-</td>
                    </tr>
                    <tr>
                        <td>11/07/2012</td>
                        <td>Transport Allowance</td>
                        <td>$ 150</td>
                        <td>Approved</td>
                        <td>13/01/2012</td>
                    </tr>


                </tbody>
            </table>
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