package task20_transportation_data_base.storage.initor.dbinitor;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static task20_transportation_data_base.storage.initor.dbinitor.DbConstants.*;

public class TransportationsConnectionPool {
    private HikariConfig config = new HikariConfig();
    private HikariDataSource dataSource;


    private static final TransportationsConnectionPool INSTANCE = new TransportationsConnectionPool();

    private TransportationsConnectionPool() {
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static TransportationsConnectionPool getInstance() {
        return INSTANCE;
    }
}
