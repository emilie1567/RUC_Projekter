// Importerer Java værktøjet Point som jeg skal bruge til at repræsentere punkter i mit
// 2D array, og Random som jeg skal bruge til at generere pseudotilfældige tal når jeg
// senere skal placere pits.
import java.awt.Point;
import java.util.Random;


// Jeg laver mit spilleareal som en 2D tabel fremfor med printlines, da printlines
// ville være besværlige at manipulere med. Ved at bruge array of arrays, kan jeg
// nemt tilgå mine elementer. Jeg giver spilleareal typen char, da spillearealet
// kommer til at bestå af bindestreger. Jeg kunne også have givet den en anden
// type, fx String, men da jeg kun vil bruge ét tegn til at repræsentere felter,
// er det mere effektivt at bruge char.
public class Spilleareal {
    private char[][] spilleareal;
    private Point spillerPosition;

    // Her vælger jeg at bruge en konstruktør til at initialisere spilleareal-objektet
    // så det kommer til at ligne et spillebræt, og placerer spilleren i position 0,0
    // som er startfeltet.
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

    // Jeg laver en hjælpemetode til at printe spillearealet med mellemrum imellem
    // felterne med to indlejrede for-løkker.
    public void printSpilleareal() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(spilleareal[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Jeg laver her en metode der tager et "index" i tabellen (x, y) og sætter
    // spilleren på nyPosition. Derefter tjekker jeg om den nye position er
    // gyldig med et if statement og returnerer med 0 hvis den er, dvs. hvis
    // spilleren er indenfor spillearealet.
    public int placerSpiller(int x, int y) {
        Point nyPosition = new Point(spillerPosition.x + x, spillerPosition.y + y);
        if (nyPosition.x < 0 || nyPosition.x >= 20
                || nyPosition.y < 0 || nyPosition.y >= 20) {
            return 0;
        }

        // Jeg vil gerne have at spilleren bliver repræsenteret med et Y hvis
        // den træder i et pit, og at der returneres med 2.
        if (spilleareal[nyPosition.x][nyPosition.y] == 'o') {
            spilleareal[spillerPosition.x][spillerPosition.y] = '-';
            spilleareal[nyPosition.x][nyPosition.y] = 'Y';

            return 2;
        }

        // Hvis den nye position er gyldig, skal spilleren kunne bevæge sig.
        // Derfor skal den gamle position i tabellen repræsenteres med et '-'
        // igen, og den nye position opdateres med 'X'. I så fald returneres
        // med 1.
        spilleareal[spillerPosition.x][spillerPosition.y] = '-';
        spilleareal[nyPosition.x][nyPosition.y] = 'X';

        spillerPosition = nyPosition;

        return 1;
    }
    // Jeg laver en ny metode med typen boolean da jeg kun har brug for at
    // tjekke om spilleren enten har vundet eller ikke har vundet. Derfor
    // skal den tjekke, om spilleren står i felt 19, 19.
    public boolean harSpillerenVundet() {
        if (spillerPosition.x == 19 && spillerPosition.y == 19) {
            return true;
        } else {
            return false;
        }
    }

    // placerPits skal ikke returnere med noget, så jeg giver den typen
    // void. Her initialiserer jeg Random, og sætter den til at generere
    // et tilfældigt tal mellem 0 og 5, og +'er med 1 så tallet er mellem
    // 1 og 5 begge inklusive.
    public void placerPits() {
        Random random = new Random();
        int antalPits = random.nextInt(5) + 1;


        // Her placerer jeg antallet af pits på spillearealet vha. en
        // for-løkke der vha. en indlejret while-løkke, tjekker om det
        // er et gyldigt felt som pits'ne kan lande på. Hvis feltet ikke
        // er et ugyldigt index, placeres der et pit.
        for (int i = 0; i < antalPits; i++) {
            boolean uGyldigtIndex = true;
            int pitX = 0;
            int pitY = 0;

            // De tilfældige pits placeres på tilfældige felter mellem
            // 0-19 ved mindre der på feltet i forvejen er et 'o', 'X'
            // eller hvis det er felt 19, 19.
            while (uGyldigtIndex == true) {
                pitX = random.nextInt(20);
                pitY = random.nextInt(20);

                if (spilleareal[pitX][pitY] == 'o' || spilleareal[pitX][pitY] == 'X'
                        || (pitX == 19 && pitY == 19)) {
                    uGyldigtIndex = true;
                } else {
                    uGyldigtIndex = false;
                }
            }
            spilleareal[pitX][pitY] = 'o';
        }
    }


    // For at tjekke om målet er muligt at nå hen til, bruger jeg
    // rekursion. Derfor vil jeg lave en boolean 20x20 tabel der
    // fortæller om feltet allerede er blevet tjekket.
    // Den skal kalde den efterfølgende hjælpemetode (true/false)
    // som udfører selve rekursionen.
    public boolean erMåletMuligt() {
        boolean[][] besøgt = new boolean[20][20];
        return hjaelpeFunktion(spillerPosition.x, spillerPosition.y, besøgt);
    }

    // Hvis målet er nået vil jeg gerne returnere true.
    // Hvis positionen er ude for brættet, er et pit, eller allerede
    // har været besøgt, returneres false.
    private boolean hjaelpeFunktion(int x, int y, boolean[][] besøgt) {
        if (x == 19 && y == 19) {
            return true;
        }

        if (x > 19 || x < 0 || y > 19 || y < 0) {
            return false;
        }

        if (spilleareal[x][y] == 'o' || besøgt[x][y]) {
            return false;
        }

        // Hvis den nuværende position er blevet besøgt, markeres
        // den i besøgt-tabellen.
        besøgt[x][y] = true;

        // I min rekursive del vil jeg tjekke om naboerne, dvs.
        // felterne rundt om det besøgte felt, kan nå til målet
        // da der så må være en vej.
        // Tjek naboer:
        boolean naboW = hjaelpeFunktion(x - 1, y, besøgt);
        boolean naboS = hjaelpeFunktion(x + 1, y, besøgt);
        boolean naboD = hjaelpeFunktion(x, y + 1, besøgt);
        boolean naboA = hjaelpeFunktion(x, y - 1, besøgt);

        // Her tjekker jeg kaldet til de omkringliggende naboer
        if (naboW || naboS || naboD || naboA) {
            return true;
        } else {
            return false;
        }
    }
}
