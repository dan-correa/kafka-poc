package com.example.kafkaPoc.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemsPoc implements Serializable {
    Integer id;
    String message;

}
