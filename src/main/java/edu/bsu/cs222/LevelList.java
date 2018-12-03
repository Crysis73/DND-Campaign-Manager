package edu.bsu.cs222;

import java.util.LinkedList;

class LevelList {
    private final LinkedList<Level> levels;

    LevelList(){
        this.levels = new LinkedList<>();
        Level one = new Level(1,0);
        Level two = new Level(2,300);
        Level three = new Level(3,900);
        Level four = new Level(4,2700);
        Level five = new Level(5,6500);
        Level six = new Level(6,14000);
        Level seven = new Level(7,23000);
        Level eight = new Level(8,34000);
        Level nine = new Level(9,48000);
        Level ten = new Level(10,64000);
        Level eleven = new Level(11,85000);
        Level twelve = new Level(12,100000);
        Level thirteen = new Level(13,120000);
        Level fourteen = new Level(14,140000);
        Level fifteen = new Level(15,165000);
        Level sixteen = new Level(16,195000);
        Level seventeen = new Level(17,225000);
        Level eighteen = new Level(18,265000);
        Level nineteen = new Level(19,305000);
        Level twenty = new Level(20,355000);
        levels.add(one);
        levels.add(two);
        levels.add(three);
        levels.add(four);
        levels.add(five);
        levels.add(six);
        levels.add(seven);
        levels.add(eight);
        levels.add(nine);
        levels.add(ten);
        levels.add(eleven);
        levels.add(twelve);
        levels.add(thirteen);
        levels.add(fourteen);
        levels.add(fifteen);
        levels.add(sixteen);
        levels.add(seventeen);
        levels.add(eighteen);
        levels.add(nineteen);
        levels.add(twenty);
    }

    LinkedList<Level> getLevels(){
        return this.levels;
    }
}
