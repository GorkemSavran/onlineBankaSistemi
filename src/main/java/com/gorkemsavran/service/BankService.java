package com.gorkemsavran.service;

import com.gorkemsavran.domain.User;

public enum BankService {
    INSTANCE;

    public double deposit(User user, double amount) {
        user.setBalance(user.getBalance() + amount);
        return user.getBalance();
    }

    public double withdraw(User user, double amount) {
        if (user.getBalance() < amount) {
            user.setBalance(0D);
            System.out.println("Girilen miktar hesaptaki miktardan fazla! Bütün para çekildi");
        } else {
            user.setBalance(user.getBalance() - amount);
        }
        return user.getBalance();
    }

    public double payCreditDebt(User user, double amount) {
        Double creditDebt = user.getCreditDebt();
        if (user.getBalance() < amount) {
            System.out.println("Hesabınızda yeterli miktarda para yok!");
            return creditDebt;
        }
        double remainDebt = creditDebt - amount;
        if (remainDebt < 0) {
            user.setBalance(user.getBalance() - creditDebt);
            remainDebt = 0;
        } else {
            user.setBalance(user.getBalance() - amount);
        }
        user.setCreditDebt(remainDebt);
        return remainDebt;
    }

    public double payCreditCardDebt(User user, double amount) {
        Double creditCardDebt = user.getCreditCardDebt();
        if (user.getBalance() < amount) {
            System.out.println("Hesabınızda yeterli miktarda para yok!");
            return creditCardDebt;
        }
        double remainDebt = creditCardDebt - amount;
        if (remainDebt < 0) {
            user.setBalance(user.getBalance() - creditCardDebt);
            remainDebt = 0;
        } else {
            user.setBalance(user.getBalance() - amount);
        }
        user.setCreditCardDebt(remainDebt);
        return remainDebt;
    }

    public boolean transferMoney(User from, User to, double amount) {
        if (from.getBalance() < amount) {
            System.out.println("Hesapta yeterli miktarda para yok!");
            return false;
        }
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        return true;
    }
}
