package com.gorkemsavran.sreen;


import com.gorkemsavran.sreen.factory.DefaultScreenFactory;
import com.gorkemsavran.sreen.factory.ScreenFactory;
import com.gorkemsavran.sreen.option.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public abstract class AbstractScreen {

    protected final static ScreenFactory screenFactory = new DefaultScreenFactory();
    protected final static Scanner scanner = new Scanner(System.in);

    private final List<Option> options = new ArrayList<>();

    public final void onScreen() {
        prepareOptions();
        options.stream().map(Option::getMessage).forEach(System.out::println);
        int choice = 0;
        if (!options.isEmpty())
            choice = scanner.nextInt();
        Optional<Option> option = findById(choice);
        option.ifPresent(Option::doAction);
        clearOptions();
        if (choice != 0)
            onScreen();
    }

    private void clearOptions() {
        options.clear();
    }

    private Optional<Option> findById(final int id) {
        for (Option option : options) {
            if (option.getId() != null && option.getId().equals(id)) {
                return Optional.of(option);
            }
        }
        return Optional.empty();
    }

    protected void addOption(Option option) {
        options.add(option);
    }

    protected abstract void prepareOptions();
}
