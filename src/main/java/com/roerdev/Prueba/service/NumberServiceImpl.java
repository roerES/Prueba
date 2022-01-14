package com.roerdev.Prueba.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class NumberServiceImpl implements NumberService{

    private Logger logger = LoggerFactory.getLogger(NumberServiceImpl.class);

    private final ExecutorService executor;

    private StringBuffer stringBuffer = new StringBuffer();

    public NumberServiceImpl(ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    @Async("threadPoolExecutor")
    public void addNumber(String value) throws Exception{
        logger.info("Agregando valor de: {}", value);
        CompletableFuture<String> futureString = produceFutureString(value);
        futureString.whenComplete((valueFuture, throwable) -> stringBuffer.append(valueFuture));
        logger.info("{}", futureString.get());
    }

    @Override
    public String result(String operator){
        double result = 0;

        List<Double> values = Arrays.stream(stringBuffer.toString().split("-"))
                .filter(s -> !s.isEmpty()).map(Double::parseDouble).collect(Collectors.toList());
        if(values.isEmpty()) {
            return String.valueOf(result);
        }
        switch(operator){
            case "s":
                result = this.calculateSum(values);
                break;
            case "-":
                result = this.calculateRes(values);
                break;
            case "*":
                result = this.calculateMult(values);
                break;
            case "/":
                result = this.calculateDiv(values);
                break;
            default:
                throw new IllegalStateException("No se puede realizar operaciones con el operador seleccionado: " + operator);
        }
        return String.valueOf(result);
    }

    private CompletableFuture<String> produceFutureString(String value) {
        return CompletableFuture.supplyAsync(
                () -> String.format("%s-", value),
                executor);
    }

    private Double calculateSum(List<Double> values){
        double result = values.get(0);
        for(int i = 1; i < values.size(); i++){
            result = result + values.get(i);
        }
        return result;
    }

    private Double calculateRes(List<Double> values){
        double result = values.get(0);
        for(int i = 1; i < values.size(); i++){
            result = result - values.get(i);
        }
        return result;
    }

    private Double calculateMult(List<Double> values){
        double result = values.get(0);
        for(int i = 1; i < values.size(); i++){
            result = result * values.get(i);
        }
        return result;
    }

    private Double calculateDiv(List<Double> values){
        double result = values.get(0);
        for(int i = 1; i < values.size(); i++){
            result = result / values.get(i);
        }
        return result;
    }
}
