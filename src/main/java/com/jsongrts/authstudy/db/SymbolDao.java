package com.jsongrts.authstudy.db;

import com.jsongrts.authstudy.AuthStudyException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jsong on 2/10/16.
 */
public class SymbolDao {
    static class RecordLoader implements DbUtils.IRecordLoader {
        @Override
        public Symbol load(final ResultSet rs) {
            try {
                Symbol s = new Symbol();
                return s.id(rs.getLong(1)).name(rs.getString(2));
            }
            catch (SQLException e) {
                throw new AuthStudyException(e);
            }
        }
    }

    public static void add(final Connection conn, final Symbol symbol) throws SQLException {
        symbol.id(DbUtils.add(conn, "INSERT INTO symbol (name) value (?)", symbol.name()));
    }

    public static void delete(final Connection conn, final long id) throws SQLException {
        DbUtils.update(conn, "DELETE FROM symbol where id=?", id);
    }

    public static Symbol get(final Connection conn, final long id) throws SQLException {
        return (Symbol) DbUtils.get(conn, new RecordLoader(), "SELECT id, name FROM symbol WHERE id=?", id);
    }

    public static Symbol get(final Connection conn, final String name) throws SQLException {
        return (Symbol) DbUtils.get(conn, new RecordLoader(), "SELECT id, name FROM symbol WHERE name=?", name);
    }

    public static ArrayList<Symbol> getAll(final Connection conn) throws SQLException {
        return (ArrayList<Symbol>) DbUtils.getList(conn, new RecordLoader(), "SELECT id, name FROM symbol");
    }
}
