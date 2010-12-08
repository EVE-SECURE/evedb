from google.appengine.ext import db
from google.appengine.tools import bulkloader

class InvTypeMaterial(db.Model):
    typeID = db.IntegerProperty()
    typeName = db.StringProperty()
    typeIconID = db.IntegerProperty(indexed=False)
    typeIcon = db.StringProperty(indexed=False)
    typeGroupID = db.IntegerProperty(indexed=False)
    typeGroupName = db.StringProperty(indexed=False)
    typeCategoryID = db.IntegerProperty(indexed=False)
    typeCategoryName = db.StringProperty(indexed=False)
    materialTypeID = db.IntegerProperty()
    materialTypeName = db.StringProperty(indexed=False)
    materialTypeIconID = db.IntegerProperty(indexed=False)
    materialTypeIcon = db.StringProperty(indexed=False)
    materialTypeGroupID = db.IntegerProperty(indexed=False)
    materialTypeGroupName = db.StringProperty(indexed=False)
    materialTypeCategoryID = db.IntegerProperty(indexed=False)
    materialTypeCategoryName = db.StringProperty(indexed=False)
    materialVolume = db.FloatProperty(indexed=False)
    quantity = db.IntegerProperty(indexed=False)
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

class InvTypeMaterialLoader(bulkloader.Loader):
    def __init__(self):
        bulkloader.Loader.__init__(self, 'InvTypeMaterial',
                                   [('typeID', safe_int),
                                    ('typeName', safe_str),
                                    ('typeIconID', safe_int),
                                    ('typeIcon', safe_str),
                                    ('typeGroupID', safe_int),
                                    ('typeGroupName', safe_str),
                                    ('typeCategoryID', safe_int),
                                    ('typeCategoryName', safe_str),
                                    ('materialTypeID', safe_int),
                                    ('materialTypeName', safe_str),
                                    ('materialTypeIconID', safe_int),
                                    ('materialTypeIcon', safe_str),
                                    ('materialTypeGroupID', safe_int),
                                    ('materialTypeGroupName', safe_str),
                                    ('materialTypeCategoryID', safe_int),
                                    ('materialTypeCategoryName', safe_str),
                                    ('materialVolume', safe_float),
                                    ('quantity', safe_int),
                                    ('dumpVersion', safe_str)
                                    ])

loaders = [InvTypeMaterialLoader]