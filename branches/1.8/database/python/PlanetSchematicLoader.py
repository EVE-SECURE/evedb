from google.appengine.ext import db
from google.appengine.tools import bulkloader

class PlanetSchematic(db.Model):
    schematicID = db.IntegerProperty(indexed=False)
    schematicTypeID = db.IntegerProperty(indexed=False)
    schematicTypeName = db.StringProperty(indexed=False)
    schematicGroupID = db.IntegerProperty(indexed=False)
    schematicGroupName = db.StringProperty(indexed=False)
    schematicCategoryID = db.IntegerProperty(indexed=False)
    schematicCategoryName = db.StringProperty(indexed=False)
    schematicIconID = db.IntegerProperty(indexed=False)
    schematicIcon = db.StringProperty(indexed=False)
    schematicName = db.StringProperty(indexed=False)
    schematicCycleTime = db.IntegerProperty(indexed=False)
    schematicQuantity = db.IntegerProperty(indexed=False)
    schematicVolume = db.FloatProperty(indexed=False)
    requiredTypeID = db.IntegerProperty(indexed=False)
    requiredTypeName = db.StringProperty(indexed=False)
    requiredGroupID = db.IntegerProperty(indexed=False)
    requiredGroupName = db.StringProperty(indexed=False)
    requiredCategoryID = db.IntegerProperty(indexed=False)
    requiredCategoryName = db.StringProperty(indexed=False)
    requiredIconID = db.IntegerProperty(indexed=False)
    requiredIcon = db.StringProperty(indexed=False)
    requiredQuantity = db.IntegerProperty(indexed=False)
    requiredVolume = db.FloatProperty(indexed=False)
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

class PlanetSchematicLoader(bulkloader.Loader):
    def __init__(self):
        bulkloader.Loader.__init__(self, 'PlanetSchematic',
                                   [('schematicID', safe_int),
                                    ('schematicTypeID', safe_int),
                                    ('schematicTypeName', safe_str),
                                    ('schematicGroupID', safe_int),
                                    ('schematicGroupName', safe_str),
                                    ('schematicCategoryID', safe_int),
                                    ('schematicCategoryName', safe_str),
                                    ('schematicIconID', safe_int),
                                    ('schematicIcon', safe_str),
                                    ('schematicName', safe_str),
                                    ('schematicCycleTime', safe_int),
                                    ('schematicQuantity', safe_int),
                                    ('schematicVolume', safe_float),
                                    ('requiredTypeID', safe_int),
                                    ('requiredTypeName', safe_str),
                                    ('requiredGroupID', safe_int),
                                    ('requiredGroupName', safe_str),
                                    ('requiredCategoryID', safe_int),
                                    ('requiredCategoryName', safe_str),
                                    ('requiredIconID', safe_int),
                                    ('requiredIcon', safe_str),
                                    ('requiredQuantity', safe_int),
                                    ('requiredVolume', safe_float),
                                    ('dumpVersion', safe_str)
                                    ])

loaders = [PlanetSchematicLoader]