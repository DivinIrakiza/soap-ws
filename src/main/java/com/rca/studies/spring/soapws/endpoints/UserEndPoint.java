package com.rca.studies.spring.soapws.endpoints;

import com.rca.studies.spring.soapws.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import studies.rca.com.soap.spring.soapws.GetUserRequest;
import studies.rca.com.soap.spring.soapws.GetUserResponse;

@Endpoint
public class UserEndPoint {
    @Autowired
    private UserService userService;


    @PayloadRoot(namespace = "http://soap.com.rca.studies/spring/soapws", localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUserRequest(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        response.setUser(userService.getUsers(request.getName()));
        return response;
    }
}
