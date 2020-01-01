DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
                          empId VARCHAR(10) NOT NULL,
                          empName VARCHAR(100) NOT NULL
);

INSERT INTO employee(empId,empName)values("emp001","emp1");
INSERT INTO employee(empId,empName)values("emp002","emp2");
INSERT INTO employee(empId,empName)values("emp003","emp3");