# Contributing
* Please read the general FredBoat contribution guide [FredBoat contributing](https://github.com/Frederikam/FredBoat/blob/dev/CONTRIBUTING.md)
* Pull requests must contain unit tests related to your changes

## Prerequisite

* Add `quarterdeck.yaml` files to the FredBoat backend root directory.
* This project uses swagger as a documentation/client to test the REST api. After running the Application class or starting it via ``./gradlew bootRun``, head over to [http://localhost:[port_number]/swagger-ui.html#/](http://localhost:4269/swagger-ui.html#/)
  * Use username and password from `quarterdeck.yaml` as swagger authentication

## Unit test requirement

* These project's tests use [Spring MockMvc](https://docs.spring.io/spring-security/site/docs/current/reference/html/test-mockmvc.html) to perform http requests. Please see existing code for details.
* Unit test method naming convention `"if/whenConditionThenExpectedState/Behavior"` 

## Join Developer Chat

Are you planning to contribute and have burning questions not answered here? Please be invited to join [FredBoat Hangout](https://discord.gg/cgPFW4q) and request an admin for writing access to the `#backend-database` channel.