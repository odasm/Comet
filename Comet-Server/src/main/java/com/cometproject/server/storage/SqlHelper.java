package com.cometproject.server.storage;

import com.cometproject.api.messaging.performance.QueryRequest;
import com.cometproject.server.network.NetworkManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class SqlHelper {
    private static StorageManager storage;
    private static Logger log = Logger.getLogger(SqlHelper.class.getName());

    private static Map<String, AtomicInteger> queryCounters = new ConcurrentHashMap<>();
    public static boolean queryLogEnabled = false;

    public static class QueryLog {
        public long startTime = System.currentTimeMillis();
        public String query;
    }

    public static Map<Integer, QueryLog> queryLog = new ConcurrentHashMap<>();

    public static void init(StorageManager storageEngine) {
        storage = storageEngine;
    }

    public static Connection getConnection() throws SQLException {
        return storage.getConnections().getConnection();
    }

    public static void closeSilently(Connection connection) {
        try {
            if (connection == null) {
                return;
            }
            connection.close();
        } catch (SQLException e) {
            handleSqlException(e);
        }
    }

    public static void closeSilently(ResultSet resultSet) {
        try {
            if (resultSet == null) {
                return;
            }
            resultSet.close();
        } catch (SQLException e) {
            handleSqlException(e);
        }
    }

    public static void closeSilently(PreparedStatement statement) {
        try {
            if (statement == null) {
                return;
            }

            if(queryLogEnabled && queryLog.containsKey(statement.hashCode())) {
                final QueryLog log = queryLog.get(statement.hashCode());
                final long timeTaken = (System.currentTimeMillis() - log.startTime);

                if(NetworkManager.getInstance().getMessagingClient() != null) {
                    NetworkManager.getInstance().getMessagingClient().sendMessage("com.cometproject:manager", new QueryRequest(log.query, timeTaken));
                }
                //System.out.println("[QUERY] " + log.query + " took " + timeTaken + "ms");

                queryLog.remove(statement.hashCode());
            }

            statement.close();
        } catch (SQLException e) {
            handleSqlException(e);
        }
    }

    public static void executeStatementSilently(PreparedStatement statement, boolean autoClose) {
        try {
            if (statement == null) {
                return;
            }

            statement.execute();

            if (autoClose) {
                statement.close();
            }
        } catch (SQLException e) {
            handleSqlException(e);
        }
    }

    public static PreparedStatement prepare(String query, Connection con) throws SQLException {
        return prepare(query, con, false);
    }

    public static PreparedStatement prepare(String query, Connection con, boolean returnKeys) throws SQLException {
        if (!queryCounters.containsKey(query)) {
            queryCounters.put(query, new AtomicInteger(1));
        } else {
            queryCounters.get(query).incrementAndGet();
        }

        final PreparedStatement statement = returnKeys ? con.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS) : con.prepareStatement(query);

        if(queryLogEnabled) {
            final QueryLog log = new QueryLog();
            log.query = query;

            queryLog.put(statement.hashCode(), log);
        }

        return statement;
    }

    public static void handleSqlException(SQLException e) {
        if (e.getMessage().equals("Pool has been shutdown") || e.getMessage().contains("Data too long for column")) return;
        log.error("Error while executing query", e);
    }

    public static String escapeWildcards(String s) {
        return s.replaceAll("_", "\\\\_").replaceAll("%", "\\\\%");
    }

    public static Map<String, AtomicInteger> getQueryCounters() {
        return queryCounters;
    }
}
