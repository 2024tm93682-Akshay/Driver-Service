# Driver-Service
Driver Service for Ride-Hailing / Fleet Dispatch


---

# 🚗 Driver Microservice – Kubernetes Deployment Documentation

### Version 3.0 — Kubernetes Deployment (Minikube)

---

## 📘 Overview

The **Driver Microservice** is a Spring Boot–based backend component of the scalable ride-hailing platform.
It manages driver information, availability, and registration. The service is fully **containerized using Docker** and deployed on **Kubernetes (Minikube)** with a **PostgreSQL** database backend.

It supports:

* REST APIs to manage drivers (CRUD operations)
* Integration with PostgreSQL
* Health and readiness probes for Kubernetes
* Observability (via Spring Boot Actuator)

---

## ⚙️ Tech Stack

| Component               | Technology                  |
| ----------------------- | --------------------------- |
| **Language**            | Java 17                     |
| **Framework**           | Spring Boot 3.x             |
| **Database**            | PostgreSQL 17               |
| **Build Tool**          | Maven                       |
| **ORM / JPA Provider**  | Hibernate + Spring Data JPA |
| **Containerization**    | Docker & Docker Compose     |
| **Deployment Platform** | Kubernetes (Minikube)       |
| **Monitoring**          | Spring Boot Actuator        |

---

## 🧩 API Endpoints

| Method   | Endpoint            | Description           |
| -------- | ------------------- | --------------------- |
| `GET`    | `/api/drivers`      | Get all drivers       |
| `GET`    | `/api/drivers/{id}` | Get driver by ID      |
| `POST`   | `/api/drivers`      | Register new driver   |
| `PUT`    | `/api/drivers/{id}` | Update driver details |
| `DELETE` | `/api/drivers/{id}` | Delete driver         |
| `GET`    | `/actuator/health`  | Health check endpoint |

---

## 🐳 How to Run with Docker Compose (Local Environment)

### 1️⃣ Clone the repository

```bash
git clone <your-repo-url>
cd DriverServiceApplication
```

### 2️⃣ Build and start services

Ensure Docker Desktop is running, then:

```bash
docker-compose up --build
```

### 3️⃣ Wait until logs show

```
Tomcat started on port(s): 8082
```

### 4️⃣ Access the service:

| Resource | URL                                     |
| -------- | --------------------------------------- |
| API      | `http://localhost:8082/api/drivers`     |
| Health   | `http://localhost:8082/actuator/health` |

### 5️⃣ Stop containers

```bash
docker-compose down
```

---

## ☸️ Deployment on Kubernetes (Minikube)

This section explains how to deploy the **Driver Service** and **PostgreSQL database** using Kubernetes manifests.

### Prerequisites

* **Minikube** installed (`minikube start`)
* **kubectl** configured
* **Docker Desktop** running

---

### 1️⃣ Start Minikube

```bash
minikube start --driver=docker
```

### 2️⃣ Set Docker environment to Minikube

In your project directory PowerShell or terminal:

```bash
minikube docker-env
```

Then apply:

```bash
& minikube -p minikube docker-env | Invoke-Expression   # PowerShell
```

or (Linux/Mac):

```bash
eval $(minikube docker-env)
```

---

### 3️⃣ Build the Docker image inside Minikube

```bash
docker build -t driver-service:latest .
```

---

### 4️⃣ Apply Kubernetes manifests

In the project folder (where `/k8s` files are stored):

```bash
kubectl apply -f postgres-configmap.yaml
kubectl apply -f postgres-secret.yaml
kubectl apply -f postgres-pvc.yaml
kubectl apply -f postgres-deplyment.yaml
kubectl apply -f driver-configmap.yaml
kubectl apply -f driver-deployment.yaml
kubectl apply -f driver-service.yaml
```

---

### 5️⃣ Verify deployment

```bash
kubectl get pods
kubectl get svc
```

Expected output:

```
NAME                              READY   STATUS    RESTARTS   AGE
postgres-764bd8f847-nh8rp         1/1     Running   0          2m
driver-service-5d6bc9c85d-2z78z   1/1     Running   0          2m
```

---

### 6️⃣ Access the service

```bash
minikube service driver-service --url
```

Output example:

```
http://127.0.0.1:65471
```

Now test the endpoints:

```bash
curl http://127.0.0.1:65471/actuator/health
```

→ Expected: `{"status":"UP"}`

or open in browser:

```
http://127.0.0.1:65471/api/drivers
```

---

### 7️⃣ View Logs

```bash
kubectl logs -f deployment/driver-service
```

---

### 8️⃣ Cleanup (Optional)

To delete everything and restart fresh:

```bash
kubectl delete -f .
minikube stop
minikube delete --all
eval $(minikube docker-env -u)
```

---

## 📁 Folder Structure

```
DriverServiceApplication/
│
├── src/
│   ├── main/java/com/driver/...     # Source code (Controller, Service, Repo)
│   ├── main/resources/
│   │   ├── application.properties
│   │   ├── application-docker.properties
│   │   ├── data.sql                # Seed data
│
├── k8s/
│   ├── driver-configmap.yaml
│   ├── driver-deployment.yaml
│   ├── driver-service.yaml
│   ├── postgres-configmap.yaml
│   ├── postgres-deplyment.yaml
│   ├── postgres-pvc.yaml
│   ├── postgres-secret.yaml
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── README.md
└── screenshots/
    ├── docker_ps_verification.png
    ├── kubectl_get_pods.png
    ├── kubectl_get_svc.png
    ├── health_endpoint.png
```

---

## 📊 Monitoring & Observability

* **Health Endpoint:** `/actuator/health`
* **Metrics Endpoint (if enabled):** `/actuator/metrics`
* Logs automatically printed to container console (`kubectl logs`)
* Use `kubectl logs driver-service-pod-name` to monitor real-time activity.

---

## 🧹 Version History

| Version | Description                                    |
| ------- | ---------------------------------------------- |
| v1.0    | Initial Spring Boot driver service             |
| v2.0    | Dockerized version (local containerized setup) |
| v3.0    | Kubernetes deployment using Minikube           |

---

## ✅ Verification Checklist

| Task                       | Status |
| -------------------------- | ------ |
| Dockerfile created         | ✅      |
| Docker Compose verified    | ✅      |
| PostgreSQL connected       | ✅      |
| Minikube cluster running   | ✅      |
| Pods deployed successfully | ✅      |
| Health endpoint returns UP | ✅      |

---
