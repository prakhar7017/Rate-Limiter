package com.example.ratelimiter;

public class RateLimiterFactory {

    public static RateLimiter createRateLimiter(RateLimiterType type, RateLimiterConfig config){
        switch(type){
            case Token_Bucket:
                return new TokenBucketRateLimiter(config.getBurstCapacity(), config.getRefillRate());
            case Leaking_Bucket:
                return new LeakingBucketRateLimiter(config.getBurstCapacity(), config.getLeakRate());    
            default:
                throw new IllegalArgumentException("Unsupported rate limiter type");
        }
    }

}
