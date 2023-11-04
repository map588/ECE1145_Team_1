package hotciv.framework;

import java.util.HashMap;

/** Collection of constants used in HotCiv Game. Note that strings are
 * used instead of enumeration types to keep the set of valid
 * constants open to extensions by future HotCiv variants.  Enums can
 * only be changed by compile time modification.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class GameConstants {
  // The size of the world is set permanently to a 16x16 grid 
  public static final int WORLDSIZE = 16;
  // Valid unit types
  public static final String ARCHER    = "archer";
  public static final String LEGION    = "legion";
  public static final String SETTLER   = "settler";
  public static final String UFO       = "ufo";

  // Valid terrain types
  public static final String PLAINS    = "plains";
  public static final String OCEANS    = "ocean";
  public static final String FOREST    = "forest";
  public static final String HILLS     = "hills";
  public static final String MOUNTAINS = "mountain";

  public static final HashMap<String, Integer> unit_cost = new HashMap<String, Integer>() {{
    put(ARCHER, 10);
    put(LEGION, 15);
    put(SETTLER, 30);
    put(UFO, 60);
  }};

    //Attack strength
  public static final HashMap<String, Integer> unit_attack = new HashMap<String, Integer>() {{
    put(ARCHER, 2);
    put(LEGION, 4);
    put(SETTLER, 0);
    put(UFO, 1);
  }};

  //Defense strength
  public static final HashMap<String, Integer> unit_defense = new HashMap<String, Integer>() {{
    put(ARCHER, 3);
    put(LEGION, 2);
    put(SETTLER, 3);
    put(UFO, 8);
  }};

  //Move count
  public static final HashMap<String, Integer> unit_moveCount = new HashMap<String, Integer>() {{
    put(ARCHER, 1);
    put(LEGION, 1);
    put(SETTLER, 1);
    put(UFO, 2);
  }};

  //Terrain
  public static final HashMap<String, Boolean> unit_terrainTraversal = new HashMap<String, Boolean>() {{
    put(ARCHER, false);
    put(LEGION, false);
    put(SETTLER, false);
    put(UFO, true);
  }};

  //City will need to be accounted for differently, it has 1 food + 1 production per turn
  public static final HashMap<String, Integer> terrain_production = new HashMap<String, Integer>() {{
    put(PLAINS, 0);
    put(OCEANS, 0);
    put(FOREST, 3);
    put(HILLS, 2);
    put(MOUNTAINS, 1);
  }};

    public static final HashMap<String, Integer> terrain_food = new HashMap<String, Integer>() {{
        put(PLAINS, 3);
        put(OCEANS, 1);
        put(FOREST, 0);
        put(HILLS, 0);
        put(MOUNTAINS, 0);
    }};

}
