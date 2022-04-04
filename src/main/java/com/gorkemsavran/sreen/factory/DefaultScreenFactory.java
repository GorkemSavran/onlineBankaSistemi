package com.gorkemsavran.sreen.factory;

import com.gorkemsavran.sreen.AbstractScreen;
import com.gorkemsavran.sreen.BankScreen;
import com.gorkemsavran.sreen.MainScreen;

public class DefaultScreenFactory implements ScreenFactory {

    @Override
    public MainScreen createMainScreen() {
        return new MainScreen();
    }

    @Override
    public AbstractScreen createBankScreen() {
        return new BankScreen();
    }
}
