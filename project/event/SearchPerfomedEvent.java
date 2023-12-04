package event;

public class SearchPerfomedEvent implements Event{

    private String searchValue;

    public SearchPerfomedEvent(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getSearchValue() {
        return searchValue;
    }
}
