package com.project.test.datasourceTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import com.project.datasource.MariaDBConnection;


public class MariaDBConnectionTest {

    private static Connection mockConnection;

    @BeforeAll
    public static void setUp() throws SQLException {
        mockConnection = MariaDBConnection.getConnection();
    }

    @Test
    public void testGetConnection() {
        Connection conn = MariaDBConnection.getConnection();
        assertNotNull(conn);
        assertEquals(mockConnection, conn);
    }

    @Test
    public void testTerminate() throws SQLException {
        Connection conn = MariaDBConnection.getConnection();
        MariaDBConnection.terminate();
        assertTrue(conn.isClosed());
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        mockConnection.close();
    }
}
