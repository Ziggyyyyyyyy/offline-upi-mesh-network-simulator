# 🚀 Offline UPI Mesh Network Simulator

A Spring Boot based simulation of an **Offline UPI Payment System** that enables payment requests to travel across nearby devices using a **Mesh Network** when internet connectivity is unavailable.

The project demonstrates how encrypted payment instructions can be propagated through a decentralized mesh, discovered by bridge devices with internet access, and securely settled on the backend. The system includes hybrid encryption, replay protection, idempotency guarantees, analytics, route tracking, and a real-time monitoring dashboard. Inspired by real-world offline payment and mesh networking concepts. :contentReference[oaicite:0]{index=0}

---

# 📌 Features

### Mesh Network Simulation
- Virtual devices
- Multi-hop packet routing
- Gossip-based packet propagation
- Bridge node discovery
- Device failure simulation
- Multi-bridge support

### Security
- AES encryption for payment payloads
- RSA encryption for AES key exchange
- Hybrid cryptography
- Replay attack protection
- Packet integrity validation
- Idempotent settlement

### Payment Processing
- Offline payment instruction generation
- Secure packet transmission
- Bridge ingestion pipeline
- Transaction settlement engine
- Account balance management

### Monitoring Dashboard
- Real-time analytics
- Packet delivery statistics
- Success rate tracking
- Average hop count
- Device monitoring
- Packet route visualization
- Transaction history

---

# 🏗 Architecture

```text
TransferRequest
        ↓
InstructionFactory
        ↓
PaymentInstruction
        ↓
AES Encrypt Payload
        ↓
RSA Encrypt AES Key
        ↓
EncryptedPayload
        ↓
MeshPacket
        ↓
Mesh Network
        ↓
Bridge Device
        ↓
BridgeIngestionService
        ↓
Decrypt + Validate
        ↓
SettlementService
        ↓
Transaction Database
        ↓
Dashboard
```

---

# 🌐 Mesh Topology

```text
Device-A -------- Device-B
    |                 |
    |                 |
    |                 |
Device-C -------- Device-D

Bridge Devices:
🌐 Device-B
🌐 Device-D
```

---

# 🔒 Security Flow

```text
Payment Instruction
        ↓
AES Encryption
        ↓
AES Key Generated
        ↓
RSA Encrypt AES Key
        ↓
Hybrid Encrypted Packet
        ↓
Mesh Transmission
        ↓
Bridge Decryption
        ↓
Settlement
```

---

# 📊 Dashboard Features

The dashboard provides:

- Total Packets Injected
- Delivered Packets
- Dropped Packets
- Success Rate
- Average Hop Count
- Device Status Monitoring
- Account Balances
- Transaction History
- Route Tracking
- Analytics Charts

Dashboard URL:

```text
http://localhost:8080/dashboard
```

---

# 🛠 Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA

### Database
- H2 Database

### Frontend
- Thymeleaf
- Bootstrap 5
- Chart.js

### Security
- AES
- RSA
- SHA-256

### Build Tool
- Maven

---

# 📂 Project Structure

```text
src/main/java/upimesh

├── controller
├── crypto
├── dto
├── mesh
├── model
├── repository
├── service
├── util

src/main/resources

├── static
│   └── css
├── templates
│   └── dashboard.html
└── application.properties
```

---

# ▶️ Running Locally

Clone repository:

```bash
git clone https://github.com/Ziggyyyyyyyy/offline-upi-mesh-network-simulator.git
```

Navigate to backend:

```bash
cd offline-upi-mesh-network-simulator/backend
```

Run application:

```bash
mvn spring-boot:run
```

Open dashboard:

```text
http://localhost:8080/dashboard
```

---

# 🧪 Demonstrated Concepts

- Distributed Systems
- Mesh Networking
- Store-and-Forward Routing
- Hybrid Cryptography
- Secure Payments
- Replay Protection
- Idempotency
- Multi-Hop Communication
- Fault Tolerance
- Network Analytics

---

# 🚧 Future Improvements

- Real Bluetooth Communication
- PostgreSQL Integration
- Redis-based Deduplication
- WebSocket Live Updates
- Mobile Application
- NFC Support
- Dynamic Routing Algorithms
- Real Device Discovery

---

# 👨‍💻 Author

Developed as a learning project to explore:

- Distributed Systems
- Spring Boot
- Cryptography
- Mesh Networking
- Offline Payment Infrastructure

---

# ⭐ If you found this project useful

Give the repository a star and feel free to contribute.
