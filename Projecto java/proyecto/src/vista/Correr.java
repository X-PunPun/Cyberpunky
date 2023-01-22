package vista;

public class Correr {

    public static void main(String[] args) {
        BarraCarga pre= new BarraCarga();
        pre.setVisible(true);
        Login iniciar= new Login();
        try{
            for(int i=0; i<=100; i++){
                Thread.sleep(40);
                pre.Porcentaje.setText(Integer.toString(i)+"%");
                pre.BarraCarga1.setValue(i);
                
                if(i==100){
                    pre.setVisible(false);
                    iniciar.setVisible(true);
                }
            }
        }catch (Exception e){
        }
    }
    
}
