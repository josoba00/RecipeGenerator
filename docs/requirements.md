# Statement of Work
### Project: RecipeGenerator
## 1. Project Overview
The goal of this project is to build a web application that allows users to collect, standardize, and maange cooking srecipes.
The application should support importing recipes from PDF files, extracting ingredients and instructions, and storing them in a structured format. 
Users can browse, filter, and get suggestions for recipes based on their preferences (e.g. vegetarian, main course)

## 2. Scope
In scope for the initial MVP:
- Backend REST API built with Java and Spring Boot.
- Basic web frontend for viewing and adding recipes.
- Storage of recipes in a database. (after in-memory version)
 
Out of scope for initial MVP (maybe added later):
- Import of recipes from PDF documents. (basic text extraction and parsing)
- Automatic recalculation of a recipe depending on number of portions
- User accounts, authentications, and authorization.
- Mobile app. (potential idea)
- Suggestions of a weekly meal plan.
- Format a recipe again as PDF to print it.
- User Managment with Accounts
- Share Recipes (state: public/private) to add and import recipes from others.

## 3. Stakeholders and Users
- User: A private person who wants to collect and manage personal recipes in a structured way.
User Goal:
- Store recipes in a constant format.
- Quickly find recipes matching current needs. (diet, course type, warm/cold)
- Save time by importing recipes from existing PDFs instead of re-typing everything.

## 4. Function Requirements
### 4.1. Recipe Management (CRUD API)

|Numbering| Description                                                           |
|--|----------------------------------------------------------------------|
| FR-1 | The system shall allow creating a new recipe via a REST API endpoint. |
| FR-2 | The system shall allow retrieving a list of all stored recipes |
| FR-3 | The system shall allow retrieving a single recip by its identifier |
| FR-4 | The system shall allow updating an existing recipe |
| FR-5 | The system shall allow deleting an existing recipe. |

Each recipe shall contain at least:
- Title
- Short description
- Number of portions
- Category (e.g. main dish, dessert, soup)
- Diet type (e.g. with meat, vegetarian, vegan, without glute)
- Temperature (e.g. cold, warm)
- Ingredient list (name, amount, unit)
- Instruction list (ordered steps as text)

### 4.2 Recipe Filtering and Suggestion

| Numbering | Description                                                                                                                            |
|-----------|---------------------------------------------------------------------------------------------------------------------------------------|
| FR-6      | The system shall allow filtering recipes by category. (e.g. main dish, dessert)                                                       |
| FR-7      | The system shall allow filtering recipes by diet type. (e.g. meat, vegetarian, vegan)                                                 |
| FR-8      | The system shall allow filtering recipes by temperature type. (warm, cold)                                                            |
| FR-9      | The system shall allow free-text search over recipe title and/or description.                                                         |
| FR-10      | The system shall provide an endpoint that return a "suggested" recipe based on optional filter parameters. (e.g. categroy + diet type |

### 4.3. PDF Import
| Numbering | Description                                                                                                                |
|-----------|---------------------------------------------------------------------------------------------------------------------------|
| FR-11     | The system shall provide an endpoint to upload a PDF file containing a recipe.                                            |
| FR-12     | The system shall extract text from the uploaded PDF.                                                                      |
| FR-13     | The system shall attempt to parse the extracted text into: title, ingredients, and instructions.                          |
| FR-14     | The system shall create a new recipe from the parsed data and store it in the same structure as manually created recipes. |
| FR-15     | The system shall return the created recipe (or id) as the response to the PDF import request                              |              

Note: If extraction or parsing fails, the system shall return an error and not create a partial recipe.

### 4.4 Frontend - Viewing Recipes
| Numbering | Description                                                                                                                  |
|-----|-----------------------------------------------------------------------------------------------------------------------------|
| FR-16 | The system shall provide a web UI that displays a list of recipes retrieved from the backend API                            |
| FR-17 | The system shall provide a detail view for a single recipe, showing all its fields, ingredients, and instructions.          |
| FR-18 | The web UI shall offer simple filters (category, diet type, temperature) and send these as query parameters to the backend. |

### 4.5 Frontend - Creating Recipes
| Numbering | Description                                                                                                            |
|-----------|-----------------------------------------------------------------------------------------------------------------------|
| FR-19     | The web UI shall allow users to create a new recipe through a form.                                                   |
| FR-20     | The form shall allow adding multiple ingredients (dynamic list).                                                      |
| FR-21     | The form shall allow adding multiple instructions steps in correct order.                                             |
| FR-22     | After a successful create operation, the user shall see the new recipe either in the list view or in the detail view. |

### 4.6 Error Handling and Validation
| Numbering | Description                                                                                                  |
|-----------|-------------------------------------------------------------------------------------------------------------|
| FR-23     | The system shall validate required fields for a recipe. (e.g. title and portions must be present and valid. |
| FR-24     | The API shall return meaningfull HTTP status codes. (e.g. 400 for validation errors, 404 for not found.)    |
| FR-25     | The frontend shall display basic error messages when API calls fail.                                        |

## 5. Non-Functional Requirements
### 5.1 Performance
| Numbering | Description                                                                                                                                                                               |
| --------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| NFR‑1     | For typical usage (single user, small dataset), API responses for simple queries (list recipes, get recipe) should be returned within 1 second on a local development machine. testlio+1​ |

### 5.2 Usability
| Numbering | Description                                                                                                                        |
| --------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| NFR‑2     | The UI shall be simple and easy to navigate with a clear separation between the recipe list and the recipe detail view. testlio+1​ |
| NFR‑3     | Basic usage (view list, open recipe, create new recipe) should be understandable without documentation. testlio+1​                 |

### 5.3 Reliabilty and Data Integrity
| Numbering | Description                                                                                                          |
| --------- | -------------------------------------------------------------------------------------------------------------------- |
| NFR‑4     | After database integration, data shall persist across application restarts. dataforest+1​                            |
| NFR‑5     | The system shall not create incomplete recipes when an error occurs during PDF parsing. d3jqtupnzefbtn.cloudfront+1​ |

### 5.4 Compatibility
| Numbering | Description                                                                                                      |
| --------- | ---------------------------------------------------------------------------------------------------------------- |
| NFR‑6     | The web application shall work on current versions of major browsers (Chrome, Firefox, Edge, Safari). testlio+1​ |

### 5.5 Maintainability
| Numbering | Description                                                                                                                              |
| --------- | ---------------------------------------------------------------------------------------------------------------------------------------- |
| NFR‑7     | The backend shall be structured into clear layers (controller, service, domain/model, persistence) to ease future changes. dataforest+1​ |
| NFR‑8     | Core logic (e.g., filtering, suggestion, PDF parsing) should have automated tests where feasible. perforce+2​                            |

## 6. Technical Constraints
| Numbering | Description                                                                                                                  |
| --------- | ---------------------------------------------------------------------------------------------------------------------------- |
| TC‑1      | The backend shall be implemented in Java using Spring Boot. perforce+1​                                                      |
| TC‑2      | The project shall use Gradle as the build system. perforce​                                                                  |
| TC‑3      | The project shall use Git and GitHub for version control, issues, and milestones. geeksforgeeks+1​                           |
| TC‑4      | The application shall initially use an in‑memory data store and later a relational database via Spring Data JPA. perforce+1​ |

## 7. Acceptance Criteria (MVP)
The MVP is considered complete when:
- A user can create a recipe via the UI and via the API.
- A user can view and filter recipes in the UI.
- All functionality works end-to-end on a local machine with clear instructions on how to run the backend and frontend.

Next MVP:
- A basic suggestion endpoint returns a recipe matching given preferences.
- A user can upload a PDF containing a recipe and get a new recipe created from it.

