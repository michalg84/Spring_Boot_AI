package com.galka.springbootai.service;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;

import java.util.concurrent.TimeUnit;


@State(Scope.Thread)
@BenchmarkMode(Mode.All)
public class OllamaServiceTest {

    String url = "http://localhost:11434";
    String model = "gemma:2b";
    String temperature = "0.7f";
    private OllamaService ollamaService = new OllamaService(new OllamaChatClient(new OllamaApi(url))
            .withDefaultOptions(OllamaOptions.create()
                        .withModel(model)
                        .withTemperature(Float.valueOf(temperature))));

    @Benchmark
    @Warmup(iterations = 1, time = 10)
    @Measurement(iterations = 3, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(1)
    public void measureName(Blackhole bh) {
        String response = ollamaService.call("How much is 2 + 4");
        bh.consume(response);
    }
}