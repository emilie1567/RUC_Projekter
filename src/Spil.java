import java.util.Scanner;

public class Spil {
    private Spilleareal spilleareal;

    //Constructor
    public Spil() {
        // initialize spillearealet
        this.spilleareal = new Spilleareal();
    }


    public void start() {
        String line;
        Scanner scan = new Scanner(System.in);
        boolean gameOver = false;
        boolean gyldigPosition = true;

        while (gameOver != true) {
            spilleareal.printSpilleareal();
            System.out.println("Vælg retning (n, s, e eller w)");

            line = scan.nextLine();
            switch(line) {
                case "n":
                    gyldigPosition = spilleareal.placerSpiller(-1,0);
                    break;
                case "s":
                    gyldigPosition = spilleareal.placerSpiller(1,0);
                    break;
                case "e":
                    gyldigPosition = spilleareal.placerSpiller(0,1);
                    break;
                case "w":
                    gyldigPosition = spilleareal.placerSpiller(0,-1);
                    break;
                default:
                    System.out.println("Du kan vælge mellem n, s, e, w.");
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

        }
    }
}