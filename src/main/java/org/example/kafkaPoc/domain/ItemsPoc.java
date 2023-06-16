package org.example.kafkaPoc.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemsPoc {
    Optional<Integer> id;
    Optional <String> message;

}
