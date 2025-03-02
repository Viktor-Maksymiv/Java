package Lab2;

public interface Bird {
    public void fly();
    public String getName();
    public boolean isRaptor();
}

class Jay implements Bird {
    @Override
    public void fly() {
        System.out.println("Не хижа птаха сойка летить у небо!");
    }
    @Override
    public String getName() {
        return "Сойка";
    }
    @Override
    public boolean isRaptor() {
        return false;
    }
}

class Woodpecker implements Bird {
    @Override
    public void fly() {
        System.out.println("Не хижий птах дятел летить у небо!");
    }
    @Override
    public String getName() {
        return "Дятел";
    }
    @Override
    public boolean isRaptor() {
        return false;
    }
}

class Cuckoo implements Bird {
    @Override
    public void fly() {
        System.out.println("Не хижа птаха зозуля летить у небо!");
    }
    @Override
    public String getName() {
        return "Зозуля";
    }
    @Override
    public boolean isRaptor() {
        return false;
    }
}

class Sparrow implements Bird {
    @Override
    public void fly() {
        System.out.println("Не хижа птаха горобець летить у небо!");
    }

    @Override
    public String getName() {
        return "Горобець";
    }

    @Override
    public boolean isRaptor() {
        return false;
    }
}

class Eagle implements Bird {
    @Override
    public void fly() {
        System.out.println("Хижий птах орел летить у небо!");
    }
    @Override
    public String getName() {
        return "Орел";
    }
    @Override
    public boolean isRaptor() {
        return true;
    }
}

class Hawk implements Bird {
    @Override
    public void fly() {
        System.out.println("Хижий птах яструб летить у небо!");
    }
    @Override
    public String getName() {
        return "Яструб";
    }
    @Override
    public boolean isRaptor() {
        return true;
    }
}

class Falcon implements Bird {
    @Override
    public void fly() {
        System.out.println("Хижий птаха сокіл летить у небо!");
    }
    @Override
    public String getName() {
        return "Сокіл";
    }
    @Override
    public boolean isRaptor() {
        return true;
    }
}
