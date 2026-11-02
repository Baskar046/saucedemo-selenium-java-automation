# SauceDemo Test Automation Framework

An automated test suite built using **Java** and **Selenium WebDriver** to validate core functionalities of the [SauceDemo](https://www.saucedemo.com/) e-commerce application.

---

##  Tech Stack & Tools
* **Language:** Java
* **Automation Tool:** Selenium WebDriver
* **Browser:** Google Chrome (with custom ChromeOptions preferences)
* **Build Tool / IDE:** IntelliJ IDEA 

---

##  Project Structure
```text
SauceDemo-Automation/
│
├── src/
│   └── Test/
│       ├── AddToCard/
│       │   └── TC007_CheckAddToCart.java
│       └── Sidebar_Navigation/
│           └── TC005_CheckNavigationSearchBar.java
│              
└── README.md

Key Features Handled:

Browser Profile Configuration: Automatically disables Chrome's native password manager pop-ups, credential saving alerts, and leak detection notifications to prevent test blockages.

Sidebar Navigation Testing: Validates sidebar toggle behavior, sub-menu redirection, and parent page routing.

Cart Interactions: Automates adding items to the cart, removing items dynamically, and verifying element states.