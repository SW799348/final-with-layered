package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;
import lk.ijse.finalwithlayered.dto.SupplierDto;
import lk.ijse.finalwithlayered.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDto> getAllSuppliers() throws SQLException,ClassNotFoundException;

    boolean deleteSupplier(String id)throws SQLException,ClassNotFoundException;

    Supplier searchSupplier(String text)throws SQLException,ClassNotFoundException;


}
