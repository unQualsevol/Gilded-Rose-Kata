package com.github.unqualsevol.gildedrose.qualityupdater;

import com.github.unqualsevol.gildedrose.model.Item;

import static java.lang.Integer.max;

public class StandardQualityUpdater extends AbstractQualityUpdater {

    @Override
    protected int getNewQuality(Item item) {
        return max(0, item.getQuality() - ((isItemExpired(item)) ? 2 : 1));
    }
}
