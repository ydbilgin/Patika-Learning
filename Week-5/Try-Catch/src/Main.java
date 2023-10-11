import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] arr = {1,2,3,4,5,6,7,8,9,10};

        System.out.println("Dizinin kaçıncı elemanını görmek istiyorsunuz?(1-10) ");
        int number = input.nextInt();
        try {
            System.out.println(arr[number-1]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.toString());
            System.out.println("1 ile 10 arası sayı girmediniz!");

        }



    }
}