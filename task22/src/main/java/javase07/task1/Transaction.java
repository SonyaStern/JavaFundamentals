package javase07.task1;

public class Transaction {
    private String src_card;
    private String dst_card;
    private String amount;
    private String date;

    void setSrc_card(String src_card) {
        this.src_card = src_card;
    }

    void setDst_card(String dst_card) {
        this.dst_card = dst_card;
    }
    void setAmount(String amount) {
        this.amount = amount;
    }
    void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction: " +
                "src_card " + src_card +
                ", dst_card " + dst_card +
                ", amount " + amount +
                ", date " + date + ".";
    }
}
