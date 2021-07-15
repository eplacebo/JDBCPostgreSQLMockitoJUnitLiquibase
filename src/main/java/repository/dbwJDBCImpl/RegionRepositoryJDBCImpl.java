package repository.dbwJDBCImpl;

import model.Region;
import repository.RegionRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegionRepositoryJDBCImpl implements RegionRepository {


    public RegionRepositoryJDBCImpl() {
    }

    @Override
    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();

        try {
            Statement statement = DBconnect.getConnectDB().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM regions");
            while (resultSet.next()) {
                Region region = new Region((long) resultSet.getInt("id_region"), resultSet.getString("name"));
                regions.add(region);
            }
            resultSet.close();
            statement.close();
            DBconnect.getConnectDB().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return regions;
    }

    @Override
    public Region getById(Long aLong) {
        Region region = null;
        try {
            PreparedStatement prepareStatement = DBconnect.getConnectDB().prepareStatement("SELECT * FROM regions WHERE id_region = ?");
            prepareStatement.setLong(1, aLong);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                region = new Region(resultSet.getLong("id_region"), resultSet.getString("name"));
                resultSet.close();
            }
            prepareStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }

    @Override
    public boolean deleteById(Long aLong) {
        try {
            PreparedStatement prepareStatement = DBconnect.getConnectDB().prepareStatement("DELETE FROM regions WHERE id_region = ?");
            prepareStatement.setLong(1, aLong);
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Region save(Region region) {
        try {
            PreparedStatement prepareStatement = DBconnect.getConnectDB().prepareStatement("INSERT INTO regions(id_region,name) VALUES (?, ?)");
            prepareStatement.setLong(1, region.getId());
            prepareStatement.setString(2, region.getName());
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }


    @Override
    public Region update(Region region) {
        try {
            PreparedStatement prepareStatement = DBconnect.getConnectDB().prepareStatement("UPDATE regions SET name = ? WHERE id_region=?");
            prepareStatement.setLong(2, region.getId());
            prepareStatement.setString(1, region.getName());
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }
}




