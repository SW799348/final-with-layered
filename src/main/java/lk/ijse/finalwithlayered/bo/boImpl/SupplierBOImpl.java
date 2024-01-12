package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.boInter.SupplierBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.interfaces.SupplierDAO;
import lk.ijse.finalwithlayered.dto.SupplierDto;
import lk.ijse.finalwithlayered.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();

        for (Supplier supplier : suppliers){
            supplierDtos.add(new SupplierDto(supplier.getSupplierId(),supplier.getName(),supplier.getAddress(),supplier.getContact_info()));
        }
        return supplierDtos;
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public Supplier searchSupplier(String text) throws SQLException, ClassNotFoundException {
        return supplierDAO.get(text);
    }
}
