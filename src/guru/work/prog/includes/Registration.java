package guru.work.prog.includes;

import guru.work.prog.interfaces.I_Include;
import guru.work.prog.models.User;

import java.io.IOException;

public class Registration extends User implements I_Include {

    public Registration(String login, String eMail, String password, String sex, double money) {
        super(login, eMail, password, sex, money);
    }

    @Override
    public void start() throws IOException {
        if (!FileWorker.checkUserInFile(getLogin(), getPassword())) {
            FileWorker.writeUserInFile(getLogin(), getEMail(), getPassword(), getSex(), getRole(), getMoney());
        }
        else {
            System.out.println("User is defined in txt!\nBlock registration -> Please change your login");
        }
    }
}
