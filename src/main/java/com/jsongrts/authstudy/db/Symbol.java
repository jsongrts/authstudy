package com.jsongrts.authstudy.db;

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
}
