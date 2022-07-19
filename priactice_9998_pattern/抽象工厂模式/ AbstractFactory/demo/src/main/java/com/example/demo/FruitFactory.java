package example.demo;

/**
 * 抽象工程类
 * @param <T>
 */
public abstract class FruitFactory <T extends Fruit>{
    public abstract T getFruit();

}
