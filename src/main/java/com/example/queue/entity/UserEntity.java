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
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {

    @Column(name = "user_name")
    String userName;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "address")
    String address;
}
