# Final-Project-Team-Bravo

Authors: Casey Haskins <cahaskins@bsu.edu>, Rebecca Auger <raauger@bsu.edu>

For our final project in CS222, we decided to make a DND Campaign Manager. Our program allows for a DM to create and manage existing campaigns using an intuitive user interface. Users can create a new campaign, create characters for that campaign, save/load campaigns, and manage character stats. 

There are a couple warning suppressed in our project. ALL warnings are suppressed for the Equipment class because that class is not currently being used. In our Character class we supressed the unused method warning because we have several methods that will be used in the future that we just have not implemented yet. In our Description class we suppressed the unused method warning because several of our methods that the compiler thinks aren't used are in fact used in our GUI. We also have a SameParameterValue warning suppressed for the setPersonalityTrait2 method because we only used that method once in our tests to set a second personality trait, so the compiler thinks that the value of personalityTrait2 is always cool. For our dndClass class, we suppressed the warning for UnusedMethod. The unused method warning is supressed because the unused methods will have use in the future. In our JsonLoader class, we suppressed the unused return value warning because the return value of the method will be used in the future.
A section of the GUIFile.fxml is commented out. This will be used for adding character equipment in the future.
