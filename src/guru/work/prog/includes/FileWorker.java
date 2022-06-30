package guru.work.prog.includes;

import guru.work.prog.models.products.Pizza;
import guru.work.prog.models.products.Purchase;
import guru.work.prog.models.products.Shawerma;
import guru.work.prog.models.products.Sushi;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class FileWorker {

    private static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private static String readerFile(FileInputStream fin) throws IOException {
        char[] arrayTextInFile = new char[fin.available()];
        int i, index = 0;
        while ((i = fin.read()) != -1) {
            arrayTextInFile[index] = (char) i;
            index++;
        }
        return new String(arrayTextInFile);
    }

    public static ArrayList<Purchase> readerPurchase() throws IOException {
        File file = new File("./src/guru/work/prog/files/purchase.txt");
        ArrayList<Purchase> arrayList = new ArrayList<>();
        FileInputStream fin = new FileInputStream(file);
        try {
            String[] lvl1 = readerFile(fin).split("\r");
            for (String s : lvl1) {
                String[] lvl2 = s.split(" ");
                Purchase p = lvl2[4].equals("pizza") ? new Pizza(lvl2[0],
                        Double.parseDouble(lvl2[1]),
                        Double.parseDouble(lvl2[2]),
                        Double.parseDouble(lvl2[3]),
                        lvl2[4]) : lvl2[4].equals("sushi") ? new Sushi(lvl2[0],
                        Double.parseDouble(lvl2[1]),
                        Double.parseDouble(lvl2[2]),
                        Double.parseDouble(lvl2[3]),
                        lvl2[4]) : new Shawerma(lvl2[0],
                        Double.parseDouble(lvl2[1]),
                        Double.parseDouble(lvl2[2]),
                        Double.parseDouble(lvl2[3]),
                        lvl2[4]);
                arrayList.add(p);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        fin.close();
        return arrayList;
    }

    public static void clearFile(File file) throws FileNotFoundException {
        PrintWriter fos = new PrintWriter(file);
        fos.write("");
        fos.close();
    }

    public static boolean checkUserInFile(String login, String password) throws IOException {
        File file = new File("./src/guru/work/prog/files/users.txt");
        FileInputStream fin = new FileInputStream(file);
        boolean flag = false;
        try {
            String[] lvl1 = readerFile(fin).split("\r");
            for (String s : lvl1) {
                String[] lvl2 = s.split(" ");
                if (lvl2[0].trim().equals(login) && lvl2[2].equals(password)) {
                    flag = true;
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        fin.close();
        return flag;
    }

    public static String checkUserRole(String login, String password) throws IOException {
        File file = new File("./src/guru/work/prog/files/users.txt");
        FileInputStream fin = new FileInputStream(file);
        boolean flag = false;
        String role = "";
        try {
            String[] lvl1 = readerFile(fin).split("\r");
            for (String s : lvl1) {
                String[] lvl2 = s.split(" ");
                if (lvl2[0].trim().equals(login) && lvl2[2].equals(password)) {
                    flag = true;
                    role = lvl2[4];
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        fin.close();
        if (flag) return role;
        else return null;
    }

    public static double checkUserMoney(String login, String password) throws IOException {
        File file = new File("./src/guru/work/prog/files/users.txt");
        FileInputStream fin = new FileInputStream(file);
        boolean flag = false;
        String money = "";
        try {
            String[] lvl1 = readerFile(fin).split("\r");
            for (String s : lvl1) {
                String[] lvl2 = s.split(" ");
                if (lvl2[0].trim().equals(login) && lvl2[2].equals(password)) {
                    flag = true;
                    money = lvl2[5];
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        fin.close();
        if (flag) return Double.parseDouble(money);
        else return 0;
    }

    private static <T> String createStrInFile(T... data) {
        StringBuilder tempStr = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (i == data.length - 1) tempStr.append(data[i]).append('\r');
            else tempStr.append(data[i]).append(" ");
        }
        return new String(tempStr);
    }

    public static <T> void writeUserInFile(T... data) throws IOException {
        File file = new File("./src/guru/work/prog/files/users.txt");
        FileOutputStream fos = new FileOutputStream(file, true);
        try {

            byte b[] = createStrInFile(data).getBytes();

            //converting string into byte array
            fos.write(b);
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("You successful register in system: " + data[0] + " ^_^");
        writeUserInFileLog((String) data[0], "registration");
    }

    public static void writeUserInFileLog(String login, String message) throws IOException {
        File file = new File("./src/guru/work/prog/files/log.txt");
        FileOutputStream fos = new FileOutputStream(file, true);
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String s = "You " + message + " in system in: " + getCurrentDate() + ", data user: " + login + " " + "\r";
            byte b[] = s.getBytes();//converting string into byte array
            fos.write(b);
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writePurchaseInFileLog(ArrayList<Purchase> list) throws IOException {
        File file = new File("./src/guru/work/prog/files/purchase.txt");
        FileOutputStream fos = new FileOutputStream(file, true);
        try {
            Purchase p = list.get(list.size() - 1);
            String strP = p.getName() + " " + p.getWeight() + " " + p.getHeight() + " " + p.getPrice() + " " + p.getProduct();
            byte b[] = createStrInFile(strP).getBytes();
            //converting string into byte array
            fos.write(b);
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("You successful add purchase in system: " + list.get(list.size() - 1) + " ^_^");
    }

    //editor file -> you must rewrite money user/admin...
    public static void editorUserInFile(String login, String password, double resultCalc) throws IOException {
        File file = new File("./src/guru/work/prog/files/users.txt");

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line;
        StringBuilder sb = new StringBuilder();
        ArrayList<String> tempArr = new ArrayList<>();

        boolean flag = false;

        while ((line = in.readLine()) != null) {
            String[] lvl2 = line.split(" ");
            if (lvl2[0].trim().equals(login) && lvl2[2].equals(password)) {
                sb.append(line.replace(lvl2[5], String.valueOf(resultCalc))).append("\r");
                System.out.println("Susessfull " + resultCalc);
                flag = true;
            }
            if (!flag) tempArr.add(line + "\r");
            else flag = false;
        }

        // write the new String with the replaced line OVER the same file
        FileOutputStream fileOut = new FileOutputStream("./src/guru/work/prog/files/users.txt");
        fileOut.write(new String(sb).getBytes());
        for (String s : tempArr) {
            fileOut.write(s.getBytes());
        }

    }
}
