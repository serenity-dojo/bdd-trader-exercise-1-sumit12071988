package net.bddtrader.z2_unittests.status;

import net.bddtrader.config.TradingDataSource;
import net.bddtrader.status.StatusController;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenCheckingApplicationStatus {

    StatusController controller;

    @Before
    public void prepareNewsController() {
        controller = new StatusController(TradingDataSource.DEV);
    }

    @Test
    public void statusShouldIncludeTradeDataSource() {

        controller = new StatusController(TradingDataSource.DEV);

        assertThat(controller.status()).isEqualTo("BDDTrader running against DEV");
    }

}
