package org.example.kafkaPoc.repository;

import org.example.kafkaPoc.domain.ItemsPoc;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageRepository {
    private List<ItemsPoc> list = new ArrayList<>();

    public void addItemsPoc  (ItemsPoc item) {
        list.add(item);
    }

    public String getAllItems () {return list.toString(); }
    }




