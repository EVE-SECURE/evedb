package lv.odylab.evedb.client;

import com.google.gson.JsonParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EveDbWsClientImplTest {
    @Mock
    private EveDbWsClient.HttpRequestSender httpRequestSender;
    private EveDbWsClientImpl eveDbWsClient;

    @Before
    public void setUp() {
        eveDbWsClient = new EveDbWsClientImpl("eveDbUrl", httpRequestSender);
    }

    @Test(expected = JsonParseException.class)
    public void test_getBaseItemsForTypeID() {
        when(httpRequestSender.doGet("eveDbUrl/baseItemsForTypeID/1", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getBaseItemsForTypeID(1L);
    }

    @Test(expected = JsonParseException.class)
    public void test_getBaseItemsForTypeName() {
        when(httpRequestSender.doGet("eveDbUrl/baseItemsForTypeName/typeName", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getBaseItemsForTypeName("typeName");
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
    public void test_getPlanetarySchematicForTypeID() {
        when(httpRequestSender.doGet("eveDbUrl/planetSchematicForTypeID/1", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getPlanetSchematicForTypeID(1L);
    }

    @Test(expected = JsonParseException.class)
    public void test_getPlanetarySchematicForTypeName() {
        when(httpRequestSender.doGet("eveDbUrl/planetSchematicForTypeName/typeName", "application/json")).thenReturn("blah-blah");
        eveDbWsClient.getPlanetSchematicForTypeName("typeName");
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
        assertThat(eveDbWsClient.getTypeIdToTypeName(1L), equalTo("typeName"));
    }

    @Test
    public void test_getTypeNameToTypeID() {
        when(httpRequestSender.doGet("eveDbUrl/typeNameToTypeID/typeName", "text/plain")).thenReturn("1");
        assertThat(eveDbWsClient.getTypeNameToTypeID("typeName"), equalTo(Long.valueOf(1)));
    }

    @Test
    public void test_getVersion() {
        when(httpRequestSender.doGet("eveDbUrl/version", "text/plain")).thenReturn("version");
        assertThat(eveDbWsClient.getVersion(), equalTo("version"));
    }

    @Test(expected = BadRequestException.class)
    public void testBadRequestException() {
        when(httpRequestSender.doGet("eveDbUrl/typeNameToTypeID/badTypeName", "text/plain")).thenThrow(new BadRequestException());
        eveDbWsClient.getTypeNameToTypeID("badTypeName");
    }
}
