import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Dizinin boyutu : ");
        int n = input.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0 ; i<n; i ++){
            System.out.println((i+1)  + " . Elemanı : ");
            int a = input.nextInt();
            list.add(a);
        }
        Collections.sort(list);
        System.out.println("Sıralama  = " + list);





    }
}