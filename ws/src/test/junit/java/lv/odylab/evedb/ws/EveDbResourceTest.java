package lv.odylab.evedb.ws;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.jetty.testing.HttpTester;
import org.mortbay.jetty.testing.ServletTester;

import static junit.framework.Assert.assertEquals;

public class EveDbResourceTest {
    private static final String PATH_JSON = "/json";
    private static final String PATH_XML = "/xml";
    private static final String PATH_JSON_XML = "/jsonxml";
    private static final String PATH_TEXT = "/text";

    private HttpTester request;
    private HttpTester response;
    private ServletTester tester;
    private String baseUrl;

    @Before
    public void startServlet() throws Exception {
        request = new HttpTester();
        request.setMethod("GET");
        request.setHeader("Host", "tester");
        response = new HttpTester();
        tester = new ServletTester();
        tester.setContextPath("/");
        tester.addServlet(JsonDummyResource.class, PATH_JSON + "/*");
        tester.addServlet(XmlDummyResource.class, PATH_XML + "/*");
        tester.addServlet(JsonXmlDummyResource.class, PATH_JSON_XML + "/*");
        tester.addServlet(TextDummyResource.class, PATH_TEXT + "/*");
        baseUrl = tester.createSocketConnector(true);
        tester.start();
    }

    @After
    public void stopServlet() throws Exception {
        tester.stop();
    }

    @Test
    public void testPost() throws Exception {
        request.setMethod("POST");
        request.setURI(baseUrl + PATH_TEXT);
        response.parse(tester.getResponses(request.generate()));
        assertEquals(405, response.getStatus());
    }

    @Test
    public void testGetJson_WithPath_WithoutAcceptHeader() throws Exception {
        request.setURI(baseUrl + PATH_JSON + "/path");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(406, response.getStatus());
    }

    @Test
    public void testGetJson_WithPath_WithAcceptHeaderJson() throws Exception {
        request.setURI(baseUrl + PATH_JSON + "/path");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("json-path", response.getContent());
        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    public void testGetJson_WithPath_WithAcceptHeaderXml() throws Exception {
        request.setURI(baseUrl + PATH_JSON + "/path");
        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(406, response.getStatus());
    }

    @Test
    public void testGetJson_WithoutPath_WithAcceptHeaderJson() throws Exception {
        request.setURI(baseUrl + PATH_JSON);
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("json-null", response.getContent());
        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    public void testGetJsonXml_WithPath_WithAcceptHeaderJson() throws Exception {
        request.setURI(baseUrl + PATH_JSON_XML + "/path");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("json-path", response.getContent());
        assertEquals("application/json; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    public void testGetJsonXml_WithPath_WithAcceptHeaderXml() throws Exception {
        request.setURI(baseUrl + PATH_JSON_XML + "/path");
        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("xml-path", response.getContent());
        assertEquals("application/xml; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    public void testGetJsonXml_WithPath_WithAcceptHeaderTextPlain() throws Exception {
        request.setURI(baseUrl + PATH_JSON_XML + "/path");
        request.setHeader("accept", "text/plain");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("xml-path", response.getContent());
        assertEquals("application/xml; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    public void testGetJsonXml_WithPath_WithAcceptHeaderTextHtml() throws Exception {
        request.setURI(baseUrl + PATH_JSON_XML + "/path");
        request.setHeader("accept", "text/html");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("xml-path", response.getContent());
        assertEquals("application/xml; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    public void testGetText_WithPath_WithAcceptHeaderJson() throws Exception {
        request.setURI(baseUrl + PATH_TEXT + "/path");
        request.setHeader("accept", "application/json");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(406, response.getStatus());
    }

    @Test
    public void testGetText_WithPath_WithAcceptHeaderXml() throws Exception {
        request.setURI(baseUrl + PATH_TEXT + "/path");
        request.setHeader("accept", "application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(406, response.getStatus());
    }

    @Test
    public void testGetText_WithPath_WithAcceptHeaderTextPlain() throws Exception {
        request.setURI(baseUrl + PATH_TEXT + "/path");
        request.setHeader("accept", "text/plain");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("text-path", response.getContent());
        assertEquals("text/plain; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    public void testGetText_WithPath_WithAcceptHeaderTextHtml() throws Exception {
        request.setURI(baseUrl + PATH_TEXT + "/path");
        request.setHeader("accept", "text/html");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("text-path", response.getContent());
        assertEquals("text/plain; charset=utf-8", response.getHeader("Content-Type"));
    }
}

