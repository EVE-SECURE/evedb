package lv.odylab.evedb;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.google.appengine.repackaged.com.google.common.base.X.assertTrue;
import static junit.framework.Assert.assertEquals;

public class ProductionTest {
    private final String baseUrl = "http://odylab-evedb.appspot.com";

    @Test
    public void test_clearCache() throws Exception {
        URL url = new URL(baseUrl + "/admin/clearCache");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        assertEquals(302, connection.getResponseCode());
    }

    @Test
    public void test_baseMaterialsForTypeID() throws Exception {
        URL url = new URL(baseUrl + "/baseMaterialsForTypeID/587");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("[{\"materialTypeID\":34,\"materialTypeName\":\"Tritanium\",\"materialTypeCategoryID\":4,\"quantity\":20524,\"materialTypeIcon\":\"06_14\"},{\"materialTypeID\":35,\"materialTypeName\":\"Pyerite\",\"materialTypeCategoryID\":4,\"quantity\":5529,\"materialTypeIcon\":\"06_15\"},{\"materialTypeID\":36,\"materialTypeName\":\"Mexallon\",\"materialTypeCategoryID\":4,\"quantity\":1841,\"materialTypeIcon\":\"06_12\"},{\"materialTypeID\":37,\"materialTypeName\":\"Isogen\",\"materialTypeCategoryID\":4,\"quantity\":317,\"materialTypeIcon\":\"06_16\"},{\"materialTypeID\":38,\"materialTypeName\":\"Nocxium\",\"materialTypeCategoryID\":4,\"quantity\":118,\"materialTypeIcon\":\"11_09\"},{\"materialTypeID\":39,\"materialTypeName\":\"Zydrine\",\"materialTypeCategoryID\":4,\"quantity\":13,\"materialTypeIcon\":\"11_11\"},{\"materialTypeID\":40,\"materialTypeName\":\"Megacyte\",\"materialTypeCategoryID\":4,\"quantity\":1,\"materialTypeIcon\":\"11_10\"}]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>34</materialTypeID><materialTypeIcon>06_14</materialTypeIcon><materialTypeName>Tritanium</materialTypeName><quantity>20524</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>35</materialTypeID><materialTypeIcon>06_15</materialTypeIcon><materialTypeName>Pyerite</materialTypeName><quantity>5529</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>36</materialTypeID><materialTypeIcon>06_12</materialTypeIcon><materialTypeName>Mexallon</materialTypeName><quantity>1841</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>37</materialTypeID><materialTypeIcon>06_16</materialTypeIcon><materialTypeName>Isogen</materialTypeName><quantity>317</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>38</materialTypeID><materialTypeIcon>11_09</materialTypeIcon><materialTypeName>Nocxium</materialTypeName><quantity>118</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>39</materialTypeID><materialTypeIcon>11_11</materialTypeIcon><materialTypeName>Zydrine</materialTypeName><quantity>13</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>40</materialTypeID><materialTypeIcon>11_10</materialTypeIcon><materialTypeName>Megacyte</materialTypeName><quantity>1</quantity></row></rowset>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_baseMaterialsForTypeID_NonExistingID() throws Exception {
        URL url = new URL(baseUrl + "/baseMaterialsForTypeID/1234567890");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_baseMaterialsForTypeID_StringInsteadOfID() throws Exception {
        URL url = new URL(baseUrl + "/baseMaterialsForTypeID/blah-blah");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_baseMaterialsForTypeName() throws Exception {
        URL url = new URL(baseUrl + "/baseMaterialsForTypeName/Rifter");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("[{\"materialTypeID\":34,\"materialTypeName\":\"Tritanium\",\"materialTypeCategoryID\":4,\"quantity\":20524,\"materialTypeIcon\":\"06_14\"},{\"materialTypeID\":35,\"materialTypeName\":\"Pyerite\",\"materialTypeCategoryID\":4,\"quantity\":5529,\"materialTypeIcon\":\"06_15\"},{\"materialTypeID\":36,\"materialTypeName\":\"Mexallon\",\"materialTypeCategoryID\":4,\"quantity\":1841,\"materialTypeIcon\":\"06_12\"},{\"materialTypeID\":37,\"materialTypeName\":\"Isogen\",\"materialTypeCategoryID\":4,\"quantity\":317,\"materialTypeIcon\":\"06_16\"},{\"materialTypeID\":38,\"materialTypeName\":\"Nocxium\",\"materialTypeCategoryID\":4,\"quantity\":118,\"materialTypeIcon\":\"11_09\"},{\"materialTypeID\":39,\"materialTypeName\":\"Zydrine\",\"materialTypeCategoryID\":4,\"quantity\":13,\"materialTypeIcon\":\"11_11\"},{\"materialTypeID\":40,\"materialTypeName\":\"Megacyte\",\"materialTypeCategoryID\":4,\"quantity\":1,\"materialTypeIcon\":\"11_10\"}]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>34</materialTypeID><materialTypeIcon>06_14</materialTypeIcon><materialTypeName>Tritanium</materialTypeName><quantity>20524</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>35</materialTypeID><materialTypeIcon>06_15</materialTypeIcon><materialTypeName>Pyerite</materialTypeName><quantity>5529</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>36</materialTypeID><materialTypeIcon>06_12</materialTypeIcon><materialTypeName>Mexallon</materialTypeName><quantity>1841</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>37</materialTypeID><materialTypeIcon>06_16</materialTypeIcon><materialTypeName>Isogen</materialTypeName><quantity>317</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>38</materialTypeID><materialTypeIcon>11_09</materialTypeIcon><materialTypeName>Nocxium</materialTypeName><quantity>118</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>39</materialTypeID><materialTypeIcon>11_11</materialTypeIcon><materialTypeName>Zydrine</materialTypeName><quantity>13</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>40</materialTypeID><materialTypeIcon>11_10</materialTypeIcon><materialTypeName>Megacyte</materialTypeName><quantity>1</quantity></row></rowset>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_baseMaterialsForTypeName_NonExistingName() throws Exception {
        URL url = new URL(baseUrl + "/baseMaterialsForTypeName/blah-blah");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_blueprintTypeByTypeID() throws Exception {
        URL url = new URL(baseUrl + "/blueprintTypeByTypeID/20188");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("{\"blueprintTypeID\":20188,\"blueprintTypeName\":\"Obelisk Blueprint\",\"productTypeID\":20187,\"productTypeName\":\"Obelisk\",\"productCategoryID\":6,\"techLevel\":1,\"productionTime\":1280000,\"researchProductivityTime\":7680000,\"researchMaterialTime\":5120000,\"researchCopyTime\":2560000,\"researchTechTime\":500000,\"productivityModifier\":256000,\"wasteFactor\":10,\"maxProductionLimit\":1}", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invBlueprintTypeDto><blueprintTypeID>20188</blueprintTypeID><blueprintTypeName>Obelisk Blueprint</blueprintTypeName><maxProductionLimit>1</maxProductionLimit><productCategoryID>6</productCategoryID><productTypeID>20187</productTypeID><productTypeName>Obelisk</productTypeName><productionTime>1280000</productionTime><productivityModifier>256000</productivityModifier><researchCopyTime>2560000</researchCopyTime><researchMaterialTime>5120000</researchMaterialTime><researchProductivityTime>7680000</researchProductivityTime><researchTechTime>500000</researchTechTime><techLevel>1</techLevel><wasteFactor>10</wasteFactor></invBlueprintTypeDto>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_blueprintTypeByTypeID_NonExistingID() throws Exception {
        URL url = new URL(baseUrl + "/blueprintTypeByTypeID/1234567890");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_blueprintTypeByTypeID_StringInsteadOfID() throws Exception {
        URL url = new URL(baseUrl + "/blueprintTypeByTypeID/blah-blah");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_blueprintTypeByTypeName() throws Exception {
        URL url = new URL(baseUrl + "/blueprintTypeByTypeName/Obelisk+Blueprint");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("{\"blueprintTypeID\":20188,\"blueprintTypeName\":\"Obelisk Blueprint\",\"productTypeID\":20187,\"productTypeName\":\"Obelisk\",\"productCategoryID\":6,\"techLevel\":1,\"productionTime\":1280000,\"researchProductivityTime\":7680000,\"researchMaterialTime\":5120000,\"researchCopyTime\":2560000,\"researchTechTime\":500000,\"productivityModifier\":256000,\"wasteFactor\":10,\"maxProductionLimit\":1}", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invBlueprintTypeDto><blueprintTypeID>20188</blueprintTypeID><blueprintTypeName>Obelisk Blueprint</blueprintTypeName><maxProductionLimit>1</maxProductionLimit><productCategoryID>6</productCategoryID><productTypeID>20187</productTypeID><productTypeName>Obelisk</productTypeName><productionTime>1280000</productionTime><productivityModifier>256000</productivityModifier><researchCopyTime>2560000</researchCopyTime><researchMaterialTime>5120000</researchMaterialTime><researchProductivityTime>7680000</researchProductivityTime><researchTechTime>500000</researchTechTime><techLevel>1</techLevel><wasteFactor>10</wasteFactor></invBlueprintTypeDto>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/blueprintTypeByTypeName/Obelisk%20Blueprint");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("{\"blueprintTypeID\":20188,\"blueprintTypeName\":\"Obelisk Blueprint\",\"productTypeID\":20187,\"productTypeName\":\"Obelisk\",\"productCategoryID\":6,\"techLevel\":1,\"productionTime\":1280000,\"researchProductivityTime\":7680000,\"researchMaterialTime\":5120000,\"researchCopyTime\":2560000,\"researchTechTime\":500000,\"productivityModifier\":256000,\"wasteFactor\":10,\"maxProductionLimit\":1}", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invBlueprintTypeDto><blueprintTypeID>20188</blueprintTypeID><blueprintTypeName>Obelisk Blueprint</blueprintTypeName><maxProductionLimit>1</maxProductionLimit><productCategoryID>6</productCategoryID><productTypeID>20187</productTypeID><productTypeName>Obelisk</productTypeName><productionTime>1280000</productionTime><productivityModifier>256000</productivityModifier><researchCopyTime>2560000</researchCopyTime><researchMaterialTime>5120000</researchMaterialTime><researchProductivityTime>7680000</researchProductivityTime><researchTechTime>500000</researchTechTime><techLevel>1</techLevel><wasteFactor>10</wasteFactor></invBlueprintTypeDto>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/blueprintTypeByTypeName/Obelisk Blueprint");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_blueprintTypeByTypeName_NonExistingName() throws Exception {
        URL url = new URL(baseUrl + "/blueprintTypeByTypeName/blah-blah");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_blueprintDetailsForTypeID() throws Exception {
        URL url = new URL(baseUrl + "/blueprintDetailsForTypeID/28849");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("{\"invBlueprintTypeDto\":{\"blueprintTypeID\":28849,\"blueprintTypeName\":\"Anshar Blueprint\",\"productTypeID\":28848,\"productTypeName\":\"Anshar\",\"productCategoryID\":6,\"techLevel\":2,\"productionTime\":1920000,\"researchProductivityTime\":11520000,\"researchMaterialTime\":7680000,\"researchCopyTime\":2560000,\"researchTechTime\":3840000,\"productivityModifier\":384000,\"wasteFactor\":10,\"maxProductionLimit\":10},\"materialDtos\":[{\"materialTypeID\":3828,\"materialTypeName\":\"Construction Blocks\",\"materialTypeCategoryID\":43,\"quantity\":1500,\"materialTypeIcon\":\"06_05\"},{\"materialTypeID\":11399,\"materialTypeName\":\"Morphite\",\"materialTypeCategoryID\":4,\"quantity\":2500,\"materialTypeIcon\":\"35_02\"},{\"materialTypeID\":21025,\"materialTypeName\":\"Capital Jump Drive\",\"materialTypeCategoryID\":17,\"quantity\":20,\"materialTypeIcon\":\"54_08\"},{\"materialTypeID\":29041,\"materialTypeName\":\"Capital Crystalline Carbonide Armor Plate\",\"materialTypeCategoryID\":17,\"quantity\":1013,\"materialTypeIcon\":\"37_14\"},{\"materialTypeID\":29051,\"materialTypeName\":\"Capital Fusion Reactor Unit\",\"materialTypeCategoryID\":17,\"quantity\":518,\"materialTypeIcon\":\"39_02\"},{\"materialTypeID\":29061,\"materialTypeName\":\"Capital Ion Thruster\",\"materialTypeCategoryID\":17,\"quantity\":422,\"materialTypeIcon\":\"37_02\"},{\"materialTypeID\":29069,\"materialTypeName\":\"Capital Magnetometric Sensor Cluster\",\"materialTypeCategoryID\":17,\"quantity\":443,\"materialTypeIcon\":\"37_06\"},{\"materialTypeID\":29081,\"materialTypeName\":\"Capital Oscillator Capacitor Unit\",\"materialTypeCategoryID\":17,\"quantity\":704,\"materialTypeIcon\":\"39_06\"},{\"materialTypeID\":29085,\"materialTypeName\":\"Capital Photon Microprocessor\",\"materialTypeCategoryID\":17,\"quantity\":704,\"materialTypeIcon\":\"37_10\"},{\"materialTypeID\":29091,\"materialTypeName\":\"Capital Pulse Shield Emitter\",\"materialTypeCategoryID\":17,\"quantity\":449,\"materialTypeIcon\":\"39_10\"}],\"manufacturingRequirementDtos\":[{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":3380,\"requiredTypeName\":\"Industry\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":268,\"requiredTypeGroupName\":\"Industry\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":11450,\"requiredTypeName\":\"Gallentean Starship Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":4,\"damagePerJob\":\"0.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":11452,\"requiredTypeName\":\"Mechanical Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":4,\"damagePerJob\":\"0.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":11478,\"requiredTypeName\":\"R.A.M.- Starship Tech\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":332,\"requiredTypeGroupName\":\"Tool\",\"requiredTypeIcon\":\"41_02\",\"quantity\":40,\"damagePerJob\":\"0.95\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":20187,\"requiredTypeName\":\"Obelisk\",\"requiredTypeCategoryID\":6,\"requiredTypeGroupID\":513,\"requiredTypeGroupName\":\"Freighter\",\"quantity\":1,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":22242,\"requiredTypeName\":\"Capital Ship Construction\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":269,\"requiredTypeGroupName\":\"Mechanic\",\"requiredTypeIcon\":\"50_11\",\"quantity\":4,\"damagePerJob\":\"0.0\"}],\"timeProductivityRequirementDtos\":[{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":3403,\"requiredTypeName\":\"Research\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":3814,\"requiredTypeName\":\"Reports\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":280,\"requiredTypeGroupName\":\"General\",\"requiredTypeIcon\":\"10_16\",\"quantity\":120,\"damagePerJob\":\"1.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":9836,\"requiredTypeName\":\"Consumer Electronics\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1034,\"requiredTypeGroupName\":\"Refined Commodities\",\"requiredTypeIcon\":\"24_08\",\"quantity\":100,\"damagePerJob\":\"1.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":11450,\"requiredTypeName\":\"Gallentean Starship Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":11452,\"requiredTypeName\":\"Mechanical Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":11466,\"requiredTypeName\":\"R.Db - CreoDron\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":332,\"requiredTypeGroupName\":\"Tool\",\"requiredTypeIcon\":\"41_01\",\"quantity\":30,\"damagePerJob\":\"0.8\"}],\"materialProductivityRequirementDtos\":[{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":3409,\"requiredTypeName\":\"Metallurgy\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":3814,\"requiredTypeName\":\"Reports\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":280,\"requiredTypeGroupName\":\"General\",\"requiredTypeIcon\":\"10_16\",\"quantity\":140,\"damagePerJob\":\"1.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":9836,\"requiredTypeName\":\"Consumer Electronics\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1034,\"requiredTypeGroupName\":\"Refined Commodities\",\"requiredTypeIcon\":\"24_08\",\"quantity\":100,\"damagePerJob\":\"1.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":11450,\"requiredTypeName\":\"Gallentean Starship Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":11452,\"requiredTypeName\":\"Mechanical Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":11466,\"requiredTypeName\":\"R.Db - CreoDron\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":332,\"requiredTypeGroupName\":\"Tool\",\"requiredTypeIcon\":\"41_01\",\"quantity\":30,\"damagePerJob\":\"0.6\"}],\"copyingRequirementDtos\":[{\"activityID\":5,\"activityName\":\"Copying\",\"requiredTypeID\":3812,\"requiredTypeName\":\"Data Sheets\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":280,\"requiredTypeGroupName\":\"General\",\"requiredTypeIcon\":\"10_16\",\"quantity\":600,\"damagePerJob\":\"1.0\"},{\"activityID\":5,\"activityName\":\"Copying\",\"requiredTypeID\":11450,\"requiredTypeName\":\"Gallentean Starship Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":5,\"activityName\":\"Copying\",\"requiredTypeID\":11452,\"requiredTypeName\":\"Mechanical Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":5,\"activityName\":\"Copying\",\"requiredTypeID\":11466,\"requiredTypeName\":\"R.Db - CreoDron\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":332,\"requiredTypeGroupName\":\"Tool\",\"requiredTypeIcon\":\"41_01\",\"quantity\":40,\"damagePerJob\":\"0.95\"}],\"reverseEngineeringRequirementDtos\":[],\"inventionRequirementDtos\":[]}", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><blueprintDetailsDto><copyingRequirementDtos><activityID>5</activityID><activityName>Copying</activityName><damagePerJob>1.0</damagePerJob><quantity>600</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>280</requiredTypeGroupID><requiredTypeGroupName>General</requiredTypeGroupName><requiredTypeID>3812</requiredTypeID><requiredTypeIcon>10_16</requiredTypeIcon><requiredTypeName>Data Sheets</requiredTypeName></copyingRequirementDtos><copyingRequirementDtos><activityID>5</activityID><activityName>Copying</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11450</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Gallentean Starship Engineering</requiredTypeName></copyingRequirementDtos><copyingRequirementDtos><activityID>5</activityID><activityName>Copying</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11452</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Mechanical Engineering</requiredTypeName></copyingRequirementDtos><copyingRequirementDtos><activityID>5</activityID><activityName>Copying</activityName><damagePerJob>0.95</damagePerJob><quantity>40</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>332</requiredTypeGroupID><requiredTypeGroupName>Tool</requiredTypeGroupName><requiredTypeID>11466</requiredTypeID><requiredTypeIcon>41_01</requiredTypeIcon><requiredTypeName>R.Db - CreoDron</requiredTypeName></copyingRequirementDtos><invBlueprintTypeDto><blueprintTypeID>28849</blueprintTypeID><blueprintTypeName>Anshar Blueprint</blueprintTypeName><maxProductionLimit>10</maxProductionLimit><productCategoryID>6</productCategoryID><productTypeID>28848</productTypeID><productTypeName>Anshar</productTypeName><productionTime>1920000</productionTime><productivityModifier>384000</productivityModifier><researchCopyTime>2560000</researchCopyTime><researchMaterialTime>7680000</researchMaterialTime><researchProductivityTime>11520000</researchProductivityTime><researchTechTime>3840000</researchTechTime><techLevel>2</techLevel><wasteFactor>10</wasteFactor></invBlueprintTypeDto><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>268</requiredTypeGroupID><requiredTypeGroupName>Industry</requiredTypeGroupName><requiredTypeID>3380</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Industry</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>4</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11450</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Gallentean Starship Engineering</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>4</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11452</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Mechanical Engineering</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.95</damagePerJob><quantity>40</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>332</requiredTypeGroupID><requiredTypeGroupName>Tool</requiredTypeGroupName><requiredTypeID>11478</requiredTypeID><requiredTypeIcon>41_02</requiredTypeIcon><requiredTypeName>R.A.M.- Starship Tech</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>1.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>6</requiredTypeCategoryID><requiredTypeGroupID>513</requiredTypeGroupID><requiredTypeGroupName>Freighter</requiredTypeGroupName><requiredTypeID>20187</requiredTypeID><requiredTypeName>Obelisk</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>4</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>269</requiredTypeGroupID><requiredTypeGroupName>Mechanic</requiredTypeGroupName><requiredTypeID>22242</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Capital Ship Construction</requiredTypeName></manufacturingRequirementDtos><materialDtos><materialTypeCategoryID>43</materialTypeCategoryID><materialTypeID>3828</materialTypeID><materialTypeIcon>06_05</materialTypeIcon><materialTypeName>Construction Blocks</materialTypeName><quantity>1500</quantity></materialDtos><materialDtos><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>11399</materialTypeID><materialTypeIcon>35_02</materialTypeIcon><materialTypeName>Morphite</materialTypeName><quantity>2500</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>21025</materialTypeID><materialTypeIcon>54_08</materialTypeIcon><materialTypeName>Capital Jump Drive</materialTypeName><quantity>20</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29041</materialTypeID><materialTypeIcon>37_14</materialTypeIcon><materialTypeName>Capital Crystalline Carbonide Armor Plate</materialTypeName><quantity>1013</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29051</materialTypeID><materialTypeIcon>39_02</materialTypeIcon><materialTypeName>Capital Fusion Reactor Unit</materialTypeName><quantity>518</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29061</materialTypeID><materialTypeIcon>37_02</materialTypeIcon><materialTypeName>Capital Ion Thruster</materialTypeName><quantity>422</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29069</materialTypeID><materialTypeIcon>37_06</materialTypeIcon><materialTypeName>Capital Magnetometric Sensor Cluster</materialTypeName><quantity>443</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29081</materialTypeID><materialTypeIcon>39_06</materialTypeIcon><materialTypeName>Capital Oscillator Capacitor Unit</materialTypeName><quantity>704</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29085</materialTypeID><materialTypeIcon>37_10</materialTypeIcon><materialTypeName>Capital Photon Microprocessor</materialTypeName><quantity>704</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29091</materialTypeID><materialTypeIcon>39_10</materialTypeIcon><materialTypeName>Capital Pulse Shield Emitter</materialTypeName><quantity>449</quantity></materialDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>3409</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Metallurgy</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>1.0</damagePerJob><quantity>140</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>280</requiredTypeGroupID><requiredTypeGroupName>General</requiredTypeGroupName><requiredTypeID>3814</requiredTypeID><requiredTypeIcon>10_16</requiredTypeIcon><requiredTypeName>Reports</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>1.0</damagePerJob><quantity>100</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1034</requiredTypeGroupID><requiredTypeGroupName>Refined Commodities</requiredTypeGroupName><requiredTypeID>9836</requiredTypeID><requiredTypeIcon>24_08</requiredTypeIcon><requiredTypeName>Consumer Electronics</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11450</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Gallentean Starship Engineering</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11452</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Mechanical Engineering</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>0.6</damagePerJob><quantity>30</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>332</requiredTypeGroupID><requiredTypeGroupName>Tool</requiredTypeGroupName><requiredTypeID>11466</requiredTypeID><requiredTypeIcon>41_01</requiredTypeIcon><requiredTypeName>R.Db - CreoDron</requiredTypeName></materialProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>3403</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Research</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>1.0</damagePerJob><quantity>120</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>280</requiredTypeGroupID><requiredTypeGroupName>General</requiredTypeGroupName><requiredTypeID>3814</requiredTypeID><requiredTypeIcon>10_16</requiredTypeIcon><requiredTypeName>Reports</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>1.0</damagePerJob><quantity>100</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1034</requiredTypeGroupID><requiredTypeGroupName>Refined Commodities</requiredTypeGroupName><requiredTypeID>9836</requiredTypeID><requiredTypeIcon>24_08</requiredTypeIcon><requiredTypeName>Consumer Electronics</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11450</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Gallentean Starship Engineering</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11452</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Mechanical Engineering</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>0.8</damagePerJob><quantity>30</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>332</requiredTypeGroupID><requiredTypeGroupName>Tool</requiredTypeGroupName><requiredTypeID>11466</requiredTypeID><requiredTypeIcon>41_01</requiredTypeIcon><requiredTypeName>R.Db - CreoDron</requiredTypeName></timeProductivityRequirementDtos></blueprintDetailsDto>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_blueprintDetailsForTypeID_NonExistingID() throws Exception {
        URL url = new URL(baseUrl + "/blueprintDetailsForTypeID/1234567890");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_blueprintDetailsForTypeID_StringInsteadOfID() throws Exception {
        URL url = new URL(baseUrl + "/blueprintDetailsForTypeID/blah-blah");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_blueprintDetailsForTypeName() throws Exception {
        URL url = new URL(baseUrl + "/blueprintDetailsForTypeName/Anshar+Blueprint");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("{\"invBlueprintTypeDto\":{\"blueprintTypeID\":28849,\"blueprintTypeName\":\"Anshar Blueprint\",\"productTypeID\":28848,\"productTypeName\":\"Anshar\",\"productCategoryID\":6,\"techLevel\":2,\"productionTime\":1920000,\"researchProductivityTime\":11520000,\"researchMaterialTime\":7680000,\"researchCopyTime\":2560000,\"researchTechTime\":3840000,\"productivityModifier\":384000,\"wasteFactor\":10,\"maxProductionLimit\":10},\"materialDtos\":[{\"materialTypeID\":3828,\"materialTypeName\":\"Construction Blocks\",\"materialTypeCategoryID\":43,\"quantity\":1500,\"materialTypeIcon\":\"06_05\"},{\"materialTypeID\":11399,\"materialTypeName\":\"Morphite\",\"materialTypeCategoryID\":4,\"quantity\":2500,\"materialTypeIcon\":\"35_02\"},{\"materialTypeID\":21025,\"materialTypeName\":\"Capital Jump Drive\",\"materialTypeCategoryID\":17,\"quantity\":20,\"materialTypeIcon\":\"54_08\"},{\"materialTypeID\":29041,\"materialTypeName\":\"Capital Crystalline Carbonide Armor Plate\",\"materialTypeCategoryID\":17,\"quantity\":1013,\"materialTypeIcon\":\"37_14\"},{\"materialTypeID\":29051,\"materialTypeName\":\"Capital Fusion Reactor Unit\",\"materialTypeCategoryID\":17,\"quantity\":518,\"materialTypeIcon\":\"39_02\"},{\"materialTypeID\":29061,\"materialTypeName\":\"Capital Ion Thruster\",\"materialTypeCategoryID\":17,\"quantity\":422,\"materialTypeIcon\":\"37_02\"},{\"materialTypeID\":29069,\"materialTypeName\":\"Capital Magnetometric Sensor Cluster\",\"materialTypeCategoryID\":17,\"quantity\":443,\"materialTypeIcon\":\"37_06\"},{\"materialTypeID\":29081,\"materialTypeName\":\"Capital Oscillator Capacitor Unit\",\"materialTypeCategoryID\":17,\"quantity\":704,\"materialTypeIcon\":\"39_06\"},{\"materialTypeID\":29085,\"materialTypeName\":\"Capital Photon Microprocessor\",\"materialTypeCategoryID\":17,\"quantity\":704,\"materialTypeIcon\":\"37_10\"},{\"materialTypeID\":29091,\"materialTypeName\":\"Capital Pulse Shield Emitter\",\"materialTypeCategoryID\":17,\"quantity\":449,\"materialTypeIcon\":\"39_10\"}],\"manufacturingRequirementDtos\":[{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":3380,\"requiredTypeName\":\"Industry\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":268,\"requiredTypeGroupName\":\"Industry\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":11450,\"requiredTypeName\":\"Gallentean Starship Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":4,\"damagePerJob\":\"0.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":11452,\"requiredTypeName\":\"Mechanical Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":4,\"damagePerJob\":\"0.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":11478,\"requiredTypeName\":\"R.A.M.- Starship Tech\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":332,\"requiredTypeGroupName\":\"Tool\",\"requiredTypeIcon\":\"41_02\",\"quantity\":40,\"damagePerJob\":\"0.95\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":20187,\"requiredTypeName\":\"Obelisk\",\"requiredTypeCategoryID\":6,\"requiredTypeGroupID\":513,\"requiredTypeGroupName\":\"Freighter\",\"quantity\":1,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":22242,\"requiredTypeName\":\"Capital Ship Construction\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":269,\"requiredTypeGroupName\":\"Mechanic\",\"requiredTypeIcon\":\"50_11\",\"quantity\":4,\"damagePerJob\":\"0.0\"}],\"timeProductivityRequirementDtos\":[{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":3403,\"requiredTypeName\":\"Research\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":3814,\"requiredTypeName\":\"Reports\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":280,\"requiredTypeGroupName\":\"General\",\"requiredTypeIcon\":\"10_16\",\"quantity\":120,\"damagePerJob\":\"1.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":9836,\"requiredTypeName\":\"Consumer Electronics\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1034,\"requiredTypeGroupName\":\"Refined Commodities\",\"requiredTypeIcon\":\"24_08\",\"quantity\":100,\"damagePerJob\":\"1.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":11450,\"requiredTypeName\":\"Gallentean Starship Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":11452,\"requiredTypeName\":\"Mechanical Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":11466,\"requiredTypeName\":\"R.Db - CreoDron\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":332,\"requiredTypeGroupName\":\"Tool\",\"requiredTypeIcon\":\"41_01\",\"quantity\":30,\"damagePerJob\":\"0.8\"}],\"materialProductivityRequirementDtos\":[{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":3409,\"requiredTypeName\":\"Metallurgy\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":3814,\"requiredTypeName\":\"Reports\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":280,\"requiredTypeGroupName\":\"General\",\"requiredTypeIcon\":\"10_16\",\"quantity\":140,\"damagePerJob\":\"1.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":9836,\"requiredTypeName\":\"Consumer Electronics\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1034,\"requiredTypeGroupName\":\"Refined Commodities\",\"requiredTypeIcon\":\"24_08\",\"quantity\":100,\"damagePerJob\":\"1.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":11450,\"requiredTypeName\":\"Gallentean Starship Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":11452,\"requiredTypeName\":\"Mechanical Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":11466,\"requiredTypeName\":\"R.Db - CreoDron\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":332,\"requiredTypeGroupName\":\"Tool\",\"requiredTypeIcon\":\"41_01\",\"quantity\":30,\"damagePerJob\":\"0.6\"}],\"copyingRequirementDtos\":[{\"activityID\":5,\"activityName\":\"Copying\",\"requiredTypeID\":3812,\"requiredTypeName\":\"Data Sheets\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":280,\"requiredTypeGroupName\":\"General\",\"requiredTypeIcon\":\"10_16\",\"quantity\":600,\"damagePerJob\":\"1.0\"},{\"activityID\":5,\"activityName\":\"Copying\",\"requiredTypeID\":11450,\"requiredTypeName\":\"Gallentean Starship Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":5,\"activityName\":\"Copying\",\"requiredTypeID\":11452,\"requiredTypeName\":\"Mechanical Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":5,\"activityName\":\"Copying\",\"requiredTypeID\":11466,\"requiredTypeName\":\"R.Db - CreoDron\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":332,\"requiredTypeGroupName\":\"Tool\",\"requiredTypeIcon\":\"41_01\",\"quantity\":40,\"damagePerJob\":\"0.95\"}],\"reverseEngineeringRequirementDtos\":[],\"inventionRequirementDtos\":[]}", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><blueprintDetailsDto><copyingRequirementDtos><activityID>5</activityID><activityName>Copying</activityName><damagePerJob>1.0</damagePerJob><quantity>600</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>280</requiredTypeGroupID><requiredTypeGroupName>General</requiredTypeGroupName><requiredTypeID>3812</requiredTypeID><requiredTypeIcon>10_16</requiredTypeIcon><requiredTypeName>Data Sheets</requiredTypeName></copyingRequirementDtos><copyingRequirementDtos><activityID>5</activityID><activityName>Copying</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11450</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Gallentean Starship Engineering</requiredTypeName></copyingRequirementDtos><copyingRequirementDtos><activityID>5</activityID><activityName>Copying</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11452</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Mechanical Engineering</requiredTypeName></copyingRequirementDtos><copyingRequirementDtos><activityID>5</activityID><activityName>Copying</activityName><damagePerJob>0.95</damagePerJob><quantity>40</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>332</requiredTypeGroupID><requiredTypeGroupName>Tool</requiredTypeGroupName><requiredTypeID>11466</requiredTypeID><requiredTypeIcon>41_01</requiredTypeIcon><requiredTypeName>R.Db - CreoDron</requiredTypeName></copyingRequirementDtos><invBlueprintTypeDto><blueprintTypeID>28849</blueprintTypeID><blueprintTypeName>Anshar Blueprint</blueprintTypeName><maxProductionLimit>10</maxProductionLimit><productCategoryID>6</productCategoryID><productTypeID>28848</productTypeID><productTypeName>Anshar</productTypeName><productionTime>1920000</productionTime><productivityModifier>384000</productivityModifier><researchCopyTime>2560000</researchCopyTime><researchMaterialTime>7680000</researchMaterialTime><researchProductivityTime>11520000</researchProductivityTime><researchTechTime>3840000</researchTechTime><techLevel>2</techLevel><wasteFactor>10</wasteFactor></invBlueprintTypeDto><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>268</requiredTypeGroupID><requiredTypeGroupName>Industry</requiredTypeGroupName><requiredTypeID>3380</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Industry</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>4</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11450</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Gallentean Starship Engineering</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>4</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11452</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Mechanical Engineering</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.95</damagePerJob><quantity>40</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>332</requiredTypeGroupID><requiredTypeGroupName>Tool</requiredTypeGroupName><requiredTypeID>11478</requiredTypeID><requiredTypeIcon>41_02</requiredTypeIcon><requiredTypeName>R.A.M.- Starship Tech</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>1.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>6</requiredTypeCategoryID><requiredTypeGroupID>513</requiredTypeGroupID><requiredTypeGroupName>Freighter</requiredTypeGroupName><requiredTypeID>20187</requiredTypeID><requiredTypeName>Obelisk</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>4</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>269</requiredTypeGroupID><requiredTypeGroupName>Mechanic</requiredTypeGroupName><requiredTypeID>22242</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Capital Ship Construction</requiredTypeName></manufacturingRequirementDtos><materialDtos><materialTypeCategoryID>43</materialTypeCategoryID><materialTypeID>3828</materialTypeID><materialTypeIcon>06_05</materialTypeIcon><materialTypeName>Construction Blocks</materialTypeName><quantity>1500</quantity></materialDtos><materialDtos><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>11399</materialTypeID><materialTypeIcon>35_02</materialTypeIcon><materialTypeName>Morphite</materialTypeName><quantity>2500</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>21025</materialTypeID><materialTypeIcon>54_08</materialTypeIcon><materialTypeName>Capital Jump Drive</materialTypeName><quantity>20</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29041</materialTypeID><materialTypeIcon>37_14</materialTypeIcon><materialTypeName>Capital Crystalline Carbonide Armor Plate</materialTypeName><quantity>1013</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29051</materialTypeID><materialTypeIcon>39_02</materialTypeIcon><materialTypeName>Capital Fusion Reactor Unit</materialTypeName><quantity>518</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29061</materialTypeID><materialTypeIcon>37_02</materialTypeIcon><materialTypeName>Capital Ion Thruster</materialTypeName><quantity>422</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29069</materialTypeID><materialTypeIcon>37_06</materialTypeIcon><materialTypeName>Capital Magnetometric Sensor Cluster</materialTypeName><quantity>443</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29081</materialTypeID><materialTypeIcon>39_06</materialTypeIcon><materialTypeName>Capital Oscillator Capacitor Unit</materialTypeName><quantity>704</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29085</materialTypeID><materialTypeIcon>37_10</materialTypeIcon><materialTypeName>Capital Photon Microprocessor</materialTypeName><quantity>704</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29091</materialTypeID><materialTypeIcon>39_10</materialTypeIcon><materialTypeName>Capital Pulse Shield Emitter</materialTypeName><quantity>449</quantity></materialDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>3409</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Metallurgy</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>1.0</damagePerJob><quantity>140</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>280</requiredTypeGroupID><requiredTypeGroupName>General</requiredTypeGroupName><requiredTypeID>3814</requiredTypeID><requiredTypeIcon>10_16</requiredTypeIcon><requiredTypeName>Reports</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>1.0</damagePerJob><quantity>100</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1034</requiredTypeGroupID><requiredTypeGroupName>Refined Commodities</requiredTypeGroupName><requiredTypeID>9836</requiredTypeID><requiredTypeIcon>24_08</requiredTypeIcon><requiredTypeName>Consumer Electronics</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11450</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Gallentean Starship Engineering</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11452</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Mechanical Engineering</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>0.6</damagePerJob><quantity>30</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>332</requiredTypeGroupID><requiredTypeGroupName>Tool</requiredTypeGroupName><requiredTypeID>11466</requiredTypeID><requiredTypeIcon>41_01</requiredTypeIcon><requiredTypeName>R.Db - CreoDron</requiredTypeName></materialProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>3403</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Research</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>1.0</damagePerJob><quantity>120</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>280</requiredTypeGroupID><requiredTypeGroupName>General</requiredTypeGroupName><requiredTypeID>3814</requiredTypeID><requiredTypeIcon>10_16</requiredTypeIcon><requiredTypeName>Reports</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>1.0</damagePerJob><quantity>100</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1034</requiredTypeGroupID><requiredTypeGroupName>Refined Commodities</requiredTypeGroupName><requiredTypeID>9836</requiredTypeID><requiredTypeIcon>24_08</requiredTypeIcon><requiredTypeName>Consumer Electronics</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11450</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Gallentean Starship Engineering</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11452</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Mechanical Engineering</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>0.8</damagePerJob><quantity>30</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>332</requiredTypeGroupID><requiredTypeGroupName>Tool</requiredTypeGroupName><requiredTypeID>11466</requiredTypeID><requiredTypeIcon>41_01</requiredTypeIcon><requiredTypeName>R.Db - CreoDron</requiredTypeName></timeProductivityRequirementDtos></blueprintDetailsDto>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/blueprintDetailsForTypeName/Anshar%20Blueprint");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("{\"invBlueprintTypeDto\":{\"blueprintTypeID\":28849,\"blueprintTypeName\":\"Anshar Blueprint\",\"productTypeID\":28848,\"productTypeName\":\"Anshar\",\"productCategoryID\":6,\"techLevel\":2,\"productionTime\":1920000,\"researchProductivityTime\":11520000,\"researchMaterialTime\":7680000,\"researchCopyTime\":2560000,\"researchTechTime\":3840000,\"productivityModifier\":384000,\"wasteFactor\":10,\"maxProductionLimit\":10},\"materialDtos\":[{\"materialTypeID\":3828,\"materialTypeName\":\"Construction Blocks\",\"materialTypeCategoryID\":43,\"quantity\":1500,\"materialTypeIcon\":\"06_05\"},{\"materialTypeID\":11399,\"materialTypeName\":\"Morphite\",\"materialTypeCategoryID\":4,\"quantity\":2500,\"materialTypeIcon\":\"35_02\"},{\"materialTypeID\":21025,\"materialTypeName\":\"Capital Jump Drive\",\"materialTypeCategoryID\":17,\"quantity\":20,\"materialTypeIcon\":\"54_08\"},{\"materialTypeID\":29041,\"materialTypeName\":\"Capital Crystalline Carbonide Armor Plate\",\"materialTypeCategoryID\":17,\"quantity\":1013,\"materialTypeIcon\":\"37_14\"},{\"materialTypeID\":29051,\"materialTypeName\":\"Capital Fusion Reactor Unit\",\"materialTypeCategoryID\":17,\"quantity\":518,\"materialTypeIcon\":\"39_02\"},{\"materialTypeID\":29061,\"materialTypeName\":\"Capital Ion Thruster\",\"materialTypeCategoryID\":17,\"quantity\":422,\"materialTypeIcon\":\"37_02\"},{\"materialTypeID\":29069,\"materialTypeName\":\"Capital Magnetometric Sensor Cluster\",\"materialTypeCategoryID\":17,\"quantity\":443,\"materialTypeIcon\":\"37_06\"},{\"materialTypeID\":29081,\"materialTypeName\":\"Capital Oscillator Capacitor Unit\",\"materialTypeCategoryID\":17,\"quantity\":704,\"materialTypeIcon\":\"39_06\"},{\"materialTypeID\":29085,\"materialTypeName\":\"Capital Photon Microprocessor\",\"materialTypeCategoryID\":17,\"quantity\":704,\"materialTypeIcon\":\"37_10\"},{\"materialTypeID\":29091,\"materialTypeName\":\"Capital Pulse Shield Emitter\",\"materialTypeCategoryID\":17,\"quantity\":449,\"materialTypeIcon\":\"39_10\"}],\"manufacturingRequirementDtos\":[{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":3380,\"requiredTypeName\":\"Industry\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":268,\"requiredTypeGroupName\":\"Industry\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":11450,\"requiredTypeName\":\"Gallentean Starship Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":4,\"damagePerJob\":\"0.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":11452,\"requiredTypeName\":\"Mechanical Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":4,\"damagePerJob\":\"0.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":11478,\"requiredTypeName\":\"R.A.M.- Starship Tech\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":332,\"requiredTypeGroupName\":\"Tool\",\"requiredTypeIcon\":\"41_02\",\"quantity\":40,\"damagePerJob\":\"0.95\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":20187,\"requiredTypeName\":\"Obelisk\",\"requiredTypeCategoryID\":6,\"requiredTypeGroupID\":513,\"requiredTypeGroupName\":\"Freighter\",\"quantity\":1,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":22242,\"requiredTypeName\":\"Capital Ship Construction\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":269,\"requiredTypeGroupName\":\"Mechanic\",\"requiredTypeIcon\":\"50_11\",\"quantity\":4,\"damagePerJob\":\"0.0\"}],\"timeProductivityRequirementDtos\":[{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":3403,\"requiredTypeName\":\"Research\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":3814,\"requiredTypeName\":\"Reports\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":280,\"requiredTypeGroupName\":\"General\",\"requiredTypeIcon\":\"10_16\",\"quantity\":120,\"damagePerJob\":\"1.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":9836,\"requiredTypeName\":\"Consumer Electronics\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1034,\"requiredTypeGroupName\":\"Refined Commodities\",\"requiredTypeIcon\":\"24_08\",\"quantity\":100,\"damagePerJob\":\"1.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":11450,\"requiredTypeName\":\"Gallentean Starship Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":11452,\"requiredTypeName\":\"Mechanical Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":3,\"activityName\":\"Researching Time Productivity\",\"requiredTypeID\":11466,\"requiredTypeName\":\"R.Db - CreoDron\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":332,\"requiredTypeGroupName\":\"Tool\",\"requiredTypeIcon\":\"41_01\",\"quantity\":30,\"damagePerJob\":\"0.8\"}],\"materialProductivityRequirementDtos\":[{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":3409,\"requiredTypeName\":\"Metallurgy\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":3814,\"requiredTypeName\":\"Reports\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":280,\"requiredTypeGroupName\":\"General\",\"requiredTypeIcon\":\"10_16\",\"quantity\":140,\"damagePerJob\":\"1.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":9836,\"requiredTypeName\":\"Consumer Electronics\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1034,\"requiredTypeGroupName\":\"Refined Commodities\",\"requiredTypeIcon\":\"24_08\",\"quantity\":100,\"damagePerJob\":\"1.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":11450,\"requiredTypeName\":\"Gallentean Starship Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":11452,\"requiredTypeName\":\"Mechanical Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":4,\"activityName\":\"Researching Material Productivity\",\"requiredTypeID\":11466,\"requiredTypeName\":\"R.Db - CreoDron\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":332,\"requiredTypeGroupName\":\"Tool\",\"requiredTypeIcon\":\"41_01\",\"quantity\":30,\"damagePerJob\":\"0.6\"}],\"copyingRequirementDtos\":[{\"activityID\":5,\"activityName\":\"Copying\",\"requiredTypeID\":3812,\"requiredTypeName\":\"Data Sheets\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":280,\"requiredTypeGroupName\":\"General\",\"requiredTypeIcon\":\"10_16\",\"quantity\":600,\"damagePerJob\":\"1.0\"},{\"activityID\":5,\"activityName\":\"Copying\",\"requiredTypeID\":11450,\"requiredTypeName\":\"Gallentean Starship Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":5,\"activityName\":\"Copying\",\"requiredTypeID\":11452,\"requiredTypeName\":\"Mechanical Engineering\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":270,\"requiredTypeGroupName\":\"Science\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":5,\"activityName\":\"Copying\",\"requiredTypeID\":11466,\"requiredTypeName\":\"R.Db - CreoDron\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":332,\"requiredTypeGroupName\":\"Tool\",\"requiredTypeIcon\":\"41_01\",\"quantity\":40,\"damagePerJob\":\"0.95\"}],\"reverseEngineeringRequirementDtos\":[],\"inventionRequirementDtos\":[]}", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><blueprintDetailsDto><copyingRequirementDtos><activityID>5</activityID><activityName>Copying</activityName><damagePerJob>1.0</damagePerJob><quantity>600</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>280</requiredTypeGroupID><requiredTypeGroupName>General</requiredTypeGroupName><requiredTypeID>3812</requiredTypeID><requiredTypeIcon>10_16</requiredTypeIcon><requiredTypeName>Data Sheets</requiredTypeName></copyingRequirementDtos><copyingRequirementDtos><activityID>5</activityID><activityName>Copying</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11450</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Gallentean Starship Engineering</requiredTypeName></copyingRequirementDtos><copyingRequirementDtos><activityID>5</activityID><activityName>Copying</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11452</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Mechanical Engineering</requiredTypeName></copyingRequirementDtos><copyingRequirementDtos><activityID>5</activityID><activityName>Copying</activityName><damagePerJob>0.95</damagePerJob><quantity>40</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>332</requiredTypeGroupID><requiredTypeGroupName>Tool</requiredTypeGroupName><requiredTypeID>11466</requiredTypeID><requiredTypeIcon>41_01</requiredTypeIcon><requiredTypeName>R.Db - CreoDron</requiredTypeName></copyingRequirementDtos><invBlueprintTypeDto><blueprintTypeID>28849</blueprintTypeID><blueprintTypeName>Anshar Blueprint</blueprintTypeName><maxProductionLimit>10</maxProductionLimit><productCategoryID>6</productCategoryID><productTypeID>28848</productTypeID><productTypeName>Anshar</productTypeName><productionTime>1920000</productionTime><productivityModifier>384000</productivityModifier><researchCopyTime>2560000</researchCopyTime><researchMaterialTime>7680000</researchMaterialTime><researchProductivityTime>11520000</researchProductivityTime><researchTechTime>3840000</researchTechTime><techLevel>2</techLevel><wasteFactor>10</wasteFactor></invBlueprintTypeDto><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>268</requiredTypeGroupID><requiredTypeGroupName>Industry</requiredTypeGroupName><requiredTypeID>3380</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Industry</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>4</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11450</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Gallentean Starship Engineering</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>4</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11452</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Mechanical Engineering</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.95</damagePerJob><quantity>40</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>332</requiredTypeGroupID><requiredTypeGroupName>Tool</requiredTypeGroupName><requiredTypeID>11478</requiredTypeID><requiredTypeIcon>41_02</requiredTypeIcon><requiredTypeName>R.A.M.- Starship Tech</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>1.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>6</requiredTypeCategoryID><requiredTypeGroupID>513</requiredTypeGroupID><requiredTypeGroupName>Freighter</requiredTypeGroupName><requiredTypeID>20187</requiredTypeID><requiredTypeName>Obelisk</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>4</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>269</requiredTypeGroupID><requiredTypeGroupName>Mechanic</requiredTypeGroupName><requiredTypeID>22242</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Capital Ship Construction</requiredTypeName></manufacturingRequirementDtos><materialDtos><materialTypeCategoryID>43</materialTypeCategoryID><materialTypeID>3828</materialTypeID><materialTypeIcon>06_05</materialTypeIcon><materialTypeName>Construction Blocks</materialTypeName><quantity>1500</quantity></materialDtos><materialDtos><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeID>11399</materialTypeID><materialTypeIcon>35_02</materialTypeIcon><materialTypeName>Morphite</materialTypeName><quantity>2500</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>21025</materialTypeID><materialTypeIcon>54_08</materialTypeIcon><materialTypeName>Capital Jump Drive</materialTypeName><quantity>20</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29041</materialTypeID><materialTypeIcon>37_14</materialTypeIcon><materialTypeName>Capital Crystalline Carbonide Armor Plate</materialTypeName><quantity>1013</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29051</materialTypeID><materialTypeIcon>39_02</materialTypeIcon><materialTypeName>Capital Fusion Reactor Unit</materialTypeName><quantity>518</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29061</materialTypeID><materialTypeIcon>37_02</materialTypeIcon><materialTypeName>Capital Ion Thruster</materialTypeName><quantity>422</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29069</materialTypeID><materialTypeIcon>37_06</materialTypeIcon><materialTypeName>Capital Magnetometric Sensor Cluster</materialTypeName><quantity>443</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29081</materialTypeID><materialTypeIcon>39_06</materialTypeIcon><materialTypeName>Capital Oscillator Capacitor Unit</materialTypeName><quantity>704</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29085</materialTypeID><materialTypeIcon>37_10</materialTypeIcon><materialTypeName>Capital Photon Microprocessor</materialTypeName><quantity>704</quantity></materialDtos><materialDtos><materialTypeCategoryID>17</materialTypeCategoryID><materialTypeID>29091</materialTypeID><materialTypeIcon>39_10</materialTypeIcon><materialTypeName>Capital Pulse Shield Emitter</materialTypeName><quantity>449</quantity></materialDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>3409</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Metallurgy</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>1.0</damagePerJob><quantity>140</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>280</requiredTypeGroupID><requiredTypeGroupName>General</requiredTypeGroupName><requiredTypeID>3814</requiredTypeID><requiredTypeIcon>10_16</requiredTypeIcon><requiredTypeName>Reports</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>1.0</damagePerJob><quantity>100</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1034</requiredTypeGroupID><requiredTypeGroupName>Refined Commodities</requiredTypeGroupName><requiredTypeID>9836</requiredTypeID><requiredTypeIcon>24_08</requiredTypeIcon><requiredTypeName>Consumer Electronics</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11450</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Gallentean Starship Engineering</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11452</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Mechanical Engineering</requiredTypeName></materialProductivityRequirementDtos><materialProductivityRequirementDtos><activityID>4</activityID><activityName>Researching Material Productivity</activityName><damagePerJob>0.6</damagePerJob><quantity>30</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>332</requiredTypeGroupID><requiredTypeGroupName>Tool</requiredTypeGroupName><requiredTypeID>11466</requiredTypeID><requiredTypeIcon>41_01</requiredTypeIcon><requiredTypeName>R.Db - CreoDron</requiredTypeName></materialProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>3403</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Research</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>1.0</damagePerJob><quantity>120</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>280</requiredTypeGroupID><requiredTypeGroupName>General</requiredTypeGroupName><requiredTypeID>3814</requiredTypeID><requiredTypeIcon>10_16</requiredTypeIcon><requiredTypeName>Reports</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>1.0</damagePerJob><quantity>100</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1034</requiredTypeGroupID><requiredTypeGroupName>Refined Commodities</requiredTypeGroupName><requiredTypeID>9836</requiredTypeID><requiredTypeIcon>24_08</requiredTypeIcon><requiredTypeName>Consumer Electronics</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11450</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Gallentean Starship Engineering</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>270</requiredTypeGroupID><requiredTypeGroupName>Science</requiredTypeGroupName><requiredTypeID>11452</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Mechanical Engineering</requiredTypeName></timeProductivityRequirementDtos><timeProductivityRequirementDtos><activityID>3</activityID><activityName>Researching Time Productivity</activityName><damagePerJob>0.8</damagePerJob><quantity>30</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>332</requiredTypeGroupID><requiredTypeGroupName>Tool</requiredTypeGroupName><requiredTypeID>11466</requiredTypeID><requiredTypeIcon>41_01</requiredTypeIcon><requiredTypeName>R.Db - CreoDron</requiredTypeName></timeProductivityRequirementDtos></blueprintDetailsDto>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/blueprintDetailsForTypeName/Anshar Blueprint");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_blueprintDetails_Issue15_HTTP400() throws Exception {
        URL url = new URL(baseUrl + "/blueprintDetailsForTypeName/Sovereignty+Blockade+Unit+Blueprint");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("{\"invBlueprintTypeDto\":{\"blueprintTypeID\":2738,\"blueprintTypeName\":\"Sovereignty Blockade Unit Blueprint\",\"productTypeID\":32250,\"productTypeName\":\"Sovereignty Blockade Unit\",\"productCategoryID\":40,\"techLevel\":1,\"productionTime\":43200,\"researchProductivityTime\":864000,\"researchMaterialTime\":864000,\"researchCopyTime\":864000,\"researchTechTime\":864000,\"productivityModifier\":8640,\"wasteFactor\":10,\"maxProductionLimit\":5},\"materialDtos\":[],\"manufacturingRequirementDtos\":[{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":2867,\"requiredTypeName\":\"Broadcast Node\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1041,\"requiredTypeGroupName\":\"Advanced Commodities\",\"requiredTypeIcon\":\"99_06\",\"quantity\":13,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":2868,\"requiredTypeName\":\"Integrity Response Drones\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1041,\"requiredTypeGroupName\":\"Advanced Commodities\",\"requiredTypeIcon\":\"99_02\",\"quantity\":9,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":2869,\"requiredTypeName\":\"Nano-Factory\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1041,\"requiredTypeGroupName\":\"Advanced Commodities\",\"requiredTypeIcon\":\"99_05\",\"quantity\":19,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":2870,\"requiredTypeName\":\"Organic Mortar Applicators\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1041,\"requiredTypeGroupName\":\"Advanced Commodities\",\"requiredTypeIcon\":\"99_03\",\"quantity\":19,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":2871,\"requiredTypeName\":\"Recursive Computing Module\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1041,\"requiredTypeGroupName\":\"Advanced Commodities\",\"requiredTypeIcon\":\"99_07\",\"quantity\":13,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":2872,\"requiredTypeName\":\"Self-Harmonizing Power Core\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1041,\"requiredTypeGroupName\":\"Advanced Commodities\",\"requiredTypeIcon\":\"99_01\",\"quantity\":13,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":2875,\"requiredTypeName\":\"Sterile Conduits\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1041,\"requiredTypeGroupName\":\"Advanced Commodities\",\"requiredTypeIcon\":\"99_04\",\"quantity\":19,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":2876,\"requiredTypeName\":\"Wetware Mainframe\",\"requiredTypeCategoryID\":43,\"requiredTypeGroupID\":1041,\"requiredTypeGroupName\":\"Advanced Commodities\",\"requiredTypeIcon\":\"99_08\",\"quantity\":9,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":3380,\"requiredTypeName\":\"Industry\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":268,\"requiredTypeGroupName\":\"Industry\",\"requiredTypeIcon\":\"50_11\",\"quantity\":5,\"damagePerJob\":\"0.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":21037,\"requiredTypeName\":\"Capital Construction Parts\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":873,\"requiredTypeGroupName\":\"Capital Construction Components\",\"requiredTypeIcon\":\"54_12\",\"quantity\":5,\"damagePerJob\":\"1.0\"}],\"timeProductivityRequirementDtos\":[],\"materialProductivityRequirementDtos\":[],\"copyingRequirementDtos\":[],\"reverseEngineeringRequirementDtos\":[],\"inventionRequirementDtos\":[]}", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><blueprintDetailsDto><invBlueprintTypeDto><blueprintTypeID>2738</blueprintTypeID><blueprintTypeName>Sovereignty Blockade Unit Blueprint</blueprintTypeName><maxProductionLimit>5</maxProductionLimit><productCategoryID>40</productCategoryID><productTypeID>32250</productTypeID><productTypeName>Sovereignty Blockade Unit</productTypeName><productionTime>43200</productionTime><productivityModifier>8640</productivityModifier><researchCopyTime>864000</researchCopyTime><researchMaterialTime>864000</researchMaterialTime><researchProductivityTime>864000</researchProductivityTime><researchTechTime>864000</researchTechTime><techLevel>1</techLevel><wasteFactor>10</wasteFactor></invBlueprintTypeDto><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>1.0</damagePerJob><quantity>13</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1041</requiredTypeGroupID><requiredTypeGroupName>Advanced Commodities</requiredTypeGroupName><requiredTypeID>2867</requiredTypeID><requiredTypeIcon>99_06</requiredTypeIcon><requiredTypeName>Broadcast Node</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>1.0</damagePerJob><quantity>9</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1041</requiredTypeGroupID><requiredTypeGroupName>Advanced Commodities</requiredTypeGroupName><requiredTypeID>2868</requiredTypeID><requiredTypeIcon>99_02</requiredTypeIcon><requiredTypeName>Integrity Response Drones</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>1.0</damagePerJob><quantity>19</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1041</requiredTypeGroupID><requiredTypeGroupName>Advanced Commodities</requiredTypeGroupName><requiredTypeID>2869</requiredTypeID><requiredTypeIcon>99_05</requiredTypeIcon><requiredTypeName>Nano-Factory</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>1.0</damagePerJob><quantity>19</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1041</requiredTypeGroupID><requiredTypeGroupName>Advanced Commodities</requiredTypeGroupName><requiredTypeID>2870</requiredTypeID><requiredTypeIcon>99_03</requiredTypeIcon><requiredTypeName>Organic Mortar Applicators</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>1.0</damagePerJob><quantity>13</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1041</requiredTypeGroupID><requiredTypeGroupName>Advanced Commodities</requiredTypeGroupName><requiredTypeID>2871</requiredTypeID><requiredTypeIcon>99_07</requiredTypeIcon><requiredTypeName>Recursive Computing Module</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>1.0</damagePerJob><quantity>13</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1041</requiredTypeGroupID><requiredTypeGroupName>Advanced Commodities</requiredTypeGroupName><requiredTypeID>2872</requiredTypeID><requiredTypeIcon>99_01</requiredTypeIcon><requiredTypeName>Self-Harmonizing Power Core</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>1.0</damagePerJob><quantity>19</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1041</requiredTypeGroupID><requiredTypeGroupName>Advanced Commodities</requiredTypeGroupName><requiredTypeID>2875</requiredTypeID><requiredTypeIcon>99_04</requiredTypeIcon><requiredTypeName>Sterile Conduits</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>1.0</damagePerJob><quantity>9</quantity><requiredTypeCategoryID>43</requiredTypeCategoryID><requiredTypeGroupID>1041</requiredTypeGroupID><requiredTypeGroupName>Advanced Commodities</requiredTypeGroupName><requiredTypeID>2876</requiredTypeID><requiredTypeIcon>99_08</requiredTypeIcon><requiredTypeName>Wetware Mainframe</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>268</requiredTypeGroupID><requiredTypeGroupName>Industry</requiredTypeGroupName><requiredTypeID>3380</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Industry</requiredTypeName></manufacturingRequirementDtos><manufacturingRequirementDtos><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>1.0</damagePerJob><quantity>5</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>873</requiredTypeGroupID><requiredTypeGroupName>Capital Construction Components</requiredTypeGroupName><requiredTypeID>21037</requiredTypeID><requiredTypeIcon>54_12</requiredTypeIcon><requiredTypeName>Capital Construction Parts</requiredTypeName></manufacturingRequirementDtos></blueprintDetailsDto>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_blueprintDetailsForTypeName_NonExistingName() throws Exception {
        URL url = new URL(baseUrl + "/blueprintDetailsForTypeName/blah-blah");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_extraMaterialsByTypeID() throws Exception {
        URL url = new URL(baseUrl + "/extraMaterialsForTypeID/20188");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("[{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":20410,\"requiredTypeName\":\"Datacore - Gallentean Starship Engineering\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":333,\"requiredTypeGroupName\":\"Datacores\",\"requiredTypeIcon\":\"64_02\",\"quantity\":64,\"damagePerJob\":\"1.0\"},{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":20424,\"requiredTypeName\":\"Datacore - Mechanical Engineering\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":333,\"requiredTypeGroupName\":\"Datacores\",\"requiredTypeIcon\":\"64_03\",\"quantity\":64,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":22242,\"requiredTypeName\":\"Capital Ship Construction\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":269,\"requiredTypeGroupName\":\"Mechanic\",\"requiredTypeIcon\":\"50_11\",\"quantity\":1,\"damagePerJob\":\"0.0\"},{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":25855,\"requiredTypeName\":\"Incognito Ship Data Interface\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":716,\"requiredTypeGroupName\":\"Data Interfaces\",\"requiredTypeIcon\":\"63_06\",\"quantity\":1,\"damagePerJob\":\"1.0\"}]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>64</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>333</requiredTypeGroupID><requiredTypeGroupName>Datacores</requiredTypeGroupName><requiredTypeID>20410</requiredTypeID><requiredTypeIcon>64_02</requiredTypeIcon><requiredTypeName>Datacore - Gallentean Starship Engineering</requiredTypeName></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>64</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>333</requiredTypeGroupID><requiredTypeGroupName>Datacores</requiredTypeGroupName><requiredTypeID>20424</requiredTypeID><requiredTypeIcon>64_03</requiredTypeIcon><requiredTypeName>Datacore - Mechanical Engineering</requiredTypeName></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>269</requiredTypeGroupID><requiredTypeGroupName>Mechanic</requiredTypeGroupName><requiredTypeID>22242</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Capital Ship Construction</requiredTypeName></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>716</requiredTypeGroupID><requiredTypeGroupName>Data Interfaces</requiredTypeGroupName><requiredTypeID>25855</requiredTypeID><requiredTypeIcon>63_06</requiredTypeIcon><requiredTypeName>Incognito Ship Data Interface</requiredTypeName></row></rowset>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_extraMaterialsByTypeID_NonExistingID() throws Exception {
        URL url = new URL(baseUrl + "/extraMaterialsForTypeID/1234567890");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_extraMaterialsByTypeID_StringInsteadOfID() throws Exception {
        URL url = new URL(baseUrl + "/extraMaterialsForTypeID/blah-blah");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_extraMaterialsByTypeName() throws Exception {
        URL url = new URL(baseUrl + "/extraMaterialsForTypeName/Obelisk+Blueprint");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("[{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":20410,\"requiredTypeName\":\"Datacore - Gallentean Starship Engineering\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":333,\"requiredTypeGroupName\":\"Datacores\",\"requiredTypeIcon\":\"64_02\",\"quantity\":64,\"damagePerJob\":\"1.0\"},{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":20424,\"requiredTypeName\":\"Datacore - Mechanical Engineering\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":333,\"requiredTypeGroupName\":\"Datacores\",\"requiredTypeIcon\":\"64_03\",\"quantity\":64,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":22242,\"requiredTypeName\":\"Capital Ship Construction\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":269,\"requiredTypeGroupName\":\"Mechanic\",\"requiredTypeIcon\":\"50_11\",\"quantity\":1,\"damagePerJob\":\"0.0\"},{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":25855,\"requiredTypeName\":\"Incognito Ship Data Interface\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":716,\"requiredTypeGroupName\":\"Data Interfaces\",\"requiredTypeIcon\":\"63_06\",\"quantity\":1,\"damagePerJob\":\"1.0\"}]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>64</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>333</requiredTypeGroupID><requiredTypeGroupName>Datacores</requiredTypeGroupName><requiredTypeID>20410</requiredTypeID><requiredTypeIcon>64_02</requiredTypeIcon><requiredTypeName>Datacore - Gallentean Starship Engineering</requiredTypeName></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>64</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>333</requiredTypeGroupID><requiredTypeGroupName>Datacores</requiredTypeGroupName><requiredTypeID>20424</requiredTypeID><requiredTypeIcon>64_03</requiredTypeIcon><requiredTypeName>Datacore - Mechanical Engineering</requiredTypeName></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>269</requiredTypeGroupID><requiredTypeGroupName>Mechanic</requiredTypeGroupName><requiredTypeID>22242</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Capital Ship Construction</requiredTypeName></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>716</requiredTypeGroupID><requiredTypeGroupName>Data Interfaces</requiredTypeGroupName><requiredTypeID>25855</requiredTypeID><requiredTypeIcon>63_06</requiredTypeIcon><requiredTypeName>Incognito Ship Data Interface</requiredTypeName></row></rowset>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/extraMaterialsForTypeName/Obelisk%20Blueprint");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("[{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":20410,\"requiredTypeName\":\"Datacore - Gallentean Starship Engineering\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":333,\"requiredTypeGroupName\":\"Datacores\",\"requiredTypeIcon\":\"64_02\",\"quantity\":64,\"damagePerJob\":\"1.0\"},{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":20424,\"requiredTypeName\":\"Datacore - Mechanical Engineering\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":333,\"requiredTypeGroupName\":\"Datacores\",\"requiredTypeIcon\":\"64_03\",\"quantity\":64,\"damagePerJob\":\"1.0\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":22242,\"requiredTypeName\":\"Capital Ship Construction\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":269,\"requiredTypeGroupName\":\"Mechanic\",\"requiredTypeIcon\":\"50_11\",\"quantity\":1,\"damagePerJob\":\"0.0\"},{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":25855,\"requiredTypeName\":\"Incognito Ship Data Interface\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":716,\"requiredTypeGroupName\":\"Data Interfaces\",\"requiredTypeIcon\":\"63_06\",\"quantity\":1,\"damagePerJob\":\"1.0\"}]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>64</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>333</requiredTypeGroupID><requiredTypeGroupName>Datacores</requiredTypeGroupName><requiredTypeID>20410</requiredTypeID><requiredTypeIcon>64_02</requiredTypeIcon><requiredTypeName>Datacore - Gallentean Starship Engineering</requiredTypeName></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>64</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>333</requiredTypeGroupID><requiredTypeGroupName>Datacores</requiredTypeGroupName><requiredTypeID>20424</requiredTypeID><requiredTypeIcon>64_03</requiredTypeIcon><requiredTypeName>Datacore - Mechanical Engineering</requiredTypeName></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>269</requiredTypeGroupID><requiredTypeGroupName>Mechanic</requiredTypeGroupName><requiredTypeID>22242</requiredTypeID><requiredTypeIcon>50_11</requiredTypeIcon><requiredTypeName>Capital Ship Construction</requiredTypeName></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>716</requiredTypeGroupID><requiredTypeGroupName>Data Interfaces</requiredTypeGroupName><requiredTypeID>25855</requiredTypeID><requiredTypeIcon>63_06</requiredTypeIcon><requiredTypeName>Incognito Ship Data Interface</requiredTypeName></row></rowset>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/extraMaterialsForTypeName/Obelisk Blueprint");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_extraMaterialsByTypeName_NonExistingName() throws Exception {
        URL url = new URL(baseUrl + "/extraMaterialsForTypeName/blah-blah");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_lookupBlueprintType() throws Exception {
        URL url = new URL(baseUrl + "/lookupBlueprintType/Mega");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("[{\"itemTypeID\":847,\"itemCategoryID\":9,\"name\":\"Mega Beam Laser I Blueprint\",\"icon\":\"13_11\"},{\"itemTypeID\":3050,\"itemCategoryID\":9,\"name\":\"Mega Beam Laser II Blueprint\",\"icon\":\"13_11\"},{\"itemTypeID\":30224,\"itemCategoryID\":9,\"name\":\"Mega Module Blueprint\",\"icon\":\"01_08\"},{\"itemTypeID\":846,\"itemCategoryID\":9,\"name\":\"Mega Pulse Laser I Blueprint\",\"icon\":\"13_15\"},{\"itemTypeID\":3058,\"itemCategoryID\":9,\"name\":\"Mega Pulse Laser II Blueprint\",\"icon\":\"13_15\"},{\"itemTypeID\":995,\"itemCategoryID\":9,\"name\":\"Megathron Blueprint\"},{\"itemTypeID\":13203,\"itemCategoryID\":9,\"name\":\"Megathron Federate Issue Blueprint\"},{\"itemTypeID\":17729,\"itemCategoryID\":9,\"name\":\"Megathron Navy Issue Blueprint\"}]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>13_11</icon><itemCategoryID>9</itemCategoryID><itemTypeID>847</itemTypeID><name>Mega Beam Laser I Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>13_11</icon><itemCategoryID>9</itemCategoryID><itemTypeID>3050</itemTypeID><name>Mega Beam Laser II Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>9</itemCategoryID><itemTypeID>30224</itemTypeID><name>Mega Module Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>13_15</icon><itemCategoryID>9</itemCategoryID><itemTypeID>846</itemTypeID><name>Mega Pulse Laser I Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>13_15</icon><itemCategoryID>9</itemCategoryID><itemTypeID>3058</itemTypeID><name>Mega Pulse Laser II Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><itemCategoryID>9</itemCategoryID><itemTypeID>995</itemTypeID><name>Megathron Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><itemCategoryID>9</itemCategoryID><itemTypeID>13203</itemTypeID><name>Megathron Federate Issue Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><itemCategoryID>9</itemCategoryID><itemTypeID>17729</itemTypeID><name>Megathron Navy Issue Blueprint</name></row></rowset>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_lookupBlueprintType_NonExistingName() throws Exception {
        URL url = new URL(baseUrl + "/lookupBlueprintType/blah-blah");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("[]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset/>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_lookupBlueprintType_TooShortName() throws Exception {
        URL url = new URL(baseUrl + "/lookupBlueprintType/A");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_lookupResourceType() throws Exception {
        URL url = new URL(baseUrl + "/lookupResourceType/Tri");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("[{\"itemTypeID\":25612,\"itemCategoryID\":4,\"name\":\"Trigger Unit\",\"icon\":\"69_14\"},{\"itemTypeID\":25598,\"itemCategoryID\":4,\"name\":\"Tripped Power Circuit\",\"icon\":\"69_15\"},{\"itemTypeID\":34,\"itemCategoryID\":4,\"name\":\"Tritanium\",\"icon\":\"06_14\"}]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>69_14</icon><itemCategoryID>4</itemCategoryID><itemTypeID>25612</itemTypeID><name>Trigger Unit</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>69_15</icon><itemCategoryID>4</itemCategoryID><itemTypeID>25598</itemTypeID><name>Tripped Power Circuit</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>06_14</icon><itemCategoryID>4</itemCategoryID><itemTypeID>34</itemTypeID><name>Tritanium</name></row></rowset>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_lookupResourceType_NonExistingName() throws Exception {
        URL url = new URL(baseUrl + "/lookupResourceType/blah-blah");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("[]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset/>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_lookupResourceType_TooShortName() throws Exception {
        URL url = new URL(baseUrl + "/lookupResourceType/A");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_lookupType() throws Exception {
        URL url = new URL(baseUrl + "/lookupType/Tri");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("[{\"itemTypeID\":22140,\"itemCategoryID\":17,\"name\":\"Tri-Vitoc\",\"icon\":\"11_15\"},{\"itemTypeID\":27951,\"itemCategoryID\":7,\"name\":\"Triage Module I\",\"icon\":\"70_10\"},{\"itemTypeID\":27952,\"itemCategoryID\":9,\"name\":\"Triage Module I Blueprint\",\"icon\":\"06_03\"},{\"itemTypeID\":17428,\"itemCategoryID\":25,\"name\":\"Triclinic Bistot\",\"icon\":\"23_06\"},{\"itemTypeID\":25612,\"itemCategoryID\":4,\"name\":\"Trigger Unit\",\"icon\":\"69_14\"},{\"itemTypeID\":11066,\"itemCategoryID\":17,\"name\":\"Trinary Data\",\"icon\":\"34_05\"},{\"itemTypeID\":16307,\"itemCategoryID\":7,\"name\":\"Triple-sheathed Adaptive Nano Plating I\",\"icon\":\"01_08\"},{\"itemTypeID\":16315,\"itemCategoryID\":7,\"name\":\"Triple-sheathed Magnetic Plating I\",\"icon\":\"01_08\"},{\"itemTypeID\":16323,\"itemCategoryID\":7,\"name\":\"Triple-sheathed Reactive Plating I\",\"icon\":\"01_08\"},{\"itemTypeID\":16331,\"itemCategoryID\":7,\"name\":\"Triple-sheathed Reflective Plating I\",\"icon\":\"01_08\"},{\"itemTypeID\":16347,\"itemCategoryID\":7,\"name\":\"Triple-sheathed Regenerative Plating I\",\"icon\":\"01_08\"},{\"itemTypeID\":16339,\"itemCategoryID\":7,\"name\":\"Triple-sheathed Thermic Plating I\",\"icon\":\"01_08\"},{\"itemTypeID\":25598,\"itemCategoryID\":4,\"name\":\"Tripped Power Circuit\",\"icon\":\"69_15\"},{\"itemTypeID\":593,\"itemCategoryID\":6,\"name\":\"Tristan\"},{\"itemTypeID\":940,\"itemCategoryID\":9,\"name\":\"Tristan Blueprint\"},{\"itemTypeID\":17916,\"itemCategoryID\":17,\"name\":\"Tritan\\u0027s Forked Key\",\"icon\":\"34_06\"},{\"itemTypeID\":34,\"itemCategoryID\":4,\"name\":\"Tritanium\",\"icon\":\"06_14\"}]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>11_15</icon><itemCategoryID>17</itemCategoryID><itemTypeID>22140</itemTypeID><name>Tri-Vitoc</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>70_10</icon><itemCategoryID>7</itemCategoryID><itemTypeID>27951</itemTypeID><name>Triage Module I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>06_03</icon><itemCategoryID>9</itemCategoryID><itemTypeID>27952</itemTypeID><name>Triage Module I Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>23_06</icon><itemCategoryID>25</itemCategoryID><itemTypeID>17428</itemTypeID><name>Triclinic Bistot</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>69_14</icon><itemCategoryID>4</itemCategoryID><itemTypeID>25612</itemTypeID><name>Trigger Unit</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>34_05</icon><itemCategoryID>17</itemCategoryID><itemTypeID>11066</itemTypeID><name>Trinary Data</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>7</itemCategoryID><itemTypeID>16307</itemTypeID><name>Triple-sheathed Adaptive Nano Plating I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>7</itemCategoryID><itemTypeID>16315</itemTypeID><name>Triple-sheathed Magnetic Plating I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>7</itemCategoryID><itemTypeID>16323</itemTypeID><name>Triple-sheathed Reactive Plating I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>7</itemCategoryID><itemTypeID>16331</itemTypeID><name>Triple-sheathed Reflective Plating I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>7</itemCategoryID><itemTypeID>16347</itemTypeID><name>Triple-sheathed Regenerative Plating I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>7</itemCategoryID><itemTypeID>16339</itemTypeID><name>Triple-sheathed Thermic Plating I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>69_15</icon><itemCategoryID>4</itemCategoryID><itemTypeID>25598</itemTypeID><name>Tripped Power Circuit</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><itemCategoryID>6</itemCategoryID><itemTypeID>593</itemTypeID><name>Tristan</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><itemCategoryID>9</itemCategoryID><itemTypeID>940</itemTypeID><name>Tristan Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>34_06</icon><itemCategoryID>17</itemCategoryID><itemTypeID>17916</itemTypeID><name>Tritan's Forked Key</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>06_14</icon><itemCategoryID>4</itemCategoryID><itemTypeID>34</itemTypeID><name>Tritanium</name></row></rowset>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_lookupType_NonExistingName() throws Exception {
        URL url = new URL(baseUrl + "/lookupType/blah-blah");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("[]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset/>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_lookupType_TooShortName() throws Exception {
        URL url = new URL(baseUrl + "/lookupType/A");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(400, connection.getResponseCode());

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_typeIdToTypeName() throws Exception {
        URL url = new URL(baseUrl + "/typeIdToTypeName/20187");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        assertEquals(200, connection.getResponseCode());
        assertEquals("Obelisk", getResponse(connection));
        assertEquals("text/plain; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_typeIdToTypeName_NonExistingID() throws Exception {
        URL url = new URL(baseUrl + "/typeIdToTypeName/1234567890");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_typeIdToTypeName_StringInsteadOfID() throws Exception {
        URL url = new URL(baseUrl + "/typeIdToTypeName/blah-blah");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_typeNameToTypeID() throws Exception {
        URL url = new URL(baseUrl + "/typeNameToTypeID/Obelisk");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        assertEquals(200, connection.getResponseCode());
        assertEquals("20187", getResponse(connection));
        assertEquals("text/plain; charset=utf-8", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/typeNameToTypeID/Obelisk+Blueprint");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        assertEquals(200, connection.getResponseCode());
        assertEquals("20188", getResponse(connection));
        assertEquals("text/plain; charset=utf-8", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/typeNameToTypeID/Obelisk%20Blueprint");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        assertEquals(200, connection.getResponseCode());
        assertEquals("20188", getResponse(connection));
        assertEquals("text/plain; charset=utf-8", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/typeNameToTypeID/Obelisk Blueprint");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_typeNameToTypeID_NonExistingName() throws Exception {
        URL url = new URL(baseUrl + "/typeNameToTypeID/blah-blah");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        assertEquals(400, connection.getResponseCode());
    }

    @Test
    public void test_version() throws Exception {
        URL url = new URL(baseUrl + "/version");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        assertEquals(200, connection.getResponseCode());
        assertTrue(getResponse(connection).endsWith("-tyr104"));
        assertEquals("text/html", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_root() throws Exception {
        URL url = new URL(baseUrl + "/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        assertEquals(200, connection.getResponseCode());
        assertTrue(getResponse(connection).endsWith("</html>"));
        assertEquals("text/html", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/robots.txt");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        assertEquals(200, connection.getResponseCode());
        assertTrue(getResponse(connection).endsWith("Disallow: /"));
        assertEquals("text/plain", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/favicon.ico");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        assertEquals(200, connection.getResponseCode());
        assertEquals("image/x-icon", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_tyr101specific_SBU() throws Exception {
        URL url = new URL(baseUrl + "/typeNameToTypeID/Sovereignty+Blockade+Unit+Blueprint");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        assertEquals(200, connection.getResponseCode());
        assertEquals("2738", getResponse(connection));
        assertEquals("text/plain; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        return stringBuilder.toString();
    }
}
