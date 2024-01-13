package lk.ijse.finalwithlayered.dao;

import lk.ijse.finalwithlayered.dto.MaterialDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudUtil<T> extends SuperDAO{

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException ;
    boolean save(T dto) throws SQLException, ClassNotFoundException ;
    boolean update(T dto) throws SQLException, ClassNotFoundException ;
    boolean delete(String id) throws SQLException, ClassNotFoundException ;
    ArrayList<String> getAllId() throws SQLException, ClassNotFoundException ;
    T get(String updateEmpId) throws SQLException, ClassNotFoundException ;
    String generateNewId() throws SQLException, ClassNotFoundException;



}
