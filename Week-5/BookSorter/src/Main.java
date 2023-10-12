import java.util.TreeSet;


public class Main {
    public static void main(String[] args) {
        TreeSet<Book> book = new TreeSet<>(new OrderNameComparable());
        book.add(new Book("AAAAA",1222,"Author 1" , 1996));
        book.add(new Book("BBBBB",111,"Author 2" , 2011));
        book.add(new Book("FFFFF",255,"Author 3" , 2000));
        book.add(new Book("CCCC",666,"Author 4" , 1888));
        book.add(new Book("DDDDD",277,"Author 5" , 1915));

        System.out.println("Kitapların alfabetik sıralaması : ");
        System.out.println();
        for (Book b : book){
            System.out.println("Kitap ismi : " + b.getName() + ", Kitap sayfası : " + b.getPageNumber() + ", Kitap Yazarı : " + b.getAuthor() + ", Çıktığı yıl : " + b.getAuthor());
        }
        System.out.println();
        System.out.println("############################");
        System.out.println();
        System.out.println("Kitapların sayfa sayısına göre sıralaması : ");
        System.out.println();
        TreeSet<Book> bookByPageNumber = new TreeSet<>(new OrderPageComparable());
        bookByPageNumber.addAll(book);
        for (Book b : bookByPageNumber){
            System.out.println("Kitap ismi : " + b.getName() + ", Kitap sayfası : " + b.getPageNumber() + ", Kitap Yazarı : " + b.getAuthor() + ", Çıktığı yıl : " + b.getAuthor());
        }




    }
}