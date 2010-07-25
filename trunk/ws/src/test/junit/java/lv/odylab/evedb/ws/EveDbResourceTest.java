package lv.odylab.evedb.ws;

import lv.odylab.evedb.domain.IdNotFoundException;
import lv.odylab.evedb.domain.NameNotFoundException;
import lv.odylab.evedb.domain.TooShortPartialNameException;
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
    private static final String PATH_EXCEPTION = "/exception";

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
        tester.addServlet(ExceptionDummyResource.class, PATH_EXCEPTION + "/*");
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

    @Test
    public void testGetXml_WithMultipleAcceptHeaders() throws Exception {
        request.setURI(baseUrl + PATH_XML + "/path");
        request.setHeader("accept", "application/json,text/*,application/xml");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("xml-path", response.getContent());
        assertEquals("application/xml; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    public void testGetXml_WithComplexMultipleAcceptHeaders() throws Exception {
        request.setURI(baseUrl + PATH_XML + "/path");
        request.setHeader("accept", "application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("xml-path", response.getContent());
        assertEquals("application/xml; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    public void testGetText_WithComplexMultipleAcceptHeaders() throws Exception {
        request.setURI(baseUrl + PATH_TEXT);
        request.setHeader("accept", "application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(200, response.getStatus());
        assertEquals("text-null", response.getContent());
        assertEquals("text/plain; charset=utf-8", response.getHeader("Content-Type"));
    }

    @Test
    public void test_NumberFormatException() throws Exception {
        request.setURI(baseUrl + PATH_EXCEPTION + "/" + NumberFormatException.class.getName());
        request.setHeader("accept", "text/plain");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(400, response.getStatus());
    }

    @Test
    public void test_IdNotFoundException() throws Exception {
        request.setURI(baseUrl + PATH_EXCEPTION + "/" + IdNotFoundException.class.getName());
        request.setHeader("accept", "text/plain");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(400, response.getStatus());
    }

    @Test
    public void test_NameNotFoundException() throws Exception {
        request.setURI(baseUrl + PATH_EXCEPTION + "/" + NameNotFoundException.class.getName());
        request.setHeader("accept", "text/plain");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(400, response.getStatus());
    }

    @Test
    public void test_TooShortPartialNameException() throws Exception {
        request.setURI(baseUrl + PATH_EXCEPTION + "/" + TooShortPartialNameException.class.getName());
        request.setHeader("accept", "text/plain");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(400, response.getStatus());
    }

    @Test
    public void test_Exception() throws Exception {
        request.setURI(baseUrl + PATH_EXCEPTION + "/" + Exception.class.getName());
        request.setHeader("accept", "text/plain");
        response.parse(tester.getResponses(request.generate()));
        assertEquals(500, response.getStatus());
    }
}

