package lv.odylab.evedb.ws;

import com.google.gson.Gson;
import lv.odylab.appengine.aspect.Caching;
import lv.odylab.evedb.application.EveDbClientFacade;
import lv.odylab.evedb.client.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;
import lv.odylab.evedb.domain.IdNotFoundException;
import lv.odylab.evedb.domain.NameNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.google.appengine.repackaged.com.google.common.base.X.assertTrue;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EveDbWsFacadeImplTest {
    private EveDbClientFacade clientFacade;
    private EveDbWsFacade wsFacade;

    @Before
    public void setUp() {
        clientFacade = mock(EveDbClientFacade.class);
        wsFacade = new EveDbWsFacadeImpl(new Gson(), clientFacade);
    }

    @Test
    public void test_clearCache() {
        when(clientFacade.clearCache()).thenReturn("OK");
        assertEquals("OK", clientFacade.clearCache());
    }

    @Test
    public void test_getInvTypeMaterials() {
        List<InvTypeMaterialDto> invTypeMaterialDtos = new ArrayList<InvTypeMaterialDto>();
        InvTypeMaterialDto invTypeMaterialDto1 = new InvTypeMaterialDto();
        invTypeMaterialDto1.setMaterialTypeID(1L);
        invTypeMaterialDto1.setMaterialTypeName("materialTypeName1");
        invTypeMaterialDto1.setMaterialTypeCategoryID(2L);
        invTypeMaterialDto1.setQuantity(3L);
        invTypeMaterialDto1.setMaterialTypeGraphicIcon("materialGraphicIcon1");
        InvTypeMaterialDto invTypeMaterialDto2 = new InvTypeMaterialDto();
        invTypeMaterialDto2.setMaterialTypeID(4L);
        invTypeMaterialDto2.setMaterialTypeName("materialTypeName2");
        invTypeMaterialDto2.setMaterialTypeCategoryID(5L);
        invTypeMaterialDto2.setQuantity(6L);
        invTypeMaterialDto2.setMaterialTypeGraphicIcon("materialGraphicIcon2");
        invTypeMaterialDtos.add(invTypeMaterialDto1);
        invTypeMaterialDtos.add(invTypeMaterialDto2);
        when(clientFacade.getInvTypeMaterialsForTypeID(1L)).thenReturn(invTypeMaterialDtos);
        when(clientFacade.getInvTypeMaterialsForTypeName("typeName")).thenReturn(invTypeMaterialDtos);
        assertEquals("[{\"materialTypeID\":1,\"materialTypeName\":\"materialTypeName1\",\"materialTypeCategoryID\":2,\"quantity\":3,\"materialTypeGraphicIcon\":\"materialGraphicIcon1\"},{\"materialTypeID\":4,\"materialTypeName\":\"materialTypeName2\",\"materialTypeCategoryID\":5,\"quantity\":6,\"materialTypeGraphicIcon\":\"materialGraphicIcon2\"}]", wsFacade.getInvTypeMaterialsForTypeIdAsJson(1L));
        assertEquals("[{\"materialTypeID\":1,\"materialTypeName\":\"materialTypeName1\",\"materialTypeCategoryID\":2,\"quantity\":3,\"materialTypeGraphicIcon\":\"materialGraphicIcon1\"},{\"materialTypeID\":4,\"materialTypeName\":\"materialTypeName2\",\"materialTypeCategoryID\":5,\"quantity\":6,\"materialTypeGraphicIcon\":\"materialGraphicIcon2\"}]", wsFacade.getInvTypeMaterialsForTypeNameAsJson("typeName"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>2</materialTypeCategoryID><materialTypeGraphicIcon>materialGraphicIcon1</materialTypeGraphicIcon><materialTypeID>1</materialTypeID><materialTypeName>materialTypeName1</materialTypeName><quantity>3</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>5</materialTypeCategoryID><materialTypeGraphicIcon>materialGraphicIcon2</materialTypeGraphicIcon><materialTypeID>4</materialTypeID><materialTypeName>materialTypeName2</materialTypeName><quantity>6</quantity></row></rowset>", wsFacade.getInvTypeMaterialsForTypeIdAsXml(1L));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>2</materialTypeCategoryID><materialTypeGraphicIcon>materialGraphicIcon1</materialTypeGraphicIcon><materialTypeID>1</materialTypeID><materialTypeName>materialTypeName1</materialTypeName><quantity>3</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>5</materialTypeCategoryID><materialTypeGraphicIcon>materialGraphicIcon2</materialTypeGraphicIcon><materialTypeID>4</materialTypeID><materialTypeName>materialTypeName2</materialTypeName><quantity>6</quantity></row></rowset>", wsFacade.getInvTypeMaterialsForTypeNameAsXml("typeName"));
    }

    @Test
    public void test_getInvTypeMaterial_EmptyCollection() {
        List<InvTypeMaterialDto> invTypeMaterialDtos = new ArrayList<InvTypeMaterialDto>();
        when(clientFacade.getInvTypeMaterialsForTypeID(1L)).thenReturn(invTypeMaterialDtos);
        when(clientFacade.getInvTypeMaterialsForTypeName("typeName")).thenReturn(invTypeMaterialDtos);
        assertEquals("[]", wsFacade.getInvTypeMaterialsForTypeIdAsJson(1L));
        assertEquals("[]", wsFacade.getInvTypeMaterialsForTypeNameAsJson("typeName"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset/>", wsFacade.getInvTypeMaterialsForTypeIdAsXml(1L));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset/>", wsFacade.getInvTypeMaterialsForTypeNameAsXml("typeName"));
    }

    @Test(expected = IdNotFoundException.class)
    public void test_getInvTypeMaterialsForTypeIdAsJson_NotFound() {
        when(clientFacade.getInvTypeMaterialsForTypeID(1L)).thenThrow(new IdNotFoundException(1L));
        wsFacade.getInvTypeMaterialsForTypeIdAsJson(1L);
    }

    @Test(expected = IdNotFoundException.class)
    public void test_getInvTypeMaterialsForTypeIdAsXml_NotFound() {
        when(clientFacade.getInvTypeMaterialsForTypeID(1L)).thenThrow(new IdNotFoundException(1L));
        wsFacade.getInvTypeMaterialsForTypeIdAsXml(1L);
    }

    @Test(expected = NameNotFoundException.class)
    public void test_getInvTypeMaterialsForTypeNameAsJson_NotFound() {
        when(clientFacade.getInvTypeMaterialsForTypeName("typeName")).thenThrow(new NameNotFoundException("typeName"));
        wsFacade.getInvTypeMaterialsForTypeNameAsJson("typeName");
    }

    @Test(expected = NameNotFoundException.class)
    public void test_getInvTypeMaterialsForTypeNameAsXml_NotFound() {
        when(clientFacade.getInvTypeMaterialsForTypeName("typeName")).thenThrow(new NameNotFoundException("typeName"));
        wsFacade.getInvTypeMaterialsForTypeNameAsXml("typeName");
    }

    @Test
    public void test_getBlueprintType() {
        InvBlueprintTypeDto invBlueprintTypeDto = new InvBlueprintTypeDto();
        invBlueprintTypeDto.setBlueprintTypeID(1L);
        invBlueprintTypeDto.setBlueprintTypeName("blueprintTypeName");
        invBlueprintTypeDto.setProductTypeID(2L);
        invBlueprintTypeDto.setProductTypeName("Obelisk");
        invBlueprintTypeDto.setProductCategoryID(3L);
        invBlueprintTypeDto.setProductGraphicIcon("productGraphicIcon");
        invBlueprintTypeDto.setWasteFactor(4);
        invBlueprintTypeDto.setMaxProductionLimit(5);
        when(clientFacade.getBlueprintTypeByTypeID(1L)).thenReturn(invBlueprintTypeDto);
        when(clientFacade.getBlueprintTypeByTypeName("typeName")).thenReturn(invBlueprintTypeDto);
        assertEquals("{\"blueprintTypeID\":1,\"blueprintTypeName\":\"blueprintTypeName\",\"productTypeID\":2,\"productTypeName\":\"Obelisk\",\"productCategoryID\":3,\"productGraphicIcon\":\"productGraphicIcon\",\"wasteFactor\":4,\"maxProductionLimit\":5}", wsFacade.getBlueprintTypeByTypeIdAsJson(1L));
        assertEquals("{\"blueprintTypeID\":1,\"blueprintTypeName\":\"blueprintTypeName\",\"productTypeID\":2,\"productTypeName\":\"Obelisk\",\"productCategoryID\":3,\"productGraphicIcon\":\"productGraphicIcon\",\"wasteFactor\":4,\"maxProductionLimit\":5}", wsFacade.getBlueprintTypeByTypeNameAsJson("typeName"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invBlueprintTypeDto><blueprintTypeID>1</blueprintTypeID><blueprintTypeName>blueprintTypeName</blueprintTypeName><maxProductionLimit>5</maxProductionLimit><productCategoryID>3</productCategoryID><productGraphicIcon>productGraphicIcon</productGraphicIcon><productTypeID>2</productTypeID><productTypeName>Obelisk</productTypeName><wasteFactor>4</wasteFactor></invBlueprintTypeDto>", wsFacade.getBlueprintTypeByTypeIdAsXml(1L));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invBlueprintTypeDto><blueprintTypeID>1</blueprintTypeID><blueprintTypeName>blueprintTypeName</blueprintTypeName><maxProductionLimit>5</maxProductionLimit><productCategoryID>3</productCategoryID><productGraphicIcon>productGraphicIcon</productGraphicIcon><productTypeID>2</productTypeID><productTypeName>Obelisk</productTypeName><wasteFactor>4</wasteFactor></invBlueprintTypeDto>", wsFacade.getBlueprintTypeByTypeNameAsXml("typeName"));
    }

    @Test
    public void test_getBlueprintType_EmptyCollection() {
        InvBlueprintTypeDto invBlueprintTypeDto = new InvBlueprintTypeDto();
        when(clientFacade.getBlueprintTypeByTypeID(1L)).thenReturn(invBlueprintTypeDto);
        when(clientFacade.getBlueprintTypeByTypeName("typeName")).thenReturn(invBlueprintTypeDto);
        assertEquals("{}", wsFacade.getBlueprintTypeByTypeIdAsJson(1L));
        assertEquals("{}", wsFacade.getBlueprintTypeByTypeNameAsJson("typeName"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invBlueprintTypeDto/>", wsFacade.getBlueprintTypeByTypeIdAsXml(1L));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invBlueprintTypeDto/>", wsFacade.getBlueprintTypeByTypeNameAsXml("typeName"));
    }

    @Test(expected = IdNotFoundException.class)
    public void test_getBlueprintTypeByTypeIdAsJson_NotFound() {
        when(clientFacade.getBlueprintTypeByTypeID(1L)).thenThrow(new IdNotFoundException(1L));
        wsFacade.getBlueprintTypeByTypeIdAsJson(1L);
    }

    @Test(expected = IdNotFoundException.class)
    public void test_getBlueprintTypeByTypeIdAsXml_NotFound() {
        when(clientFacade.getBlueprintTypeByTypeID(1L)).thenThrow(new IdNotFoundException(1L));
        wsFacade.getBlueprintTypeByTypeIdAsXml(1L);
    }

    @Test(expected = NameNotFoundException.class)
    public void test_getBlueprintTypeByTypeNameAsJson_NotFound() {
        when(clientFacade.getBlueprintTypeByTypeName("typeName")).thenThrow(new NameNotFoundException("typeName"));
        wsFacade.getBlueprintTypeByTypeNameAsJson("typeName");
    }

    @Test(expected = NameNotFoundException.class)
    public void test_getBlueprintTypeByTypeNameAsXml_NotFound() {
        when(clientFacade.getBlueprintTypeByTypeName("typeName")).thenThrow(new NameNotFoundException("typeName"));
        wsFacade.getBlueprintTypeByTypeNameAsXml("typeName");
    }

    @Test
    public void test_getRamTypeRequirement() {
        List<RamTypeRequirementDto> ramTypeRequirementDtos = new ArrayList<RamTypeRequirementDto>();
        RamTypeRequirementDto ramTypeRequirementDto1 = new RamTypeRequirementDto();
        ramTypeRequirementDto1.setActivityID(1L);
        ramTypeRequirementDto1.setActivityName("activityName1");
        ramTypeRequirementDto1.setRequiredTypeID(2L);
        ramTypeRequirementDto1.setRequiredTypeCategoryID(3L);
        ramTypeRequirementDto1.setRequiredTypeGroupID(4L);
        ramTypeRequirementDto1.setRequiredTypeGroupName("requiredTypeGroupName1");
        ramTypeRequirementDto1.setQuantity(5L);
        ramTypeRequirementDto1.setDamagePerJob("0.6");
        ramTypeRequirementDto1.setRequiredTypeNameGraphicIcon("requiredTypeGraphicIcon1");
        RamTypeRequirementDto ramTypeRequirementDto2 = new RamTypeRequirementDto();
        ramTypeRequirementDto2.setActivityID(1L);
        ramTypeRequirementDto2.setActivityName("activityName1");
        ramTypeRequirementDto2.setRequiredTypeID(2L);
        ramTypeRequirementDto2.setRequiredTypeCategoryID(3L);
        ramTypeRequirementDto2.setRequiredTypeGroupID(4L);
        ramTypeRequirementDto2.setRequiredTypeGroupName("requiredTypeGroupName1");
        ramTypeRequirementDto2.setQuantity(5L);
        ramTypeRequirementDto2.setDamagePerJob("0.6");
        ramTypeRequirementDto2.setRequiredTypeNameGraphicIcon("requiredTypeGraphicIcon1");
        ramTypeRequirementDtos.add(ramTypeRequirementDto1);
        ramTypeRequirementDtos.add(ramTypeRequirementDto2);
        when(clientFacade.getRamTypeRequirementsForTypeID(1L)).thenReturn(ramTypeRequirementDtos);
        when(clientFacade.getRamTypeRequirementsForTypeName("typeName")).thenReturn(ramTypeRequirementDtos);
        assertEquals("[{\"activityID\":1,\"activityName\":\"activityName1\",\"requiredTypeID\":2,\"requiredTypeCategoryID\":3,\"requiredTypeGroupID\":4,\"requiredTypeGroupName\":\"requiredTypeGroupName1\",\"quantity\":5,\"damagePerJob\":\"0.6\",\"requiredTypeNameGraphicIcon\":\"requiredTypeGraphicIcon1\"},{\"activityID\":1,\"activityName\":\"activityName1\",\"requiredTypeID\":2,\"requiredTypeCategoryID\":3,\"requiredTypeGroupID\":4,\"requiredTypeGroupName\":\"requiredTypeGroupName1\",\"quantity\":5,\"damagePerJob\":\"0.6\",\"requiredTypeNameGraphicIcon\":\"requiredTypeGraphicIcon1\"}]", wsFacade.getRamTypeRequirementsForTypeIdAsJson(1L));
        assertEquals("[{\"activityID\":1,\"activityName\":\"activityName1\",\"requiredTypeID\":2,\"requiredTypeCategoryID\":3,\"requiredTypeGroupID\":4,\"requiredTypeGroupName\":\"requiredTypeGroupName1\",\"quantity\":5,\"damagePerJob\":\"0.6\",\"requiredTypeNameGraphicIcon\":\"requiredTypeGraphicIcon1\"},{\"activityID\":1,\"activityName\":\"activityName1\",\"requiredTypeID\":2,\"requiredTypeCategoryID\":3,\"requiredTypeGroupID\":4,\"requiredTypeGroupName\":\"requiredTypeGroupName1\",\"quantity\":5,\"damagePerJob\":\"0.6\",\"requiredTypeNameGraphicIcon\":\"requiredTypeGraphicIcon1\"}]", wsFacade.getRamTypeRequirementsForTypeNameAsJson("typeName"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>1</activityID><activityName>activityName1</activityName><damagePerJob>0.6</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>3</requiredTypeCategoryID><requiredTypeGroupID>4</requiredTypeGroupID><requiredTypeGroupName>requiredTypeGroupName1</requiredTypeGroupName><requiredTypeID>2</requiredTypeID><requiredTypeNameGraphicIcon>requiredTypeGraphicIcon1</requiredTypeNameGraphicIcon></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>1</activityID><activityName>activityName1</activityName><damagePerJob>0.6</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>3</requiredTypeCategoryID><requiredTypeGroupID>4</requiredTypeGroupID><requiredTypeGroupName>requiredTypeGroupName1</requiredTypeGroupName><requiredTypeID>2</requiredTypeID><requiredTypeNameGraphicIcon>requiredTypeGraphicIcon1</requiredTypeNameGraphicIcon></row></rowset>", wsFacade.getRamTypeRequirementsForTypeIdAsXml(1L));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>1</activityID><activityName>activityName1</activityName><damagePerJob>0.6</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>3</requiredTypeCategoryID><requiredTypeGroupID>4</requiredTypeGroupID><requiredTypeGroupName>requiredTypeGroupName1</requiredTypeGroupName><requiredTypeID>2</requiredTypeID><requiredTypeNameGraphicIcon>requiredTypeGraphicIcon1</requiredTypeNameGraphicIcon></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>1</activityID><activityName>activityName1</activityName><damagePerJob>0.6</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>3</requiredTypeCategoryID><requiredTypeGroupID>4</requiredTypeGroupID><requiredTypeGroupName>requiredTypeGroupName1</requiredTypeGroupName><requiredTypeID>2</requiredTypeID><requiredTypeNameGraphicIcon>requiredTypeGraphicIcon1</requiredTypeNameGraphicIcon></row></rowset>", wsFacade.getRamTypeRequirementsForTypeNameAsXml("typeName"));
    }

    @Test
    public void test_getRamTypeRequirement_EmptyCollection() {
        List<RamTypeRequirementDto> ramTypeRequirementDtos = new ArrayList<RamTypeRequirementDto>();
        when(clientFacade.getRamTypeRequirementsForTypeID(1L)).thenReturn(ramTypeRequirementDtos);
        when(clientFacade.getRamTypeRequirementsForTypeName("typeName")).thenReturn(ramTypeRequirementDtos);
        assertEquals("[]", wsFacade.getRamTypeRequirementsForTypeIdAsJson(1L));
        assertEquals("[]", wsFacade.getRamTypeRequirementsForTypeNameAsJson("typeName"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset/>", wsFacade.getRamTypeRequirementsForTypeIdAsXml(1L));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset/>", wsFacade.getRamTypeRequirementsForTypeNameAsXml("typeName"));
    }

    @Test(expected = IdNotFoundException.class)
    public void test_getRamTypeRequirementsForTypeIdAsJson_NotFound() {
        when(clientFacade.getRamTypeRequirementsForTypeID(1L)).thenThrow(new IdNotFoundException(1L));
        wsFacade.getRamTypeRequirementsForTypeIdAsJson(1L);
    }

    @Test(expected = IdNotFoundException.class)
    public void test_getRamTypeRequirementsForTypeIdAsXml_NotFound() {
        when(clientFacade.getRamTypeRequirementsForTypeID(1L)).thenThrow(new IdNotFoundException(1L));
        wsFacade.getRamTypeRequirementsForTypeIdAsXml(1L);
    }

    @Test(expected = NameNotFoundException.class)
    public void test_getRamTypeRequirementsForTypeNameAsJson_NotFound() {
        when(clientFacade.getRamTypeRequirementsForTypeName("typeName")).thenThrow(new NameNotFoundException("typeName"));
        wsFacade.getRamTypeRequirementsForTypeNameAsJson("typeName");
    }

    @Test(expected = NameNotFoundException.class)
    public void test_getRamTypeRequirementsForTypeNameAsXml_NotFound() {
        when(clientFacade.getRamTypeRequirementsForTypeName("typeName")).thenThrow(new NameNotFoundException("typeName"));
        wsFacade.getRamTypeRequirementsForTypeNameAsXml("typeName");
    }

    @Test
    public void test_findType() {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = new ArrayList<InvTypeBasicInfoDto>();
        InvTypeBasicInfoDto invTypeBasicInfoDto1 = new InvTypeBasicInfoDto();
        invTypeBasicInfoDto1.setItemTypeID(1L);
        invTypeBasicInfoDto1.setName("name1");
        invTypeBasicInfoDto1.setItemCategoryID(2L);
        invTypeBasicInfoDto1.setGraphicIcon("graphicIcon1");
        InvTypeBasicInfoDto invTypeBasicInfoDto2 = new InvTypeBasicInfoDto();
        invTypeBasicInfoDto2.setItemTypeID(3L);
        invTypeBasicInfoDto2.setName("name2");
        invTypeBasicInfoDto2.setItemCategoryID(4L);
        invTypeBasicInfoDto2.setGraphicIcon("graphicIcon2");
        invTypeBasicInfoDtos.add(invTypeBasicInfoDto1);
        invTypeBasicInfoDtos.add(invTypeBasicInfoDto2);
        when(clientFacade.findBlueprintTypeByPartialTypeName("partialTypeName")).thenReturn(invTypeBasicInfoDtos);
        when(clientFacade.findResourceTypeByPartialTypeName("partialTypeName")).thenReturn(invTypeBasicInfoDtos);
        when(clientFacade.findTypeByPartialTypeName("partialTypeName")).thenReturn(invTypeBasicInfoDtos);
        assertEquals("[{\"itemTypeID\":1,\"itemCategoryID\":2,\"name\":\"name1\",\"graphicIcon\":\"graphicIcon1\"},{\"itemTypeID\":3,\"itemCategoryID\":4,\"name\":\"name2\",\"graphicIcon\":\"graphicIcon2\"}]", wsFacade.findBlueprintTypeByPartialTypeNameAsJson("partialTypeName"));
        assertEquals("[{\"itemTypeID\":1,\"itemCategoryID\":2,\"name\":\"name1\",\"graphicIcon\":\"graphicIcon1\"},{\"itemTypeID\":3,\"itemCategoryID\":4,\"name\":\"name2\",\"graphicIcon\":\"graphicIcon2\"}]", wsFacade.findResourceTypeByPartialTypeNameAsJson("partialTypeName"));
        assertEquals("[{\"itemTypeID\":1,\"itemCategoryID\":2,\"name\":\"name1\",\"graphicIcon\":\"graphicIcon1\"},{\"itemTypeID\":3,\"itemCategoryID\":4,\"name\":\"name2\",\"graphicIcon\":\"graphicIcon2\"}]", wsFacade.findTypeByPartialTypeNameAsJson("partialTypeName"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><graphicIcon>graphicIcon1</graphicIcon><itemCategoryID>2</itemCategoryID><itemTypeID>1</itemTypeID><name>name1</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><graphicIcon>graphicIcon2</graphicIcon><itemCategoryID>4</itemCategoryID><itemTypeID>3</itemTypeID><name>name2</name></row></rowset>", wsFacade.findBlueprintTypeByPartialTypeNameAsXml("partialTypeName"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><graphicIcon>graphicIcon1</graphicIcon><itemCategoryID>2</itemCategoryID><itemTypeID>1</itemTypeID><name>name1</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><graphicIcon>graphicIcon2</graphicIcon><itemCategoryID>4</itemCategoryID><itemTypeID>3</itemTypeID><name>name2</name></row></rowset>", wsFacade.findResourceTypeByPartialTypeNameAsXml("partialTypeName"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><graphicIcon>graphicIcon1</graphicIcon><itemCategoryID>2</itemCategoryID><itemTypeID>1</itemTypeID><name>name1</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><graphicIcon>graphicIcon2</graphicIcon><itemCategoryID>4</itemCategoryID><itemTypeID>3</itemTypeID><name>name2</name></row></rowset>", wsFacade.findTypeByPartialTypeNameAsXml("partialTypeName"));
    }

    @Test
    public void test_findType_EmptyCollection() {
        List<InvTypeBasicInfoDto> invTypeBasicInfoDtos = new ArrayList<InvTypeBasicInfoDto>();
        when(clientFacade.findBlueprintTypeByPartialTypeName("partialTypeName")).thenReturn(invTypeBasicInfoDtos);
        when(clientFacade.findResourceTypeByPartialTypeName("partialTypeName")).thenReturn(invTypeBasicInfoDtos);
        when(clientFacade.findTypeByPartialTypeName("partialTypeName")).thenReturn(invTypeBasicInfoDtos);
        assertEquals("[]", wsFacade.findBlueprintTypeByPartialTypeNameAsJson("partialTypeName"));
        assertEquals("[]", wsFacade.findResourceTypeByPartialTypeNameAsJson("partialTypeName"));
        assertEquals("[]", wsFacade.findTypeByPartialTypeNameAsJson("partialTypeName"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset/>", wsFacade.findBlueprintTypeByPartialTypeNameAsXml("partialTypeName"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset/>", wsFacade.findResourceTypeByPartialTypeNameAsXml("partialTypeName"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset/>", wsFacade.findTypeByPartialTypeNameAsXml("partialTypeName"));
    }

    @Test
    public void test_getTypeBasicInfo() {
        InvTypeBasicInfoDto invTypeBasicInfoDto = new InvTypeBasicInfoDto();
        invTypeBasicInfoDto.setItemTypeID(1L);
        invTypeBasicInfoDto.setName("name1");
        invTypeBasicInfoDto.setItemCategoryID(2L);
        invTypeBasicInfoDto.setGraphicIcon("graphicIcon1");
        when(clientFacade.getTypeBasicInfoByTypeID(1L)).thenReturn(invTypeBasicInfoDto);
        when(clientFacade.getTypeBasicInfoByTypeName("typeName")).thenReturn(invTypeBasicInfoDto);
        assertEquals("{\"itemTypeID\":1,\"itemCategoryID\":2,\"name\":\"name1\",\"graphicIcon\":\"graphicIcon1\"}", wsFacade.getTypeBasicInfoByTypeIdAsJson(1L));
        assertEquals("{\"itemTypeID\":1,\"itemCategoryID\":2,\"name\":\"name1\",\"graphicIcon\":\"graphicIcon1\"}", wsFacade.getTypeBasicInfoByTypeNameAsJson("typeName"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invTypeBasicInfoDto><graphicIcon>graphicIcon1</graphicIcon><itemCategoryID>2</itemCategoryID><itemTypeID>1</itemTypeID><name>name1</name></invTypeBasicInfoDto>", wsFacade.getTypeBasicInfoByTypeIdAsXml(1L));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invTypeBasicInfoDto><graphicIcon>graphicIcon1</graphicIcon><itemCategoryID>2</itemCategoryID><itemTypeID>1</itemTypeID><name>name1</name></invTypeBasicInfoDto>", wsFacade.getTypeBasicInfoByTypeNameAsXml("typeName"));
    }

    @Test(expected = IdNotFoundException.class)
    public void test_getTypeBasicInfoByTypeIdAsJson_NotFound() {
        when(clientFacade.getTypeBasicInfoByTypeID(1L)).thenThrow(new IdNotFoundException(1L));
        wsFacade.getTypeBasicInfoByTypeIdAsJson(1L);
    }

    @Test(expected = IdNotFoundException.class)
    public void test_getTypeBasicInfoByTypeIdAsXml_NotFound() {
        when(clientFacade.getTypeBasicInfoByTypeID(1L)).thenThrow(new IdNotFoundException(1L));
        wsFacade.getTypeBasicInfoByTypeIdAsXml(1L);
    }

    @Test(expected = NameNotFoundException.class)
    public void test_getTypeBasicInfoByTypeNameAsJson_NotFound() {
        when(clientFacade.getTypeBasicInfoByTypeName("typeName")).thenThrow(new NameNotFoundException("typeName"));
        wsFacade.getTypeBasicInfoByTypeNameAsJson("typeName");
    }

    @Test(expected = NameNotFoundException.class)
    public void test_getTypeBasicInfoByTypeNameAsXml_NotFound() {
        when(clientFacade.getTypeBasicInfoByTypeName("typeName")).thenThrow(new NameNotFoundException("typeName"));
        wsFacade.getTypeBasicInfoByTypeNameAsXml("typeName");
    }

    @Test
    public void test_getType() {
        when(clientFacade.getTypeNameByTypeID(1L)).thenReturn("typeName");
        when(clientFacade.getTypeIdByTypeName("typeName")).thenReturn(1L);
        assertEquals("typeName", wsFacade.getTypeNameByTypeID(1L));
        assertEquals("1", wsFacade.getTypeIdByTypeName("typeName"));
    }

    @Test(expected = IdNotFoundException.class)
    public void test_getTypeNameByTypeID_NotFound() {
        when(clientFacade.getTypeNameByTypeID(1L)).thenThrow(new IdNotFoundException(1L));
        wsFacade.getTypeNameByTypeID(1L);
    }

    @Test(expected = NameNotFoundException.class)
    public void test_getTypeIdByTypeName_NotFound() {
        when(clientFacade.getTypeIdByTypeName("typeName")).thenThrow(new NameNotFoundException("typeName"));
        wsFacade.getTypeIdByTypeName("typeName");
    }

    @Test
    public void test_getTypeIdByTypeName_NameWithWhitespaces() {
        when(clientFacade.getTypeIdByTypeName("type name with whitespaces")).thenReturn(1L);
        assertEquals("1", wsFacade.getTypeIdByTypeName("type name with whitespaces"));
    }

    @Test
    public void test_getVersion() {
        when(clientFacade.getVersion()).thenReturn("version");
        assertEquals("version", wsFacade.getVersion());
    }

    @Test
    public void test_CachingAnnotation() {
        List<String> excludedMethodNames = new ArrayList<String>();
        Method[] excludedMethods = Object.class.getMethods();
        for (Method excludedMethod : excludedMethods) {
            excludedMethodNames.add(excludedMethod.getName());
        }
        excludedMethodNames.add("getVersion");
        excludedMethodNames.add("clearCache");

        Method[] methods = wsFacade.getClass().getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (!excludedMethodNames.contains(methodName)) {
                assertTrue(method.isAnnotationPresent(Caching.class), "Method " + method.getName() + " is not annotated with @Caching");
            }
        }
    }
}
