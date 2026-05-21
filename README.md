# Apache Kafka Concept Study

## Core Concepts
- **Topic**: Named channel for messages
- **Partition**: Ordered log within a topic
- **Broker**: Kafka server storing partitions
- **Offset**: Message position in a partition
- **Producer**: Writes messages to topics
- **Consumer**: Reads messages from topics
- **Consumer Group**: Group sharing partitions for load balancing
- **Zookeeper/KRaft**: Cluster coordination layer

## Message Flow
Producer → Topic (Partitions) → Consumer Group → Consumers

## Key Properties
- Append-only log (immutable)
- Replicated across brokers
- Configurable retention (time or size)
- At-least-once / exactly-once delivery

## Delivery Semantics
- at-most-once: fire and forget
- at-least-once: ack + retry
- exactly-once: idempotent producer + transactions
