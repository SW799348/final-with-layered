package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;
import lk.ijse.finalwithlayered.dto.SupplierDto;

import java.sql.SQLException;

public interface UpdateSupplierBO extends SuperBO {

    boolean updateSupplier(SupplierDto supplierDto) throws SQLException,ClassNotFoundException;


}
