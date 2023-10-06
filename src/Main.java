import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Plant> plants = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Game Mode: ");
        String mode = sc.nextLine();
        boolean tine = false;
        if (mode.equals("Night") || mode.equals("Fog")) tine = true;
        String input;
        do {
            System.out.print("Add a plant: ");
            input = sc.nextLine();
            switch (input) {
                case "DONE":
                    break;
                case "Wall Nut":
                    plants.add(new Plant.WallNut());
                    break;
                case "Sun-shroom":
                    plants.add(new Mushroom.SunShroom(tine));
                    break;

                // add more plants here
                case "Puff-shroom":
                    plants.add(new Mushroom.PuffShroom(tine));
                    break;
                case "Doom-shroom":
                    plants.add(new Mushroom.DoomShroom(tine));
                    break;
                case "Sunflower":
                    plants.add(new Plant.Sunflower());
                    break;
                case "Twin Sunflower":
                    for (Plant i : plants) {
                        if (i instanceof Plant.Sunflower) {
                            int pos = plants.indexOf(i);
                            plants.remove(i);
                            plants.add(pos, new Plant.TwinSunflower());
                            break;
                        }
                    }
                    break;
                case "Peashooter":
                    plants.add(new Plant.Peashooter());
                    break;
                case "Squash":
                    plants.add(new Plant.Squash());
                    break;
                case "Jalapeno":
                    plants.add(new Plant.Jalapeno());
                    break;

                case "Coffee Bean":
                    Plant.CoffeeBean b = new Plant.CoffeeBean();
                    for (Plant p : plants) {
                        if (p instanceof Mushroom) {
                            if (((Mushroom) p).isAwake()) continue;
                            else {
                                ((Mushroom) p).awaken(b);
                                break;
                            }
                        }
                    }
                    break;
                case "Lily Pad":
                    plants.add(new Plant.LilyPad());
                    break;
                case "Cattail":
                    for (Plant j : plants) {
                        if (j instanceof Plant.LilyPad) {
                            int index = plants.indexOf(j);
                            plants.remove(j);
                            plants.add(index, new Plant.Cattail());
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println(input + " is not a plant");
            }
        } while (!input.equals("DONE"));

        do {
            System.out.print("Do something: ");
            input = sc.nextLine();
            switch (input) {
                case "DONE":
                    break;
                case "Produce Sun":
                    // add implementation here
                    int ctr = 0, sum = 0;
                    for (Plant p : plants) {
                        if (p instanceof SunProducer) {
                            ctr++;
                            sum += ((SunProducer) p).produce_sun();
                        }
                    }
                    if (ctr == 0) System.out.println("You have no sun producers");
                    else System.out.println(ctr + " sun producers gather " + sum + " suns");
                    break;
                case "Attack":
                    // add implementation here
                    ctr = 0;
                    sum = 0;
                    for (Plant p : plants) {
                        if (p instanceof Attacker) {
                            if (p.isAlive()) {
                                ctr++;
                                sum += ((Attacker) p).attack();
                            }
                        }
                    }
                    if (ctr == 0) System.out.println("You have no attackers");
                    else System.out.println(ctr + " attackers dealing " + sum + " damage");
                    break;
                // add more cases here
                case "Instant Kill Status":
                    ctr = 0;
                    for (Plant p : plants) {
                        if (p instanceof InstantKiller) {
                            if (p.isAlive()) {
                                ctr++;
                                if (((InstantKiller) p).killType() == InstantKiller.instant) System.out.println(p.name
                                        + " can kill instantly");
                                else System.out.println(p.name + " can kill on contact");
                            }
                        }
                    }
                    if (ctr == 0) System.out.println("You have no plants which can kill instantly");
                    break;
                case "Attacker Status":
                    ctr = 0;
                    for (Plant p : plants) {
                        if (p instanceof Attacker) {
                            if (p.isAlive()) {
                                ctr++;
                                if (((Attacker) p).rangeType() == Attacker.singleLine) System.out.println(p.name
                                        + " can attack on a single line");
                                else if (((Attacker) p).rangeType() == Attacker.limited) System.out.println(p.name
                                        + " can attack only when enemy is nearby");
                                else if (((Attacker) p).rangeType() == Attacker.aoe) System.out.println(p.name + " can attack using area - of - effect");
                                else System.out.println(p.name + " can attack any enemies from anywhere");
                            }
                        }
                    }
                    if (ctr == 0) System.out.println("You have no attackers");
                    break;
                case "Sort by HP":
                    Collections.sort(plants, new Plant.PlantHPComparator());
                    for (Plant p : plants) {
                        System.out.println(p);
                    }
                    break;
                case "Sort by Name":
                    Collections.sort(plants, new Plant.PlantNameComparator());
                    for (Plant p : plants) {
                        System.out.println(p);
                    }
                    break;
                case "Sort by Sun Cost":
                    Collections.sort(plants, new Plant.PlantCostComparator());
                    for (Plant p : plants) {
                        System.out.println(p);
                    }
                    break;
                default:
                    System.out.println("Unknown action: " + input);
            }
        } while (!input.equals("DONE"));
    }
}