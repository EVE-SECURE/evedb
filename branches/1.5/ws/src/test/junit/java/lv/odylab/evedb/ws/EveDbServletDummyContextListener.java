package lv.odylab.evedb.ws;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import lv.odylab.evedb.application.EveDbClientFacade;

import static org.mockito.Mockito.mock;

public class EveDbServletDummyContextListener extends GuiceServletContextListener {
    private EveDbClientFacade clientFacade;

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new EveDbServletModule(), new AbstractModule() {
            @Override
            protected void configure() {
                clientFacade = mock(EveDbClientFacade.class);
                bind(EveDbClientFacade.class).toInstance(clientFacade);
            }
        });
    }

    public EveDbClientFacade getClientFacade() {
        return clientFacade;
    }
}