package task20_transportation_data_base.storage.initor.dbinitor;

import task20_transportation_data_base.common.solutions.utils.db.QueryHelper;

import java.sql.SQLException;

import static task20_transportation_data_base.storage.initor.dbinitor.DbConstants.*;

public class TransportationSchemeInitor {
    private TransportationSchemeInitor() {
    }

    public static void createTables() throws SQLException {
        createCargoTable();
        createCarrierTable();
        createTransportationTable();
    }

    private static void createCargoTable() throws SQLException {
        String createCargoTableStatement = "CREATE TABLE IF NOT EXISTS " +
                CARGO_TABLE_NAME +
                " ( ID BIGINT NOT NULL PRIMARY KEY," +
                "  NAME VARCHAR(100)," +
                "  WEIGHT INT," +
                "  CARGO_TYPE VARCHAR(100)," +
                "  STORED_TEMPERATURE INT," +
                "  EXPIRATION_DATE DATE," +
                "  SIZE VARCHAR(100)," +
                "  MATERIAL VARCHAR(100))";
        QueryHelper.executeQueryWithoutParameters(createCargoTableStatement);
    }

    private static void createCarrierTable() throws SQLException {
        String createCarrierTableStatement = "CREATE TABLE IF NOT EXISTS " +
                CARRIER_TABLE_NAME +
                "  (ID BIGINT NOT NULL PRIMARY KEY," +
                "  NAME VARCHAR(100)," +
                "  ADDRESS VARCHAR(300)," +
                "  CARRIER_TYPE VARCHAR(100))";
        QueryHelper.executeQueryWithoutParameters(createCarrierTableStatement);
    }

    private static void createTransportationTable() throws SQLException {
        String createCarrierTableStatement = "CREATE TABLE PUBLIC.TRANSPORTATION " +
                TRANSPORTATION_TABLE_NAME +
                "  (ID BIGINT NOT NULL PRIMARY KEY," +
                "  DESCRIPTION VARCHAR(200)," +
                "  BILL_TO VARCHAR(200)," +
                "  BEGIN_DATE DATE," +
                "  CARGO_ID BIGINT REFERENCES public.cargo(id)," +
                "  CARRIER_ID BIGINT REFERENCES public.carrier(id))";
        QueryHelper.executeQueryWithoutParameters(createCarrierTableStatement);
    }

    public static void dropTables() throws SQLException {
        dropTable(TRANSPORTATION_TABLE_NAME);
        dropTable(CARGO_TABLE_NAME);
        dropTable(CARRIER_TABLE_NAME);
    }

    public static void dropTable(String tableName) throws SQLException {
        String dropTableStatement = "DROP TABLE " + tableName;
        QueryHelper.executeQueryWithoutParameters(dropTableStatement);
    }
}
