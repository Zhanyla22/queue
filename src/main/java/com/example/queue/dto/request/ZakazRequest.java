package com.example.queue.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ZakazRequest {

    @JsonProperty("user_id")
    Long userId;

    @JsonProperty("user_name")
    String userName;

    @JsonProperty("product_id")
    Long productId;

    @JsonProperty("product_quantity")
    Long productQuantity;

    @JsonProperty("product_name")
    String productName;

}
