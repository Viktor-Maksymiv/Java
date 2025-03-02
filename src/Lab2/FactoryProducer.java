package Lab2;

public class FactoryProducer {
    public static AbstractFactory getFactory() {
        if(Math.random() < 0.5) {
            return new RaptorBirdFactory();
        }
        else {
            return new NonRaptorBirdFactory();
        }
    }
}
