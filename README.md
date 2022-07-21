# OpenWeatherMapConsumerApp

It is a multimodule Maven project implemented completely in JAVA under version 17.0.1.

## Objective
 The project consists in consuming data from a free, public data REST API. 
In this case, I have chosen https://openweathermap.org/api. 
This service provides current, historical and predictive weather data. All this following Publisher/Subscriber pattern.

## Description by module
Each element (sensor and predictor) will be project modules. While the broker
we will install it as if it were an application in the operating system.

Firstly, I had to register in the service and obtain an API key.
Then, I consulted documentation of 'Current weather data' API at https://openweathermap.org/current.
After that, I did some tests in the browser of my query before coding.
The following step was to select an HTTP client JAVA dependency, which finally was org.apache.httpcomponents:httpclient:4.5.13.

### Sensor
The sensor module has the task of obtaining, every 15 minutes, for example, the data of a city (London in this case) and generate an event in JSON format
from data obtained from the meteorological service.

The event has the following structure:  
ts: Timestamp in UTC of taking the measurements.  
location: Object with latitude and longitude fields.  

In addition, it includes the following mapped measures of the response of OpenWeatherMap:

• weather: From weather.main.  
• temp: Starting from main.temp.  
• wind: From wind.speed.  
• windDirection: From wind.deg.  
• humidity: From main.humidity.  
• pressure: From main.pressure.  

The metric used for temperature is expressed in Celsius degrees and speed in m/s. I specified this in the request to the OpenWeatherMap API.

The event is sent to 'sensor.Weather' topic of the Apache ActiveMQ broker.

### Datalake-builder
The task of this module is to generate the data lake that will be the data source for all the applications of the system.
It consumes the events coming from the topic sensor.Weather and will store them on disk in a directory 
with path root directory (given as the first argument argv[0]) plus datalake/events/sensor.Weather. Within the directory, the events are saved in files with the extension “.json” in such a way that the events are saved in temporal order and separated by time. 

Directory structure example:  
datalake/events/sensor.Weather/  
2021120701.json  
2021120702.json  
2021120703.json  
…  
      
So that in each json file the events are in turn ordered temporarily. 
The ts field of the event will be used as a temporary reference. 
If the sampling frequency is 15 minutes, approximately 4 events should enter each file.
Each event is separated from the previous one by a line break.

### Analytics
The task of this module is to exploit the data from sensorization, in order to carry out analyzes or predictive models. 
It consumes the events coming from the sensor.Weather topic and store the information in an SQLite table. 
The table is called weather and will have the following columns with their corresponding format:  

• long ts (epoch millis)  
• double lat  
• double lon  
• double temperature  
• double pressure  
• double humidity  

## Requirements
1) Install the ActiveMQ broker on your computer. For this you can follow the tutorial that the product developers give us. 
https://activemq.apache.org/getting-started. You can download the binary from here: https://activemq.apache.org/components/classic/download/.

2) To activate the broker once installed in your computer, you have to navigate to the installation directory and go to 'lib' folder. From there, open the
commands terminal and enter /activemq start. Then, search on your browser http://127.0.0.1:8161/ or http://localhost:8161/ and activeMQ broker will be automatically 
activated. When the broker starts up, by default it accepts connections on port 61616.


