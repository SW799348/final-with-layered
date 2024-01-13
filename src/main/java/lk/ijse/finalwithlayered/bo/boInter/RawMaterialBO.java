package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;
import lk.ijse.finalwithlayered.dto.MaterialDto;
import lk.ijse.finalwithlayered.dto.tm.CartTm;
import lk.ijse.finalwithlayered.entity.Material;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RawMaterialBO extends SuperBO {

    String generateNextMaterialId() throws SQLException, ClassNotFoundException;
    boolean updateRawMaterial(List<CartTm> tmList) throws SQLException,ClassNotFoundException;
    boolean updateQty(CartTm cartTm) throws SQLException,ClassNotFoundException;
    boolean saveMaterial(MaterialDto dto) throws SQLException,ClassNotFoundException;

    ArrayList<MaterialDto> getAllMaterials() throws SQLException,ClassNotFoundException;

    boolean deleteMaterial(String id) throws SQLException,ClassNotFoundException;

    Material getMaterial(String updateMaterial) throws SQLException,ClassNotFoundException;

    boolean updateMaterial(MaterialDto materialDto) throws SQLException,ClassNotFoundException;

    ArrayList<String> getAllMaterialIds() throws SQLException,ClassNotFoundException;





}
