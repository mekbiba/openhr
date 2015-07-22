<div id="horizontal">


    <div id="left-pane" >
        <h2 style="font-size:16px; text-transform : uppercase;text-align:center; background-color:#ccc;color:#000;padding:5px">Actions</h2>
        <table>
            <tr>
                <td><input type="text" value="" placeholder="search messages"/></td>
                <td><input type="button" value="Search"/></td>
            </tr>
            <tr>
                <td><input type="button" value="Inbox(1)"/></td>
                <td></td>
            </tr>
            <tr>
                <td><input type="button" value="Sent(0)"/></td>
                <td></td>
            </tr>
            <tr>
                <td><input type="button" value="Trash(0)"/></td>
                <td></td>
            </tr>
        </table>
    </div>

    <div id="right-pane" >
        <div class="legend" style="padding-right:10px; text-align:right">

            <div style="float:right">
                <input type="button" value="Hide" id="hideMsg"/>
                <label>Order by :</label>
                <select>

                    <option>
                        New First
                    </option>
                    <option>
                        Important First
                    </option>
                </select>			
            </div>

            <div style="float:left">
                <p>Messages on Notice Board</p>		
            </div>
            <div style="clear:both"></div>

        </div>

        <table id="messagesGrid">
            <thead>
                <tr>
                    <th data-field="messageType">Subject</th>
                    <th data-field="from">From</th>
                    <th data-field="date">Date</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Leave</td>
                    <td>mekbib.awoke@moderneth.com</td>
                    <td>Jan 01, 2012</td>
                </tr>

                <tr>
                    <td>Leave</td>
                    <td>eliab@moderneth.com</td>
                    <td>Jan 01, 2012</td>
                </tr>							
            </tbody>
        </table>
    </div>	
</div>


<script>
    $(document).ready(function() {
        $("#messagesGrid").kendoGrid({
            scrollable: true,
            selectable: "multiple, row",
            sortable: true,
            height: 300,
            toolbar: ["save", "cancel"],
            command: "destroy",
            editable: true,
            navigatable: true
        });


        $("#horizontal").kendoSplitter({
            orientation: "horizontal",
            panes: [
                {collapsible: false, size: "250px"},
                {collapsible: false, resizable: true}
            ]
        });


    });
</script>