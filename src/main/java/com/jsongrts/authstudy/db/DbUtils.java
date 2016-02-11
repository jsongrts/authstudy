package com.jsongrts.authstudy.db;

import com.jsongrts.authstudy.AuthStudyException;

import java.sql.*;

import java.util.ArrayList;

/**
 * A db utilities class
 */
public class DbUtils {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/stockapp?user=root");
        }
        catch (Exception e) {
            throw new AuthStudyException(e);
        }
    }

    public interface IRecordLoader {
        Object load(final ResultSet rs);
    }

    public static long add(final Connection conn, final String sql, Object... args) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            _setParams(pstmt, args);
            pstmt.execute();

            return _getLong(conn, "SELECT LAST_INSERT_ID()");
        }
        catch (SQLException e) {
            throw new AuthStudyException(e);
        }
        finally {
            closeQuietly(pstmt);
        }
    }

    protected static long _getLong(final Connection conn, final String sql, Object... params) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            _setParams(pstmt, params);
            rs = pstmt.executeQuery();
            if (rs.next())
                return rs.getLong(1);
            throw new AuthStudyException("Empty result set");
        }
        catch (SQLException e) {
            throw new AuthStudyException(e);
        }
        finally {
            closeQuietly(rs);
            closeQuietly(pstmt);
        }
    }

    private static void _setParams(final PreparedStatement pstmt, Object... args) {
        try {
            int i = 1;
            for (Object arg : args) {
                if (arg instanceof Long)
                    pstmt.setLong(i, (Long) arg);
                else if (arg instanceof Integer)
                    pstmt.setInt(i, (Integer) arg);
                else if (arg instanceof Float)
                    pstmt.setFloat(i, (Float) arg);
                else if (arg instanceof Double)
                    pstmt.setDouble(i, (Double) arg);
                else if (arg instanceof String)
                    pstmt.setString(i, (String) arg);
                else
                    pstmt.setString(i, arg == null ? null : arg.toString());
                i++;
            }
        }
        catch (SQLException e) {
            throw new AuthStudyException(e);
        }
    }

    public static void closeQuietly(final PreparedStatement pstmt) {
        if (pstmt != null)
            try { pstmt.close(); } catch (SQLException ignore) {}
    }

    public static void closeQuietly(final Connection conn) {
        if (conn != null)
            try { conn.close(); } catch (SQLException ignore) {}
    }

    public static void closeQuietly(final ResultSet rs) {
        if (rs != null)
            try { rs.close(); } catch (SQLException ignore) {}
    }

    public static void update(final Connection conn, final String sql, Object... args) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            _setParams(pstmt, args);
            pstmt.execute();
        }
        catch (SQLException e) {
            throw new AuthStudyException(e);
        }
        finally {
            closeQuietly(pstmt);
        }

    }

    public static Object get(final Connection conn, final IRecordLoader loader, final String sql, Object... args) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            _setParams(pstmt, args);
            rs = pstmt.executeQuery();
            if (rs.next())
                return loader.load(rs);
            else
                return null;
        }
        catch (SQLException e) {
            throw new AuthStudyException(e);
        }
        finally {
            closeQuietly(rs);
            closeQuietly(pstmt);
        }
    }

    public static ArrayList<?> getList(final Connection conn,
                                            final IRecordLoader loader,
                                            final String sql,
                                            Object... args) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            _setParams(pstmt, args);
            rs = pstmt.executeQuery();
            ArrayList<Object> arr = new ArrayList<>();
            while (rs.next())
                arr.add(loader.load(rs));
            return arr;
        }
        catch (SQLException e) {
            throw new AuthStudyException(e);
        }
        finally {
            closeQuietly(rs);
            closeQuietly(pstmt);
        }
    }
}
