import java.util.Stack;

interface BidCommand {
    void execute();
    void undo();
}

class PlaceBidCommand implements BidCommand {
    private Auction auction;
    private Bid bid;

    public PlaceBidCommand(Auction auction, Bid bid) {
        this.auction = auction;
        this.bid = bid;
    }

    @Override
    public void execute() {
        auction.placeBid(bid);
    }

    @Override
    public void undo() {
        auction.removeBid(bid);
    }
}

class Auction {
    private String item;
    private Bid highestBid;

    public Auction(String item) {
        this.item = item;
    }

    public void placeBid(Bid bid) {
        if (highestBid == null || bid.getAmount() > highestBid.getAmount()) {
            highestBid = bid;
            System.out.println("Bid placed: " + bid.getBidder() + " bids $" + bid.getAmount() + " for " + item);
        } else {
            System.out.println("Bid too low: " + bid.getBidder() + " bids $" + bid.getAmount() + " for " + item);
        }
    }

    public void removeBid(Bid bid) {
        System.out.println("Bid removed: " + bid.getBidder() + "'s bid of $" + bid.getAmount() + " for " + item);
    }

    public Bid getHighestBid() {
        return highestBid;
    }
}

class Bid {
    private String bidder;
    private double amount;

    public Bid(String bidder, double amount) {
        this.bidder = bidder;
        this.amount = amount;
    }

    public String getBidder() {
        return bidder;
    }

    public double getAmount() {
        return amount;
    }
}

class AuctionManager {
    private Stack<BidCommand> bidHistory = new Stack<>();

    public void executeCommand(BidCommand command) {
        command.execute();
        bidHistory.push(command);
    }

    public void undoLastBid() {
        if (!bidHistory.isEmpty()) {
            BidCommand lastCommand = bidHistory.pop();
            lastCommand.undo();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Auction auction = new Auction("Vintage Car");
        AuctionManager manager = new AuctionManager();

        Bid bid1 = new Bid("User1", 1000);
        Bid bid2 = new Bid("User2", 1500);

        BidCommand bidCommand1 = new PlaceBidCommand(auction, bid1);
        BidCommand bidCommand2 = new PlaceBidCommand(auction, bid2);

        manager.executeCommand(bidCommand1);
        manager.executeCommand(bidCommand2);

        manager.undoLastBid();
    }
}
