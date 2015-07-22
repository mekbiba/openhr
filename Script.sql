--<ScriptOptions statementTerminator=";"/>

DROP TABLE `database`.`emp_payroll_view`;

CREATE TABLE `database`.`emp_payroll_view` (
	`EMPLOYEEID` VARCHAR(45) NOT NULL,
	`FULL_NAME` VARCHAR(137) NOT NULL,
	`GROSS_SALARY` DOUBLE NOT NULL,
	`BENEFIT_TYPE` VARCHAR(45),
	`BENEFIT_AMNT` DOUBLE
);

