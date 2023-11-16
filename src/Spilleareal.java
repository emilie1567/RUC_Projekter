//
import java.awt.Point;
import java.util.Random;

public class Spilleareal {

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
    public int placerSpiller(int x, int y) {
        // Den nye position
        Point nyPosition = new Point(spillerPosition.x + x, spillerPosition.y + y);
        // Tjekker om den nye position er en gyldig position ved brug af if statement.
        // x < 0 -> ugyldig. x >= 20. Hvis det er gyldigt, returner 0.
        if (nyPosition.x < 0 || nyPosition.x >= 20
                || nyPosition.y < 0 || nyPosition.y >= 20) {
            return 0;
        }

        // hvis spilleren træder i et pit returneres 2.
        if (spilleareal[nyPosition.x][nyPosition.y] == 'o') {
            spilleareal[spillerPosition.x][spillerPosition.y] = '-';
            spilleareal[nyPosition.x][nyPosition.y] = 'Y';

            return 2;
        }

        // fjern gamle position og sætter spiller.
        spilleareal[spillerPosition.x][spillerPosition.y] = '-';
        //Hvis position er gyldig, vil vi rykke spilleren w, a, s eller d og returnere
        // 1.
        spilleareal[nyPosition.x][nyPosition.y] = 'X';


        // opdater den nuværende position
        spillerPosition = nyPosition;

        return 1;
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


        //Placer pits i en løkke (og husk at tjekke om de kan placeres der)
        for (int i = 0; i < antalPits; i++) {
            boolean uGyldigtIndex = true;
            int pitX = 0;
            int pitY = 0;

            while (uGyldigtIndex == true) {
                //"udregn" tilfældige indekser (for x og y). Genererer tilfældigt tal mellem 0-19
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



    public boolean erMåletMuligt() {
        //lav 20x20 array som fortæller om du har været på et index før
        boolean[][] besøgt = new boolean[20][20];
        //kald hjælpefunktion der udfører rekursion
        return hjaelpeFunktion(spillerPosition.x, spillerPosition.y, besøgt);
    }

    private boolean hjaelpeFunktion(int x, int y, boolean[][] besøgt) {
        //basecase (målet er nået)
        if (x == 19 && y == 19) {
            return true;
        }

        //basecase (ude af brættet)
        if (x > 19 || x < 0 || y > 19 || y < 0) {
            return false;
        }

        //basecase (pit)
        if (spilleareal[x][y] == 'o' || besøgt[x][y]) {
            return false;
        }

        //sætter om feltet allerede er blevet itereret.
        besøgt[x][y] = true;

        //recursive step
        //tjek naboer:
        boolean naboW = hjaelpeFunktion(x - 1, y, besøgt);
        boolean naboS = hjaelpeFunktion(x + 1, y, besøgt);
        boolean naboD = hjaelpeFunktion(x, y + 1, besøgt);
        boolean naboA = hjaelpeFunktion(x, y - 1, besøgt);

        if (naboW || naboS || naboD || naboA) {
            //hvis en af naboerne kan komme i mål er målet muligt
            return true;
        } else {
            return false;
        }
    }
}
