package com.gorkemsavran.sreen;

import com.gorkemsavran.domain.User;
import com.gorkemsavran.service.BankService;
import com.gorkemsavran.service.UserService;
import com.gorkemsavran.sreen.option.OnlyMessageOption;
import com.gorkemsavran.sreen.option.StandardOption;

public class BankScreen extends AbstractScreen {

    User user;

    @Override
    protected void prepareOptions() {
        user = UserService.INSTANCE.getLoggedInUser();
        addOption(new OnlyMessageOption("Hoşgeldiniz " + user.getTcKimlikNo()));
        addOption(new OnlyMessageOption("Hesabınızdaki miktar: " + user.getBalance()));
        addOption(new OnlyMessageOption("Kredi borcunuz: " + user.getCreditDebt()));
        addOption(new OnlyMessageOption("Kredi kartı borcunuz: " + user.getCreditCardDebt()));
        addOption(new StandardOption(0, "Çıkış yap", UserService.INSTANCE::logOut));
        addOption(new StandardOption(1, "Para yatır", this::deposit));
        addOption(new StandardOption(2, "Para çek", this::withdraw));
        addOption(new StandardOption(3, "Kredi öde", this::payCreditDebt));
        addOption(new StandardOption(4, "Kredi kartı öde", this::payCreditCardDebt));
        addOption(new StandardOption(5, "Para gönder", this::transferMoney));
    }

    private void deposit() {
        System.out.print("Yatırmak istediğiniz miktarı giriniz: ");
        double amount = scanner.nextDouble();
        if (amount < 0) {
            System.out.println("Miktar 0 dan küçük olamaz!");
            return;
        }
        double balance = BankService.INSTANCE.deposit(user, amount);
        System.out.println("Hesabınızdaki miktar: " + balance);
    }

    private void withdraw() {
        System.out.print("Çekmek istediğiniz miktarı giriniz: ");
        double amount = scanner.nextDouble();
        if (amount < 0) {
            System.out.println("Miktar 0 dan küçük olamaz!");
            return;
        }
        double balance = BankService.INSTANCE.withdraw(user, amount);
        System.out.println("Hesabınızdaki miktar: " + balance);
    }

    private void payCreditDebt() {
        System.out.print("Ödemek istediğiniz miktarı giriniz: ");
        double amount = scanner.nextDouble();
        if (amount < 0) {
            System.out.println("Miktar 0 dan küçük olamaz!");
            return;
        }
        double remainDebt = BankService.INSTANCE.payCreditDebt(user, amount);
        System.out.println("Kalan kredi borcu: " + remainDebt);
    }

    private void payCreditCardDebt() {
        System.out.print("Ödemek istediğiniz miktarı giriniz: ");
        double amount = scanner.nextDouble();
        if (amount < 0) {
            System.out.println("Miktar 0 dan küçük olamaz!");
            return;
        }
        double remainDebt = BankService.INSTANCE.payCreditCardDebt(user, amount);
        System.out.println("Kalan kredi kartı borcu: " + remainDebt);
    }

    private void transferMoney() {
        System.out.print("Göndermek istediğiniz kişinin TC kimlik numarasını giriniz: ");
        String tcKimlikNo = scanner.next();
        System.out.print("Göndermek istediğiniz miktarı giriniz: ");
        double amount = scanner.nextDouble();
        if (amount < 0) {
            System.out.println("Miktar 0 dan küçük olamaz!");
            return;
        }
        User to = UserService.INSTANCE.getUser(tcKimlikNo);
        if (to == null) {
            System.out.println("Para gönderilmek istenen kullanıcı sistemde bulunamadı!");
            return;
        }
        boolean isTransferred = BankService.INSTANCE.transferMoney(user, to, amount);
        if (isTransferred)
            System.out.println("Gönderim başarılı!");
        else
            System.out.println("Gönderim yapılamadı!");
    }
}
