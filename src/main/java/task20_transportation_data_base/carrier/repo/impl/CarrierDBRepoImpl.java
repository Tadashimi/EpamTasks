package task20_transportation_data_base.carrier.repo.impl;

import task20_transportation_data_base.carrier.domain.Carrier;
import task20_transportation_data_base.carrier.domain.CarrierMapper;
import task20_transportation_data_base.carrier.repo.CarrierRepo;
import task20_transportation_data_base.common.solutions.utils.db.QueryHelper;
import task20_transportation_data_base.storage.IdGenerator;
import task20_transportation_data_base.storage.initor.dbinitor.TransportationsConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static task20_transportation_data_base.common.solutions.utils.db.QueryHelper.executeUpdate;
import static task20_transportation_data_base.storage.initor.dbinitor.DbConstants.CARRIER_TABLE_NAME;

public class CarrierDBRepoImpl implements CarrierRepo {
    @Override
    public Optional<Carrier> getByIdFetchingTransportations(long id) {
        return findById(id);
    }

    @Override
    public Carrier[] findByName(String name) {
        Carrier[] c = QueryHelper.select("SELECT * FROM " + CARRIER_TABLE_NAME +
                        " WHERE UPPER(NAME) LIKE UPPER(?)",
                preparedStatement -> preparedStatement.setString(1, name),
                CarrierMapper::mapCarrier).toArray(new Carrier[0]);
        return c;
    }

    @Override
    public Optional<Carrier> findById(Long id) {
        Carrier carrier = QueryHelper.selectOne("SELECT * FROM " + CARRIER_TABLE_NAME +
                        " WHERE ID = ?",
                preparedStatement -> preparedStatement.setLong(1, id),
                CarrierMapper::mapCarrier);
        return Optional.ofNullable(carrier);
    }

    @Override
    public void save(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        //TODO Return affected rows count.
        //int affectedRows =
        executeUpdate(
                "INSERT INTO " + CARRIER_TABLE_NAME +
                        " (ID, NAME, ADDRESS, CARRIER_TYPE)" +
                        " VALUES (?,?,?,?)",
                preparedStatement -> {
                    int i = 0;
                    preparedStatement.setLong(++i, carrier.getId());
                    preparedStatement.setString(++i, carrier.getName());
                    preparedStatement.setString(++i, carrier.getAddress());
                    preparedStatement.setString(++i, carrier.getCarrierType().toString());
                }
        );
    }

    @Override
    public boolean update(Carrier carrier) {
        int affectedRows = executeUpdate(
                "UPDATE " + CARRIER_TABLE_NAME +
                        " SET NAME = ?, " +
                        " ADDRESS = ?" +
                        " CARRIER_TYPE = ?" +
                        " WHERE ID = ?",
                preparedStatement -> {
                    int i = 0;
                    preparedStatement.setString(++i, carrier.getName());
                    preparedStatement.setString(++i, carrier.getAddress());
                    preparedStatement.setString(++i, carrier.getCarrierType().toString());
                    preparedStatement.setLong(++i, carrier.getId());
                }
        );

        return affectedRows == 1;
    }

    @Override
    public boolean deleteById(Long id) {
        int affectedRows = executeUpdate("DELETE FROM " + CARRIER_TABLE_NAME +
                        " WHERE ID = ?",
                preparedStatement -> preparedStatement.setLong(1, id));
        return affectedRows == 1;
    }

    @Override
    public List<Carrier> getAll() {
        return QueryHelper.selectAll("SELECT * FROM " + CARRIER_TABLE_NAME,
                CarrierMapper::mapCarrier);
    }

    @Override
    public int countAll() {
        return QueryHelper.getRowsCount("SELECT COUNT (*) AS TOTAL_COUNT FROM " + CARRIER_TABLE_NAME,
                resultSet -> resultSet.getInt("TOTAL_COUNT"));
    }

    public int saveSeveralCarriers(List<Carrier> carriers) throws SQLException {
        boolean autoCommitState = true;
        Connection connection = null;
        int affectedRows = 0;

        try {
            connection = TransportationsConnectionPool.getInstance().getConnection();
            autoCommitState = connection.getAutoCommit();
            connection.setAutoCommit(false);

            for (Carrier carrier : carriers) {
                carrier.setId(IdGenerator.generateId());
                affectedRows += QueryHelper.executeUpdateInConnection("INSERT INTO " + CARRIER_TABLE_NAME +
                                " (ID, NAME, ADDRESS, CARRIER_TYPE)" +
                                " VALUES (?,?,?,?)", connection,
                        preparedStatement -> {
                            int i = 0;
                            preparedStatement.setLong(++i, carrier.getId());
                            preparedStatement.setString(++i, carrier.getName());
                            preparedStatement.setString(++i, carrier.getAddress());
                            preparedStatement.setString(++i, carrier.getCarrierType().toString());
                        });
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
