package model.Data;

import java.io.FileInputStream;
import java.io.IOException;
/**

A.in class level we *save* all the info of level, and on levelLoader we manage and create new level using the info from class level

B.This separation helps us use the open/closed principle because we have a class that keeps the info and another class for manage the info,
if one day we will want to load levels differently we will not have to change the class level , we will have to create another levelLoader
so - the code is open for changes(by creating new manage classes) but closed for editing(no need to edit the info class)  

C.the interface levelLOader create level object by using the subclasses (that implements levelLoader) and creating Level object with differents
codes ( each subclass load different type of file)

 D.we chose Inputstream and not String filename because with Inputstream we can read different types of file include txt, xml and obj files .
 each one of the LevelLoader subclass reads different type of file.
 
*/

public interface LevelLoader {
	
	public Level loadlevel(FileInputStream inputStream) throws IOException;
//	public Level levelloader = new Level();
}
