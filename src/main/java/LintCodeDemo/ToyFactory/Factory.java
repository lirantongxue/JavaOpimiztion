package LintCodeDemo.ToyFactory;

/**
 * 玩具工厂代理
 *
 * @author v-ranli
 * @create 2016-11-09-11:17
 */

public class Factory {
    private Cat cat = new Cat();
    private Dog dog = new Dog();

    public Toy getToy(String type) {
        // Write your code here
        if (type.equals("Cat")) {
            return cat;
        } else if (type.equals("Dog")) {
            return dog;
        } else {
            return null;
        }
    }


    public static void main(String[] args) {
        Factory factory = new Factory();
        Toy toy = factory.getToy("Cat");
        toy.talk();
    }
}
