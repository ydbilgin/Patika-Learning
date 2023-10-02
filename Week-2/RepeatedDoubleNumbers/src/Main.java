import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] list1 = {3, 7, 3, 3, 2, 9, 10, 21, 1, 33, 9, 1,2,2,2,4,4,4,6,6,6};
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> checkList = new ArrayList<>();
        list.addAll(Arrays.asList(list1));
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if ((i != j) && (!checkList.contains(list.get(i))) && (list.get(i) == list.get(j))) {
                    if (list.get(i)%2==0){
                        checkList.add(list.get(i));
                    }


                }
            }
        }
        if (checkList.size()==0){
            System.out.println("Kendini tekrar eden çift sayı bulunamamıştır!");
        }else {
            System.out.println("Kendini tekrar eden çift sayılar : " + checkList);
        }


    }
}