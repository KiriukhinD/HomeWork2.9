package com.example.homework2_9.Component;

import com.example.homework2_9.Model.Item;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;


@Component
@SessionScope
public class Basket {
    private final List<Item> items;


    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(List<Item> items) {
        items.addAll(items);

    }

    public List<Item> get() {
        return new ArrayList<>(items);
    }

}
