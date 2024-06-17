package rs.raf.turisticki_vodic_be.dto.responses;


import java.util.List;


public class PageResponse {
    private List<?> items;
    private int lastPage;

    public PageResponse(List<?> items, int lastPage) {
        this.items = items;
        this.lastPage = lastPage;
    }

    public PageResponse(){


    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }
}
