package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;
import lk.ijse.finalwithlayered.dto.SupplierDto;

import java.sql.SQLException;

public interface AddSupplierBO extends SuperBO {
    String generateNextSupId() throws SQLException,ClassNotFoundException;
    boolean saveSupplier(SupplierDto supplierDto)throws SQLException,ClassNotFoundException;

}
