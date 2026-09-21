package lw01.unguided;

public class ProjectorRental extends Rental {
    private static final int FIRST_DAYS = 3;
    private static final int FIRST_RATE = 60_000;
    private static final int NEXT_RATE = 45_000;
    private static final int SETUP_FEE = 20_000;

    public ProjectorRental(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int d = getDays();
        int firstPart = Math.min(d, FIRST_DAYS) * FIRST_RATE;
        int extraPart = Math.max(0, d - FIRST_DAYS) * NEXT_RATE;
        return firstPart + extraPart + SETUP_FEE;
    }

    @Override
    public String label() {
        return "Projector";
    }
}

