SELECT
  it.typeID blueprintTypeID,
  it.typeName blueprintTypeName,
  it_product.typeID productTypeID,
  it_product.typeName productTypeName,
  ig_product.categoryID productCategoryID,
  ei_product.iconID productTypeIconID,
  ei_product.iconFile productTypeIcon,
  ibt.productionTime,
  ibt.techLevel,
  ibt.researchProductivityTime,
  ibt.researchMaterialTime,
  ibt.researchCopyTime,
  ibt.researchTechTime,
  ibt.productivityModifier,
  ibt.materialModifier,
  ibt.wasteFactor,                                                                                                      
  it_product.volume productVolume,
  it_product.portionSize productPortionSize,
  ibt.maxProductionLimit,
  it.published published,
  'inc100' dumpVersion
FROM
  invBlueprintTypes ibt
  LEFT JOIN invTypes it ON ibt.blueprintTypeID = it.typeID
  LEFT JOIN invTypes it_product ON ibt.productTypeID = it_product.typeID
  LEFT JOIN invGroups ig_product ON it_product.groupID = ig_product.groupID
  LEFT JOIN eveIcons ei_product ON it_product.iconID = ei_product.iconID