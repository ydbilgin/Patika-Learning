import java.util.Scanner;

public class Main {
    static boolean isAsal(int a, int b) {
        if (a == 2 && a == 3 ){
            System.out.println(a + " asal sayıdır.");
            return true;
        }
        else{
            if (b == 1){
                System.out.println(a + " asal sayıdır.");
                return true;
            }
            else if (a % b == 0){
                System.out.println(a + " asal sayı değildir.");
                return false;
            }
        }
        return isAsal(a, b - 1);

    }

    public static void main(String[] args) {
        int a, b;
        Scanner input = new Scanner(System.in);
        System.out.println("Sayı : ");
        a = input.nextInt();
        b = a - 1;

        isAsal(a, b);


    }
}