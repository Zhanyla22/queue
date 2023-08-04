package com.example.queue.entity;

import com.example.queue.entity.base.BaseEntity;
import com.example.queue.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "zakazy")
public class ZakazEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(columnDefinition = "user_id",
            referencedColumnName = "id")
    UserEntity userEntity;

    @ManyToOne
    @JoinColumn(columnDefinition = "product_id",
            referencedColumnName = "id")
    ProductEntity productEntity;

    @Column(name = "summ")
    Double summ;

    @Column(name = "quantity")
    Long quantity;

    @Enumerated(EnumType.STRING)
    Status status;
}
