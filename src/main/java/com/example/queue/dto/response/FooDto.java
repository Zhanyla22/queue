package com.example.queue.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FooDto {

    Long id;

    String title;

    String body;

    Long userId;
}
