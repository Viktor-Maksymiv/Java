package Lab2;

import static Lab2.FactoryProducer.getFactory;

public class Lab2 {
    public static void main(String[] args) {
        String[] nonRaptorBirds = {"Сойка", "Дятел", "Зозуля", "Горобець"};
        String[] raptorBirds = {"Орел", "Яструб", "Сокіл"};
        AbstractFactory factory;
        Bird[] birds = new Bird[20];

        for (int i = 0; i < birds.length; i++) {

            factory = getFactory();

            if(factory instanceof RaptorBirdFactory) {
                birds[i] = factory.getBird(raptorBirds[(int) ((Math.random() * 100) % raptorBirds.length)]);
            }
            else {
                birds[i] = factory.getBird(nonRaptorBirds[(int) ((Math.random() * 100) % nonRaptorBirds.length)]);
            }

            birds[i].fly();

            if(birds[i].isRaptor()) {
                System.out.println("Усі нехижі птахи ховаються і сідають на землю!");
                if ( i > 0) {
                    for (int j = i - 1; !(birds[j].isRaptor()); j--) {
                        System.out.printf("нехижа птаха %s сховалась і приземлилась %n", birds[j].getName());
                    }
                }
            }

            System.out.println("----------------------------");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
