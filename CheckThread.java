//import java.util.ArrayList;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//
//public class CheckThread extends Thread implements Runnable {
//    static double time;
//    private static ExecutorService threadExecutor = Executors.newCachedThreadPool();
//    ArrayList<ShootThread> shootThread = new ArrayList<ShootThread>();
//
//    @Override
//    public void run() {
//
//        while (ControlTower.airPort.getHp() != 0 || ControlTower.savedAircrafts.size() != 0) {
//            for (int i = 0; i < ControlTower.aircrafts.size(); i++) {
//                if (ControlTower.aircrafts.get(i) instanceof Fighter) {
//                    for (int j = 0; j < ControlTower.antiAircrafts.size(); j++) {
//                        if (ControlTower.distance(ControlTower.aircrafts.get(i).getCoordinate(), ControlTower.antiAircrafts.get(j).getCoordinate()) <= ControlTower.antiAircrafts.get(j).getRange()
//                                && ControlTower.antiAircrafts.get(j).fighterInRange.contains( ControlTower.aircrafts.get(i) ) == false ) {
//
//                            ControlTower.antiAircrafts.get(j).fighterInRange.add( (Fighter) ControlTower.aircrafts.get(i));
//                            if( ControlTower.antiAircrafts.get( j ).fighterInRange.size() == 1 ) {
//                                shootThread.add(new ShootThread( ControlTower.antiAircrafts.get(j) ) );
//                                threadExecutor.execute( shootThread.get( shootThread.size()-1 ) );
//                            }
//
//                        }
//                    }
//                }
//            }
//            for (int i = 0; i < ControlTower.antiAircrafts.size(); i++){
//                for (int j = 0; j < ControlTower.antiAircrafts.get(j).fighterInRange.size() ; j++){
//                    if ( ControlTower.distance( ControlTower.antiAircrafts.get(i).fighterInRange.get(j).getCoordinate(),
//                            ControlTower.antiAircrafts.get(j).getCoordinate() ) > ControlTower.antiAircrafts.get(j).getRange() ){
//                        ControlTower.antiAircrafts.get(i).fighterInRange.remove( (Fighter) ControlTower.antiAircrafts.get(i).fighterInRange.get(j));
//                    }
//                }
//            }
//
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                System.out.println("Intrrupted");
//            }
//        }
//    }
//}
//
//
