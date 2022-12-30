# TuneUp

Tune Up is a dating website based on musical tastes. The project was realized within the context of the [Software Architecture](https://hecnet.unil.ch/hec/syllabus/descriptif/2458?dyn_lang=fr) course given by [prof. Benoît Garbinato](https://hecnet.unil.ch/hec/recherche/fiche?pnom=bgarbinato&dyn_lang=fr). To carry out this project I used technologies such as Java, XML, MySql or JakartaEE. TuneUp has an MVC architecture (model/view/controller).

The project is divided into 4 phases. In the [first phase](https://github.com/stefarine/TuneUp/tree/master/TuneUp-v1), I imagined the application. I defined the different functions of the application and I schematized the database. Then I implemented a first basic version of the application where the views are in the terminal.

<p align="center">
<img height=500 src="https://user-images.githubusercontent.com/57952280/210099088-5cb3cb60-3bf9-4680-878a-7ee5d94f8ad4.png">
</p>

During the [second phase](https://github.com/stefarine/TuneUp/tree/master/TuneUp-v2), I implemented the web views of the application. At this stage, visually the application is finished but the database is not persistent. I simply use a Java test class to simulate my database. 

For the [third phase](https://github.com/stefarine/TuneUp/tree/master/TuneUp-v3), I create persistence by linking my program to a MySql database. At this stage, the application is fully functional. 

Finally, in [phase 4](https://github.com/stefarine/TuneUp/tree/master/TuneUp-v4), for the needs of the course, I implement a restful api. The project is divided in two parts, a [client](https://github.com/stefarine/TuneUp/tree/master/TuneUp-v4/TuneUp-RestfulClient) and a [server](https://github.com/stefarine/TuneUp/tree/master/TuneUp-v4/hec.soar_TuneUp-RestfulService_war_1.0-SNAPSHOT). The client makes requests to the server to get or modify information in the database. 
