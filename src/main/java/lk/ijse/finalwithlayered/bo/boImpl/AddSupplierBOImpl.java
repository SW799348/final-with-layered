package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.boInter.AddSupplierBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.interfaces.AddSupplierDAO;
import lk.ijse.finalwithlayered.dto.SupplierDto;
import lk.ijse.finalwithlayered.entity.Supplier;

import java.sql.SQLException;

public class AddSupplierBOImpl implements AddSupplierBO {

    AddSupplierDAO addSupplierDAO= (AddSupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADD_SUPPLIER);
    @Override
    public String generateNextSupId() throws SQLException, ClassNotFoundException {
        return addSupplierDAO.generateNewId();
    }

    @Override
    public boolean saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return addSupplierDAO.save(new Supplier(supplierDto.getSupplierId(),supplierDto.getName(),supplierDto.getContact_info(),supplierDto.getAddress()));
    }
}
