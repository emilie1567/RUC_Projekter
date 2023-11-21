// Importerer Java værktøjet Scanner som jeg skal bruge til at scanne brugerinput.
import java.util.Scanner;

// Her laver jeg en variabel der holder en instance af min Spilleareal klasse.
// Jeg initialiserer med konstruktøren spilleareal-variablen, og sætter den til
// en ny instance med new-operatoren.
public class Spil {
    private Spilleareal spilleareal;

    //Constructor
    public Spil() {
        // initialize spillearealet
        this.spilleareal = new Spilleareal();
    }

    // Her vælger jeg at bruge en while-løkke til at styre hvordan spilleren
    // kan bevæge sig rundt på spillearealet. Hvis jeg havde valgt en for-løkke
    // skulle jeg kende til hvor mange gange der skulle itereres, og dette er
    // umuligt at vide, da det er et menneske der skal spille spillet, og kan
    // gå vilkårligt rundt på spillebrættet.
    public void start() {
        String input;
        Scanner scan = new Scanner(System.in);
        boolean gameOver = false;
        int gyldigPosition = 0;

        // Så længe spillet ikke er slut (gameOver != true), skal det opdaterede
        // spilleareal printes, og spilleren skal have mulighed for at bevæge sig.
        // While-løkken fortsætter indtil gameOver = true.
        System.out.println("Bevæg spilleren ned i målet i det nederste højre hjørne uden at træde i et pit.");
        while (gameOver != true) {
            spilleareal.printSpilleareal();
            System.out.println("Vælg retning - w (op), a (venstre), s (ned) eller d (højre).");

            // Alt afhængig af hvilken w, a, s, eller d tast spilleren trykker på
            // vil placerSpiller metoden kaldes i switch statementet for at rykke
            // spilleren. I gyldigPosition variablen gemmes den opdaterede værdi
            // afhængig af placerSpiller-metoden.
            input = scan.nextLine();
            switch(input) {
                case "w":
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

            //Med et if statement tjekker jeg om spilleren har vundet og sætter
            // gameOver til true hvis det er tilfældet at spilleren står i 19, 19
            // som metoden tjekker i Spilleareal.
            if (spilleareal.harSpillerenVundet()) {
                //Sæt gameOver til at være sand
                gameOver = true;
                //print spillearealet
                spilleareal.printSpilleareal();
                //Skriv "du har vundet"
                System.out.println("Du vandt!");
            }

            // Hvis spilleren bevæger sig til en gyldig position, skal pits placeres.
            // Hvis den nye position er på et pit, skal spillet sluttes.
            // Hvis positionen hverken er i et pit eller en gyldig position, må
            // positionen være uden for brættet, og spilleren skal have besked om
            // at holde sig indenfor spillearealet.
            if (gyldigPosition == 1) {
                spilleareal.placerPits();
            } else if (gyldigPosition == 2) {
                spilleareal.printSpilleareal();
                gameOver = true;
                System.out.println("Du tabte!");
            } else {
                System.out.println("Du kan ikke gå ud over brættet.");
            }

            // Her vil jeg kalde min metode med rekursion til at tjekke om målet
            // er muligt, hvis ikke, skal spillet være forbi.
            if (spilleareal.erMåletMuligt() == false) {
                spilleareal.printSpilleareal();
                gameOver = true;
                System.out.println("Målet er ikke muligt!");
            }
        }
    }
}