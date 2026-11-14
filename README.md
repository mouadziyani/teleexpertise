# Télé-Expertise Médicale — API REST (JAX-RS)

![Java 17](https://img.shields.io/badge/Java%2017-ED8B00?style=flat-square&logo=openjdk&logoColor=white) ![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white) ![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-F80000?style=flat-square) ![Jersey](https://img.shields.io/badge/Jersey-4E4E4E?style=flat-square) ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=flat-square&logo=hibernate&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white)

API REST de **télé-expertise médicale** : un **médecin généraliste** peut envoyer une **demande d'expertise** à un **spécialiste**, qui y répond à distance. Projet réalisé en **Java / Jakarta EE** avec **JAX-RS (Jersey)** et **Hibernate**.

## ✨ Fonctionnalités

- 🔐 **Basic Auth** via un filtre (`BasicAuthFilter`) et contrôle des rôles avec `@RolesAllowed`.
- 👨‍⚕️ Liste des **spécialistes** (par spécialité) pour les généralistes.
- 📨 **Demandes d'expertise** : création par le généraliste (avec priorité), consultation et **réponse** par le spécialiste.
- 🔄 Suivi des statuts (`StatutDemande`, `StatutConsultation`).
- 🧮 Utilisation de la **Stream API** pour filtrer et trier les données.
- 🌱 Seeder d'utilisateurs avec mots de passe hachés (**jBCrypt**).

## 🔗 Endpoints

| Méthode | Route | Rôle |
| --- | --- | --- |
| GET | `/specialistes` | GENERALIST |
| POST | `/demande` | GENERALIST |
| GET | `/demande` | GENERALIST / SPECIALISTE |
| PUT | `/demande/{id}/reponse` | SPECIALISTE |

## 🗄️ Entités

`User` · `Patient` · `Consultation` · `Specialiste` · `DemandeExpertise` — enums `Specialite`, `Priorite`, `UserRole`…

## ▶️ Lancer le projet

1. Créer une base MySQL et adapter `src/main/resources/META-INF/persistence.xml`.
2. Construire et déployer :

```bash
mvn clean package
# déployer le .war sur Tomcat 10+ (ou un serveur Jakarta EE)
```

---

## 👤 Auteur

**Mouad Ziyani** — Développeur Full-Stack (PHP/Laravel · Java/Spring · React)

[![Portfolio](https://img.shields.io/badge/Portfolio-mouadziyani.com-d92632?style=flat-square)](https://mouadziyani.com)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Mouad%20Ziyani-0A66C2?style=flat-square&logo=linkedin)](https://www.linkedin.com/in/mouad-ziyani-956484339)
[![GitHub](https://img.shields.io/badge/GitHub-mouadziyani-181717?style=flat-square&logo=github)](https://github.com/mouadziyani)

## 📄 Licence

© 2025-2026 Mouad Ziyani — **Tous droits réservés.**

Ce projet est publié uniquement à titre de démonstration (portfolio). Il est **interdit** de copier, cloner, modifier, redistribuer ou réutiliser ce code, en tout ou en partie, sans l'autorisation écrite préalable de l'auteur. Voir le fichier [LICENSE](LICENSE).
