package task20_transportation_data_base.transportation.repo.impl;

import task20_transportation_data_base.common.solutions.utils.db.QueryHelper;
import task20_transportation_data_base.storage.IdGenerator;
import task20_transportation_data_base.storage.initor.dbinitor.TransportationsConnectionPool;
import task20_transportation_data_base.transportation.domain.Transportation;
import task20_transportation_data_base.transportation.domain.TransportationMapper;
import task20_transportation_data_base.transportation.repo.TransportationRepo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import static task20_transportation_data_base.common.solutions.utils.db.QueryHelper.executeUpdate;
import static task20_transportation_data_base.storage.initor.dbinitor.DbConstants.*;

public class TransportationDBRepoImpl implements TransportationRepo {
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
                "INSERT INTO " + TRANSPORTATION_TABLE_NAME +
                        " (ID, DESCRIPTION, BILL_TO, BEGIN_DATE, CARGO_ID, CARRIER_ID)" +
                        " VALUES (?,?,?,?,?,?)",
                preparedStatement -> {
                    int i = 0;
                    preparedStatement.setLong(++i, transportation.getId());
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
                }
        );
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
        int affectedRows = executeUpdate("DELETE FROM " + TRANSPORTATION_TABLE_NAME +
                        " WHERE ID = ?",
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
}
