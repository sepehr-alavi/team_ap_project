
public class ShootThread extends Thread implements Runnable{
    AntiAircraft shootingAircraft;

    public ShootThread( AntiAircraft shootingAircraft ){
        this.shootingAircraft = shootingAircraft;
    }

    @Override
    public void run() {
        while( shootingAircraft.fighterInRange.size() > 0 ){
            for( int i = 0; i<shootingAircraft.fighterInRange.size(); i++ ){
                shootingAircraft.shoot( shootingAircraft.fighterInRange.get( i ) );
            }
            try{
                if( shootingAircraft instanceof Cannon ){
                    Cannon shootingCannon = (Cannon)shootingAircraft;
                    Thread.sleep( shootingCannon.getFireRate()*1000 );
                }
                else if( shootingAircraft instanceof MachineGun )
                    Thread.sleep( 1000 );
            } catch(InterruptedException e ){
                System.out.println("Interrupted");
            }
        }
    }
}
