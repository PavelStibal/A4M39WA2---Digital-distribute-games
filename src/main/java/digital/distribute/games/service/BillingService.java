package digital.distribute.games.service;

import digital.distribute.games.entity.BillingAcc;

import java.util.Date;
import java.util.List;

/**
 * @author Pavel Stibal
 */
public interface BillingService extends AbstractService<BillingAcc> {
    List<BillingAcc> findByDate(Date date);
    List<BillingAcc> findByPlayer(Long playerID);
}
