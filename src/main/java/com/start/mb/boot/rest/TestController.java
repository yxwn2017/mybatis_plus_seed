package com.start.mb.boot.rest;

import com.start.mb.boot.rest.feign.FeignTestRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alibaba.nacos.ribbon.NacosServer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

/**
 * @author wll
 * @version version
 * @title TestController
 * @desc
 * @date 2020/3/27
 */
@Slf4j
@RestController
public class TestController {

    @LoadBalanced
    private final RestTemplate restTemplate;

    private final LoadBalancerClient loadBalancerClient;

    @Autowired
    private FeignTestRest feignTestRest;

    @Autowired
    public TestController(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
    }

    @RequestMapping(value = "/echo/01/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        //通过服务名直接访问，需要RestTemplate支持@LoadBalanced
        return restTemplate.getForObject("http://service-provider/api/echo/" + str, String.class);
    }

    /**
     * LoadBalancerClient
     * java.lang.IllegalStateException: No instances available for 172.20.84.23
     */
    @RequestMapping(value = "/echo/02/{str}", method = RequestMethod.GET)
    public String echo02(@PathVariable String str) {

        ServiceInstance serviceInstance = loadBalancerClient.choose("service-provider");
        URI uri = serviceInstance.getUri();
        String serverName = serviceInstance.getServiceId();
        String forObject = restTemplate.getForObject(uri + "/api/echo/" + str, String.class);

        return forObject;
    }

    /**
     * java.lang.IllegalStateException: No instances available for 172.20.84.23
     *
     * @param str
     * @return
     */
    @RequestMapping(value = "/echo/03/{str}", method = RequestMethod.GET)
    public String echo03(@PathVariable String str) {

        //获取元信息---》此方式需去掉RestTemplate上的@LoadBalanced注解
        RibbonLoadBalancerClient.RibbonServer ribbonServer = (RibbonLoadBalancerClient.RibbonServer) loadBalancerClient.choose("service-provider");
        NacosServer nacosServer = (NacosServer) ribbonServer.getServer();
        Map<String, String> metadata = nacosServer.getMetadata();
        System.out.println("元数据------>" + metadata);
        String forObject = restTemplate.getForObject(ribbonServer.getUri() + "/api/echo/" + str, String.class);

        return forObject;
    }

    @RequestMapping(value = "/echo/04/{str}", method = RequestMethod.GET)
    public String echo04(@PathVariable String str) {

        Long st = System.currentTimeMillis();
        String result = feignTestRest.echo(str);
        Long et =  System.currentTimeMillis();

        log.info("for info ,start time is |{}|,end time is ||{}, request time : {}",st,et,et-st);

        return result;
    }

    /**
     *  Webflux模式替换了旧的Servlet线程模型。用少量的线程处理request和response io操作，
     * 这些线程称为Loop线程，而业务交给响应式编程框架处理，响应式编程是非常灵活的
     * 用户可以将业务中阻塞的操作提交到响应式框架的work线程中执行，
     * 而不阻塞的操作依然可以在Loop线程中进行处理，大大提高了Loop线程的利用率。
     * 反应堆式编程reactor。
     * <p>
     * 原文链接：https://blog.csdn.net/qq_38765404/article/details/89521124
     */

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @RequestMapping(value = "/echo/05/{str}", method = RequestMethod.GET)
    public Mono<String> echo05(@PathVariable String str) {

        Mono<String> result = webClientBuilder.build()
                .get()
                .uri("http://service-provider/api/echo/" + str)
                .retrieve()
                .bodyToMono(String.class);
        return result;
    }

}
