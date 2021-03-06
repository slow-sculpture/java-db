/*
package hibernate;


import hibernate.shop.complaint.ComplaintStatus;
import hibernate.shop.complaint.OrderComplaint;
import hibernate.shop.complaint.OrderComplaintRepository;
import hibernate.shop.order.Order;
import hibernate.shop.order.OrderDetail;
import hibernate.shop.order.OrderRepository;
import hibernate.shop.product.Price;
import hibernate.shop.product.Product;
import hibernate.shop.product.ProductRepository;
import hibernate.shop.product.ProductType;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public class App {

    public static void main(String[] args) throws Exception {
        Product mercS = new Product("Mercedes S", ProductType.CAR,
                new Price(new BigDecimal(40000), new BigDecimal(60000)));

        Product mercA = new Product("Mercedes A", ProductType.CAR,
                new Price(new BigDecimal(10000), new BigDecimal(20000)));

        Product smallCar = new Product("Red car", ProductType.TOY,
                new Price(new BigDecimal(30), new BigDecimal(45)));


        ProductRepository.saveProduct(mercS);
        ProductRepository.saveProduct(mercA);
        ProductRepository.saveProduct(smallCar);

        Optional<Product> product1 = ProductRepository.findOneById(1L);
        Optional<Product> product2 = ProductRepository.findOneById(2L);
        Optional<Product> product3 = ProductRepository.findOneById(3L);

        //z Optional get() ale niezalecane bo nie sprawdza czy to null
        //product1.get().getName();
        // ==
        //System.out.println(product1.getName());
        //System.out.println(product2.getName());
        //System.out.println(product3.getName());

        //jesli null to zwraca pusty String i nie wywala wyjatku
        System.out.println();
        System.out.println("Lista wybranych produktow:");
        System.out.println(product1.map(p->p.getName()).orElse(("")));
        System.out.println(product2.map(p->p.getName()).orElse(("")));
        System.out.println(product3.map(p->p.getName()).orElse(("")));


        ProductRepository.findAll().forEach(p-> System.out.println("findAllDTO()"+p.getName()));

        ProductRepository.findAllByProductType(ProductType.CAR)
                .forEach(p-> System.out.println("type car: "+p.getName()));

        ProductRepository.findAllByProductType(ProductType.TOY)
                .forEach(p-> System.out.println("type toy: "+p.getName()));

        Long carCount = ProductRepository.countByProductType(ProductType.CAR);
        System.out.println("cars in db " +carCount);

        ProductRepository.findAllWithPriceLess(new BigDecimal(50))
                .forEach(p-> System.out.println("product with price less: "+p.getName()+" "+p.getPrice().getVatPrice()));

        ProductRepository.findAllByNameLike("merc")
                .forEach(p-> System.out.println("find all by merc: "+p.getName()));

        Optional<Product> toyOptional = ProductRepository.findOneById(3L);
        if(toyOptional.isPresent()){
            Product toy = toyOptional.get();
            toy.getPrice().setGrossPrice(toy.getPrice().getGrossPrice().add(BigDecimal.ONE));
            toy.getPrice().setNettoPrice(toy.getPrice().getNettoPrice().add(BigDecimal.ONE));

            ProductRepository.saveProduct(toy);
        }

        ProductRepository.delete(2L);

        //pozycja zamowienia
        OrderDetail orderDetail = OrderDetail.builder()
                .amount(BigDecimal.ONE)
                .product(product1.get())
                .price(product1.get().getPrice())
                .build();

        OrderDetail orderDetail2 = OrderDetail.builder()
                .amount(BigDecimal.TEN)
                .product(product3.get())
                .price(product3.get().getPrice())
                .build();

        OrderDetail orderDetail3 = OrderDetail.builder()
                .amount(BigDecimal.TEN)
                .product(product1.get())
                .price(product1.get().getPrice())
                .build();


       //zamowienie
        Order order = Order.builder()
               .userEmail("test@sda.pl")
               .totalGross(new BigDecimal(12))
               .totalNetto(new BigDecimal(10))
                //dodajemy seta do zamowienia
                .orderDetailSet(new HashSet<>())
               .build();

        //dodaje pozycje zamowienia do zamowienia
        order.addOrderDetail(orderDetail);
        order.addOrderDetail(orderDetail2);
        order.addOrderDetail(orderDetail3);

       //zapis zamowienia
       OrderRepository.saveOrder(order);

        List<Order> all = OrderRepository.findAllDTO();

        System.out.println("d");

        ProductRepository.findAllNative()
                .forEach(p-> System.out.println("find all from native: "+p.getName()));

        OrderRepository.findAllOrderWithProduct(1L)
                .forEach(p-> System.out.println("find order with product: "+order.getId()));

        List<Order> allOrderWithProduct = OrderRepository.findAllOrderWithProduct(1L);

        for(Order o : allOrderWithProduct){
            o.getOrderDetailSet().stream().forEach(od ->
                    System.out.println("zamowienie o id "+o.getId()+" "+od.getProduct().getName()));
        }
        //powyzsze wyrzuca blad bo listy i sety nie sa pobierane automatycznie zeby nie obciazac DB -> LAZY
        //mozna albo ustawic FetchType.EAGER (dodane po // w order)
        //albo zrobic specjalne zapytanie SQL: LEFT JOIN FETCH (dodane w OrderRepository.findAllOrderWithProduct

        System.out.println();
        List<Order> allOrder = OrderRepository.findAll();

        for(Order o : allOrder){
            o.getOrderDetailSet().stream().forEach(od ->
                    System.out.println("zamowienie o id "+o.getId()+" "+od.getProduct().getName()));
        }

        Set<Order> orderSet = new HashSet<>();
        orderSet.add(order);

        OrderComplaint orderComplaint = OrderComplaint.builder()
                .complaintStatus(ComplaintStatus.PENDING)
                .message("New complaint from John")
                .orderSet(orderSet)
                .build();

        OrderComplaintRepository.saveOrderComplaint(orderComplaint);

        ProductRepository.findAllByNameLikeWithCriteria("merc")
                .forEach(p-> System.out.println("find all by merc: "+p.getName()));

        ProductRepository.findAllCarOrToyWithPriceLessThen(new BigDecimal(70000))
        .forEach(p-> System.out.println("findAllCarOrToyWithPriceLessThen: "+p.getName()));



    }
}
*/
