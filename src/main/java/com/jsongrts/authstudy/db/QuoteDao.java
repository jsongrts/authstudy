package com.jsongrts.authstudy.db;

import com.jsongrts.authstudy.AuthStudyException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jsong on 2/10/16.
 */
public class QuoteDao {
    static class RecordLoader implements DbUtils.IRecordLoader {
        @Override
        public Quote load(final ResultSet rs) {
            try {
                Quote q = new Quote();
                return q.id(rs.getLong(1)).symbolId(rs.getLong(2)).epochInSec(rs.getLong(3)).price(rs.getFloat(4));
            }
            catch (SQLException e) {
                throw new AuthStudyException(e);
            }
        }
    }

    public void add(final Connection conn, final Quote quote) {
        quote.id(DbUtils.add(conn, "INSERT INTO quote(symbol_id, date_in_epoch, price",
                quote.symbolId(), quote.epochInSec(), quote.price()));
    }

    public ArrayList<Quote> getBySymbolAndDateRange(final Connection conn,
                                                    final long symbolId,
                                                    final long startEpochInSec,
                                                    final long endEpochInSec) {
        return (ArrayList<Quote>) DbUtils.getList(conn, new RecordLoader(),
                "SELECT id, symbol_id, date_in_epoch, price FROM quote WHERE symbol_id=? AND date_in_epoch >= ? AND date_in_epoch < ?",
                symbolId, startEpochInSec, endEpochInSec);
    }
}
