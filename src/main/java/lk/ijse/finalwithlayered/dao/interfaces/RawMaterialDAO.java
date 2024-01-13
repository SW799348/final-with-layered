package lk.ijse.finalwithlayered.dao.interfaces;

import lk.ijse.finalwithlayered.dao.CrudUtil;
import lk.ijse.finalwithlayered.dto.tm.CartTm;
import lk.ijse.finalwithlayered.entity.Material;

import java.sql.SQLException;
import java.util.List;

public interface RawMaterialDAO extends CrudUtil<Material> {
    boolean updateQty(CartTm cartTm)throws SQLException,ClassNotFoundException;

    boolean updateRawMaterial(List<CartTm> tmList)throws SQLException,ClassNotFoundException;
}
