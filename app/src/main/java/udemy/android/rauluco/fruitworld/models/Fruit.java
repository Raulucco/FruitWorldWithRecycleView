package udemy.android.rauluco.fruitworld.models;


public class Fruit {
    public static final int MAXIMUN_AMOUNT = 20;
    private String name;
    private String description;
    private String amount;
    private int icon;

    public Fruit() {
    }

    public Fruit(String name, String description, String amount, int icon) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.icon = icon;
    }

    public void addItem() {
        if (amount != null && !amountIsNotAllowToIncrease(this)) {
            amount = (Integer.parseInt(amount) + 1) + "";
        } else if (amount == null) {
            amount = "1";
        }
    }

    public void resetAmount() {
        amount = "0";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean amountIsNotAllowToIncrease(Fruit f) {
        return f.getAmount().equals(Fruit.MAXIMUN_AMOUNT + "");
    }
}
