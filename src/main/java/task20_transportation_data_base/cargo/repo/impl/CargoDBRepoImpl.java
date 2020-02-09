package task20_transportation_data_base.cargo.repo.impl;

import task20_transportation_data_base.cargo.domain.Cargo;
import task20_transportation_data_base.cargo.domain.CargoMapper;
import task20_transportation_data_base.cargo.domain.ClothersCargo;
import task20_transportation_data_base.cargo.domain.FoodCargo;
import task20_transportation_data_base.cargo.search.CargoSearchCondition;
import task20_transportation_data_base.common.solutions.utils.db.QueryHelper;
import task20_transportation_data_base.storage.IdGenerator;
import task20_transportation_data_base.storage.initor.dbinitor.TransportationsConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

import static task20_transportation_data_base.common.solutions.utils.db.QueryHelper.executeUpdate;
import static task20_transportation_data_base.storage.initor.dbinitor.DbConstants.CARGO_TABLE_NAME;

public class CargoDBRepoImpl extends CommonCargoRepo {
    private final String INSERT_SQL = "INSERT INTO " + CARGO_TABLE_NAME +
            " (ID, NAME, WEIGHT, CARGO_TYPE, STORED_TEMPERATURE, EXPIRATION_DATE, SIZE, MATERIAL)" +
            " VALUES (?,?,?,?,?,?,?,?)";

    @Override
    public Optional<Cargo> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public Cargo[] findByName(String name) {
        return QueryHelper.select("SELECT * FROM " + CARGO_TABLE_NAME +
                        " WHERE UPPER(NAME) LIKE UPPER(?)",
                preparedStatement -> preparedStatement.setString(1, name),
                CargoMapper::mapCargo).toArray(new Cargo[0]);
    }

    @Override
    public List<Cargo> search(CargoSearchCondition searchCondition) {
        return QueryHelper.selectAll(createSqlStatementFromSearchCondition(searchCondition),
                CargoMapper::mapCargo);
    }

    private String createSqlStatementFromSearchCondition(CargoSearchCondition searchCondition) {
        StringBuilder sql = new StringBuilder("SELECT * FROM " + CARGO_TABLE_NAME);
        if (searchCondition.needSorting()) {
            sql.append(" ORDER BY ");
            String[] strs = searchCondition.getSortFields().stream()
                    .map(Enum::name)
                    .toArray(String[]::new);
            String separator = searchCondition.isAscOrdering() ? " ASC, " : " DESC, ";
            sql.append(String.join(separator, strs));
            if (searchCondition.isAscOrdering()) {
                sql.append(" ASC");
            } else {
                sql.append(" DESC");
            }
        }
        return sql.toString();
    }

    @Override
    public Optional<Cargo> findById(Long id) {
        Cargo cargo = QueryHelper.selectOne("SELECT * FROM " + CARGO_TABLE_NAME +
                        " WHERE ID = ?",
                preparedStatement -> preparedStatement.setLong(1, id),
                CargoMapper::mapCargo);
        return Optional.ofNullable(cargo);
    }

    @Override
    public void save(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        //TODO Return affected rows count.
        //int affectedRows =
        executeUpdate(
                INSERT_SQL,
                preparedStatement -> setStatementParametersFromCargo(preparedStatement, cargo)
        );
    }

    private PreparedStatement setStatementParametersFromCargo(PreparedStatement preparedStatement,
                                                              Cargo cargo) throws Exception {
        int i = 0;
        preparedStatement.setLong(++i, cargo.getId());
        preparedStatement.setString(++i, cargo.getName());
        preparedStatement.setInt(++i, cargo.getWeight());
        preparedStatement.setString(++i, cargo.getCargoType().toString());
        switch (cargo.getCargoType()) {
            case FOOD:
                preparedStatement.setInt(++i, ((FoodCargo) cargo).getStoreTemperature());
                preparedStatement.setDate(++i,
                        java.sql.Date.valueOf(((FoodCargo) cargo).getExpirationDate()));
                preparedStatement.setNull(++i, Types.VARCHAR);
                preparedStatement.setNull(++i, Types.VARCHAR);
                break;
            case CLOTHERS:
                preparedStatement.setNull(++i, Types.INTEGER);
                preparedStatement.setNull(++i, Types.DATE);
                preparedStatement.setString(++i, ((ClothersCargo) cargo).getSize());
                preparedStatement.setString(++i, ((ClothersCargo) cargo).getMaterial());
                break;
        }
        return preparedStatement;
    }

    @Override
    public boolean update(Cargo cargo) {
        int affectedRows = executeUpdate(
                "UPDATE " + CARGO_TABLE_NAME +
                        " SET NAME = ?, " +
                        " WEIGHT = ?" +
                        " CARGO_TYPE = ?" +
                        " STORED_TEMPERATURE = ?" +
                        " EXPIRATION_DATE = ?" +
                        " SIZE = ?" +
                        " MATERIAL = ?" +
                        " WHERE ID = ?",
                preparedStatement -> {
                    int i = 0;
                    preparedStatement.setString(++i, cargo.getName());
                    preparedStatement.setInt(++i, cargo.getWeight());
                    preparedStatement.setString(++i, cargo.getCargoType().toString());
                    switch (cargo.getCargoType()) {
                        case FOOD:
                            preparedStatement.setInt(++i, ((FoodCargo) cargo).getStoreTemperature());
                            preparedStatement.setDate(++i,
                                    java.sql.Date.valueOf(((FoodCargo) cargo).getExpirationDate()));
                            preparedStatement.setNull(++i, Types.VARCHAR);
                            preparedStatement.setNull(++i, Types.VARCHAR);
                            break;
                        case CLOTHERS:
                            preparedStatement.setNull(++i, Types.INTEGER);
                            preparedStatement.setNull(++i, Types.DATE);
                            preparedStatement.setString(++i, ((ClothersCargo) cargo).getSize());
                            preparedStatement.setString(++i, ((ClothersCargo) cargo).getMaterial());
                            break;
                    }
                    preparedStatement.setLong(++i, cargo.getId());
                }
        );

        return affectedRows == 1;
    }

    @Override
    public boolean deleteById(Long id) {
        int affectedRows = executeUpdate("DELETE FROM " + CARGO_TABLE_NAME +
                        " WHERE ID = ?",
                preparedStatement -> preparedStatement.setLong(1, id));
        return affectedRows == 1;
    }

    @Override
    public List<Cargo> getAll() {
        return QueryHelper.selectAll("SELECT * FROM " + CARGO_TABLE_NAME,
                CargoMapper::mapCargo);
    }

    @Override
    public int countAll() {
        return QueryHelper.getRowsCount("SELECT COUNT (*) AS TOTAL_COUNT FROM " + CARGO_TABLE_NAME,
                resultSet -> resultSet.getInt("TOTAL_COUNT"));
    }

    public int saveSeveralCargoes(List<Cargo> cargos) throws Exception {
        boolean autoCommitState = true;
        Connection connection = null;
        int affectedRows = 0;
        try {
            connection = TransportationsConnectionPool.getInstance().getConnection();
            autoCommitState = connection.getAutoCommit();
            connection.setAutoCommit(false);

            for (Cargo cargo : cargos) {
                cargo.setId(IdGenerator.generateId());
                affectedRows += QueryHelper.executeUpdateInConnection(
                        INSERT_SQL,
                        connection,
                        preparedStatement -> setStatementParametersFromCargo(preparedStatement, cargo));
            }
            connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw new RuntimeException("Error in transaction");
        } finally {
            if (connection != null) {
                connection.setAutoCommit(autoCommitState);
                connection.close();
            }
        }
        return affectedRows;
    }
}
