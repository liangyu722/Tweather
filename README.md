# Earthquakes

### Introduction
An Android app to display the weather:
● Using https://twitter-code-challenge.s3.amazonaws.com/current.json to get current weather 
Display the following
○ Temperature in Celsius and Fahrenheit
○ Wind speed.
○ A cloud icon if the cloudiness percentage is greater than 50%.

● Provide a button, that when tapped, fetches the weather for the next 5 days, and displays
the standard deviation of the temperature
https://twitter-code-challenge.s3.amazonaws.com/future_1.json
Display the following
○ standard deviation for temperature in Celsius and Fahrenheit

● Provide unit test for the calculation of standard deviation

### Architecture
DataBinding / XML <br />
Activity/Fragment <br />
ViewModel <br />
UseCase <br />
Repository <br />
DataSource <br />
Service <br />

### Technologies
Dagger2<br />
Retrofit<br />
Coroutines<br />
Mockk<br />
Kluent<br />
ConstraintLayout <br />

### Out of Scope
Unit test for Repository
Unit test for UseCase (Other than calculation of standard deviation)
Unit test for ViewModel
UI test for View
Database persistence

### Improvement 
Unit test for Repository
Unit test for UseCase (other than getFutureWeatherStandardDeviation which is covered due to requirement on unit testing of standard deviation)
Unit test for ViewModel
UI test for View
Reactive flow using Coroutines
