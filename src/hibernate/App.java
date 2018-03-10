package hibernate;


import hibernate.shop.Price;
import hibernate.shop.Product;
import hibernate.shop.ProductRepository;
import hibernate.shop.ProductType;



import java.math.BigDecimal;
import java.util.Optional;


public class App {

    public static void main(String[] args) throws Exception {
        Product mercS = new Product("Mercedes S", ProductType.CAR,
                new Price(new BigDecimal(40000), new BigDecimal(60000)));

        Product mercA = new Product("Mercedes A", ProductType.CAR,
                new Price(new BigDecimal(10000), new BigDecimal(20000)));


        ProductRepository.saveProduct(mercS);
        ProductRepository.saveProduct(mercA);

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

    }
}
