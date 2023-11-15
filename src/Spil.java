import java.util.Scanner;

public class Spil {
    private Spilleareal spilleareal;

    //Constructor
    public Spil() {
        // initialize spillearealet
        this.spilleareal = new Spilleareal();
    }


    public void start() {
        String input;
        Scanner scan = new Scanner(System.in);
        boolean gameOver = false;
        boolean gyldigPosition = true;

        System.out.println("Bevæg spilleren ned i 19,19 uden at træde i et pit.");
        while (gameOver != true) {
            spilleareal.printSpilleareal();
            System.out.println("Vælg retning - w (op), a (venstre), s (ned) eller d (højre).");

            input = scan.nextLine(); //læser indtil "\n" = newline eller enter. Kalder placerSpiller som
            //er en metode på spilleareal.
            switch(input) {
                case "w":
                    //gyldigPosition bestemmer om vi skal placere pits eller ej
                    gyldigPosition = spilleareal.placerSpiller(-1,0);
                    break;
                case "s":
                    gyldigPosition = spilleareal.placerSpiller(1,0);
                    break;
                case "d":
                    gyldigPosition = spilleareal.placerSpiller(0,1);
                    break;
                case "a":
                    gyldigPosition = spilleareal.placerSpiller(0,-1);
                    break;
                default:
                    System.out.println("Du kan vælge mellem w, a, s, d.");
                    break;
            }

            //Tjek om spilleren har vundet
            if (spilleareal.harSpillerenVundet()) {
                //Sæt gameOver til at være sand
                gameOver = true;
                //print spillearealet
                spilleareal.printSpilleareal();
                //Skriv "du har vundet"
                System.out.println("Du har vundet!");
            }

            // Tjekker om spilleren bevæger sig ud fra spillearealet.
            if (gyldigPosition == true) {
                // placer pits
                spilleareal.placerPits();
            } else {
                System.out.println("Du kan ikke gå ud over brættet");
            }

            //tjek om målet er muligt, hvis ikke så sæt gameOver = true;
            if (spilleareal.erMåletMuligt() == false) {
                spilleareal.printSpilleareal();
                gameOver = true;
                System.out.println("Målet er ikke muligt!");
            }
        }
    }
}