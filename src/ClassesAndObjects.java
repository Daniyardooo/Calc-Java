public class ClassesAndObjects {
    public static void main(String[] args) {
        Person person1 = new Person();
        person1.name = "Roman";
        person1.age = 50;
        Person person2 = new Person();
        person2.name = "Vova";
        person2.age = 20;
        int year1 = person1.calculateYearsToRetirment();
        int year2 = person2.calculateYearsToRetirment();

        System.out.println("Первому человеку до пенсии: "+year1+ "лет");
        System.out.println("Второму человеку до пенсии: "+year2+ "лет");
    }
}

class Person {
    String name;
    int age;
    int  calculateYearsToRetirment(){
        int years = 65-age;
        return years;
    }

    void speak(){
        for(int i =0; i<3; i++){
        System.out.println("My name is "+ name +", I'am "+age+" years old");}
    }
    void sayHello(){
        System.out.println("Hello!");
    }


}