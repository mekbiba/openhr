<%@include file="../common/jspHeader.jsp"%>


<ul class="menu">
    <li>Modules
        <ul>
            <li>
                Security
                <ul>
                    <li><a href="<%=request.getContextPath() + "/do/Role"%>">
                            Manage Roles</a></li>
                    <li><a href="<%=request.getContextPath() + "/do/User"%>">
                            Manage Users</a></li>
                </ul>
            </li>
        </ul>
    </li>


    <li><a href="<%=request.getContextPath() + "/do/AdminReport"%>">
            Report</a>
    </li>	
</ul>