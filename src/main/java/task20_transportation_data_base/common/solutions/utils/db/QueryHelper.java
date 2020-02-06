package task20_transportation_data_base.common.solutions.utils.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryHelper {
    private static final String SELECT_ALL_FROM_TABLE_SQL = "SELECT * FROM ";
    private static final String SELECT_COUNT_FROM_TABLE_SQL = "SELECT COUNT (*) AS TOTAL_COUNT FROM ";
    private static final String SELECT_BY_ID = " WHERE ID = ?";

    private QueryHelper() {
    }

    public static void executeQueryWithoutParameters(String statement, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.execute();
    }

    public static ResultSet selectAll(String tableName, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_TABLE_SQL + tableName);
        return preparedStatement.executeQuery();
    }

    public static int getRowsCount(String tableName, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(SELECT_COUNT_FROM_TABLE_SQL + tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.getInt("TOTAL_COUNT");
    }

    public static ResultSet selectById(String tableName, Connection connection, long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_TABLE_SQL + tableName + SELECT_BY_ID);
        preparedStatement.setLong(1, id);
        return preparedStatement.executeQuery();
    }
}
