package com.gorkemsavran.sreen;

import com.gorkemsavran.domain.User;
import com.gorkemsavran.service.UserService;
import com.gorkemsavran.sreen.option.StandardOption;

public class MainScreen extends AbstractScreen {

    private String tcKimlikNo;
    private String password;

    @Override
    protected void prepareOptions() {
        addOption(new StandardOption(0, "Çıkış yap!", () -> {
        }));
        addOption(new StandardOption(1, "Giriş Yap", this::login));
        addOption(new StandardOption(2, "Kayıt Ol", this::register));
    }

    private void login() {
        System.out.print("TC Kimlik No: ");
        String tcKimlikNo = scanner.next();
        System.out.print("Şifre: ");
        String password = scanner.next();
        boolean isLoggedIn = UserService.INSTANCE.login(tcKimlikNo, password);
        if (isLoggedIn) {
            System.out.println("Başarıyla giriş yapıldı!");
            screenFactory.createBankScreen().onScreen();
        } else
            System.out.println("Giriş yaparken bir hata oluştu!");
    }

    private void register() {
        User user = new User();
        System.out.print("TC Kimlik No: ");
        user.setTcKimlikNo(scanner.next());
        System.out.print("Şifre: ");
        user.setPassword(scanner.next());
        System.out.print("Date of Birth: ");
        user.setDateOfBirth(scanner.next());
        user.setBalance(0D);
        user.setCreditDebt(0D);
        user.setCreditCardDebt(0D);
        boolean isUserRegistered = UserService.INSTANCE.addUser(user);
        if (isUserRegistered)
            System.out.println("Kullanıcı başarıyla kaydoldu");
        else
            System.out.println("Kullanıcı kaydolamadı! (Şifre doğum tarihi içermemeli)");
    }
}
