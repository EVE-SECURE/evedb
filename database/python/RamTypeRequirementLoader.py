from google.appengine.ext import db
from google.appengine.tools import bulkloader

class RamTypeRequirement(db.Model):
    typeID = db.IntegerProperty()
    typeName = db.StringProperty()
    activityID = db.IntegerProperty(indexed=False)
    activityName = db.StringProperty(indexed=False)
    typeIconID = db.IntegerProperty(indexed=False)
    typeIcon = db.StringProperty(indexed=False)
    typeGroupID = db.IntegerProperty(indexed=False)
    typeGroupName = db.StringProperty(indexed=False)
    typeCategoryID = db.IntegerProperty(indexed=False)
    typeCategoryName = db.StringProperty(indexed=False)
    requiredTypeID = db.IntegerProperty()
    requiredTypeName = db.StringProperty(indexed=False)
    requiredTypeIconID = db.IntegerProperty(indexed=False)
    requiredTypeIcon = db.StringProperty(indexed=False)
    requiredTypeGroupID = db.IntegerProperty(indexed=False)
    requiredTypeGroupName = db.StringProperty(indexed=False)
    requiredTypeCategoryID = db.IntegerProperty(indexed=False)
    requiredTypeCategoryName = db.StringProperty(indexed=False)
    quantity = db.IntegerProperty(indexed=False)
    damagePerJob = db.FloatProperty(indexed=False)
    recycle = db.BooleanProperty(indexed=False)
    dumpVersion = db.StringProperty()

def safe_int(x):
    if x == "NULL":
        return None
    return int(x)

def safe_str(x):
    if x == "NULL":
        return None
    return x.decode('utf-8')

def safe_float(x):
    if x == "NULL":
        return None
    return float(x)

def safe_bool(x):
    if x == "NULL":
        return None
    elif x == "1":
        return True
    else:
        return False

class RamTypeRequirementLoader(bulkloader.Loader):
    def __init__(self):
        bulkloader.Loader.__init__(self, 'RamTypeRequirement',
                                   [('typeID', safe_int),
                                    ('typeName', safe_str),
                                    ('activityID', safe_int),
                                    ('activityName', safe_str),
                                    ('typeIconID', safe_int),
                                    ('typeIcon', safe_str),
                                    ('typeGroupID', safe_int),
                                    ('typeGroupName', safe_str),
                                    ('typeCategoryID', safe_int),
                                    ('typeCategoryName', safe_str),
                                    ('requiredTypeID', safe_int),
                                    ('requiredTypeName', safe_str),
                                    ('requiredTypeIconID', safe_int),
                                    ('requiredTypeIcon', safe_str),
                                    ('requiredTypeGroupID', safe_int),
                                    ('requiredTypeGroupName', safe_str),
                                    ('requiredTypeCategoryID', safe_int),
                                    ('requiredTypeCategoryName', safe_str),
                                    ('quantity', safe_int),
                                    ('damagePerJob', safe_float),
                                    ('recycle', safe_bool),
                                    ('dumpVersion', safe_str)
                                    ])

loaders = [RamTypeRequirementLoader]