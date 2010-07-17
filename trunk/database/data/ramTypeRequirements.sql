SELECT
  it.typeID,
  it.typeName,
  ra.activityID,
  ra.activityName,
  eg.graphicID typeGraphicID,
  eg.icon typeIcon,
  ig.groupID typeGroupID,
  ig.groupName typeGroupName,
  ic.categoryID typeCategoryID,
  ic.categoryName typeCategoryName,
  it_required.typeID requiredTypeID,
  it_required.typeName requiredTypeName,
  eg_required.graphicID requiredTypeGraphicID,
  eg_required.icon requiredTypeIcon,
  ig_required.groupID requiredTypeGroupID,
  ig_required.groupName requiredTypeGroupName,
  ic_required.categoryID requiredTypeCategoryID,
  ic_required.categoryName requiredTypeCategoryName,
  rtr.quantity,
  rtr.damagePerJob,
  rtr.recycle,
  "tyr101" dumpVersion
FROM
  ramTypeRequirements rtr
  LEFT JOIN ramActivities ra ON rtr.activityID = ra.activityID
  LEFT JOIN invTypes it ON rtr.typeID = it.typeID
  LEFT JOIN invGroups ig ON it.groupID = ig.groupID
  LEFT JOIN invCategories ic ON ig.categoryID = ic.categoryID
  LEFT JOIN invTypes it_required ON rtr.requiredTypeID = it_required.typeID
  LEFT JOIN invGroups ig_required ON it_required.groupID = ig_required.groupID
  LEFT JOIN invCategories ic_required ON ig_required.categoryID = ic_required.categoryID
  LEFT JOIN eveGraphics eg ON it.graphicID = eg.graphicID
  LEFT JOIN eveGraphics eg_required ON it_required.graphicID = eg_required.graphicID