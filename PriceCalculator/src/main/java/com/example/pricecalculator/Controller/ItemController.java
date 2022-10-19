package com.example.pricecalculator.Controller;

import com.example.pricecalculator.Dto.PriceCalculationMultiple;
import com.example.pricecalculator.Dto.PriceCalculationDTO;
import com.example.pricecalculator.Exceptions.RecordNotFoundException;
import com.example.pricecalculator.Exceptions.RecordNotFoundException;
import com.example.pricecalculator.Model.Item;
import com.example.pricecalculator.Model.TotalPrice;
import com.example.pricecalculator.Service.IntItemService;
import com.example.pricecalculator.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    IntItemService service;


    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> list = service.getAllItems();
        return new ResponseEntity<List<Item>>(list, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Item entity = service.getItemById(id);
        return new ResponseEntity<Item>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteItemById(id);
        return HttpStatus.FORBIDDEN;
    }


    @PostMapping
    public ResponseEntity<Item> createOrUpdateItem(@RequestBody Item item) throws RecordNotFoundException {
        Item updated = service.createOrUpdateItem(item);
        return new ResponseEntity<Item>(updated, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/calculate_price/single/{itemId}/{amount}")
    public ResponseEntity<PriceCalculationDTO> calculateSingleItemPrice(@PathVariable("itemId") Long itemId, @PathVariable("amount") Integer amount) throws RecordNotFoundException {
        Item item = service.getItemById(itemId);
        PriceCalculationDTO dto = new PriceCalculationDTO();
        if(item != null) {
            dto.setPrice(service.calculateItemPrice(item, amount));
        }
        return new ResponseEntity<PriceCalculationDTO>(dto, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/calculate_price/all")
    public ResponseEntity<PriceCalculationDTO> calculateTotalPrice(@RequestBody ArrayList<PriceCalculationMultiple> itemsList) throws RecordNotFoundException {
        List<TotalPrice> items = new ArrayList<TotalPrice>();
        for(PriceCalculationMultiple item: itemsList) {
            Item availableItem = service.getItemById(item.getItemId());
            if(availableItem != null) {
                TotalPrice newPrice = new TotalPrice();
                newPrice.setItem(availableItem);
                newPrice.setPurchasedAmount(item.getAmount());
                items.add(newPrice);
            }
        }
        PriceCalculationDTO dto = new PriceCalculationDTO();
        dto.setPrice(service.calculateTotalPrice(items));
        return new ResponseEntity<PriceCalculationDTO>(dto, new HttpHeaders(), HttpStatus.OK);
    }
}
