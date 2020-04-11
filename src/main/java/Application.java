import com.github.unqualsevol.gildedrose.GildedRose;
import com.github.unqualsevol.gildedrose.model.Item;
import com.github.unqualsevol.gildedrose.model.ItemBuilder;
import com.github.unqualsevol.gildedrose.qualityupdater.QualityUpdaterFactory;
import com.github.unqualsevol.gildedrose.sellinupdater.SellInUpdater;

import java.util.ArrayList;

public class Application {
    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("OMGHAI!");

        var items = new ArrayList<Item>();
        items.add(ItemBuilder.anItem().withName("+5 Dexterity Vest").withSellIn(10).withQuality(20).build());
        items.add(ItemBuilder.anItem().withName("Aged Brie").withSellIn(2).withQuality(0).build());
        items.add(ItemBuilder.anItem().withName("Elixir of the Mongoose").withSellIn(5).withQuality(7).build());
        items.add(ItemBuilder.anItem().withName("Sulfuras, Hand of Ragnaros").withSellIn(0).withQuality(80).build());
        items.add(ItemBuilder.anItem().withName("Backstage passes to a TAFKAL80ETC concert").withSellIn(15).withQuality(20).build());
        items.add(ItemBuilder.anItem().withName("Conjured Mana Cake").withSellIn(3).withQuality(6).build());

        GildedRose gildedRose = new GildedRose(new QualityUpdaterFactory(), new SellInUpdater());
        gildedRose.updateQuality(items);
    }
}