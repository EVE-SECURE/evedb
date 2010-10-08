package lv.odylab.evedb.ws;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.google.appengine.repackaged.com.google.common.base.X.assertTrue;
import static junit.framework.Assert.assertEquals;

public class Dom100ProductionTest {
    private final String baseUrl = "http://dom100.latest.odylab-evedb.appspot.com";

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
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>06_14</materialTypeIcon><materialTypeID>34</materialTypeID><materialTypeName>Tritanium</materialTypeName><quantity>20524</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>06_15</materialTypeIcon><materialTypeID>35</materialTypeID><materialTypeName>Pyerite</materialTypeName><quantity>5529</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>06_12</materialTypeIcon><materialTypeID>36</materialTypeID><materialTypeName>Mexallon</materialTypeName><quantity>1841</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>06_16</materialTypeIcon><materialTypeID>37</materialTypeID><materialTypeName>Isogen</materialTypeName><quantity>317</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>11_09</materialTypeIcon><materialTypeID>38</materialTypeID><materialTypeName>Nocxium</materialTypeName><quantity>118</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>11_11</materialTypeIcon><materialTypeID>39</materialTypeID><materialTypeName>Zydrine</materialTypeName><quantity>13</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>11_10</materialTypeIcon><materialTypeID>40</materialTypeID><materialTypeName>Megacyte</materialTypeName><quantity>1</quantity></row></rowset>", getResponse(connection));
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
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>06_14</materialTypeIcon><materialTypeID>34</materialTypeID><materialTypeName>Tritanium</materialTypeName><quantity>20524</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>06_15</materialTypeIcon><materialTypeID>35</materialTypeID><materialTypeName>Pyerite</materialTypeName><quantity>5529</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>06_12</materialTypeIcon><materialTypeID>36</materialTypeID><materialTypeName>Mexallon</materialTypeName><quantity>1841</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>06_16</materialTypeIcon><materialTypeID>37</materialTypeID><materialTypeName>Isogen</materialTypeName><quantity>317</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>11_09</materialTypeIcon><materialTypeID>38</materialTypeID><materialTypeName>Nocxium</materialTypeName><quantity>118</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>11_11</materialTypeIcon><materialTypeID>39</materialTypeID><materialTypeName>Zydrine</materialTypeName><quantity>13</quantity></row><row xsi:type=\"invTypeMaterialDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><materialTypeCategoryID>4</materialTypeCategoryID><materialTypeIcon>11_10</materialTypeIcon><materialTypeID>40</materialTypeID><materialTypeName>Megacyte</materialTypeName><quantity>1</quantity></row></rowset>", getResponse(connection));
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
        assertEquals("{\"blueprintTypeID\":20188,\"blueprintTypeName\":\"Obelisk Blueprint\",\"productTypeID\":20187,\"productTypeName\":\"Obelisk\",\"productCategoryID\":6,\"productIcon\":\"\",\"wasteFactor\":10,\"maxProductionLimit\":1}", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invBlueprintTypeDto><blueprintTypeID>20188</blueprintTypeID><blueprintTypeName>Obelisk Blueprint</blueprintTypeName><maxProductionLimit>1</maxProductionLimit><productCategoryID>6</productCategoryID><productIcon></productIcon><productTypeID>20187</productTypeID><productTypeName>Obelisk</productTypeName><wasteFactor>10</wasteFactor></invBlueprintTypeDto>", getResponse(connection));
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
        assertEquals("{\"blueprintTypeID\":20188,\"blueprintTypeName\":\"Obelisk Blueprint\",\"productTypeID\":20187,\"productTypeName\":\"Obelisk\",\"productCategoryID\":6,\"productIcon\":\"\",\"wasteFactor\":10,\"maxProductionLimit\":1}", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invBlueprintTypeDto><blueprintTypeID>20188</blueprintTypeID><blueprintTypeName>Obelisk Blueprint</blueprintTypeName><maxProductionLimit>1</maxProductionLimit><productCategoryID>6</productCategoryID><productIcon></productIcon><productTypeID>20187</productTypeID><productTypeName>Obelisk</productTypeName><wasteFactor>10</wasteFactor></invBlueprintTypeDto>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/blueprintTypeByTypeName/Obelisk%20Blueprint");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("{\"blueprintTypeID\":20188,\"blueprintTypeName\":\"Obelisk Blueprint\",\"productTypeID\":20187,\"productTypeName\":\"Obelisk\",\"productCategoryID\":6,\"productIcon\":\"\",\"wasteFactor\":10,\"maxProductionLimit\":1}", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><invBlueprintTypeDto><blueprintTypeID>20188</blueprintTypeID><blueprintTypeName>Obelisk Blueprint</blueprintTypeName><maxProductionLimit>1</maxProductionLimit><productCategoryID>6</productCategoryID><productIcon></productIcon><productTypeID>20187</productTypeID><productTypeName>Obelisk</productTypeName><wasteFactor>10</wasteFactor></invBlueprintTypeDto>", getResponse(connection));
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
    public void test_extraMaterialsByTypeID() throws Exception {
        URL url = new URL(baseUrl + "/extraMaterialsForTypeID/20188");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("[{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":20410,\"requiredTypeName\":\"Datacore - Gallentean Starship Engineering\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":333,\"requiredTypeGroupName\":\"Datacores\",\"quantity\":64,\"damagePerJob\":\"1.0\",\"requiredTypeNameIcon\":\"64_02\"},{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":20424,\"requiredTypeName\":\"Datacore - Mechanical Engineering\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":333,\"requiredTypeGroupName\":\"Datacores\",\"quantity\":64,\"damagePerJob\":\"1.0\",\"requiredTypeNameIcon\":\"64_03\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":22242,\"requiredTypeName\":\"Capital Ship Construction\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":269,\"requiredTypeGroupName\":\"Mechanic\",\"quantity\":1,\"damagePerJob\":\"0.0\",\"requiredTypeNameIcon\":\"50_11\"},{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":25855,\"requiredTypeName\":\"Incognito Ship Data Interface\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":716,\"requiredTypeGroupName\":\"Data Interfaces\",\"quantity\":1,\"damagePerJob\":\"1.0\",\"requiredTypeNameIcon\":\"63_06\"}]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>64</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>333</requiredTypeGroupID><requiredTypeGroupName>Datacores</requiredTypeGroupName><requiredTypeID>20410</requiredTypeID><requiredTypeName>Datacore - Gallentean Starship Engineering</requiredTypeName><requiredTypeNameIcon>64_02</requiredTypeNameIcon></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>64</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>333</requiredTypeGroupID><requiredTypeGroupName>Datacores</requiredTypeGroupName><requiredTypeID>20424</requiredTypeID><requiredTypeName>Datacore - Mechanical Engineering</requiredTypeName><requiredTypeNameIcon>64_03</requiredTypeNameIcon></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>269</requiredTypeGroupID><requiredTypeGroupName>Mechanic</requiredTypeGroupName><requiredTypeID>22242</requiredTypeID><requiredTypeName>Capital Ship Construction</requiredTypeName><requiredTypeNameIcon>50_11</requiredTypeNameIcon></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>716</requiredTypeGroupID><requiredTypeGroupName>Data Interfaces</requiredTypeGroupName><requiredTypeID>25855</requiredTypeID><requiredTypeName>Incognito Ship Data Interface</requiredTypeName><requiredTypeNameIcon>63_06</requiredTypeNameIcon></row></rowset>", getResponse(connection));
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
        assertEquals("[{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":20410,\"requiredTypeName\":\"Datacore - Gallentean Starship Engineering\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":333,\"requiredTypeGroupName\":\"Datacores\",\"quantity\":64,\"damagePerJob\":\"1.0\",\"requiredTypeNameIcon\":\"64_02\"},{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":20424,\"requiredTypeName\":\"Datacore - Mechanical Engineering\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":333,\"requiredTypeGroupName\":\"Datacores\",\"quantity\":64,\"damagePerJob\":\"1.0\",\"requiredTypeNameIcon\":\"64_03\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":22242,\"requiredTypeName\":\"Capital Ship Construction\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":269,\"requiredTypeGroupName\":\"Mechanic\",\"quantity\":1,\"damagePerJob\":\"0.0\",\"requiredTypeNameIcon\":\"50_11\"},{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":25855,\"requiredTypeName\":\"Incognito Ship Data Interface\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":716,\"requiredTypeGroupName\":\"Data Interfaces\",\"quantity\":1,\"damagePerJob\":\"1.0\",\"requiredTypeNameIcon\":\"63_06\"}]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>64</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>333</requiredTypeGroupID><requiredTypeGroupName>Datacores</requiredTypeGroupName><requiredTypeID>20410</requiredTypeID><requiredTypeName>Datacore - Gallentean Starship Engineering</requiredTypeName><requiredTypeNameIcon>64_02</requiredTypeNameIcon></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>64</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>333</requiredTypeGroupID><requiredTypeGroupName>Datacores</requiredTypeGroupName><requiredTypeID>20424</requiredTypeID><requiredTypeName>Datacore - Mechanical Engineering</requiredTypeName><requiredTypeNameIcon>64_03</requiredTypeNameIcon></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>269</requiredTypeGroupID><requiredTypeGroupName>Mechanic</requiredTypeGroupName><requiredTypeID>22242</requiredTypeID><requiredTypeName>Capital Ship Construction</requiredTypeName><requiredTypeNameIcon>50_11</requiredTypeNameIcon></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>716</requiredTypeGroupID><requiredTypeGroupName>Data Interfaces</requiredTypeGroupName><requiredTypeID>25855</requiredTypeID><requiredTypeName>Incognito Ship Data Interface</requiredTypeName><requiredTypeNameIcon>63_06</requiredTypeNameIcon></row></rowset>", getResponse(connection));
        assertEquals("application/xml; charset=utf-8", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/extraMaterialsForTypeName/Obelisk%20Blueprint");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        assertEquals(200, connection.getResponseCode());
        assertEquals("[{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":20410,\"requiredTypeName\":\"Datacore - Gallentean Starship Engineering\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":333,\"requiredTypeGroupName\":\"Datacores\",\"quantity\":64,\"damagePerJob\":\"1.0\",\"requiredTypeNameIcon\":\"64_02\"},{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":20424,\"requiredTypeName\":\"Datacore - Mechanical Engineering\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":333,\"requiredTypeGroupName\":\"Datacores\",\"quantity\":64,\"damagePerJob\":\"1.0\",\"requiredTypeNameIcon\":\"64_03\"},{\"activityID\":1,\"activityName\":\"Manufacturing\",\"requiredTypeID\":22242,\"requiredTypeName\":\"Capital Ship Construction\",\"requiredTypeCategoryID\":16,\"requiredTypeGroupID\":269,\"requiredTypeGroupName\":\"Mechanic\",\"quantity\":1,\"damagePerJob\":\"0.0\",\"requiredTypeNameIcon\":\"50_11\"},{\"activityID\":8,\"activityName\":\"Invention\",\"requiredTypeID\":25855,\"requiredTypeName\":\"Incognito Ship Data Interface\",\"requiredTypeCategoryID\":17,\"requiredTypeGroupID\":716,\"requiredTypeGroupName\":\"Data Interfaces\",\"quantity\":1,\"damagePerJob\":\"1.0\",\"requiredTypeNameIcon\":\"63_06\"}]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>64</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>333</requiredTypeGroupID><requiredTypeGroupName>Datacores</requiredTypeGroupName><requiredTypeID>20410</requiredTypeID><requiredTypeName>Datacore - Gallentean Starship Engineering</requiredTypeName><requiredTypeNameIcon>64_02</requiredTypeNameIcon></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>64</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>333</requiredTypeGroupID><requiredTypeGroupName>Datacores</requiredTypeGroupName><requiredTypeID>20424</requiredTypeID><requiredTypeName>Datacore - Mechanical Engineering</requiredTypeName><requiredTypeNameIcon>64_03</requiredTypeNameIcon></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>1</activityID><activityName>Manufacturing</activityName><damagePerJob>0.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>16</requiredTypeCategoryID><requiredTypeGroupID>269</requiredTypeGroupID><requiredTypeGroupName>Mechanic</requiredTypeGroupName><requiredTypeID>22242</requiredTypeID><requiredTypeName>Capital Ship Construction</requiredTypeName><requiredTypeNameIcon>50_11</requiredTypeNameIcon></row><row xsi:type=\"ramTypeRequirementDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><activityID>8</activityID><activityName>Invention</activityName><damagePerJob>1.0</damagePerJob><quantity>1</quantity><requiredTypeCategoryID>17</requiredTypeCategoryID><requiredTypeGroupID>716</requiredTypeGroupID><requiredTypeGroupName>Data Interfaces</requiredTypeGroupName><requiredTypeID>25855</requiredTypeID><requiredTypeName>Incognito Ship Data Interface</requiredTypeName><requiredTypeNameIcon>63_06</requiredTypeNameIcon></row></rowset>", getResponse(connection));
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
        assertEquals("[{\"itemTypeID\":847,\"itemCategoryID\":9,\"name\":\"Mega Beam Laser I Blueprint\",\"icon\":\"13_11\"},{\"itemTypeID\":3050,\"itemCategoryID\":9,\"name\":\"Mega Beam Laser II Blueprint\",\"icon\":\"13_11\"},{\"itemTypeID\":30224,\"itemCategoryID\":9,\"name\":\"Mega Module Blueprint\",\"icon\":\"01_08\"},{\"itemTypeID\":846,\"itemCategoryID\":9,\"name\":\"Mega Pulse Laser I Blueprint\",\"icon\":\"13_15\"},{\"itemTypeID\":3058,\"itemCategoryID\":9,\"name\":\"Mega Pulse Laser II Blueprint\",\"icon\":\"13_15\"},{\"itemTypeID\":995,\"itemCategoryID\":9,\"name\":\"Megathron Blueprint\",\"icon\":\"\"},{\"itemTypeID\":13203,\"itemCategoryID\":9,\"name\":\"Megathron Federate Issue Blueprint\",\"icon\":\"\"},{\"itemTypeID\":17729,\"itemCategoryID\":9,\"name\":\"Megathron Navy Issue Blueprint\",\"icon\":\"\"}]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>13_11</icon><itemCategoryID>9</itemCategoryID><itemTypeID>847</itemTypeID><name>Mega Beam Laser I Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>13_11</icon><itemCategoryID>9</itemCategoryID><itemTypeID>3050</itemTypeID><name>Mega Beam Laser II Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>9</itemCategoryID><itemTypeID>30224</itemTypeID><name>Mega Module Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>13_15</icon><itemCategoryID>9</itemCategoryID><itemTypeID>846</itemTypeID><name>Mega Pulse Laser I Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>13_15</icon><itemCategoryID>9</itemCategoryID><itemTypeID>3058</itemTypeID><name>Mega Pulse Laser II Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon></icon><itemCategoryID>9</itemCategoryID><itemTypeID>995</itemTypeID><name>Megathron Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon></icon><itemCategoryID>9</itemCategoryID><itemTypeID>13203</itemTypeID><name>Megathron Federate Issue Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon></icon><itemCategoryID>9</itemCategoryID><itemTypeID>17729</itemTypeID><name>Megathron Navy Issue Blueprint</name></row></rowset>", getResponse(connection));
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
        assertEquals("[{\"itemTypeID\":22140,\"itemCategoryID\":17,\"name\":\"Tri-Vitoc\",\"icon\":\"11_15\"},{\"itemTypeID\":27951,\"itemCategoryID\":7,\"name\":\"Triage Module I\",\"icon\":\"70_10\"},{\"itemTypeID\":27952,\"itemCategoryID\":9,\"name\":\"Triage Module I Blueprint\",\"icon\":\"06_03\"},{\"itemTypeID\":17428,\"itemCategoryID\":25,\"name\":\"Triclinic Bistot\",\"icon\":\"23_06\"},{\"itemTypeID\":25612,\"itemCategoryID\":4,\"name\":\"Trigger Unit\",\"icon\":\"69_14\"},{\"itemTypeID\":11066,\"itemCategoryID\":17,\"name\":\"Trinary Data\",\"icon\":\"34_05\"},{\"itemTypeID\":16307,\"itemCategoryID\":7,\"name\":\"Triple-sheathed Adaptive Nano Plating I\",\"icon\":\"01_08\"},{\"itemTypeID\":16315,\"itemCategoryID\":7,\"name\":\"Triple-sheathed Magnetic Plating I\",\"icon\":\"01_08\"},{\"itemTypeID\":16323,\"itemCategoryID\":7,\"name\":\"Triple-sheathed Reactive Plating I\",\"icon\":\"01_08\"},{\"itemTypeID\":16331,\"itemCategoryID\":7,\"name\":\"Triple-sheathed Reflective Plating I\",\"icon\":\"01_08\"},{\"itemTypeID\":16347,\"itemCategoryID\":7,\"name\":\"Triple-sheathed Regenerative Plating I\",\"icon\":\"01_08\"},{\"itemTypeID\":16339,\"itemCategoryID\":7,\"name\":\"Triple-sheathed Thermic Plating I\",\"icon\":\"01_08\"},{\"itemTypeID\":25598,\"itemCategoryID\":4,\"name\":\"Tripped Power Circuit\",\"icon\":\"69_15\"},{\"itemTypeID\":593,\"itemCategoryID\":6,\"name\":\"Tristan\",\"icon\":\"\"},{\"itemTypeID\":940,\"itemCategoryID\":9,\"name\":\"Tristan Blueprint\",\"icon\":\"\"},{\"itemTypeID\":17916,\"itemCategoryID\":17,\"name\":\"Tritan\\u0027s Forked Key\",\"icon\":\"34_06\"},{\"itemTypeID\":34,\"itemCategoryID\":4,\"name\":\"Tritanium\",\"icon\":\"06_14\"}]", getResponse(connection));
        assertEquals("application/json; charset=utf-8", connection.getHeaderField("Content-Type"));

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");
        assertEquals(200, connection.getResponseCode());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rowset><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>11_15</icon><itemCategoryID>17</itemCategoryID><itemTypeID>22140</itemTypeID><name>Tri-Vitoc</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>70_10</icon><itemCategoryID>7</itemCategoryID><itemTypeID>27951</itemTypeID><name>Triage Module I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>06_03</icon><itemCategoryID>9</itemCategoryID><itemTypeID>27952</itemTypeID><name>Triage Module I Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>23_06</icon><itemCategoryID>25</itemCategoryID><itemTypeID>17428</itemTypeID><name>Triclinic Bistot</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>69_14</icon><itemCategoryID>4</itemCategoryID><itemTypeID>25612</itemTypeID><name>Trigger Unit</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>34_05</icon><itemCategoryID>17</itemCategoryID><itemTypeID>11066</itemTypeID><name>Trinary Data</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>7</itemCategoryID><itemTypeID>16307</itemTypeID><name>Triple-sheathed Adaptive Nano Plating I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>7</itemCategoryID><itemTypeID>16315</itemTypeID><name>Triple-sheathed Magnetic Plating I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>7</itemCategoryID><itemTypeID>16323</itemTypeID><name>Triple-sheathed Reactive Plating I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>7</itemCategoryID><itemTypeID>16331</itemTypeID><name>Triple-sheathed Reflective Plating I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>7</itemCategoryID><itemTypeID>16347</itemTypeID><name>Triple-sheathed Regenerative Plating I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>01_08</icon><itemCategoryID>7</itemCategoryID><itemTypeID>16339</itemTypeID><name>Triple-sheathed Thermic Plating I</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>69_15</icon><itemCategoryID>4</itemCategoryID><itemTypeID>25598</itemTypeID><name>Tripped Power Circuit</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon></icon><itemCategoryID>6</itemCategoryID><itemTypeID>593</itemTypeID><name>Tristan</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon></icon><itemCategoryID>9</itemCategoryID><itemTypeID>940</itemTypeID><name>Tristan Blueprint</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>34_06</icon><itemCategoryID>17</itemCategoryID><itemTypeID>17916</itemTypeID><name>Tritan's Forked Key</name></row><row xsi:type=\"invTypeBasicInfoDto\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><icon>06_14</icon><itemCategoryID>4</itemCategoryID><itemTypeID>34</itemTypeID><name>Tritanium</name></row></rowset>", getResponse(connection));
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
        assertTrue(getResponse(connection).endsWith("-dom100"));
        assertEquals("text/plain; charset=utf-8", connection.getHeaderField("Content-Type"));

        url = new URL(baseUrl + "/");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        assertEquals(200, connection.getResponseCode());
        assertTrue(getResponse(connection).endsWith("-dom100"));
        assertEquals("text/plain; charset=utf-8", connection.getHeaderField("Content-Type"));
    }

    @Test
    public void test_dom100specific_NoSBU() throws Exception {
        URL url = new URL(baseUrl + "/typeNameToTypeID/Sovereignty+Blockade+Unit+Blueprint");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/plain");
        assertEquals(400, connection.getResponseCode());
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
