
delete from USERS_TO_PERMISSIONS;
delete from PERMISSIONS;
delete from VENDOR;
delete from INSTALLATION;
delete from USERS;

insert into USERS (USER_ID,login,user_password) values (0,'shadley000','admin1');
insert into USERS (USER_ID,login,user_password) values (1,'Diamond','password');

insert into INSTALLATION (INSTALLATION_ID, INSTALLATION_NAME) values (0,'DemoRig');
insert into INSTALLATION (INSTALLATION_ID, INSTALLATION_NAME) values (101,'BlackRhino');
insert into INSTALLATION (INSTALLATION_ID, INSTALLATION_NAME) values (102,'BlackLion');
insert into INSTALLATION (INSTALLATION_ID, INSTALLATION_NAME) values (103,'BlackHawk');
insert into INSTALLATION (INSTALLATION_ID, INSTALLATION_NAME) values (104,'BlackHornet');

insert into VENDOR (VENDOR_ID, VENDOR_NAME) values (0,'NOV DCS');
insert into VENDOR (VENDOR_ID, VENDOR_NAME) values (1,'Kongsberg K-Chief');


insert into PERMISSIONS (PERMISSION_ID,DESCRIPTION) values (0,'view');
insert into PERMISSIONS (PERMISSION_ID,DESCRIPTION) values (1,'comment');
insert into PERMISSIONS (PERMISSION_ID,DESCRIPTION) values (2,'upload files');
insert into PERMISSIONS (PERMISSION_ID,DESCRIPTION) values (3,'admin');

-- ADMIN ***********************************************************

insert into USERS_TO_PERMISSIONS SELECT distinct 0, i.INSTALLATION_ID, v.VENDOR_ID, p.PERMISSION_ID from INSTALLATION i, VENDOR v, PERMISSIONS p;

insert into USERS_TO_PERMISSIONS SELECT distinct 1, 101, v.VENDOR_ID, p.PERMISSION_ID from VENDOR v, PERMISSIONS p WHERE p.PERMISSION_ID <>3;
insert into USERS_TO_PERMISSIONS SELECT distinct 1, 102, v.VENDOR_ID, p.PERMISSION_ID from VENDOR v, PERMISSIONS p WHERE p.PERMISSION_ID <>3;
insert into USERS_TO_PERMISSIONS SELECT distinct 1, 103, v.VENDOR_ID, p.PERMISSION_ID from VENDOR v, PERMISSIONS p WHERE p.PERMISSION_ID <>3;
insert into USERS_TO_PERMISSIONS SELECT distinct 1, 104, v.VENDOR_ID, p.PERMISSION_ID from VENDOR v, PERMISSIONS p WHERE p.PERMISSION_ID <>3;
    
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
  