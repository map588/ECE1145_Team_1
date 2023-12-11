package hotciv.standard;
import hotciv.framework.ManagerFactory;
import hotciv.framework.GameType;
import hotciv.manager_factories.*;


public class ManagerFactoryFactory {

        public static ManagerFactory getManagerFactory(String gameType) {
            switch (gameType) {
                case "alphaCiv":
                    return new alphaManagerFactory();
                case "betaCiv":
                    return new betaManagerFactory();
                case "gammaCiv":
                    return new gammaManagerFactory();
                case "deltaCiv":
                    return new deltaManagerFactory();
                case "epsilonCiv":
                    return new epsilonManagerFactory();
                case "zetaCiv":
                    return new zetaManagerFactory();
                case "etaCiv":
                    return new etaManagerFactory();
                case "semiCiv":
                    return new semiManagerFactory();
                case "thetaCiv":
                    return new thetaManagerFactory();
                default:
                    return null;
            }
        }
}
