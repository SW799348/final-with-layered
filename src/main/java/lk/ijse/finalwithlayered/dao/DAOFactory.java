package lk.ijse.finalwithlayered.dao;

import lk.ijse.finalwithlayered.dao.implementation.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,ADD_CUSTOMER,UPDATE_CUSTOMER,EMPLOYEE,ADD_EMPLOYEE,UPDATE_EMPLOYEE,USER,SUPPLIER,ADD_SUPPLIER,UPDATE_SUPPLIER,ATTENDANCE,RAW_MATERIAL,SALARY,ORDER,ORDER_DETAIL
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ADD_CUSTOMER:
                return new AddCustomerDAOImpl();
            case UPDATE_CUSTOMER:
                return new UpdateCustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ADD_EMPLOYEE:
                return new AddEmployeeDAOImpl();
            case UPDATE_EMPLOYEE:
                return new UpdateEmployeeDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case ADD_SUPPLIER:
                return new AddSupplierDAOImpl();
            case UPDATE_SUPPLIER:
                return new UpdateSupplierDAOImpl();
            case USER:
                return new UserDAOImpl();
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case RAW_MATERIAL:
                return new RawMaterialDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();

            default:
                return null;
        }
    }
}
