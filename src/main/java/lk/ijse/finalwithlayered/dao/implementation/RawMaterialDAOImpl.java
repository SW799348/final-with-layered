package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.RawMaterialDAO;
import lk.ijse.finalwithlayered.dto.tm.CartTm;
import lk.ijse.finalwithlayered.entity.Customer;
import lk.ijse.finalwithlayered.entity.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RawMaterialDAOImpl implements RawMaterialDAO {

    @Override
    public ArrayList<Material> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst= SqlUtil.execute("SELECT * FROM raw_material");

        ArrayList<Material> allMaterials = new ArrayList<>();

        while (rst.next()){
            Material entity = new Material(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            );
            allMaterials.add(entity);
        }

        return allMaterials;
    }

    @Override
    public boolean save(Material entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT into raw_material values (?,?,?,?);",
                entity.getMaterialId(),
                entity.getDescription(),
                entity.getQtyOnHand(),
                entity.getUnitPrice());
    }

    @Override
    public boolean update(Material entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE raw_material SET description=?,qty_on_hand=?,unit_price=? WHERE materialId=?;;",
                entity.getMaterialId(),
                entity.getDescription(),
                entity.getQtyOnHand(),
                entity.getUnitPrice());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE  from raw_material where materialId=?;",id);
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet rst=SqlUtil.execute("SELECT materialId from raw_material");

        ArrayList<String> materialIds = new ArrayList<>();

        while (rst.next()){
            String ids = rst.getString(1);
            materialIds.add(ids);
        }

        return materialIds;
    }

    @Override
    public Material get(String updateEmpId) throws SQLException, ClassNotFoundException {
        ResultSet rst=SqlUtil.execute("SELECT * from raw_material where materialId=?",updateEmpId);

        Material entity = null;
        if(rst.next()){
            entity = new Material(
                    updateEmpId + "",
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            );
        }
        return entity;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT materialId FROM raw_material");

        int max = 0;
        while (resultSet.next()){
            String x = resultSet.getString(1);
            String[] y = x.split("M");
            int id = Integer.parseInt(y[1]);

            if (max < id){
                max = id;
            }

        }

        return "M00" + ++max;
    }

    @Override
    public boolean updateQty(CartTm cartTm) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE raw_material SET qty_on_hand = qty_on_hand - ? WHERE materialId = ?;",cartTm.getRequiredMaterialQty(),cartTm.getMaterialId());
    }

    @Override
    public boolean updateRawMaterial(List<CartTm> tmList) throws SQLException, ClassNotFoundException {
        for (CartTm cartTm : tmList) {
            if(!updateQty(cartTm)) {
                return false;
            }
        }
        return true;
    }
}
