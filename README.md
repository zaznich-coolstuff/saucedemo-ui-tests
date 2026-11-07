# SauceDemo UI Tests

A small collection of automated tests I put together for the MyNewTerm Software Tester technical assessment.

The tests cover three main parts of the SauceDemo shopping journey: logging in, selecting a product, and completing an order.

## What I've used

- Java 21
- Selenium WebDriver
- TestNG
- Maven
- Google Chrome

## The tests

**UI-01 — Login**

Logs in with a valid user and checks that the Products page loads correctly, including the URL, heading and product list.

**UI-02 — Selecting a product**

Opens a product from the listing and checks that its name, description and price match. It then adds the item to the cart and verifies the cart badge, product details and quantity.

**UI-03 — Checkout**

Goes through the full checkout process with two products and fictional customer details. Checks the cart contents, order subtotal, tax, total, payment and shipping information, and finally the order confirmation.

## How the project is organised

I've used Page Object Model to keep the Selenium interactions separate from the test assertions.

- `pages/` — Locators and methods for interacting with SauceDemo.
- `tests/` — The three TestNG tests.
- `base/` — Browser setup and cleanup shared across the tests.
- `listeners/` — Takes a screenshot if a test fails.

The tests are written with a Given / When / Then structure to match the test cases I've documented separately.

Each test opens its own Chrome session, so it can run independently. I've used explicit waits rather than fixed delays.

## What you need to run it

You'll need Java JDK 21, Maven, Google Chrome and an internet connection.

Selenium Manager takes care of setting up ChromeDriver, so there's no need to download it separately.

## Running the tests

Open a terminal in the project folder and run:

```bash
mvn clean test
```

You can also run each test separately:

```bash
mvn -Dtest=LoginTest test
mvn -Dtest=ProductTest test
mvn -Dtest=CheckoutTest test
```

## Results and screenshots

TestNG results are saved in:

`target/surefire-reports/`

If a test fails, a screenshot is automatically saved in:

`target/screenshots/`

The `target/` folder is ignored by Git, so these files are generated locally when the tests run.