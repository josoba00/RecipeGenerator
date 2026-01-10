# RecipeForge - Forge Every Recipe Into Clarity
<p align="center">
  <img src="docs/LogoDesign/LogoBroad/logo-transparent-broad.png"
       alt="drawing"
       height="300" />
</p>

## Recipe Import Web Application

A web application to collect, standardize, and manage cooking recipes. Users can create recipes manually or (later) import them from PDF files, then filter and get suggestions based on preferences like diet type and course type.

## Table of Contents

- [About the Project](#about-the-project)
- [Milestones](#milestones)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)

## About the Project

This project is a personal learning and productivity tool built with Java and Spring Boot. The goal is to provide a simple way to:

- Store recipes in a consistent, structured format
- Filter recipes by category (e.g., main dish, dessert), diet type (meat, vegetarian, vegan) and other properties
- Import recipes from PDF files found online instead of re‑typing them

The application is developed iteratively in milestones, ensuring that each step results in a runnable version (MVP‑driven development).

## Milestones

Current and planned features (per milestones):
<!--- empty box: &#x2610; checked box: &#x2612;--->

| Nr. | Milestone                  | Description                                                                                                                                        |  Status  |
|-----|----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|:--------:|
| 0   | Setup and Planning         | Set everything up: add README, create UML/ER diagram, plan the timeline.                                                                           | &#x2612; |
| 1   | CRUD‑API without database  | Backend CRUD‑API: Create (POST new object), Read (GET list or detail), Update (PUT/PATCH object), Delete (DELETE object).                          | &#x2610; |
| 2   | Filter of the recipes      | Filter by specific categories (type, meat, vegetarian, …); recommendation of a recipe based on filters.                                            | &#x2610; |
| 3   | Frontend – basic view      | Basic frontend: show list of recipes, recipe detail view, basic filter via query parameters.                                                       | &#x2610; |
| 4   | Frontend – add own recipes | Detailed frontend: add form to add recipes via frontend, save data, update recipe list, validate backend with error messages and mandatory fields. | &#x2610; |
| 5   | Database setup             | Correct database setup: correct saving of data, ACID concerns, adapt current create/read of CRUD.                                                  | &#x2610; |
| 6   | Import of PDF documents    | Import recipes via PDF reader, find a way to read recipes from PDF, ensure correct visual representation in the standardized format.               | &#x2610; |

## Tech Stack

- **Backend:** Java, Spring Boot, Spring Web
- **Build:** Gradle
- **Testing:** JUnit
- **Database:** In-memory (initial), later H2/PostgreSQL via Spring Data JPA
- **Frontend:** (planned) Simple SPA (e.g., React/Vue) consuming the REST API
- **Version Control:** Git + GitHub (issues, milestones, projects)

## Getting Started

### Prerequisites

- Java 21 (e.g., Amazon Corretto 21)
- Gradle

### Backend – Run locally

```bash
# Clone the repository
git clone https://github.com/josoba00/RecipeGenerator.git
cd RecipeGenerator

# Run backend
./gradlew bootRun
```

The backend will be available at:
- `http://localhost:8080/api/recipes/hello`

### Frontend – Run locally 

```bash
cd frontend
npm install
npm run dev
```

The frontend will then be available at like `http://localhost:5173` (Vite default) and will talk to the backend at `http://localhost:8080/api`.[6][7][5]

## Usage
- **Create a recipe**

  `POST /api/recipes` with JSON body:

  ```json
  {
    "title": "Spaghetti Bolognese",
    "description": "Classic Bolognese.",
    "portions": 4,
    "category": "MAIN",
    "dietType": "MEAT",
    "ingredients": [
      { "name": "Spaghetti", "amount": 400, "unit": "g" }
    ],
    "instructions": [
      { "stepNumber": 1, "text": "Boil water ..." }
    ]
  }
  ```

- **List recipes**

  `GET /api/recipes`

- **Filter recipes**

  `GET /api/recipes?category=MAIN&dietType=VEGAN`

- **Get suggestion**

  `GET /api/recipes/suggestion?dietType=VEGETARIAN`

## Contributing

This is currently a personal learning project, but contributions and feedback are welcome via GitHub Issues and Pull Requests.

Basic flow:
1. Create an issue or pick an existing one.
2. Create a branch from `dev` (e.g., `feature/issue-number-description`).
3. Implement changes, add tests if possible.
4. Open a Pull Request into `dev` and link the issue.
