package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.boInter.UpdateSupplierBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.interfaces.UpdateSupplierDAO;
import lk.ijse.finalwithlayered.dto.SupplierDto;
import lk.ijse.finalwithlayered.entity.Supplier;

import java.sql.SQLException;

public class UpdateSupplierBOImpl implements UpdateSupplierBO {

    UpdateSupplierDAO supplierDAO= (UpdateSupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.UPDATE_SUPPLIER);
    @Override
    public boolean updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDto.getSupplierId(),supplierDto.getName(),supplierDto.getContact_info(),supplierDto.getAddress()));
    }
}
