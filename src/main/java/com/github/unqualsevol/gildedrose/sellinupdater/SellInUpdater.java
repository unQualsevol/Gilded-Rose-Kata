package com.github.unqualsevol.gildedrose.sellinupdater;

import com.github.unqualsevol.gildedrose.model.Item;

import static com.github.unqualsevol.gildedrose.qualityupdater.QualityUpdater.SULFURAS_HAND_OF_RAGNAROS;

public class SellInUpdater {

    public Item updateSellIn(Item item) {
        item.setSellIn(item.getSellIn() - ((isSulfuras(item)) ? 0 : 1));
        return item;
    }

    private boolean isSulfuras(Item item) {
        return SULFURAS_HAND_OF_RAGNAROS.equals(item.getName());
    }
}