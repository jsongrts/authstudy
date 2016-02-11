package com.jsongrts.authstudy;

import com.jsongrts.authstudy.rest.resource.SymbolResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jsong on 2/10/16.
 */
@ApplicationPath("/service")
public class StockApplication extends Application {
    private final static Set<Class<?>> _empty = new HashSet<Class<?>>();
    private final Set<Object> _singletons = new HashSet<Object>();

    public StockApplication() {
        _singletons.add(new SymbolResource());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return _empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return _singletons;
    }
}
