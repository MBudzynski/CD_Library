package menu;

import java.util.Scanner;

/* Scanner jest klasa finalną, nie można zamockować, wraper pozwoli na mockowanie na potrzeby testów*/
public class CustomScanner {

    private final Scanner scanner = new Scanner(System.in);

    public String nextLine() {
        return scanner.nextLine();
    }
}
