package lv.odylab.evedb.client;

import lv.odylab.evedb.rpc.dto.BlueprintDetailsDto;
import lv.odylab.evedb.rpc.dto.InvBlueprintTypeDto;
import lv.odylab.evedb.rpc.dto.InvTypeBasicInfoDto;
import lv.odylab.evedb.rpc.dto.InvTypeMaterialDto;
import lv.odylab.evedb.rpc.dto.PlanetSchematicDto;
import lv.odylab.evedb.rpc.dto.RamTypeRequirementDto;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class EveDbWsClientImplIntegrationTest {
    private final EveDbWsClient client = new EveDbWsClientImpl("http://inc101.latest.odylab-evedb.appspot.com");

    @Test
    public void testGetBaseMaterialsForTypeID() {
        List<InvTypeMaterialDto> invTypeMaterialDtoList = client.getBaseMaterialsForTypeID(20187L);
        assertThat(invTypeMaterialDtoList.size(), equalTo(4));
    }

    @Test(expected = BadRequestException.class)
    public void testGetBaseMaterialsForTypeID_IdNotFound() {
        client.getBaseMaterialsForTypeID(1234567890L);
    }

    @Test
    public void testGetBaseMaterialsForTypeName() {
        List<InvTypeMaterialDto> invTypeMaterialDtoList = client.getBaseMaterialsForTypeName("Obelisk");
        assertThat(invTypeMaterialDtoList.size(), equalTo(4));
    }

    @Test
    public void testGetBlueprintTypeByTypeID() {
        InvBlueprintTypeDto invBlueprintTypeDto = client.getBlueprintTypeByTypeID(20188L);
        assertThat(invBlueprintTypeDto.getBlueprintTypeName(), equalTo("Obelisk Blueprint"));
    }

    @Test
    public void testGetBlueprintTypeByTypeName() {
        InvBlueprintTypeDto invBlueprintTypeDto = client.getBlueprintTypeByTypeName("Obelisk Blueprint");
        assertThat(invBlueprintTypeDto.getBlueprintTypeID(), equalTo(20188L));
    }

    @Test
    public void testGetBlueprintDetailsForTypeID() {
        BlueprintDetailsDto blueprintDetailsDto = client.getBlueprintDetailsForTypeID(12024L);
        assertThat(blueprintDetailsDto.getInvBlueprintTypeDto().getBlueprintTypeName(), equalTo("Deimos Blueprint"));
    }

    @Test
    public void testGetBlueprintDetailsForTypeName() {
        BlueprintDetailsDto blueprintDetailsDto = client.getBlueprintDetailsForTypeName("Deimos Blueprint");
        assertThat(blueprintDetailsDto.getInvBlueprintTypeDto().getBlueprintTypeID(), equalTo(12024L));
    }

    @Test
    public void testGetExtraMaterialsForTypeID() {
        List<RamTypeRequirementDto> ramTypeRequirementDtoList = client.getExtraMaterialsForTypeID(20188L);
        assertThat(ramTypeRequirementDtoList.size(), equalTo(4));
    }

    @Test
    public void testGetExtraMaterialsForTypeName() {
        List<RamTypeRequirementDto> ramTypeRequirementDtoList = client.getExtraMaterialsForTypeName("Obelisk Blueprint");
        assertThat(ramTypeRequirementDtoList.size(), equalTo(4));
    }

    @Test
    public void testLookupBlueprintType() {
        List<InvTypeBasicInfoDto> typeBasicInfoDtoList = client.lookupBlueprintType("Obeli");
        assertThat(typeBasicInfoDtoList.size(), equalTo(1));
    }

    @Test
    public void testLookupResourceType() {
        List<InvTypeBasicInfoDto> typeBasicInfoDtoList = client.lookupResourceType("Trit");
        assertThat(typeBasicInfoDtoList.size(), equalTo(2));
    }

    @Test
    public void testLookupType() {
        List<InvTypeBasicInfoDto> typeBasicInfoDtoList = client.lookupType("Obeli");
        assertThat(typeBasicInfoDtoList.size(), equalTo(2));
    }

    @Test
    public void testGetPlanetarySchematicForTypeID() {
        List<PlanetSchematicDto> planetSchematicDtos = client.getPlanetSchematicForTypeID(9838L);
        assertThat(planetSchematicDtos.size(), equalTo(2));
    }

    @Test
    public void testgetPlanetarySchematicForTypeName() {
        List<PlanetSchematicDto> planetSchematicDtos = client.getPlanetSchematicForTypeName("Rocket Fuel");
        assertThat(planetSchematicDtos.size(), equalTo(2));
    }

    @Test
    public void testGetTypeBasicInfoByTypeID() {
        InvTypeBasicInfoDto invTypeBasicInfoDto = client.getTypeBasicInfoByTypeID(20188L);
        assertThat(invTypeBasicInfoDto.getName(), equalTo("Obelisk Blueprint"));
    }

    @Test
    public void testGetTypeBasicInfoByTypeName() {
        InvTypeBasicInfoDto invTypeBasicInfoDto = client.getTypeBasicInfoByTypeName("Obelisk");
        assertThat(invTypeBasicInfoDto.getItemTypeID(), equalTo(20187L));
    }

    @Test
    public void testTypeIdToTypeName() {
        assertThat(client.getTypeIdToTypeName(20187L), equalTo("Obelisk"));
    }

    @Test
    public void testTypeNameToTypeID() {
        assertThat(client.getTypeNameToTypeID("Obelisk"), equalTo(20187L));
    }

    @Test
    public void testVersion() {
        assertThat(client.getVersion(), equalTo("1.9-SNAPSHOT-inc101"));
    }
}
