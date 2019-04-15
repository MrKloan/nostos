# nostos [![Build Status](https://travis-ci.org/MrKloan/nostos.svg?branch=master)](https://travis-ci.org/MrKloan/nostos) [![codecov](https://codecov.io/gh/MrKloan/nostos/branch/master/graph/badge.svg)](https://codecov.io/gh/MrKloan/nostos)
> [Nostos](https://en.wikipedia.org/wiki/Nostos) is a Greek literary theme about journeying back home. Also, it is a thematic coding dojo for practicing Object Calisthenics.

## Specifications

*Nostos* is a webservice acting as an itineraries aggregator. Using an indefinite number of partner connectors, its goal 
is to compute and provide the optimal multimodal journeys. 

### Model

The domain model is composed of a set of domain *concepts*, each with their own *invariants*. There is no implementation
restriction, as long as the concepts are clearly present and their invariants are enforced.

#### Coordinates

`Coordinates` are composed of:
* A latitude, which is a floating point number between -90° and 90° exclusive;
* A longitude, which is a floating point number between -180° and 180° exclusive.

#### Vehicle

A `Vehicle` is composed of: 
* A vehicle number, which is an alphanumerical upper-cased string of 6 chars, padded with 0 on the left hand side if the string is shorter;
* A company name;
* An equipment, which is an alphabetical upper-cased string of 3 chars;
* A set of services, which are alphabetical upper-cased strings of 3 chars.

#### Stop point

A `Stop point` is composed of: 
* A label;
* Coordinates;
* An arrival date time;
* A departure date time, which must be posterior to its arrival date time.

#### Leg

A `Leg` is composed of:
* A type, which is a fixed value of either: `FOOT`, `CAR`, `BUS`, `SUBWAY`, `RAPID_TRANSIT`, `TRAIN`, `WAITING` or `OTHER`;
* Origin coordinates;
* Destination coordinates;
* A distance between its origin and its destination;
* A departure date time;
* An arrival date time, which must be posterior to its departure date time;
* A duration between its departure and its arrival;
* A set of stop points, which can be empty (ie. for a `FOOT` leg you should have an empty set of stop points, but for a `BUS` leg you could have many);
* A carbon footprint, which is a positive floating point value;
* A set of vehicles, which can be empty (ie. for a `FOOT` leg you should not have any vehicle);

#### Warning

A `Warning` is composed of:
* A code, which is an upper-cased alphanumerical string using underscores (`_`) as word separator;
* A message.

#### Journey

A `Journey` is composed of:
* A classification, which is a fixed value of either: `FASTEST`, `COMFORT` or `NONE`;
* A set of legs, which must contain at least 1 leg;
* Origin coordinates;
* Destination coordinates;
* A distance between its origin and its destination;
* A departure date time;
* An arrival date time, which must be posterior to its departure date time;
* A duration between its departure and its arrival;
* A carbon footprint, which is a positive floating point number;
* A set of warnings, which can be empty.

### Business rules

#### Multimodal journeys

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

#### Journey classification

Once the computation of your multimodal journeys is completed, you need to classify them. The classification rules are:

* A journey is `FASTEST` if it has the shortest duration;
* A journey is `COMFORT` if it has the least number of legs;
* Every other journey has a classification of `NONE`;
* If only one journey is returned, its classification is `NONE`.

### Product

Our final product is a web service. It should respond to a `GET` request with an input of: `origin`, `destination`, 
`departure_time`; and an output of: `journeys`, `warnings`.


## Object Calisthenics
> I encourage you to read [this excellent blog post by William Durand](https://williamdurand.fr/2013/06/03/object-calisthenics/).

The 9 rules of Object Calisthenics are:

1. Only one level of indentation per method;
2. Don't use the `else` keyword;
3. Wrap all primitives and strings;
4. First class collections;
5. One dot per line;
6. Don't abbreviate;
7. Keep all entities small;
8. No classes with more than two instance variables;
9. No getters/setters/properties.