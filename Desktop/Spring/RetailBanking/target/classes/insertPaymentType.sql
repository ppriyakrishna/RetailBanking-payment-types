/*
-- Query: SELECT * FROM cs548_retailbanking.paymenttype
LIMIT 0, 1000

-- Date: 2013-08-25 11:58
*/
INSERT INTO `paymenttype` (`type_Id`,`type_Code`,`type_Name`,`description`) VALUES (1,'DFT','DirectFundsTransfer','If Both accounts are in the same country ');
INSERT INTO `paymenttype` (`type_Id`,`type_Code`,`type_Name`,`description`) VALUES (2,'CBFT','CrossBorderFundsTransfer','If one of the account is in different country');
