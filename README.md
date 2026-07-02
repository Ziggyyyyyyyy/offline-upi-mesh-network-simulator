# 🚀 Offline UPI Mesh Network Simulator

A **Spring Boot-based backend implementation** that demonstrates how **encrypted UPI payment instructions** can be securely routed through a **Mesh Network** when the sender does not have internet connectivity.

Instead of sending a payment request directly to a banking server, the system forwards encrypted payment packets across nearby **virtual mesh devices** until they reach a **Bridge Device** with internet access. The bridge validates, decrypts, and settles the transaction while ensuring security using **Hybrid Encryption**, **Replay Protection**, and **Idempotent Processing**.

This project focuses on exploring concepts used in **Distributed Systems**, **Secure Payment Infrastructure**, **Store-and-Forward Networking**, and **Backend System Design**.

---

# 🎥 Demo

> 📹 Loom Demo: *(Add your Loom link here)*

> 📂 GitHub Repository: https://github.com/Ziggyyyyyyyy/offline-upi-mesh-network-simulator

---

# ✨ Features

## 🌐 Mesh Network

- Multi-hop packet routing
- Gossip-based packet propagation
- Virtual device simulation
- Bridge node discovery
- Device failure simulation
- Multi-bridge support
- Packet route tracking

---

## 🔒 Security

- Hybrid AES + RSA Encryption
- SHA-256 Packet Hashing
- Replay Attack Protection
- Idempotent Packet Processing
- Secure Payment Instruction Transmission

---

## 💳 Payment Processing

- Offline Payment Instruction Generation
- Mesh Packet Creation
- Bridge Ingestion Pipeline
- Payment Settlement Engine
- Account Balance Updates
- Transaction Persistence

---

## 📊 Dashboard

- Packet Analytics
- Success Rate Monitoring
- Average Hop Count
- Device Status
- Route Visualization
- Transaction History
- Account Monitoring
- Real-time Charts using Chart.js

---

# 🏗 System Architecture

```text
Transfer Request
        │
        ▼
InstructionFactory
        │
        ▼
PaymentInstruction
        │
        ▼
Hybrid Encryption
(AES Payload + RSA Key)
        │
        ▼
EncryptedPayload
        │
        ▼
MeshPacket
        │
        ▼
Mesh Network
        │
        ▼
Bridge Device
        │
        ▼
BridgeIngestionService
        │
        ▼
Replay Protection
        │
        ▼
Idempotency Validation
        │
        ▼
SettlementService
        │
        ▼
H2 Database
        │
        ▼
Dashboard
```

---

# 🌐 Mesh Topology

```text
               Device-B 🌐
              /        \
             /          \
        Device-A      Device-D 🌐
             \          /
              \        /
               Device-C
```

**🌐 = Bridge Device (Internet Available)**

---

# 🔄 Project Workflow

```text
User
   │
   ▼
Transfer Request
   │
   ▼
Payment Instruction Created
   │
   ▼
AES Encryption
   │
   ▼
RSA Encrypts AES Key
   │
   ▼
Mesh Packet Created
   │
   ▼
Packet Broadcast
   │
   ▼
Nearby Devices
   │
   ▼
Bridge Device Found
   │
   ▼
Packet Validation
   │
   ├── Replay Protection
   │
   ├── Idempotency Check
   │
   ▼
Settlement
   │
   ▼
Database Updated
   │
   ▼
Dashboard Refreshed
```

---

# 🏛 Design Concepts

- Layered Spring Boot Architecture
- Factory Pattern
- Dependency Injection
- Store-and-Forward Routing
- Hybrid Cryptography
- Replay Protection
- Idempotent Processing
- Mesh Routing
- Real-time Monitoring Dashboard

---

# 🛠 Tech Stack

| Category | Technology |
|-----------|------------|
| Language | Java 21 |
| Framework | Spring Boot |
| Database | H2 Database |
| ORM | Spring Data JPA |
| Frontend | Thymeleaf |
| UI | Bootstrap 5 |
| Charts | Chart.js |
| Security | AES, RSA, SHA-256 |
| Build Tool | Maven |

---

# 📂 Project Structure

```text
src
└── main
    ├── java
    │   └── upimesh
    │       ├── controller
    │       ├── crypto
    │       ├── dto
    │       ├── mesh
    │       ├── model
    │       ├── repository
    │       ├── service
    │       └── util
    │
    └── resources
        ├── static
        │   └── css
        ├── templates
        │   └── dashboard.html
        └── application.properties
```

---

# 🔗 REST APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/hybrid/send` | Inject encrypted payment into mesh |
| POST | `/api/mesh/gossip` | Execute one gossip propagation round |
| GET | `/dashboard` | Open monitoring dashboard |
| GET | `/api/accounts` | View account balances |
| GET | `/api/transactions` | View transaction history |
| GET | `/api/analytics/mesh` | Mesh analytics |

---

# 📸 Dashboard

The dashboard provides a real-time overview of the simulator including:

- 📦 Total Packets Injected
- ✅ Delivered Packets
- ❌ Dropped Packets
- 📈 Success Rate
- 🚀 Average Hop Count
- 📡 Mesh Device Status
- 💳 Account Balances
- 📜 Transaction History
- 🛰 Packet Route Tracking
- 📊 Analytics Charts

Dashboard URL

```
http://localhost:8080/dashboard
```

---

# 🗄 Database Console

H2 Console

```
http://localhost:8080/h2-console
```

---

# ▶️ Running the Project

Clone the repository

```bash
git clone https://github.com/Ziggyyyyyyyy/offline-upi-mesh-network-simulator.git
```

Navigate to the backend

```bash
cd offline-upi-mesh-network-simulator/backend
```

Run the application

```bash
mvn spring-boot:run
```

Open Dashboard

```
http://localhost:8080/dashboard
```

---

# 📌 Current Scope

This project is a **backend simulation** of an offline UPI mesh payment architecture.

The current implementation focuses on validating the backend workflow rather than real device communication.

Current implementation includes:

- Virtual mesh devices
- In-memory packet propagation
- Simulated bridge discovery
- Hybrid encryption
- Replay protection
- Idempotent settlement
- Analytics dashboard
- Route visualization

The communication layer can later be replaced with **Bluetooth**, **Wi-Fi Direct**, or **Google Nearby Connections** without changing the core backend architecture.

---

# 🧪 What This Project Demonstrates

- Distributed Systems
- Mesh Networking
- Store-and-Forward Routing
- Secure Payment Processing
- Hybrid Cryptography
- Replay Protection
- Idempotent Transaction Handling
- Multi-Hop Communication
- Spring Boot Backend Development
- Dashboard Monitoring & Analytics

---

# 🚀 Future Enhancements

- Bluetooth / Wi-Fi Direct Communication
- Google Nearby Connections API
- Android Mobile Client
- Automatic Internet Detection
- Dynamic Bridge Election
- ACK-based Packet Cleanup
- Configurable Routing Algorithms
- PostgreSQL Integration
- Redis-based Duplicate Detection
- Kafka/RabbitMQ Event Processing
- JWT Authentication
- Docker Deployment
- WebSocket Live Dashboard
- Prometheus + Grafana Monitoring
- Unit & Integration Testing (JUnit + Mockito)

---

# 🎯 Learning Outcomes

While building this project, I explored:

- Spring Boot Architecture
- Spring Data JPA
- Hybrid Encryption (AES + RSA)
- Backend Security Concepts
- Distributed System Design
- Mesh Networking Fundamentals
- Transaction Management
- Dashboard Development using Thymeleaf
- Real-time Monitoring & Analytics
- Modular Backend Architecture

---

# 🤝 Contributions

Suggestions, improvements, and feedback are always welcome.

Feel free to open an Issue or submit a Pull Request.

---

# ⭐ If you found this project interesting, consider giving it a star!
