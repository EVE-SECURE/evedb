SELECT
  ps.schematicID,
  it_pstm.typeID schematicTypeID,
  it_pstm.typeName schematicTypeName,
  ig_pstm.groupID schematicGroupID,
  ig_pstm.groupName schematicGroupName,
  ic_pstm.categoryID schematicCategoryID,
  ic_pstm.categoryName schematicCategoryName,
  ei_pstm.iconID schematicIconID,
  ei_pstm.iconFile schematicIcon,
  ps.schematicName schematicName,
  ps.cycleTime schematicCycleTime,
  pstm.quantity schematicQuantity,
  it_pstm.volume schematicVolume,
  it_pstm2.typeID requiredTypeID,
  it_pstm2.typeName requredTypeName,
  ig_pstm2.groupID requiredGroupID,
  ig_pstm2.groupName requiredGroupName,
  ic_pstm2.categoryID requiredCategoryID,
  ic_pstm2.categoryName requiredCategoryName,
  ei_pstm2.iconID requiredIconID,
  ei_pstm2.iconFile schematicIcon,
  pstm2.quantity requiredQuantity,
  it_pstm2.volume requiredVolume,
  'inc100' dumpVersion
FROM
  planetSchematics ps
  LEFT JOIN (select * FROM planetSchematicsTypeMap where isInput=0) pstm ON ps.schematicID = pstm.schematicID
  LEFT JOIN planetSchematicsTypeMap pstm2 ON ps.schematicID = pstm2.schematicID
  LEFT JOIN invTypes it_pstm ON pstm.typeID = it_pstm.typeID
  LEFT JOIN invTypes it_pstm2 ON pstm2.typeID = it_pstm2.typeID
  LEFT JOIN invGroups ig_pstm ON it_pstm.groupID = ig_pstm.groupID
  LEFT JOIN invGroups ig_pstm2 ON it_pstm2.groupID = ig_pstm2.groupID
  LEFT JOIN invCategories ic_pstm ON ig_pstm.categoryID = ic_pstm.categoryID
  LEFT JOIN invCategories ic_pstm2 ON ig_pstm2.categoryID = ic_pstm2.categoryID
  LEFT JOIN eveIcons ei_pstm ON ei_pstm.iconID = it_pstm.iconID
  LEFT JOIN eveIcons ei_pstm2 ON ei_pstm2.iconID = it_pstm2.iconID
WHERE
  pstm2.isInput = 1