package lv.odylab.evedb.ws;

import com.google.inject.servlet.GuiceFilter;
import lv.odylab.evedb.application.EveDbClientFacade;
import lv.odylab.evedb.client.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.jetty.servlet.DefaultServlet;
import org.mortbay.jetty.testing.HttpTester;
import org.mortbay.jetty.testing.ServletTester;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class EveDbServletModuleTest {
    private HttpTester request;
    private HttpTester response;
    private ServletTester tester;
    private String baseUrl;
    private EveDbServletDummyContextListener contextListener;

    @Before
    public void startServlet() throws Exception {
        request = new HttpTester();
        request.setMethod("GET");
        request.setHeader("Host", "tester");
        response = new HttpTester();
        tester = new ServletTester();
        tester.setContextPath("/");
        tester.addFilter(GuiceFilter.class, "/*", 0);
        contextListener = new EveDbServletDummyContextListener();
        tester.addEventListener(contextListener);
        tester.addServlet(DefaultServlet.class, "/");
        baseUrl = tester.createSocketConnector(true);
        tester.start();
    }

    @After
    public void stopServlet() throws Exception {
        tester.stop();
    }

    @Test
    public void test_clearCache() throws Exception {
        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.clearCache()).thenReturn("OK");
        request.setURI(baseUrl + "/admin/clearCache");
        request.setHeader("accept", "text/plain");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("OK", response.getContent());
    }

    @Test
    public void test_baseMaterialsForTypeID() throws Exception {
        InvTypeMaterialDto invTypeMaterialDto = new InvTypeMaterialDto();
        invTypeMaterialDto.setMaterialTypeName("materialTypeName");
        List<InvTypeMaterialDto> invTypeMaterialDtos = new ArrayList<InvTypeMaterialDto>();
        invTypeMaterialDtos.add(invTypeMaterialDto);

        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.getInvTypeMaterialsForTypeID(1L)).thenReturn(invTypeMaterialDtos);
        request.setURI(baseUrl + "/baseMaterialsForTypeID/1");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("[{\"materialTypeName\":\"materialTypeName\"}]", response.getContent());

        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeName>materialTypeName</materialTypeName></row></rowset>", response.getContent());
    }

    @Test
    public void test_baseMaterialsForTypeName() throws Exception {
        InvTypeMaterialDto invTypeMaterialDto = new InvTypeMaterialDto();
        invTypeMaterialDto.setMaterialTypeName("materialTypeName");
        List<InvTypeMaterialDto> invTypeMaterialDtos = new ArrayList<InvTypeMaterialDto>();
        invTypeMaterialDtos.add(invTypeMaterialDto);

        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.getInvTypeMaterialsForTypeName("typeName")).thenReturn(invTypeMaterialDtos);
        request.setURI(baseUrl + "/baseMaterialsForTypeName/typeName");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("[{\"materialTypeName\":\"materialTypeName\"}]", response.getContent());

        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeName>materialTypeName</materialTypeName></row></rowset>", response.getContent());
    }

    @Test
    public void test_blueprintTypeByTypeID() throws Exception {
        InvBlueprintTypeDto invBlueprintTypeDto = new InvBlueprintTypeDto();
        invBlueprintTypeDto.setBlueprintTypeName("blueprintTypeName");

        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.getBlueprintTypeByTypeID(1L)).thenReturn(invBlueprintTypeDto);
        request.setURI(baseUrl + "/blueprintTypeByTypeID/1");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("{\"blueprintTypeName\":\"blueprintTypeName\"}", response.getContent());

        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invBlueprintTypeDto><blueprintTypeName>blueprintTypeName</blueprintTypeName></invBlueprintTypeDto>", response.getContent());
    }

    @Test
    public void test_blueprintTypeByTypeName() throws Exception {
        InvBlueprintTypeDto invBlueprintTypeDto = new InvBlueprintTypeDto();
        invBlueprintTypeDto.setBlueprintTypeName("blueprintTypeName");

        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.getBlueprintTypeByTypeName("typeName")).thenReturn(invBlueprintTypeDto);
        request.setURI(baseUrl + "/blueprintTypeByTypeName/typeName");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("{\"blueprintTypeName\":\"blueprintTypeName\"}", response.getContent());

        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invBlueprintTypeDto><blueprintTypeName>blueprintTypeName</blueprintTypeName></invBlueprintTypeDto>", response.getContent());
    }

    @Test
    public void test_extraMaterialsForTypeID() throws Exception {
        RamTypeRequirementDto ramTypeRequirementDto = new RamTypeRequirementDto();
        ramTypeRequirementDto.setRequiredTypeName("requiredTypeName");
        List<RamTypeRequirementDto> ramTypeRequirementDtos = new ArrayList<RamTypeRequirementDto>();
        ramTypeRequirementDtos.add(ramTypeRequirementDto);

        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.getRamTypeRequirementsForTypeID(1L)).thenReturn(ramTypeRequirementDtos);
        request.setURI(baseUrl + "/extraMaterialsForTypeID/1");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("[{\"requiredTypeName\":\"requiredTypeName\"}]", response.getContent());

        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><requiredTypeName>requiredTypeName</requiredTypeName></row></rowset>", response.getContent());
    }

    @Test
    public void test_extraMaterialsForTypeName() throws Exception {
        RamTypeRequirementDto ramTypeRequirementDto = new RamTypeRequirementDto();
        ramTypeRequirementDto.setRequiredTypeName("requiredTypeName");
        List<RamTypeRequirementDto> ramTypeRequirementDtos = new ArrayList<RamTypeRequirementDto>();
        ramTypeRequirementDtos.add(ramTypeRequirementDto);

        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.getRamTypeRequirementsForTypeName("typeName")).thenReturn(ramTypeRequirementDtos);
        request.setURI(baseUrl + "/extraMaterialsForTypeName/typeName");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("[{\"requiredTypeName\":\"requiredTypeName\"}]", response.getContent());

        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><requiredTypeName>requiredTypeName</requiredTypeName></row></rowset>", response.getContent());
    }

    @Test
    public void test_lookupBlueprintType() throws Exception {
        InvTypeBasicInfoDto invTypeBasicInfoDto = new InvTypeBasicInfoDto();
        invTypeBasicInfoDto.setName("name");
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = new ArrayList<InvTypeBasicInfoDto>();
        invTypeBasicInfoDtos.add(invTypeBasicInfoDto);

        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.findBlueprintTypeByPartialTypeName("query")).thenReturn(invTypeBasicInfoDtos);
        request.setURI(baseUrl + "/lookupBlueprintType/query");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("[{\"name\":\"name\"}]", response.getContent());

        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><name>name</name></row></rowset>", response.getContent());
    }

    @Test
    public void test_lookupResourceType() throws Exception {
        InvTypeBasicInfoDto invTypeBasicInfoDto = new InvTypeBasicInfoDto();
        invTypeBasicInfoDto.setName("name");
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = new ArrayList<InvTypeBasicInfoDto>();
        invTypeBasicInfoDtos.add(invTypeBasicInfoDto);

        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.findResourceTypeByPartialTypeName("query")).thenReturn(invTypeBasicInfoDtos);
        request.setURI(baseUrl + "/lookupResourceType/query");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("[{\"name\":\"name\"}]", response.getContent());

        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><name>name</name></row></rowset>", response.getContent());
    }

    @Test
    public void test_lookupType() throws Exception {
        InvTypeBasicInfoDto invTypeBasicInfoDto = new InvTypeBasicInfoDto();
        invTypeBasicInfoDto.setName("name");
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = new ArrayList<InvTypeBasicInfoDto>();
        invTypeBasicInfoDtos.add(invTypeBasicInfoDto);

        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.findTypeByPartialTypeName("query")).thenReturn(invTypeBasicInfoDtos);
        request.setURI(baseUrl + "/lookupType/query");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("[{\"name\":\"name\"}]", response.getContent());

        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><name>name</name></row></rowset>", response.getContent());
    }

    @Test
    public void test_typeBasicInfoByTypeID() throws Exception {
        InvTypeBasicInfoDto invTypeBasicInfoDto = new InvTypeBasicInfoDto();
        invTypeBasicInfoDto.setName("name");

        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.getTypeBasicInfoByTypeID(1L)).thenReturn(invTypeBasicInfoDto);
        request.setURI(baseUrl + "/typeBasicInfoByTypeID/1");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("{\"name\":\"name\"}", response.getContent());

        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invTypeBasicInfoDto><name>name</name></invTypeBasicInfoDto>", response.getContent());
    }

    @Test
    public void test_typeBasicInfoByTypeName() throws Exception {
        InvTypeBasicInfoDto invTypeBasicInfoDto = new InvTypeBasicInfoDto();
        invTypeBasicInfoDto.setName("name");

        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.getTypeBasicInfoByTypeName("typeName")).thenReturn(invTypeBasicInfoDto);
        request.setURI(baseUrl + "/typeBasicInfoByTypeName/typeName");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("{\"name\":\"name\"}", response.getContent());

        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invTypeBasicInfoDto><name>name</name></invTypeBasicInfoDto>", response.getContent());
    }

    @Test
    public void test_typeIdToTypeName() throws Exception {
        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.getTypeNameByTypeID(1L)).thenReturn("typeName");
        request.setURI(baseUrl + "/typeIdToTypeName/1");
        request.setHeader("accept", "text/plain");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("typeName", response.getContent());
    }

    @Test
    public void test_typeNameToTypeID() throws Exception {
        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.getTypeIdByTypeName("typeName")).thenReturn(1L);
        request.setURI(baseUrl + "/typeNameToTypeID/typeName");
        request.setHeader("accept", "text/plain");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("1", response.getContent());
    }

    @Test
    public void test_version() throws Exception {
        EveDbClientFacade clientFacade = contextListener.getClientFacade();
        when(clientFacade.getVersion()).thenReturn("version");
        request.setURI(baseUrl + "/version");
        request.setHeader("accept", "text/plain");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("version", response.getContent());
    }
}
