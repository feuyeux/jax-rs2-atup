package org.feuyeux.jaxrs2.atup.core.rest;

import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.util.CollectionUtils;

/**
 * ATUP Rest Request
 * 
 * @author feuyeux@gmail.com
 * @since 1.0
 * 09/09/2013
 */
public class AtupRequest<T> {
    private ClientConfig clientConfig;
    private Set<Class<?>> clientRegisters;

    //security
    //timeout
    
    public AtupRequest() {
    }

    public AtupRequest(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    public void setClientRegisters(Set<Class<?>> clientRegisters) {
        this.clientRegisters = clientRegisters;
    }

    public T rest(String method, String requestUrl, Set<AtupRequestParam> headParams, Set<AtupRequestParam> queryParams, MediaType requestDataType,
            Class<T> returnType) {
        if (clientConfig == null) {
            clientConfig = new ClientConfig();
        }
        Client client = ClientBuilder.newClient(clientConfig);

        if (!CollectionUtils.isEmpty(clientRegisters)) {
            for (Class<?> clazz : clientRegisters) {
                client.register(clazz);
            }
        }

        WebTarget webTarget = client.target(requestUrl);
        if (!CollectionUtils.isEmpty(queryParams)) {
            for (AtupRequestParam atupRequestParam : queryParams) {
                webTarget = webTarget.queryParam(atupRequestParam.getKey(), atupRequestParam.getValue());
            }
        }

        Invocation.Builder invocationBuilder = webTarget.request(requestDataType);
        if (!CollectionUtils.isEmpty(headParams)) {
            for (AtupRequestParam atupRequestParam : headParams) {
                invocationBuilder.header(atupRequestParam.getKey(), atupRequestParam.getValue());
            }
        }

        switch (method) {
            case "GET":
                javax.ws.rs.core.Response response = invocationBuilder.get();
                return response.readEntity(returnType);

            default:
                return null;
        }

    }
}
