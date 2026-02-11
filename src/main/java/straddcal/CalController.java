package straddcal;

import java.util.Scanner;

public class CalController {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Separator sep = new Separator(sc.nextLine());
        Adder adder = new Adder(sep.split());
        System.out.println(adder.sum().toString());
    }
}
