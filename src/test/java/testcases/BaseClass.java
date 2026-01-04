package testcases;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import endpoints.EndPoints;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.LogInitializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;


public class BaseClass {

    ConfigReader configReader;
    //For logging
    RequestLoggingFilter requestLoggingFilter;
    ResponseLoggingFilter responseLoggingFilter;
    @BeforeSuite
    public void setupLogging() {
        LogInitializer.initializeLogger();
    }
    @BeforeClass
    public void setup() throws FileNotFoundException {
        RestAssured.baseURI = EndPoints.BASE_URL;

        configReader = new ConfigReader();
        // Setup filters for logging
        FileOutputStream fos = new FileOutputStream(".\\logs\\test_logging.log");
        PrintStream log = new PrintStream(fos, true);

        requestLoggingFilter = new RequestLoggingFilter(log);
        responseLoggingFilter = new ResponseLoggingFilter(log);

        RestAssured.filters(requestLoggingFilter, responseLoggingFilter);


    }

    public Boolean isSortedDescending(List<Integer> list) {

        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(0) < list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    public Boolean isSortedAscending(List<Integer> list) {

        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(0) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
/*package testcases;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.LogInitializer;
import endpoints.EndPoints;

public class BaseClass {

    // Thread-safe RequestSpecification
    private static ThreadLocal<RequestSpecification> requestSpec =
            ThreadLocal.withInitial(() ->
                    new RequestSpecBuilder()
                            .setBaseUri(EndPoints.BASE_URL)
                            .setContentType("application/json")
                            .build()
            );

    @BeforeSuite
    public void setupLogging() {
        LogInitializer.initializeLogger(); // Log4j only
    }

    @BeforeMethod
    public void setupRequest() {
        // Ensures spec is initialized per thread
        requestSpec.get();
    }

    @AfterMethod
    public void clearThread() {
        requestSpec.remove(); // VERY IMPORTANT
    }

    protected RequestSpecification request() {
        return requestSpec.get();
    }
}Use this for parallel="tests" in testng.xml

request()
    .body(user)
.when()
    .post("/users")
.then()
    .statusCode(201);
1️⃣ Where does request() come from?

👉 request() is NOT from Rest Assured
👉 request() is a method WE created in BaseClass

From the BaseClass I gave you:

protected RequestSpecification request() {
    return requestSpec.get();
}


📌 This method simply returns the ThreadLocal RequestSpecification.

2️⃣ Why did we create request()?

Because:

We cannot use RestAssured.given() in parallel safely

We cannot use global RestAssured.baseURI

Each thread must have its own request configuration

So we wrap it inside a method called request().

3️⃣ How does the test case get access to request()?
Because:

Your test class extends BaseClass

public class UserTest extends BaseClass {


So request() is inherited.

4️⃣ How request() is used in test case
In Test Class:
request()
    .body(user)
.when()
    .post("/users")
.then()
    .statusCode(201);

What actually happens internally:
request()
 ↓
requestSpec.get()
 ↓
ThreadLocal RequestSpecification
 ↓
Each thread gets its own copy

5️⃣ Why not directly use requestSpec.get() in test?

Because:

requestSpec is private

We want clean & readable tests

We hide implementation details

📌 This is encapsulation (OOP principle).

6️⃣ Visual Picture (Easy to Remember)
BaseClass
 ├── ThreadLocal<RequestSpecification>
 ├── request()   ← helper method
 |
TestClass
 └── request() → used here

7️⃣ Simple analogy (REAL LIFE)

Think of it like this:

requestSpec = Locker

request() = Key

Each thread has its own locker

Test case uses the key, not the locker directly

8️⃣ Interview-ready explanation ⭐

“We created a request() helper method in BaseClass that returns a ThreadLocal RequestSpecification. Test classes extend BaseClass and use request() to ensure thread-safe API calls during parallel execution.”

🏁 FINAL TAKEAWAY

✔ request() is your own method
✔ It returns thread-safe RequestSpecification
✔ Test cases can call it because they extend BaseClass
✔ This is the correct design for parallel Rest Assured tests


*/
}

