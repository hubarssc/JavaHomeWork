package fauna;

public class Cat extends Animal {
    private String color;

    public Cat() {
        super();
    }

    public Cat(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void sound() {
        System.out.print("Мяу! ");
    }
}
