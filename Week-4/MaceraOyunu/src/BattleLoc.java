import java.util.Random;

public class BattleLoc extends Location {
    private Monster monster;
    private String award;
    private int maxMonster;
    int selectSituation;
    Random r = new Random();

    public BattleLoc(int idLoc, Player player, String name, Monster monster, String award, int maxMonster) {
        super(idLoc, player, name);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation() {
        int monsNumber = this.randomMonsterNumber();
        System.out.println("Şu an buradasınız : " + this.getName());
        System.out.println("Dikkatli ol! Burada " + monsNumber + " tane " + this.getMonster().getName() + " yaşıyor!");
        do {
            System.out.println("1- Savaş\n2- Kaç");
            selectSituation = input.nextInt();
            if (selectSituation == 1) {
                //savaş işlemleri
                System.out.println("Savaş işlemleri olacak");
                if (combat(monsNumber)){
                    System.out.println(this.getMonster() + " tüm düşmanları yendiniz ! ");
                    return true;
                }
            }
            if (this.getPlayer().getHealth()<=0){
                System.out.println("Öldünüz ");
                return false;
            }

        } while ((1 != selectSituation) && (2 != selectSituation));

        return true;
    }

    public void afterHit() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() + " canı : " + this.getMonster().getHealth());
        System.out.println();

    }

    public boolean combat(int monsNumber) {
        for (int i = 1; i <= monsNumber; i++) {
            this.getMonster().setHealth(this.getMonster().getOrjinalHealth());
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                do {
                    System.out.println("1- Savaş\n2- Kaç");
                    selectSituation = input.nextInt();
                    if (selectSituation == 1) {
                        int randomHit = r.nextInt(2);
                        if (randomHit == 0){
                            System.out.println("Siz vurdunuz!");
                            this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                        }else {
                            if (this.getMonster().getHealth()>0){
                                System.out.println();
                                System.out.println("Canavar size vurdu ! ");
                                int monsterDamage = this.getMonster().getDamage()- this.getPlayer().getInventory().getArmor().getBlock();
                                if (monsterDamage < 0 ){
                                    monsterDamage =0;
                                }
                                this.getPlayer().setHealth(this.getPlayer().getHealth()- monsterDamage);
                                afterHit();
                            }
                        }


                    }else {
                        return false;
                    }
                } while ((1 != selectSituation) && (2 != selectSituation));

            }
            if (this.getMonster().getHealth()<this.getPlayer().getHealth()){
                System.out.println("Düşmanı yendiniz ! ");
                System.out.println(this.getMonster().getAward() + " para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getMonster().getAward());
                System.out.println("Güncel Paranız : " + this.getPlayer().getMoney());
            }else {
                return false;
            }
        }
        return true;
    }

    public void playerStats() {
        System.out.println("Oyuncu değerleri : ");
        System.out.println("---------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Blocklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para : " + this.getPlayer().getMoney());

    }

    public void monsterStats(int i) {
        System.out.println();
        System.out.println(i + "." +this.getMonster().getName() + " değerleri : ");
        System.out.println("-------------------");
        System.out.println("Sağlık : " + this.getMonster().getHealth());
        System.out.println("Hasar : " + this.getMonster().getDamage());
        System.out.println("Ödül : " + this.getMonster().getAward());

    }

    public int randomMonsterNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxMonster()) + 1;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }
}
