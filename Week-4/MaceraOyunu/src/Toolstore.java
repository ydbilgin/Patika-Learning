public class Toolstore extends NormalLoc {
    public Toolstore(Player player) {
        super(2, player, "Mağaza\t-->\tSilah ve Zırh satın alabilirsiniz ! ");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Mağazaya hoşgeldiniz");
        System.out.println("1 - Silahlar" +
                "\t2 - Zırhlar" +
                "\t3 - Çıkış Yap");
        System.out.print("Seçiminiz : ");
        int selectMenu = input.nextInt();
        while (selectMenu < 1 || selectMenu > 3) {
            System.out.println("Geçersiz değer girdiniz! Tekrar giriniz : ");
            selectMenu = input.nextInt();
        }
        switch (selectMenu) {
            case 1:
                printWeapon();
                buyWeapon();
                break;
            case 2:
                printArmor();
                buyArmor();
                break;
            case 3:
                System.out.println("Bir daha bekleriz!");
                return true;
        }
        return true;
    }


    public void printWeapon() {
        System.out.println("######### Silahlar #########");
        System.out.println();
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " - " + w.getName() + "\t<Para : " + w.getPrice() + " , Hasar : " + w.getDamage());
        }
        System.out.println("0 - Geri dön");
    }

    public void buyWeapon() {
        System.out.println("Bir silah seçiniz : ");

        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length) {
            System.out.println("Geçersiz değer girdiniz! Tekrar giriniz : ");
            selectWeaponID = input.nextInt();
        }
        if (selectWeaponID!=0){
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli bakiye bulunmamaktadır ! ");
                    System.out.println();
                } else {
                    //Satın alma işlemini yapıldığı yer.
                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız.");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan bakiye : " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);

                }
            }
        }else {
            onLocation();
        }
    }

    public void printArmor() {
        System.out.println("--------Zırhlar--------");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName() +
                    "\t<Para : " + a.getPrice() + "\t, Zırh : " + a.getBlock() + " >");
        }
        System.out.println("0 - Geri dön");
    }

    public void buyArmor() {
        System.out.println("Bir zırh seçiniz : ");

        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length) {
            System.out.println("Geçersiz değer girdiniz! Tekrar giriniz : ");
            selectArmorID = input.nextInt();
        }
        if (selectArmorID != 0) {
            Armor selectArmor = Armor.getArmorObjByID(selectArmorID);
            if (selectArmor != null) {
                if (selectArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır!");
                } else {
                    System.out.println(selectArmor.getName() + " zırhını satın aldınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectArmor.getPrice());
                    this.getPlayer().getInventory().setArmor(selectArmor);
                    System.out.println("Kalan bakiye : " + this.getPlayer().getMoney());

                }
            }
        }else {
            onLocation();
        }

    }
}
