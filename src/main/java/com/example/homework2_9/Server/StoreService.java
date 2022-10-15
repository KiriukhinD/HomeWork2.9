package com.example.homework2_9.Server;


import com.example.homework2_9.Component.Basket;
import com.example.homework2_9.Model.Item;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final Map<Integer, Item> items = new HashMap<>();
    private final Basket basket;


    public StoreService(Basket basket) {
        this.basket = basket;
    }

    public void init() {
        items.put(1, new Item(1, "телевизор", 23_000));
        items.put(2, new Item(2, "утюг", 10_000));
        items.put(3, new Item(3, "принтер", 11_000));
        items.put(4, new Item(4, "плита", 12_000));
        items.put(5, new Item(5, "монитор", 14_000));
    }


    public void add(List<Integer> ids) {
        basket.add(ids.stream()
                .map(items::get)
                .collect(Collectors.toList()));
    }

    public List<Item> get() {
        return basket.get();
    }
}
