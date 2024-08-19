import java.util.ArrayList;
import java.util.List;

class AuctionFactory {
    public static Auction createAuction(String type, String item) {
        if (type.equalsIgnoreCase("Timed")) {
            return new TimedAuction(item);
        } else if (type.equalsIgnoreCase("Reserve")) {
            return new ReserveAuction(item);
        }
        return new Auction(item);
    }
}

class AuctionManagerSingleton {
    private static AuctionManagerSingleton instance;
    private List<Auction> auctions = new ArrayList<>();

    private AuctionManagerSingleton() {}

    public static AuctionManagerSingleton getInstance() {
        if (instance == null) {
            instance = new AuctionManagerSingleton();
        }
        return instance;
    }

    public void addAuction(Auction auction) {
        auctions.add(auction);
        System.out.println("Auction added for item: " + auction.getItem());
    }

    public List<Auction> getAuctions() {
        return auctions;
    }
}

class TimedAuction extends Auction {
    public TimedAuction(String item) {
        super(item);
    }
}

class ReserveAuction extends Auction {
    public ReserveAuction(String item) {
        super(item);
    }
}

public class Main {
    public static void main(String[] args) {
        AuctionManagerSingleton auctionManager = AuctionManagerSingleton.getInstance();

        Auction timedAuction = AuctionFactory.createAuction("Timed", "Painting");
        Auction reserveAuction = AuctionFactory.createAuction("Reserve", "Antique Vase");

        auctionManager.addAuction(timedAuction);
        auctionManager.addAuction(reserveAuction);
    }
}
