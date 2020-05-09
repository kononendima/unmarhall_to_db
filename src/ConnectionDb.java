import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionDb {

        private static HikariConfig config = new HikariConfig();
        private static HikariDataSource ds;

        static {
            config.setJdbcUrl( "jdbc:postgresql://localhost:5432/Person" );
            config.setUsername( "postgres" );
            config.setPassword( "1123" );
            config.addDataSourceProperty( "cachePrepStmts" , "true" );
            config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
            config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
            ds = new HikariDataSource( config );
        }

        private ConnectionDb(){
        }

        public static Connection getConnection() throws SQLException {
            return ds.getConnection();
        }


}
