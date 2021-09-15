package repository;

import entity.LogEntity;

import java.sql.*;
import java.util.List;

public class LogRepository {

    static Connection connection;

    public void insertLogs(List<LogEntity> logEntityList, String path) throws SQLException{
        Statement stmt;
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:"+path, "SA", "");
            stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE if not exists Log (ID VARCHAR(40) NOT NULL, START_TIMESTAMP BIGINT NOT NULL, DURATION INT NOT NULL, TYPE VARCHAR(20) NULL, HOST VARCHAR(255) NULL, long_event BIT NOT NULL);");
            int logCounter = 0;
            String SQLStatement = "INSERT INTO Log VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement prepStatement = connection.prepareStatement(SQLStatement);

            for (LogEntity logEntity : logEntityList) {
                prepStatement.setString(1, logEntity.getId());
                prepStatement.setLong(2, logEntity.getStartTimestamp());
                prepStatement.setInt(3, logEntity.getDuration());
                prepStatement.setString(4, logEntity.getType());
                prepStatement.setString(5, logEntity.getHost());
                prepStatement.setBoolean(6, logEntity.isLongEvent());
                prepStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }
}

