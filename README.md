# 🚀 Offline UPI Mesh Network Simulator

An **Offline UPI Payment Simulator** built using **Spring Boot** that demonstrates how digital payments can be securely routed through a **Mesh Network** when the sender does not have internet connectivity.

Instead of sending a payment request directly to a server, the system forwards encrypted payment packets across nearby virtual devices until they reach a **Bridge Device** with internet access. The bridge then validates, decrypts, and settles the transaction while maintaining security through **Hybrid Encryption**, **Replay Protection**, and **Idempotent Processing**.

This project is designed as a learning implementation of concepts used in **distributed systems, secure payment infrastructure, and mesh networking**.

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

## 🔒 Security
- Hybrid AES + RSA Encryption
- SHA-256 packet hashing
- Replay attack protection
- Idempotent packet processing
- Secure payment instruction transmission

## 💳 Payment Processing
- Offline payment instruction generation
- Mesh packet creation
- Bridge ingestion pipeline
- Payment settlement engine
- Account balance updates
- Transaction persistence

## 📊 Dashboard
- Packet analytics
- Success rate monitoring
- Average hop count
- Device status
- Route visualization
- Transaction history
- Account monitoring
- Real-time charts using Chart.js

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

# 📸 Dashboard

The dashboard provides a real-time overview of the system including:

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
- PostgreSQL Integration
- Redis-based Duplicate Detection
- Dynamic Routing Algorithms
- WebSocket Live Dashboard
- Mobile Client Application
- NFC-based Offline Payments
- Real Device Discovery

---

# 🎯 Learning Outcomes

While building this project, I explored:

- Spring Boot Architecture
- Spring Data JPA
- Hybrid Encryption (AES + RSA)
- Backend Security Concepts
- Mesh Networking Fundamentals
- Distributed System Design
- Transaction Management
- Dashboard Development with Thymeleaf
- Real-time Monitoring and Analytics

---

If you have suggestions or feedback, feel free to open an issue or submit a pull request.
