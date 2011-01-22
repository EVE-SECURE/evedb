SELECT
  it.typeID,
  it.typeName,
  ra.activityID,
  ra.activityName,
  ei.iconID typeIconID,
  ei.iconFile typeIcon,
  ig.groupID typeGroupID,
  ig.groupName typeGroupName,
  ic.categoryID typeCategoryID,
  ic.categoryName typeCategoryName,
  it_required.typeID requiredTypeID,
  it_required.typeName requiredTypeName,
  ei_required.iconID requiredTypeIconID,
  ei_required.iconFile requiredTypeIcon,
  ig_required.groupID requiredTypeGroupID,
  ig_required.groupName requiredTypeGroupName,
  ic_required.categoryID requiredTypeCategoryID,
  ic_required.categoryName requiredTypeCategoryName,
  it_required.volume requiredTypeVolume,
  rtr.quantity,
  rtr.damagePerJob,
  rtr.recycle,
  'inc110' dumpVersion
FROM
  ramTypeRequirements rtr
  LEFT JOIN ramActivities ra ON rtr.activityID = ra.activityID
  LEFT JOIN invTypes it ON rtr.typeID = it.typeID
  LEFT JOIN invGroups ig ON it.groupID = ig.groupID
  LEFT JOIN invCategories ic ON ig.categoryID = ic.categoryID
  LEFT JOIN invTypes it_required ON rtr.requiredTypeID = it_required.typeID
  LEFT JOIN invGroups ig_required ON it_required.groupID = ig_required.groupID
  LEFT JOIN invCategories ic_required ON ig_required.categoryID = ic_required.categoryID
  LEFT JOIN eveIcons ei ON it.iconID = ei.iconID
  LEFT JOIN eveIcons ei_required ON it_required.iconID = ei_required.iconID