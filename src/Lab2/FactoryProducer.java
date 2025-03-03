package Lab2;

public class FactoryProducer {
    static AbstractFactory raptorFactory;
    static AbstractFactory nonRaptorFactory;
    public static AbstractFactory getFactory() {
        if(Math.random() < 0.5) {
            if (raptorFactory == null) {
                raptorFactory = new RaptorBirdFactory();
            }
            return raptorFactory;
        }
        else {
            if (nonRaptorFactory == null) {
                nonRaptorFactory = new NonRaptorBirdFactory();
            }
            return nonRaptorFactory;
        }
    }
}
