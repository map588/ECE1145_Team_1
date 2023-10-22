package hotciv.standard;

import hotciv.framework.*;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

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

 Please visit http://www.baerbak.com for further information.

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
public class gammaCiv_tests {
    private GameImpl game; //Changed Game to GameImpl -TPD

    /**
     * Fixture for gammaCiv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(GameType.gammaCiv, 2);
    }

    //..........Unit Tests...........//

    @Test
    public void settlerActionToCity(){
        //Player red has a settler at (3,4) in the test game constructor
        Position posSettler = new Position(3, 4);
        //game.setCityAt(posSettler, game.getUnitAt(posSettler).getOwner()); //old code
        game.performUnitActionAt(posSettler); //with refactoring
        assertThat(game.getCityAt(posSettler).getOwner(), is(Player.RED) );
        assertThat(game.getCityAt(posSettler).getSize(), is(1));
        assertNull(game.getUnitAt(posSettler));
    }

    @Test
    public void archerFortifyDoublesDefense(){ //simulate fortifying an archer
        Position posArcher = new Position(0,2);
        game.getUnitAt(posArcher).setDefensiveStrength(2);
        game.performUnitActionAt(posArcher);
        assertThat(game.getUnitAt(posArcher).getDefensiveStrength(), is(4));
    }
    @Test
    public void archerFortifyHalvesDefense(){ //simulate fortifying an already fortified archer
        Position posArcher = new Position(0,2);
        game.getUnitAt(posArcher).setDefensiveStrength(1);
        game.getUnitAt(posArcher).fortify();
        game.performUnitActionAt(posArcher);
        assertThat(game.getUnitAt(posArcher).getDefensiveStrength(), is(1));
    }

    //...............Integration Testing...................//

    @Test
    public void settlerActionToCityIntegrated(){
        //Player red has a settler at (3,4) in the test game constructor
        Position posSettler = new Position(3, 4);
        game.performUnitActionAt(posSettler);
        assertThat(game.getCityAt(posSettler).getOwner(), is(Player.RED) );
        assertThat(game.getCityAt(posSettler).getSize(), is(1));
        assertNull(game.getUnitAt(posSettler));
    }

    @Test
    public void archerFortifyDoublesDefenseIntegrated(){ //simulate fortifying an archer
        Position posArcher = new Position(0,2);
        game.getUnitAt(posArcher).setDefensiveStrength(2);
        game.performUnitActionAt(posArcher);
        assertThat(game.getUnitAt(posArcher).getDefensiveStrength(), is(4));
    }
    @Test
    public void archerFortifyHalvesDefenseIntegrated(){ //simulate fortifying an already fortified archer
        Position posArcher = new Position(0,2);
        game.getUnitAt(posArcher).setDefensiveStrength(1);
        game.getUnitAt(posArcher).fortify();
        game.performUnitActionAt(posArcher);
        assertThat(game.getUnitAt(posArcher).getDefensiveStrength(), is(1));
    }
}