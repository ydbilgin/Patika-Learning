import java.util.Comparator;
public class OrderNameComparable implements Comparator<Book> {


    @Override
    public int compare(Book o1, Book o2) {
        return o1.name.compareTo(o2.name);
    }
}

