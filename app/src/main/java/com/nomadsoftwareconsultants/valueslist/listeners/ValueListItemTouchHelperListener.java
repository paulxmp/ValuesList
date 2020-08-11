package com.nomadsoftwareconsultants.valueslist.listeners;

public interface ValueListItemTouchHelperListener {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
