from google.appengine.ext import db
from google.appengine.tools import bulkloader

class InvBlueprintType(db.Model):
    blueprintTypeID = db.IntegerProperty(indexed=False)
    blueprintTypeName = db.StringProperty(indexed=False)
    productTypeID = db.IntegerProperty(indexed=False)
    productTypeName = db.StringProperty(indexed=False)
    productCategoryID = db.IntegerProperty(indexed=False)
    productTypeIconID = db.IntegerProperty(indexed=False)
    productTypeIcon = db.StringProperty(indexed=False)
    parentBlueprintTypeID = db.IntegerProperty(indexed=False)
    parentBlueprintTypeName = db.StringProperty(indexed=False)
    parentProductTypeID = db.IntegerProperty(indexed=False)
    parentProductTypeName = db.StringProperty(indexed=False)
    productionTime = db.IntegerProperty(indexed=False)
    techLevel = db.IntegerProperty(indexed=False)
    researchProductivityTime = db.IntegerProperty(indexed=False)
    researchMaterialTime = db.IntegerProperty(indexed=False)
    researchCopyTime = db.IntegerProperty(indexed=False)
    researchTechTime = db.IntegerProperty(indexed=False)
    productivityModifier = db.IntegerProperty(indexed=False)
    materialModifier = db.IntegerProperty(indexed=False)
    wasteFactor = db.IntegerProperty(indexed=False)
    productVolume = db.FloatProperty(indexed=False)
    productPortionSize = db.IntegerProperty(indexed=False)
    maxProductionLimit = db.IntegerProperty(indexed=False)
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

class InvBlueprintTypeLoader(bulkloader.Loader):
    def __init__(self):
        bulkloader.Loader.__init__(self, 'InvBlueprintType',
                                   [('blueprintTypeID', safe_int),
                                    ('blueprintTypeName', safe_str),
                                    ('productTypeID', safe_int),
                                    ('productTypeName', safe_str),
                                    ('productCategoryID', safe_int),
                                    ('productTypeIconID', safe_int),
                                    ('productTypeIcon', safe_str),
                                    ('parentBlueprintTypeID', safe_int),
                                    ('parentBlueprintTypeName', safe_str),
                                    ('parentProductTypeID', safe_int),
                                    ('parentProductTypeName', safe_str),
                                    ('productionTime', safe_int),
                                    ('techLevel', safe_int),
                                    ('researchProductivityTime', safe_int),
                                    ('researchMaterialTime', safe_int),
                                    ('researchCopyTime', safe_int),
                                    ('researchTechTime', safe_int),
                                    ('productivityModifier', safe_int),
                                    ('materialModifier', safe_int),
                                    ('wasteFactor', safe_int),
                                    ('productVolume', safe_float),
                                    ('productPortionSize', safe_int),
                                    ('maxProductionLimit', safe_int),
                                    ('published', safe_bool),
                                    ('dumpVersion', safe_str)
                                    ])

loaders = [InvBlueprintTypeLoader]