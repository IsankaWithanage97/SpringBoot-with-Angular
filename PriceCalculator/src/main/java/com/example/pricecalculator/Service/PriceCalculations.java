package com.example.pricecalculator.Service;

import com.example.pricecalculator.Model.Item;
import com.example.pricecalculator.Model.TotalPrice;

import java.util.List;

public interface PriceCalculations {
    Double calculateItemPrice(Item item, Integer amount);
    Double calculateTotalPrice(List<TotalPrice> list);
}
