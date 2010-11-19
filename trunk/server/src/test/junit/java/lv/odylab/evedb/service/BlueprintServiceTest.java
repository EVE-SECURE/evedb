package lv.odylab.evedb.service;

import lv.odylab.evedb.domain.InvBlueprintType;
import lv.odylab.evedb.domain.InvBlueprintTypeDao;
import lv.odylab.evedb.domain.InvTypeMaterial;
import lv.odylab.evedb.domain.InvTypeMaterialDao;
import lv.odylab.evedb.domain.RamTypeRequirement;
import lv.odylab.evedb.domain.RamTypeRequirementDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlueprintServiceTest {
    @Mock
    private InvBlueprintTypeDao invBlueprintTypeDao;
    @Mock
    private InvTypeMaterialDao invTypeMaterialDao;
    @Mock
    private RamTypeRequirementDao ramTypeRequirementDao;
    private BlueprintService blueprintService;

    @Before
    public void setUp() {
        blueprintService = new BlueprintService(invBlueprintTypeDao, invTypeMaterialDao, ramTypeRequirementDao, "dumpVersion");

        InvBlueprintType invBlueprintType = new InvBlueprintType();
        invBlueprintType.setBlueprintTypeID(1L);
        invBlueprintType.setProductTypeID(2L);
        List<InvTypeMaterial> invTypeMaterials = new ArrayList<InvTypeMaterial>();
        InvTypeMaterial invTypeMaterial1 = new InvTypeMaterial();
        invTypeMaterial1.setMaterialTypeID(3L);
        invTypeMaterial1.setQuantity(10L);
        InvTypeMaterial invTypeMaterial2 = new InvTypeMaterial();
        invTypeMaterial2.setMaterialTypeID(4L);
        invTypeMaterial2.setQuantity(20L);
        InvTypeMaterial invTypeMaterial3 = new InvTypeMaterial();
        invTypeMaterial3.setMaterialTypeID(5L);
        invTypeMaterial3.setQuantity(30L);
        invTypeMaterials.add(invTypeMaterial1);
        invTypeMaterials.add(invTypeMaterial2);
        invTypeMaterials.add(invTypeMaterial3);
        List<RamTypeRequirement> ramTypeRequirements = new ArrayList<RamTypeRequirement>();
        RamTypeRequirement ramTypeRequirement1 = new RamTypeRequirement();
        ramTypeRequirement1.setRequiredTypeID(6L);
        ramTypeRequirement1.setQuantity(1L);
        ramTypeRequirement1.setActivityName("Manufacturing");
        ramTypeRequirement1.setRecycle(Boolean.TRUE);
        RamTypeRequirement ramTypeRequirement2 = new RamTypeRequirement();
        ramTypeRequirement2.setRequiredTypeID(7L);
        ramTypeRequirement2.setQuantity(1L);
        ramTypeRequirement2.setActivityName("Manufacturing");
        ramTypeRequirement2.setRecycle(Boolean.FALSE);
        RamTypeRequirement ramTypeRequirement3 = new RamTypeRequirement();
        ramTypeRequirement3.setRequiredTypeID(8L);
        ramTypeRequirement3.setQuantity(1L);
        ramTypeRequirement3.setActivityName("Researching Time Productivity");
        ramTypeRequirement3.setRecycle(Boolean.FALSE);
        RamTypeRequirement ramTypeRequirement4 = new RamTypeRequirement();
        ramTypeRequirement4.setRequiredTypeID(9L);
        ramTypeRequirement4.setQuantity(1L);
        ramTypeRequirement4.setActivityName("Researching Material Productivity");
        ramTypeRequirement4.setRecycle(Boolean.FALSE);
        RamTypeRequirement ramTypeRequirement5 = new RamTypeRequirement();
        ramTypeRequirement5.setRequiredTypeID(10L);
        ramTypeRequirement5.setQuantity(1L);
        ramTypeRequirement5.setActivityName("Copying");
        ramTypeRequirement5.setRecycle(Boolean.FALSE);
        RamTypeRequirement ramTypeRequirement6 = new RamTypeRequirement();
        ramTypeRequirement6.setRequiredTypeID(11L);
        ramTypeRequirement6.setQuantity(1L);
        ramTypeRequirement6.setActivityName("Reverse Engineering");
        ramTypeRequirement6.setRecycle(Boolean.FALSE);
        RamTypeRequirement ramTypeRequirement7 = new RamTypeRequirement();
        ramTypeRequirement7.setRequiredTypeID(12L);
        ramTypeRequirement7.setQuantity(1L);
        ramTypeRequirement7.setActivityName("Invention");
        ramTypeRequirement7.setRecycle(Boolean.FALSE);
        ramTypeRequirements.add(ramTypeRequirement1);
        ramTypeRequirements.add(ramTypeRequirement2);
        ramTypeRequirements.add(ramTypeRequirement3);
        ramTypeRequirements.add(ramTypeRequirement4);
        ramTypeRequirements.add(ramTypeRequirement5);
        ramTypeRequirements.add(ramTypeRequirement6);
        ramTypeRequirements.add(ramTypeRequirement7);

        List<InvTypeMaterial> invTypeMaterialsForRequirement = new ArrayList<InvTypeMaterial>();
        InvTypeMaterial invTypeMaterialForRequirement1 = new InvTypeMaterial();
        invTypeMaterialForRequirement1.setMaterialTypeID(3L);
        invTypeMaterialForRequirement1.setQuantity(5L);
        InvTypeMaterial invTypeMaterialForRequirement2 = new InvTypeMaterial();
        invTypeMaterialForRequirement2.setMaterialTypeID(4L);
        invTypeMaterialForRequirement2.setQuantity(25L);
        invTypeMaterialsForRequirement.add(invTypeMaterialForRequirement1);
        invTypeMaterialsForRequirement.add(invTypeMaterialForRequirement2);

        when(invBlueprintTypeDao.getByTypeID(1L, "dumpVersion")).thenReturn(invBlueprintType);
        when(invBlueprintTypeDao.getByTypeName("typeName", "dumpVersion")).thenReturn(invBlueprintType);
        when(invTypeMaterialDao.getForTypeIdWithoutCheck(2L, "dumpVersion")).thenReturn(invTypeMaterials);
        when(invTypeMaterialDao.getForTypeIdWithoutCheck(6L, "dumpVersion")).thenReturn(invTypeMaterialsForRequirement);
        when(ramTypeRequirementDao.getForTypeIdWithoutCheck(1L, "dumpVersion")).thenReturn(ramTypeRequirements);
    }

    @Test
    public void test_getBlueprintDetailsForTypeID() {
        BlueprintDetails blueprintDetails = blueprintService.getBlueprintDetailsForTypeID(1L);
        assertEquals(Long.valueOf(2), blueprintDetails.getBlueprintType().getProductTypeID());
        assertEquals(2, blueprintDetails.getMaterials().size());
        assertEquals(Long.valueOf(5), blueprintDetails.getMaterials().get(0).getQuantity());
        assertEquals(Long.valueOf(30), blueprintDetails.getMaterials().get(1).getQuantity());
        assertEquals(2, blueprintDetails.getManufacturingRequirements().size());
        assertEquals(1, blueprintDetails.getTimeProductivityRequirements().size());
        assertEquals(1, blueprintDetails.getMaterialProductivityRequirements().size());
        assertEquals(1, blueprintDetails.getCopyingRequirements().size());
        assertEquals(1, blueprintDetails.getReverseEngineeringRequirements().size());
        assertEquals(1, blueprintDetails.getInventionRequirements().size());
    }

    @Test
    public void test_getBlueprintDetailsForTypeName() {
        BlueprintDetails blueprintDetails = blueprintService.getBlueprintDetailsForTypeName("typeName");
        assertEquals(Long.valueOf(2), blueprintDetails.getBlueprintType().getProductTypeID());
        assertEquals(2, blueprintDetails.getMaterials().size());
        assertEquals(Long.valueOf(5), blueprintDetails.getMaterials().get(0).getQuantity());
        assertEquals(Long.valueOf(30), blueprintDetails.getMaterials().get(1).getQuantity());
        assertEquals(2, blueprintDetails.getManufacturingRequirements().size());
        assertEquals(1, blueprintDetails.getTimeProductivityRequirements().size());
        assertEquals(1, blueprintDetails.getMaterialProductivityRequirements().size());
        assertEquals(1, blueprintDetails.getCopyingRequirements().size());
        assertEquals(1, blueprintDetails.getReverseEngineeringRequirements().size());
        assertEquals(1, blueprintDetails.getInventionRequirements().size());
    }
}
