package guru.work.prog.models;

import guru.work.prog.exceptions.ExceptionResultCalculateInCash;

public class Cash {
    private String login;
    private String password;
    private double  resultCalculate;

    public Cash(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setResultCalculate(double resultCalculate) throws ExceptionResultCalculateInCash {
        if(resultCalculate>=0) this.resultCalculate = resultCalculate;
        else new ExceptionResultCalculateInCash("You don't have enough money to pay for the purchase");
    }

    public double getResultCalculate() {
        return resultCalculate;
    }
}
