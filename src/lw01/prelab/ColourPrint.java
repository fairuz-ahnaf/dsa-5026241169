package lw01.prelab;

public class ColourPrint extends PrintJob {

    private static final int FIRST_TIER_PAGES = 10;
    private static final int FIRST_TIER_RATE  = 1500;
    private static final int EXTRA_PAGE_RATE  = 1000;
    private static final int SETUP_FEE        = 2000;

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int firstTier = Math.min(pages, FIRST_TIER_PAGES);
        int extra = Math.max(pages - FIRST_TIER_PAGES, 0);
        return firstTier * FIRST_TIER_RATE + extra * EXTRA_PAGE_RATE + SETUP_FEE;
    }

    @Override
    public String label() {
        return "Colour";
    }
}