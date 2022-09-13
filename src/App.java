// Tanner Bacon, Jenna Habel, Parker Schmidt, Benjamin Kloepfer, Jialing Lu

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
  /**
   * @param args
   */
  public static void main(String args[]) {
    // INITIALIZE VARIABLES
    String line = "";
    List<List<String>> namesList = new ArrayList<>();
    int numStudents = namesList.size();
    

    // READ CSV FOR NAMES
    try (BufferedReader readFile = new BufferedReader(new InputStreamReader(new FileInputStream("./lib/names.csv"), "UTF-8"))) {
    
      // GET NAMES LINE BY LINE FROM CSV
      while ((line = readFile.readLine()) != null) {
        String[] namesArray = line.split("\n");
        namesList.add(Arrays.asList(namesArray));        
      }

      // GET NUMBER OF STUDENTS FOR DIVIDING TEAMS
      numStudents = namesList.size();

      System.out.println("num students: " + numStudents);
      System.out.println("list size: " + namesList.size());

      // CONFIRM LIST AND SUCCESSFUL RETRIEVAL
      System.out.println(namesList + "\n");
      System.out.println("Names successfully collected.\n");

    } catch (IOException e) {
      // CATCH BLOCK IN CASE OF UNSUCESSFUL RETRIEVAL OF NAMES
      e.printStackTrace();
    }

    // SHUFFLE THE ARRAY OF NAMES
    ShuffleArray(namesList);

    // ADD NAMES TO TEAMS
    AssignTeams(namesList, numStudents);
  }

  static List<List<String>> ShuffleArray(List<List<String>> namesList) {
    System.out.println("Shuffling names...");

    // SHUFFLE LIST
		Collections.shuffle(namesList);

    // PRINT SCRAMBLED LIST AND LIST SIZE
    System.out.println(namesList);
    System.out.println("List size: " + namesList.size());

    /*
     * LIST FORMAT LOOKS LIKE THIS ->
     * [["Last, First"], ["Donaldson, Jacob"], ["Lunt, Owen Spencer"], ["Perry, Matt"]]
     */

    // RETURN SHUFFLED LIST
    return namesList;
	}

    // ASSIGN TEAMS FUNCTION
    static void AssignTeams(List<List<String>> namesList, int numStudents) {
      
    // GET NUMBER OF TEAMS FROM USER
    System.out.println("How many teams do you want?");
    int numTeams;
    try (Scanner input = new Scanner(System.in)) {
      String numTeamsInput = input.nextLine();
      numTeams = Integer.valueOf(numTeamsInput);
    }

    // INITIALIZE TEAM VARIABLES
    int teamSize = numStudents/numTeams;
    String newTeam[][] = new String[numTeams][teamSize + 1];
    int k = 0;
  
    // ENSURE THAT THE LIST INCLUDES ALL STUDENTS
    if (namesList.size() >= (numStudents)) {

      // THIS LOOP ENSURES THAT EACH TEAM IS FILLED TO FULL CAPACITY
      // IF THE NUMBER OF STUDENTS DOESN'T FIT EQUALLY INTO THE NUMBER OF TEAMS, SOME TEAMS WILL HAVE 1 LESS STUDENT
      for (int j = 0; j <= (teamSize); j++) {

        // ADD STUDENT TO TEAM 1, THEN TEAM 2, THEN TEAM 3, ETC.
        // THIS IS LIKE DEALING A CARD TO EACH PLAYER AT A TABLE
        for (int i = 0; i < numTeams; i++) {
 
          // STOP WHEN THE STUDENTS HAVE ALL BEEN ASSIGNED TEAMS
          // THIS IF STATEMENT ALLOWS YOU TO ADD ANY REMAINDERS TO A TEAM
          if (k == namesList.size()) { 
            break;
          }

          // TODO: REMOVE THIS LINE AFTER TESTING
          // System.out.println("TEAM: " + (i+1) + " Student: " + (j+1));

          // THIS WILL ASSIGN THE NEXT STUDENT TO A TEAM
          newTeam[i][j]  = namesList.get(k).toString();
   
          // ITERATE TO NEXT STUDENT
          k++; 
        }

      }      
    }

    // THIS WILL PRINT THE ARRAY OF ARRAYS
    Printing(teamSize, numTeams, newTeam);
  }
  
  public static void Printing(int teamSize, int numTeams, String[][] newTeam) {

    // THIS LOOP WILL TRANSPOSE THE MATRIX SO THAT IT WILL PRINT ALL OF TEAM 1, THEN ALL OF TEAM 2, ETC
    for (int i = 0; i < (numTeams); i++) {

      // FIRST WE PRINT THE TEAM NAME
      System.out.println("Team #" + (i+1));

      // THEN WE PRINT ALL MEMBERS OF THE TEAM
      for (int j = 0; j <= teamSize; j++) {

        // THIS WILL ENSURE THAT IF SOME TEAMS HAVE 1 LESS PERSON THAT WE DO NOT PRINT "NULL" FOR THEIR LAST TEAM MEMBER
        if (newTeam[i][j] != null) {
          System.out.println(newTeam[i][j]);
        } else {
          continue;
        }
      }

    }
  }
}