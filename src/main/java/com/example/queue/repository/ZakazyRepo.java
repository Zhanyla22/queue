package com.example.queue.repository;

import com.example.queue.entity.ZakazEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZakazyRepo extends JpaRepository<ZakazEntity,Long> {
}
