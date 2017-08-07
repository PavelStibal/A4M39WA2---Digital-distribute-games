package digital.distribute.games.controller;

import digital.distribute.games.entity.BillingAcc;
import digital.distribute.games.entity.Game;
import digital.distribute.games.entity.Player;
import digital.distribute.games.service.BillingService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Pavel Stibal
 */
@Model
public class BillController {
    @Inject
    private Logger logger;

    @Inject
    private BillingService billingService;

    @Inject
    private FacesContext facesContext;

    @Produces
    @Named(value = "newBill")
    private BillingAcc newBill;

    @PostConstruct
    public void initNewGame() {
        newBill = new BillingAcc();
    }

    public List<BillingAcc> getAllBillForUser(final long id) throws IOException {
        try {
            List<BillingAcc> bills = billingService.findByPlayer(id);
            logger.fine("Bills received.");
            return bills;
        } catch (Exception e) {
            logger.severe("Error during getAllBillForUser(): " + e.getMessage());
            throw new WebApplicationException();
        }
    }

    public void createBill(final Game game, final Player player) throws IOException {
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        newBill.setPlayer_id(player);
        newBill.setCost((double) (1 + (int)(Math.random() * 500)));
        newBill.setDate(new Date());
        newBill.setDescription(game.getName());
        try {
            billingService.create(newBill);
            logger.info("New Bill created.");
            response.sendRedirect("/Digital_distribute_games-1.0-SNAPSHOT/faces/pages/detail/detail_bill.xhtml?id2=" + player.getId());
        } catch (Exception e) {
            logger.severe("Error during create reservation!");
        }
    }
}
