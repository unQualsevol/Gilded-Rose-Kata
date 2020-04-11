package com.github.unqualsevol.gildedrose.qualityupdater;

import com.github.unqualsevol.gildedrose.model.Item;

import static java.lang.Integer.min;

public class AgedBrieQualityUpdater extends AbstractQualityUpdater {

    @Override
    protected int getNewQuality(Item item) {
        return min(50, item.getQuality() + ((isItemExpired(item)) ? 2 : 1));
    }
}
