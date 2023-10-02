public class Student {
    String name;
    String stuNo;
    String classes;
    Course c1;
    Course c2;
    Course c3;
    double average;
    boolean isPass;

    Student(String name, String stuNo, String classes, Course c1, Course c2, Course c3) {
        this.name = name;
        this.stuNo = stuNo;
        this.classes = classes;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.average = 0;
        this.isPass = false;
    }

    void addBulkExamNote(int note1, int note2, int note3) {
        if (note1 >= 0 && note1 <= 100) {
            this.c1.note = note1;
        }
        if (note2 >= 0 && note2 <= 100) {
            this.c2.note = note2;
        }
        if (note3 >= 0 && note3 <= 100) {
            this.c3.note = note3;
        }
    }

    void addBulkSozluNote(int noteSozlu1, int noteSozlu2, int noteSozlu3) {
        if (noteSozlu1 >= 0 && noteSozlu1 <= 100) {
            this.c1.noteSozlu = noteSozlu1;
        }
        if (noteSozlu2 >= 0 && noteSozlu2 <= 100) {
            this.c2.noteSozlu = noteSozlu2;
        }
        if (noteSozlu3 >= 0 && noteSozlu3 <= 100) {
            this.c3.noteSozlu = noteSozlu3;
        }
    }

    void calcAverage() {
        this.average = (((this.c1.note * 0.8) + ((this.c1.noteSozlu) * 0.20)) + ((this.c2.note * 0.8) + (this.c2.noteSozlu * 0.20)) + ((this.c3.note * 0.8) + (this.c3.noteSozlu * 0.20))) / 3.0;
    }

    boolean isCheckPass() {
        calcAverage();
        return this.average > 55;
    }

    void printNote() {
        System.out.println("=========================");
        System.out.println("Öğrenci : " + this.name);
        System.out.println("Tarih Notu : " + ((this.c1.note * 0.8) + ((this.c1.noteSozlu) * 0.20)));
        System.out.println("Biyoloji Notu : " + ((this.c2.note * 0.8) + (this.c2.noteSozlu * 0.20)));
        System.out.println("Fizik Notu : " + ((this.c3.note * 0.8) + (this.c3.noteSozlu * 0.20)));
    }

    void isPass() {
        if (this.c1.note == 0 || this.c2.note == 0 || this.c3.note == 0) {
            System.out.println("Notlar tam olarak girilmemiş");
        } else {
            this.isPass = isCheckPass();
            printNote();
            System.out.println("Ortalama : " + this.average);
            if (this.isPass) {
                System.out.println("Sınıfı Geçti. ");
            } else {
                System.out.println("Sınıfta Kaldı.");
            }
        }
    }
}
