from google.appengine.ext import db
from google.appengine.tools import bulkloader

class InvType(db.Model):
    typeID = db.IntegerProperty(indexed=False)
    typeName = db.StringProperty(indexed=False)
    typeIconID = db.IntegerProperty(indexed=False)
    typeIcon = db.StringProperty(indexed=False)
    groupID = db.IntegerProperty(indexed=False)
    groupName = db.StringProperty(indexed=False)
    categoryID = db.IntegerProperty(indexed=False)
    categoryName = db.StringProperty(indexed=False)
    metaLevel = db.IntegerProperty(indexed=False)
    parentTypeID = db.IntegerProperty(indexed=False)
    parentTypeName = db.StringProperty(indexed=False)
    mass = db.FloatProperty(indexed=False)
    volume = db.FloatProperty(indexed=False)
    capacity = db.FloatProperty(indexed=False)
    portionSize = db.IntegerProperty(indexed=False)
    published = db.BooleanProperty(indexed=False)
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

class InvTypeLoader(bulkloader.Loader):
    def __init__(self):
        bulkloader.Loader.__init__(self, 'InvType',
                                   [('typeID', safe_int),
                                    ('typeName', safe_str),
                                    ('typeIconID', safe_int),
                                    ('typeIcon', safe_str),
                                    ('groupID', safe_int),
                                    ('groupName', safe_str),
                                    ('categoryID', safe_int),
                                    ('categoryName', safe_str),
                                    ('metaLevel', safe_int),
                                    ('parentTypeID', safe_int),
                                    ('parentTypeName', safe_str),
                                    ('mass', safe_float),
                                    ('volume', safe_float),
                                    ('capacity', safe_float),
                                    ('portionSize', safe_int),
                                    ('published', safe_bool),
                                    ('dumpVersion', safe_str)
                                    ])

loaders = [InvTypeLoader]