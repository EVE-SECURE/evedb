package lv.odylab.evedb.application;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.googlecode.objectify.ObjectifyFactory;
import lv.odylab.appengine.aspect.Caching;
import lv.odylab.appengine.aspect.CachingAspect;
import lv.odylab.evedb.domain.EveDbObjectifyFactory;
import lv.odylab.evedb.service.BlueprintService;
import lv.odylab.evedb.service.BlueprintServiceImpl;

import java.net.URL;
import java.util.Properties;

public class EveDbModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ObjectifyFactory.class).to(EveDbObjectifyFactory.class);
        bind(EveDbApplicationFacade.class).to(EveDbApplicationFacadeImpl.class);
        bind(EveDbClientFacade.class).to(EveDbClientFacadeImpl.class);
        bind(EveDbDtoMapper.class).to(EveDbDtoMapperImpl.class);
        bind(BlueprintService.class).to(BlueprintServiceImpl.class);

        CachingAspect cachingAspect = new CachingAspect();
        requestInjection(cachingAspect);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Caching.class), cachingAspect);

        try {
            Names.bindProperties(binder(), loadProperties());
        } catch (Exception e) {
            binder().addError(e);
        }
    }

    private Properties loadProperties() throws Exception {
        Properties properties = new Properties();
        URL url = getClass().getResource("/evedb.properties");
        properties.load(url.openStream());
        return properties;
    }
}
