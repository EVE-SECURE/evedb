SELECT
  it.typeID blueprintTypeID,
  it.typeName blueprintTypeName,
  it_product.typeID productTypeID,
  it_product.typeName productTypeName,
  ig_product.categoryID productCategoryID,
  ei_product.iconID productTypeIconID,
  ei_product.iconFile productTypeIcon,
  it_parent.typeID parentBlueprintTypeID,
  it_parent.typeName parentBlueprintTypeName,
  it_parent_product.typeID parentProductTypeID,
  it_parent_product.typeName parentProductTypeName,
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
  'inc110' dumpVersion
FROM
  invBlueprintTypes ibt
  LEFT JOIN (SELECT * FROM invMetaTypes WHERE metaGroupID = 2) imt ON ibt.productTypeID = imt.typeID -- Tech2 metagroup
  LEFT JOIN invBlueprintTypes ibt_parent ON imt.parentTypeID = ibt_parent.productTypeID
  LEFT JOIN invTypes it ON ibt.blueprintTypeID = it.typeID
  LEFT JOIN invTypes it_product ON ibt.productTypeID = it_product.typeID
  LEFT JOIN invTypes it_parent ON ibt_parent.blueprintTypeID = it_parent.typeID
  LEFT JOIN invTypes it_parent_product ON ibt_parent.productTypeID = it_parent_product.typeID
  LEFT JOIN invGroups ig_product ON it_product.groupID = ig_product.groupID
  LEFT JOIN eveIcons ei_product ON it_product.iconID = ei_product.iconID