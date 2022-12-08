import javax.print.DocFlavor;
import java.util.Random;

public class Util {
    public static void waiter(int timeSeconds) {
        try {
            Thread.sleep(timeSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

   public static String generateRandomString(int length){
        String symbol = "-/.^&*_!@%=+>)";
        String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String small_letter = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String finalString = cap_letter + small_letter +
                numbers + symbol;

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i=0; i < length; i++){
            int number = random.nextInt(76);
            sb.append(finalString.charAt(number));
        }
        return sb.toString();
    }


}

