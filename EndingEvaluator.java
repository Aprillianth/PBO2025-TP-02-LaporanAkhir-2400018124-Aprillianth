public class EndingEvaluator {
    public static String evaluate(Player player) {
        if (player.getMoralScore() >= 3) {
            return "Ending Baik: Kamu menjadi pahlawan.";
        } else if (player.getMoralScore() >= 0) {
            return "Ending Netral: Kamu selamat.";
        } else {
            return "Ending Buruk: Pilihanmu membawa kehancuran.";
        }
    }
}
