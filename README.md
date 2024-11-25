# RunnerUP
Computer Science Advanced Programming Capstone Project
## Table of Contents
 * [Summary](
## Summary
## Technologies
* **Intellij IDEA** primary IDE
* **Java** JDK version 22
* **Maven** dependency management tool
* **JavaFX** Controls, Graphics, FXML
* **Azure MySQL Database** MySQL Database powered by Microsoft Azure

## Setup
### Prerequisites:

1. **Java Development Kit (JDK)**
    * Version: 22
    * [Download Link](https://www.oracle.com/java/technologies/javase/jdk22-archive-downloads.html)
2. **Apache Maven**
    * [Installation Guide](https://maven.apache.org/install.html)
3. **Microsoft Azure**
    * Setup an Azure Database for MySQL flexible server
    * [Setup Guide](https://azure.microsoft.com/en-us/products/azure-sql/database/?ef_id=_k_CjwKCAiA3ZC6BhBaEiwAeqfvyvBEYfdidY8eK6Cr7DplhG778BkQ3fTP16mBgVt8aVEbjmNoibR0ExoCd2cQAvD_BwE_k_&OCID=AIDcmm5edswduu_SEM__k_CjwKCAiA3ZC6BhBaEiwAeqfvyvBEYfdidY8eK6Cr7DplhG778BkQ3fTP16mBgVt8aVEbjmNoibR0ExoCd2cQAvD_BwE_k_&gad_source=1&gclid=CjwKCAiA3ZC6BhBaEiwAeqfvyvBEYfdidY8eK6Cr7DplhG778BkQ3fTP16mBgVt8aVEbjmNoibR0ExoCd2cQAvD_BwE)
### Instructions

1. **Clone the Repository**
2. **Azure Database Configuration**
   * This project uses the DBConnectivity.java class to initialize the Database connection using the Database's URL and Name. Additionally, it will require the SQL Server URL, the user's username, and the user's password. The above should be placed in the corresponding String variables.
3. **Build the Project**
4. **Run the Project**
# Features
* **Login/Register** Authenticates user or allows them to create an account to start tracking their running data.
* **Home Page** Accessible directly post-login. Contains sidebar allowing for access to BMI Calculator, Progress, Analysis, and Setting page.
* **BMI Calculator Page** Allows a user to calculate their BMI, and additionally creates a chart tracking their BMI over time for visual aid regarding their running journey
* **Progress Tracker Page** Allows user to input their running data and charts the running data. Additionally creates a table that tracks the distance ran, the time the user ran, their pace, and heart rate.
* **Analysis Page** Uses the running data inputted by the user and analyzes it, giving the user recommendations on how they can better improve their health, as well as what types of runs they should focus on.
* **Settings Page** Allows user to configure different settings.


