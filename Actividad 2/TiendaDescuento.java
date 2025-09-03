import java.util.Scanner;

public class TiendaDescuentos {

    // Constantes de descuento por tipo de producto
    public static final double DESCUENTO_ROPA = 0.10;       // 10%
    public static final double DESCUENTO_TECNOLOGIA = 0.05; // 5%
    public static final double DESCUENTO_ALIMENTOS = 0.02;  // 2%

    // Umbral de descuento adicional
    public static final double UMBRAL_TOTAL = 500000;
    public static final double DESCUENTO_ADICIONAL = 0.05; // 5%

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Variables acumuladoras
        double totalSinDescuento = 0;
        double totalConDescuento = 0;

        int contador = 0;

        while (true) {
            System.out.println("\n--- Producto " + (contador + 1) + " ---");
            System.out.print("Nombre del producto (o escriba 'fin' para terminar): ");
            String nombre = sc.nextLine();

            // Si el usuario escribe "fin", se termina la compra
            if (nombre.equalsIgnoreCase("fin")) {
                break;
            }

            int tipo = 0;
            // Validar tipo con números correctos
            while (true) {
                System.out.print("Tipo de producto (1: Ropa, 2: Tecnología, 3: Alimentos): ");
                if (sc.hasNextInt()) {
                    tipo = sc.nextInt();
                    if (tipo >= 1 && tipo <= 3) {
                        break; // válido
                    } else {
                        System.out.println("Tipo inválido. Intente de nuevo.");
                    }
                } else {
                    System.out.println("ERROR: debe ingresar un número (1, 2 o 3).");
                    sc.next(); // limpiar entrada inválida
                }
            }

            double precio = 0;
            // Validar precio como número positivo
            while (true) {
                System.out.print("Precio: ");
                if (sc.hasNextDouble()) {
                    precio = sc.nextDouble();
                    if (precio > 0) {
                        break; // válido
                    } else {
                        System.out.println("El precio debe ser mayor que 0.");
                    }
                } else {
                    System.out.println("ERROR: debe ingresar un valor numérico válido.");
                    sc.next(); // limpiar entrada inválida
                }
            }

            sc.nextLine(); // limpiar buffer

            double descuentoAplicado = 0;

            // Determinar descuento según tipo
            switch (tipo) {
                case 1:
                    descuentoAplicado = precio * DESCUENTO_ROPA;
                    break;
                case 2:
                    descuentoAplicado = precio * DESCUENTO_TECNOLOGIA;
                    break;
                case 3:
                    descuentoAplicado = precio * DESCUENTO_ALIMENTOS;
                    break;
            }

            double precioConDescuento = precio - descuentoAplicado;

            totalSinDescuento += precio;
            totalConDescuento += precioConDescuento;

            contador++;
        }

        // Si no ingresó productos
        if (contador == 0) {
            System.out.println("\nNo se ingresaron productos. Compra cancelada.");
            return;
        }

        // Verificar descuento adicional
        if (totalConDescuento > UMBRAL_TOTAL) {
            double descuentoExtra = totalConDescuento * DESCUENTO_ADICIONAL;
            totalConDescuento -= descuentoExtra;
            System.out.println("\nSe aplicó un descuento adicional del 5% por superar $" + UMBRAL_TOTAL);
        }

        // Cálculo del ahorro
        double ahorro = totalSinDescuento - totalConDescuento;

        // Mostrar resultados
        System.out.println("\n================= RESUMEN COMPRA =================");
        System.out.println("Total sin descuento: $" + totalSinDescuento);
        System.out.println("Total con descuentos: $" + totalConDescuento);
        System.out.println("Ahorro total: $" + ahorro);
    }
}