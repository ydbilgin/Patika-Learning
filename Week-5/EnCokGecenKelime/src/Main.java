import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int maxNumberOfWord = 0;
        String maxWord = "";
        Scanner input = new Scanner(System.in);
        System.out.println("Lütfen bir metin giriniz : ");
        String text = input.nextLine();
        //Noktalama işaretlerinden arındırmak için bunu yaptım
        text = text.replaceAll("[\\p{Punct}&&[^']]+", " ");
        String [] words = text.split("\\s+");
        Map<String , Integer> numberOfSentences = new HashMap<>();

        for (String s : words){
            //tüm kelimeleri küçük harfle yazdırdım.
            s = s.toLowerCase();
            if (numberOfSentences.containsKey(s)){
                int count = numberOfSentences.get(s);
                numberOfSentences.put(s, count+1);
            }else {
                numberOfSentences.put(s , 1);
            }
        }
        for (String s : numberOfSentences.keySet()){
            if (numberOfSentences.get(s) > maxNumberOfWord){
                maxWord = s ;
                maxNumberOfWord = numberOfSentences.get(s);
            }

        }
        System.out.println("En çok geçen kelime : " + maxWord);
        System.out.println("Metinde geçtiği sayı : " + maxNumberOfWord);

    }
}