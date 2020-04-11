package com.github.unqualsevol.gildedrose;

import com.github.unqualsevol.gildedrose.model.Item;
import com.github.unqualsevol.gildedrose.qualityupdater.QualityUpdaterFactory;
import com.github.unqualsevol.gildedrose.sellinupdater.SellInUpdater;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.unqualsevol.gildedrose.model.ItemTestBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;


public class GildedRoseTest {

    private GildedRose testSubject;

    @Before
    public void setUp() {
        testSubject = new GildedRose(new QualityUpdaterFactory(), new SellInUpdater());
    }

    @Test
    public void returns_nothing_when_nothing_updated() {
        //given
        var items = List.<Item>of();
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated).isEmpty();

    }

    @Test
    public void returns_same_amount_of_items_when_updated() {
        //given
        List<Item> items = List.of(
                aStandardItem().withQuality(10).withSellIn(10).build(),
                aSulfuras().build(),
                anAgedBrie().withQuality(10).withSellIn(10).build(),
                aBackstagePass().withQuality(10).withSellIn(10).build(),
                aConjuredItem().withQuality(10).withSellIn(10).build()
        );
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated).hasSameSizeAs(items);

    }

    @Test
    public void sulfuras_never_change() {
        //given
        var items = List.of(
                aSulfuras().build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(aSulfuras().build());
    }

    @Test
    public void standard_item_decreases_by_one_sellIn_and_quality_when_standard_still_good_to_sell() {
        //given
        var items = List.of(
                aStandardItem().withSellIn(5).withQuality(10).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(aStandardItem().withSellIn(4).withQuality(9).build());

    }

    @Test
    public void standard_item_decreases_by_one_sellIn_and_by_two_quality_when_is_out_of_days() {
        //given
        var items = List.of(
                anExpiredStandardItem().withQuality(10).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(aStandardItem().withSellIn(-2).withQuality(8).build());

    }

    @Test
    public void standard_item_do_not_decreases_quality_when_quality_is_0() {
        //given
        var items = List.of(
                aQualityZeroStandardItem().withSellIn(-1).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(aStandardItem().withSellIn(-2).withQuality(0).build());
    }

    @Test
    public void aged_brie_increases_quality_when_still_good_to_sell() {
        //given
        var items = List.of(
                anAgedBrie().withSellIn(10).withQuality(10).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(anAgedBrie().withSellIn(9).withQuality(11).build());

    }

    @Test
    public void aged_brie_increases_by_2_quality_when_is_out_of_days() {
        //given
        var items = List.of(
                anExpiredAgedBrie().withQuality(10).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(anAgedBrie().withSellIn(-2).withQuality(12).build());

    }

    @Test
    public void aged_brie_quality_cannot_be_increased_over_50() {
        //given
        var items = List.of(
                anAgedBrie().withSellIn(5).withQuality(50).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(anAgedBrie().withSellIn(4).withQuality(50).build());
    }

    @Test
    public void backstage_pass_increase_quality_by_2_when_are_10_or_less_days_to_expire() {
        //given
        var items = List.of(
                aBackstagePass().withSellIn(10).withQuality(10).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(aBackstagePass().withSellIn(9).withQuality(12).build());
    }

    @Test
    public void backstage_pass_increase_quality_by_3_when_are_5_or_less_days_to_expire() {
        //given
        var items = List.of(
                aBackstagePass().withSellIn(5).withQuality(10).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(aBackstagePass().withSellIn(4).withQuality(13).build());
    }

    @Test
    public void backstage_pass_quality_cannot_be_increased_over_50() {
        //given
        var items = List.of(
                aBackstagePass().withSellIn(3).withQuality(49).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(aBackstagePass().withSellIn(2).withQuality(50).build());
    }

    @Test
    public void a_backstage_pass_quality_drops_to_zero_when_expires() {
        //given
        var items = List.of(
                aBackstagePass().withSellIn(0).withQuality(10).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(anExpiredBackstagePass().build());
    }

    @Test
    public void backstage_pass_do_not_decreases_quality_when_quality_is_0() {
        //given
        var items = List.of(
                anQualityZeroBackstagePass().withSellIn(-1).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(aBackstagePass().withSellIn(-2).withQuality(0));

    }

    @Test
    public void conjured_item_losses_quality_twice_as_fast_as_normal_items() {
        //given
        var items = List.of(
                aConjuredItem().withSellIn(10).withQuality(10).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(aConjuredItem().withSellIn(9).withQuality(8).build());
    }

    @Test
    public void expired_conjured_item_losses_quality_twice_as_fast_as_normal_items() {
        //given
        var items = List.of(
                anExpiredConjuredItem().withQuality(10).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(aConjuredItem().withSellIn(-2).withQuality(6).build());
    }

    @Test
    public void conjured_item_do_not_decreases_quality_under_zero() {
        //given
        var items = List.of(
                anExpiredConjuredItem().withQuality(2).build());
        //when
        List<Item> itemsUpdated = testSubject.updateQuality(items);
        //then
        assertThat(itemsUpdated.get(0)).isEqualToComparingFieldByField(aConjuredItem().withSellIn(-2).withQuality(0).build());

    }
}
