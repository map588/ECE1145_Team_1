package hotciv.standard;

import hotciv.framework.GameType;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.ARCHER;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/** Skeleton class for AlphaCiv test cases

 Updated Oct 2015 for using Hamcrest matchers

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
public class etaCiv_tests {
    private GameImpl game;

    @Before
    public void setUp() {
        game = new GameImpl(GameType.etaCiv, 2);
    }

    // CITY WORKFORCE FOCUS TESTS...

    @Test
    public void productionFocus() {
        // When a city is set to production focus, the citizens are assigned to the tiles adjacent
        // to the city that offer the most production. The city must have a citizen assigned to it.
    }

    @Test
    public void foodFocus() {
        // When a city is set to food focus, the citizens are assigned to the tiles adjacent
        // to the city that offer the most food. The city must have a citizen assigned to it.
    }


    // CITY POPULATION TESTS...
    @Test
    public void populationGrowth() {
        // population size in the city increases by one once the total collected food
        // in the city exceeds 5 + (city size)*3 .
    }

    @Test
    public void maxPopulation() {
        // population size is capped at 9
    }

    @Test
    public void foodResetOnPopulationIncrease() {
        // food count is reset to 0 when the population increases.
    }
}