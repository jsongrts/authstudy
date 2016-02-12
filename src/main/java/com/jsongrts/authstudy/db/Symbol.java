package com.jsongrts.authstudy.db;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

/**
 * Created by jsong on 2/10/16.
 */
public class Symbol {
    private long _id;
    private String _name;

    public long id() { return _id; }
    public Symbol id(final long id) { _id = id; return this; }

    public String name() { return _name; }
    public Symbol name(final String name) { _name = name; return this; }

    public static class SymbolJsonDeserializer implements JsonDeserializer<Symbol> {
        @Override
        public Symbol deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            Symbol s = new Symbol();
            JsonObject j = json.getAsJsonObject();
            s.id(j.get("id").getAsLong()).name(j.get("name").getAsString());
            return s;
        }
    }
}
