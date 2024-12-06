package com.example.kourse.Repository;

import com.example.kourse.Models.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {
}