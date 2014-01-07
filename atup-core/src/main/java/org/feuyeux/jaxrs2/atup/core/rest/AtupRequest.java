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
    private static final String DELETE = "DELETE";
    private static final String PUT = "PUT";
    public static final String POST = "POST";
    private ClientConfig clientConfig;
    private Set<Class<?>> clientRegisters;

    //security
    //timeout

    public AtupRequest() {
    }

    public AtupRequest(final ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    public void setClientRegisters(final Set<Class<?>> clientRegisters) {
        this.clientRegisters = clientRegisters;
    }

    public T rest(final String method, final String requestUrl, final Class<T> returnType) {
        return rest(method, requestUrl, null, null, null, null, returnType);
    }

    public T rest(final String method, final String requestUrl, final Set<AtupRequestParam> headParams, final Set<AtupRequestParam> queryParams,
                  final MediaType requestDataType, final Class<T> returnType) {
        return rest(method, requestUrl, headParams, queryParams, requestDataType, null, returnType);
    }

    public T rest(final String method, final String requestUrl, final Set<AtupRequestParam> headParams, final Set<AtupRequestParam> queryParams,
                  final MediaType requestDataType, final S requestData, final Class<T> returnType) {
        if (clientConfig == null) {
            clientConfig = new ClientConfig();
        }
        final Client client = ClientBuilder.newClient(clientConfig);

        if (!CollectionUtils.isEmpty(clientRegisters)) {
            for (final Class<?> clazz : clientRegisters) {
                client.register(clazz);
            }
        }

        WebTarget webTarget = client.target(requestUrl);
        if (!CollectionUtils.isEmpty(queryParams)) {
            for (final AtupRequestParam atupRequestParam : queryParams) {
                webTarget = webTarget.queryParam(atupRequestParam.getKey(), atupRequestParam.getValue());
            }
        }

        final Invocation.Builder invocationBuilder = webTarget.request();
        if (!CollectionUtils.isEmpty(headParams)) {
            for (final AtupRequestParam atupRequestParam : headParams) {
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
