# Rate Limiter

A small Java library that limits how many requests a client can make using **Token Bucket** and **Leaking Bucket** algorithms. Useful for APIs, services, or any place you need per-client rate limiting.

## Features

- **Token Bucket** – Burst capacity with a steady refill rate (tokens per second).
- **Leaking Bucket** – Fixed-capacity queue with a constant leak (processing) rate.
- **Per-client limits** – Each client (e.g. by `clientId`) has its own bucket.
- **Configurable** – Set burst capacity, refill rate, and leak rate via `RateLimiterConfig`.
- **Java 8+** – Maven project, no extra dependencies for the core logic.

## Prerequisites

- **Java 8** or higher  
- **Maven 3.x**

## Build & Run

```bash
# Compile
mvn clean compile

# Run the demo (Main class)
mvn exec:java -Dexec.mainClass="com.example.ratelimiter.Main"

# Run tests
mvn test
```

## Quick Start

```java
import com.example.ratelimiter.*;

// Config: burstCapacity=5, refillRate=3/sec, leakRate=1/sec
RateLimiterConfig config = new RateLimiterConfig(5, 3, 1);

// Choose algorithm
RateLimiter limiter = RateLimiterFactory.createRateLimiter(RateLimiterType.Token_Bucket, config);

String clientId = "user-123";
if (limiter.allowRequest(clientId)) {
    // Request allowed — process it
} else {
    // Rate limited — reject or retry later
}
```

### Token Bucket

- **Burst capacity**: Max tokens at once (e.g. 5).
- **Refill rate**: Tokens added per second (e.g. 3).
- Good when you want to allow short bursts and then a steady rate.

### Leaking Bucket

- **Capacity**: Max requests queued (e.g. 5).
- **Leak rate**: Requests processed per second (e.g. 1).
- Good when you want to smooth traffic to a fixed output rate.

## Project Structure

```
src/main/java/com/example/ratelimiter/
├── RateLimiter.java           # Interface: allowRequest(clientId)
├── RateLimiterConfig.java     # burstCapacity, refillRate, leakRate
├── RateLimiterFactory.java    # Creates Token Bucket or Leaking Bucket limiter
├── RateLimiterType.java       # Token_Bucket, Leaking_Bucket
├── TokenBucket.java           # Token bucket state & refill logic
├── TokenBucketRateLimiter.java
├── LeakingBucketRateLimiter.java
└── Main.java                  # Demo usage
```

## Configuration

| Parameter        | Token Bucket | Leaking Bucket |
|------------------|--------------|-----------------|
| Burst capacity   | Max tokens   | Queue size      |
| Refill rate     | Tokens/sec   | —               |
| Leak rate       | —            | Processed/sec   |

Constructor: `RateLimiterConfig(burstCapacity, refillRate, leakRate)` — use the rate that applies to your chosen algorithm; the other is ignored for that type.

## License

This project is open source. Use it as you like.

---

If you find this useful, consider giving it a star.
