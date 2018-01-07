
select * from users;
select * from installation;
select * from vendor;
select * from permissions;
select * from users_to_permissions;
select * from ALARM_FILE;
select * from ERROR_LOG;
select * from ALARM_DATA;

select count(*) from ALARM_FILE;
select count(*) from ERROR_LOG;
select count(*) from ALARM_DATA;

select distinct count( ERROR_MESSAGE) from ERROR_LOG;
select f.load_date, f.FILE_NAME,e.ERROR_MESSAGE from ERROR_LOG e, ALARM_FILE f WHERE e.FILE_ID = f.FILE_ID;

select * from ALARM_FILE where DATA_ERROR>0;
select * from ALARM_FILE where DATA_SKIPPED>0;

select * from ALARM_DATA where DESCRIPTION like 'DDS%' and INSTALLATION_ID = 101 and VENDOR_ID = 0 and ALM_TIME > '2016-09-01';
select * from ALARM_DATA where DESCRIPTION like 'DDS%' and INSTALLATION_ID = 102 and VENDOR_ID = 0 and ALM_TIME > '2016-09-01';
  
select distinct error_message from error_log;

select f.LOAD_DATE, f.FILE_NAME,e.ERROR_MESSAGE,e.linenum from ERROR_LOG e, ALARM_FILE f WHERE e.FILE_ID = f.FILE_ID AND f.INSTALLATION_ID = ? and f.VENDOR_ID = ? ORDER BY f.LOAD_DATE;


-- update ALARM_DATA set SYSTEM = 'ACS' WHERE VENDOR_ID=0 AND SYSTEM = 'ACS2';
-- update ALARM_DATA set SYSTEM = 'MP' WHERE VENDOR_ID=0 AND SYSTEM like 'MP%';
-- update ALARM_DATA set SYSTEM = 'SDI' WHERE VENDOR_ID=0 AND SYSTEM like 'SDI2';




