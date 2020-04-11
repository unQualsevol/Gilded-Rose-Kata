package com.github.unqualsevol.gildedrose;

import com.github.unqualsevol.gildedrose.model.Item;
import com.github.unqualsevol.gildedrose.qualityupdater.QualityUpdaterFactory;
import com.github.unqualsevol.gildedrose.sellinupdater.SellInUpdater;

import java.util.List;

import static java.util.stream.Collectors.toList;


public class GildedRose {

    private final QualityUpdaterFactory qualityControlFactory;
    private final SellInUpdater sellInUpdater;

    public GildedRose(QualityUpdaterFactory qualityControlFactory, SellInUpdater sellInUpdater) {
        this.qualityControlFactory = qualityControlFactory;
        this.sellInUpdater = sellInUpdater;
    }

    public List<Item> updateQuality(List<Item> items) {
        return items.parallelStream()
                .map(sellInUpdater::updateSellIn)
                .map(qualityControlFactory::updateQuality)
                .collect(toList());
    }

}