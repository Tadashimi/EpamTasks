package task20_transportation_data_base.cargo.repo.impl;

import task20_transportation_data_base.cargo.domain.Cargo;
import task20_transportation_data_base.cargo.domain.CargoType;
import task20_transportation_data_base.cargo.domain.ClothersCargo;
import task20_transportation_data_base.cargo.domain.FoodCargo;
import task20_transportation_data_base.cargo.search.CargoSearchCondition;
import task20_transportation_data_base.storage.IdGenerator;
import task20_transportation_data_base.common.solutions.utils.db.QueryHelper;
import task20_transportation_data_base.storage.initor.dbinitor.TransportationsDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CargoDBRepoImpl extends CommonCargoRepo {
    private final String TABLE_NAME = "PUBLIC.CARGO";

    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public Cargo[] findByName(String name) {
        return new Cargo[0];
    }

    @Override
    public List<Cargo> search(CargoSearchCondition cargoSearchCondition) {
        return null;
    }

    @Override
    public Optional<Cargo> findById(Long id) {
        try {
            Connection connection = TransportationsDataSource.getConnection();
            ResultSet resultSet = QueryHelper.selectById(TABLE_NAME, connection, id);
            if (resultSet.next()) {
                return Optional.of(getCargoFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Cargo entity) {
        try {
            Connection connection = TransportationsDataSource.getConnection();
            entity.setId(IdGenerator.generateId());
            switch (entity.getCargoType()) {
                case FOOD:
                    saveFoodCargoToDB((FoodCargo) entity, connection);
                    break;
                case CLOTHERS:
                    saveClothersCargoToDB((ClothersCargo) entity, connection);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveFoodCargoToDB(FoodCargo cargo, Connection connection) throws SQLException {
        String saveFoodCargoToDB = "INSERT INTO PUBLIC.CARGO" +
                "(ID, NAME, WEIGHT, CARGO_TYPE, STORED_TEMPERATURE, EXPIRATION_DATE) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(saveFoodCargoToDB);
        preparedStatement.setLong(1, cargo.getId());
        preparedStatement.setString(2, cargo.getName());
        preparedStatement.setInt(3, cargo.getWeight());
        preparedStatement.setString(4, cargo.getCargoType().toString());
        preparedStatement.setInt(5, cargo.getStoreTemperature());
        preparedStatement.setDate(6, java.sql.Date.valueOf(cargo.getExpirationDate()));
        preparedStatement.execute();
    }

    private void saveClothersCargoToDB(ClothersCargo cargo, Connection connection) throws SQLException {
        String saveClothersCargoToDB = "INSERT INTO PUBLIC.CARGO" +
                "(ID, NAME, WEIGHT, CARGO_TYPE, SIZE, MATERIAL) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(saveClothersCargoToDB);
        preparedStatement.setLong(1, cargo.getId());
        preparedStatement.setString(2, cargo.getName());
        preparedStatement.setInt(3, cargo.getWeight());
        preparedStatement.setString(4, cargo.getCargoType().toString());
        preparedStatement.setString(5, cargo.getSize());
        preparedStatement.setString(6, cargo.getMaterial());
        preparedStatement.execute();
    }

    @Override
    public boolean update(Cargo entity) {
        return false;
    }

    @Override
    public boolean deleteById(Long aLong) {
        return false;
    }

    @Override
    public List<Cargo> getAll() {
        List<Cargo> cargos = new ArrayList<>();
        try {
            Connection connection = TransportationsDataSource.getConnection();
            ResultSet resultSet = QueryHelper.selectAll(TABLE_NAME, connection);

            while (resultSet.next()) {
                cargos.add(getCargoFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cargos.size() == 0 ? Collections.EMPTY_LIST : cargos;
    }

    private Cargo getCargoFromResultSet(ResultSet resultSet) throws SQLException {
        Cargo cargo = null;
        CargoType cargoType = CargoType.valueOf(resultSet.getString("CARGO_TYPE"));
        switch (cargoType) {
            case FOOD:
                cargo = new FoodCargo();
                ((FoodCargo) cargo).setStoreTemperature(resultSet.getInt("STORED_TEMPERATURE"));
                ((FoodCargo) cargo).setExpirationDate(resultSet.getDate("EXPIRATION_DATE").toLocalDate());
                break;
            case CLOTHERS:
                cargo = new ClothersCargo();
                ((ClothersCargo) cargo).setSize(resultSet.getString("SIZE"));
                ((ClothersCargo) cargo).setMaterial(resultSet.getString("MATERIAL"));
                break;
        }
        cargo.setId(resultSet.getLong("ID"));
        cargo.setName(resultSet.getString("NAME"));
        cargo.setWeight(resultSet.getInt("WEIGHT"));
        return cargo;
    }

    @Override
    public int countAll() {
        try {
            Connection connection = TransportationsDataSource.getConnection();
            return QueryHelper.getRowsCount(TABLE_NAME, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
