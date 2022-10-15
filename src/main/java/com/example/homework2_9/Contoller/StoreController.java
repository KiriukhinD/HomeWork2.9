package com.example.homework2_9.Contoller;

import com.example.homework2_9.Model.Item;
import com.example.homework2_9.Server.StoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/get")
    public List<Item> get(@RequestParam("/id") List<Integer> ids) {
        return storeService.get();
    }

    @GetMapping("/add")
    public void add(@RequestParam("/id") List<Integer> ids) {
        storeService.add(ids);
    }

}
