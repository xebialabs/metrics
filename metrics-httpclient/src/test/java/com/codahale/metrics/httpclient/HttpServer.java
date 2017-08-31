package com.codahale.metrics.httpclient;

import org.junit.rules.ExternalResource;
import org.junit.rules.TemporaryFolder;

public class HttpServer extends ExternalResource {

    private org.glassfish.grizzly.http.server.HttpServer httpServer;

    private TemporaryFolder docRoot = new TemporaryFolder();

    @Override
    protected void before() throws Throwable {
        docRoot.create();
        httpServer = org.glassfish.grizzly.http.server.HttpServer.createSimpleServer(docRoot.getRoot().getAbsolutePath());
        httpServer.start();
    }

    @Override
    protected void after() {
        try {
            httpServer.shutdownNow();
        } catch (Exception e) {}
        try {
            docRoot.delete();
        } catch (Exception e) {}

    }

    public org.glassfish.grizzly.http.server.HttpServer getHttpServer() {
        return httpServer;
    }

    public TemporaryFolder getDocRoot() {
        return docRoot;
    }
}
