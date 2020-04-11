package com.github.unqualsevol.gildedrose.qualityupdater;

import com.github.unqualsevol.gildedrose.model.Item;

public interface QualityUpdater {
    String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    String AGED_BRIE = "Aged Brie";
    String CONJURED = "Conjured Mana Cake";

    Item updateQuality(Item item);
}
