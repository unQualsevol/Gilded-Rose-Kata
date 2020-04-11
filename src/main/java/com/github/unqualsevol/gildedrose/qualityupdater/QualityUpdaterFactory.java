package com.github.unqualsevol.gildedrose.qualityupdater;

import com.github.unqualsevol.gildedrose.model.Item;

public class QualityUpdaterFactory implements QualityUpdater {

    @Override
    public Item updateQuality(Item item) {
        return transform(item.getName()).updateQuality(item);
    }

    private QualityUpdater transform(String itemName) {
        //*
        switch (itemName) {
            case SULFURAS_HAND_OF_RAGNAROS:
                return new LegendaryQualityUpdater();
            case BACKSTAGE_PASSES:
                return new BackstageQualityUpdater();
            case AGED_BRIE:
                return new AgedBrieQualityUpdater();
            case CONJURED:
                return new ConjuredQualityUpdater();
            default:
                return new StandardQualityUpdater();
        }
        /*/
        //ready to Java 14
        return switch (itemName) {
            case SULFURAS_HAND_OF_RAGNAROS -> new LegendaryUpdatableItem(item);
            case BACKSTAGE_PASSES -> new BackstageUpdatableItem(item);
            case AGED_BRIE -> new AgedBrieUpdatableItem(item);
            case CONJURED -> new ConjuredUpdatableItem(item);
            default -> new StandardUpdatableItem(item);
        };
        //*/
    }
}