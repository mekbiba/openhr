<%@include file="../common/jspHeader.jsp"%>


<ul class="menu">
    <li>
        Modules
        <ul>
            <li><a href="<%=request.getContextPath() + "/do/Employee"%>">
                    Employee List</a>
            </li>					
            <li>
                Leave
                <ul>
                    <li><a href="<%=request.getContextPath() + "/do/LeaveType"%>">
                            Define Leave Type</a>
                    </li>
                    <li><a href="<%=request.getContextPath() + "/do/Leave"%>">
                            Assign Leave</a>
                    </li>
                    <li><a href="<%=request.getContextPath() + "/do/ApproveLeave"%>">
                            Approve Leave</a>
                    </li>
                </ul>
            </li>


            <li>
                Pay Groups
                <ul>						
                    <li><a href="<%=request.getContextPath() + "/do/PayGroup"%>">
                            Define Pay Group</a></li>
                    <li><a href="<%=request.getContextPath() + "/do/EmpPayGroup"%>">
                            Assign Pay Group</a></li>
                </ul>
            </li>		

            <li>
                Benefit Packages
                <ul>						
                    <li><a href="<%=request.getContextPath() + "/do/BenefitType"%>">
                            Define Benefit Type</a>
                    </li>
                    <li><a href="<%=request.getContextPath() + "/do/Benefit"%>">
                            Assign Benefit</a>
                    </li>
                </ul>
            </li>



            <li><a href="<%=request.getContextPath() + "/do/Position"%>">
                    Job Titles</a>
            </li>			 
        </ul>
    </li>


    <li><a href="<%=request.getContextPath() + "/do/HRReport"%>">
            Report</a>
    </li>
    <li><a href="<%=request.getContextPath() + "/do/Settings"%>">
            Settings</a>
    </li>
</ul>