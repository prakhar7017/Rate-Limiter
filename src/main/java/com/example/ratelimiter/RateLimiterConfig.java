package com.example.ratelimiter;

public class RateLimiterConfig {
    private final long burstCapacity;
    private final long refillRatePerSecond;
    private final long leakRatePerSecond;

    public RateLimiterConfig(long burstCapacity, long refillRate, long leakRate) {
        this.burstCapacity = burstCapacity;
        this.refillRatePerSecond = refillRate;
        this.leakRatePerSecond = leakRate;
    }

    public long getBurstCapacity() {
        return this.burstCapacity;
    }

    public long getRefillRate() {
        return this.refillRatePerSecond;
    }

    public long getLeakRate() {
        return this.leakRatePerSecond;
    }
}
