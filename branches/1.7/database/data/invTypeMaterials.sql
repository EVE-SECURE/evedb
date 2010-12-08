SELECT
  it.typeID,
  it.typeName,
  ei.iconID typeIconID,
  ei.iconFile typeIcon,
  ig.groupID typeGroupID,
  ig.groupName typeGroupName,
  ic.categoryID typeCategoryID,
  ic.categoryName typeCategoryName,
  it_material.typeID materialTypeID,
  it_material.typeName materialTypeName,
  ei_material.iconID materialTypeIconID,
  ei_material.iconFile materialTypeIcon,
  ig_material.groupID materialTypeGroupID,
  ig_material.groupName materialTypeGroupName,
  ic_material.categoryID materialTypeCategoryID,
  ic_material.categoryName materialTypeCategoryName,
  it_material.volume materialVolume,
  itm.quantity,
  'inc100' dumpVersion
FROM
  invTypeMaterials itm
  LEFT JOIN invTypes it ON itm.typeID = it.typeID
  LEFT JOIN invGroups ig ON it.groupID = ig.groupID
  LEFT JOIN invCategories ic ON ig.categoryID = ic.categoryID
  LEFT JOIN invTypes it_material ON itm.materialTypeID = it_material.typeID
  LEFT JOIN invGroups ig_material ON it_material.groupID = ig_material.groupID
  LEFT JOIN invCategories ic_material ON ig_material.categoryID = ic_material.categoryID
  LEFT JOIN eveIcons ei ON it.iconID = ei.iconID
  LEFT JOIN eveIcons ei_material ON it_material.iconID = ei_material.iconID