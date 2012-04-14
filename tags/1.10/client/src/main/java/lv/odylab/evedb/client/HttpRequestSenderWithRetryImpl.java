package lv.odylab.evedb.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpRequestSenderWithRetryImpl implements EveDbWsClient.HttpRequestSender {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Integer retryCount;
    private final EveDbWsClient.HttpRequestSender httpRequestSender;

    public HttpRequestSenderWithRetryImpl(Integer retryCount, EveDbWsClient.HttpRequestSender httpRequestSender) {
        this.retryCount = retryCount;
        this.httpRequestSender = httpRequestSender;
    }

    @Override
    public String doGet(String urlString, String acceptHeader) {
        for (int i = 1; i <= retryCount; i++) {
            try {
                return httpRequestSender.doGet(urlString, acceptHeader);
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    logger.warn("doGet failed, attempt number: {}", i);
                    if (i == retryCount) {
                        logger.warn("doGet failed after {} attempts, throwing exception", i);
                        throw e;
                    }
                } else {
                    throw e;
                }
            }
        }
        throw new RuntimeException("doGet internal error");
    }
}