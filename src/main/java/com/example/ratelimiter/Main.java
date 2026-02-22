package com.example.ratelimiter;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        RateLimiterConfig config = new RateLimiterConfig(5, 3, 1);
        RateLimiter limiter = RateLimiterFactory.createRateLimiter(RateLimiterType.Leaking_Bucket, config);

        String clientId = "user-123";

        for (int i = 0; i <= 6; i++) {
            boolean allowed = limiter.allowRequest(clientId);
            System.out.println("Request " + i + " allowed: " + allowed);
        }

        Thread.sleep(1000);
        for (int i = 0; i <= 4; i++) {
            boolean allowed = limiter.allowRequest(clientId);
            System.out.println("Request " + i + " allowed: " + allowed);
        }
    }
}
