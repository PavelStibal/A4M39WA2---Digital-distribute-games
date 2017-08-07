package digital.distribute.games.service.impl;

import digital.distribute.games.dao.BillingDAO;
import digital.distribute.games.entity.BillingAcc;
import digital.distribute.games.service.BillingService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Pavel Stibal
 */
@Stateless
@Transactional
public class BillingServiceImpl implements BillingService {
    private final Logger logger;

    private final BillingDAO billingDAO;

    @Inject
    public BillingServiceImpl(Logger logger, BillingDAO billingDAO) {
        this.logger = logger;
        this.billingDAO = billingDAO;
    }

    public BillingAcc get(Long id) {
        final BillingAcc billingAcc = billingDAO.find(id);
        logger.info("Returning bill: " + billingAcc);
        return billingAcc;
    }

    public void create(final BillingAcc billingAcc) {
        billingDAO.save(billingAcc);
        logger.info("Created new bill with id: " + billingAcc.getId());
    }

    public void delete(Long id) {
        delete(billingDAO.find(id));
    }

    public void delete(BillingAcc billingAcc) {
        billingDAO.delete(billingAcc);
        logger.info("Bill with id " + billingAcc.getId() + " deleted.");
    }

    public void update(BillingAcc billingAcc) {
        billingDAO.update(billingAcc);
        logger.info("Updated bill with id " + billingAcc.getId());
    }

    public List<BillingAcc> findAll() {
        List<BillingAcc> bills = billingDAO.findAll();
        logger.info("Returning " + bills.size() + " bills for user.");
        return bills;
    }

    public List<BillingAcc> findByDate(Date date) {
        List<BillingAcc> bills = new LinkedList();
        bills.addAll(billingDAO.findByProperty("date", date));
        logger.info("Returning " + bills.size() + " bills for user.");
        return bills;
    }

    @Override
    public List<BillingAcc> findByPlayer(Long playerID) {
        List<BillingAcc> bills = new LinkedList();
        bills.addAll(billingDAO.findByProperty("player", playerID));
        logger.info("Returning " + bills.size() + " bills for user.");
        return bills;
    }
}
