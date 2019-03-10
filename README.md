Shop Management
========================
#### [Live Demo](http://li118-165.members.linode.com:8080)

Just a very simple Spring Boot Web App to showcase my skills.

*Please use Google Chrome. It seems Safari does not support &lt;input type="date"&gt;.*

### Purpose

One of the facilities of an e-commerce application is to manage merchants. Some merchant's shops are only open
for a certain period of days. This humble web app wishes to provide a facility to update shop active dates in bulk by 
importing a CSV file. Additionally, a search facility is provided to search for opened shops for a given day.

#### Example of CSV
    shop,start_date,end_date
    1,20200401,20200402
    2,20200402,20200403

### Technological Considerations
#### Architecture 

It is worthwhile to discuss the architecture behind the trivial application. The architecture is designed after the
inspiration of vertical slices as presented [here](http://olivergierke.de/2013/01/whoops-where-did-my-architecture-go/) 
by Oliver Gierke, the Spring Data Project Lead at Pivotal. Coincidentally, he is also an advocate of domain driven design. 
Furthermore, I have listed all the technologies that I've used in this project. This may be overkill for such a trivial
application but if this application were to be extended, I believe the architecture is strong enough to accommodate the
increasing complexity of the business domain.

#### Back-end Technologies

1. Java
2. JUnit5
3. AssertJ
4. Spring Boot
5. REST
6. Bean Validation 2.0 (JSR-380)
7. JPA
8. Hibernate

#### Front-end Technologies
1. HTML5
2. CSS3
3. ReactJS

#### Dev-ops Technologies
1. Git
2. Liquibase
3. Maven
4. Docker
5. Ansible

### Copyright
Copyright © 2019, Emmett Young, All rights reserved.

No reproduction and usage are allowed in whole or in part for distribution, personal and commercial purposes.