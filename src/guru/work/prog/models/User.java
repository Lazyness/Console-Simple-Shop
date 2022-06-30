package guru.work.prog.models;

import guru.work.prog.info.Info;

public class User {
    private String login;
    private String eMail;
    private String password;
    private int sex;
    private String role;
    private double money;

    public User(String login, String eMail, String password, String sex, double money) {
        this.login = login;
        this.eMail = eMail;
        this.password = password;
        this.sex = setSexAlright(sex);
        this.role = login.equals("admin") && password.equals("admin") ? "admin" : "user";
        this.money = money;
        Info.printData(this.login, this.eMail, this.password, this.sex, this.role, this.money);
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        Info.printData(this.login,this.password);
    }

    public String getLogin() {
        return login;
    }

    public String getEMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    private int setSexAlright(String sex) {
        return (sex.equals("male") || sex.equals("female")) ? sex.equals("male") ? 1 : 0 : -1;
    }

    public int getSex() {
        return sex;
    }

    public String getRole() {
        return role;
    }

    public double getMoney() {
        return money;
    }
}
