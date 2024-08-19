import java.util.ArrayList;
import java.util.List;

interface AuctionSubject {
    void registerObserver(AuctionObserver observer);
    void removeObserver(AuctionObserver observer);
    void notifyObservers();
}

class Auction implements AuctionSubject {
    private String item;
    private List<AuctionObserver> observers = new ArrayList<>();
    private Bid highestBid;

    public Auction(String item) {
        this.item = item;
    }

    @Override
    public void registerObserver(AuctionObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(AuctionObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (AuctionObserver observer : observers) {
            observer.update(highestBid);
        }
    }

    public void placeBid(Bid bid) {
        if (highestBid == null || bid.getAmount() > highestBid.getAmount()) {
            highestBid = bid;
            notifyObservers();
        }
    }

    public String getItem() {
        return item;
    }
}

interface AuctionObserver {
    void update(Bid highestBid);
}

class User implements AuctionObserver {
    private String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public void update(Bid highestBid) {
        System.out.println(username + " notified: New highest bid of $" + highestBid.getAmount() + " by " + highestBid.getBidder());
    }
}

public class Main {
    public static void main(String[] args) {
        Auction auction = new Auction("Rare Coin");

        User user1 = new User("User1");
        User user2 = new User("User2");

        auction.registerObserver(user1);
        auction.registerObserver(user2);

        auction.placeBid(new Bid("User3", 2000));
    }
}
