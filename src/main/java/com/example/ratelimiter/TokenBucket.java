package com.example.ratelimiter;

public class TokenBucket {
    private final long capacity;
    private final long refillRatePerSecond;

    private long tokens;
    private long lastRefillTimeMs;

    public TokenBucket(long capacity, long refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.tokens = capacity;
        this.lastRefillTimeMs = System.currentTimeMillis();
    }

    public synchronized boolean tryConsume() {
        refill();
        if (this.tokens <= 0) {
            return false;
        }
        this.tokens--;
        return true;
    }

    private void refill() {
        long nowMs = System.currentTimeMillis();
        long elapsedMs = nowMs - this.lastRefillTimeMs;
        if (elapsedMs <= 0) {
            return;
        }
        // Tokens to add: (elapsed seconds) * refillRate
        long newTokens = (long) ((elapsedMs / 1000.0) * this.refillRatePerSecond);
        if (newTokens > 0) {
            this.tokens = Math.min(this.capacity, this.tokens + newTokens);
            // Advance by the time that produced these tokens (preserve fractional seconds)
            this.lastRefillTimeMs += (newTokens * 1000L) / this.refillRatePerSecond;
        }
    }
}
