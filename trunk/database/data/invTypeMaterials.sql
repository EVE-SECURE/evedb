SELECT
  it.typeID,
  it.typeName,
  eg.graphicID typeGraphicID,
  eg.icon typeIcon,
  ig.groupID typeGroupID,
  ig.groupName typeGroupName,
  ic.categoryID typeCategoryID,
  ic.categoryName typeCategoryName,
  it_material.typeID materialTypeID,
  it_material.typeName materialTypeName,
  eg_material.graphicID materialTypeGraphicID,
  eg_material.icon materialTypeIcon,
  ig_material.groupID materialTypeGroupID,
  ig_material.groupName materialTypeGroupName,
  ic_material.categoryID materialTypeCategoryID,
  ic_material.categoryName materialTypeCategoryName,
  itm.quantity,
  "tyr101" dumpVersion
FROM
  invTypeMaterials itm
  LEFT JOIN invTypes it ON itm.typeID = it.typeID
  LEFT JOIN invGroups ig ON it.groupID = ig.groupID
  LEFT JOIN invCategories ic ON ig.categoryID = ic.categoryID
  LEFT JOIN invTypes it_material ON itm.materialTypeID = it_material.typeID
  LEFT JOIN invGroups ig_material ON it_material.groupID = ig_material.groupID
  LEFT JOIN invCategories ic_material ON ig_material.categoryID = ic_material.categoryID
  LEFT JOIN eveGraphics eg ON it.graphicID = eg.graphicID
  LEFT JOIN eveGraphics eg_material ON it_material.graphicID = eg_material.graphicID