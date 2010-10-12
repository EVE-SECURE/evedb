package lv.odylab.evedb.servlet;

import com.google.gson.JsonParseException;
import lv.odylab.evedb.client.BadRequestException;
import lv.odylab.evedb.client.EveDbWsClient;
import lv.odylab.evedb.client.EveDbWsClientImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EveDbWsClientImplTest {
    @Test
    public void none() {
    }

    @Mock
    private EveDbWsClient.HttpRequestSender httpRequestSender;
    private EveDbWsClientImpl eveDbWsClient;

    @Before
    public void setUp() {
        eveDbWsClient = new EveDbWsClientImpl("eveDbUrl", httpRequestSender);
    }

    @Test(expected = JsonParseException.class)
    public void test_getBaseMaterialsForTypeID() {
        when(httpRequestSender.doGet("eveDbUrl/baseMaterialsForTypeID/1", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getBaseMaterialsForTypeID(1L);
    }

    @Test(expected = JsonParseException.class)
    public void test_getBaseMaterialsForTypeName() {
        when(httpRequestSender.doGet("eveDbUrl/baseMaterialsForTypeName/typeName", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getBaseMaterialsForTypeName("typeName");
    }

    @Test(expected = JsonParseException.class)
    public void test_getBlueprintTypeByTypeID() {
        when(httpRequestSender.doGet("eveDbUrl/blueprintTypeByTypeID/1", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getBlueprintTypeByTypeID(1L);
    }

    @Test(expected = JsonParseException.class)
    public void test_getBlueprintTypeByTypeName() {
        when(httpRequestSender.doGet("eveDbUrl/blueprintTypeByTypeName/typeName", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getBlueprintTypeByTypeName("typeName");
    }

    @Test(expected = JsonParseException.class)
    public void test_getBlueprintDetailsForTypeID() {
        when(httpRequestSender.doGet("eveDbUrl/blueprintDetailsForTypeID/1", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getBlueprintDetailsForTypeID(1L);
    }

    @Test(expected = JsonParseException.class)
    public void test_getBlueprintDetailsForTypeName() {
        when(httpRequestSender.doGet("eveDbUrl/blueprintDetailsForTypeName/typeName", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getBlueprintDetailsForTypeName("typeName");
    }

    @Test(expected = JsonParseException.class)
    public void test_getExtraMaterialsForTypeID() {
        when(httpRequestSender.doGet("eveDbUrl/extraMaterialsForTypeID/1", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getExtraMaterialsForTypeID(1L);
    }

    @Test(expected = JsonParseException.class)
    public void test_getExtraMaterialsForTypeName() {
        when(httpRequestSender.doGet("eveDbUrl/extraMaterialsForTypeName/typeName", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getExtraMaterialsForTypeName("typeName");
    }

    @Test(expected = JsonParseException.class)
    public void test_lookupBlueprintType() {
        when(httpRequestSender.doGet("eveDbUrl/lookupBlueprintType/typeName", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.lookupBlueprintType("typeName");
    }

    @Test(expected = JsonParseException.class)
    public void test_lookupResourceType() {
        when(httpRequestSender.doGet("eveDbUrl/lookupResourceType/typeName", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.lookupResourceType("typeName");
    }

    @Test(expected = JsonParseException.class)
    public void test_lookupType() {
        when(httpRequestSender.doGet("eveDbUrl/lookupType/typeName", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.lookupType("typeName");
    }

    @Test(expected = JsonParseException.class)
    public void test_getTypeBasicInfoByTypeID() {
        when(httpRequestSender.doGet("eveDbUrl/typeBasicInfoByTypeID/1", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getTypeBasicInfoByTypeID(1L);
    }

    @Test(expected = JsonParseException.class)
    public void test_getTypeBasicInfoByTypeName() {
        when(httpRequestSender.doGet("eveDbUrl/typeBasicInfoByTypeName/typeName", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getTypeBasicInfoByTypeName("typeName");
    }

    @Test
    public void test_getTypeIdToTypeName() {
        when(httpRequestSender.doGet("eveDbUrl/typeIdToTypeName/1", "text/plain")).thenReturn("typeName");
        assertEquals("typeName", eveDbWsClient.getTypeIdToTypeName(1L));
    }

    @Test
    public void test_getTypeNameToTypeID() {
        when(httpRequestSender.doGet("eveDbUrl/typeNameToTypeID/typeName", "text/plain")).thenReturn("1");
        assertEquals(Long.valueOf(1), eveDbWsClient.getTypeNameToTypeID("typeName"));
    }

    @Test
    public void test_getVersion() {
        when(httpRequestSender.doGet("eveDbUrl/version", "text/plain")).thenReturn("version");
        assertEquals("version", eveDbWsClient.getVersion());
    }

    @Test(expected = BadRequestException.class)
    public void testBadRequestException() {
        when(httpRequestSender.doGet("eveDbUrl/typeNameToTypeID/badTypeName", "text/plain")).thenThrow(new BadRequestException());
        eveDbWsClient.getTypeNameToTypeID("badTypeName");
    }
}
