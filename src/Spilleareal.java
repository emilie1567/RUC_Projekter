import java.awt.Point;
import java.util.Random;

public class Spilleareal {
    // '-' = tom plads
    private char[][] spilleareal;
    private Point spillerPosition;

    //constructor
    public Spilleareal() {
        this.spilleareal = new char[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                this.spilleareal[i][j] = '-';
            }
        }
        this.spillerPosition = new Point(0, 0);
        this.placerSpiller(0, 0);
    }

    // hjælpefunktion til at printe spillearealet
    public void printSpilleareal() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(spilleareal[i][j] + " ");
            }
            System.out.println();
        }
    }

    // lav en funktion der tager et "index" og sætter spilleren der
    public boolean placerSpiller(int x, int y) {
        // Den nye position
        Point nyPosition = new Point(spillerPosition.x + x, spillerPosition.y + y);
        // tjek om den nye position er en gyldig position
        // x < 0 -> ugyldig. x >= 20
        if (nyPosition.x < 0 || nyPosition.x >= 20
                || nyPosition.y < 0 || nyPosition.y >= 20) {
            return false;
        }

        // fjern gamle position og sætter spiller.
        spilleareal[spillerPosition.x][spillerPosition.y] = '-';
        //Hvis position er gyldig, vil vi rykke spilleren n, s, e eller w og.
        spilleareal[nyPosition.x][nyPosition.y] = 'X';


        // opdater den nuværende position
        spillerPosition = nyPosition;

        return true;
    }

    public boolean harSpillerenVundet() {
        // Tjekker om spilleren er i felt 19,19.
        if (spillerPosition.x == 19 && spillerPosition.y == 19) {
            return true;
        } else {
            return false;
        }
    }

    public void placerPits() {
        Random random = new Random();
        //"udregn" hvor mange pits der skal placeres
        int antalPits = random.nextInt(5) + 1;

        //print antalPits
        System.out.printf("antalPits = %d \n", antalPits);

        //Placer pits i en løkke (og husk at tjekke om de kan placeres der)
        for (int i = 0; i < antalPits; i++) {
            boolean uGyldigtIndex = true;
            int pitX = 0;
            int pitY = 0;

            while (uGyldigtIndex == true) {
                //"udregn" tilfældige indekser (for x og y)
                pitX = random.nextInt(20);
                pitY = random.nextInt(20);
                //tjek om indeks er gyldigt
                if (spilleareal[pitX][pitY] == 'o' || spilleareal[pitX][pitY] == 'X'
                    || (pitX == 19 && pitY == 19)) {
                    uGyldigtIndex = true;
                } else {
                    uGyldigtIndex = false;
                }
            }
            //placer en pit på de nye koordinater
            spilleareal[pitX][pitY] = 'o';
        }
    }

// restriktioner = må ikke ramme pit, mål eller spiller.




}