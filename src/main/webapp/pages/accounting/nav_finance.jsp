<%@include file="../common/jspHeader.jsp"%>
<ul class="menu">	
    <li>
        <a href="<%=request.getContextPath() + "/do/Payroll"%>">
            Payroll</a>
    </li>

    <li>
        Deductions
        <ul>
            <li><a href="<%=request.getContextPath() + "/do/DeductionType"%>">
                    Deduction Types</a>
            </li>
            <li><a href="<%=request.getContextPath() + "/do/Deduction"%>">
                    Deductions</a>
            </li>
        </ul>				
    </li>

    <li>
        <a href="<%=request.getContextPath() + "/do/AccountingReport"%>">
            Report</a>
    </li>	
</ul>