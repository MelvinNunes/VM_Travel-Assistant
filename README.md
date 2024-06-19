# Coding Challenge: Travel Assistant Web-App

Welcome to the Travel Assistant repository. This repository is structured as a monorepo, meaning it contains multiple projects within a single repository. This approach allows us to manage and maintain all related projects in one place, ensuring consistency and simplifying dependency management.

- Objective

Develop a comprehensive travel assistant web application that allows users to search for a destination
city and obtain detailed information including population, GDP per capita, weather forecast, and
currency exchange rates.

- Frontend Requirements:
  • Design a user-friendly interface with a clean layout that adapts to various screen sizes
  (responsive design).
  • Implement a search functionality that communicates with the backend to retrieve data.
  • Display the results in an organized manner, with separate sections for population/GDP,
  weather, and exchange rates.

- Backend Requirements:
  • Create RESTful APIs that handle requests for city-specific data.
  • Integrate with external APIs from:
  o openweathermap.org for weather forecasts.
  o exchangeratesapi.io for currency exchange rates.
  o worldbank.org for population and GDP data.
  • Implement caching strategies to optimize response times and reduce API calls (nice to have).
  • Ensure data validation and error handling to manage unexpected inputs and API failures.
