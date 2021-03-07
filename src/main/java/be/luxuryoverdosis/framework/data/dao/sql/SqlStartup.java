package be.luxuryoverdosis.framework.data.dao.sql;

public class SqlStartup {
	public final static String SQL_090709a = "CREATE TABLE base_sql ( " +
		"Id int(10) unsigned NOT NULL AUTO_INCREMENT, " +
		"Version int(10) unsigned NOT NULL DEFAULT '0', " +
		"UserAdd varchar(45) NOT NULL DEFAULT '', " +
		"UserUpdate varchar(45) NOT NULL DEFAULT '', " +
		"DateAdd date NOT NULL DEFAULT '0000-00-00', " +
		"DateUpdate date NOT NULL DEFAULT '0000-00-00', " +
		"Name varchar(45) NOT NULL DEFAULT '', " +
		"Content varchar(4000) NOT NULL DEFAULT '', " +
		"App varchar(45) NOT NULL DEFAULT '', " +
		"PRIMARY KEY (Id))";

	public final static String SQL_090710a = "CREATE TABLE base_role ( " +
		"Id int(10) unsigned NOT NULL AUTO_INCREMENT, " +
		"Version int(10) unsigned NOT NULL DEFAULT '0', " +
		"UserAdd varchar(45) NOT NULL DEFAULT '', " +
		"UserUpdate varchar(45) NOT NULL DEFAULT '', " +
		"DateAdd date NOT NULL DEFAULT '0000-00-00', " +
		"DateUpdate date NOT NULL DEFAULT '0000-00-00', " +
		"Name varchar(45) NOT NULL DEFAULT '', " +
		"PRIMARY KEY (Id))";
	
	public final static String SQL_090710b = "INSERT INTO base_role (Id, Version, UserAdd, UserUpdate, DateAdd, DateUpdate, Name) values " +
		 "(1, 1, 'root', 'root', '2010-10-19', '2010-10-19', 'BEHEERDER'), " +
		 "(2, 1, 'root', 'root', '2010-10-19', '2010-10-19', 'UITGEBREIDE_GEBRUIKER'), " +
		 "(3, 1, 'root', 'root', '2010-10-19', '2010-10-19', 'NORMALE_GEBRUIKER')";
	
	public final static String SQL_090711a = "CREATE TABLE base_user ( " +
		"Id int(10) unsigned NOT NULL AUTO_INCREMENT, " +
		"Version int(10) unsigned NOT NULL DEFAULT '0', " +
		"UserAdd varchar(45) NOT NULL DEFAULT '', " +
		"UserUpdate varchar(45) NOT NULL DEFAULT '', " +
		"DateAdd date NOT NULL DEFAULT '0000-00-00', " +
		"DateUpdate date NOT NULL DEFAULT '0000-00-00', " +
		"Name varchar(45) NOT NULL DEFAULT '', " +
		"UserName varchar(45) NOT NULL DEFAULT '', " +
		"Password varchar(45) NOT NULL DEFAULT '', " +
		"Email varchar(45) NOT NULL DEFAULT '', " +
		"Role_Id int(10) unsigned DEFAULT NULL, " +
		"PRIMARY KEY (Id), " +
		"KEY FK_USER_ROLE_ID (Role_Id), " +
		"CONSTRAINT FK_USER_ROLE_ID FOREIGN KEY (Role_Id) REFERENCES base_role (Id))";
	
	public final static String SQL_090711b = "INSERT INTO base_user (Id, Version, UserAdd, UserUpdate, DateAdd, DateUpdate, Name, UserName, Password, Email, Role_Id) VALUES " + 
		"(1, 1, 'root', 'root', '2010-10-19', '2010-10-19', 'root', 'Root', 'cm9vdA==', 'kurt.jennen@skynet.be', 1)";
	
	public final static String SQL_090904a = "CREATE TABLE base_job (" +
		"Id int(10) unsigned NOT NULL AUTO_INCREMENT, " +
		"Version int(10) unsigned NOT NULL DEFAULT '0', " +
		"UserAdd varchar(45) NOT NULL DEFAULT '', " +
		"UserUpdate varchar(45) NOT NULL DEFAULT '', " +
		"DateAdd date NOT NULL DEFAULT '0000-00-00', " +
		"DateUpdate date NOT NULL DEFAULT '0000-00-00', " +
		"Name varchar(45) NOT NULL DEFAULT '', " +
		"Filename varchar(256) DEFAULT '', " +
		"File longblob, " +
		"Filesize int(10) unsigned DEFAULT NULL, " +
		"Contenttype varchar(45) DEFAULT NULL, " +
		"Executed tinyint(1) NOT NULL DEFAULT '0', " +
		"Started datetime DEFAULT NULL, " +
		"Ended datetime DEFAULT NULL, " +
		"Status varchar(45) NOT NULL DEFAULT '', " +
		"PRIMARY KEY (Id))";
	
	public final static String SQL_090911a = "CREATE TABLE base_job_log ( " +
		"Id int(10) unsigned NOT NULL AUTO_INCREMENT, " +
		"Version int(10) unsigned NOT NULL DEFAULT '0', " +
		"UserAdd varchar(45) NOT NULL DEFAULT '', " +
		"UserUpdate varchar(45) NOT NULL DEFAULT '', " +
		"DateAdd date NOT NULL DEFAULT '0000-00-00', " +
		"DateUpdate date NOT NULL DEFAULT '0000-00-00', " +
		"Job_Id int(10) unsigned NOT NULL DEFAULT '0', "+
		"Input varchar(256) NOT NULL DEFAULT '', " +
		"Output varchar(256) NOT NULL DEFAULT '', " +
		"PRIMARY KEY (Id), " +
		"KEY FK_JOB_LOG_JOB_ID (Job_Id), " +
		"CONSTRAINT FK_JOB_LOG_JOB_ID FOREIGN KEY (Job_Id) REFERENCES base_job (Id))";
	
	public final static String SQL_090916a = "CREATE TABLE base_job_param ( " +
		"Id int(10) unsigned NOT NULL AUTO_INCREMENT, " +
		"Version int(10) unsigned NOT NULL DEFAULT '0', " +
		"UserAdd varchar(45) NOT NULL DEFAULT '', " +
		"UserUpdate varchar(45) NOT NULL DEFAULT '', " +
		"DateAdd date NOT NULL DEFAULT '0000-00-00', " +
		"DateUpdate date NOT NULL DEFAULT '0000-00-00', " +
		"Job_Id int(10) unsigned NOT NULL DEFAULT '0', " +
		"Name varchar(45) NOT NULL DEFAULT '', " +
		"Value varchar(45) NOT NULL DEFAULT '', " +
		"PRIMARY KEY (Id), " +
		"KEY FK_JOB_PARAM_JOB_ID (Job_Id), " +
		"CONSTRAINT FK_JOB_PARAM_JOB_ID FOREIGN KEY (Job_Id) REFERENCES base_job (Id))";
	
	public final static String SQL_091007a = "CREATE TABLE base_query ( " +
		"Id int(10) unsigned NOT NULL AUTO_INCREMENT, " +
		"Version int(10) unsigned NOT NULL DEFAULT '0', " +
		"UserAdd varchar(45) NOT NULL DEFAULT '', " +
		"UserUpdate varchar(45) NOT NULL DEFAULT '', " +
		"DateAdd date NOT NULL DEFAULT '0000-00-00', " +
		"DateUpdate date NOT NULL DEFAULT '0000-00-00', " +
		"Name varchar(45) NOT NULL DEFAULT '', " +
		"Type varchar(45) NOT NULL DEFAULT '', " +
		"User_Id int(10) unsigned DEFAULT NULL, " +
		"Complex char(1) DEFAULT NULL, " +
		"PRIMARY KEY (Id), " +
		"KEY FK_QUERY_USER_ID (User_Id), " +
		"CONSTRAINT FK_QUERY_USER_ID FOREIGN KEY (User_Id) REFERENCES base_user (Id))";
	
	public final static String SQL_091007b = "CREATE TABLE base_query_param ( " +
		"Id int(10) unsigned NOT NULL AUTO_INCREMENT, " +
		"Version int(10) unsigned NOT NULL DEFAULT '0', " +
		"UserAdd varchar(45) NOT NULL DEFAULT '', " +
		"UserUpdate varchar(45) NOT NULL DEFAULT '', " +
		"DateAdd date NOT NULL DEFAULT '0000-00-00', " +
		"DateUpdate date NOT NULL DEFAULT '0000-00-00', " +
		"Parameter varchar(45) NOT NULL DEFAULT '', " +
		"Operator varchar(45) NOT NULL DEFAULT '', " +
		"Value varchar(45) NOT NULL DEFAULT '', " +
		"Query_Id int(10) unsigned DEFAULT NULL, " +
		"AddAndOr varchar(45) DEFAULT '', " +
		"OpenBracket char(2) DEFAULT '', " +
		"CloseBracket char(2) DEFAULT '', " +
		"PRIMARY KEY (Id), " +
		"KEY FK_QUERY_PARAM_QUERY_ID (Query_Id), " +
		"CONSTRAINT FK_QUERY_PARAM_QUERY_ID FOREIGN KEY (Query_Id) REFERENCES base_query (Id))";
	
	public final static String SQL_100317a = "ALTER TABLE base_user ADD ( " +
		"DateExp date NOT NULL DEFAULT '2100-01-01')";
	
	public final static String SQL_120117a = "CREATE TABLE base_menu ( " +
		"Id int(10) unsigned NOT NULL AUTO_INCREMENT, " +
		"Version int(10) unsigned NOT NULL DEFAULT '0', " +
		"UserAdd varchar(45) NOT NULL DEFAULT '', " +
		"UserUpdate varchar(45) NOT NULL DEFAULT '', " +
		"DateAdd date NOT NULL DEFAULT '0000-00-00', " +
		"DateUpdate date NOT NULL DEFAULT '0000-00-00', " +
		"FullName varchar(256) NOT NULL DEFAULT '', " +
		"Name varchar(256) NOT NULL DEFAULT '', " +
		"Title varchar(256) NOT NULL DEFAULT '', " +
		"FullLevel varchar(256) NOT NULL DEFAULT '', " +
		"Level int(1) NOT NULL DEFAULT '0', " +
		"Hidden char(1) NOT NULL DEFAULT '', " +
		"User_Id int(10) unsigned DEFAULT NULL, " +
		"PRIMARY KEY (Id), " +
		"KEY FK_MENU_USER_ID (User_Id), " +
		"CONSTRAINT FK_MENU_USER_ID FOREIGN KEY (User_Id) REFERENCES base_user (Id))";
	
	public final static String SQL_120131a = "CREATE TABLE base_number ( " +
		"Id int(10) unsigned NOT NULL AUTO_INCREMENT, " +
		"Version int(10) unsigned NOT NULL DEFAULT '0', " +
		"UserAdd varchar(45) NOT NULL DEFAULT '', " +
		"UserUpdate varchar(45) NOT NULL DEFAULT '', " +
		"DateAdd date NOT NULL DEFAULT '0000-00-00', " +
		"DateUpdate date NOT NULL DEFAULT '0000-00-00', " +
		"AppCode varchar(4) NOT NULL DEFAULT '', " +
		"Year varchar(4) NOT NULL DEFAULT '', " +
		"Number int(10) unsigned NOT NULL DEFAULT '0', " +
		"Type varchar(2) NOT NULL DEFAULT '', " +
		"PRIMARY KEY (Id))";
	
	public final static String SQL_120228a = "ALTER TABLE base_menu ADD ( " +
		"Disabled char(1) NOT NULL DEFAULT '', " +
		"ForPay char(1) NOT NULL DEFAULT '', " +
		"Payed char(1) NOT NULL DEFAULT '')";
	
	public final static String SQL_120625b = "CREATE TABLE base_document ( " +
		"Id int(10) unsigned NOT NULL AUTO_INCREMENT, " +
		"Version int(10) unsigned NOT NULL DEFAULT '0', " +
		"UserAdd varchar(45) NOT NULL DEFAULT '', " +
		"UserUpdate varchar(45) NOT NULL DEFAULT '', " +
		"DateAdd date NOT NULL DEFAULT '0000-00-00', " +
		"DateUpdate date NOT NULL DEFAULT '0000-00-00', " +
		"Type varchar(45) NOT NULL DEFAULT '', " +
		"Filename varchar(256) DEFAULT '', " +
		"File longblob, " +
		"Filesize int(10) unsigned DEFAULT NULL, " +
		"Contenttype varchar(45) DEFAULT NULL, " +
		"PRIMARY KEY (Id))";
	
	//Spring Batch Start
	public final static String SQL_130311a = "CREATE TABLE batch_job_instance ( " +
		"Job_Instance_Id bigint(20) NOT NULL, " +
		"Version bigint(20) DEFAULT NULL, " +
		"Job_Name varchar(100) NOT NULL, " +
		"Job_Key varchar(32) NOT NULL, " +
		"PRIMARY KEY (Job_Instance_Id), " +
		"UNIQUE KEY JOB_INST_UN(Job_Name, Job_Key))";
	
	public final static String SQL_130311b = "CREATE TABLE batch_job_params ( " +
		  "Job_Instance_Id bigint(20) NOT NULL, " +
		  "Type_Cd varchar(6) NOT NULL, " +
		  "Key_name varchar(100) NOT NULL, " +
		  "String_Val varchar(250) DEFAULT NULL, " +
		  "Date_Val datetime DEFAULT NULL, " +
		  "Long_Val bigint(20) DEFAULT NULL, " +
		  "Double_Val double DEFAULT NULL, " +
		  "KEY JOB_INST_PARAMS_FK (Job_Instance_Id), " +
		  "CONSTRAINT `JOB_INST_PARAMS_FK` FOREIGN KEY (Job_Instance_Id) REFERENCES batch_job_instance (Job_Instance_Id)) ";
	
	public final static String SQL_130311c = "CREATE TABLE batch_job_seq ( " +
		"Id bigint(20) NOT NULL)";
	
	public final static String SQL_130311d = "CREATE TABLE batch_job_execution ( " +
		"Job_Execution_Id bigint(20) NOT NULL, " +
		"Version bigint(20) DEFAULT NULL, " +
		"Job_Instance_Id bigint(20) NOT NULL, " +
		"Create_Time datetime NOT NULL, " +
		"Start_Time datetime DEFAULT NULL, " +
		"End_Time datetime DEFAULT NULL, " +
		"Status varchar(10) DEFAULT NULL, " +
		"Exit_Code varchar(100) DEFAULT NULL, " +
		"Exit_Message varchar(2500) DEFAULT NULL, " +
		"Last_Updated datetime DEFAULT NULL, " +
		"PRIMARY KEY (Job_Execution_Id), " +
		"KEY JOB_INST_EXEC_FK (Job_Instance_Id), " +
		"CONSTRAINT JOB_INST_EXEC_FK FOREIGN KEY (Job_Instance_Id) REFERENCES batch_job_instance (Job_Instance_Id))";
	
	public final static String SQL_130311e = "CREATE TABLE batch_job_execution_context ( " +
		"Job_Execution_Id bigint(20) NOT NULL, " +
		"Short_Context varchar(2500) NOT NULL, " +
		"Serialized_Context text, " +
		"PRIMARY KEY (Job_Execution_Id), " +
		"CONSTRAINT JOB_EXEC_CTX_FK FOREIGN KEY (Job_Execution_Id) REFERENCES batch_job_execution (Job_Execution_Id))";
	
	public final static String SQL_130311f = "CREATE TABLE batch_job_execution_seq ( " +
		"Id bigint(20) NOT NULL)";
	
	public final static String SQL_130311g = "CREATE TABLE batch_step_execution ( " +
		"Step_Execution_Id bigint(20) NOT NULL, " +
		"Version bigint(20) NOT NULL, " +
		"Step_Name varchar(100) NOT NULL, " +
		"Job_Execution_Id bigint(20) NOT NULL, " +
		"Start_Time datetime NOT NULL, " +
		"End_Time datetime DEFAULT NULL, " +
		"Status varchar(10) DEFAULT NULL, " +
		"Commit_Count bigint(20) DEFAULT NULL, " +
		"Read_Count bigint(20) DEFAULT NULL, " +
		"Filter_Count bigint(20) DEFAULT NULL, " +
		"Write_Count bigint(20) DEFAULT NULL, " +
		"Read_Skip_Count bigint(20) DEFAULT NULL, " +
		"Write_Skip_Count bigint(20) DEFAULT NULL, " +
		"Process_Skip_Count bigint(20) DEFAULT NULL, " +
		"Rollback_Count bigint(20) DEFAULT NULL, " +
		"Exit_Code varchar(100) DEFAULT NULL, " +
		"Exit_Message varchar(2500) DEFAULT NULL, " +
		"Last_Updated datetime DEFAULT NULL, " +
		"PRIMARY KEY (Step_Execution_Id), " +
		"KEY JOB_EXEC_STEP_FK (Job_Execution_Id), " +
		"CONSTRAINT JOB_EXEC_STEP_FK FOREIGN KEY (Job_Execution_Id) REFERENCES batch_job_execution (Job_Execution_Id))";
	
	public final static String SQL_130311h = "CREATE TABLE batch_step_execution_context ( " +
		"Step_Execution_Id bigint(20) NOT NULL, " +
		"Short_Context varchar(2500) NOT NULL, " +
		"Serialized_Context text, " +
		"PRIMARY KEY (Step_Execution_Id), " +
		"CONSTRAINT STEP_EXEC_CTX_FK FOREIGN KEY (Step_Execution_Id) REFERENCES batch_step_execution (Step_Execution_Id))";
	
	public final static String SQL_130311i = "CREATE TABLE batch_step_execution_seq ( " +
		"Id bigint(20) NOT NULL)";
	
	public final static String SQL_190710a = "INSERT INTO batch_job_seq (Id) values " +
		"(0)";
	
	public final static String SQL_190710b = "INSERT INTO batch_job_execution_seq (Id) values " +
		"(0)";
	
	public final static String SQL_190710c = "INSERT INTO batch_step_execution_seq (Id) values " +
		"(0)";
	//Spring Batch End
	
	public final static String SQL_140531a = "ALTER TABLE base_job_log ADD (" +
			"File longblob)";
	
	//Spring Batch Upgrade Begin
	public final static String SQL_181019a = "ALTER TABLE batch_job_execution MODIFY COLUMN " +
			"Exit_Code varchar(2500) DEFAULT NULL";
	
	public final static String SQL_181019b = "ALTER TABLE batch_job_execution ADD ( " +
			"Job_Configuration_Location VARCHAR(2500) NULL)";
			
	
	public final static String SQL_181019c = "ALTER TABLE batch_step_execution MODIFY COLUMN " +
			"Exit_Code varchar(2500) DEFAULT NULL";
	
	public final static String SQL_181019d = "CREATE TABLE batch_job_execution_params ( " +
			  "Job_Execution_Id bigint(20) NOT NULL, " +
			  "Type_Cd varchar(6) NOT NULL, " +
			  "Key_name varchar(100) NOT NULL, " +
			  "String_Val varchar(250) DEFAULT NULL, " +
			  "Date_Val datetime DEFAULT NULL, " +
			  "Long_Val bigint(20) DEFAULT NULL, " +
			  "Double_Val double DEFAULT NULL, " +
			  "Identifying char(1) NOT NULL, " +
			  "KEY JOB_EXEC_PARAMS_FK (Job_Execution_Id), " +
			  "CONSTRAINT JOB_EXEC_PARAMS_FK FOREIGN KEY (Job_Execution_Id) REFERENCES batch_job_execution (Job_Execution_Id))";
	
	public final static String SQL_181019e = "ALTER TABLE batch_step_execution_seq ADD ( " +
			"Unique_Key char(1) NOT NULL, " +
			"UNIQUE KEY UNIQUE_KEY_UN(Unique_Key))";
	
	public final static String SQL_181019f = "ALTER TABLE batch_job_execution_seq ADD ( " +
			"Unique_Key char(1) NOT NULL, " +
			"UNIQUE KEY UNIQUE_KEY_UN(Unique_Key))";
	
	public final static String SQL_181019g = "ALTER TABLE batch_job_seq ADD ( " +
			"Unique_Key char(1) NOT NULL, " +
			"UNIQUE KEY UNIQUE_KEY_UN(Unique_Key))";
	
	public final static String SQL_181019h = "INSERT INTO BATCH_STEP_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_STEP_EXECUTION_SEQ)";
	
	public final static String SQL_181019i = "INSERT INTO BATCH_JOB_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_EXECUTION_SEQ)";
	
	public final static String SQL_181019j = "INSERT INTO BATCH_JOB_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_SEQ)";
	//Spring Batch Upgrade End
	
	public final static String SQL_210307a = "DELETE FROM base_query_param";
	
	public final static String SQL_210307b = "DELETE FROM base_query";
	
	public final static String SQL_210307c = "ALTER TABLE base_query_param CHANGE Parameter Name VARCHAR(45) NOT NULL DEFAULT ''";
	
}