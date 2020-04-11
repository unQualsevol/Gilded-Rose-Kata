package com.github.unqualsevol.gildedrose.qualityupdater;

import com.github.unqualsevol.gildedrose.model.Item;

import static java.lang.Integer.min;

public class BackstageQualityUpdater extends AbstractQualityUpdater {

    @Override
    protected int getNewQuality(Item item) {
        if (isItemExpired(item)) {
            return 0;
        }
        int delta = 1;
        if (item.getSellIn() <= 11) {
            delta++;
        }
        if (item.getSellIn() <= 6) {
            delta++;
        }
        return min(50, item.getQuality() + delta);
    }
}
