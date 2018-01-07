


drop table if exists TEMP_TAGS;
create table TEMP_TAGS
(
    CLIENT_ID INT,
    INSTALLATION_ID INT,
    VENDOR_ID INT,
    TAGNAME VARCHAR(32),
    ALREADY_EXISTS int
    );

insert into TEMP_TAGS 
SELECT DISTINCT CLIENT_ID,
    INSTALLATION_ID,
    VENDOR_ID,
    TAGNAME,
    0
from ALARM_DATA a
where 
a.ALM_TIME>'2017-11-01'; 



UPDATE TEMP_TAGS t, ALARM_TYPE a
SET t.ALREADY_EXISTS=1  
where t.client_id = a.client_id 
and t.installation_id = a.installation_id 
and t.vendor_id = a.vendor_ID 
and t.tagname=a.tagname; 


delete from TEMP_TAGS where ALREADY_EXISTS<>0;

insert into ALARM_TYPE
SELECT DISTINCT
    a.Client_ID,
    a.INSTALLATION_ID,
    a.VENDOR_ID,
    a.SYSTEM,
    a.SUBSYSTEM,
    a.TAGNAME,
    a.MSGTYPE,     
    a.PRIORITY,
    a.DESCRIPTION,
    0,0,0,0,0,0,0,'',''
FROM
    ALARM_DATA a, TEMP_TAGS t
where t.client_id = a.client_id 
	and t.installation_id = a.installation_id 
	and t.vendor_id = a.vendor_ID 
    and t.installation_id = 101
    and t.vendor_id = 0
	and t.tagname=a.tagname
    and a.ALM_TIME>'2017-11-01'; 

insert into ALARM_TYPE
SELECT DISTINCT
    a.Client_ID,
    a.INSTALLATION_ID,
    a.VENDOR_ID,
    a.SYSTEM,
    a.SUBSYSTEM,
    a.TAGNAME,
    a.MSGTYPE,
    a.PRIORITY,
    a.DESCRIPTION,
    0,0,0,0,0,0,0,'',''
FROM
    ALARM_DATA a, TEMP_TAGS t
where t.client_id = a.client_id 
	and t.installation_id = a.installation_id 
	and t.vendor_id = a.vendor_ID 
    and t.installation_id = 102
    and t.vendor_id = 0
	and t.tagname=a.tagname
    and a.ALM_TIME>'2017-11-01'; 

    
insert into ALARM_TYPE
SELECT DISTINCT
    a.Client_ID,
    a.INSTALLATION_ID,
    a.VENDOR_ID,
    a.SYSTEM,
    a.SUBSYSTEM,
    a.TAGNAME,
    a.MSGTYPE,
    a.PRIORITY,
    a.DESCRIPTION,
    0,0,0,0,0,0,0,'',''
FROM
    ALARM_DATA a, TEMP_TAGS t
where t.client_id = a.client_id 
	and t.installation_id = a.installation_id 
	and t.vendor_id = a.vendor_ID 
    and t.vendor_id = 1
	and t.tagname=a.tagname
    and a.ALM_TIME>'2017-11-01';  
    
drop table TEMP_TAGS;

UPDATE ALARM_TYPE SET FLAG_ID_PRIORITY=1 WHERE PRIORITY='Low' OR PRIORITY='LOW';
UPDATE ALARM_TYPE SET FLAG_ID_PRIORITY=2 WHERE PRIORITY='Medium' OR PRIORITY='MEDIUM';
UPDATE ALARM_TYPE SET FLAG_ID_PRIORITY=3 WHERE PRIORITY='HIGH' and VENDOR_ID =0;

UPDATE ALARM_TYPE SET FLAG_ID_PRIORITY=2 WHERE PRIORITY='High' and VENDOR_ID =1;
UPDATE ALARM_TYPE SET FLAG_ID_PRIORITY=3 WHERE PRIORITY='CRITICAL' and VENDOR_ID =1;
 
 
 

