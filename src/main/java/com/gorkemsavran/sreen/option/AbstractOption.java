package com.gorkemsavran.sreen.option;

public abstract class AbstractOption implements Option {

    private final Integer id;
    private final String message;

    protected AbstractOption(final Integer id, final String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return getId() != null ? getId() + "-" + message : message;
    }
}
