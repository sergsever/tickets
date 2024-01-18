package org.slvov;

import java.util.Comparator;
public   class FlytimeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket left, Ticket right) {
        return Long.compare(left.flytime(), right.flytime());
    }
}


