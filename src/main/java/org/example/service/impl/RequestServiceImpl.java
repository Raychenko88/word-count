package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.RequestDAO;
import org.example.model.Request;
import org.example.service.RequestService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestDAO requestDAO;

    @Override
    public Request save(String requestString) {
        Request request = new Request();
        request.setPayload(requestString);
        return requestDAO.save(request);
    }
}
