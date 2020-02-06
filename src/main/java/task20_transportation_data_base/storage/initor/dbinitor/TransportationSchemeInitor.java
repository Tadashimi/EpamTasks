package task20_transportation_data_base.storage.initor.dbinitor;

import task20_transportation_data_base.common.solutions.utils.db.QueryHelper;

import java.sql.Connection;
import java.sql.SQLException;

public class TransportationSchemeInitor {
    private static Connection currentConnection;

    private TransportationSchemeInitor() {
    }

    public static void createTables(Connection connection) throws SQLException {
        currentConnection = connection;
        createCargoTable();
        createCarrierTable();
        createTransportationTable();
    }

    private static void createCargoTable() throws SQLException {
        String createCargoTableStatement = "CREATE TABLE IF NOT EXISTS PUBLIC.CARGO (\n" +
                "  ID BIGINT NOT NULL PRIMARY KEY,\n" +
                "  NAME VARCHAR(100),\n" +
                "  WEIGHT INT,\n" +
                "  CARGO_TYPE VARCHAR(100),\n" +
                "  STORED_TEMPERATURE INT,\n" +
                "  EXPIRATION_DATE DATE,\n" +
                "  SIZE VARCHAR(100),\n" +
                "  MATERIAL VARCHAR(100))";
        QueryHelper.executeQueryWithoutParameters(createCargoTableStatement, currentConnection);
    }

    private static void createCarrierTable() throws SQLException {
        String createCarrierTableStatement = "CREATE TABLE IF NOT EXISTS PUBLIC.CARRIER (\n" +
                "  ID BIGINT NOT NULL PRIMARY KEY,\n" +
                "  NAME VARCHAR(100),\n" +
                "  ADDRESS VARCHAR(300),\n" +
                "  CARRIER_TYPE VARCHAR(100))";
        QueryHelper.executeQueryWithoutParameters(createCarrierTableStatement, currentConnection);
    }

    private static void createTransportationTable() throws SQLException {
        String createCarrierTableStatement = "CREATE TABLE PUBLIC.TRANSPORTATION (\n" +
                "  ID BIGINT NOT NULL PRIMARY KEY,\n" +
                "  DESCRIPTION VARCHAR(200),\n" +
                "  BILL_TO VARCHAR(200),\n" +
                "  BEGIN_DATE DATE,\n" +
                "  CARGO_ID BIGINT REFERENCES public.cargo(id),\n" +
                "  CARRIER_ID BIGINT REFERENCES public.carrier(id))";
        QueryHelper.executeQueryWithoutParameters(createCarrierTableStatement, currentConnection);
    }

    private static void dropTable(String tableName) throws SQLException {
        String dropTableStatement = "DROP TABLE" + tableName;
        QueryHelper.executeQueryWithoutParameters(dropTableStatement, currentConnection);
    }
}
