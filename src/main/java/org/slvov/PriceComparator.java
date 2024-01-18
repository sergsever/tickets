package org.slvov;

import java.util.Comparator;

public   class PriceComparator implements Comparator<Ticket>
{
    @Override
    public int compare(Ticket left, Ticket right)
    {
        return Long.compare(left.get_price(), right.get_price());
    }
}