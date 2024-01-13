package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.boInter.RawMaterialBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.interfaces.RawMaterialDAO;
import lk.ijse.finalwithlayered.dto.MaterialDto;
import lk.ijse.finalwithlayered.dto.tm.CartTm;
import lk.ijse.finalwithlayered.entity.Customer;
import lk.ijse.finalwithlayered.entity.Material;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RawMaterialBOImpl implements RawMaterialBO {

    RawMaterialDAO rawMaterialDAO= (RawMaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RAW_MATERIAL);
    @Override
    public String generateNextMaterialId() throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.generateNewId();
    }

    @Override
    public boolean updateRawMaterial(List<CartTm> tmList) throws SQLException, ClassNotFoundException {
       return rawMaterialDAO.updateRawMaterial(tmList);
    }

    @Override
    public boolean updateQty(CartTm cartTm) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.updateQty(cartTm);
    }

    @Override
    public boolean saveMaterial(MaterialDto dto) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.save(new Material(dto.getMaterialId(),dto.getDescription(),dto.getQtyOnHand(),dto.getUnitPrice()));
    }

    @Override
    public ArrayList<MaterialDto> getAllMaterials() throws SQLException, ClassNotFoundException {
        ArrayList<Material> materials = rawMaterialDAO.getAll();
        ArrayList<MaterialDto> materialDtos = new ArrayList<>();

        for (Material material : materials){
            materialDtos.add(new MaterialDto(material.getMaterialId(),material.getDescription(),material.getQtyOnHand(),material.getUnitPrice()));
        }
        return materialDtos;
    }

    @Override
    public boolean deleteMaterial(String id) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.delete(id);
    }

    @Override
    public Material getMaterial(String updateMaterial) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.get(updateMaterial);
    }

    @Override
    public boolean updateMaterial(MaterialDto materialDto) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.update(new Material(materialDto.getMaterialId(),materialDto.getDescription(),materialDto.getQtyOnHand(),materialDto.getUnitPrice()));
    }

    @Override
    public ArrayList<String> getAllMaterialIds() throws SQLException, ClassNotFoundException {
        ArrayList<Material> materials = rawMaterialDAO.getAll();

        // Step 2: Create a new list to store customer IDs
        ArrayList<String> materialIds = new ArrayList<>();

        // Step 3: Iterate through each Customer object in the retrieved list
        for (Material material : materials){
            // Step 4: Extract the customer ID and add it to the list
            String materialId = material.getMaterialId();
            materialIds.add(materialId);
        }

        // Step 5: Return the list of customer IDs
        return materialIds;
    }


}
