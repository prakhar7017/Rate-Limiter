package com.example.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {
    private final long capacity;
    private final long refillRate;
    private final Map<String, TokenBucket> bucketsByClient = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(long capacity, long refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
    }

    public boolean allowRequest(String clientId) {
        TokenBucket bucket = this.bucketsByClient.computeIfAbsent(clientId, id -> new TokenBucket(capacity, refillRate));
        return bucket.tryConsume();
    }
}
