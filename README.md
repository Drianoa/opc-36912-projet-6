# Monde de Dév MDD

| Auteur | Version |    Commentaire    |
|:------:|:-------:|:-----------------:|
| MERLY  |  1.0.0  | Première version  |
| MERLY  |  1.0.1  | Gestion d'erreurs |

## Présentation

Ce projet est un MVP de monde de dev, prochain réseau social à destination des développeurs.

## Installation

### Prérequis

Avoir installé sur sa machine:

- Java 21
- Node 22
- Docker pour le lancement des conteneurs MYSQL

#### Java

- **Java JDK** : Version 21 [comme ici](https://adoptium.net/fr/temurin/releases?version=21&os=any&arch=any)

```shell
java -version
```

Exemple de résultat:

```
openjdk version "21.0.8" 2025-07-15 LTS
OpenJDK Runtime Environment Temurin-21.0.8+9 (build 21.0.8+9-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.8+9 (build 21.0.8+9-LTS, mixed mode, sharing)
```

#### Node

- **Node.js** :  en version 16.20 [Node.js](https://nodejs.org/fr/download)

```shell
node --version
```

Exemple de résultat:

```
v22.19.0
```

#### Docker

- **Docker Desktop** :  avec docker-desktop pour windows ou selon votre système
  d'exploitation [Docker](https://www.docker.com/products/docker-desktop/)

```shell
docker --version
```

Exemple de résultat:

```
Docker version 28.1.1, build 4eba377
```

## Lancement de l'application

#### Base de données

##### via Docker

Installez et configurez la base de données avec docker compose:

```shell
cd infrastructure
docker compose up -d
```

### Backend

Il faut dans un premier temps compiler le projet avec maven pour générer les sources mapstruct et les informations de
build.

```shell
mvnw clean compile -U
```

Puis il faut lancer le projet avec maven (ou via votre IDE préféré)

```shell
mvnw spring-boot:run
```

### Frontend

Il faut installer les dépendances du projet

```shell
cd front
npm install
```

puis lancer celui-ci

```shell
npm run start
```

## Documentation

Ce projet est développé avec Java pour le backend et utilise diverses technologies

- Spring Boot en version 3.5.6
- Spring Security : pour l'authentification et l'autorisation
- Spring Data JPA : pour l'accès aux données
- MapStruct : pour la conversion Dto <-> Entité
- Lombok : pour la réduction du code boilerplate
- OpenAPI : pour la documentation API
- Swagger : pour la visualisation de la documentation API

Pour le frontend il utilise :

- Angular 19.2
- tailwindcss 4.1

### Architecture du projet

L'application suit une architecture en couches avec une organisation par fonctionnalités (feature-based).

Voici la structure détaillée :

```
opc-36912-projet-3/
├── .mvn/                               # Configuration Maven Wrapper
├── infrastructure/                     # Fichiers d'infrastructure
│   ├── docker-compose.yaml             # Configuration Docker pour la base de données
│   └── script.sql                      # Script SQL d'initialisation de la base de données
├── src/
│   ├── main/
│   │   ├── java/                       # Code source Java
│   │   │   ├── configuration/          # Configuration globale de l'application
│   │   │   ├── security/               # Gestion de la sécurité
│   │   │   ├── features/               # Fonctionnalités métier
│   │   │   │   ├── fonctionnalité/     # Fonctionnalité métier : pour les élements qui la composent si un seul 
│   │   │   │   │   │                   # fichier est présent il sera à la racine, sinon il sera dans un dossier
│   │   │   │   │   ├── controller/     # Endpoints
│   │   │   │   │   ├── dto/            # Objets de transfert
│   │   │   │   │   ├── mappers/        # Conversion DTO <-> Entité
│   │   │   │   │   ├── services/       # Logique métier
│   │   │   │   │   ├── repositories/   # Accès aux données
│   │   │   │   │   └── model/          # Modèles JPA
│   │   │   └── dtos/                   # Objets de transfert globaux
│   │   └── resources/                  # Fichiers de configuration
│   └── test/                           # Tests unitaires et d'intégration
├── .gitignore                          # Fichiers ignorés par Git
├── mvnw                                # Maven Wrapper pour Unix
├── mvnw.cmd                            # Maven Wrapper pour Windows
├── pom.xml                             # Configuration Maven
└── README.md                           # Documentation du projet
```

#### Flux de Données

1) Requête HTTP → Controller → DTO → Mapper → Service → Repository → Base de données
2) Réponse : Base de données → Repository → Service → → Controller → Réponse HTTP

Note : Certains DTO sont mappé directement via spring-data

#### Configuration

Fichiers de Configuration Principaux

1) application.properties : Configuration de l'application Spring Boot
2) secrets-example.properties : Exemple de configuration pour les secrets
