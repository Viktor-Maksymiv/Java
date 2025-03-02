package Lab2;

public abstract class AbstractFactory {
    public abstract Bird getBird(String birdName);
}

class NonRaptorBirdFactory extends AbstractFactory {
    @Override
    public Bird getBird(String birdName) {
        if (birdName.equalsIgnoreCase("Сойка")) {
            return new Jay();
        } else if (birdName.equalsIgnoreCase("Дятел")) {
            return new Woodpecker();
        } else if (birdName.equalsIgnoreCase("Зозуля")) {
            return new Cuckoo();
        } else if (birdName.equalsIgnoreCase("Горобець")) {
            return new Sparrow();
        }
        return null;
    }
}

class RaptorBirdFactory extends AbstractFactory {
    @Override
    public Bird getBird(String birdName) {
        if (birdName.equalsIgnoreCase("Орел")) {
            return new Eagle();
        } else if (birdName.equalsIgnoreCase("Сокіл")) {
            return new Falcon();
        } else if (birdName.equalsIgnoreCase("Яструб")) {
            return new Hawk();
        }
        return null;
    }
}
