package com.gorkemsavran.sreen.option;

public class StandardOption extends AbstractOption {

    private final Runnable action;

    public StandardOption(final Integer id, final String message, final Runnable action) {
        super(id, message);
        this.action = action;
    }

    @Override
    public void doAction() {
        action.run();
    }
}
