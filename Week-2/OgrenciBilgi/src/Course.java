public class Course {
    Teacher teacher;
    String name;
    String code;
    String prefix;
    int note;
    int noteSozlu;

    Course ( String name,String code, String prefix){
        this.name = name;
        this.code = code;
        this.prefix = prefix;
        int note = 0;
        int noteSozlu = 0;

    }
    void addTeacher(Teacher teacher){
        if (teacher.branch.equals(this.prefix)){
            this.teacher=teacher;
        }else {
            System.out.println("Öğretmen branşı dışında ders veremez!");
        }

    }
    void printTeacherInfo(){
        this.teacher.print();
    }

}
