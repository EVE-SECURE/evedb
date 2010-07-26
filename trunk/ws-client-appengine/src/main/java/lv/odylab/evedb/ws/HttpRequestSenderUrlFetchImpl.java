package lv.odylab.evedb.ws;

import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import lv.odylab.appengine.GoogleAppEngineServices;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.google.appengine.api.urlfetch.FetchOptions.Builder.withDeadline;

public class HttpRequestSenderUrlFetchImpl implements EveDbWsClient.HttpRequestSender {
    private GoogleAppEngineServices appEngineServices;

    public HttpRequestSenderUrlFetchImpl(GoogleAppEngineServices appEngineServices) {
        this.appEngineServices = appEngineServices;
    }

    @Override
    public String doGet(String urlString, String acceptHeader) {
        URLFetchService urlFetchService = appEngineServices.getUrlFetchService();
        try {
            HTTPRequest httpRequest = new HTTPRequest(new URL(urlString), HTTPMethod.GET, withDeadline(10d));
            HTTPResponse httpResponse = urlFetchService.fetch(httpRequest);
            if (httpResponse.getResponseCode() == 400) {
                throw new BadRequestException();
            }
            return httpResponse.getContent().toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
