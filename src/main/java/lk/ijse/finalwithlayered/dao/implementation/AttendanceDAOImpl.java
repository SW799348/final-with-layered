package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.interfaces.AttendanceDAO;
import lk.ijse.finalwithlayered.entity.Attendance;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public ArrayList<Attendance> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Attendance dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Attendance dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Attendance get(String updateEmpId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
