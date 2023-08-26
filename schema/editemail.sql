use newsteelsystem;
/*update employee set others =null where others='';
delete from contacts where phoneType is null and numbers='';*/
/*select employee.employeeId,employee.customerId from employee left join contacts on employee.employeeId=contacts.employeeId where contacts.contactsId is null;*/
delete * from employee left join contacts on employee.employeeId=contacts.employeeId where contacts.contactsId is null;