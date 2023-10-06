import java.util.Comparator;

public abstract class Plant {
    public static final int INFINITE = Integer.MAX_VALUE;
    String name;
    int hp;
    int sun_cost;

    public Plant(String name, int sun_cost) {
        this.name = name;
        this.hp = 6;
        this.sun_cost = sun_cost;
    }

    public Plant(String name, int hp, int sun_cost) {
        this.name = name;
        this.hp = hp;
        this.sun_cost = sun_cost;
    }

    public boolean isAlive() {
        // TODO implementation
        return hp > 0;
    }

    public String die() {
        // TODO implementation
        hp = 0;
        return this.name + " dies";
    }

    @Override
    public String toString() {
        // TODO implementation
        if (hp == INFINITE) return name + " " + "(" + "∞" + ")" + " - cost: " + sun_cost;
        return name + " " + "(" + hp + ")" + " - cost: " + sun_cost;
    }

    public static class Sunflower extends Plant implements SunProducer, Upgradable {
        public Sunflower() {
            super("Sunflower", 50);
        }

        @Override
        public int produce_sun() {
            System.out.println(name + " produces 25 suns");
            return 25;
        }

        @Override
        public PlantUpgrade upgrade() {
            return new PlantUpgrade() {
                @Override
                public int concurrentSunCost() {
                    return 0;
                }
            };
        }
    }

    public static class TwinSunflower extends Plant implements PlantUpgrade, SunProducer {
        public TwinSunflower() {
            super("Twin Sunflower", 250);
        }

        @Override
        public int concurrentSunCost() {
            return 50;
        }

        @Override
        public int produce_sun() {
            System.out.println(name + " produces 50 suns");
            return 50;
        }
    }

    public static class Peashooter extends Plant implements Attacker {
        public Peashooter() {
            super("Peashooter", 100);
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            return 1;
        }

        @Override
        public int rangeType() {
            return singleLine;
        }
    }

    public static class WallNut extends Plant {
        public WallNut() {
            super("Wall Nut", 25, 50);
        }
    }

    public static class Squash extends Plant implements InstantKiller, Attacker {
        public Squash() {
            super("Squash", INFINITE, 50);
        }

        @Override
        public int killType() {
            return close;
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            System.out.println(die());
            return 3;
        }

        @Override
        public int rangeType() {
            return limited;
        }

        @Override
        public String die() {
            return super.die() + " while squashing zombies";
        }
    }

    public static class Jalapeno extends Plant implements InstantKiller, Attacker {
        public Jalapeno() {
            super("Jalapeno", INFINITE, 125);
        }

        @Override
        public int killType() {
            return instant;
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            System.out.println(die());
            return 5;
        }

        @Override
        public int rangeType() {
            return singleLine;
        }

        @Override
        public String die() {
            return super.die() + " while exploding";
        }
    }

    public static class CoffeeBean extends Plant {
        public CoffeeBean() {
            super("Coffee Bean", INFINITE, 75);
        }
    }

    public static class LilyPad extends Plant implements Upgradable {
        public LilyPad() {
            super("Lily Pad", 25);
        }

        @Override
        public PlantUpgrade upgrade() {
            return new PlantUpgrade() {
                @Override
                public int concurrentSunCost() {
                    return 25;
                }
            };
        }
    }

    public static class Cattail extends Plant implements Attacker, PlantUpgrade {
        public Cattail() {
            super("Cattail", 225);
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            return 1;
        }

        @Override
        public int rangeType() {
            return free;
        }

        @Override
        public int concurrentSunCost() {
            return 25;
        }
    }

    public static class PlantHPComparator implements Comparator<Plant> {
        @Override
        public int compare(Plant p1, Plant p2) {
            if (p1.hp < p2.hp) return 1;
            else if (p1.hp == p2.hp) {
                PlantNameComparator mama = new PlantNameComparator();
                return mama.compare(p1, p2);
            }
            return -1;
        }
    }

    public static class PlantNameComparator implements Comparator<Plant> {
        @Override
        public int compare(Plant p1, Plant p2) {
            return p1.name.compareTo(p2.name);
        }
    }

    public static class PlantCostComparator implements Comparator<Plant> {
        @Override
        public int compare(Plant p1, Plant p2) {
            if (p1.sun_cost < p2.sun_cost) return 1;
            else if (p1.sun_cost == p2.sun_cost) {
                PlantNameComparator mama = new PlantNameComparator();
                return mama.compare(p1, p2);
            }
            return -1;
        }
    }
}
