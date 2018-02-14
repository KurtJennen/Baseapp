delete from batch_step_execution_context;
delete from batch_step_execution;
delete from batch_job_execution_context;
delete from batch_job_execution;
delete from batch_job_params;
delete from batch_job_instance;
update batch_step_execution_seq set id = 0;
update batch_job_seq set id = 0;
update batch_job_execution_seq set id = 0;