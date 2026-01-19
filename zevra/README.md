# Zevra Backend

[![CI](https://github.com/WernerWlff/Zevra_back/actions/workflows/ci.yml/badge.svg)](https://github.com/WernerWlff/Zevra_back/actions/workflows/ci.yml)

Backend API pour Zevra, une plateforme de fitness pour des exercices ciblés par groupes musculaires.

## 📋 Description

Application Spring Boot RESTful permettant la gestion d'exercices de fitness et de groupes musculaires.

## 🛠️ Technologies

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Security** - Authentification et autorisation
- **Spring Data JPA** - Accès aux données
- **PostgreSQL** - Base de données
- **Flyway** - Gestion des migrations de base de données
- **Maven** - Gestion des dépendances

## 📦 Prérequis

- Java 21 ou supérieur
- Maven 3.6+ (ou utiliser le Maven Wrapper inclus)
- PostgreSQL 16+

## 🚀 Installation

1. Clonez le dépôt :
```bash
git clone <url-du-repo>
cd Zevra_Back/zevra
```

2. Configurez les variables d'environnement :
Créez un fichier `.env` à la racine du projet `zevra` avec les variables suivantes :

```env
DB_URL=jdbc:postgresql://localhost:5432/zevra
DB_USERNAME=your_username
DB_PASSWORD=your_password
DB_MAX_POOL_SIZE=10
```

3. Créez la base de données PostgreSQL :
```sql
CREATE DATABASE zevra;
```

## ▶️ Lancement de l'application

### Avec Maven Wrapper (recommandé)
```bash
./mvnw spring-boot:run
```

### Avec Maven installé localement
```bash
mvn spring-boot:run
```

L'application sera accessible sur `http://localhost:8080`

## 📁 Structure du projet

```
zevra/
├── src/
│   ├── main/
│   │   ├── java/com/zevra/zevra/
│   │   │   ├── config/          # Configurations (CORS, Security, etc.)
│   │   │   └── ZevraApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/zevra/zevra/
│           └── ZevraApplicationTests.java
├── .github/workflows/
│   └── ci.yml                   # Configuration CI/CD
├── pom.xml
└── README.md
```

## ⚙️ Configuration

### Variables d'environnement

| Variable | Description | Exemple |
|----------|-------------|---------|
| `DB_URL` | URL de connexion PostgreSQL | `jdbc:postgresql://localhost:5432/zevra` |
| `DB_USERNAME` | Nom d'utilisateur PostgreSQL | `postgres` |
| `DB_PASSWORD` | Mot de passe PostgreSQL | `password` |
| `DB_MAX_POOL_SIZE` | Taille maximale du pool de connexions | `10` |

Le projet utilise le plugin envFile pour charger automatiquement les variables d'environnement depuis le fichier .env. Ce plugin permet une configuration globale des variables d'environnement pour le développement local.

### Configuration CORS

Le backend est configuré pour accepter les requêtes depuis `http://localhost:4200` (frontend Angular).

Les endpoints `/api/**` acceptent les méthodes : GET, POST, PUT, DELETE.

## 🔧 Build

### Compiler le projet
```bash
./mvnw clean compile
```

### Créer le package JAR
```bash
./mvnw clean package
```

Le fichier JAR sera généré dans `target/zevra-0.0.1-SNAPSHOT.jar`

### Exécuter le JAR
```bash
java -jar target/zevra-0.0.1-SNAPSHOT.jar
```

## 📝 Notes

- Le fichier `.env` est ignoré par Git pour des raisons de sécurité
- Les migrations Flyway sont désactivées en mode test (défini dans la CI)
- Le port par défaut est 8080 (configurable dans `application.properties`)

## 📄 Licence

Voir le fichier LICENSE pour plus d'informations.
