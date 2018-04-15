package hibernate.shop.servlets;

import hibernate.shop.cart.Cart;
import hibernate.shop.cart.CartDetail;
import hibernate.shop.cart.CartRepository;
import hibernate.shop.order.Order;
import hibernate.shop.order.OrderDetail;
import hibernate.shop.order.OrderRepository;
import hibernate.shop.user.User;
import hibernate.shop.utils.UserSessionHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;

//adnotacja zamiast dodawania w hibernatego.cfg
@WebServlet(name = "CreateOrderServlet", urlPatterns = "/createOrder")
public class CreateOrderServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFromCookie = UserSessionHelper.getUserFromCookie(req.getCookies());
        if(userFromCookie!=null){
            Optional<Cart> byUserId = CartRepository.findByUserId(userFromCookie.getId());

            byUserId.ifPresent(x->createAndSaveOrder(x));
        }
    }

    private void createAndSaveOrder(Cart cart) {
        Order order = new Order();
        order.setTotalGross(cart.getTotalGrossPrice());
        order.setTotalNetto(cart.getTotalNettoPrice());
        order.setUser(cart.getUser());
        order.setOrderDetailSet(new HashSet<>());
        for(CartDetail cd :cart.getCartDetailSet()){
            OrderDetail od = new OrderDetail();
            od.setOrder(order);
            od.setProduct(cd.getProduct());
            od.setPrice(cd.getPrice());
            od.setAmount(cd.getAmount());
            order.addOrderDetail(od);
        }
        OrderRepository.saveOrder(order);
        CartRepository.removeCart(cart);
    }
}
