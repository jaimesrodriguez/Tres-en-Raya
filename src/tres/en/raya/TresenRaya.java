package tres.en.raya;
import java.lang.*;
import java.util.Scanner;
import java.util.Arrays;
/**
 *
 * @author jaimesaro
 */
public class TresenRaya {
    private char [][] campo;
    
    TresenRaya(){
        campo = new char[3][3];
        for(int f=0;f<3;f++){
            Arrays.fill(campo[f],' ');
        }
    }
    
    public static int verificarCoordenada(int num){
        Scanner sc = new Scanner(System.in);
        while(num<1 || num>3){
            System.out.println("Fuera de rango. Introduce otra coordenada.");
            num= sc.nextInt();
        }
        return(num);
    }
    
    public void turno(char jugador){
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Dónde vas a poner tu ficha?");
        System.out.println("Fila: ");
        int fil = verificarCoordenada(sc.nextInt())-1;
        System.out.println("Columna: ");
        int col = verificarCoordenada(sc.nextInt())-1;
        
        if(campo[fil][col]!=' '){
            System.out.println("Hueco ya lleno.");
            turno(jugador);
        }
        else{
            campo[fil][col]=jugador;
            mostrarTablero();
        }
    }
    public boolean filaVacia(int f){
        if(campo[f][0]==' ' && campo[f][1]==' ' && campo[f][2]==' '){
            return true;
        }
        return false;
    }
    public boolean tresenFila(){
        for(int f=0;f<3;f++){
            if(campo[f][0]==campo[f][1] && campo[f][1]==campo[f][2] && !filaVacia(f)){
                return(true);
            }
        }
        return(false);
    }
    public boolean columnaVacia(int c){
        if(campo[0][c]==' ' && campo[1][c]==' ' && campo[2][c]==' '){
            return true;
        }
        return false;
    }
    public boolean tresenColumna(){
        for(int c=0;c<3;c++){
            if(campo[0][c]==campo[1][c] && campo[1][c]==campo[2][c] && !columnaVacia(c)){
                return(true);
            }
        }
        return(false);
    }
    public boolean diagonalVacia(int d){
        for(int i=0; i<=1;i++){
            if(campo[2*d][0]==' ' && campo[1][1]==' ' && campo[2-2*d][2]==' '){
                return(true);
            }
        }
        return(false);
    }
    public boolean tresenDiagonal(){
        for(int i=0; i<=1;i++){
            if(campo[2*i][0]==campo[1][1] && campo[1][1]==campo[2-2*i][2] && !diagonalVacia(i)){
                return(true);
            }
        }
        return(false);
    }
    
    public boolean verificarVictoria(char jugador){
        if(tresenFila() || tresenColumna() || tresenDiagonal()){
            return(true);
        }
        return(false);
    }
    
    public void resultado(){
        if(verificarVictoria('X')){
            System.out.println("GANADOR: X");
        }
        else
            if(verificarVictoria('O')){
                System.out.println("GANADOR: O");
            }
            else{
                System.out.println("EMPATE");
            }
    }
    
    
    public void mostrarTablero(){
        System.out.println("   1 2 3");
        System.out.println("  +-+-+-+");
        for(int f=0;f<campo.length;f++){
            System.out.print((f+1)+"-|");
            for(int c=0;c<campo[f].length;c++){
                System.out.print(campo[f][c]+"|");
            }
            System.out.println("");
            System.out.println("  +-+-+-+");
        }   
    }
    
    public void partida(){
        mostrarTablero();
        for(int t=3;t>=0;t--){
            System.out.println("TURNO DE X:");
            turno('X');
            if(verificarVictoria('X')){break;}
            System.out.println("TURNO DE O:");
            turno('O');
            if(verificarVictoria('O')){break;}
        }
        
        if(!verificarVictoria('X') && !verificarVictoria('O')){
            System.out.println("EMPATE");
        }
    }
    
    public static void main(String[] args) {
        TresenRaya juego = new TresenRaya();
        juego.partida();
    }
}
