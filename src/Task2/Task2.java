package Task2;

import java.io.Console;
import java.util.Arrays;


// cd src
// javac Task2/Task2.java
// java Task2/Task2
//pass 12345
public class Task2 {

    public static void main(String[] args) {
        Console cons = System.console();
        if (cons != null) {
            String login = cons.readLine("Логін:");
            char[] passwd = cons.readPassword("%s",
                    "Пароль:");
            //System.out.printf("Хеш пароля: " + hashPassword(passwd));
            System.out.printf("\nВаш логін: %s", login);
            if (checkThePass(hashPassword(passwd))){
                System.out.println("\nПароль пральний. Доступ надано\n");
            }
            else {
                System.out.println("\nПароль не правильний. У доступі відмовлено\n");
            }
            Arrays.fill(passwd, ' ');
        }
    }
    public static boolean checkThePass(int hash){
        final int STORED_HASH = 43930;
            return STORED_HASH==hash;
        }
    public static int hashPassword(char[] password) {
        int hash = 0;
        for (char c : password) {
            hash = hash * 5 + c + 7;
        }
        return hash;
    }
}
