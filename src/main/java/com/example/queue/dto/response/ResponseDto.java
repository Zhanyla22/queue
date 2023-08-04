package com.example.queue.dto.response;

import com.example.queue.enums.ResultCode;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseDto {

    Object data;

    ResultCode status;

    String message;

}
