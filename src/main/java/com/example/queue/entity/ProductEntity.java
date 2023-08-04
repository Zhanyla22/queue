package com.example.queue.entity;

import com.example.queue.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "quantity")
    Long quantity;

    @Column(name = "price")
    Double price;
}
