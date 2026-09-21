package lw01.unguided;

public class LaptopRental extends Rental {
    private static final int DAILY_RATE = 40_000;
    private static final int SETUP_FEE = 10_000;

    public LaptopRental(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        return getDays() * DAILY_RATE + SETUP_FEE;
    }

    @Override
    public String label() {
        return "Laptop";
    }
}
