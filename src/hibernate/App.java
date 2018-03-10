package hibernate;


import hibernate.shop.*;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


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


        ProductRepository.findAll().forEach(p-> System.out.println("findAll()"+p.getName()));

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

       Order order = Order.builder()
               .userEmail("test@sda.pl")
               .totalGross(new BigDecimal(12))
               .totalNetto(new BigDecimal(10))
               .build();

       OrderRepository.saveOrder(order);

        List<Order> all = OrderRepository.findAll();

        System.out.println("d");

        ProductRepository.findAllNative()
                .forEach(p-> System.out.println("find all from native: "+p.getName()));

    }
}
