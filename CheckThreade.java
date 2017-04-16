import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CheckThreade extends Thread implements Runnable {
    static double time;
    private static ExecutorService threadExecutor = Executors.newCachedThreadPool();

    @Override
    public void run() {

        while (controlTower.airPort.getHp() != 0 || controlTower.savedAircrafts.size() != 0) {
            for (int i = 0; i < controlTower.aircrafts.size(); i++) {
                if (controlTower.aircrafts.get(i) instanceof Fighter) {
                    for (int j = 0; j < controlTower.antiAircrafts.size(); j++) {
                        if (controlTower.distance(controlTower.aircrafts.get(i).getCoordinate(), controlTower.antiAircrafts.get(j).getCoordinate()) <= controlTower.antiAircrafts.get(j).getRange()
                                && controlTower.antiAircrafts.get(j).fighterInRange.contains( controlTower.aircrafts.get(i) ) == false ) {

                            controlTower.antiAircrafts.get(j).fighterInRange.add( (Fighter) controlTower.aircrafts.get(i));
                            //threadExecutor.execute( shootThread );

                        }
                    }
                }
            }
            for (int i = 0; i < controlTower.antiAircrafts.size(); i++){
                for ( int j =0 ; j < controlTower.antiAircrafts.get(j).fighterInRange.size() ; j++){
                    if ( controlTower.distance( controlTower.antiAircrafts.get(i).fighterInRange.get(j).getCoordinate(),
                            controlTower.antiAircrafts.get(j).getCoordinate() ) > controlTower.antiAircrafts.get(j).getRange() ){
                        controlTower.antiAircrafts.get(i).fighterInRange.remove( (Fighter) controlTower.antiAircrafts.get(i).fighterInRange.get(j));
                    }
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Intrrupted");
            }
        }
    }
}


