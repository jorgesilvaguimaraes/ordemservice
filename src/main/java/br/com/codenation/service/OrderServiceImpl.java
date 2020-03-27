package br.com.codenation.service;

import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		return items.stream().reduce(0.0, (subtotal, item) -> subtotal + (this.calculaDesconto(item.getProductId()) * item.getQuantity()), Double::sum);
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		List<Product> products = new ArrayList<>();

		ids.forEach(id -> {
			Optional<Product> product = productRepository.findById((Long)id);
			product.ifPresent(products::add);

		});

		return new HashSet<>(products);
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return  orders.stream().reduce( 0.0 ,(sub,  items)->  sub + calculateOrderValue(items), Double::sum);
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		return productIds.stream().map( p -> productRepository.findById(p).get()).collect(Collectors.groupingBy(Product::getIsSale));
	}

	private Double calculaDesconto(Long id){

		Optional<Product> product = productRepository.findById(id);

		return product.map(value -> value.getIsSale() ? (value.getValue() - (value.getValue() * 20 / 100)) : value.getValue()).orElse(0.0);

	}
}