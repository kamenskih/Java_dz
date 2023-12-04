package event;

import view.ViewType;

public class ViewUpdatedEvent implements Event{

    private ViewType viewType;

    public ViewUpdatedEvent(ViewType viewType) {
        this.viewType = viewType;
    }

    public ViewType getViewType() {
        return viewType;
    }
}
