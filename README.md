# HackYeah 2025 - Trovi

## Prerequisites

- Node.js v20
- Docker & Docker Compose
- Android Studio

## Setup

### Clone the repository

```bash
git clone <repository-url>
cd <repository-directory>
```

### 🐳 Run database server

This project includes a `docker-compose.yml` file that sets up:

- **PostgreSQL** database (`postgres`)
- **pgAdmin 4** web UI (`pgadmin`) at port `5050`

To start the PostgreSQL and pgAdmin containers:

```bash
docker-compose up -d
```

### Install dependencies

```bash
cd backend
npm install
```

### Run Prisma migrations & run backend server

```bash
npx prisma db push
npm run dev
```

## Run the Android App

1. Open the **`mobile/`** directory in **Android Studio**.
2. Wait for Gradle to finish syncing (Android Studio will automatically install the correct JDK and SDK versions if needed).
3. Run the app:
    - **On emulator** — works out of the box.
    - **On a physical device** — update the local IP address in  
      `API_BASE_URL` inside `KtorApi.kt`
      so it points to your backend machine (e.g. `http://192.168.x.x:3000/api`).
4. Select **Run ▶️** from the toolbar or use `Shift + F10`.