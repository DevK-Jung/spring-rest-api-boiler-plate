package com.kjung.boilerplate.httpclient.httpInterface;

import com.kjung.boilerplate.httpclient.httpInterface.dto.SampleDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

@HttpExchange("/posts")
public interface SampleHttpInterfaceService {
    @GetExchange("/{postId}")
    SampleDto getSample(@PathVariable Long postId);

    @PostExchange
    SampleDto postSample(@RequestBody SampleDto param);

    @PutExchange
    SampleDto putSample(@RequestBody SampleDto param);

    @DeleteExchange("/{postId}")
    void deleteSample(@PathVariable Long postId);
}
