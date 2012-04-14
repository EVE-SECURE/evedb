SELECT
  it.typeID,
  it.typeName,
  ei.iconID typeIconID,
  ei.iconFile typeIcon,
  ig.groupID,
  ig.groupName,
  ic.categoryID,
  ic.categoryName,
  dta.valueInt metaLevel,
  it_parent.typeID parentTypeID,
  it_parent.typeName parentTypeName,
  it.mass,
  it.volume,
  it.capacity,
  it.portionSize,
  it.published,
  'cru16' dumpVersion
FROM
  invTypes it
  LEFT JOIN invGroups ig ON it.groupID = ig.groupID
  LEFT JOIN invCategories ic ON ig.categoryID = ic.categoryID
  LEFT JOIN eveIcons ei ON it.iconID = ei.iconID
  LEFT JOIN (SELECT * FROM dgmTypeAttributes WHERE attributeID = 633) dta ON it.typeID = dta.typeID
  LEFT JOIN invMetaTypes imt ON it.typeID = imt.typeID
  LEFT JOIN invTypes it_parent ON imt.parentTypeID = it_parent.typeID