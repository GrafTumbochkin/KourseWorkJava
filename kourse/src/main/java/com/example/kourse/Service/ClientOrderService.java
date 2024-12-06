package com.example.kourse.Service;

import com.example.kourse.Models.ClientOrder;
import com.example.kourse.Repository.ClientOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientOrderService {
    private final ClientOrderRepository clientOrderRepository;

    @Autowired
    public ClientOrderService(ClientOrderRepository clientOrderRepository) {
        this.clientOrderRepository = clientOrderRepository;
    }

    public List<ClientOrder> getAllOrders() {
        return clientOrderRepository.findAll();
    }

    public ClientOrder createOrder(ClientOrder order) {
        return clientOrderRepository.save(order);
    }

    public ClientOrder updateOrder(Long id, ClientOrder updatedOrder) {
        return clientOrderRepository.findById(id).map(order -> {
            order.setCompletionDate(updatedOrder.getCompletionDate());
            order.setQuantity(updatedOrder.getQuantity());
            order.setProduct(updatedOrder.getProduct());
            return clientOrderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(Long id) {
        clientOrderRepository.deleteById(id);
    }
}
