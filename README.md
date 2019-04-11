# nostos [![Build Status](https://travis-ci.org/MrKloan/nostos.svg?branch=master)](https://travis-ci.org/MrKloan/nostos) [![codecov](https://codecov.io/gh/MrKloan/nostos/branch/master/graph/badge.svg)](https://codecov.io/gh/MrKloan/nostos)
> [Nostos](https://en.wikipedia.org/wiki/Nostos) is a Greek literary theme about journeying back home. Also, it is a thematic coding dojo for practicing Object Calisthenics.

## Specifications

*Nostos* is a webservice acting as an itineraries aggregator. Using an indefinite number of partner connectors, its goal 
is to compute and provide the optimal multimodal journeys. 

The domain model is described as follows:

* Journey: journey classification, legs, departure time, arrival time, duration, carbon footprint, warnings.
* Journey classifications: `FASTEST`, `COMFORT`, `NONE`.
* Warning: code, message.
* Leg: leg type, origin coordinates, destination coordinates, distance, departure time, arrival time, duration, stop points, carbon footprint, vehicles.
* Leg types: `FOOT`, `CAR`, `BUS`, `SUBWAY`, `RAPID_TRANSIT`, `TRAIN`, `WAITING`, `OTHER`.
* Stop point: label, coordinates, arrival time, departure time, duration.
* Vehicle: number, company, offer managers, equipment, services.

A predefined set of 2 partners must be implemented through connectors. The first one will gather *Train* journeys - 
journeys only composed of `TRAIN` legs; while the second one will gather *Public Transport* journeys - journeys composed
of any type of legs. 

Please note that you may need to propagate warnings along with the journeys from the partner response. 

Our goal is to create *multimodal journeys* from point *A*, which we will call `origin`, to point *B*, which we will call
`destination`, starting at a given point in time. In our context, this only means combining the journeys of our *Train* 
partner with the ones of our *Public Transport* partner. The following business rules must be implemented:

* Send a query to the *Train* partner from `origin` to `destination` at a given `departure_time`;
* Call the *Public Transport* partner to fill the potential gaps between `origin`, the gathered `TRAIN` legs, and `destination`;
* If the *Train* partner does not return any journey, call the *Public Transport* partner from `origin` to `destination` with the same `departure_time`.

Once the computation of your multimodal journeys is completed, you need to classify them. The classification rules are:

* A journey is `FASTEST` if it has the shortest duration;
* A journey is `COMFORT` if it has the least number of legs;
* Every other journey has a classification of `NONE`;
* If only one journey is returned, its classification is `NONE`.

### TL;DR

* **Input**: `origin`, `destination`, `departure_time`.
* **Output**: `journeys`, `warnings`.
