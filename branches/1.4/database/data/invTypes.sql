SELECT
  it.typeID,
  it.typeName,
  eg.graphicID typeGraphicID,
  eg.icon typeIcon,
  ig.groupID,
  ig.groupName,
  ic.categoryID,
  ic.categoryName,
  it.mass,
  it.volume,
  it.capacity,
  it.portionSize,
  it.published,
  "tyr101" dumpVersion
FROM
  invTypes it
  LEFT JOIN invGroups ig ON it.groupID = ig.groupID
  LEFT JOIN invCategories ic ON ig.categoryID = ic.categoryID
  LEFT JOIN eveGraphics eg ON it.graphicID = eg.graphicID