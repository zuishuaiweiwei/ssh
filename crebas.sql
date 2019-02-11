/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     8/4 20:20:44                                 */
/*==============================================================*/


DROP TABLE IF EXISTS emp_role;

DROP TABLE IF EXISTS lingdao;

DROP TABLE IF EXISTS role_privilege;

DROP TABLE IF EXISTS t_dept;

DROP TABLE IF EXISTS t_employee;

DROP TABLE IF EXISTS t_org;

DROP TABLE IF EXISTS t_privilege;

DROP TABLE IF EXISTS t_role;

/*==============================================================*/
/* Table: emp_role                                              */
/*==============================================================*/
CREATE TABLE emp_role
(
  emp_id  VARCHAR(32) NOT NULL,
  role_id VARCHAR(32) NOT NULL,
  state   INT,
  PRIMARY KEY (emp_id, role_id)
);

/*==============================================================*/
/* Table: lingdao                                               */
/*==============================================================*/
CREATE TABLE lingdao
(
  emp_id   VARCHAR(32) NOT NULL,
  dept_id  VARCHAR(32),
  name     VARCHAR(32),
  position VARCHAR(32),
  PRIMARY KEY (emp_id)
);

/*==============================================================*/
/* Table: role_privilege                                        */
/*==============================================================*/
CREATE TABLE role_privilege
(
  role_id VARCHAR(32) NOT NULL,
  pri_id  VARCHAR(32) NOT NULL,
  PRIMARY KEY (role_id, pri_id)
);

/*==============================================================*/
/* Table: t_dept                                                */
/*==============================================================*/
CREATE TABLE t_dept
(
  dept_id VARCHAR(32) NOT NULL,
  org_id  VARCHAR(32) NOT NULL,
  name    VARCHAR(32),
  PRIMARY KEY (dept_id)
);

/*==============================================================*/
/* Table: t_employee                                            */
/*==============================================================*/
CREATE TABLE t_employee
(
  emp_id  VARCHAR(32) NOT NULL,
  dept_id VARCHAR(32) NOT NULL,
  name    VARCHAR(32),
  PRIMARY KEY (emp_id)
);

/*==============================================================*/
/* Table: t_org                                                 */
/*==============================================================*/
CREATE TABLE t_org
(
  org_id VARCHAR(32) NOT NULL,
  name   VARCHAR(32),
  PRIMARY KEY (org_id)
);

/*==============================================================*/
/* Table: t_privilege                                           */
/*==============================================================*/
CREATE TABLE t_privilege
(
  pri_id VARCHAR(32) NOT NULL,
  name   VARCHAR(32),
  PRIMARY KEY (pri_id)
);

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
CREATE TABLE t_role
(
  role_id VARCHAR(32) NOT NULL,
  name    VARCHAR(32),
  PRIMARY KEY (role_id)
);

ALTER TABLE emp_role
  ADD CONSTRAINT FK_emp_role FOREIGN KEY (emp_id)
REFERENCES t_employee (emp_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE emp_role
  ADD CONSTRAINT FK_emp_role2 FOREIGN KEY (role_id)
REFERENCES t_role (role_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE lingdao
  ADD CONSTRAINT FK_Inheritance_1 FOREIGN KEY (emp_id)
REFERENCES t_employee (emp_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE role_privilege
  ADD CONSTRAINT FK_belong FOREIGN KEY (role_id)
REFERENCES t_role (role_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE role_privilege
  ADD CONSTRAINT FK_own FOREIGN KEY (pri_id)
REFERENCES t_privilege (pri_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE t_dept
  ADD CONSTRAINT FK_org_dept FOREIGN KEY (org_id)
REFERENCES t_org (org_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE t_employee
  ADD CONSTRAINT FK_dept_emp FOREIGN KEY (dept_id)
REFERENCES t_dept (dept_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

