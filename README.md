# Quiz2 (News App)

A simple Android News Application built using **Kotlin**, **Retrofit**, **Glide**, **Room**, and **ViewModel + LiveData**.  
This application demonstrates core Android development concepts, including API integration, local data persistence, and modern architectural patterns.

**Status:** Prototype

---

## Features

- Fetches latest news headlines using the **GNews API**
- Allows users to select different countries to view region-specific news
- Provides search and filtering functionality for articles
- Displays full article details on a separate screen
- Supports basic offline caching using **Room Database**

---

## Screenshots

### Main Screen
<img width="246" height="532" alt="Main Screen" src="https://github.com/user-attachments/assets/491eba9a-7783-49cd-a8a8-c838c63f872a" />

### Main Screen (Country Selection)
<img width="235" height="533" alt="Country Selection" src="https://github.com/user-attachments/assets/e26f7abe-f5d4-4317-8bc5-5c05ad869139" />

### Full Article Screen
<img width="240" height="512" alt="Full Article Screen" src="https://github.com/user-attachments/assets/336a4ed3-7168-485c-92b3-b402e4bf187e" />

---

## Technology Stack

- **Programming Language:** :contentReference[oaicite:0]{index=0}
- **Architecture Pattern:** :contentReference[oaicite:1]{index=1}
- **Networking Library:** :contentReference[oaicite:2]{index=2}
- **Image Loading Library:** :contentReference[oaicite:3]{index=3}
- **Local Database:** :contentReference[oaicite:4]{index=4}
- **Lifecycle Management:** :contentReference[oaicite:5]{index=5}

---

## Build and Run Instructions

### Windows
```powershell
./gradlew.bat assembleDebug
./gradlew.bat installDebug
