package com.example.queue.dto.response;

import com.example.queue.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ZakazResponse {

    Long id;

    @JsonProperty("product_name")
    String productName;

    @JsonProperty("user_name")
    String userName;

    @JsonProperty("price")
    Double price;

    Long quantity;

    Status status;
}
