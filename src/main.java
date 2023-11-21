// Herfra kører min main fil som indeholder en instance af Spil
// klassen som jeg gemmer i variablen game. Derefter kalder jeg
// start metoden på instancen, som indeholder logikken til at
// starte og køre spillet.

public class main {
    public static void main(String[] args) {
        Spil game = new Spil();
        game.start();
    }
}