[![Build Status](https://travis-ci.org/flaviojmendes/fitbit-support-services.svg?branch=master)](https://travis-ci.org/flaviojmendes/fitbit-support-services)
[![Maintainability](https://api.codeclimate.com/v1/badges/b930af797dd812d9b2b5/maintainability)](https://codeclimate.com/github/flaviojmendes/fitbit-support-services/maintainability)

## Fitbit Support Services

This service works in order to make easier the life of a Fitbit Developer.

For now it is supporting weather retrievals for DarkSky and OpenWeatherMap API.


### How to start

To start the service simply execute:

```
./gradlew build && ./gradlew buildDocker && docker-compose up -d
```

### How to use

Fist you need to add some API Keys:

```
HTTP POST
http://{domain}/apiKey/{DARKSKY|OPENWEATHER}/

BODY
{key1},{key2},...,{keyN}

```

To retrieve weather:

```
HTTP GET
http://{domain}/weather/{DARKSKY|OPENWEATHER}/{latitude}/{longitude}/

```
