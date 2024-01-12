drop database if exists factory10;

create database if not exists factory10;

use factory10;

create table user(
            userId varchar(30) PRIMARY KEY,
	    userName varchar(155) NOT NULL

);

create table customer(
            custId varchar(30) PRIMARY KEY,
	        custName varchar(155) NOT NULL,
            address text  NOT NULL,
            tel int  NOT NULL

);

drop table if exists orders;

create table if not exists orders;

create table orders(
            orderId varchar(30) PRIMARY KEY,
            date date  NOT NULL,
            custId varchar(30),
            payemnt_status varchar(155),
            constraint foreign key (custId) references customer(custId)
    on delete  cascade on update cascade

);

create table raw_material (
                              materialId varchar(30) PRIMARY KEY,
                              description text NOT NULL,
                              qty_on_hand varchar(50) NOT NULL,
                              unit_price double NOT NULL

);



create table order_detail(
            materialId varchar(30),
            orderId varchar(30) ,
            unit_price double ,
            required_material_qty_for_each_size double,
               constraint foreign key (orderId) references orders(orderId)
                on delete  cascade on update cascade,
            constraint foreign key (materialId) references raw_material(materialId)
                on delete cascade on update cascade


);


create table supplier(
            supplierId varchar(30) PRIMARY KEY,
	    name varchar(155) NOT NULL,
            contact_info varchar(40) NOT NULL,
            supplier_address text NOT NULL


);

create table supplier_details (
        supplierId varchar(30) ,
        description text NOT NULL,
        supply_date date NOT NULL,
        materialId varchar(30),
        qtySupplied  varchar(155),
        unitPrice double,
        constraint foreign key (supplierId) references supplier(supplierId) on delete  cascade on update cascade,
        constraint foreign key (materialId) references raw_material(materialId) on delete  cascade on update cascade


);

create table employee(
            empId varchar(30) PRIMARY KEY,
	        name varchar(155) NOT NULL,
            address text NOT NULL,
            tel int NOT NULL,
            userId varchar(30),
            constraint foreign key (userId) references user(userId) on delete  cascade on update cascade

);


create table attendance(
            empId varchar(30),
            attendanceId varchar(30) PRIMARY KEY,
            date date NOT NULL,
            time_in time,
            time_out time,
	        working_hours double NOT NULL,
            constraint foreign key (empId) references employee(empId) on delete  cascade on update cascade

);

create table machine(
            machineId varchar(30) PRIMARY KEY,
	        machine_type varchar(155) NOT NULL,
            maintaince_status text NOT NULL,
            empId varchar(30),
            constraint foreign key (empId) references employee(empId) on delete  cascade on update cascade

);

create table salary(
                       salaryId varchar(30) PRIMARY KEY,
                       empId varchar(30) not null,
                       month varchar(155) not null,
                       Amount_of_work_done_per_month int,
                       Ot_hours int,
                       total_salary double not null,
                       constraint foreign key (empId) references employee(empId) on delete  cascade on update cascade

);









