import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuctionProtocol {

    public static String startAuction(UUID userUUID) {
        return "[HELLO][" + userUUID.toString() + "]";
    }

    public static String bid() {
        return "[BID][" + CurrentAuction.getInstance().getLastBidUUID().toString() + "][" + CurrentAuction.getInstance().getLastBid() + "]";
    }

    public static String bid(UUID userUUID, double bid) {
        return "[BID][" + userUUID.toString() + "][" + bid + "]";
    }

    public static String endAuction(UUID userUUID) {
        return "[ENDAUCTION][" + CurrentAuction.getInstance().getLastBidUUID().toString().equals(userUUID.toString()) + "]";
    }



    public static String[] readCommand(String command) {
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(command);

        int matchCount = 0;
        while (matcher.find()) {
            matchCount++;
        }

        String[] values = new String[matchCount];
        matcher.reset();
        int index = 0;
        while (matcher.find()) {
            values[index] = matcher.group(1);
            index++;
        }

        return values;
    }


}
