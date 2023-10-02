public class Employee {
    String name ;
    double salary;
    int workHours;
    int hireYear;
    double vat;
    double bonus;


    Employee(String name, double salary, int workHours,int hireYear){
        this.name = name;
        this.salary=salary;
        if (workHours<40){
            System.out.println("Haftalık çalışma saatin 40'ın altında. KOVULDUN!");
        }else {
            this.workHours=workHours;
        }
        this.hireYear= hireYear;
    }
    double tax(){
        if (this.salary<=1000){
            return 0;
        }else {
            return (this.salary * 0.03);
        }
    }

    double bonus(){
        return (this.workHours-40) * 30;
    }
    double raiseSalary(){
        int workTimeByYear = 2021 - this.hireYear;
        if (workTimeByYear<10){
            return (this.salary*0.05);
        }else if (workTimeByYear >9 && workTimeByYear<20){
            return (this.salary*0.1);
        }else{
            return (this.salary* 0.15);
        }
    }
    public String toString(){
        System.out.println("Adı : " + this.name);
        System.out.println("Maaşı : " + this.salary);
        System.out.println("Çalışma saati : " + this.workHours);
        System.out.println("Başlangıç yılı : " + this.hireYear);
        System.out.println("Vergi : " + tax());
        System.out.println("Bonus : " + bonus());
        System.out.println("Maaş artışı : " + raiseSalary());
        System.out.println("Vergi ve Bonuslar ile birlikte maaş : " + (this.salary+bonus()-tax()));
        System.out.println("Toplam Maaş : " + (this.salary+bonus()-tax()+raiseSalary()));
        return null;
    }

}
