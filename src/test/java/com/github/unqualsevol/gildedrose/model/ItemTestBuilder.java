package com.github.unqualsevol.gildedrose.model;

import static com.github.unqualsevol.gildedrose.model.ItemBuilder.anItem;

public class ItemTestBuilder {

    public static final String DEFAULT_NAME = "DEFAULT_NAME";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    public static ItemBuilder aStandardItem() {
        return anItem().withName(DEFAULT_NAME);
    }

    public static ItemBuilder aQualityZeroStandardItem() {
        return aStandardItem().withQuality(0);
    }

    public static ItemBuilder anExpiredStandardItem() {
        return aStandardItem().withSellIn(-1);
    }

    public static ItemBuilder anAgedBrie() {
        return anItem().withName(AGED_BRIE);
    }

    public static ItemBuilder anExpiredAgedBrie() {
        return anItem().withName(AGED_BRIE).withSellIn(-1);
    }

    public static ItemBuilder aSulfuras() {
        return anItem().withName(SULFURAS_HAND_OF_RAGNAROS).withSellIn(0).withQuality(80);
    }

    public static ItemBuilder aBackstagePass() {
        return anItem().withName(BACKSTAGE_PASSES);
    }

    public static ItemBuilder anExpiredBackstagePass() {
        return aBackstagePass().withSellIn(-1).withQuality(0);
    }
    public static ItemBuilder anQualityZeroBackstagePass() {
        return aBackstagePass().withQuality(0);
    }

    public static ItemBuilder aConjuredItem() {
        return anItem().withName("Conjured Mana Cake");
    }

    public static ItemBuilder anExpiredConjuredItem() {
        return aConjuredItem().withSellIn(-1);
    }

    public static ItemBuilder aQualityZeroConjuredItem() {
        return aConjuredItem().withQuality(0);
    }
}
