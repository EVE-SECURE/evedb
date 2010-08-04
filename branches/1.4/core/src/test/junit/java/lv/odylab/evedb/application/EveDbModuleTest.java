package lv.odylab.evedb.application;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class EveDbModuleTest {

    @Test
    public void testGuice() {
        Injector injector = Guice.createInjector(Stage.PRODUCTION, new EveDbModule());
        assertNotNull(injector.getInstance(EveDbApplicationFacade.class));
    }
}
