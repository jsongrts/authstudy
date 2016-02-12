package com.jsonrts.authstudy;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jsongrts.authstudy.db.Symbol;
import com.jsongrts.authstudy.rest.resource.LmFieldNameingStrategy;
import org.junit.Test;

import java.util.ArrayList;


/**
 * Created by jsong on 2/11/16.
 */
public class TestGson {
    @Test
    public void testObjectToJson() {
        Symbol s = new Symbol();
        s.id(1).name("goog");

        Gson gson = new GsonBuilder().setFieldNamingStrategy(new LmFieldNameingStrategy()).create();
        System.out.println(gson.toJson(s));
    }

    @Test
    public void testJsonToObject() {
        String s = "{\"id\": 1, \"name\": \"goog\"}";

        Gson gson = new GsonBuilder().registerTypeAdapter(Symbol.class, new Symbol.SymbolJsonDeserializer()).create();
        Symbol sb = gson.fromJson(s, Symbol.class);
        System.out.printf("id=%d, name=%s\n", sb.id(), sb.name());
    }

    @Test
    public void testArrayToJson() {
        ArrayList<Symbol> symbols = new ArrayList<>();
        symbols.add(new Symbol().id(1).name("jsong"));
        symbols.add(new Symbol().id(2).name("yliu"));

        Gson gson = new GsonBuilder().setFieldNamingStrategy(new LmFieldNameingStrategy()).create();
        System.out.println(gson.toJson(symbols));
    }
}
