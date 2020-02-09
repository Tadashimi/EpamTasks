package task20_transportation_data_base.common.solutions.utils.db;

import task20_transportation_data_base.storage.initor.dbinitor.TransportationsConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryHelper {

    private QueryHelper() {
    }

    public static int executeUpdate(String sql,
                                    JdbcConsumer<PreparedStatement> psConsumer) {
        try (Connection connection = TransportationsConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            psConsumer.accept(preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int executeUpdateInConnection(String sql, Connection connection, JdbcConsumer<PreparedStatement> psConsumer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            psConsumer.accept(preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> selectAll(String sql,
                                        JdbcFunction<ResultSet, T> rsConverter) {
        try (Connection connection = TransportationsConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                T t = rsConverter.apply(resultSet);
                result.add(t);
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeQueryWithoutParameters(String statement) throws SQLException {
        try (Connection connection = TransportationsConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.execute();
        }
    }

    public static <T> List<T> select(String sql,
                                     JdbcConsumer<PreparedStatement> psConsumer,
                                     JdbcFunction<ResultSet, T> rsConverter) {
        try (Connection connection = TransportationsConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            psConsumer.accept(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                T t = rsConverter.apply(resultSet);
                result.add(t);
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T selectOne(String sql,
                                  JdbcConsumer<PreparedStatement> psConsumer,
                                  JdbcFunction<ResultSet, T> rsConverter) {
        T result = null;
        try (Connection connection = TransportationsConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            psConsumer.accept(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = rsConverter.apply(resultSet);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static int getRowsCount(String sql, JdbcFunction<ResultSet, Integer> rsConverter) {
        int result = 0;
        try (Connection con = TransportationsConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                result = rsConverter.apply(resultSet);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
