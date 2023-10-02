import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Dizinin boyutu : ");
        int n = input.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> adetList = new ArrayList<>();
        for (int i = 0 ; i<n; i ++){
            System.out.println((i+1)  + " . Elemanı : ");
            int a = input.nextInt();
            // aldığım her input için listenin içinde olup olmadığını kontrol ediyorum.
            if(list.contains(a)){
                int index = list.indexOf(a);
                //adet listesi olan listeye a değerinin bulunduğu indexi daha önce 1 olarak atamıştık. şimdi o indexi 1 artırarak 2 yaptık. 2'yken buraya girerse 3 olarak çıkacak.
                adetList.set(index,adetList.get(index)+1);
            }
            //lsitenin içinde değilse listeye ekleyip adet listesine 1 elemanını ekliyorum.
            else{
                list.add(a);
                adetList.add(1);
            }
        }
        System.out.println("Tekrar sayıları : ");
        for(int i = 0 ; i<list.size();i++){
            int sayi = list.get(i);
            int tekrarSayisi = adetList.get(i);
            System.out.println(sayi + " sayısı " + tekrarSayisi + " kere tekrar edildi");
        }

    }
}