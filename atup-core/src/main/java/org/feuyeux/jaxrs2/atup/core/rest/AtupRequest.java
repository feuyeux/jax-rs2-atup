package org.feuyeux.jaxrs2.atup.core.rest;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * ATUP Rest Request
 *
 * @author feuyeux@gmail.com
 * @since 1.0
 * 09/09/2013
 */
public class AtupRequest<S, T> {
    public static final String GET = "GET";
    public static final String DELETE = "DELETE";
    public static final String PUT = "PUT";
    public static final String POST = "POST";
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

    public T rest(String method, String requestUrl, Class<T> returnType) {
        return rest(method, requestUrl, null, null, null, null, returnType);
    }

    public T rest(String method, String requestUrl, Set<AtupRequestParam> headParams, Set<AtupRequestParam> queryParams, MediaType requestDataType,
                  Class<T> returnType) {
        return rest(method, requestUrl, headParams, queryParams, requestDataType, null, returnType);
    }

    public T rest(String method, String requestUrl, Set<AtupRequestParam> headParams, Set<AtupRequestParam> queryParams, MediaType requestDataType, S requestData,
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

        javax.ws.rs.core.Response response;
        Entity<S> entity;
        switch (method) {
            case GET:
                response = invocationBuilder.get();
                return response.readEntity(returnType);
            case DELETE:
                response = invocationBuilder.delete();
                return response.readEntity(returnType);
            case PUT:
                entity = Entity.entity(requestData, requestDataType);
                response = invocationBuilder.put(entity);
                return response.readEntity(returnType);
            case POST:
                entity = Entity.entity(requestData, requestDataType);
                response = invocationBuilder.post(entity);
                return response.readEntity(returnType);
            default:
                return null;
        }
    }
}
