package au.com.unico.ws.endpoint;

import au.com.unico.dao.GcdRepository;
import au.com.unico.domain.Gcd;
import au.com.unico.gcd_ws.GetGcdListResponse;
import au.com.unico.gcd_ws.GetGcdResponse;
import au.com.unico.gcd_ws.GetGcdSumResponse;
import au.com.unico.service.GcdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by xiaofei on 2017/12/10.
 */
@Endpoint
public class GcdEndpoint {
    private static final String NAMESPACE_URI = "http://www.unico.com.au/gcd-ws";

    private GcdRepository gcdRepository;

    private GcdService service;

    @Autowired
    public GcdEndpoint(GcdRepository gcdRepository, GcdService service) {
        this.gcdRepository = gcdRepository;
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGcdRequest")
    @ResponsePayload
    public GetGcdResponse getGcd() {
        Optional<Integer> gcd = service.getGcd();
        GetGcdResponse response = new GetGcdResponse();
        if (gcd.isPresent()) {
            response.setGcd(gcd.get());
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGcdListRequest")
    @ResponsePayload
    public GetGcdListResponse gcdList() {
        List<Gcd> gcdList = (List<Gcd>) gcdRepository.findAll();

        GetGcdListResponse response = new GetGcdListResponse();

        response.getGcdList().addAll(gcdList.stream().map((gcd) -> gcd.getGcdNum()).collect(Collectors.toList()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGcdSumRequest")
    @ResponsePayload
    public GetGcdSumResponse gcdSum() {
        GetGcdSumResponse response = new GetGcdSumResponse();

        List<Gcd> gcdList = (List<Gcd>) gcdRepository.findAll();
        if (CollectionUtils.isEmpty(gcdList)) {
            return response;
        }
        response.setGcdSum(gcdList.stream().mapToInt((gcd) -> gcd.getGcdNum()).sum());
        return response;
    }
}
