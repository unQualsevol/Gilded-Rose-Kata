package com.github.unqualsevol.gildedrose.qualityupdater;

import com.github.unqualsevol.gildedrose.model.Item;

public class LegendaryQualityUpdater extends AbstractQualityUpdater {

    @Override
    protected int getNewQuality(Item item) {
        return item.getQuality();
    }

}
