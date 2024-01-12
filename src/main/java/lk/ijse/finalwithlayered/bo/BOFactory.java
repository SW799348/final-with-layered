package lk.ijse.finalwithlayered.bo;

import lk.ijse.finalwithlayered.bo.boImpl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {

        CUSTOMER,ADD_CUSTOMER,UPDATE_CUSTOMER,EMPLOYEE,ADD_EMPLOYEE,UPDATE_EMPLOYEE,USER,SUPPLIER,ADD_SUPPLIER,UPDATE_SUPPLIER,ATTENDANCE
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ADD_CUSTOMER:
                return new AddCustomerBOImpl();
            case UPDATE_CUSTOMER:
                return new UpdateCustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ADD_EMPLOYEE:
                return new AddEmployeeBOImpl();
            case UPDATE_EMPLOYEE:
                return new UpdateEmployeeBOImpl();
            case USER:
                return new UserBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case ADD_SUPPLIER:
                return new AddSupplierBOImpl();
            case UPDATE_SUPPLIER:
                return new UpdateSupplierBOImpl();
            case ATTENDANCE:
                return new AttendanceBOImpl();
            default:
                return null;
        }
    }



}
