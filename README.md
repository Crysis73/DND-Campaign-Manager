# Final-Project-Team-Bravo

Authors: Casey Haskins <cahaskins@bsu.edu>, Chance Morgan <ckmorgan2@bsu.edu>, Hailey Carlson <hdcarlson@bsu.edu>, Rebecca Auger <raauger@bsu.edu>

For our final project in CS222, we decided to make a DND Campaign Manager. Our program allows for a DM to create and manage existing campaigns using an intuitive user interface. Users can create a new campaign, create characters for that campaign, save/load campaigns, and manage character stats/inventories. 

Few warnings are suppressed in our project. There is a "EmptyMethod" warning because we left placeholder methods for functions we plan to implement. We also have a "WeakerAccess" warning that's suppressed in our controller class. Our FXML document needs access to the Controller class, and if we accept intellij's suggested level of access, the FXML file no longer can access its' Controller class. Additionally, we have one one suppressed "SameParameterValue" warning in our because it won't be an issue once the rest of the dndClass functionality is added. Furthermore, we suppressed "JavaFxUnresolvedFxIdReference" because we have a lot of elements in our FXML file that will be added in future iterations that were removed in this one to satisfy requirements that are not referenced because they were commented out. 
