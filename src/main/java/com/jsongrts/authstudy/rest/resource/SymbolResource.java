package com.jsongrts.authstudy.rest.resource;


import com.google.gson.*;
import com.jsongrts.authstudy.AuthStudyException;
import com.jsongrts.authstudy.db.DbUtils;
import com.jsongrts.authstudy.db.Symbol;
import com.jsongrts.authstudy.db.SymbolDao;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jsong on 2/10/16.
 */
@Path("/symbols")
public class SymbolResource {
    @POST
    @Consumes("application/json")
    public Response createSymbol(InputStream ins) {
        Symbol s = _readSymbol(ins);

        Connection conn = DbUtils.getConnection();
        try {
            conn.setAutoCommit(true);
            SymbolDao.add(conn, s);
            return Response.created(URI.create("/symbols/" + s.id())).build();
        }
        catch (SQLException e) {
            throw new AuthStudyException(e);
        }
        finally {
            DbUtils.closeQuietly(conn);
        }
    }


    @GET
    @Produces("application/json")
    public String getAll() {
        Connection conn = DbUtils.getConnection();
        try {
            conn.setAutoCommit(true);
            ArrayList<Symbol> symbols = SymbolDao.getAll(conn);
            Gson gson = new GsonBuilder().setFieldNamingStrategy(new LmFieldNameingStrategy()).create();
            return gson.toJson(symbols);
        }
        catch (SQLException e) {
            throw new AuthStudyException(e);
        }
        finally {
            DbUtils.closeQuietly(conn);
        }
    }


    private Symbol _readSymbol(final InputStream ins) {
        JsonElement j = new JsonParser().parse(new InputStreamReader(ins));
        Symbol s = new Symbol();
        return s.name(j.getAsJsonObject().get("name").getAsString());
    }
}
