package com.example.kourse.Controllers;

import com.example.kourse.Models.ClientOrder;
import com.example.kourse.Service.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class ClientOrderController {
    private final ClientOrderService clientOrderService;

    @Autowired
    public ClientOrderController(ClientOrderService clientOrderService) {
        this.clientOrderService = clientOrderService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    @GetMapping
    public List<ClientOrder> getAllOrders() {
        return clientOrderService.getAllOrders();
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping
    public ClientOrder createOrder(@RequestBody ClientOrder clientOrder) {
        return clientOrderService.createOrder(clientOrder);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ClientOrder updateOrder(@PathVariable Long id, @RequestBody ClientOrder updatedOrder) {
        return clientOrderService.updateOrder(id, updatedOrder);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        clientOrderService.deleteOrder(id);
    }
}
