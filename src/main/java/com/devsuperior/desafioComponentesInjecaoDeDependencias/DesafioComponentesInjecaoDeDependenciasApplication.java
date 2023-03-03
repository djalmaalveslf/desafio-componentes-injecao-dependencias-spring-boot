package com.devsuperior.desafioComponentesInjecaoDeDependencias;

import com.devsuperior.entities.Order;
import com.devsuperior.services.OrderService;
import com.devsuperior.services.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan({"com.devsuperior"})
public class DesafioComponentesInjecaoDeDependenciasApplication implements CommandLineRunner {

	@Autowired
	private ShippingService shippingService;
	@Autowired
	private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(DesafioComponentesInjecaoDeDependenciasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		int code = sc.nextInt();
		double basic = sc.nextDouble();
		double discount = sc.nextDouble();

		Order order = new Order(code, basic, discount);

		System.out.println("Pedido código: " + order.getCode());

		double result = orderService.total(order) + shippingService.shipment(order);

		System.out.println("Valor total: R$" + String.format("%.2f", result));

		sc.close();
	}
}
