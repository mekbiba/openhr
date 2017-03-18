package com.openhr;

import static com.openhr.Payroll.generatePayroll;
import static com.openhr.factories.BenefitViewFactory.findAllBenefitTypes;
import static jxl.Workbook.createWorkbook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import com.openhr.data.EmployeePayroll;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Orientation;
import jxl.format.PageOrientation;
import jxl.write.Alignment;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.Pattern;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Report {

   
    public void run(HttpServletResponse response) {
        WritableWorkbook payrollWorkBook = null;
        try {
        	
            int offSet = 2;
            String reportName = "COMPANYNAME_Payroll_Sheet_" + new SimpleDateFormat("MMM_yyyy").format(new Date());
            response.setHeader("Content-Disposition", "attachment; filename=PayRoll.xls");
            payrollWorkBook = createWorkbook(response.getOutputStream());
            WritableSheet s = payrollWorkBook.createSheet("Payroll", 0);
            WritableSheet s2 = payrollWorkBook.createSheet("Statements", 1);

            Label temp = null;
            s.getSettings().setAutomaticFormulaCalculation(true);
            s.mergeCells(0 + offSet, 0, 7 + offSet, 1);
            s.getSettings().setDefaultColumnWidth(15);
            s.getSettings().setOrientation(PageOrientation.LANDSCAPE);
            NumberFormat currency = new NumberFormat(
                    NumberFormat.CURRENCY_DOLLAR + " ###,###.00",
                    NumberFormat.COMPLEX_FORMAT);
            WritableFont arialFont = new WritableFont(WritableFont.ARIAL, 9);
            WritableFont arialFont1 = new WritableFont(WritableFont.ARIAL, 9);
            WritableFont headingFont = new WritableFont(WritableFont.ARIAL, 16);
            arialFont.setColour(Colour.BLACK);
            arialFont1.setColour(Colour.AUTOMATIC);
            WritableCellFormat cellFormat = new WritableCellFormat(arialFont);
            WritableCellFormat cellFormat1 = new WritableCellFormat(arialFont1);
            cellFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat1.setBackground(Colour.WHITE);
            cellFormat1.setWrap(true);
            WritableCellFormat cellFormat2 = new WritableCellFormat(arialFont1);
            cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat2.setWrap(true);
            WritableCellFormat netPayCellFormat = new WritableCellFormat(
                    arialFont1, currency);

            netPayCellFormat.setBackground(Colour.ICE_BLUE);
            netPayCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            cellFormat2.setBackground(Colour.VERY_LIGHT_YELLOW);
            WritableCellFormat headingFormat = new WritableCellFormat(
                    headingFont);
            headingFormat.setAlignment(Alignment.CENTRE);

            cellFormat.setAlignment(Alignment.CENTRE);
            cellFormat.setBackground(Colour.GRAY_25, Pattern.SOLID);
            cellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
            cellFormat.setOrientation(Orientation.HORIZONTAL);

            List benefitTypes = findAllBenefitTypes();
            List columnHeaders = new ArrayList<>();
            columnHeaders.add(" EMPLOYEE ID ");
            columnHeaders.add(" FULL NAME ");
            columnHeaders.add(" GROSS SALARY ");

            columnHeaders.add(" BENEFIT TITLE ");
            columnHeaders.add(" AMOUNT ");
            columnHeaders.add("TAX(%)");
            columnHeaders.add("TAXED");
            columnHeaders.add("NET PAY");

            s2.mergeCells(0 + offSet, 0, 4 + offSet, 1);
            temp = new Label(0 + offSet, 0, "Statements ", headingFormat);
            s2.addCell(temp);

            temp = new Label(0 + offSet, 0, "COMPANY-NAME Payroll for Month : "
                    + new SimpleDateFormat("MMM, yyyy").format(new Date()),
                    headingFormat);

            s.addCell(temp);

            Number number;
            Double taxable = 0.0;
            Double benefitAmnt = 0.0;
            Double totalPayable = 0.0;
            Double taxRate = 0.0;
            for (int i = 0; i < columnHeaders.size(); i++) {
                temp = new Label(i + offSet, 2,
                        columnHeaders.get(i).toString(), cellFormat);
                s.addCell(temp);
            }

            List<EmployeePayroll> payroll = generatePayroll();
            System.out.println("SIZE OF THE LIST " + payroll.size());
            for (int y = 3; y < payroll.size() + 3; y++) {
                System.out.println(y - 3);
                for (int i = 0; i < columnHeaders.size(); i++) {
                    switch (i) {
                        case 0:
                            if ((y - 3) % 2 == 0) {
                                temp = new Label(i + offSet, y, payroll.get(y - 3)
                                        .getEmployeeid(), cellFormat1);
                            } else {
                                temp = new Label(i + offSet, y, payroll.get(y - 3)
                                        .getEmployeeid(), cellFormat2);
                            }
                            s.addCell(temp);
                            break;

                        case 1:
                            if ((y - 3) % 2 == 0) {
                                temp = new Label(i + offSet, y, payroll.get(y - 3)
                                        .getFullName(), cellFormat1);
                            } else {
                                temp = new Label(i + offSet, y, payroll.get(y - 3)
                                        .getFullName(), cellFormat2);
                            }
                            s.addCell(temp);
                            break;

                        case 2:
                            if ((y - 3) % 2 == 0) {
                                number = new Number(i + offSet, y, payroll.get(
                                        y - 3).getGrossSalary(), cellFormat1);
                            } else {
                                number = new Number(i + offSet, y, payroll.get(
                                        y - 3).getGrossSalary(), cellFormat2);
                            }
                            s.addCell(number);
                            break;

                        case 3:
                            String benefitType = payroll.get(y - 3)
                                    .getBenefitType() != null ? payroll.get(y - 3)
                                    .getBenefitType() : "No Benefit";
                            if ((y - 3) % 2 == 0) {
                                temp = new Label(i + offSet, y, benefitType,
                                        cellFormat1);
                            } else {
                                temp = new Label(i + offSet, y, benefitType,
                                        cellFormat2);
                            }
                            s.addCell(temp);
                            break;

                        case 4:
                            benefitAmnt = payroll.get(y - 3).getBenefitAmnt() != null ? payroll
                                    .get(y - 3).getBenefitAmnt() : 0.0;
                            if ((y - 3) % 2 == 0) {
                                number = new Number(i + offSet, y, benefitAmnt,
                                        cellFormat1);
                            } else {
                                number = new Number(i + offSet, y, benefitAmnt,
                                        cellFormat2);
                            }
                            s.addCell(number);
                            break;

                        case 5:
                            taxRate = 0.15;
                            if (payroll.get(y - 3).getGrossSalary() < 0) {
                                payroll.get(y - 3).setGrossSalary(0);
                            }
                            if (payroll.get(y - 3).getGrossSalary() <= 150) {
                                taxRate = 0.0;
                            }
                            if (payroll.get(y - 3).getGrossSalary() > 150
                                    && payroll.get(y - 3).getGrossSalary() <= 650) {
                                taxRate = 0.1;

                            }
                            if (payroll.get(y - 3).getGrossSalary() > 650
                                    && payroll.get(y - 3).getGrossSalary() <= 1400) {
                                taxRate = 0.15;
                            }
                            if (payroll.get(y - 3).getGrossSalary() > 1400
                                    && payroll.get(y - 3).getGrossSalary() <= 2350) {
                                taxRate = 0.2;
                            }
                            if (payroll.get(y - 3).getGrossSalary() > 2350
                                    && payroll.get(y - 3).getGrossSalary() <= 3550) {
                                taxRate = 0.25;
                            }
                            if (payroll.get(y - 3).getGrossSalary() > 3550
                                    && payroll.get(y - 3).getGrossSalary() <= 5000) {
                                taxRate = 0.3;
                            }
                            if (payroll.get(y - 3).getGrossSalary() > 5000) {
                                taxRate = 0.35;
                            }
                            if ((y - 3) % 2 == 0) {

                                number = new Number(i + offSet, y, 100 * taxRate,
                                        cellFormat1);
                            } else {
                                number = new Number(i + offSet, y, 100 * taxRate,
                                        cellFormat2);
                            }

                            s.addCell(number);
                            break;

                        case 6:
                            taxable = payroll.get(y - 3).getGrossSalary() * taxRate;
                            if ((y - 3) % 2 == 0) {
                                number = new Number(i + offSet, y, taxable,
                                        cellFormat1);
                            } else {
                                number = new Number(i + offSet, y, taxable,
                                        cellFormat2);
                            }

                            s.addCell(number);
                            break;

                        case 7:
                            Double netPay = (payroll.get(y - 3).getGrossSalary() + benefitAmnt)
                                    - taxable;
                            totalPayable += netPay;

                            number = new Number(i + offSet, y, netPay,
                                    netPayCellFormat);

                            s.addCell(number);
                            break;

                    }

                }
            }

            WritableFont totalFont = new WritableFont(WritableFont.ARIAL, 12,
                    WritableFont.BOLD);
            WritableCellFormat totalLabelFormat = new WritableCellFormat(
                    totalFont);
            WritableCellFormat totalFormat = new WritableCellFormat(totalFont,
                    currency);
            temp = new Label(offSet + (columnHeaders.size() - 2),
                    payroll.size() + 3, "Total", totalLabelFormat);
            s.addCell(temp);
            number = new Number(offSet + (columnHeaders.size() - 1),
                    payroll.size() + 3, totalPayable, totalFormat);
            s.addCell(number);

            temp = new Label(offSet + (columnHeaders.size() - 2),
                    payroll.size() + 6, "Approved By : ");
            s.addCell(temp);
            temp = new Label(offSet + (columnHeaders.size() - 1),
                    payroll.size() + 6, "____________________");
            s.addCell(temp);
            temp = new Label(offSet + (columnHeaders.size() - 2),
                    payroll.size() + 5, "Generated time : ");
            s.addCell(temp);

            temp = new Label(offSet + (columnHeaders.size() - 1),
                    payroll.size() + 5, new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss").format(new Date()));
            s.addCell(temp);

            payrollWorkBook.write();

        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
				response.flushBuffer();
            	payrollWorkBook.close();
				
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
