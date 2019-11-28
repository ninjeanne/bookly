# Bookly - Software Architecture Document

### Version 1.0

# Revision history

| Date       | Version | Description                                          | Author           |
|------------|---------|------------------------------------------------------|------------------|
| 28/11/2019 | 1.0     | Initial Documentation                                | Alexandra Stober |
| 			 |  	   |                                                      |                  |

# Table of Contents
- [Introduction](#1-introduction)
    - [Purpose](#11-purpose)
    - [Scope](#12-scope)
    - [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
    - [References](#14-references)
    - [Overview](#15-overview)
- [Architectural Representation](#2-architectural-representation)
- [Architectural Goals and Constraints](#3-architectural-goals-and-constraints)
- [Use-Case View](#4-use-case-view)
- [Logical View](#5-logical-view)
    - [Overview](#51-overview)
    - [Architecturally Significant Design Packages](#52-architecturally-significant-design-packages)
- [Process View](#6-process-view)
- [Deployment View](#7-deployment-view)
- [Implementation View](#8-implementation-view)
    - [Overview](#81-overview)
    - [Layers](#82-layers)
- [Data View](#9-data-view)
- [Size and Performance](#10-size-and-performance)
- [Quality/Metrics](#11-qualitymetrics)

## 1. Introduction

### 1.1 Purpose

This document provides a comprehensive architectural overview of the system, using a number of different architectural 
views to depict different aspects of the system. It is intended to capture and convey the significant architectural 
decisions which have been made on the system.

### 1.2 Scope

This document describes the technical architecture of the bookly project, including module structure and dependencies as 
well as the structure of classes.

### 1.3 Definitions, Acronyms and Abbreviations

| Abbreviation | Description                            |
| ------------ | -------------------------------------- |
| API          | Application programming interface      |
| MVC          | Model View Controller                  |
| REST         | Representational state transfer        |
| SDK          | Software development kit               |
| SRS          | Software Requirements Specification    |
| UC           | Use Case                               |
| VCS          | Version Control System                 |
| N/A          | Not Applicable                         |

### 1.4 References

| Reference                                                                        						    | Date       |
|-----------------------------------------------------------------------------------------------------------|------------|
| <a href="https://blog.bookly.online/">Bookly Blog</a>                         						    | 28/10/2019 |
| <a href="https://gitlab.com/project_bookly/bookly">GitLab Repository</a>         				            | 28/10/2019 |
| <a href="https://nicoschinacher.myjetbrains.com/youtrack/issues?q=project:%20bookly">YouTrack</a>			| 28/10/2019 |

### 1.5 Overview

This document contains the architectural representation, goals and constraints as well as logical, deployment, 
implementation and data views.

## 2. Architectural Representation

Our project bookly uses the classic MVC structure as follows:

## 3. Architectural Goals And Constraints

As our main technology we decided to use Spring MVC, which is a framework that takes not only care of the backend but 
also of the frontend. Besides the controller and model language is Java, so that we do not have to care about 
serialization. 


## 4. Use-Case View

This is our overall use-case diagram:

<img src="./design/usecase.png" alt="Overall use-case diagram" />

## 5. Logical View

### 5.1 Overview

We split our architecture according to the MVC architecture as follows:

ADD IMAGE

We are working with maven modules, because it allows us to modularize the project better. We defined our module 
structure as follows:

```
|--???????
|   |-- application
|   |-- model
|   |-- shared
|   |-- util
``` 

For this maven module structure has the following dependency graph:

ADD PICTURE MAVEN STRUCTURE


### 5.2 Architecturally Significant Design Packages

The module `???????` contains all controllers, the main application as well as all views. In this module, 
the Spring MVC framework is realized. However, the module `??????` is outsources, so that the controllers 
cannot directly access the database. This is all handled by the class `???????` which represents the interface 
between Controllers and Models. 


## 6. Process View

N/A

## 7. Deployment View

This is our deployment view:

ADD IMAGE Deployment

We only have one instance of an application server due to lack of capacity. The database is running on the 
server  as well due to the same reason.

## 8. Implementation View

N/A

## 9. Data View

Our data view is modelled as followed:

<img src="./design/DatabaseERM.png" alt="Data View" />


## 10. Size and Performance

N/A

## 11. Quality/Metrics

To ensure a high quality we are using continuous integration. It automatically builds, tests, 
measures and deploys the application, if the respective previous step has not failed. This happens periodically and when 
changes are pushed to a branch. When merging the master branch into the deployment branch, the application will 
automatically be deployed as well after pushing the button.


ADD SWAGGER


## 12. Patterns

N/A