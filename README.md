# UBC Campus Tours

## Purpose of the Application:
UBC Campus Tours is an application that provides self-guided tours to prospective and incoming students. Many incoming students find that campus tours augment their university experience with a smooth transition from high school. Additionally, prospective students and their family find visiting campus to be the best way to experience life at their future institution. Therefore, UBC offers scheduled campus tours to all students who desire to explore campus. However, there is a high demand on non-scheduled, customizable tours that allow each student to experience campus autonomously. Highlight features of the app include:

- **Customizable** list of campus stops
- **Keeping track** of visited and unvisited stops 
- **Recommended** food places
- **Faculty-specific** tours

## Target Audience:
-	Prospective students deciding whether UBC is right for them
-	Prospective students choosing which UBC campus is right for them
-	Incoming students
-	2020-2021 cohort that experience campus only virtually

## Personal Motivation:
As a current *Student Ambassador*, I had given over 200 tours around the UBC campus and chatted with over 20,000 students about my experience of student life. I noticed that tours were more effective when they were targeted for specific group (for example a prospective student interested in learning about the Computer Science program at UBC versus an international student incoming student interested in learning about UBC Athletics). Additionally, students are more confident making their university decision when they learnt everything they needed to know. This explains the demand on something that keeps track of the student’s knowledge about the campus. I am also interested in working with the Emerging Media Lab on making a VR of UBC campus which could go hand in hand with this application.

### User Stories:
-   As a user, I want to be able to add my own tour stops
-   As a user, I want to be able to specify the type of my tour stops
-	As a user, I want to be able to view a mapping of the stops I still need to visit
-	As a user, I want to be able to view a mapping of the stops I had already visited
-   As a user, I want to be able to visit my tour stops
-   As a user, I want to be able to mark stops I want to revisit
-   As a user, I want to be able to get my tour stops customized according my faculty
-   As a user, I want to be able to get recommended food places with each stop (will be implemented in next phase)
-   As a user, I want to be able to save my upcoming tour route to file 
-   As a user, I want to be able to be able to load my upcoming tour route from file 

### Sources:
CPSC210 Instructors (2021) JsonSerializationDemo Source Code (Version 1.0) [Source Code] : https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

### Phase 4 Task 2:
I have chosen to implement a Map interface in the TourRoute class where the TourRoute consists of two HashMaps of String keys and TourStop values.

### Phase 4 Task 3:
If I had more time to work on the project, I would:

- **Reduce code clones** in TourStop instantiation according to user's input. 
  In the existing program, instantiation using one of TourStop subclasses (one of Library, Museum, FacultyBuilding, Garden) depended on the user's preference which caused code clones in CampusToursConsole and TourRouteGui. This can
  be simply resolved by pulling the repeated piece of code into a method in TourRoute and call that from CampusToursConsole.
  Another way is to rethink the relationship
  between TourStop and the four subclasses.
  It might be best to use an enumeration in the context of the existing program where
  there is not so much difference between the functionality of each tour stop type. However, I kept it as such to leave
  room for additional features that are unique to each tour stop type.
  
  
- **Reduce code clones** between CampusToursConsole and TourRouteGui by pulling code clones of `saveTourRoute()` and `SaveStopListener` class as well as
   `loadTourRoute()` and `LoadStopListener` class into methods.
  
  
- **Increase cohesion and improve Single Responsibility principle**
    in the TourRouteGui. This class had many responsibilities such as building the buttons, placing the buttons,
    building the title, placing the title elements. This was evident through the existing code clusters in the class.
    This can be resolved by extracting those clusters into separate classes.
  

- **Reduce semantic coupling** between CampusToursConsole and TourRoute
specifically when implementing the `recommendArea()` in `TourRoute` class
  and `allowCustomization()` in `CampusToursConsole` class. It is unlikely that I would need
  to change the area and faculty relationship since my implementation took inspiration from
  the urban plan of UBC which is unlikely to change. However, the default area I have is
  "North", and I might want to change that in the future. In that case, it would be best to extract the faculty-area customization code into a separate method
  to keep a single point of control.
  


