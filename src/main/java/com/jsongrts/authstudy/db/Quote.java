package com.jsongrts.authstudy.db;

/**
 * Created by jsong on 2/10/16.
 */
public class Quote {
    private long _id;
    private long _symbolId;
    private long _epochInSec;
    private float _price;

    public long id() { return _id; }
    public long symbolId() { return _symbolId; }
    public long epochInSec() { return _epochInSec; }
    public float price() { return _price; }

    public Quote id(final long id) { _id = id; return this; }
    public Quote symbolId(final long id) { _symbolId = id; return this; }
    public Quote epochInSec(final long epochInSec) { _epochInSec = epochInSec; return this; }
    public Quote price(final float price) { _price = price; return this; }
}
