package com.jsongrts.authstudy.rest.resource;

import com.google.gson.FieldNamingStrategy;

import java.lang.reflect.Field;

/**
 * In our coding style guideline, all non-public fields should be
 * prefixed with "_". For example
 *
 *     private int _id;
 *
 * The default Gson field naming strategy will translate this field
 * as the JSON key "_id" which looks weired.
 *
 * So we provide this custom strategy to get rid of the "_" when converting
 * a field into a JSON key name
 */
public class LmFieldNameingStrategy implements FieldNamingStrategy {
    @Override
    public String translateName(Field f) {
        final String n = f.getName();
        return n.startsWith("_") ? n.substring(1) : n;
    }
}
