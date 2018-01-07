  
  CREATE TABLE `test_pivot` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(32) DEFAULT NULL,
  `action` varchar(16) DEFAULT NULL,
  `pagecount` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=MyISAM;

SELECT
    company_name,  
    SUM(action = 'EMAIL')AS Email,
    SUM(action = 'PRINT' AND pagecount = 1)AS Print1Pages,
    SUM(action = 'PRINT' AND pagecount = 2)AS Print2Pages,
    SUM(action = 'PRINT' AND pagecount = 3)AS Print3Pages
FROM t
GROUP BY company_name