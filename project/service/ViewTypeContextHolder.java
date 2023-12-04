package service;

import event.EventManager;
import event.ViewUpdatedEvent;
import view.ViewType;

public class ViewTypeContextHolder {

    private static final ViewType DEFAULT_VIEW_TYPE = ViewType.LIST;

    private static ViewType viewType = DEFAULT_VIEW_TYPE;

    public static ViewType getViewType() {
        return viewType;
    }

    public static void setViewType(ViewType newViewType) {
        System.out.println("View changed to " + newViewType);
        viewType = newViewType;
        EventManager.notify(new ViewUpdatedEvent(viewType));
    }

}
