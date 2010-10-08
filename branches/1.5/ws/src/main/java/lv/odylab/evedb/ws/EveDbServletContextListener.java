package lv.odylab.evedb.ws;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import lv.odylab.evedb.application.EveDbModule;

public class EveDbServletContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new EveDbModule(), new EveDbServletModule());
    }
}
