import java.util.Scanner;

public class Main {

    static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean salir= false;

        while (!salir){
            ConsolaUtil.mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();


            if (opcion == 0 ){
                salir=true;
                System.out.println("Gracias por usar el conversor de moneda.");
                continue;
            }
            System.out.println("Cantidad: ");
            double cantidad = scanner.nextDouble();
            scanner.nextLine();

            ConversorService.realizarConversion(opcion,cantidad);
        }
        scanner.close();

    }

}