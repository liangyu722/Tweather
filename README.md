# Earthquakes

### Introduction
An Android app to display the weather:
<br />
● Using https://twitter-code-challenge.s3.amazonaws.com/current.json to get current weather <br />
Display the following<br />
○ Temperature in Celsius and Fahrenheit<br />
○ Wind speed.<br />
○ A cloud icon if the cloudiness percentage is greater than 50%.<br />

● Provide a button, that when tapped, fetches the weather for the next 5 days, and displays
the standard deviation of the temperature https://twitter-code-challenge.s3.amazonaws.com/future_1.json<br />
Display the following<br />

● Provide unit test for the calculation of standard deviation<br />

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
Unit test for Repository<br />
Unit test for UseCase (Other than calculation of standard deviation)<br />
Unit test for ViewModel<br />
UI test for View<br />
Database persistence<br />

### Improvement 
Unit test for Repository<br />
Unit test for UseCase (other than getFutureWeatherStandardDeviation which is covered due to requirement on unit testing of standard deviation)<br />
Unit test for ViewModel<br />
UI test for View<br />
Reactive flow using Coroutines<br />
