package guru.work.prog.includes;

import guru.work.prog.interfaces.I_Include;
import guru.work.prog.models.User;

import java.io.IOException;

public class Login extends User implements I_Include {

    public Login(String login, String password) {
        super(login, password);
    }

    @Override
    public void start() throws IOException {
        if (FileWorker.checkUserInFile(getLogin(), getPassword())) {
            System.out.println("User is defined!");
        } else {
            System.out.println("User undefined!");
        }
    }

}
