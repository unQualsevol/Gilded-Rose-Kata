package com.github.unqualsevol.gildedrose.qualityupdater;

import com.github.unqualsevol.gildedrose.model.Item;

abstract class AbstractQualityUpdater implements QualityUpdater {

    @Override
    public Item updateQuality(Item item) {
        item.setQuality(getNewQuality(item));
        return item;
    }

    protected abstract int getNewQuality(Item item);

    protected boolean isItemExpired(Item item) {
        return item.getSellIn() < 0;
    }
}
