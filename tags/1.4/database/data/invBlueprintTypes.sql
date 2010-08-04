SELECT
  it.typeID blueprintTypeID,
  it.typeName blueprintTypeName,
  it_product.typeID productTypeID,
  it_product.typeName productTypeName,
  ig_product.categoryID productCategoryID,
  eg_product.graphicID productGraphicID,
  eg_product.icon productTypeIcon,
  ibt.productionTime,
  ibt.techLevel,
  ibt.researchProductivityTime,
  ibt.researchMaterialTime,
  ibt.researchCopyTime,
  ibt.researchTechTime,
  ibt.productivityModifier,
  ibt.materialModifier,
  ibt.wasteFactor,
  ibt.maxProductionLimit,
  it.published published,
  "tyr101" dumpVersion
FROM
  invBlueprintTypes ibt
  LEFT JOIN invTypes it ON ibt.blueprintTypeID = it.typeID
  LEFT JOIN invTypes it_product ON ibt.productTypeID = it_product.typeID
  LEFT JOIN invGroups ig_product ON it_product.groupID = ig_product.groupID
  LEFT JOIN eveGraphics eg_product ON it_product.graphicID = eg_product.graphicID