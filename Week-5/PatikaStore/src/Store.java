import java.util.*;

public class Store {
    Scanner input = new Scanner(System.in);
    private List<Brand> brandList;
    private List<Product> phoneList;
    private List<Product> notebookList;
    Brand brand;

    public Store() {
        brandList = new ArrayList<>();
        phoneList = new ArrayList<>();
        notebookList = new ArrayList<>();
        brandList.add(new Brand("Samsung", 1));
        brandList.add(new Brand("Lenovo", 2));
        brandList.add(new Brand("Apple", 3));
        brandList.add(new Brand("Huawei", 4));
        brandList.add(new Brand("Casper", 5));
        brandList.add(new Brand("Asus", 6));
        brandList.add(new Brand("Hp", 7));
        brandList.add(new Brand("Xiaomi", 8));
        brandList.add(new Brand("Monster", 9));
        phoneList.add(new Phone(1, "Samsung GALAXY A51", 3199.0, 22, brandList.get(0), 6, 128, 6.5, 32, 4000.0, "Siyah"));
        phoneList.add(new Phone(2, "iPhone 11", 7379.0, 13, brandList.get(2), 6, 64, 6.1, 5, 3046.0, "Mavi"));
        phoneList.add(new Phone(3, "Redmi Note 10 Pro", 4012.0, 38, brandList.get(7), 12, 128, 6.5, 35, 4000.0, "Beyaz"));
        notebookList.add(new Notebook(1, "Huawei Matebook 14", 7000.0, 9, brandList.get(3), 16, 512, 14.0));
        notebookList.add(new Notebook(2, "LEVONO V14 IGL", 3699.0, 16, brandList.get(1), 8, 1024, 14.0));
        notebookList.add(new Notebook(3, "ASUS Tuf Gaming", 8199.0, 22, brandList.get(5), 32, 2048, 15.6));

    }

    public void menu() {
        System.out.println("PatikaStore Ürün Yönetim Paneli !");
        System.out.println("1 - Notebook İşlemleri");
        System.out.println("2 - Cep Telefonu İşlemleri");
        System.out.println("3 - Marka Listele");
        System.out.println("4 - Ürün Ekle");
        System.out.println("5 - Ürün çıkar");
        System.out.println("6 - Ürün filtreleme işlemi");
        System.out.println("0 - Çıkış Yap");
        System.out.println("Seçiminiz : ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                printNotebook();
                menu();
                break;
            case 2:
                printPhone();
                menu();
                break;
            case 3:
                printBrands();
                menu();
                break;
            case 4:
                createProduct();
                break;
            case 5:
                deleteProduct();
                break;
            case 6:
                printBrands();
                System.out.println("Listelenecek markayı giriniz :");
                String brandName = input.next();
                listProductsByBrand(brandName);
                menu();
                break;
            case 0:
                System.out.println("Çıkış yapıldı !");
                break;
            default:
                System.out.println("Lütfen doğru değer giriniz : ");
                menu();

        }


    }


    public void printNotebook() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.format("| %-2s | %-30s | %-10s | %-10s | %-10s | %-10s | %-10s |%n", "ID", "Ürün Adı", "Fiyat", "Marka", "Depolama", "Ekran", "RAM");
        System.out.println("----------------------------------------------------------------------------------------------------");


        for (Product product : notebookList) {
            System.out.format("| %-2d | %-30s | %-10.2f TL | %-10s | %-10d | %-10.1f | %-10d |%n",
                    product.getProductID(), product.getProductName(), product.getPrice(), product.getBrand(), product.getStorage(), product.getScreen(), product.getRam());
        }

        System.out.println("----------------------------------------------------------------------------------------------------");


    }

    public void printPhone() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.format("| %-2s | %-30s | %-10s | %-10s | %-10s | %-10s | %-10s |%n", "ID", "Ürün Adı", "Fiyat", "Marka", "Depolama", "Ekran", "RAM");
        System.out.println("----------------------------------------------------------------------------------------------------");


        for (Product product : phoneList) {
            System.out.format("| %-2d | %-30s | %-10.2f TL | %-10s | %-10d | %-10.1f | %-10d |%n",
                    product.getProductID(), product.getProductName(), product.getPrice(), product.getBrand(), product.getStorage(), product.getScreen(), product.getRam());
        }

        System.out.println("----------------------------------------------------------------------------------------------------");


    }

    public void printBrands() {
        Collections.sort(brandList, new OrderNameBrandComparable());
        System.out.println("Markalarımız");
        System.out.println("-----------------------");
        for (Brand b : brandList) {
            System.out.println(b);
        }
    }

    public Product createProduct() {
        System.out.println("Girmek istediğiniz ürünü seçiniz : ");
        System.out.println("1-Notebook");
        System.out.println("2-Cep Telefonu");
        int select = input.nextInt();
        switch (select) {
            case 1:
                addNotebook();
                menu();
                break;
            case 2:
                addPhone();
                menu();
                break;
        }
        return null;
    }

    public void addNotebook() {
        System.out.println("Eklemek istediğiniz ürünün adını giriniz : ");
        String addProductName = input.next();
        System.out.println("Ürünün fiyatını giriniz : ");
        int addProductPrice = input.nextInt();
        System.out.println("Stoğu giriniz : ");
        int addProductStock = input.nextInt();
        System.out.println("Marka ismi giriniz : ");
        String addProductBrandName = input.next();
        addProductBrandName = addProductBrandName.substring(0, 1).toUpperCase() + addProductBrandName.substring(1).toLowerCase();
        System.out.println("Marka ID'si giriniz : ");
        int addProductBrandID = input.nextInt();
        brandList.add(new Brand(addProductBrandName, addProductBrandID));
        System.out.println("Ürünün RAM'ini giriniz : ");
        int addProductRam = input.nextInt();
        System.out.println("Ürünün depolama alanını giriniz : ");
        int addProductStorage = input.nextInt();
        System.out.println("Ürünün ekran boyutunu giriniz(Inch) : ");
        double addProductScreen = input.nextDouble();
        int newID = notebookList.get(notebookList.size() - 1).getProductID() + 1;

        notebookList.add(new Notebook(newID, addProductName, addProductPrice, addProductStock, brandList.get(brandList.size() - 1), addProductRam, addProductStorage, addProductScreen));


    }

    public void addPhone() {

        System.out.println("Eklemek istediğiniz ürünün adını giriniz : ");
        String addProductName = input.next();
        System.out.println("Ürünün fiyatını giriniz : ");
        int addProductPrice = input.nextInt();
        System.out.println("Stoğu giriniz : ");
        int addProductStock = input.nextInt();
        System.out.println("Marka ismi giriniz : ");
        String addProductBrandName = input.next();
        addProductBrandName = addProductBrandName.substring(0, 1).toUpperCase() + addProductBrandName.substring(1).toLowerCase();
        System.out.println("Marka ID'si giriniz : ");
        int addProductBrandID = input.nextInt();
        brandList.add(new Brand(addProductBrandName, addProductBrandID));
        System.out.println("Ürünün RAM'ini giriniz : ");
        int addProductRam = input.nextInt();
        System.out.println("Ürünün depolama alanını giriniz : ");
        int addProductStorage = input.nextInt();
        System.out.println("Ürünün ekran boyutunu giriniz(Inch) : ");
        double addProductScreen = input.nextDouble();
        System.out.println("ürünün kamerasını giriniz : ");
        int addProductCam = input.nextInt();
        System.out.println("Ürünün bataryasını giriniz : ");
        double addProductBattery = input.nextDouble();
        System.out.println("Ürünün rengini giriniz : ");
        String addProductColor = input.next();
        int newID = phoneList.get(phoneList.size() - 1).getProductID() + 1;

        phoneList.add(new Phone(newID, addProductName, addProductPrice, addProductStock, brandList.get(brandList.size() - 1), addProductRam, addProductStorage, addProductScreen, addProductCam, addProductBattery, addProductColor));
    }

    public void deleteProduct() {
        System.out.println("Silmek istediğiniz ürün türünü seçiniz : ");
        System.out.println("1-Notebook");
        System.out.println("2-Cep Telefonu");
        System.out.println("3-Menu");
        System.out.println("4-Çıkış yap");
        int select = input.nextInt();
        switch (select) {
            case 1:
                deleteNotebook();
                deleteProduct();
                break;
            case 2:
                deletePhone();
                deleteProduct();
                break;
            case 3:
                menu();
                break;
            default:
                break;
        }

    }

    public void deleteNotebook() {
        boolean isContains = false;
        printNotebook();
        System.out.println("Silmek istediğiniz ürünün id'sini seçiniz :");
        System.out.println("Menüye dönmek için 0 tuşlayınız :");
        int removeID = input.nextInt();
        if (removeID == 0) {
            menu();
        }
        Product productToRemove = null;
        for (Product p : notebookList) {
            if (p.getProductID() == removeID) {
                productToRemove = p;
                isContains = true;

            }
        }
        if (productToRemove != null) {
            notebookList.remove(productToRemove);
            System.out.println("Ürün silindi.");
        }
        if (!isContains) {
            System.out.println("Belirttiğiniz ID'de ürün yoktur.");
            deleteNotebook();
        }


    }

    public void deletePhone() {
        boolean isContains = false;
        printPhone();
        System.out.println("Silmek istediğiniz ürünün id'sini seçiniz :");
        System.out.println("Menüye dönmek için 0 tuşlayınız :");
        int removeID = input.nextInt();
        if (removeID == 0) {
            menu();
        }
        Product productToRemove = null;
        for (Product p : phoneList) {
            if (p.getProductID() == removeID) {
                productToRemove = p;
                isContains = true;

            }
        }
        if (productToRemove != null) {
            phoneList.remove(productToRemove);
            System.out.println("Ürün silindi.");
        }
        if (!isContains) {
            System.out.println("Belirttiğiniz ID'de ürün yoktur.");
            deletePhone();
        }

    }

    public void listProductsByBrand(String brandName) {
        String standardBrandName = brandName.toUpperCase();
        System.out.println("Marka: " + standardBrandName);
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.format("| %-2s | %-30s | %-10s | %-10s | %-10s | %-10s | %-10s |%n", "ID", "Ürün Adı", "Fiyat", "Marka", "Depolama", "Ekran", "RAM");
        System.out.println("--------------------------------------------------------------------------------------------");

        for (Product product : notebookList) {
            if (product.getBrand().getName().toUpperCase().equals(standardBrandName)) {
                System.out.format("| %-2d | %-30s | %-10.2f TL | %-10s | %-10d | %-10.1f | %-10d |%n",
                        product.getProductID(), product.getProductName(), product.getPrice(), product.getBrand().getName(), product.getStorage(), product.getScreen(), product.getRam());
            }
        }

        for (Product product : phoneList) {
            if (product.getBrand().getName().toUpperCase().equals(standardBrandName)) {
                System.out.format("| %-2d | %-30s | %-10.2f TL | %-10s | %-10d | %-10.1f | %-10d |%n",
                        product.getProductID(), product.getProductName(), product.getPrice(), product.getBrand().getName(), product.getStorage(), product.getScreen(), product.getRam());
            }
        }

        System.out.println("--------------------------------------------------------------------------------------------");
    }


}
