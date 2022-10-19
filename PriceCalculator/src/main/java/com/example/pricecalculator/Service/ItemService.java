package com.example.pricecalculator.Service;

import com.example.pricecalculator.Exceptions.RecordNotFoundException;
import com.example.pricecalculator.Model.Item;
import com.example.pricecalculator.Model.TotalPrice;
import com.example.pricecalculator.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ItemService implements IntItemService{
    @Autowired
    ItemRepository repository;


    @Override
    public Double calculateItemPrice(Item item, Integer amount) {
        Double totalPrice = 0.0;
        try {
            Integer cartoonAmount = amount / item.getNoOfUnitsInCartoon();
            Integer remainingAmount = amount % item.getNoOfUnitsInCartoon();
            Double cartoonPrice = cartoonAmount * item.getPriceOFSingleCartoon();
            Double singleUnitPrice = Double.valueOf(remainingAmount * item.calculateSingleUnitPrice());
            totalPrice = cartoonPrice + singleUnitPrice;
            if(cartoonAmount >= item.getMinCartoonAmountToDiscount()) {
                Double discountAmount = (item.getPriceOFSingleCartoon() * item.getDiscountPrecentage());
                totalPrice = totalPrice - discountAmount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }


    public Double calculateTotalPrice(List<TotalPrice> list) {
        Double totalPrice = 0.0;
        for(TotalPrice purchasedItem: list) {
            totalPrice = totalPrice + this.calculateItemPrice(purchasedItem.getItem(), purchasedItem.getPurchasedAmount());
        }
        return totalPrice;
    }


    @Override
    public List<Item> getAllItems() {
        List<Item> itemList = repository.findAll();
        if(itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<Item>();
        }
    }


    @Override
    public Item getItemById(Long id) throws RecordNotFoundException {
        Optional<Item> item = repository.findById(id);
        if(item.isPresent()) {
            return item.get();
        } else {
            throw new RecordNotFoundException("No item record exist for given id",id);
        }
    }


    @Override
    public void deleteItemById(Long id) throws RecordNotFoundException {
        Optional<Item> item = repository.findById(id);

        if(item.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No item exist for given id",id);
        }
    }


    @Override
    public Item createOrUpdateItem(Item entity) throws RecordNotFoundException {
        if(entity.getId()!=null)  {
            Optional<Item> item = repository.findById(entity.getId());
            if(item.isPresent()) {
                Item newEntity = item.get();
                newEntity.setImageUrl(entity.getImageUrl());
                newEntity.setDiscountPrecentage(entity.getDiscountPrecentage());

                newEntity.setMinCartoonAmountToDiscount(entity.getMinCartoonAmountToDiscount());
                newEntity.setNoOfUnitsInCartoon(entity.getNoOfUnitsInCartoon());
                newEntity.setPriceOFSingleCartoon(entity.getPriceOFSingleCartoon());
                newEntity.setItemName(entity.getItemName());
                newEntity = repository.save(newEntity);
                return newEntity;
            } else {
                entity = repository.save(entity);
                return entity;
            }
        } else {
            entity = repository.save(entity);
            return entity;
        }
    }
}
