package task20_transportation_data_base.transportation.repo.impl;

import task20_transportation_data_base.common.solutions.utils.db.QueryHelper;
import task20_transportation_data_base.storage.IdGenerator;
import task20_transportation_data_base.storage.initor.dbinitor.TransportationsConnectionPool;
import task20_transportation_data_base.transportation.domain.Transportation;
import task20_transportation_data_base.transportation.domain.TransportationMapper;
import task20_transportation_data_base.transportation.repo.TransportationRepo;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static task20_transportation_data_base.common.solutions.utils.db.QueryHelper.executeUpdate;
import static task20_transportation_data_base.storage.initor.dbinitor.DbConstants.*;

public class TransportationDBRepoImpl implements TransportationRepo {

    private final String INSERT_SQL = "INSERT INTO " + TRANSPORTATION_TABLE_NAME +
            " (ID, DESCRIPTION, BILL_TO, BEGIN_DATE, CARGO_ID, CARRIER_ID)" +
            " VALUES (?,?,?,?,?,?)";

    @Override
    public Optional<Transportation> findById(Long id) {
        String sql = "SELECT * FROM " +
                TRANSPORTATION_TABLE_NAME + " T " +
                " LEFT JOIN " + CARGO_TABLE_NAME + " CG ON " + " CG.ID = " + " T.CARGO_ID " +
                " LEFT JOIN " + CARRIER_TABLE_NAME + " CR ON " + " CR.ID = " + " T.CARRIER_ID " +
                " WHERE T.ID = ?";
        Transportation transportation = QueryHelper.selectOne(sql,
                preparedStatement -> preparedStatement.setLong(1, id),
                TransportationMapper::mapTransportation);
        return Optional.ofNullable(transportation);
    }

    @Override
    public void save(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        //TODO Return affected rows count.
        //int affectedRows =
        executeUpdate(
                INSERT_SQL,
                preparedStatement -> setStatementParametersFromCarrier(preparedStatement, transportation)
        );
    }

    private PreparedStatement setStatementParametersFromCarrier(PreparedStatement preparedStatement, Transportation transportation) throws Exception {
        int i = 0;
        preparedStatement.setLong(++i, transportation.getId());
        preparedStatement.setString(++i, transportation.getDescription());
        preparedStatement.setString(++i, transportation.getBillTo());
        preparedStatement.setDate(++i, Date.valueOf(transportation.getTransportationBeginDate()));
        i++;
        if (transportation.getCargo() != null) {
            preparedStatement.setLong(i, transportation.getCargo().getId());
        } else {
            preparedStatement.setNull(i, Types.BIGINT);
        }
        i++;
        if (transportation.getCarrier() != null) {
            preparedStatement.setLong(i, transportation.getCarrier().getId());
        } else {
            preparedStatement.setNull(i, Types.BIGINT);
        }
        return preparedStatement;
    }

    @Override
    public boolean update(Transportation transportation) {
        int affectedRows = executeUpdate(
                "UPDATE " + TRANSPORTATION_TABLE_NAME +
                        " SET DESCRIPTION = ?, " +
                        " BILL_TO = ?" +
                        " BEGIN_DATE = ?" +
                        " CARGO_ID = ?" +
                        " CARRIER_ID = ?" +
                        " WHERE ID = ?",
                preparedStatement -> {
                    int i = 0;
                    preparedStatement.setString(++i, transportation.getDescription());
                    preparedStatement.setString(++i, transportation.getBillTo());
                    preparedStatement.setDate(++i, Date.valueOf(transportation.getTransportationBeginDate()));
                    i++;
                    if (transportation.getCargo() != null) {
                        preparedStatement.setLong(i, transportation.getCargo().getId());
                    }
                    i++;
                    if (transportation.getCarrier() != null) {
                        preparedStatement.setLong(i, transportation.getCarrier().getId());
                    }
                    preparedStatement.setLong(++i, transportation.getId());
                }
        );

        return affectedRows == 1;
    }

    @Override
    public boolean deleteById(Long id) {
        int affectedRows = executeUpdate("DELETE FROM " + TRANSPORTATION_TABLE_NAME + " WHERE ID = ?",
                preparedStatement -> preparedStatement.setLong(1, id));
        return affectedRows == 1;
    }

    @Override
    public List<Transportation> getAll() {
        String sql = "SELECT * FROM " +
                TRANSPORTATION_TABLE_NAME + " T " +
                " LEFT JOIN " + CARGO_TABLE_NAME + " CG ON " + " CG.ID = " + " T.CARGO_ID " +
                " LEFT JOIN " + CARRIER_TABLE_NAME + " CR ON " + " CR.ID = " + " T.CARRIER_ID ";
        return QueryHelper.selectAll(sql, TransportationMapper::mapTransportation);
    }

    @Override
    public int countAll() {
        return QueryHelper.getRowsCount("SELECT COUNT (*) AS TOTAL_COUNT FROM " + TRANSPORTATION_TABLE_NAME,
                resultSet -> resultSet.getInt("TOTAL_COUNT"));
    }

    public int saveSeveralCarriers(List<Transportation> transportations) throws SQLException {
        boolean autoCommitState = true;
        Connection connection = null;
        int affectedRows = 0;

        try {
            connection = TransportationsConnectionPool.getInstance().getConnection();
            autoCommitState = connection.getAutoCommit();
            connection.setAutoCommit(false);

            for (Transportation transportation : transportations) {
                transportation.setId(IdGenerator.generateId());
                affectedRows += QueryHelper.executeUpdateInConnection(
                        INSERT_SQL,
                        connection,
                        preparedStatement -> setStatementParametersFromCarrier(preparedStatement, transportation));
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
