package com.gorkemsavran.domain;

import lombok.*;
import org.json.JSONObject;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String tcKimlikNo;
    private String password;
    private String dateOfBirth;
    private Double balance;
    private Double creditDebt;
    private Double creditCardDebt;

    public JSONObject toJson() {
        return new JSONObject(this);
    }

    public static User fromJson(JSONObject userJson) {
        return new User(
                userJson.getString("tcKimlikNo"),
                userJson.getString("password"),
                userJson.getString("dateOfBirth"),
                userJson.getDouble("balance"),
                userJson.getDouble("creditDebt"),
                userJson.getDouble("creditCardDebt")
        );
    }

    public void update(User user) {
        password = user.password;
        dateOfBirth = user.dateOfBirth;
        balance = user.balance;
        creditDebt = user.creditDebt;
        creditCardDebt = user.creditCardDebt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(tcKimlikNo, user.tcKimlikNo);
    }

    @Override
    public int hashCode() {
        return tcKimlikNo != null ? tcKimlikNo.hashCode() : 0;
    }
}
