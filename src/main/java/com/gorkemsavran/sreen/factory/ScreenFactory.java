package com.gorkemsavran.sreen.factory;

import com.gorkemsavran.sreen.AbstractScreen;

public interface ScreenFactory {
    AbstractScreen createMainScreen();
    AbstractScreen createBankScreen();
}
