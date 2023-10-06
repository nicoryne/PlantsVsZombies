interface Attacker {
    int singleLine = 1;
    int aoe = 2;
    int limited = 3;
    int free = 4;

    int attack();

    int rangeType();
}

interface InstantKiller {
    int instant = 1;
    int close = 2;

    int killType();
}

interface PlantUpgrade {
    int concurrentSunCost();
}

interface SunProducer {
    int produce_sun();
}

interface Upgradable {
    PlantUpgrade upgrade();
}