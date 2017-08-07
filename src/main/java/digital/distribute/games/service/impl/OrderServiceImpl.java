package digital.distribute.games.service.impl;

import digital.distribute.games.dao.OrderDAO;
import digital.distribute.games.entity.Oorder;
import digital.distribute.games.service.OrderService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Pavel Stibal
 */
@Stateless
@Transactional
public class OrderServiceImpl implements OrderService {

    private final Logger logger;

    private final OrderDAO orderDAO;

    @Inject
    public OrderServiceImpl(Logger logger, OrderDAO orderDAO) {
        this.logger = logger;
        this.orderDAO = orderDAO;
    }

    @Override
    public Oorder get(Long id) {
        final Oorder oorder = orderDAO.find(id);
        logger.info("Returning order: " + oorder);
        return oorder;
    }

    @Override
    public void create(Oorder oorder) {
        orderDAO.save(oorder);
        logger.info("Created new order with id: " + oorder.getId());
    }

    @Override
    public void delete(Long id) {
        delete(orderDAO.find(id));
    }

    @Override
    public void delete(Oorder oorder) {
        orderDAO.delete(oorder);
        logger.info("Order with id " + oorder.getId() + " deleted.");
    }

    @Override
    public void update(Oorder oorder) {
        orderDAO.update(oorder);
        logger.info("Updated order with id " + oorder.getId());
    }

    @Override
    public List<Oorder> findAll() {
        List<Oorder> oorders = orderDAO.findAll();
        logger.info("Returning " + oorders.size() + " orders");
        return oorders;
    }
}
