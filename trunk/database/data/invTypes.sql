SELECT
  it.typeID,
  it.typeName,
  ei.iconID typeIconID,
  ei.iconFile typeIcon,
  ig.groupID,
  ig.groupName,
  ic.categoryID,
  ic.categoryName,
  it.mass,
  it.volume,
  it.capacity,
  it.portionSize,
  it.published,
  'inc101' dumpVersion
FROM
  invTypes it
  LEFT JOIN invGroups ig ON it.groupID = ig.groupID
  LEFT JOIN invCategories ic ON ig.categoryID = ic.categoryID
  LEFT JOIN eveIcons ei ON it.iconID = ei.iconID