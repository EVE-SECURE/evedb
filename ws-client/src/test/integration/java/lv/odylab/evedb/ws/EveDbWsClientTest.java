package lv.odylab.evedb.ws;

import lv.odylab.evedb.client.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.client.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.client.rpc.dto.RamTypeRequirementDto;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class EveDbWsClientTest {
    private final EveDbWsClient client = new EveDbWsClient("http://dev.latest.odylab-evedb.appspot.com");

    @Test
    public void testGetBaseMaterialsForTypeID() {
        List<InvTypeMaterialDto> invTypeMaterialDtoList = client.getBaseMaterialsForTypeID(20187L);
        assertEquals(4, invTypeMaterialDtoList.size());
    }

    @Test
    public void testGetBaseMaterialsForTypeID_IdNotFound() {
        List<InvTypeMaterialDto> invTypeMaterialDtoList = client.getBaseMaterialsForTypeID(1234567890L);
        assertEquals(4, invTypeMaterialDtoList.size());
    }

    @Test
    public void testGetBaseMaterialsForTypeName() {
        List<InvTypeMaterialDto> invTypeMaterialDtoList = client.getBaseMaterialsForTypeName("Obelisk");
        assertEquals(4, invTypeMaterialDtoList.size());
    }

    @Test
    public void testGetBlueprintTypeByTypeID() {
        InvBlueprintTypeDto invBlueprintTypeDto = client.getBlueprintTypeByTypeID(20188L);
        assertEquals("Obelisk Blueprint", invBlueprintTypeDto.getBlueprintTypeName());
    }

    @Test
    public void testGetBlueprintTypeByTypeName() {
        InvBlueprintTypeDto invBlueprintTypeDto = client.getBlueprintTypeByTypeName("Obelisk Blueprint");
        assertEquals(Long.valueOf(20188), invBlueprintTypeDto.getBlueprintTypeID());
    }

    @Test
    public void testGetExtraMaterialsForTypeID() {
        List<RamTypeRequirementDto> ramTypeRequirementDtoList = client.getExtraMaterialsForTypeID(20188L);
        assertEquals(4, ramTypeRequirementDtoList.size());
    }

    @Test
    public void testGetExtraMaterialsForTypeName() {
        List<RamTypeRequirementDto> ramTypeRequirementDtoList = client.getExtraMaterialsForTypeName("Obelisk Blueprint");
        assertEquals(4, ramTypeRequirementDtoList.size());
    }

    @Test
    public void testLookupBlueprintType() {
        List<InvTypeBasicInfoDto> typeBasicInfoDtoList = client.lookupBlueprintType("Obeli");
        assertEquals(1, typeBasicInfoDtoList.size());
    }

    @Test
    public void testLookupResourceType() {
        List<InvTypeBasicInfoDto> typeBasicInfoDtoList = client.lookupResourceType("Trit");
        assertEquals(1, typeBasicInfoDtoList.size());
    }

    @Test
    public void testLookupType() {
        List<InvTypeBasicInfoDto> typeBasicInfoDtoList = client.lookupType("Obeli");
        assertEquals(2, typeBasicInfoDtoList.size());
    }

    @Test
    public void testGetTypeBasicInfoByTypeID() {
        InvTypeBasicInfoDto invTypeBasicInfoDto = client.getTypeBasicInfoByTypeID(20188L);
        assertEquals("Obelisk Blueprint", invTypeBasicInfoDto.getName());
    }

    @Test
    public void testGetTypeBasicInfoByTypeName() {
        InvTypeBasicInfoDto invTypeBasicInfoDto = client.getTypeBasicInfoByTypeName("Obelisk");
        assertEquals(Long.valueOf(20187), invTypeBasicInfoDto.getItemTypeID());
    }

    @Test
    public void testTypeIdToTypeName() {
        assertEquals("Obelisk", client.getTypeIdToTypeName(20187L));
    }

    @Test
    public void testTypeNameToTypeID() {
        assertEquals(Long.valueOf(20187), client.getTypeNameToTypeID("Obelisk"));
    }

    @Test
    public void testVersion() {
        assertEquals("1.4-SNAPSHOT", client.getEveDbVersion());
    }
}
