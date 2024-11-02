package be.luxuryoverdosis.framework.data.dao.sql;

public final class SqlStartup {
	private SqlStartup() {
	}
	
	public static final String SQL_090709A = "CREATE TABLE base_sql ( "
		+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
		+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
		+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
		+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
		+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
		+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
		+ "Name varchar(45) NOT NULL DEFAULT '', "
		+ "Content varchar(4000) NOT NULL DEFAULT '', "
		+ "App varchar(45) NOT NULL DEFAULT '', "
		+ "PRIMARY KEY (Id))";

	public static final String SQL_090710A = "CREATE TABLE base_role ( "
		+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
		+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
		+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
		+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
		+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
		+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
		+ "Name varchar(45) NOT NULL DEFAULT '', "
		+ "PRIMARY KEY (Id))";
	
	public static final String SQL_090710B = "INSERT INTO base_role (Id, Version, UserAdd, UserUpdate, DateAdd, DateUpdate, Name) values "
		+ "(1, 1, 'root', 'root', now(), now(), 'BEHEERDER'), "
		+ "(2, 1, 'root', 'root', now(), now(), 'UITGEBREIDE_GEBRUIKER'), "
		+ "(3, 1, 'root', 'root', now(), now(), 'NORMALE_GEBRUIKER')";
	
	public static final String SQL_090711A = "CREATE TABLE base_user ( "
		+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
		+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
		+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
		+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
		+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
		+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
		+ "Name varchar(45) NOT NULL DEFAULT '', "
		+ "UserName varchar(45) NOT NULL DEFAULT '', "
		+ "Password varchar(45) NOT NULL DEFAULT '', "
		+ "Email varchar(45) NOT NULL DEFAULT '', "
		+ "Role_Id int(10) unsigned DEFAULT NULL, "
		+ "PRIMARY KEY (Id), "
		+ "KEY FK_USER_ROLE_ID (Role_Id), "
		+ "CONSTRAINT FK_USER_ROLE_ID FOREIGN KEY (Role_Id) REFERENCES base_role (Id))";
	
	public static final String SQL_090711B = "INSERT INTO base_user (Id, Version, UserAdd, UserUpdate, DateAdd, DateUpdate, Name, UserName, Password, Email, Role_Id) VALUES " 
		+ "(1, 1, 'root', 'root', now(), now(), 'root', 'Root', 'cm9vdA==', 'kurt.jennen@skynet.be', 1)";
	
	public static final String SQL_090904A = "CREATE TABLE base_job ("
		+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
		+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
		+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
		+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
		+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
		+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
		+ "Name varchar(60) NOT NULL DEFAULT '', "
		+ "Filename varchar(256) DEFAULT '', "
		+ "File longblob, "
		+ "Filesize int(10) unsigned DEFAULT NULL, "
		+ "Contenttype varchar(45) DEFAULT NULL, "
		+ "Executed tinyint(1) NOT NULL DEFAULT '0', "
		+ "Started datetime DEFAULT NULL, "
		+ "Ended datetime DEFAULT NULL, "
		+ "Status varchar(45) NOT NULL DEFAULT '', "
		+ "PRIMARY KEY (Id))";
	
	public static final String SQL_090911A = "CREATE TABLE base_job_log ( "
		+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
		+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
		+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
		+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
		+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
		+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
		+ "Job_Id int(10) unsigned NOT NULL DEFAULT '0', "
		+ "Input varchar(256) NOT NULL DEFAULT '', "
		+ "Output varchar(256) NOT NULL DEFAULT '', "
		+ "PRIMARY KEY (Id), "
		+ "KEY FK_JOB_LOG_JOB_ID (Job_Id), "
		+ "CONSTRAINT FK_JOB_LOG_JOB_ID FOREIGN KEY (Job_Id) REFERENCES base_job (Id))";
	
	public static final String SQL_090916A = "CREATE TABLE base_job_param ( "
		+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
		+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
		+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
		+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
		+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
		+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
		+ "Job_Id int(10) unsigned NOT NULL DEFAULT '0', "
		+ "Name varchar(45) NOT NULL DEFAULT '', "
		+ "Value varchar(45) NOT NULL DEFAULT '', "
		+ "PRIMARY KEY (Id), "
		+ "KEY FK_JOB_PARAM_JOB_ID (Job_Id), "
		+ "CONSTRAINT FK_JOB_PARAM_JOB_ID FOREIGN KEY (Job_Id) REFERENCES base_job (Id))";
	
	public static final String SQL_091007A = "CREATE TABLE base_query ( "
		+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
		+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
		+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
		+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
		+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
		+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
		+ "Name varchar(45) NOT NULL DEFAULT '', "
		+ "Type varchar(45) NOT NULL DEFAULT '', "
		+ "User_Id int(10) unsigned DEFAULT NULL, "
		+ "Complex char(1) DEFAULT NULL, "
		+ "PRIMARY KEY (Id), "
		+ "KEY FK_QUERY_USER_ID (User_Id), "
		+ "CONSTRAINT FK_QUERY_USER_ID FOREIGN KEY (User_Id) REFERENCES base_user (Id))";
	
	public static final String SQL_091007B = "CREATE TABLE base_query_param ( "
		+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
		+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
		+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
		+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
		+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
		+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
		+ "Parameter varchar(45) NOT NULL DEFAULT '', "
		+ "Operator varchar(45) NOT NULL DEFAULT '', "
		+ "Value varchar(45) NOT NULL DEFAULT '', "
		+ "Query_Id int(10) unsigned DEFAULT NULL, "
		+ "AddAndOr varchar(45) DEFAULT '', "
		+ "OpenBracket char(2) DEFAULT '', "
		+ "CloseBracket char(2) DEFAULT '', "
		+ "PRIMARY KEY (Id), "
		+ "KEY FK_QUERY_PARAM_QUERY_ID (Query_Id), "
		+ "CONSTRAINT FK_QUERY_PARAM_QUERY_ID FOREIGN KEY (Query_Id) REFERENCES base_query (Id))";
	
	public static final String SQL_100317A = "ALTER TABLE base_user ADD ( "
		+ "DateExp date NOT NULL DEFAULT '2100-01-01')";
	
	public static final String SQL_120117A = "CREATE TABLE base_menu ( "
		+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
		+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
		+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
		+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
		+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
		+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
		+ "FullName varchar(256) NOT NULL DEFAULT '', "
		+ "Name varchar(256) NOT NULL DEFAULT '', "
		+ "Title varchar(256) NOT NULL DEFAULT '', "
		+ "FullLevel varchar(256) NOT NULL DEFAULT '', "
		+ "Level int(1) NOT NULL DEFAULT '0', "
		+ "Hidden char(1) NOT NULL DEFAULT '', "
		+ "User_Id int(10) unsigned DEFAULT NULL, "
		+ "PRIMARY KEY (Id), "
		+ "KEY FK_MENU_USER_ID (User_Id), "
		+ "CONSTRAINT FK_MENU_USER_ID FOREIGN KEY (User_Id) REFERENCES base_user (Id))";
	
	public static final String SQL_120131A = "CREATE TABLE base_number ( "
		+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
		+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
		+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
		+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
		+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
		+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
		+ "AppCode varchar(4) NOT NULL DEFAULT '', "
		+ "Year varchar(4) NOT NULL DEFAULT '', "
		+ "Number int(10) unsigned NOT NULL DEFAULT '0', "
		+ "Type varchar(2) NOT NULL DEFAULT '', "
		+ "PRIMARY KEY (Id))";
	
	public static final String SQL_120228A = "ALTER TABLE base_menu ADD ( "
		+ "Disabled char(1) NOT NULL DEFAULT '', "
		+ "ForPay char(1) NOT NULL DEFAULT '', "
		+ "Payed char(1) NOT NULL DEFAULT '')";
	
	public static final String SQL_120625B = "CREATE TABLE base_document ( "
		+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
		+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
		+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
		+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
		+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
		+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
		+ "Type varchar(45) NOT NULL DEFAULT '', "
		+ "Filename varchar(256) DEFAULT '', "
		+ "File longblob, "
		+ "Filesize int(10) unsigned DEFAULT NULL, "
		+ "Contenttype varchar(45) DEFAULT NULL, "
		+ "PRIMARY KEY (Id))";
	
	//Spring Batch Start
	public static final String SQL_130311A = "CREATE TABLE batch_job_instance ( "
		+ "Job_Instance_Id bigint(20) NOT NULL, "
		+ "Version bigint(20) DEFAULT NULL, "
		+ "Job_Name varchar(100) NOT NULL, "
		+ "Job_Key varchar(32) NOT NULL, "
		+ "PRIMARY KEY (Job_Instance_Id), "
		+ "UNIQUE KEY JOB_INST_UN(Job_Name, Job_Key))";
	
	public static final String SQL_130311B = "CREATE TABLE batch_job_params ( "
		+ "Job_Instance_Id bigint(20) NOT NULL, "
		+ "Type_Cd varchar(6) NOT NULL, "
		+ "Key_name varchar(100) NOT NULL, "
		+  "String_Val varchar(250) DEFAULT NULL, "
		+ "Date_Val datetime DEFAULT NULL, "
		+ "Long_Val bigint(20) DEFAULT NULL, "
		+ "Double_Val double DEFAULT NULL, "
		+ "KEY JOB_INST_PARAMS_FK (Job_Instance_Id), "
		+ "CONSTRAINT `JOB_INST_PARAMS_FK` FOREIGN KEY (Job_Instance_Id) REFERENCES batch_job_instance (Job_Instance_Id)) ";
	
	public static final String SQL_130311C = "CREATE TABLE batch_job_seq ( "
		+ "Id bigint(20) NOT NULL)";
	
	public static final String SQL_130311D = "CREATE TABLE batch_job_execution ( "
		+ "Job_Execution_Id bigint(20) NOT NULL, "
		+ "Version bigint(20) DEFAULT NULL, "
		+ "Job_Instance_Id bigint(20) NOT NULL, "
		+ "Create_Time datetime NOT NULL, "
		+ "Start_Time datetime DEFAULT NULL, "
		+ "End_Time datetime DEFAULT NULL, "
		+ "Status varchar(10) DEFAULT NULL, "
		+ "Exit_Code varchar(100) DEFAULT NULL, "
		+ "Exit_Message varchar(2500) DEFAULT NULL, "
		+ "Last_Updated datetime DEFAULT NULL, "
		+ "PRIMARY KEY (Job_Execution_Id), "
		+ "KEY JOB_INST_EXEC_FK (Job_Instance_Id), "
		+ "CONSTRAINT JOB_INST_EXEC_FK FOREIGN KEY (Job_Instance_Id) REFERENCES batch_job_instance (Job_Instance_Id))";
	
	public static final String SQL_130311E = "CREATE TABLE batch_job_execution_context ( "
		+ "Job_Execution_Id bigint(20) NOT NULL, "
		+ "Short_Context varchar(2500) NOT NULL, "
		+ "Serialized_Context text, "
		+ "PRIMARY KEY (Job_Execution_Id), "
		+ "CONSTRAINT JOB_EXEC_CTX_FK FOREIGN KEY (Job_Execution_Id) REFERENCES batch_job_execution (Job_Execution_Id))";
	
	public static final String SQL_130311F = "CREATE TABLE batch_job_execution_seq ( "
		+ "Id bigint(20) NOT NULL)";
	
	public static final String SQL_130311G = "CREATE TABLE batch_step_execution ( "
		+ "Step_Execution_Id bigint(20) NOT NULL, "
		+ "Version bigint(20) NOT NULL, "
		+ "Step_Name varchar(100) NOT NULL, "
		+ "Job_Execution_Id bigint(20) NOT NULL, "
		+ "Start_Time datetime NOT NULL, "
		+ "End_Time datetime DEFAULT NULL, "
		+ "Status varchar(10) DEFAULT NULL, "
		+ "Commit_Count bigint(20) DEFAULT NULL, "
		+ "Read_Count bigint(20) DEFAULT NULL, "
		+ "Filter_Count bigint(20) DEFAULT NULL, "
		+ "Write_Count bigint(20) DEFAULT NULL, "
		+ "Read_Skip_Count bigint(20) DEFAULT NULL, "
		+ "Write_Skip_Count bigint(20) DEFAULT NULL, "
		+ "Process_Skip_Count bigint(20) DEFAULT NULL, "
		+ "Rollback_Count bigint(20) DEFAULT NULL, "
		+ "Exit_Code varchar(100) DEFAULT NULL, "
		+ "Exit_Message varchar(2500) DEFAULT NULL, "
		+ "Last_Updated datetime DEFAULT NULL, "
		+ "PRIMARY KEY (Step_Execution_Id), "
		+ "KEY JOB_EXEC_STEP_FK (Job_Execution_Id), "
		+ "CONSTRAINT JOB_EXEC_STEP_FK FOREIGN KEY (Job_Execution_Id) REFERENCES batch_job_execution (Job_Execution_Id))";
	
	public static final String SQL_130311H = "CREATE TABLE batch_step_execution_context ( "
		+ "Step_Execution_Id bigint(20) NOT NULL, "
		+ "Short_Context varchar(2500) NOT NULL, "
		+ "Serialized_Context text, "
		+ "PRIMARY KEY (Step_Execution_Id), "
		+ "CONSTRAINT STEP_EXEC_CTX_FK FOREIGN KEY (Step_Execution_Id) REFERENCES batch_step_execution (Step_Execution_Id))";
	
	public static final String SQL_130311I = "CREATE TABLE batch_step_execution_seq ( "
		+ "Id bigint(20) NOT NULL)";
	
	public static final String SQL_190710A = "INSERT INTO batch_job_seq (Id) values "
		+ "(0)";
	
	public static final String SQL_190710B = "INSERT INTO batch_job_execution_seq (Id) values "
		+ "(0)";
	
	public static final String SQL_190710C = "INSERT INTO batch_step_execution_seq (Id) values "
		+ "(0)";
	//Spring Batch End
	
	public static final String SQL_140531A = "ALTER TABLE base_job_log ADD ("
			+ "File longblob)";
	
	//Spring Batch Upgrade Begin
	public static final String SQL_181019A = "ALTER TABLE batch_job_execution MODIFY COLUMN "
			+ "Exit_Code varchar(2500) DEFAULT NULL";
	
	public static final String SQL_181019B = "ALTER TABLE batch_job_execution ADD ( "
			+ "Job_Configuration_Location VARCHAR(2500) NULL)";
			
	
	public static final String SQL_181019C = "ALTER TABLE batch_step_execution MODIFY COLUMN "
			+ "Exit_Code varchar(2500) DEFAULT NULL";
	
	public static final String SQL_181019D = "CREATE TABLE batch_job_execution_params ( "
			+ "Job_Execution_Id bigint(20) NOT NULL, "
			+ "Type_Cd varchar(6) NOT NULL, "
			+ "Key_name varchar(100) NOT NULL, "
			+ "String_Val varchar(250) DEFAULT NULL, "
			+ "Date_Val datetime DEFAULT NULL, "
			+ "Long_Val bigint(20) DEFAULT NULL, "
			+ "Double_Val double DEFAULT NULL, "
			+ "Identifying char(1) NOT NULL, "
			+ "KEY JOB_EXEC_PARAMS_FK (Job_Execution_Id), "
			+ "CONSTRAINT JOB_EXEC_PARAMS_FK FOREIGN KEY (Job_Execution_Id) REFERENCES batch_job_execution (Job_Execution_Id))";
	
	public static final String SQL_181019E = "ALTER TABLE batch_step_execution_seq ADD ( "
			+ "Unique_Key char(1) NOT NULL, "
			+ "UNIQUE KEY UNIQUE_KEY_UN(Unique_Key))";
	
	public static final String SQL_181019F = "ALTER TABLE batch_job_execution_seq ADD ( "
			+ "Unique_Key char(1) NOT NULL, "
			+ "UNIQUE KEY UNIQUE_KEY_UN(Unique_Key))";
	
	public static final String SQL_181019G = "ALTER TABLE batch_job_seq ADD ( "
			+ "Unique_Key char(1) NOT NULL, "
			+ "UNIQUE KEY UNIQUE_KEY_UN(Unique_Key))";
	
	public static final String SQL_181019H = "INSERT INTO BATCH_STEP_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_STEP_EXECUTION_SEQ)";
	
	public static final String SQL_181019I = "INSERT INTO BATCH_JOB_EXECUTION_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_EXECUTION_SEQ)";
	
	public static final String SQL_181019J = "INSERT INTO BATCH_JOB_SEQ (ID, UNIQUE_KEY) select * from (select 0 as ID, '0' as UNIQUE_KEY) as tmp where not exists(select * from BATCH_JOB_SEQ)";
	//Spring Batch Upgrade End
	
	public static final String SQL_210307A = "DELETE FROM base_query_param";
	
	public static final String SQL_210307B = "DELETE FROM base_query";
	
	public static final String SQL_210307C = "ALTER TABLE base_query_param CHANGE Parameter Name VARCHAR(45) NOT NULL DEFAULT ''";
	
	public static final String SQL_210503A = "CREATE TABLE base_user_role ( "
			+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
			+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
			+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
			+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
			+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
			+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
			+ "User_Id int(10) unsigned NOT NULL DEFAULT '0', "
			+ "Role_Id int(10) unsigned NOT NULL DEFAULT '0', "
			+ "PRIMARY KEY (Id), "
			+ "KEY FK_USER_ROLE_USER_ID (User_Id), "
			+ "CONSTRAINT FK_USER_ROLE_USER_ID FOREIGN KEY (User_Id) REFERENCES base_user (Id), "
			+ "KEY FK_USER_ROLE_ROLE_ID (Role_Id), "
			+ "CONSTRAINT FK_USER_ROLE_ROLE_ID FOREIGN KEY (Role_Id) REFERENCES base_role (Id))";
	
	public static final String SQL_210504A = "INSERT INTO base_user_role (Id, Version, UserAdd, UserUpdate, DateAdd, DateUpdate, User_Id, Role_Id) VALUES " 
			+ "(1, 1, 'root', 'root', now(), now(), 1, 1)";
	
	public static final String SQL_210525A = "UPDATE base_role SET name = 'Beheerder' where name = 'BEHEERDER'";
	public static final String SQL_210525B = "UPDATE base_role SET name = 'UitgebreideGebruiker' where name = 'UITGEBREIDE_GEBRUIKER'";
	public static final String SQL_210525C = "UPDATE base_role SET name = 'NormaleGebruiker' where name = 'NORMALE_GEBRUIKER'";
	
	public static final String SQL_221117A = "CREATE TABLE batch_job_hdr_selected ( "
			+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
			+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
			+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
			+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
			+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
			+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
			+ "Job_Name varchar(100) NOT NULL, "
			+ "PRIMARY KEY (Id))";
	
	public static final String SQL_221117B = "CREATE TABLE batch_job_dtl_selected ( "
			+ "Id int(10) unsigned NOT NULL AUTO_INCREMENT, "
			+ "Version int(10) unsigned NOT NULL DEFAULT '0', "
			+ "UserAdd varchar(45) NOT NULL DEFAULT '', "
			+ "UserUpdate varchar(45) NOT NULL DEFAULT '', "
			+ "DateAdd datetime NOT NULL DEFAULT '0000-00-00', "
			+ "DateUpdate datetime NOT NULL DEFAULT '0000-00-00', "
			+ "Batch_Job_Hdr_Selected_Id int(10) unsigned NOT NULL DEFAULT '0', "
			+ "Selected_Id int(10) unsigned NOT NULL DEFAULT '0', "
			+ "PRIMARY KEY (Id), "
			+ "CONSTRAINT JOB_HDR_SELECTED_FK FOREIGN KEY (Batch_Job_Hdr_Selected_Id) REFERENCES batch_job_hdr_selected (Id))";
	
	public static final String SQL_230228A = "ALTER TABLE base_document MODIFY COLUMN "
			+ "Contenttype varchar(256) DEFAULT NULL";
	
	public static final String SQL_230320A = "ALTER TABLE base_number MODIFY COLUMN "
			+ "Type varchar(4) NOT NULL DEFAULT ''";
	
}
