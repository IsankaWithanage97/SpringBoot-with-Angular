package com.example.pricecalculator.Service;

import com.example.pricecalculator.Exceptions.RecordNotFoundException;
import com.example.pricecalculator.Model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IntItemService extends PriceCalculations{
    List<Item> getAllItems();
    Item getItemById(Long id) throws RecordNotFoundException;
    void deleteItemById(Long id) throws RecordNotFoundException;
    Item createOrUpdateItem(Item entity) throws RecordNotFoundException;
}