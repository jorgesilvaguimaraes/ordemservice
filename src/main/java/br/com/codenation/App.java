package br.com.codenation;

import br.com.codenation.model.OrderItem;
import br.com.codenation.repository.ProductRepositoryImpl;
import br.com.codenation.service.OrderService;
import br.com.codenation.service.OrderServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args){
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(1l, 3l));
        items.add(new OrderItem(2l, 2l));
        List<OrderItem> items2 = new ArrayList<>();
        items.add(new OrderItem(1l, 3l));
        items.add(new OrderItem(2l, 2l));
        List<OrderItem> items3 = new ArrayList<>();
        items.add(new OrderItem(1l, 3l));
        items.add(new OrderItem(2l, 2l));

        OrderService productRepository = new OrderServiceImpl();
        System.out.println(productRepository.calculateOrderValue(items));
        System.out.println(productRepository.calculateOrderValue(items2));
        System.out.println(productRepository.calculateOrderValue(items3));
        System.out.println(productRepository.calculateMultipleOrders(Arrays.asList(items, items2, items3)));
        System.out.println(productRepository.groupProductsBySale(Arrays.asList(1l, 2l, 12l)));

    }
}
