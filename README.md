# API Automation & Security Testing - Bosta APIs

## Overview
This project automates security testing and functional validation of key Bosta APIs using Java and Rest Assured. It leverages OpenAI to enhance penetration testing capabilities, simulating attack vectors and identifying potential vulnerabilities.

The APIs covered include:

- **Create Pickup**: Request a pickup for order collection.
- **Update Bank Info**: Update business bank account details.
- **Forget Password**: Request a password reset link.

Continuous Integration and Delivery (CI/CD) pipelines are included to automate testing workflows.

---

## APIs Details

### 1. Create Pickup API
- **Endpoint:** `POST https://stg-app.bosta.co/api/v2/pickups`
- **Description:** Businesses create pickup requests; delivery agents are assigned, and fees deducted automatically.
- **Sample Request Payload:**
  ```json
  {
    "businessLocationId": "Q1MCImvcs",
    "contactPerson": {
      "_id": "pvE7i1MA_8",
      "name": "Elkholaey-",
      "phone": "+201202436309"
    },
    "scheduledDate": "2025-04-15",
    "numberOfParcels": "3",
    "hasBigItems": false,
    "repeatedData": {
      "repeatedType": "One Time"
    },
    "creationSrc": "Web"
  }