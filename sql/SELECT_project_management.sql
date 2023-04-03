#change date format '19-08-17' gg-mm-yy
alter table tasks modify task_start_date varchar(30);
alter table tasks modify task_end_date varchar(30);
select task_start_date from tasks;
update tasks set task_start_date=date_format(task_start_date,"%d-%d-%Y");
select task_start_date from tasks;
#
select DATE_FORMAT(STR_TO_DATE(task_start_date, '%d.%m.%y'), '%Y-%m-%d') as date_formatted from tasks;
#elenco dei progetti in ordine decrescente di costo;
select project_id,sum(work_percentage * salary) as costo_progetto_totale from employees natural join project_teams group by project_id order by costo_progetto_totale desc;
#elenco dei dipendenti in ordine decrescente con più tasks
select employee_id,count(task_id) as count from employees natural join emp_tasks group by employee_id order by count desc;
#elenco dei task in ordine decrescente con più dipendenti
select task_id,count(employee_id) as employee from employees natural join emp_tasks group by task_id order by employee desc;
#elenco dei task in ordine decrescente più lunghi
select str_to_date(task_end_date,'%d.%m.%Y') as DateFormat from tasks;
select task_id,datediff(task_end_date,task_start_date) as differenza_tra_date from tasks order by differenza_tra_date desc;
#elenco dei progetti in ordine decrescente con più dipendenti
select project_name,count(*) as numero_dipendenti from projects natural join project_teams group by project_id order  by numero_dipendenti,project_name desc;
#calcolo dei numero di giorni per task
#calcolo del numero di giorni per progetto
#elenco dei dipendenti con ruolo di coordinatore
select distinct first_name,last_name,employee_id from emp_tasks natural join employees as A inner join  tasks on A.employee_id = tasks.coordinator_id;
select coordinator_id from tasks;
#Statistica dei progetti per coordinatore:
#num. progetti
select leader_id,count(*) from projects group by leader_id;
#costo progetti
select leader_id,sum(work_percentage * salary) as costo_progetto_totale from employees natural join project_teams as A inner join projects on A.employee_id= projects.leader_id group by leader_id;
#durata min
#durata media
#durata max
#LIMIT e OFFSET limita quelle che sono le tuple in uscita dall'API. Prestazioni, riservatezzza.
